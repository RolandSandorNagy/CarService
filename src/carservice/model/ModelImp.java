package carservice.model;

import carservice.cache.Cache;
import carservice.cache.CacheImp;
import carservice.controller.Controller;
import carservice.enums.ViewState;
import carservice.enums.WorkSheetState;
import carservice.structs.Customer;
import carservice.structs.WorkSheet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelImp implements Model
{
    private Controller controller;    
    private Cache cache;
    
    private ViewState viewState;
        
    public ModelImp() 
    {
        try 
        {
            cache = new CacheImp();
        } 
        catch (ClassNotFoundException ex) 
        {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }
    }           
    
    @Override
    public void addController( Controller controller ) 
    {
        this.controller = controller;
    }

    @Override
    public void init() 
    {
        //viewState = ViewState.HOME;
        viewState = ViewState.CONNECTIONSETTINGS;
        cache.init();
    }

    @Override
    public ViewState getViewState() 
    {
        return viewState;
    }

    @Override
    public void setView( ViewState viewState ) 
    {
        this.viewState = viewState;
    }

    @Override
    public Object[][] getDataTableContent() 
    {
        switch( viewState )            
        {
            case COLLEAGUES: return get_ColleaguesTable_Content();
            case WORKSHEETS: return get_WorkSheetsTable_Content();
            case CUSTOMERS: return get_CustomersTable_Content();
            case STORE: return get_CarPartsTable_Content();
            default: return null;
        }
    }

    @Override
    public Object[] getDataTableHeadings() 
    {
        switch( viewState )            
        {
            case COLLEAGUES: return get_ColleaguesTable_Headings();
            case WORKSHEETS: return get_WorkSheetsTable_Headings();
            case CUSTOMERS: return get_CustomersTable_Headings();
            case STORE: return get_CarPartsTable_Headings();
            default: return null;
        }
    }
    
    private Object[][] get_ColleaguesTable_Content() 
    {
        return cache.get_ColleaguesTable_Content();
    }

    private Object[] get_ColleaguesTable_Headings() 
    {
        return cache.get_ColleaguesTable_Headings();
    }

    private Object[][] get_WorkSheetsTable_Content() 
    {
        return cache.get_WorkSheetsTable_Content();
    }

    private Object[] get_WorkSheetsTable_Headings() 
    {
        return cache.get_WorkSheetsTable_Headings();
    }

    private Object[][] get_CustomersTable_Content() 
    {
        return cache.get_CustomersTable_Content();
    }

    private Object[] get_CustomersTable_Headings() 
    {
        return cache.get_CustomersTable_Headings();
    }

    private Object[][] get_CarPartsTable_Content() 
    {
        return cache.get_CarPartsTable_Content();
    }

    private Object[] get_CarPartsTable_Headings() 
    {
        return cache.get_CarPartsTable_Headings();
    }

    @Override
    public String[] getColleaguesNamesStringArray() 
    {
        return cache.getColleaguesNamesStringArray();
    }

    @Override
    public void refresh() 
    {
        cache.refresh();
    }

    @Override
    public int getMaxIdOfWorkSheets() 
    {
        return cache.getMaxIdOfWorkSheets();
    }   

    @Override
    public int handleCustomerId( Customer customer, String workSheetId ) 
    {   
        return cache.handleCustomerId( customer, workSheetId );
    }

    @Override
    public int calculateWorkSheetCost( int time, int colleagueId, String parts ) 
    {
        return cache.calculateWorkSheetCost( time, colleagueId, parts );
    }

    @Override
    public int handleColleagueId( String colleagueName ) 
    {
        return cache.findColleagueIdByName( colleagueName );
    }

    @Override
    public void insetNewWorkSheetIntoDataBase( WorkSheet workSheet ) 
    {
        cache.insetNewWorkSheetIntoDataBase( workSheet );
    }

    @Override
    public boolean partsAvailable( String parts ) 
    {
        return cache.partsAvailable( parts );
    }

    @Override
    public void decreasePartsFromDataBase( String parts ) 
    {
        cache.decreasePartsFromDataBase( parts );
    }

    @Override
    public void updateLocalWorkSheetArray( String value, int workSheetId, String columnName ) 
    {
        cache.updateLocalWorkSheetArray( value, workSheetId, columnName );
    }

    @Override
    public void saveData() 
    {
        cache.saveData();
    }

    @Override
    public void setModelToCache() 
    {
        cache.setModel( this );
    }

    @Override
    public boolean checkIfColleagueIdExists( int colleagueId ) 
    {
        return cache.checkIfColleagueIdExists( colleagueId );
    }

    @Override
    public boolean checkIfCustomerIdExists( int customerId ) 
    {
        return cache.checkIfCustomerIdExists( customerId );
    }

    @Override
    public void deleteWorkSheetsById( ArrayList<Integer> selectedWorkSheetsIds ) 
    {
        cache.deleteWorkSheetsById( selectedWorkSheetsIds );
    }

    @Override
    public String[] getCustomerNamesStringArray() 
    {
        return cache.getCustomerNamesStringArray();
    }

    @Override
    public String[] getColleagueNamesStringArray() 
    {
        return cache.getColleagueNamesStringArray();
    }

    @Override
    public void setFilterProperties( String filterCustomerString, String filterStateString, String filterColleagueString ) 
    {
        cache.setFilterProperties( filterCustomerString, filterStateString, filterColleagueString );
    }

    @Override
    public void finalizeWorkSheet( int workSheetId ) 
    {
        cache.finalizeWorkSheet( workSheetId );
    }

    @Override
    public boolean testConnection( String hostString, String dataBaseString, String userString, String passwordString ) 
    {
        return cache.testConnection( hostString, dataBaseString, userString, passwordString );
    }
}   