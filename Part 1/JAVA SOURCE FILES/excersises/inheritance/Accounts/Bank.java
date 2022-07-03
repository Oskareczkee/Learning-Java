package excersises.inheritance.Accounts;

import java.util.*;

public class Bank 
{
    //bank test
    public static void main(String[] args) throws Exception {
        Bank bank = new Bank();

        var acc1= new SavingsAccount(1, 0.1);
        var acc2= new CurrentAccount(2, 0.0);
        var acc3= new Account(3);

        acc1.deposit(10.0);
        acc2.deposit(20.0);
        acc3.deposit(30.0);

        acc2.addOverdraft(500.0);

        bank.addAccount(acc1);
        bank.addAccount(acc2);
        bank.addAccount(acc3);

        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(acc3);

        bank.update(0.2);
        bank.payDivident(1);

        System.out.println(acc1);
        System.out.println(acc2);
        System.out.println(acc3);

    }
        

    private List<Account> accounts = new ArrayList<>();
    public Bank(){}

    public void addAccount(Account account)
    {
        accounts.add(account);
    }

    public void closeAccount(int accNum)
    {
        if(!accounts.removeIf(x->x.getAccountNumber().equals(accNum)))
            System.out.printf("Account[%d] could not be found", accNum);  
    }

    public void update(Double newIntrestRate)
    {
        for (Account account : accounts) {
            if(account instanceof SavingsAccount)
            {
                SavingsAccount acc = (SavingsAccount)account;
                acc.setIntrestRate(newIntrestRate);
                continue;
            }
            else if(account instanceof CurrentAccount)
            {
                CurrentAccount acc = (CurrentAccount) account;
                if(acc.getOverdraft()>0)
                    System.out.println(acc + " is in overdraft");
            }
        }
    }

    private Account getAccount(int accNum)
    {
        return accounts.stream().filter(x-> x.getAccountNumber().equals(accNum)).findFirst().orElse(null);
    }

    private Double CalculateDivident(Double balance, Double intrestRate)
    {
        return balance*intrestRate;
    }

    public void payDivident(int accNum)
    {
        Account acc = getAccount(accNum);

        if(!(acc instanceof SavingsAccount))
        {
            System.out.println("Account does not exist or is not a saving account");
            return;
        }

        SavingsAccount sacc = (SavingsAccount)acc;
        sacc.deposit(CalculateDivident(sacc.getBalance(), sacc.getIntrestRate()));
    }

    
}
