package excersises.inheritance.Accounts;

public class SavingsAccount extends Account {
    private Double intrestRate;

    public SavingsAccount(int accNum, Double _intrestRate){
        super(accNum);
        intrestRate = _intrestRate;
    }

    public void setIntrestRate(Double _intrestRate)
    {
        intrestRate=_intrestRate;
    }

    public Double getIntrestRate()
    {
        return intrestRate;
    }

    @Override
    public String toString()
    {
        return super.toString()+" intrestRate="+intrestRate;
    }
    
}
