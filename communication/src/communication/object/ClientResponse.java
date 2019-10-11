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
public class ClientResponse implements Serializable {
    private List<FileInfomation> result;

    /**
     * @return the result
     */
    public List<FileInfomation> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<FileInfomation> result) {
        this.result = result;
    }
    
}
