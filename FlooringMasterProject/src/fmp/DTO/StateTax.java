/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DTO;

import java.util.Map;

/**
 *
 * @author lildocta
 */
public class StateTax
    {
    String state;
    double tax;

    public StateTax(String state, double tax)
        {
        this.state = state;
        this.tax = tax;
        }
    

    
    public double getTax()
        {
        return this.tax;
        }
    
    public String getState()
        {
        return this.state;
        }
    }

