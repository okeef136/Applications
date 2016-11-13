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
public class HoldItem extends PokeItem
    {

    public HoldItem(String name, int cost, int stock, String use)
        {
        super(name, cost, stock, use);
        }

    

    @Override
    public void MultiPurchase(int howMany)
        {
            this.stock -= howMany;
        }

    }
