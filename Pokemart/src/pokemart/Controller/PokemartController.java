/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemart.Controller;

import pokemart.DAO.PokemartInterface;
import pokemart.UI.PokeCIO;

/**
 *
 * @author lildocta
 */
public class PokemartController

    {

    private final PokeCIO cio = new PokeCIO();
    private final PokemartInterface pokeInt;

    public PokemartController(PokemartInterface textFile)
        {
        pokeInt = textFile;
        }

    public void run() throws InterruptedException
        {
        int again = 1;
        int choice;
        String use;
        int trainerMoney = 0;
        Boolean run = true;
        Boolean firstRun = true;
        
        while(firstRun)
        {
        int trainerChoice = cio.readInteger("A wild meowth appears!\n"
                + "What would you like to do?\n"
                + "1) Fight\n"
                + "2) Throw last pokeball\n"
                + "3) Run\n", 1, 3);
        
        switch(trainerChoice)
        {
            case 1: 
                cio.write("Without any usable pokemon you recklessy charge the meowth!\n"
                        + "Unfortunately you are a mere human and meowth begins relentessly scratching you\n"
                        + "in a blind panic you throw a kick and by pure chance knock the meowth out cold\n"
                        + "you shake the meowth until some PokeCoins fall out and then you scamper away\n");
                trainerMoney = (int)((Math.random() * 2000) + 500);
                firstRun = false;
                break;
            case 2:
                cio.write("You toss your last pokeball with all your strength!\n"
                        + "Unfortunately you've been spending too much time playing pokemon\n"
                        + "and not enough time at the Gym, thus your weak throw is ignored by meowth\n"
                        + "Meowth is so embarassed for you that he tosses a few Pokecoins your way...\n");
                trainerMoney = (int)((Math.random() * 1000) + 500);
                firstRun = false;
                break;
            case 3:
                cio.write("Cowards are never rewarded, I would advise fighting\n");
                break;
                
        }
        
        }
        do
        {
            cio.write("Welcome to the Viridian City Pokemart!\n"
                    + "You have: " + trainerMoney + "\n"
                    + "What would you like to do?\n");
            int userChoice = cio.readInteger("1) Purchase an item\n"
                    + "2) Sell an item\n"
                    + "3) Exit\n"
                    + "> ", 1, 3);

            switch (userChoice)
            {
                case 1:
                    int purchMenu = cio.readInteger("Purchase Menu\n===========\n"
                            + "1) Pokeballs\n"
                            + "2) Consumables\n"
                            + "3) Holdables\n"
                            + "4) Black Market HMs\n"
                            + "5) Exit\n"
                            + "> ", 1, 5);

                    switch (purchMenu)
                    {
                        case 1:
                            use = "Pokeball";
                            Purchase(use);
                            break;
                        case 2:
                            use = "Consumable";
                            Purchase(use);
                            break;
                        case 3:
                            use = "Holdable";
                            Purchase(use);
                            break;
                        case 4:
                            use = "HM";
                            cio.write("Welcome to the underground HM market!\n"
                                    + "the old days of searching for HMs is over,\n"
                                    + "now you can purchase a variety of HMs from us for\n"
                                    + "a very reasonable fee!\n");
                            Purchase(use);
                            break;
                        case 5:
                            again = 1;
                            break;
                    }

                    break;
                case 2:
                    cio.write("This Pokemart DOES NOT accept returns!\n");
                    Thread.sleep(1000);
                    break;
                case 3:
                    cio.write("Thanks for shopping with us!");
                    again = 0;
                    break;
            }

        } while (again == 1);
            

    
    }
    //Method to handle purchasing PokeItems
    public void Purchase(String use)
        {
        String purchChoice;
        int howMany;
        int continuePurch;
        cio.write("Name         Cost   Stock\n==========================\n");
        purchChoice = cio.readString(pokeInt.listItems(use) + "\nEnter the name of the item you would like to purchase\n> ");
        howMany = cio.readInteger("===========\nHow many would you like to purchase?\n> ", 1, 100);
        continuePurch = cio.readInteger("That will cost " + pokeInt.getCost(purchChoice, use, howMany)
                + ".  Is that okay?\n"
                + "1) Yes\n"
                + "2) No\n");
        if (continuePurch == 1)
        {
            if (pokeInt.getStock(purchChoice, use, howMany) == -1)
            {
                cio.write("Purchaes failed, there aren't that many " + purchChoice + "s in stock!\n");
            } else
            {
            pokeInt.setStock(purchChoice, use, howMany);
            }
        }
        }
}
