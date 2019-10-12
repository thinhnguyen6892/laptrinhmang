/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 *
 * @author hungt
 */
public class DownloadRunnable implements Runnable{
    
    private File dir;
    private String fileId;
    private String fileName;
    private String ip;
    private String port;
    
    public DownloadRunnable(File dir, String fileId, String fileName, String ip, String port) {
        this.dir = dir;
        this.fileId = fileId;
        this.fileName = fileName;
        this.ip = ip;
        this.port = port;
    }
    
    
    private void makeAckToFileServer(File file, String fileId, String addressString, Integer port) throws IOException {
        String selectedFile = fileId;
        DatagramSocket socket = new DatagramSocket();
        byte[] selectedFileData = selectedFile.getBytes();
        InetAddress address = InetAddress.getByName(addressString);
        DatagramPacket fileAck = new DatagramPacket(selectedFileData, selectedFileData.length, address, port);
        socket.send(fileAck);
        //Wait for receiving
        FileOutputStream outToFile = new FileOutputStream(file);
        receivingFile(outToFile, socket);
        JOptionPane.showMessageDialog(null, "File downloaded in: " + file.getAbsolutePath());
    }

    private void receivingFile(FileOutputStream outToFile, DatagramSocket socket) throws IOException {

        boolean flag;
        int sequenceNumber = 0;
        int findLast = 0;
        int totalTransferred = 0;
        while (true) {
            byte[] message = new byte[1024];
            byte[] fileByteArray = new byte[1021];

            // Receive packet and retrieve message
            DatagramPacket receivedPacket = new DatagramPacket(message, message.length);
            socket.setSoTimeout(0);
            socket.receive(receivedPacket);

            message = receivedPacket.getData();
            totalTransferred = receivedPacket.getLength() + totalTransferred;
            totalTransferred = Math.round(totalTransferred);
//            StartTime timer;
            // start the timer at the point transfer begins
            if (sequenceNumber == 0) {
//                timer = new StartTime();
            }

            if (Math.round(totalTransferred / 1000) % 50 == 0) {
                double previousTimeElapsed = 0;
                int previousSize = 0;
//                PrintFactory.printCurrentStatistics(totalTransferred, previousSize,
//                        timer, previousTimeElapsed);
            }
            InetAddress address = receivedPacket.getAddress();
            int port = receivedPacket.getPort();

            sequenceNumber = ((message[0] & 0xff) << 8) + (message[1] & 0xff);
            // Retrieve the last message flag
            // a returned value of true means we have a problem
            flag = (message[2] & 0xff) == 1;
            // if sequence number is the last one +1, then it is correct
            // we get the data from the message and write the message
            // that it has been received correctly
            if (sequenceNumber == (findLast + 1)) {

                // set the last sequence number to be the one we just received
                findLast = sequenceNumber;

                // Retrieve data from message
                System.arraycopy(message, 3, fileByteArray, 0, 1021);

                // Write the message to the file and print received message
                outToFile.write(fileByteArray);
                System.out.println("Received: Sequence number:"
                        + findLast);

                // Send acknowledgement
                sendAck(findLast, socket, address, port);
            } else {
                System.out.println("Expected sequence number: "
                        + (findLast + 1) + " but received "
                        + sequenceNumber + ". DISCARDING");
                // Re send the acknowledgement
                sendAck(findLast, socket, address, port);
            }

            // Check for last message
            if (flag) {
                outToFile.flush();
                outToFile.close();
                break;
            }
        }
    }

    private void sendAck(int findLast, DatagramSocket socket, InetAddress address, int port) throws IOException {
        // send acknowledgement
        byte[] ackPacket = new byte[2];
        ackPacket[0] = (byte) (findLast >> 8);
        ackPacket[1] = (byte) (findLast);
        // the datagram packet to be sent
        DatagramPacket acknowledgement = new DatagramPacket(ackPacket,
                ackPacket.length, address, port);
        socket.send(acknowledgement);
        System.out.println("Sent ack: Sequence Number = " + findLast);
    }
    
    @Override
    public void run() {
        try {
                File fileToSave = new File(dir, fileName);
                makeAckToFileServer(fileToSave, fileId, ip, Integer.valueOf(port));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Invalid data, please check the data!!!");
            return;
        }
    }
}
