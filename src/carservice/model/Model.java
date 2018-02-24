package carservice.model;

import carservice.controller.Controller;
import carservice.enums.ViewState;
import carservice.enums.WorkSheetState;
import carservice.structs.Customer;
import carservice.structs.WorkSheet;
import java.util.ArrayList;

public interface Model 
{

    public void addController( Controller controller );

    public void init();

    public ViewState getViewState();
    public void setView( ViewState viewState );

    public Object[][] getDataTableContent();
    public Object[] getDataTableHeadings();

    public String[] getColleaguesNamesStringArray();

    public void refresh();

    public int getMaxIdOfWorkSheets();

    public int handleCustomerId( Customer customer, String workSheetId );

    public int calculateWorkSheetCost( int time, int colleagueId, String parts );

    public int handleColleagueId( String colleagueName );

    public void insetNewWorkSheetIntoDataBase( WorkSheet workSheet );

    public boolean partsAvailable( String parts );

    public void decreasePartsFromDataBase( String parts );

    public void updateLocalWorkSheetArray( String value, int workSheetId, String columnName );

    public void saveData();

    public void setModelToCache();
    
    public boolean checkIfColleagueIdExists( int colleagueId );

    public boolean checkIfCustomerIdExists( int customerId );

    public void deleteWorkSheetsById( ArrayList<Integer> selectedWorkSheetsIds );

    public String[] getCustomerNamesStringArray();

    public String[] getColleagueNamesStringArray();

    public void setFilterProperties( String filterCustomerString, String filterStateString, String filterColleagueString );

    public void finalizeWorkSheet( int get );

    public boolean testConnection(String hostString, String dataBaseString, String userString, String passwordString);

}
