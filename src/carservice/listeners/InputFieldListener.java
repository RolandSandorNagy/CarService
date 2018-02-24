package carservice.listeners;

import carservice.controller.Controller;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;

public class InputFieldListener extends KeyAdapter
{
    private final Controller controller;
    private final JComponent component;    
    
    public InputFieldListener( Controller controller, JComponent component )
    {
        this.controller = controller;
        this.component  = component;  
    }
 
    public InputFieldListener( Controller controller )
    {
        this.controller = controller;
        component = null;  
    }
    
    @Override
    public void keyTyped( KeyEvent e ) 
    {        
        System.out.println( "Key typed." );
        component.setBackground( Color.WHITE );
        if( component.getName().equals( "hours" ) ) 
            handleWorkHoursInputEvent( e );
    }

    private void handleWorkHoursInputEvent( KeyEvent e ) 
    {
        if( isNumeric( Character.toString( e.getKeyChar() ) ) ) 
            System.out.println( "Numeric." );
        else
            e.consume();            
    }
    
    public static boolean isNumeric( String ch )
    {
        try
        {
            Integer.parseInt( ch );
            return true;            
        }
        catch( Exception ex )
        {
            return false;            
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) 
    {
        System.out.println( "Key pressed." );
    }

    @Override
    public void keyReleased(KeyEvent e) 
    {
        System.out.println( "Key released." );
    }            
}
