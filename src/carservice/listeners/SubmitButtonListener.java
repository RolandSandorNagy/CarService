package carservice.listeners;

import carservice.controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitButtonListener implements ActionListener
{
        private final Controller controller;
        
        public SubmitButtonListener( Controller controller )
        {
            this.controller = controller;
        }    
    
        @Override
        public void actionPerformed( ActionEvent evt )
        {
            if( controller.validateInputData() )
                controller.submitWorkSheet();
        }

}
