/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.object;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author hungt
 */
public class FileResponse implements Serializable{
    private List<String> fileNames;
    private Integer port;
    
    /**
     * @return the fileNames
     */
    public List<String> getFileNames() {
        return fileNames;
    }

    /**
     * @param fileNames the fileNames to set
     */
    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }

    /**
     * @return the port
     */
    public Integer getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(Integer port) {
        this.port = port;
    }

  
    
}
