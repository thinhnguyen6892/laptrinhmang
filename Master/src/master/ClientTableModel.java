/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package master;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import master.model.ClientModel;

/**
 *
 * @author hungt
 */
public class ClientTableModel extends AbstractTableModel implements Serializable {

    
    List<ClientModel> model = new ArrayList<>();
    private final String[] columns = {"STT","Address", "Port", "Status"};
    
    @Override
    public int getRowCount() {
        return model.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public void add(String address, int port) {
        ClientModel clientModel = new ClientModel();
        clientModel.setIpAddress(address);
        clientModel.setPort(port);
        model.add(clientModel);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClientModel entity = model.get(rowIndex);
        switch(columnIndex) {
            case 0: return rowIndex+1;
            case 1: return entity.getIpAddress();
            case 2: return entity.getPort();
            case 3: return entity.getStatus();
            default: return null;
        }
    }
}
