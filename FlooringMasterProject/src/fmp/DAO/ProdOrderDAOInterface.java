/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DAO;

import fmp.DTO.Order;
import java.util.List;

/**
 *
 * @author lildocta
 */
public interface ProdOrderDAOInterface
    {
    List<Order> createNewOrder(String customer, String state, double area, String material);
    
    void addNewOrder(List<Order> newOrders);

    Order changeOrder(Order order, String newCustomer, String newState, String newMaterial, double newArea);

    void removeOrder(int orderID, String date);
    
    void readOrders(String filename);

    List<String> retrieveMaterials();

    Order retrieveOrder(int orderID, String date);

    List<String> retrieveStates();

    void writeOrder();
    
    List<String> showAllOrders();
    
    List<String> showOrdersByDate(String userDate);
    
    }
