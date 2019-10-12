package examples;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.util.Random;

public class FileTransmissionWorkerThread extends Thread {

    private File file;
    private Integer port;
    private InetAddress address;
    
    public FileTransmissionWorkerThread(InetAddress address, Integer port, File file) {
        this.file = file;
        this.port = port;
        this.address = address;
    }
    
    @Override
    public void run() {
        try {
            // Create a byte array to store file
            //InputStream inFromFile = new FileInputStream(file);
            DatagramSocket socket = new DatagramSocket();
            byte[] fileByteArray = Files.readAllBytes(file.toPath()); 
            //      startTimer();
            beginTransfer(socket, fileByteArray);
        } catch(IOException exception) {
            
        }
        
    }
    
    private void beginTransfer(DatagramSocket socket, byte[] fileByteArray) throws IOException {
        int retransmitted = 0;
        int sequenceNumber = 0;
        boolean flag;
        int totalTransferred = 0;
        int ackSequence = 0;

        for (int i = 0; i < fileByteArray.length; i = i + 1021) {
            sequenceNumber += 1;
            // Create message
            byte[] message = new byte[1024];
            message[0] = (byte) (sequenceNumber >> 8);
            message[1] = (byte) (sequenceNumber);

            if ((i + 1021) >= fileByteArray.length) {
                flag = true;
                message[2] = (byte) (1);
            } else {
                flag = false;
                message[2] = (byte) (0);
            }

            if (!flag) {
                System.arraycopy(fileByteArray, i, message, 3, 1021);
            } else { // If it is the last message
                System.arraycopy(fileByteArray, i, message, 3, fileByteArray.length - i);
            }

//            int randomInt = shouldThisPacketBeSent();

            DatagramPacket sendPacket = new DatagramPacket(message, message.length, address, port);

//            if (randomInt <= 100) {
                socket.send(sendPacket);
//            }

            totalTransferred = gatherTotalDataSentSoFarStatistic(totalTransferred, sendPacket);

/*
            if (Math.round(totalTransferred / 1000) % 50 == 0) {
                PrintFactory.printCurrentStatistics(totalTransferred, previousSize, timer, previousTimeElapsed);
            }
*/

            System.out.println("Sent: Sequence number = " + sequenceNumber);

            // For verifying the the packet
            boolean ackRec;

            // The acknowledgment is not correct
            while (true) {
                // Create another packet by setting a byte array and creating
                // data gram packet
                byte[] ack = new byte[2];
                DatagramPacket ackpack = new DatagramPacket(ack, ack.length);

                try {
                    // set the socket timeout for the packet acknowledgment
                    socket.setSoTimeout(50);
                    socket.receive(ackpack);
                    ackSequence = ((ack[0] & 0xff) << 8)
                            + (ack[1] & 0xff);
                    ackRec = true;

                }
                // we did not receive an ack
                catch (SocketTimeoutException e) {
                    System.out.println("Socket timed out waiting for the ");
                    ackRec = false;
                }

                // everything is ok so we can move on to next packet
                // Break if there is an acknowledgment next packet can be sent
                if ((ackSequence == sequenceNumber)
                        && (ackRec)) {
                    System.out.println("Ack received: Sequence Number = "
                            + ackSequence);
                    break;
                }

                // Re send the packet
                else {
                    socket.send(sendPacket);
                    System.out.println("Resending: Sequence Number = "
                            + sequenceNumber);
                    // Increment retransmission counter
                    retransmitted += 1;
                }
            }
        }
    }

    private static int gatherTotalDataSentSoFarStatistic(int totalTransferred, DatagramPacket sendPacket) {
        totalTransferred = sendPacket.getLength() + totalTransferred;
        totalTransferred = Math.round(totalTransferred);
        return totalTransferred;
    }

    private static int shouldThisPacketBeSent() {
        Random randomGenerator = new Random();
        return randomGenerator.nextInt(100);
    }
}
