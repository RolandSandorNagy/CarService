package carservice.structs;

public final class CarPart
{
    private String id; 
    private String name; 
    private int price; 
    private int quantity; 
    
    public CarPart( String id, String name, int price, int quantity ) 
    {
        setId( id );
        setName( name );
        setPrice( price );
        setQuantity( quantity );
    }

    public String getId()
    {
        return id;
    }
    
    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setId( String id )
    {
        this.id = id;
    }
    
    public void setName( String name )
    {
        this.name = name;
    }

    public void setPrice( int price )
    {
        this.price = price;
    }

    public void setQuantity( int quantity )
    {
        this.quantity = quantity;
    }
}
