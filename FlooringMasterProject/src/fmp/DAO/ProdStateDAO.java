/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import fmp.DTO.StateTax;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author lildocta
 */
public class ProdStateDAO
    {
    HashMap<String, Double> taxHash = new HashMap<>();
    List<String> states = new ArrayList<>();
    
    public ProdStateDAO(String stateFile)
        {
            readStates(stateFile);
        }
    
    public void readStates(String stateFile)
       {
       try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(stateFile)));
            String temp;
            String[] tempStrings;
            

            while (sc.hasNextLine()) 
            {
                temp = sc.nextLine();
                tempStrings = temp.split("::");
                String state = tempStrings[0];
                double tax = Double.parseDouble(tempStrings[1]);
                StateTax newOrder = new StateTax(state, tax);
                taxHash.put(state, tax);
                states.add(state);
            }
            sc.close();
            
        } catch (FileNotFoundException e) {
            
        }
       }
    
    public double retrieveTax(String state)
        {
        double tax = taxHash.get(state);
        return tax;
        }
    
    
    }
