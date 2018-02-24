package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshButtonListener implements ActionListener
{
    private Controller controller;
    
    public RefreshButtonListener( Controller controller )
    {
        this.controller = controller;        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        controller.refresh();
    }    
}
