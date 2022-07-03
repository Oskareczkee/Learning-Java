package excersises.inheritance;

import java.util.*;

public class Employee extends Person
{
    //test
    public static void main(String[] args) throws Exception {
        Employee test = new Employee("Agata", "Siemowit", new GregorianCalendar().getTime(), 4200.0);
        Employee test2 = new Employee();

        System.out.println("Employee class test");
        System.out.println(test);
        System.out.println(test2);
        System.out.println(test.equals(test2));
        System.out.println(test.equals(test));
        System.out.printf("test hash: %d\ntest hash2: %d", test.hashCode(), test2.hashCode());
    }

    private static int nextID=1;

    private Double salary;
    private final int ID;
    private Date joinDate;

    {
        salary = 0.0;
        ID = nextID;
        nextID++;
        joinDate = new GregorianCalendar().getTime();
    }

    public Employee(){
        super();
    }

    public Employee(String n, String sn, Date jd, Double s)
    {
        super(n, sn);
        joinDate=jd;
        salary=s;
    }

    public Double getSalary()
    {
        return salary;
    }

    public int getID()
    {
        return ID;
    }

    public Date getJoinDate()
    {
        return joinDate;
    }

    public void setSalary(Double _salary)
    {
        salary=_salary;
    }

    public void setJoinDate(Date _newDate)
    {
        joinDate=_newDate;
    }
    


    @Override
    public String getDescription()
    {
        return "An employee working in some company";
    }

    @Override
    public String toString()
    {
        return "ID["+ID+"]"+"  Name["+super.getName() + " " + super.getSurname() + "]  Salary["+salary+"]  Join Date["+joinDate+"]";
    }

    @Override
    public final boolean equals(Object other)
    {
        if(this==other) return true;
        if(other==null) return false;
        if(this.getClass()!=other.getClass()) return false;

        Employee p = (Employee)other;

        return ID==p.ID;   
    }

    @Override
    public int hashCode()
    {
        return 7* Objects.hash(salary)
            +11*Objects.hash(joinDate)
            +13*Objects.hash(super.getName());
    }
}
