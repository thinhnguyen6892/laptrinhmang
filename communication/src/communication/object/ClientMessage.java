/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package communication.object;

import communication.object.contanst.Task;
import java.io.Serializable;

/**
 *
 * @author hungt
 */
public class ClientMessage implements Serializable{
    private Task task;
    private Integer clientPort;
    private String parameters;


    /**
     * @return the parameters
     */
    public String getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the task
     */
    public Task getTask() {
        return task;
    }

    /**
     * @param task the task to set
     */
    public void setTask(Task task) {
        this.task = task;
    }

    /**
     * @return the clientPort
     */
    public Integer getClientPort() {
        return clientPort;
    }

    /**
     * @param clientPort the clientPort to set
     */
    public void setClientPort(Integer clientPort) {
        this.clientPort = clientPort;
    }
    
}
