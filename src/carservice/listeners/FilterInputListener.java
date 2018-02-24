package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterInputListener implements ActionListener
{    
    private final Controller controller;
    
    public FilterInputListener( Controller controller )
    {
        this.controller = controller;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        controller.enableDisableFilterButton();
    }    
}
