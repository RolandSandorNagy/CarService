package carservice.listeners;

import carservice.controller.Controller;
import carservice.enums.ViewState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuButtonListener implements ActionListener
{
    private final Controller controller;
    private final ViewState viewState;

    public MenuButtonListener( Controller controller, ViewState viewState )
    {
        this.controller = controller;
        this.viewState = viewState;
    }
    
    @Override
    public void actionPerformed( ActionEvent evt )
    {
        controller.changeView( viewState );
    }
    
}
