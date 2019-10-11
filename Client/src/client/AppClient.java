/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;


import communication.object.ClientMessage;
import communication.object.ClientResponse;
import communication.object.FileInfomation;
import communication.object.contanst.Task;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.Socket;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author nguye
 */
public class AppClient extends javax.swing.JFrame {

    /**
     * Creates new form AppClient
     */
    
    
    public AppClient() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIp = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();
        btnConnect = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btcSearch = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableDataServer = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("APP CLIENT");

        jLabel2.setText("IP:");

        txtIp.setText("127.0.0.1");

        jLabel3.setText("PORT:");

        txtPort.setText("14");
        txtPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPortActionPerformed(evt);
            }
        });

        btnConnect.setText("Connect");
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        jLabel4.setText("Search:");

        btcSearch.setText("Search");
        btcSearch.setEnabled(null != socket);
        btcSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcSearchActionPerformed(evt);
            }
        });

        tableDataServer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name File", "Ip Address", "Port", "Download "
            }
        ));
        tableDataServer.setEnabled(null != socket);
        tableDataServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataServerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableDataServer);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnConnect))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btcSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConnect))
                .addGap(61, 61, 61)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btcSearch)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(659, 572));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btcSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcSearchActionPerformed

        String search = txtSearch.getText();
        if (search.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Input empty!!");
            return;
        }
        try{
            // create an object output stream from the output stream so we can send an object through it
            ClientMessage clientMessage = new ClientMessage();
            clientMessage.setTask(Task.SEARCH);
            clientMessage.setParameters(search);
            // get the output stream from the socket.
            objectOutputStream.writeObject(clientMessage);
            Object object = objectInputStream.readObject();
            if(object instanceof ClientResponse){
                ClientResponse fileInfomation = (ClientResponse) object;
                DefaultTableModel model = (DefaultTableModel) tableDataServer.getModel();
                model.setRowCount(0);
                for(FileInfomation s : fileInfomation.getResult()) {
                    Object[] row = new Object[4];
                    row[0] = s.getFileName();
                    row[1] = s.getIpAddress();
                    row[2] = s.getPort();
                    row[3] = "Download";
                    model.addRow(row);
                }
                tableDataServer.setModel(model);
            }
            
        } catch(IOException iOException) {
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AppClient.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btcSearchActionPerformed

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        String IpString = txtIp.getText();
        String Port = txtPort.getText();
        if (IpString.isEmpty() || Port.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid data, please check the data!!!");
            return;
        }
        try {
            socket = new Socket(IpString, Integer.valueOf(Port)); // Connect to server
            final OutputStream os = socket.getOutputStream();
            this.objectOutputStream = new ObjectOutputStream(os);

            final InputStream is = socket.getInputStream();
            this.objectInputStream = new ObjectInputStream(is);
            System.out.println("Connected: " + socket);
            JOptionPane.showMessageDialog(null, "Connected!!!");
            
            ClientMessage clientMessage = new ClientMessage();
            clientMessage.setTask(Task.CLIENT_PING);
            objectOutputStream.writeObject(clientMessage);
            txtSearch.setEnabled(true);
            btcSearch.setEnabled(true);
            tableDataServer.setEnabled(true);
                     
        } catch (IOException ie) {
            System.out.println("Can't connect to server");
        }
//        
//        btcSearch.setEnabled(true);
//        JOptionPane.showMessageDialog(null, "Connect success!!");
//        DefaultTableModel model = (DefaultTableModel) tableDataServer.getModel();
//        model.setRowCount(0);
//        Object[] row = new Object[4];
//        for (int i = 0; i < 10; i++) {
//            row[0] = "server " + i;
//            row[1] = "10.20.0." + i;
//            row[2] = "28";
//            row[3] = "Download";
//            model.addRow(row);
//        }
//        tableDataServer.setModel(model);
    }//GEN-LAST:event_btnConnectActionPerformed

    private void tableDataServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataServerMouseClicked
        JTable target = (JTable) evt.getSource();
        String nameFile = tableDataServer.getValueAt(target.getSelectedRow(), 0).toString();
        String ip = tableDataServer.getValueAt(target.getSelectedRow(), 1).toString();
        String port = tableDataServer.getValueAt(target.getSelectedRow(), 2).toString();
        JOptionPane.showMessageDialog(null, nameFile + ": " + ip + ":" + port + " Download.....");
    }//GEN-LAST:event_tableDataServerMouseClicked

    private void txtPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPortActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
         if (socket != null) {
                try {
                    socket.close();
                } catch (IOException ex) {
                    Logger.getLogger(AppClient.class.getName()).log(Level.SEVERE, null, ex);
                }
         }
    }//GEN-LAST:event_formWindowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HandelFile();
                AppClient FormAppClient = new AppClient();
                FormAppClient.setTitle("App Client");
                FormAppClient.setVisible(true);
            }

            private void HandelFile() {
                File dir = new File("TBD");
                if (!dir.exists()) {
                    dir.mkdir();
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btcSearch;
    private javax.swing.JButton btnConnect;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableDataServer;
    private javax.swing.JTextField txtIp;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
    private Socket socket = null;
    private ObjectOutputStream objectOutputStream;
    private ObjectInputStream objectInputStream;
    
}
