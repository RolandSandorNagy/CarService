package carservice.listeners;

import carservice.controller.Controller;
import carservice.enums.ViewState;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.CellEditor;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class TablePropertyChangeListener implements PropertyChangeListener 
{
    public static boolean modification = false;
    public static ArrayList<Integer> changedWorkSheetids = new ArrayList<>();
    
    private final Controller controller;
    private String value;
    
    public TablePropertyChangeListener( Controller controller )
    {
        this.controller = controller;
        value = null;
        //value = "BADVALUE";
    }
    
    @Override
    public void propertyChange( PropertyChangeEvent evt ) 
    {               
        int rowno = controller.getSelectedRowNumber();
        int colno = controller.getSelectedColNumber();

        if( noRowSelected( rowno, colno ) ) return;

        String newValue = getValue( evt, rowno, colno );        
        
        if( userClicked_InTo_Cell( evt ) ) 
        {
            System.out.println( "thats it. The New Value is " + newValue );
            value = newValue;
        }                        
        else if( cellContentChanged( newValue ) )
        {    
            value = newValue;                            
            int workSheetId = (int)( (JTable)evt.getSource() ).getValueAt( rowno, 0 );
            String columnName = getColumnNameByNumber( colno );            
            if( !validateNewValue( value, workSheetId, columnName ) )
            {
                controller.changeView( ViewState.WRONGMODIFICATIONVALUE );
                return;                
            }
            controller.updateLocalWorkSheetArray( value, workSheetId, columnName );
            controller.activateSaveDataBtn();
            changedWorkSheetids.add( workSheetId );
            TablePropertyChangeListener.modification = true;
            controller.changeView( ViewState.WORKSHEETS );
            System.out.println( "Table has been modified." );                                    
        }
        else System.out.println( "Table is the same." );
    }

    private String getColumnNameByNumber( int colno ) 
    {
        switch( colno )
        {
            case 2: return "customer"; 
            case 3: return "problem"; 
            case 4: return "parts"; 
            case 5: return "colleague"; 
            case 6: return "hours"; 
            default: break;
        }
        return "";
    }

    private boolean userClicked_InTo_Cell( PropertyChangeEvent evt ) 
    {
        return ( evt.getOldValue() == null );
    }

    private boolean noRowSelected(int rowno, int colno) 
    {
        return ( ( rowno < 0 ) || ( colno < 0 ) );
    }

    private String getValue(PropertyChangeEvent evt, int rowno, int colno ) 
    {
        return ( (JTable)evt.getSource() ).getValueAt( rowno, colno ).toString();
    }

    private boolean cellContentChanged( String newValue ) 
    {
        return value != null && !value.equals( newValue );
        //return value != "" && !value.equals( newValue );
        //return !value.equals( newValue ) && !value.equals( "BADVALUE" );
    }

    private boolean validateNewValue( String value, int workSheetId, String columnName ) 
    {
        // Itt még gond van!!! ToDo...
        if( columnName.equals( "customer" ) )
        {
            if( !InputFieldListener.isNumeric( value ) || newCustomerIdDoesntExists( Integer.parseInt( value ) ) )
                return false;             
        }                
        else if( columnName.equals( "colleague" ) )
        {
            if( !InputFieldListener.isNumeric( value ) || newColleagueIdDoesntExists( Integer.parseInt( value ) ) )
                return false; 
        }                   
        else if( columnName.equals( "hours" ) )
        {
            if( !InputFieldListener.isNumeric( value ) || Integer.parseInt( value ) < 0 )
                return false;
        }
        return true;
    }

    private boolean newColleagueIdDoesntExists( int colleagueId ) 
    {
        return !controller.checkIfColleagueIdExists( colleagueId );
    }

    private boolean newCustomerIdDoesntExists( int customerId ) 
    {
        return !controller.checkIfCustomerIdExists( customerId );
    }
}
