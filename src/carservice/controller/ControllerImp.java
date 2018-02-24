package carservice.controller;

import carservice.enums.ViewState;
import carservice.enums.WorkSheetState;
import carservice.listeners.TablePropertyChangeListener;
import carservice.model.Model;
import carservice.structs.Customer;
import carservice.structs.WorkSheet;
import carservice.view.View;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;

public class ControllerImp implements Controller
{
    private final Model model;
    private final View view;
    
    public ControllerImp( Model model, View view ) 
    {
        this.model = model;
        this.view = view;
    }    

    @Override
    public void init() 
    {
        model.init();
        view.init();
        //view.showView( null, null, model.getViewState() );        
        changeView( model.getViewState() );        
    }

    @Override
    public void changeView( ViewState viewState ) 
    {
        System.out.println( "SideBar button was pressed." );
        model.setView( viewState );
        view.showView( model.getDataTableContent(), model.getDataTableHeadings(), model.getViewState() );        
    }

    @Override
    public void refresh() 
    {
        System.out.println( "Sidebar refresh button was pressed." );
        TablePropertyChangeListener.modification = false;
        model.refresh();
        changeView( model.getViewState() );
    }

    @Override
    public void filterWorkSheets() 
    {
        System.out.println( "filter button was action performed." );
        String filterCustomerString = view.getFilterCustomerString();
        String filterStateString = view.getFilterStateString();
        String filterColleagueString = view.getFilterColleagueString();
        model.setFilterProperties( filterCustomerString, filterStateString, filterColleagueString );
        changeView( ViewState.WORKSHEETS );
        model.setFilterProperties( "", "", "" );
    }

    @Override
    public void finalizeWorkSheet() 
    {
        // ToDo...
        System.out.println( "Finalize button was pressed." );
        ArrayList<Integer> workSheetIds = view.getSelectedWorkSheetsIds();
        if( workSheetIds.size() == 1 )
        {
            System.out.println( "Eddig jó!" );
            model.finalizeWorkSheet( (int)workSheetIds.get( 0 ) );            
        }
        else System.out.println( "TÖBB: " + workSheetIds.size() );
        changeView( ViewState.WORKSHEETS );
    }

    @Override
    public void deleteWorkSheet() 
    {
        System.out.println( "Delete button was pressed." );
        ArrayList<Integer> selectedWorkSheetsIds = view.getSelectedWorkSheetsIds();
        model.deleteWorkSheetsById( selectedWorkSheetsIds );        
        changeView( ViewState.WORKSHEETS );
    }

    @Override
    public void saveData() 
    {
        System.out.println( "SaveData button was pressed." );
        model.saveData();
        TablePropertyChangeListener.modification = false;
        TablePropertyChangeListener.changedWorkSheetids.clear();
        changeView( ViewState.MODIFICATIONSAVED );
    }

    @Override
    public void resetAddWorkSheetForm() 
    {
        System.out.println( "Reset button was pressed." );
        changeView( ViewState.ADDWORKSHEET );
    }

    @Override
    public void exitProgram() 
    {
        // ToDo...
        System.exit( 0 );                
    }

    @Override
    public void enableDisableFilterButton() 
    {
        if( view.notAllFilterInputNull() )  view.enableFilterButton();
        else                                view.disableFilterButton();
            
    }

    @Override
    public String[] getColleaguesNamesStringArray() 
    {
        return model.getColleaguesNamesStringArray();
    }

    @Override
    public boolean validateInputData() 
    {
        view.gatherInformationFromForm();
        
        if(    ( view.getAddWorkSheetFormCustomerNameInputFieldText().equals( "" ) )
            || ( view.getAddWorkSheetFormCustomerTelInputFieldText().equals( "" ) ) 
            || ( view.getAddWorkSheetFormCustomerNumberPlateInputFieldText().equals( "" ) ) 
            || ( view.getAddWorkSheetFormProblemInputAreaText().equals( "" ) ) )
            {
                System.out.println( "invalid form: few data." );
                highLightInvalidDataFields();
                return false;
            }        
        return true;
    }

    private void highLightInvalidDataFields() 
    {
        if( ( view.getAddWorkSheetFormCustomerNameInputFieldText().equals( "" ) ) ) 
            view.highLightCustomerNameInputField();
        if( ( view.getAddWorkSheetFormCustomerTelInputFieldText().equals( "" ) ) )
            view.highLightCustomerTelInputField();
        if( ( view.getAddWorkSheetFormCustomerNumberPlateInputFieldText().equals( "" ) ) )
            view.highLightCustomerNumberPlateField();
        if( ( view.getAddWorkSheetFormProblemInputAreaText().equals( "" ) ) )
            view.highLightProblemInputField();
    }

    @Override
    public void submitWorkSheet() 
    {
        System.out.println( "Submit button was pressed." );
        WorkSheet workSheet = ripWorkSheetOffForm();
        model.insetNewWorkSheetIntoDataBase( workSheet );
        model.refresh();
        changeView( ViewState.WORKSHEETADDED );
        System.out.println( workSheet );        
    }

    private WorkSheet ripWorkSheetOffForm() 
    {
        int id = model.getMaxIdOfWorkSheets() + 1; 

        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date date = new Date();
        String dateString = dateFormat.format(date); 
        
        Customer customer = createCustomerFromForm();
        int customerId = handleCustomerId( customer, Integer.toString( id ) );
        String problem = view.getAddWorkSheetFormProblemInputAreaText();
        String parts = view.getAddWorkSheetFormCarPartsInputAreaText();
        int colleagueId = handleColleagueId( view.getAddWorkSheetFormColleagueComboBoxText() );
        int time = handleTimeInputFieldText();
        int cost = model.calculateWorkSheetCost( time, colleagueId, parts );
        WorkSheetState state = calculateWorkSheetInitState( parts, colleagueId, time ); 
        WorkSheet workSheet = new WorkSheet( id, dateString, customerId, problem, parts, colleagueId, time, cost, state );        
        return workSheet;
    }

    private WorkSheetState calculateWorkSheetInitState( String parts, int colleagueId, int time ) 
    {
        if( colleagueId != 0 && time != 0 )
        {
            boolean partsAvailable = model.partsAvailable( parts );
            if( partsAvailable )
            {
                model.decreasePartsFromDataBase( parts );
                return WorkSheetState.FELDOLGOZOTT;                
            }
            else return WorkSheetState.ALKATRESZREVAR;               
        }
        else return WorkSheetState.FELDOLGOZANDO;
    }    

    
    private Customer createCustomerFromForm() 
    {
        String name = view.getAddWorkSheetFormCustomerNameInputFieldText();
        String tel = view.getAddWorkSheetFormCustomerTelInputFieldText();
        String numberPlate = view.getAddWorkSheetFormCustomerNumberPlateInputFieldText();
        Customer customer = new Customer( 0, name, tel, numberPlate, "" );
        return customer;
    }

    private int handleCustomerId( Customer customer, String workSheetId ) 
    {        
        return model.handleCustomerId( customer, workSheetId );
    }

    private int handleColleagueId( String colleagueName ) 
    {
        if( colleagueName.equals( "" ) ) return 0;
        return model.handleColleagueId( colleagueName );
    }

    private int handleTimeInputFieldText() 
    {
        int retVal = 0;
        try
        {
            retVal = Integer.parseInt( view.getAddWorkSheetFormWorkHoursInputFieldText() );
        }
        catch( Exception e )
        {
            System.out.println( "Could not parse Hours input field to INT." );
        }
        return retVal;     
    }

    @Override
    public boolean tableHasBeenModified() 
    {
        //return !model.decideIfWorkSheetsAreSame( view.getTableContentInArrayList() );
        return false;
    }

    @Override
    public int getSelectedRowNumber() 
    {
        return view.getSelectedRowNumber();
    }

    @Override
    public int getSelectedColNumber() 
    {
        return view.getSelectedColNumber();
    }

    @Override
    public void updateLocalWorkSheetArray( String value, int workSheetId, String columnName ) 
    {
        model.updateLocalWorkSheetArray( value, workSheetId, columnName );
    }

    @Override
    public void activateSaveDataBtn() 
    {
        view.enableSaveDataButton();
    }

    @Override
    public void enableDeleteButton() 
    {        
        view.enableDeleteButton();
    }

    @Override
    public void disableDeleteButton() 
    {
        view.disableDeleteButton();
    }

    @Override
    public void enableFinalizeButton() 
    {        
        view.enableFinalizeButton();
    }
    
    @Override
    public void disableFinalizeButton() 
    {        
        view.disableFinalizeButton();
    }

    @Override
    public void removeListeners() 
    {
        view.removeListeners();
    }

    @Override
    public boolean checkIfColleagueIdExists( int colleagueId ) 
    {
        return model.checkIfColleagueIdExists( colleagueId );
    }

    @Override
    public boolean checkIfCustomerIdExists( int customerId ) 
    {
        return model.checkIfCustomerIdExists( customerId );
    }

    @Override
    public String[] getCustomerNamesStringArray() 
    {
        return model.getCustomerNamesStringArray();        
    }

    @Override
    public String[] getColleagueNamesStringArray() 
    {
        return model.getColleagueNamesStringArray();        
    }

    @Override
    public void handleFinalizeButton(JTable contentTable) 
    {
        int rowNo = contentTable.getSelectedRow();
        int rowCount = contentTable.getSelectedRowCount();
        String state = contentTable.getValueAt( rowNo, 8 ).toString();
        if( rowCount == 1 && state.equals( "FOLYAMATBAN" ) )
            enableFinalizeButton();                
        else disableFinalizeButton();
    }

    @Override
    public boolean testConnection( String hostString, String dataBaseString, String userString, String passwordString ) 
    {   
        return model.testConnection( hostString, dataBaseString, userString, passwordString );
    }
}
