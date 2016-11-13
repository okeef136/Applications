/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemart.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import pokemart.DTO.Consumable;
import pokemart.DTO.HM;
import pokemart.DTO.HoldItem;
import pokemart.DTO.PokeBall;
import pokemart.DTO.PokeItem;

/**
 *
 * @author lildocta
 */
public class PokemartDataAccess implements PokemartInterface
    {

    private String file;

    public PokemartDataAccess(String filename)
        {
        readItems(filename);
        file = filename;
        }
    
    HashMap<String, Collection<PokeItem>> pokeItems = new HashMap<>();
    Collection<PokeItem> consume  = new ArrayList<>();
    Collection<PokeItem> hms = new ArrayList<>();
    Collection<PokeItem> hold = new ArrayList<>();
    Collection<PokeItem> balls = new ArrayList<>();

    private void writeConsumable(Collection<PokeItem> poke)
        {
        try
        {
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            for (PokeItem c : poke)
            {
                pw.println(c.getName() + "::" + c.getCost() + "::"
                        + c.getStock() + "::" + c.getUse());
            }
            out.flush();
            out.close();
        } catch (IOException e)
        {
            System.out.println("Write Failed");
        }
        }

    private void readItems(String file)
        {
        try
        {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(file)));
            String temp;
            String[] tempStrings;
            int intTemp1;
            int intTemp2;

            while (sc.hasNextLine())
            {
                temp = sc.nextLine();
                tempStrings = temp.split("::");
                intTemp1 = Integer.valueOf(tempStrings[1]);
                intTemp2 = Integer.valueOf(tempStrings[2]);

                //
                switch (tempStrings[3])
                {
                    case "Pokeball":
                        balls.add(new PokeBall(tempStrings[0], intTemp1, intTemp2, tempStrings[3]));
                        break;
                    case "Consumable":
                        consume.add(new Consumable(tempStrings[0], intTemp1, intTemp2, tempStrings[3]));
                        break;
                    case "HM":
                        hms.add(new HM(tempStrings[0], intTemp1, intTemp2, tempStrings[3]));
                        break;
                    case "Holdable":
                        hold.add(new HoldItem(tempStrings[0], intTemp1, intTemp2, tempStrings[3]));
                        break;
                }
                //adds collection of each object to pokeItems HashMap
                pokeItems.put("Pokeball", balls);
                pokeItems.put("Consumable", consume);
                pokeItems.put("HM", hms);
                pokeItems.put("Holdable", hold);

            }
            sc.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("Error Occurred" + e.getMessage());
        }
        }

    @Override
    public String listItems(String use)
        {
        
        String list = "";
        String name;
        String cost;
        int numSpace;
        for (PokeItem p : pokeItems.get(use))
        {
            name = p.getName();
            cost = p.getCost() + "";
            name = String.format("%-11s", name);
            cost = String.format("%-8s", cost);
            list = name + "  " + cost + "  " + p.getStock() + "\n" + list;
        }
        return list;
        }

    @Override
    public void setStock(String name, String use, int howMany)
        {
        
        for (PokeItem p : pokeItems.get(use))
            {
                if (p.getName().equals(name))
                {
                    if (p.getStock() < howMany)
                    {
                        howMany = 0;
                    }
                    p.MultiPurchase(howMany);
                } 
            }
                
        }
    
    @Override
    public int getStock(String name, String use, int howMany)
        {
        int stock = 0;
        for(PokeItem p : pokeItems.get(use))
            if (p.getName().equals(name))
            {
                stock = p.getStock();
                if (stock < howMany)
                {
                    stock = -1;
                }
            } 
        return stock;
        }
    
    @Override
    public int getCost(String name, String use, int howMany)
        {
        int cost = 0;
        for(PokeItem p : pokeItems.get(use))
            if (p.getName().equals(name))
            {
                cost = howMany * p.getCost();
            } 
        return cost;
        }

    }
