/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DTO;

import java.math.BigDecimal;

/**
 *
 * @author lildocta
 */
public class Product
    {
    String material;
    private BigDecimal materialCostSqFt;
    private BigDecimal laborCostSqFt;

    public Product(String material, BigDecimal materialCostSqFt, BigDecimal laborCostSqFt)
        {
        this.material = material;
        this.materialCostSqFt = materialCostSqFt;
        this.laborCostSqFt = laborCostSqFt;
        }

    /**
     * @return the materialCostSqFt
     */
    public BigDecimal getMaterialCostSqFt()
        {
        return materialCostSqFt;
        }

    /**
     * @return the laborCostSqFt
     */
    public BigDecimal getLaborCostSqFt()
        {
        return laborCostSqFt;
        }
    
    public String getMaterial()
        {
        return this.material;
        }

    
    }
