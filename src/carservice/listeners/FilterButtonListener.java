package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilterButtonListener implements ActionListener
{
    private final Controller controller;

    public FilterButtonListener( Controller controller )
    {
        this.controller = controller;
    }
    
    @Override
    public void actionPerformed( ActionEvent evt )
    {
        controller.filterWorkSheets();
    }    
}
