/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import communication.object.FileResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import master.model.FileServerModel;

/**
 *
 * @author hungt
 */
public class FileServerTableModel extends AbstractTableModel implements Serializable {
    
    private List<FileServerModel> model = new ArrayList<>();
    private final String[] columns = {"STT", "File", "Address", "Port"};
    
    
    @Override
    public int getRowCount() {
        return getModel().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public void add(FileResponse response, String address, Integer port) {
        for(String s : response.getFileNames()) {
            FileServerModel clientModel = new FileServerModel();
            clientModel.setFileName(s);
            clientModel.setIpAddress(address);
            clientModel.setPort(port);
            getModel().add(clientModel);
        }
    }
    
    public void removeFiles(String address, Integer port){
        synchronized(this) {
            Iterator itr = model.iterator(); 
            while (itr.hasNext()){ 
                FileServerModel fsm = (FileServerModel)itr.next(); 
                if(fsm.getIpAddress().equalsIgnoreCase(address) && fsm.getPort().equals(port)) {
                    itr.remove(); 
                } 
            }
        }
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FileServerModel entity = getModel().get(rowIndex);
        switch(columnIndex) {
            case 0: return rowIndex+1;
            case 1: return entity.getFileName();
            case 2: return entity.getIpAddress();
            case 3: return entity.getPort();
            default: return null;
        }
    }

    /**
     * @return the model
     */
    public List<FileServerModel> getModel() {
        return model;
    }
}
