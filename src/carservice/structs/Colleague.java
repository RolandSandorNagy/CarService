package carservice.structs;

public final class Colleague extends Person
{
    private String address;
    private int rate;
    private int hours;
    
    public Colleague( int id, String name, String tel, int rate, int hours, String address ) 
    {
        super( id, name, tel );
        setRate( rate );
        setHours( hours );
        setAddress( address );
    }

    public int getRate()
    {
        return rate;
    }

    public int getHours()
    {
        return hours;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setRate( int rate ) 
    {
        this.rate = rate;
    }

    public void setHours( int hours ) 
    {
        this.hours = hours;
    }

    public void setAddress( String address ) 
    {
        this.address = address;
    }
    
}
