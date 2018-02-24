package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveDataButtonListener implements ActionListener 
{
    private Controller controller;

    public SaveDataButtonListener(Controller controller )
    {
        this.controller = controller;        
    }
    
    @Override
    public void actionPerformed( ActionEvent evt )
    {
        controller.saveData();
    }    
}
