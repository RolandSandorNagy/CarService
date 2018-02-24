package carservice.structs;

public final class Customer extends Person
{
    private String numberPlate;
    private String workSheets;
    
    public Customer( int id, String name, String tel, String numberPlate, String workSheets ) {
        super( id, name, tel );
        setNumberPlate( numberPlate );
        setWorkSheets( workSheets );
    }

    public String getNumberPlate() 
    {   
        return numberPlate;
    }

    public String getWorkSheets() 
    {   
        return workSheets;
    }

    public void setNumberPlate( String numberPlate ) 
    {
        this.numberPlate = numberPlate;
    }
     
    public void setWorkSheets( String workSheets ) 
    {
        this.workSheets = workSheets;
    }

    public void addWorkSheet(String workSheetId) 
    {
        workSheets += ( "," + workSheetId );
    }
    
    @Override
    public String toString()
    {
        return    "\nCUSTOMER:"
                + "\n--------\n"                
                + "ID: " + getId()
                + ", NAME: " + getName()
                + ", TEL: " + getTel()
                + ", NUMBERPLATE: " + numberPlate
                + ", WORKSHEETS: " + workSheets;
    }
}
