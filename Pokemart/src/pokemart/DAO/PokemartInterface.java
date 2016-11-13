/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemart.DAO;

/**
 *
 * @author lildocta
 */
public interface PokemartInterface
    {

    int getCost(String name, String use, int howMany);

    int getStock(String name, String use, int howMany);

    String listItems(String use);

    void setStock(String name, String use, int howMany);
    
    }
