package carservice.structs;

abstract public class Person 
{
    private int id;
    private String name;
    private String tel;

    public Person( int id, String name, String tel )            
    {
        setId( id );
        setName( name );
        setTel( tel );
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getTel()
    {
        return tel;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public void setName(String name) 
    {
        this.name = name;
    }

    public void setTel(String tel) 
    {
        this.tel = tel;
    }
    
}
