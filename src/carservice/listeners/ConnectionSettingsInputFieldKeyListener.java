package carservice.listeners;

import carservice.view.ViewImp;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConnectionSettingsInputFieldKeyListener implements KeyListener
{
    private ViewImp view;
    
    public ConnectionSettingsInputFieldKeyListener( ViewImp view )
    {
        this.view = view;
    }
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
        String dataBaseString = view.getConnectionSettingsDataBaseInputFieldText();
        String userString = view.getConnectionSettingsUserInputFieldText();        
        String passwordString = view.getConnectionSettingsPasswordInputFieldText();

        if( dataBaseString.equals( "" ) || userString.equals( "" ) || passwordString.equals( "" ) )
        {
            view.setConnectionSettingsTestButtonEnabled( false );                                    
            return;                
        }                
        view.setConnectionSettingsTestButtonEnabled( true );                                    
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        String dataBaseString = view.getConnectionSettingsDataBaseInputFieldText();
        String userString = view.getConnectionSettingsUserInputFieldText();        
        String passwordString = view.getConnectionSettingsPasswordInputFieldText();

        if( dataBaseString.equals( "" ) || userString.equals( "" ) || passwordString.equals( "" ) )
        {
            view.setConnectionSettingsTestButtonEnabled( false );                                    
            return;                
        }                
        view.setConnectionSettingsTestButtonEnabled( true );                                    
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {

    }
    
}
