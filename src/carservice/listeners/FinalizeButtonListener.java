package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalizeButtonListener implements ActionListener
{
    private final Controller controller;
    
    public FinalizeButtonListener( Controller controller )
    {
        this.controller = controller;
    }
    
    @Override
    public void actionPerformed( ActionEvent evt )
    {
        System.out.println( "ITT VAGYOK!!!!" );
        controller.finalizeWorkSheet();
    }    
}
