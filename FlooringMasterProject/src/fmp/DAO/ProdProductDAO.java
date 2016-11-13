/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DAO;

import fmp.DTO.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lildocta
 */
public class ProdProductDAO
    {
    HashMap<String, BigDecimal> materialHash = new HashMap<>();
    HashMap<String, BigDecimal> laborHash = new HashMap<>();
    List<String> materialList = new ArrayList<>();
    
    public ProdProductDAO(String productFile)
        {
            readProducts(productFile);
        }
    
    public BigDecimal retrieveMaterialCost(String material)
        {
        BigDecimal materialCost = materialHash.get(material);
        return materialCost;
        }
    
   public BigDecimal retrieveLaborCost(String material)
        {
        BigDecimal laborCost = laborHash.get(material);
        return laborCost;
        }
   
    public void readProducts(String productFile)
       {
       String[] tempStrings;
       try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(productFile)));
            
            
            while (sc.hasNextLine()) 
            {
                String temp = sc.nextLine();
                tempStrings = temp.split("::");
                String material = tempStrings[0];
                BigDecimal matCost = BigDecimal.valueOf(Double.parseDouble(tempStrings[1]));
                BigDecimal labCost = BigDecimal.valueOf(Double.parseDouble(tempStrings[2]));
                Product newProd = new Product(material, matCost, labCost);
                materialHash.put(material, matCost);
                laborHash.put(material, labCost);
                materialList.add(material);
                
            }
            sc.close();
            
        } catch (FileNotFoundException excep) {
            
        }
       }
    
    
    }
