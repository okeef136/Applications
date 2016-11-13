/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemart.main;
import pokemart.Controller.PokemartController;
import pokemart.DAO.PokemartDataAccess;
/**
 *
 * @author lildocta
 */
public class PokemartMain
    {
    public static void main(String[] args) throws InterruptedException 
        {
            new PokemartController(new PokemartDataAccess("Pokeitems.txt")).run();
        }
        
    }
