package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteButtonListener implements ActionListener
{
    private Controller controller;
    
    public DeleteButtonListener( Controller controller )
    {
        this.controller = controller;
    }
    
    @Override
    public void actionPerformed( ActionEvent evt )
    {
        TablePropertyChangeListener.modification = true;
        controller.deleteWorkSheet();
    }
}
