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
public class ATMMachine
    {

    public static void main(String[] args)
        {
        ATMConsoleIO cio = new ATMConsoleIO();
        Checking check = new Checking();
        Savings save = new Savings();
        int userDec;
        int userPin = 12345;

        int enteredPin = cio.readInteger("Please enter your PIN\n> ", 12345, 12345);
        if (enteredPin == userPin)
        {
            do
            {
                {
                    int userChoice = cio.readInteger("Welcome! Please select which account you would like to access\n"
                            + "1)Checking Account\n"
                            + "2)Saving Account\n"
                            + "3)Exit\n"
                            + "> ", 1, 3);
                    if (userChoice == 1)
                    {
                        do
                        {
                        int userCheck = cio.readInteger("CHECKING ACCOUNT\n=========\nPlease pick from the choices below\n"
                                + "1) Withdraw\n"
                                + "2) Deposit\n"
                                + "3) Check Balance\n"
                                + "4) Check Temporary Authorizations\n"
                                + "5) Exit\n"
                                + "> ", 1, 5);

                        switch (userCheck)
                        {
                            case 1:
                                check.withdraw();
                                break;
                            case 2:
                                check.deposit();
                                break;
                            case 3:
                                cio.write(check.dispBalance());
                                break;
                            case 4:
                                cio.write(check.dispTempBalance());
                                break;
                            case 5: 
                                System.exit(0);
                        }
                        userDec = cio.readInteger("\nWould you like to continue using your Checking Account?\n1) yes\n2) no\n", 1, 2);
                        } while (userDec == 1);
                        
                    } else if (userChoice == 2)
                    {
                        do
                        {
                        int userCheck = cio.readInteger("SAVINGS ACCOUNT\n===============\nPlease pick from the choices below\n"
                                + "1) Withdraw\n"
                                + "2) Deposit\n"
                                + "3) Check Balance\n"
                                + "4) Exit\n"
                                + "> ", 1, 4);

                        switch (userCheck)
                        {
                            case 1:
                                save.withdraw();
                                break;
                            case 2:
                                save.deposit();
                                break;
                            case 3:
                                cio.write(save.dispBalance());
                                break;
                            case 4:
                                System.exit(0);
                                break;
                        } 
                        userDec = cio.readInteger("\nWould you like to continue accessing your Savings Account?\n1) yes\n2) no\n", 1, 2);
                        }while (userDec == 1);
                    }

                }
                userDec = cio.readInteger("\nWould you like to continue using the ATM?\n1) yes\n2) no\n", 1, 2);
            } while (userDec == 1);
        
        }
        }
    }
