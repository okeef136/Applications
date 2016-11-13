/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokemart.DTO;

/**
 *
 * @author lildocta
 */
public abstract class PokeItem
    {
        protected int stock;
        protected String name;
        protected int cost;
        protected String use;

    public PokeItem(String name, int cost, int stock, String use)
        {
        this.stock = stock;
        this.name = name;
        this.cost = cost;
        this.use = use;
        }
        
    public abstract void MultiPurchase(int howMany);

    /**
     * @return the stock
     */
    public int getStock()
        {
        return stock;
        }

    /**
     * @return the name
     */
    public String getName()
        {
        return name;
        }

    /**
     * @return the cost
     */
    public int getCost()
        {
        return cost;
        }

    public String getUse()
        {
        return use;
        }
    }
