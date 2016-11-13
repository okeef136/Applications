/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bankaccountmanager;

/**
 *
 * @author lildocta
 */
public class Savings extends Account
    {
    ATMConsoleIO aio = new ATMConsoleIO();
    
    @Override
    protected double withdraw()
        {
            balance = getBalance();
            withdraw = aio.readDouble("How much would you like to withdraw?\n", 0, balance);
            double newBalance = balance - withdraw;
            setBalance(newBalance);
            return withdraw;
        }

    @Override
    protected void deposit()
        {
            balance = getBalance();
            deposit = aio.readDouble("How much would you like to deposit?\n", 0, 100000);
            if (deposit >= 10000)
            {
                aio.write("You have deposited more than $10,000.\n"
                        + "Before you can access these funds our manager must clear the deposit.\n"
                        + "Your funds will be placed in a temporary account");
                
                setTempBalance(deposit);
            } else
            {
            double newBalance = balance + deposit;
            setBalance(newBalance);
            }
        }
    
    }
