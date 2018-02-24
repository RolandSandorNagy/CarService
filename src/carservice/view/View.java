package carservice.view;

import carservice.controller.Controller;
import carservice.enums.ViewState;
import carservice.structs.WorkSheet;
import java.util.ArrayList;

public interface View 
{

    public void addController( Controller controller );

    public void init();

    public void showView( Object[][] content, Object[] headings, ViewState state );

    public boolean notAllFilterInputNull();

    public void enableFilterButton();
    public void disableFilterButton();

    public void gatherInformationFromForm();

    public String getAddWorkSheetFormCarPartsInputAreaText();
    public String getAddWorkSheetFormColleagueComboBoxText();
    public String getAddWorkSheetFormCustomerNameInputFieldText();
    public String getAddWorkSheetFormCustomerNumberPlateInputFieldText();
    public String getAddWorkSheetFormCustomerTelInputFieldText();
    public String getAddWorkSheetFormProblemInputAreaText();
    public String getAddWorkSheetFormWorkHoursInputFieldText();

    public void highLightCustomerNameInputField();

    public void highLightCustomerTelInputField();

    public void highLightCustomerNumberPlateField();

    public void highLightProblemInputField();

    //public ArrayList<WorkSheet> getTableContentInArrayList();
    public ArrayList<WorkSheet> getWorkSheetsFromContentTable();

    public int getSelectedRowNumber();

    public int getSelectedColNumber();

    public void enableSaveDataButton();
    public void disableSaveDataButton();

    public void enableDeleteButton();
    public void disableDeleteButton();

    public void removeListeners();

    public ArrayList<Integer> getSelectedWorkSheetsIds();

    public void enableFinalizeButton();
    public void disableFinalizeButton();

    public String getFilterCustomerString();

    public String getFilterStateString();

    public String getFilterColleagueString();
    
}
