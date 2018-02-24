package carservice;

import carservice.model.Model;
import carservice.model.ModelImp;
import carservice.view.View;
import carservice.view.ViewImp;
import carservice.controller.Controller;
import carservice.controller.ControllerImp;

public class CarService 
{
    private Model       model;
    private View        view;
    private Controller  controller;
    
    public static void main( String[] args ) 
    {
        try { new CarService(); }
        catch( IllegalStateException ex ) { }
    }

    public CarService()
    {
        model = new ModelImp();
        view = new ViewImp();
        controller = new ControllerImp( model, view );
        model.setModelToCache();
        model.addController( controller );
        view.addController( controller );
        
        controller.init();
    }
}
