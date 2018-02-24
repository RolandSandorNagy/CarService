package carservice.cache;
 
import carservice.model.ModelImp;
import carservice.structs.Customer;
import carservice.structs.WorkSheet;
import java.util.ArrayList;

public interface Cache 
{    
    
    public void init();

    public Object[][] get_ColleaguesTable_Content();
    public Object[]   get_ColleaguesTable_Headings();
    
    public Object[][] get_WorkSheetsTable_Content();
    public Object[]   get_WorkSheetsTable_Headings();

    public Object[][] get_CustomersTable_Content();
    public Object[]   get_CustomersTable_Headings();

    public Object[][] get_CarPartsTable_Content();
    public Object[]   get_CarPartsTable_Headings();

    public String[] getColleaguesNamesStringArray();

    public int getMaxIdOfWorkSheets();

    public int handleCustomerId( Customer customer, String workSheetId );
    
    public void refresh();

    public int findColleagueIdByName( String colleagueName );

    public void insetNewWorkSheetIntoDataBase( WorkSheet workSheet );

    public int calculateWorkSheetCost( int time, int colleagueId, String parts );

    public boolean partsAvailable( String parts );

    public void decreasePartsFromDataBase(String parts);

    public void updateLocalWorkSheetArray(String value, int workSheetId, String columnName);

    public void saveData();

    public void setModel( ModelImp aThis );

    public boolean checkIfColleagueIdExists( int colleagueId );

    public boolean checkIfCustomerIdExists( int customerId );

    public void deleteWorkSheetsById(ArrayList<Integer> selectedWorkSheetsIds);

    public String[] getCustomerNamesStringArray();

    public String[] getColleagueNamesStringArray();

    public void setFilterProperties(String filterCustomerString, String filterStateString, String filterColleagueString);

    public void finalizeWorkSheet(int workSheetId);

    public boolean testConnection(String hostString, String dataBaseString, String userString, String passwordString);

}
