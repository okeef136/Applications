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
public abstract class Account
    {
        protected double balance;
        public double withdraw;
        public double deposit;
        protected double tempBalance;
                
        protected abstract double withdraw();
        protected abstract void deposit();
        
        protected String dispBalance()
            {
                String stringBalance = "Your balance is " + this.balance;
                return stringBalance;
            }
        
        protected String dispTempBalance()
            {
                String stringTempBalance = "Your temporary balance is " + this.tempBalance;
                return stringTempBalance;
            }
        protected double getBalance()
            {
                return this.balance;
            }
        
        protected double getTempBalance()
            {
                return this.tempBalance;
            }
        
        protected void setBalance(double newBalance)
            {
                this.balance = newBalance;
            }
        
        protected void setTempBalance(double tempAmount)
            {
                this.tempBalance = tempAmount;
            }
        
        
//        public void getWithAmt(double withdraw)
//            {
//                this.withdraw = withdraw;
//            }
//        
//        public void getDepAmt(double deposit)
//            {
//                this.deposit = deposit;
//            }
    }
