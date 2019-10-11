/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import communication.object.ClientMessage;
import communication.object.ClientResponse;
import communication.object.FileInfomation;
import communication.object.FileRequest;
import communication.object.FileResponse;
import communication.object.contanst.Task;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import master.model.FileServerModel;

/**
 *
 * @author hungt
 */
public class WorkerThread extends Thread {
    private Socket socket;
    private JTable client;
    private JTable server;
    private ClientTableModel clientTableModel = new ClientTableModel();
    private FileServerTableModel fileServerTableModel = new FileServerTableModel();
    
    public WorkerThread(Socket socket, ClientTableModel clientTableModel, JTable client, FileServerTableModel fileServerTableModel, JTable server) {
        this.socket = socket;
        this.server = server;
        this.client = client;
        this.clientTableModel = clientTableModel;
        this.fileServerTableModel = fileServerTableModel;
    }
 
    public void run() {
        System.out.println("Processing: " + socket);
        try {
            
            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // get the input stream from the connected socket
            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            
            Object object;
            while(socket.isConnected()){
                object = objectInputStream.readObject();
                if(object instanceof ClientMessage){
                    ClientMessage clientMessage = (ClientMessage) object;
                    Task task = clientMessage.getTask();
                    switch(task) {
                            case SEARCH:
                                searchFile(clientMessage, objectOutputStream);
                                break;
                            case FILE_SERVER_CLOSE:
                                handlerFileServerClosing(clientMessage);
                                break;
                            case CLIENT_PING:
//                                clientTableModel.add(socket.getInetAddress().toString(), socket.getPort());
//                                clientTableModel.fireTableDataChanged();
                                break;   
                            }
                } else if (object instanceof FileResponse) {
                    updateFileAvalibility((FileResponse) object);
                }   
        }
            
            
        } catch (IOException e) {
            System.err.println("Request Processing Error: " + e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("Complete processing: " + socket);
        
    }
    
    private String getClientAddress() {
        SocketAddress socketAddress = socket.getRemoteSocketAddress();
        String address = "";
        if (socketAddress instanceof InetSocketAddress) {
            InetAddress inetAddress = ((InetSocketAddress)socketAddress).getAddress();
            if (inetAddress instanceof Inet4Address || inetAddress instanceof Inet6Address)
                address =  inetAddress.toString();
            else
                System.err.println("Not an IP address.");
        } else {
            System.err.println("Not an internet protocol socket.");
        }
        return address.replace("/", "");
    }
    
    private void handlerFileServerClosing(ClientMessage clientMessage) {
        String address = getClientAddress();
        fileServerTableModel.removeFiles(address, clientMessage.getClientPort());
        fileServerTableModel.fireTableDataChanged();
    }
    
    private void updateFileAvalibility(FileResponse response){
        SocketAddress socketAddress = socket.getRemoteSocketAddress();
        String address = getClientAddress();
        Integer port = response.getPort();
        fileServerTableModel.add(response, address, port);
        fileServerTableModel.fireTableDataChanged();
    }
    
    private void searchFile(ClientMessage clientMessage, ObjectOutputStream objectOutputStream) throws IOException {
        List<FileServerModel> serverModels = fileServerTableModel.getModel();
        List<FileInfomation> result = new ArrayList<>();
        for(FileServerModel model : serverModels) {
            if(model.getFileName().equalsIgnoreCase(clientMessage.getParameters())) {
                FileInfomation fi = new FileInfomation();
                fi.setFileName(model.getFileName());
                fi.setIpAddress(model.getIpAddress());
                fi.setPort(model.getPort());
                result.add(fi);
            }
        }
        ClientResponse response = new ClientResponse();
        response.setResult(result);
        objectOutputStream.writeObject(response);
    }
    
}
