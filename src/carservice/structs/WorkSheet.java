package carservice.structs;

import carservice.enums.WorkSheetState;

public class WorkSheet 
{
    private int id;
    private String date; 
    private int customer; 
    private String problem; 
    private String parts; 
    private int colleague; 
    private int time; 
    private int cost; 
    private WorkSheetState state;
    
    public WorkSheet( int id, String date, int customer, String problem, String parts, int colleague, int time, int cost, WorkSheetState state ) 
    {
        setId( id );
        setDate( date );
        setCustomer( customer );
        setProblem( problem );
        setParts( parts );
        setColleague( colleague );
        setTime( time );
        setCost( cost );
        setState( state );
    }

    public int getId() 
    {
        return id;
    }

    public String getDate() 
    {
        return date;
    }

    public int getCustomer() 
    {
        return customer;
    }

    public String getProblem() 
    {
        return problem;
    }

    public String getParts() 
    {
        return parts;
    }

    public int getColleague() 
    {
        return colleague;
    }

    public int getTime() 
    {
        return time;
    }

    public int getCost() 
    {
        return cost;
    }

    public WorkSheetState getState() 
    {
        return state;
    }
    
    public void setId( int id ) 
    {
        this.id = id;
    }

    public void setDate( String date ) 
    {
        this.date = date;
    }

    public void setCustomer( int customer ) 
    {
        this.customer = customer;
    }

    public void setProblem( String problem ) 
    {
        this.problem = problem;
    }
    
    public void setParts( String parts ) 
    {
        this.parts = parts;
    }

    public void setColleague( int colleague ) 
    {
        this.colleague = colleague;
    }

    public void setTime( int time ) 
    {
        this.time = time;
    }

    public void setCost( int cost ) 
    {
        this.cost = cost;
    }

    public void setState( WorkSheetState state ) 
    {
        this.state = state;
    }
    
    @Override
    public String toString()
    {
        return    "\nWORKSHEET:"
                + "\n--------\n"
                + "ID: " + id
                + ", DATE: " + date
                + ", CUSTOMER: " + customer
                + ", PROBLEM: " + problem
                + ", PARTS: " + parts
                + ", COLLEAGUEID: " + colleague
                + ", TIME: " + time
                + ", COST: " + cost
                + ", STATE: " + state;
    }
}
