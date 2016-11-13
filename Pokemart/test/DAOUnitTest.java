/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pokemart.DAO.PokemartDataAccess.*;
import pokemart.DAO.PokemartInterface;
import pokemart.DTO.PokeItem;

/**
 *
 * @author lildocta
 */
public class DAOUnitTest
    {
    private final PokemartInterface pokeInt;
    
    public DAOUnitTest(PokemartInterface textFile)
        {
        pokeInt = textFile;
        }
    
    @BeforeClass
    public static void setUpClass()
        {
        }
    
    @AfterClass
    public static void tearDownClass()
        {
        }
    
    @Before
    public void setUp()
        {
        }
    
    @After
    public void tearDown()
        {
        }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void listItemsTest() 
         {
            String list = pokeInt.listItems("Pokeball");
            assertEquals(list, "");
         }
    }
