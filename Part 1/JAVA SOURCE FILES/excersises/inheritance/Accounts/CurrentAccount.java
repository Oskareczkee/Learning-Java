package excersises.inheritance.Accounts;

public class CurrentAccount extends Account {
    private Double overdraft;

    public CurrentAccount(int accNum, Double _overdraft)
    {
        super(accNum);
        overdraft=_overdraft;
    }

    public Double getOverdraft()
    {
        return overdraft;
    }

    public void setOverdraft(Double ovd)
    {
        overdraft=ovd;
    }

    public void addOverdraft(Double val)
    {
        if(val<0)
        {
            System.err.println("CurrentAccount.addOverdraft(...): "
            +"value cannot be negative.");
            return;
        }

        overdraft+=val;
    }

    public void payOverdraft(Double val)
    {
        if(val<0)
        {
            System.err.println("CurrentAccount.payOverdraft(...): "
            +"overdraft cannot be negative value.");
            return;
        }
        else if(val> overdraft)
        {
            System.err.println("CurrentAccount.payOverdraft(...): "
            +"value to pay cannot be greater than overdraft.");
            return;
        }
        overdraft-=val;
    }

    @Override
    public String toString()
    {
        return super.toString()+" overdraft="+overdraft;
    }
}
