package carservice.controller;

import carservice.enums.ViewState;
import carservice.enums.WorkSheetState;
import javax.swing.JTable;

public interface Controller 
{
    
    public void init();
        
    public void changeView( ViewState viewState );

    public void refresh();

    public void filterWorkSheets();

    public void finalizeWorkSheet();

    public void deleteWorkSheet();

    public void saveData();

    public void resetAddWorkSheetForm();
    
    public void exitProgram();

    public void enableDisableFilterButton();

    public String[] getColleaguesNamesStringArray();

    public boolean validateInputData();

    public void submitWorkSheet();

    public boolean tableHasBeenModified();

    public int getSelectedRowNumber();

    public int getSelectedColNumber();

    public void updateLocalWorkSheetArray( String value, int rowno, String columnName );

    public void activateSaveDataBtn();

    public void enableDeleteButton();
    
    public void disableDeleteButton();

    public void removeListeners();

    public boolean checkIfColleagueIdExists( int colleagueId );

    public boolean checkIfCustomerIdExists( int customerId );

    public String[] getCustomerNamesStringArray();

    public String[] getColleagueNamesStringArray();

    public void enableFinalizeButton();

    public void disableFinalizeButton();

    public void handleFinalizeButton( JTable contentTable );

    public boolean testConnection(String hostString, String dataBaseString, String userString, String passwordString);

}
