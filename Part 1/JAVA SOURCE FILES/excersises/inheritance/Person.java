package excersises.inheritance;

public abstract class Person 
{
    private String name;
    private String surname;

    {
        name = "";
        surname="";
    }

    public String getName()
    {
        return name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setName(String _name)
    {
        name=_name;
    }

    public void setSurname(String _sname)
    {
        surname = _sname;
    }
 
    public Person(){}
    public Person(String n, String sname){name=n; surname = sname;} 


    public abstract String getDescription();
}
