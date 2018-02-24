package carservice.cache;

import carservice.cache.Cache;
import carservice.enums.ViewState;
import carservice.enums.WorkSheetState;
import carservice.listeners.TablePropertyChangeListener;
import carservice.model.ModelImp;
import carservice.structs.CarPart;
import carservice.structs.Colleague;
import carservice.structs.Customer;
import carservice.structs.Person;
import carservice.structs.WorkSheet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CacheImp implements Runnable, Cache 
{
    private ModelImp model;
    
    private String host = "jdbc:mysql://localhost/carservice";
    //private final String host = "jdbc:mysql://localhost/testdb";
    private String user = "testadmin";
    private String pass = "password";
    private String dbs = "carservice";
    //private final String dbs = "testdb";
    
    private Connection database;    
    private Statement statement;
    private ResultSet resultSet;    
    private Thread thread;

    private ArrayList<WorkSheet> workSheets;
    private ArrayList<Colleague> colleagues;
    private ArrayList<Customer> customers;
    private ArrayList<CarPart> carParts;
    
    private ArrayList<Integer> deletedWorkSheetsIds;
    private ArrayList<WorkSheet> removedWorkSheets;

    private boolean running;
    private boolean customersChanged;
    private boolean workSheetWasDeleted;
    
    private String filterCustomerString;
    private String filterStateString;
    private String filterColleagueString;
    private boolean partsChanged;
    
    public CacheImp() throws ClassNotFoundException 
    {
       Class.forName( "com.mysql.jdbc.Driver");                               
    }           

    @Override
    public void init() 
    {
        initMembers();
        fetchAllData();
        startThread();
    }

    private void initMembers()
    {
        database = null;
        statement = null;
        resultSet = null; 
        running = false;
        customersChanged = false;
        partsChanged = false;
        workSheetWasDeleted = false;
        filterCustomerString = "";
        filterStateString = "";
        filterColleagueString = "";
        customers = new ArrayList<>();
        workSheets = new ArrayList<>();
        colleagues = new ArrayList<>();
        carParts = new ArrayList<>();        
        deletedWorkSheetsIds = new ArrayList<>();
        removedWorkSheets = new ArrayList<>();
    }
    
    private void fetchAllData()
    {
        try 
        {
            fetchWorkSheets();
            fetchColleagues();
            fetchCustomers();
            fetchCarParts();
        }
        catch (SQLException ex) 
        {
            System.out.println( "Statement could not be created." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            try 
            {
                statement.close();
            } 
            catch (SQLException ex) 
            {
                System.out.println( "Statement could not be closed." );
                Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void fetchColleagues() throws SQLException
    {
        openConnection();
        statement = database.createStatement();
        String query = "SELECT * FROM colleagues";
        resultSet = statement.executeQuery( query );            
        processResultSetForColleagues();       
        resultSet.close();
        statement.close();
        closeConnection();
    }

    private void processResultSetForColleagues() throws SQLException
    {
        colleagues.clear();
        while( resultSet.next() )
        {
            int id = resultSet.getInt( "id" );
            String name = resultSet.getString( "name" );
            String address = resultSet.getString( "address" );
            String tel = resultSet.getString( "tel" );
            int rate = resultSet.getInt( "rate" );
            int hours = calculateHoursByColleagueId( id );
            Colleague colleague = new Colleague( id, name, tel, rate, hours, address );
            colleagues.add( colleague );
        }
    }    

    private int calculateHoursByColleagueId( int colleagueId )
    {
        int hours = 0;
        for ( WorkSheet workSheet : workSheets ) 
        {
            if(    ( workSheet.getColleague() == colleagueId )
                && ( workSheet.getState() != WorkSheetState.UNDEFINED )
                && ( workSheet.getState() != WorkSheetState.KIFIZETETT ) )
            {
                int time = workSheet.getTime();
                hours += time;                
            }
        }
        return hours;
    }
   
    private void fetchWorkSheets() throws SQLException
    {
        openConnection();
        statement = database.createStatement();
        String query = "SELECT * FROM worksheets";
        resultSet = statement.executeQuery( query );            
        processResultSetForWorkSheets();        
        resultSet.close();
        statement.close();
        closeConnection();
    }

    private void processResultSetForWorkSheets() throws SQLException
    {
        workSheets.clear();
        while( resultSet.next() )
        {
            int id = resultSet.getInt( "id" );
            String date = resultSet.getString( "date" );
            int customer = resultSet.getInt( "customer" );
            String problem = resultSet.getString( "problem" );
            String parts = resultSet.getString( "parts" );
            int colleague = resultSet.getInt( "colleague" );
            int time = resultSet.getInt( "time" );
            int cost = resultSet.getInt( "cost" );
            WorkSheetState state = convertIntToWorkSheetState( resultSet.getInt( "state" ) );
            WorkSheet workSheet = new WorkSheet( id, date, customer, problem, parts, colleague, time, cost, state );
            workSheets.add( workSheet );
        }
    }    
        
    private void fetchCustomers() throws SQLException
    {
        openConnection();
        statement = database.createStatement();
        String query = "SELECT * FROM customers";
        resultSet = statement.executeQuery( query );            
        processResultSetForCustomers();        
        resultSet.close();
        statement.close();
        closeConnection();
    }

    private void processResultSetForCustomers() throws SQLException
    {
        customers.clear();
        while( resultSet.next() )
        {
            int id = resultSet.getInt( "id" );
            String name = resultSet.getString( "name" );
            String tel = resultSet.getString( "tel" );
            String numberPlate = resultSet.getString( "number_plate" );
            String workSheets = resultSet.getString( "worksheets" );
            int hours = calculateHoursByColleagueId( id );
            Customer customer = new Customer( id, name, tel, numberPlate, workSheets );
            customers.add( customer );
        }
    }        
    
    private void fetchCarParts() throws SQLException
    {
        openConnection();
        statement = database.createStatement();
        String query = "SELECT * FROM store";
        resultSet = statement.executeQuery( query );            
        processResultSetForCarParts();        
        resultSet.close();
        statement.close();
        closeConnection();
    }

    private void processResultSetForCarParts() throws SQLException
    {
        carParts.clear();
        while( resultSet.next() )
        {            
            String id = resultSet.getString( "part_id" );
            String name = resultSet.getString( "part_name" );
            int price = resultSet.getInt( "part_price" );
            int quantity = resultSet.getInt( "part_quantity" );
            CarPart carPart = new CarPart( id, name, price, quantity );
            carParts.add( carPart );
        }
    }            
    
    private void openConnection()
    {
        try {
            database = DriverManager.getConnection( host, user, pass );
        } catch (SQLException ex) {
            System.out.println( "Connection error while openning connection!" );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    private void closeConnection()
    {
        try {
            database.close();
        } catch (SQLException ex) {
            System.out.println( "Connection error while closing connection!" );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private synchronized void startThread() 
    {
        thread = new Thread( this );        
        running = true;
        thread.start();
    }

    @Override
    public void run() 
    {
        // ToDo...   : Cache's cyclic duty
        while( running )
        {
            
        }
    }

    private synchronized void stopThread() throws InterruptedException 
    {
        running = false;
        thread.join();
    }

    @Override
    public Object[][] get_ColleaguesTable_Content() 
    {
        Object[][] obj = new Object[ colleagues.size() ][ 6 ];
        for( int i = 0; i < colleagues.size(); ++i ) 
        {
            int hours = getColleaguesWorkHours( colleagues.get( i ).getId() );
            
            obj[ i ][ 0 ] = colleagues.get( i ).getId();
            obj[ i ][ 1 ] = colleagues.get( i ).getName();
            obj[ i ][ 2 ] = colleagues.get( i ).getAddress();
            obj[ i ][ 3 ] = colleagues.get( i ).getTel();
            obj[ i ][ 4 ] = colleagues.get( i ).getRate();
            //obj[ i ][ 5 ] = colleagues.get( i ).getHours();
            obj[ i ][ 5 ] = hours;
        }
        return obj;
    }

    private int getColleaguesWorkHours( int colleagueId )
    {
        int hours = 0;
        for( WorkSheet workSheet : workSheets ) 
        {
           if(    workSheet.getState() != WorkSheetState.KIFIZETETT 
               && workSheet.getState() != WorkSheetState.UNDEFINED
               && workSheet.getColleague() == colleagueId ) 
               hours += workSheet.getTime();
        }
        return hours;
    }
    
    @Override
    public Object[] get_ColleaguesTable_Headings() 
    {
        return new Object[]{ "ID", "Név", "Cím", "Telefon", "Óradíj", "Munkaórák" };
    }

    @Override
    public Object[][] get_WorkSheetsTable_Content() 
    {
        int numberOfRows = countFilteredWorkSheets();
        Object[][] obj = new Object[ numberOfRows ][ 9 ];
        for( int i = 0, j = 0; i < workSheets.size(); ++i ) 
        {
            if( filteredWorkSheet( workSheets.get( i ) ) )
            {
                obj[ j ][ 0 ] = workSheets.get( i ).getId();
                obj[ j ][ 1 ] = workSheets.get( i ).getDate();
                //obj[ j ][ 2 ] = workSheets.get( i ).getCustomer();
                obj[ j ][ 2 ] = getCustomerNameById( workSheets.get( i ).getCustomer() );
                obj[ j ][ 3 ] = workSheets.get( i ).getProblem();
                obj[ j ][ 4 ] = workSheets.get( i ).getParts();
                //obj[ j ][ 5 ] = workSheets.get( i ).getColleague();
                obj[ j ][ 5 ] = getColleagueNameById( workSheets.get( i ).getColleague() );
                obj[ j ][ 6 ] = workSheets.get( i ).getTime();
                obj[ j ][ 7 ] = workSheets.get( i ).getCost();
                obj[ j ][ 8 ] = workSheets.get( i ).getState();
                //obj[ i ][ 8 ] = convertViewStateToInt( workSheets.get( i ).getState() );
                ++j;                
            }
        }
        return obj;
    }

    private int countFilteredWorkSheets()
    {
        if( filterCustomerString.equals( "" ) && filterStateString.equals( "" ) && filterColleagueString.equals( "" ) )
            return workSheets.size();
        int count = 0;
        if( filterCustomerString.equals( "" ) )
        {
            if( filterColleagueString.equals( "" ) )
            {
                // Csak state alapján         
                WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                for( WorkSheet workSheet : workSheets ) 
                {                    
                    if( workSheet.getState() == filterState )
                        ++count;
                }
                return count;
            }
            else
            {
                if( filterStateString.equals( "" ) )
                {
                    // Csak colleague alapján         
                    String filterName = filterColleagueString;
                    for( WorkSheet workSheet : workSheets ) 
                    {                    
                        String colleagueName = findColleagueById( workSheet.getColleague() ).getName();                        
                        if( colleagueName.equals( filterName ) )
                            ++count;
                    }
                    return count;
                }
                else
                {
                    // csak colleague és state alapján                    
                    String filterName = filterColleagueString;
                    WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                    for( WorkSheet workSheet : workSheets ) 
                    {      
                        String colleagueName = findColleagueById( workSheet.getColleague() ).getName();                        
                        if( colleagueName.equals( filterName ) && workSheet.getState() == filterState )
                            ++count;
                    }
                    return count;
                }
            }
        }
        else
        {
            if( filterColleagueString.equals( "" ) )
            {
                if( filterStateString.equals( "" ) )
                {
                    // csak customer alapján
                    String filterName = filterCustomerString;
                    for( WorkSheet workSheet : workSheets ) 
                    {                    
                        String customerName = this.findCustomerById( workSheet.getCustomer() ).getName();
                        if( customerName.equals( filterName ) )
                            ++count;
                    }
                    return count;
                }
                else
                {
                    // csak customer és state alapján                    
                    String filterName = filterCustomerString;
                    WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                    for( WorkSheet workSheet : workSheets ) 
                    {                    
                        String customerName = findCustomerById( workSheet.getCustomer() ).getName();
                        if( customerName.equals( filterName ) && workSheet.getState() == filterState )
                            ++count;
                    }
                    return count;
                }
            }
            else
            {
                if( filterStateString.equals( "" ) )
                {
                    // csak customer és colleague alapján

                    String filterCustomerName = filterCustomerString;
                    String filterColleagueName = filterColleagueString ;
                    WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                    for( WorkSheet workSheet : workSheets ) 
                    {                    
                        String customerName = findCustomerById( workSheet.getCustomer() ).getName();
                        String colleagueName = findColleagueById( workSheet.getColleague() ).getName();
                        if( customerName.equals( filterCustomerName ) && colleagueName.equals( filterColleagueName ) && workSheet.getState() == filterState )
                            ++count;
                    }
                    return count;
                }
                else
                {
                    // minden alapján                    
                    String filterCustomerName = filterCustomerString;
                    String filterColleagueName = filterColleagueString ;
                    WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                    for( WorkSheet workSheet : workSheets ) 
                    {                    
                        String customerName = findCustomerById( workSheet.getCustomer() ).getName();
                        String colleagueName = findColleagueById( workSheet.getColleague() ).getName();
                        if( customerName.equals( filterCustomerName ) && colleagueName.equals( filterColleagueName ) && workSheet.getState() == filterState )
                            ++count;
                    }
                    return count;
                }
            }            
        }
    }

    private WorkSheetState convertStringToWorkSheetState( String workSheetStateString )
    {
        workSheetStateString.toUpperCase();
        if( workSheetStateString.equals( "FELDOLGOZANDO" ) )
            return WorkSheetState.FELDOLGOZANDO; 
        else if( workSheetStateString.equals( "ALKATRESZREVAR" ) )
            return WorkSheetState.ALKATRESZREVAR; 
        else if( workSheetStateString.equals( "FELDOLGOZOTT" ) )
            return WorkSheetState.FELDOLGOZOTT; 
        else if( workSheetStateString.equals( "FOLYAMATBAN" ) )
            return WorkSheetState.FOLYAMATBAN; 
        else if( workSheetStateString.equals( "KIFIZETETT" ) )
            return WorkSheetState.KIFIZETETT; 
        else return WorkSheetState.UNDEFINED;
    }
    
    private boolean filteredWorkSheet( WorkSheet workSheet ) 
    {
        if( filterCustomerString.equals( "" ) && filterStateString.equals( "" ) && filterColleagueString.equals( "" ) )
            return true;
        if( filterCustomerString.equals( "" ) )
        {
            if( filterColleagueString.equals( "" ) )
            {
                // Csak state alapján         
                WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                return ( workSheet.getState() == filterState );
            }
            else
            {
                if( filterStateString.equals( "" ) )
                {
                    // Csak colleague alapján         
                    String filterName = filterColleagueString;
                    String colleagueName = this.findColleagueById( workSheet.getColleague() ).getName();
                    return ( colleagueName.equals( filterName ) );
                }
                else
                {
                    // csak colleague és state alapján                    
                    String filterName = filterColleagueString;
                    String colleagueName = this.findColleagueById( workSheet.getColleague() ).getName();
                    WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                    return ( colleagueName.equals( filterName ) && workSheet.getState() == filterState );
                }
            }
        }
        else
        {
            if( filterColleagueString.equals( "" ) )
            {
                if( filterStateString.equals( "" ) )
                {
                    // csak customer alapján
                    String filterName = filterCustomerString;
                    String customerName = this.findCustomerById( workSheet.getCustomer() ).getName();
                    return ( customerName.equals( filterName ) );
                }
                else
                {
                    // csak customer és state alapján                    
                    String filterName = filterCustomerString;
                    String customerName = this.findCustomerById( workSheet.getCustomer() ).getName();
                    WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                    return ( customerName.equals( filterName ) && workSheet.getState() == filterState );
                }
            }
            else
            {
                if( filterStateString.equals( "" ) )
                {
                    // csak customer és colleague alapján
                    String filterCustomerName = filterCustomerString;
                    String customerName = findCustomerById( workSheet.getCustomer() ).getName();
                    String filterColleagueName = filterColleagueString;
                    String colleagueName = this.findColleagueById( workSheet.getColleague() ).getName();
                    int filterColleagueId = findFilterColleagueByName( filterColleagueString );
                    //int filterColleagueId = Integer.parseInt( filterColleagueString );
                    return ( customerName.equals( filterCustomerName ) && colleagueName.equals( filterColleagueName ) );
                }
                else
                {
                    // minden alapján                    
                    String filterCustomerName = filterCustomerString;
                    String customerName = this.findCustomerById( workSheet.getCustomer() ).getName();
                    String filterColleagueName = filterColleagueString;
                    String colleagueName = this.findColleagueById( workSheet.getColleague() ).getName();
                    WorkSheetState filterState = convertStringToWorkSheetState( filterStateString );
                    return ( customerName.equals( filterCustomerName ) && colleagueName.equals( filterColleagueName ) && workSheet.getState() == filterState );
                }
            }            
        }
    }
    
    private int findFilterColleagueByName( String colleagueName )
    {
        for( Colleague colleague : colleagues ) 
        {
            if( colleague.getName().equals( colleagueName ) )
                return colleague.getId();
        }
        return -1;
    }

    private String getCustomerNameById( int customerId )
    {
        for (Customer customer : customers) 
        {
            if( customer.getId() == customerId )
                return customer.getName();
        }
        return "";
    }

    private String getColleagueNameById( int colleagueId )
    {
        for (Colleague colleague : colleagues) 
        {
            if( colleague.getId() == colleagueId )
                return colleague.getName();
        }
        return "";
    }
    
    private int convertWorkSheetStateToInt( WorkSheetState state )
    {
        switch( state )
        {
            case FELDOLGOZANDO: return 0; 
            case ALKATRESZREVAR: return 1; 
            case FELDOLGOZOTT: return 2; 
            case FOLYAMATBAN: return 3; 
            case KIFIZETETT: return 4; 
            default: return -1;
        }
    }

    private WorkSheetState convertIntToWorkSheetState( int state )
    {
        switch( state )
        {
            case 0: return WorkSheetState.FELDOLGOZANDO; 
            case 1: return WorkSheetState.ALKATRESZREVAR; 
            case 2: return WorkSheetState.FELDOLGOZOTT; 
            case 3: return WorkSheetState.FOLYAMATBAN; 
            case 4: return WorkSheetState.KIFIZETETT; 
            default: return WorkSheetState.UNDEFINED;
        }
    }

    
    @Override
    public Object[] get_WorkSheetsTable_Headings() 
    {
        return new Object[]{ "ID", "Dátum", "Ügyfél", "Probléma", "Alkatrészek", "Munkatárs", "Órák", "Teljes ár", "Állapot" };
    }

    @Override
    public Object[][] get_CustomersTable_Content() 
    {
        Object[][] obj = new Object[ customers.size() ][ 6 ];
        for( int i = 0; i < customers.size(); ++i ) 
        {
            obj[ i ][ 0 ] = customers.get( i ).getId();
            obj[ i ][ 1 ] = customers.get( i ).getName();
            obj[ i ][ 2 ] = customers.get( i ).getTel();
            obj[ i ][ 3 ] = customers.get( i ).getNumberPlate();
            obj[ i ][ 4 ] = customers.get( i ).getWorkSheets();
        }
        return obj;
    }

    @Override
    public Object[] get_CustomersTable_Headings() 
    {        
        return new Object[]{ "ID", "Név", "Telefon", "Rendszám", "Munkalap(ok)" };
    }

    @Override
    public Object[][] get_CarPartsTable_Content() 
    {
        Object[][] obj = new Object[ carParts.size() ][ 6 ];
        for( int i = 0; i < carParts.size(); ++i ) 
        {
            obj[ i ][ 0 ] = carParts.get( i ).getId();
            obj[ i ][ 1 ] = carParts.get( i ).getName();
            obj[ i ][ 2 ] = carParts.get( i ).getPrice();
            obj[ i ][ 3 ] = carParts.get( i ).getQuantity();
        }
        return obj;
    }

    @Override
    public Object[] get_CarPartsTable_Headings() 
    {
        return new Object[]{ "ID", "Név", "Egységár", "Mennyiség" };
    }

    @Override
    public String[] getColleaguesNamesStringArray() 
    {
        String[] names = new String[ colleagues.size() + 1 ];
        names[ 0 ] = "";
        for ( int i = 1; i < colleagues.size() + 1; ++i ) 
        {
            names[ i ] = colleagues.get( i - 1 ).getName();
        }
        return names;                
    }

    @Override
    public int getMaxIdOfWorkSheets() 
    {
        int maxId = 0;
        try 
        {
            openConnection();
            statement = database.createStatement();
            String query = "SELECT MAX(id) FROM worksheets";
            resultSet = statement.executeQuery( query );
            if( resultSet.next() ) maxId = resultSet.getInt( 1 );
            
            System.out.println( "Max workSheetID: " + maxId );
            
            resultSet.close();
            statement.close();
            closeConnection();
        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Statemment creation exception while asking for max worksheet id." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxId;
   }

    @Override
    public int handleCustomerId( Customer customer, String workSheetId )
    {
        int customerId = 0;
        for( Customer cus : customers ) 
        {
            if(    ( cus.getName().equals( customer.getName() ) ) 
                && ( cus.getTel().equals( customer.getTel() ) ) 
                && ( cus.getNumberPlate().equals( customer.getNumberPlate() ) ) 
              )
            {
                cus.addWorkSheet( workSheetId );
                updateCustomerWorkSheetsInDataBase( cus );
                return cus.getId();                                        
            }
        }
        customer.setWorkSheets( workSheetId );
        customerId = insertNewCustomerIntoDataBase( customer );        
        System.out.println( customer );
        return customerId;
    }

    private void updateCustomerWorkSheetsInDataBase( Customer customer )
    {
        openConnection();        
        try 
        {
            statement = database.createStatement();
            int id = customer.getId();
            String workSheets = customer.getWorkSheets();
            String query = "UPDATE customers SET worksheets='" + workSheets + "' WHERE id=" + id;
            statement.executeUpdate( query );            
            statement.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println( "Could not update new worksheets sting to customer." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }

    private int insertNewCustomerIntoDataBase( Customer customer ) 
    {
        customer.setId( getMaxIdOfCustomers() + 1 ); 
        
        int id = customer.getId();
        String name = customer.getName();
        String tel = customer.getTel();
        String numberPlate = customer.getNumberPlate();
        String workSheets = customer.getWorkSheets();
 
        openConnection();
        try {
            statement = database.createStatement();
            String query =   "INSERT INTO customers "
                           + "VALUES("
                           + id
                           + ", '"
                           + name
                           + "', '"
                           + tel
                           + "', '"
                           + numberPlate
                           + "', '"
                           + workSheets
                           + "')";
            statement.executeUpdate( query );
            statement.close();
        } catch (SQLException ex) {
            System.out.println( "Could not insert new customer into database." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        openConnection();

        refresh();

        return id;
    }

    @Override
    public void refresh() 
    {
        fetchAllData();        
    }

    @Override
    public int findColleagueIdByName( String colleagueName ) 
    {
        for( Colleague colleague : colleagues ) 
        {
            if( colleague.getName().equals( colleagueName ) )
                return colleague.getId();
        }
        return 0;
    }

    private int getMaxIdOfCustomers() 
    {
        int maxId = 0;
        openConnection();
        try 
        {
            statement = database.createStatement();
            String query = "SELECT MAX(id) from customers";
            resultSet = statement.executeQuery( query );
            if( resultSet.next() ) maxId = resultSet.getInt( 1 );
            resultSet.close();
            statement.close();
        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Statemment creation exception while asking for max user id." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
        return maxId;
    }

    @Override
    public void insetNewWorkSheetIntoDataBase( WorkSheet workSheet ) 
    {
        int id = workSheet.getId();
        String date = workSheet.getDate();
        int customer = workSheet.getCustomer();
        String problem = workSheet.getProblem();
        String parts = workSheet.getParts();
        int colleague = workSheet.getColleague();
        int time = workSheet.getTime();
        int cost = workSheet.getCost();
        int state = convertWorkSheetStateToInt( workSheet.getState() );
        
        openConnection();
        try 
        {
            statement = database.createStatement();
            String query = "INSERT INTO worksheets "
                    + "VALUES("
                    + id
                    + ", '"
                    + date
                    + "', "
                    + customer
                    + ", '"
                    + problem
                    + "', '"
                    + parts
                    + "', "
                    + colleague
                    + ", "
                    + time
                    + ", "
                    + cost
                    + ", "
                    + state
                    + ")";
            statement.executeUpdate( query );
            statement.close();            
        } 
        catch (SQLException ex) 
        {
            System.out.println( "Could not insert worksheet into database." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }        
        closeConnection();
    }

    @Override
    public int calculateWorkSheetCost( int time, int colleagueId, String parts ) 
    {
        int rate = findRateByColleagueId( colleagueId );
        int sumPriceOfParts = sumPriceOfParts( parts );

        return ( time * rate + sumPriceOfParts );
    }

    private int findRateByColleagueId( int colleagueId ) 
    {
        for( Colleague colleague : colleagues ) 
        {   
            if( colleague.getId() == colleagueId )
                return colleague.getRate();
        }
        return 0;
    }

    private int sumPriceOfParts( String parts )  
    {
        int sum = 0;
        String[] partsArr = parts.split( "," );
        for( String partId : partsArr ) 
        {
            for( CarPart carPart : carParts ) 
            {
                if( carPart.getId().equals( partId ) )
                    sum += carPart.getPrice();
            }
        }
        return sum;
    }

    @Override
    public boolean partsAvailable( String parts )             
    {
        if( parts.equals( "" ) ) return true;
            
        String[] partsArr = parts.split( "," );
        for( String partId : partsArr ) 
        {
            boolean available = false;
            for( CarPart carPart : carParts ) 
            {
                if(    ( carPart.getId().equals( partId ) ) 
                    && ( carPart.getQuantity() > 0 ) )
                    available = true;
            }
            if( !available ) return false;
        }
        return true;
    }

    @Override
    public void decreasePartsFromDataBase( String parts ) 
    {
        String[] partsArr = parts.split( "," );
        for( int i = 0; i < partsArr.length; ++i ) 
        {
            for( CarPart carPart : carParts ) 
            {
                if( carPart.getId().equals( partsArr[ i ] ) ) 
                    decreaseCarPartQuantityByOneAtDataBase( partsArr[ i ] );
            }            
        }
    }

    private void decreaseCarPartQuantityByOneAtDataBase( String partId ) 
    {
        String newQuantity = Integer.toString( getCarPartQuantityById( partId ) - 1 );
        openConnection();
        try 
        {
            statement = database.createStatement();
            String query = "UPDATE store SET part_quantity=" + newQuantity + " WHERE part_id='" + partId + "'";
            statement.executeUpdate( query );
            statement.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println( "Could not decrease part quantity on database." );
            Logger.getLogger( CacheImp.class.getName() ).log( Level.SEVERE, null, ex );
        }        
        closeConnection();
    }

    private int getCarPartQuantityById( String partId ) 
    {
        int quantity = 0;
        openConnection();
        try 
        {
            statement = database.createStatement();
            String query = "SELECT part_quantity FROM store WHERE part_id='" + partId + "'";
            resultSet = statement.executeQuery( query );
            if( resultSet.next() ) quantity = resultSet.getInt( 1 );
            statement.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println( "Could not get part quantity on database." );
            Logger.getLogger( CacheImp.class.getName() ).log( Level.SEVERE, null, ex );
        }        
        closeConnection();        
        return quantity;
    }

    @Override
    public void updateLocalWorkSheetArray( String value, int workSheetId, String columnName ) 
    {
        for( WorkSheet workSheet : workSheets ) 
        {
            if( workSheet.getId() == workSheetId )
                updateWorkSheet( workSheet, value, columnName );
        }
    }

    private void updateWorkSheet( WorkSheet workSheet, String value, String columnName ) 
    {
        if( columnName.equals( "customer" ) ) 
            handleCustomerChangeOnWorkSheet( workSheet, value );
        else if( columnName.equals( "problem" ) )
            handleProblemChangeOnWorkSheet( workSheet, value );
        else if( columnName.equals( "parts" ) )
            handleCarPartsChangeOnWorkSheet( workSheet, value );            
        else if( columnName.equals( "colleague" ) )
            handleColleagueChangeOnWorkSheet( workSheet, value );            
        else if( columnName.equals( "hours" ) )
            handleTimeChangeOnWorkSheet( workSheet, value );            
    }
        
    @Override
    public void saveData() 
    {
        ArrayList<Integer> ids = TablePropertyChangeListener.changedWorkSheetids;

        for( Integer id : ids ) 
        {
            WorkSheet ws = findWorkSheetById( id );
            if( ws == null ) continue;            
            updateWorkSheetIntoDataBasePartial( ws );
        }
        
        if( customersChanged )
        {
            updateEachCustomerWorkSheetsIntoDataBase();
            customersChanged = false;
        }

        if( partsChanged )
        {
            updateStoreInDataBase();
            partsChanged = false;
        }
        
        if( workSheetWasDeleted )
        {
            deleteWorkSheetsFromDataBase();
            updateStoreInDataBase();
            workSheetWasDeleted = false;
            removedWorkSheets = new ArrayList<>();
            deletedWorkSheetsIds = new ArrayList<>();
        }
    }
    
    private void updateStoreInDataBase()
    {
        for( CarPart carPart : carParts ) 
        {
            updateCarPartIntoStoreDataBase( carPart );
        }
    }

    private void updateCarPartIntoStoreDataBase( CarPart carPart )
    {
        openConnection();                
        try 
        {
            String partId = carPart.getId();
            int quantity = carPart.getQuantity();            
            
            statement = database.createStatement();
            String query = "UPDATE store SET part_quantity=" + quantity + " WHERE part_id='" + partId + "'";
            statement.executeUpdate( query );            
            statement.close();            
        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Could not update carPart into database." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeConnection();
    }
    
    private void deleteWorkSheetsFromDataBase()
    {
        System.out.println( "number of deletedWorkSheetIds: " + deletedWorkSheetsIds.size() );
        for( Integer workSheetId : deletedWorkSheetsIds ) 
            deleteWorkSheetsFromDataBaseById( workSheetId );
    }

    private void deleteWorkSheetsFromDataBaseById( int workSheetId )
    {
        openConnection();
            
        try 
        {
            statement = database.createStatement();
            String query = "DELETE FROM worksheets WHERE id=" + workSheetId;
            System.out.println( "Query: " + query );
            statement.executeUpdate( query );
            statement.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println( "Could not delete worksheet from database." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        closeConnection();
    }
    
    private WorkSheet findWorkSheetById( Integer id ) 
    {
        for( WorkSheet workSheet : workSheets ) 
        {
            if( workSheet.getId() == (int)id ) return workSheet;
        }
        return null;
    }

    private void updateWorkSheetIntoDataBasePartial( WorkSheet ws ) 
    {
        openConnection();        
        try 
        {
            int id = ws.getId();
            int customer = ws.getCustomer();
            String problem = ws.getProblem();
            String parts = ws.getParts();
            int colleague  = ws.getColleague();
            int time = ws.getTime();
            int cost = ws.getCost();
            int state = convertWorkSheetStateToInt( ws.getState() );
            
            statement = database.createStatement();
            String query =   "UPDATE worksheets SET "
                           + "customer=" 
                           + customer 
                           + ", problem='"
                           + problem
                           + "', parts='"
                           + parts
                           + "', colleague="
                           + colleague
                           + ", time="
                           + time
                           + ", cost="
                           + cost
                           + ", state="
                           + state
                           + " WHERE id="
                           + id;
            statement.executeLargeUpdate( query );
            statement.close();
        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Could not update worksheet to database." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }        
        closeConnection();
    }

    
    private void updateWorkSheetIntoDataBaseFully( WorkSheet ws ) 
    {
        openConnection();        
        try 
        {
            int id = ws.getId();
            String date = ws.getDate();
            int customer = ws.getCustomer();
            String problem = ws.getProblem();
            String parts = ws.getParts();
            int colleague  = ws.getColleague();
            int time = ws.getTime();
            int cost = ws.getCost();
            int state = convertWorkSheetStateToInt( ws.getState() );
            
            statement = database.createStatement();
            String query =   "UPDATE worksheets SET "
                           + "customer=" 
                           + customer 
                           + ", date='" 
                           + date 
                           + "', problem='"
                           + problem
                           + "', parts='"
                           + parts
                           + "', colleague="
                           + colleague
                           + ", time="
                           + time
                           + ", cost="
                           + cost
                           + ", state="
                           + state
                           + " WHERE id="
                           + id;
            statement.executeLargeUpdate( query );
            statement.close();
        } 
        catch ( SQLException ex ) 
        {
            System.out.println( "Could not update worksheet to database." );
            Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
        }        
        closeConnection();
    }
    
    @Override
    public void setModel( ModelImp model ) 
    {
        this.model = model;
    }

    @Override
    public boolean checkIfColleagueIdExists( int colleagueId ) 
    {   
        for( Colleague colleague : colleagues ) 
        {
            if( colleague.getId() == colleagueId )
                return true;
        }
        return false;
    }

    @Override
    public boolean checkIfCustomerIdExists( int customerId ) 
    {
        for( Customer customer : customers ) 
        {
            if( customer.getId() == customerId )
                return true;
        }
        return false;        
    }

    private WorkSheetState calculateWorkSheetState( String parts, int colleagueId, int time ) 
    {
        WorkSheetState state = null;
        if( partsAvailable( parts ) && colleagueId > 0 && time > 0 )
        {
            decreasePartsFromLocalDataBase( parts );
            state = WorkSheetState.FELDOLGOZOTT;             
        }
        else if( partsAvailable( parts ) )
            state = WorkSheetState.FELDOLGOZANDO; 
        else 
            state = WorkSheetState.ALKATRESZREVAR;                   
        return state;
    }

    private void decreasePartsFromLocalDataBase( String parts )
    {
        String[] partsArr = parts.split( "," );
        for( String partId : partsArr ) 
        {
            for( CarPart carPart : carParts ) 
            {   
                if( carPart.getId().equals( partId ) )
                    carPart.setQuantity( carPart.getQuantity(  ) - 1 );
            }
        }
    }
    
    private void handleCustomerChangeOnWorkSheet( WorkSheet workSheet, String value ) 
    {
        int oldCustomerId = workSheet.getCustomer();
        workSheet.setCustomer( Integer.parseInt( value ) );
        setCustomerWorkSheetToNewWorkSheetId( workSheet.getId(), oldCustomerId, workSheet.getCustomer() );
        customersChanged = true;
    }

    private void handleProblemChangeOnWorkSheet(WorkSheet workSheet, String value) 
    {
        workSheet.setProblem( value );
    }

    private void handleCarPartsChangeOnWorkSheet( WorkSheet workSheet, String value )
    { 
        String oldValue = workSheet.getParts();
        //putPartsBackToStore( oldValue );        
        putPartsBackToLocalStore( oldValue );        
        workSheet.setParts( value );
        
        int time = workSheet.getTime();
        int colleagueId = workSheet.getColleague();
        String parts = workSheet.getParts();        
        
        int cost = calculateWorkSheetCost( time, colleagueId, parts );
        workSheet.setCost( cost );
        
        WorkSheetState state = calculateWorkSheetState(parts, colleagueId, time);
        workSheet.setState( state );
        
        //decreasePartsFromDataBase( parts );
        partsChanged = true;
   }            

    private void putPartsBackToLocalStore( String parts )
    {
        String[] partsArr = parts.split( "," );
        for( String partId : partsArr ) 
        {
            for( CarPart carPart : carParts ) 
            {
                if( carPart.getId().equals( partId ) )
                    carPart.setQuantity( carPart.getQuantity() + 1 );
            }
        }
    }                
    
    private void handleColleagueChangeOnWorkSheet(WorkSheet workSheet, String value) 
    {
        workSheet.setColleague( Integer.parseInt( value ) );

        int time = workSheet.getTime();
        int colleagueId = workSheet.getColleague();
        String parts = workSheet.getParts();        
        
        int cost = calculateWorkSheetCost( time, colleagueId, parts );
        workSheet.setCost( cost );
        
        WorkSheetState state = calculateWorkSheetState(parts, colleagueId, time);
        workSheet.setState( state );         
    }

    private void handleTimeChangeOnWorkSheet(WorkSheet workSheet, String value) 
    {
        workSheet.setTime( Integer.parseInt( value ) );        
        
        int time = workSheet.getTime();
        int colleagueId = workSheet.getColleague();
        String parts = workSheet.getParts();        
        
        int cost = calculateWorkSheetCost( time, colleagueId, parts );
        workSheet.setCost( cost );
        
        WorkSheetState state = calculateWorkSheetState(parts, colleagueId, time);
        workSheet.setState( state );         
    }

    private void setCustomerWorkSheetToNewWorkSheetId( int workSheetId, int oldCustomerId, int newCustomerId )
    {
        earseWorkSheetIdFromCustomer( workSheetId, oldCustomerId );
        insertWorkSheetIdToCustomer( workSheetId, newCustomerId );
    }

    private void earseWorkSheetIdFromCustomer( int workSheetId, int customerId )
    {
        Customer customer = findCustomerById( customerId );
        String oldWorkSheetsString = customer.getWorkSheets();
        String[] oldWorkSheetsStringArr = oldWorkSheetsString.split( "," );
        String newWorkSheetsString = "";
        boolean added = false;

        for ( String workSheetIdString : oldWorkSheetsStringArr ) 
        {            
            if( Integer.parseInt( workSheetIdString ) == workSheetId ) continue;
            if( added == true )
            {
                newWorkSheetsString += ",";
                added = false;
            }
            newWorkSheetsString += workSheetIdString;
            added = true;
        }
        customer.setWorkSheets( newWorkSheetsString );
    }
    
    private Customer findCustomerById( int customerId )
    {
        for( Customer customer : customers ) 
        {
            if( customer.getId() == customerId )
                return customer;
        }
        return null;
    }

    private Colleague findColleagueById( int colleagueId )
    {
        for( Colleague colleague : colleagues ) 
        {
            if( colleague.getId() == colleagueId )
                return colleague;
        }
        return null;
    }
   
    private void insertWorkSheetIdToCustomer( int workSheetId, int customerId )
    {
        Customer customer = findCustomerById( customerId );        
        String workSheetsString = customer.getWorkSheets();
        if( workSheetsString.length() > 0 ) workSheetsString += ( "," + workSheetId );
        else workSheetsString += workSheetId;
        customer.setWorkSheets( workSheetsString );
    }
    
    private void putPartsBackToStore( String parts )
    {
        String[] partsArr = parts.split(",");
        String partsToIncrease = "";
        boolean added = false;
        for( String partId : partsArr ) 
        {
            if( added ) partsToIncrease += ",";
            added = false;
            for( CarPart carPart : carParts ) 
            {
                if( carPart.getId().equals( partId ) )
                {
                    partsToIncrease += partId;
                    added = true;                    
                }                
            }                
        }
        increasePartsInDataBase( partsToIncrease );
    }
    
    private void increasePartsInDataBase( String parts ) 
    {
        String[] partsArr = parts.split( "," );
        for( int i = 0; i < partsArr.length; ++i ) 
        {
            for( CarPart carPart : carParts ) 
            {
                if( carPart.getId().equals( partsArr[ i ] ) ) 
                    increaseCarPartQuantityByOneAtDataBase( partsArr[ i ] );
            }            
        }
    }
    
    private void increaseCarPartQuantityByOneAtDataBase( String partId ) 
    {
        String newQuantity = Integer.toString(getCarPartQuantityById( partId ) + 1 );
        openConnection();
        try 
        {
            statement = database.createStatement();
            String query = "UPDATE store SET part_quantity=" + newQuantity + " WHERE part_id='" + partId + "'";
            statement.executeUpdate( query );
            statement.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println( "Could not decrease part quantity on database." );
            Logger.getLogger( CacheImp.class.getName() ).log( Level.SEVERE, null, ex );
        }        
        closeConnection();
    }

    private void updateEachCustomerWorkSheetsIntoDataBase() 
    {
        for( Customer customer : customers ) 
            updateCustomerWorkSheetsInDataBase( customer );
    }

    @Override
    public void deleteWorkSheetsById( ArrayList<Integer> selectedWorkSheetsIds ) 
    {
        for( Integer workSheetsId : selectedWorkSheetsIds ) 
            deleteWorkSheet( workSheetsId );
    }

    private void deleteWorkSheet( Integer workSheetsId ) 
    {
        WorkSheet ws = this.findWorkSheetById( workSheetsId );
        if( ws.getState() != WorkSheetState.ALKATRESZREVAR && ws.getState() != WorkSheetState.UNDEFINED )           
            //    putPartsBackToStore( ws.getParts() );
            this.putPartsBackToLocalStore( ws.getParts() );
        workSheets.remove( ws );
        deletedWorkSheetsIds.add( ws.getId() );
        removedWorkSheets.add( ws );
        workSheetWasDeleted = true;
    }

    @Override
    public String[] getCustomerNamesStringArray() 
    {
        String[] names = new String[ customers.size() + 1 ];
        names[ 0 ] = "";
        for( int i = 1; i < customers.size() + 1; ++i ) 
            names[ i ] = customers.get( i - 1 ).getName();
        return names;
    }
    
    @Override
    public String[] getColleagueNamesStringArray() 
    {
        String[] names = new String[ colleagues.size() + 1 ];
        names[ 0 ] = "";
        for( int i = 1; i < colleagues.size() + 1; ++i ) 
            names[ i ] = colleagues.get( i - 1 ).getName();
        return names;
    }

    @Override
    public void setFilterProperties(String filterCustomerString, String filterStateString, String filterColleagueString) 
    {
        this.filterCustomerString = filterCustomerString;
        this.filterStateString = filterStateString;
        this.filterColleagueString = filterColleagueString;
    }

    @Override
    public void finalizeWorkSheet( int workSheetId ) 
    {
        System.out.println( "ITT VAGYOK" );
        for( WorkSheet workSheet : workSheets ) 
        {
            if( workSheet.getId() == workSheetId )
                workSheet.setState( WorkSheetState.KIFIZETETT );
        }
    }

    @Override
    public boolean testConnection( String hostString, String dataBaseString, String user, String pass ) 
    {
        System.out.println( "Testing..." );
        String host = hostString + dataBaseString;
        try 
        {
            database = DriverManager.getConnection( host, user, pass );
            statement = database.createStatement();
            String query = "SELECT * FROM worksheets";
            resultSet = statement.executeQuery( query );
            query = "SELECT * FROM colleagues";
            resultSet = statement.executeQuery( query );
            query = "SELECT * FROM customers";
            resultSet = statement.executeQuery( query );
            query = "SELECT * FROM store";
            resultSet = statement.executeQuery( query );
            resultSet.close();
            statement.close();
            database.close();
        } 
        catch (SQLException ex) 
        {
            System.out.println( "Test has failed!" );
            //Logger.getLogger(CacheImp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        this.host = host;
        this.user = user;
        this.pass = pass;
        System.out.println( "Test was successful!" );
        return true;
    }
}

