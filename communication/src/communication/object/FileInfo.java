/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.object;

import java.io.Serializable;

/**
 *
 * @author hungt
 */
public class FileInfo implements Serializable {
    private String destinationDirectory;
    private String sourceDirectory;
    private String filename;
    private long fileSize;
    private int piecesOfFile;
    private int lastByteLength;
    private String status;

    /**
     * @return the destinationDirectory
     */
    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    /**
     * @param destinationDirectory the destinationDirectory to set
     */
    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    /**
     * @return the sourceDirectory
     */
    public String getSourceDirectory() {
        return sourceDirectory;
    }

    /**
     * @param sourceDirectory the sourceDirectory to set
     */
    public void setSourceDirectory(String sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    /**
     * @return the filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename the filename to set
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * @return the fileSize
     */
    public long getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the piecesOfFile
     */
    public int getPiecesOfFile() {
        return piecesOfFile;
    }

    /**
     * @param piecesOfFile the piecesOfFile to set
     */
    public void setPiecesOfFile(int piecesOfFile) {
        this.piecesOfFile = piecesOfFile;
    }

    /**
     * @return the lastByteLength
     */
    public int getLastByteLength() {
        return lastByteLength;
    }

    /**
     * @param lastByteLength the lastByteLength to set
     */
    public void setLastByteLength(int lastByteLength) {
        this.lastByteLength = lastByteLength;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
