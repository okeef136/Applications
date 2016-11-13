/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author lildocta
 */
public final class Order
    {
    private String date;
    private int orderID;
    private String customer;
    private String state;
    private double tax;
    private String material;
    private double area;
    private BigDecimal materialCostSqFt;
    private BigDecimal laborCostSqFt;
    private BigDecimal laborCost; 
    private BigDecimal materialCost; 
    private BigDecimal totalTax;
    private BigDecimal total;
    

    public Order(int orderID, String customer, String state, double area, String material, double tax, BigDecimal materialCostSqFt, BigDecimal laborCostSqFt)
        {
        
        this.date = setDate();
        this.orderID = orderID;
        this.customer = customer;
        this.material = material;
        this.area = area;
        setMaterial(material, materialCostSqFt, laborCostSqFt);
        setState(state, tax);
        }
    
    public Order(String date, int orderID, String customer, String state, double area, String material, double tax, BigDecimal materialCostSqFt, BigDecimal laborCostSqFt)
        {
        
        this.date = date;
        this.orderID = orderID;
        this.customer = customer;
        this.state = state;
        this.tax = tax;
        this.material = material;
        this.area = area;
        setMaterial(material, materialCostSqFt, laborCostSqFt);
        setState(state, tax);
        
        }
    
    

    /**
     * @return the orderID
     */
    public int getOrderID()
        {
        return orderID;
        }
    
    public String getDate()
        {
        return date;
        }
    
    public String setDate()
        {
        LocalDate ndate = LocalDate.now();
        String month = Integer.toString(ndate.getMonthValue());
        String day = Integer.toString(ndate.getDayOfMonth());
        String year = Integer.toString(ndate.getYear());
        String newDate = month + day + year;
        return newDate;
        }
    /**
     * @return the name
     */
    public String getCustomer()
        {
        return customer;
        }

    /**
     * @param customer the name to set
     */
    public void setCustomer(String customer)
        {
        this.customer = customer;
        }

    /**
     * @return the state
     */
    public String getState()
        {
        return state;
        }

    /**
     * @param state the state to set
     * @param tax the tax to set
     */
    public void setState(String state, double tax)
        {
        this.state = state;
        setTax(tax);
        }

    /**
     * @return the material
     */
    public String getMaterial()
        {
        return material;
        }

    /**
     * @param material the material to set
     * @param materialCostSqFt
     * @param laborCostSqFt
     */
    public void setMaterial(String material, BigDecimal materialCostSqFt, BigDecimal laborCostSqFt)
        {
        this.material = material;
        setMaterialCostSqFt(materialCostSqFt);
        setLaborCostSqFt(laborCostSqFt);
        }

    /**
     * @return the area
     */
    public double getArea()
        {
        return area;
        }
    
    public void setOrderId(int orderID)
        {
        this.orderID = orderID;
        }

    /**
     * @param area the area to set
     */
    public void setArea(double area)
        {
        this.area = area;
        setMaterial(this.material, this.materialCostSqFt, this.laborCostSqFt);
        setState(this.state, this.tax);
        }
    
    public String toString(Order order)
        {
        String month = order.date.substring(0,2);
        String day = order.date.substring(2,4);
        String year = order.date.substring(4,8);
        BigDecimal totalFormat = order.total.setScale(2, RoundingMode.CEILING);
        return  "\n" + "=========================" 
                + "\nDate: " + month + "/" + day + "/" + year
                + "\nName: " + order.customer 
                + "\nState: " + order.state 
                + "\nArea: " + order.area 
                + "\nMaterial: " + order.material 
                + "\nLabor Cost: " + order.laborCost 
                + "\nMaterial Cost: " + order.materialCost 
                + "\nTotal: " + totalFormat
                + "\n=========================\n"; 
        }
    
    public String toFile(Order order)
        {
        return  order.orderID + "::" 
                + order.customer + "::"
                + order.state + "::"
                + order.tax + "::"
                + order.material + "::"
                + order.area + "::"
                + order.materialCostSqFt + "::" 
                + order.laborCostSqFt + "::"
                + order.laborCost + "::"
                + order.materialCost + "::"
                + order.totalTax + "::"
                + order.total;
        }

    /**
     * @param tax the tax to set
     */
    public void setTax(double tax)
        {
        this.tax = tax;
        setTotalTax();
        setTotal();
        }

    /**
     * @param materialCostSqFt the materialCostSqFt to set
     */
    public void setMaterialCostSqFt(BigDecimal materialCostSqFt)
        {
        this.materialCostSqFt = materialCostSqFt;
        setMaterialCost();
        }

    /**
     * @param laborCostSqFt the laborCostSqFt to set
     */
    public void setLaborCostSqFt(BigDecimal laborCostSqFt)
        {
        this.laborCostSqFt = laborCostSqFt;
        setLaborCost();
        setTotalTax();
        setTotal();
        }

    /**
     * @param laborCost the laborCost to set
     */
    public void setLaborCost()
        {
        this.laborCost = laborCostSqFt.multiply(BigDecimal.valueOf(getArea()));
        }

    /**
     * @param materialCost the materialCost to set
     */
    public void setMaterialCost()
        {
        this.materialCost = materialCostSqFt.multiply(BigDecimal.valueOf(getArea()));
        }

    /**
     * @param totalTax the totalTax to set
     */
    public void setTotalTax()
        {
        this.totalTax = laborCost.add(materialCost).multiply(BigDecimal.valueOf(tax)); 
        }

    /**
     * @param total the total to set
     */
    public void setTotal()
        {
        this.total = laborCost.add(materialCost).add(totalTax);
        }

    @Override
    public int hashCode()
        {
        int hash = 5;
        return hash;
        }

    @Override
    public boolean equals(Object obj)
        {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderID != other.orderID)
        {
            return false;
        }
        if (!Objects.equals(this.date, other.date))
        {
            return false;
        }
        return true;
        }
    
    }
