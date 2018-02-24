package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTable;

public class TableFocusListener implements FocusListener
{
    private Controller controller;
    private JTable table;
    
    public TableFocusListener( Controller controller, JTable table )
    {
        this.controller = controller;
        this.table = table;
    }
    
    @Override
    public void focusGained( FocusEvent e ) 
    {   
        if( tableExist() && cellSelected() )
        {
            int rowNo = table.getSelectedRow();
            if( !table.getValueAt( rowNo, 8 ).toString().equals( "KIFIZETETT" ) )
                controller.enableDeleteButton();           
            else controller.disableDeleteButton();
            controller.handleFinalizeButton( table );            
        }
    }

    @Override
    public void focusLost( FocusEvent e ) 
    {
        /*
        if( tableExist() && cellSelected() )
        {
            controller.disableDeleteButton();            
            controller.disableFinalizeButton();            
        }
        */
    }

    private boolean cellSelected() 
    {
        return table.getSelectedRow() >= 0;
    }

    private boolean tableExist() 
    {
        return table != null;
    }
}
