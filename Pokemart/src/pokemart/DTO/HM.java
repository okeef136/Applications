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
public class HM extends PokeItem
    {

    public HM(String name, int cost, int stock, String use)
        {
        super(name, cost, stock, use);
        }

    

    @Override
    public void MultiPurchase(int howMany)
        {
        if (howMany > 1)
        {
            System.out.println("Purchase failed, you can only purchase 1 of each HM");
        } else 
        {
            stock -= 1;
        }
        }
        
    
    }
