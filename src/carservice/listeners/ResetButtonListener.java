package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetButtonListener implements ActionListener
{
        private final Controller controller;
        
        public ResetButtonListener( Controller controller )
        {
            this.controller = controller;
        }

        @Override
        public void actionPerformed( ActionEvent evt )
        {
            controller.resetAddWorkSheetForm();
        }
    
}
