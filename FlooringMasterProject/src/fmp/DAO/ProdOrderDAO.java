/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DAO;
import fmp.DTO.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
/**
 *
 * @author lildocta
 */
public class ProdOrderDAO implements ProdOrderDAOInterface
    {
    String prodFile;
    String statFile;
    ProdStateDAO psDAO;
    ProdProductDAO ppDAO;
    
    
    public ProdOrderDAO(ProdStateDAO prodstate, ProdProductDAO prodprod, String orderFile)
        {
        readOrders(orderFile);
        psDAO = prodstate;
        ppDAO = prodprod;
        }
    
    public HashMap<String, List<Order>> ordHash = new HashMap<>();
    @Override
    public void writeOrder()
        {
            PrintWriter out = null;
            try {
            
                for(String k : ordHash.keySet())
                {
                    File file2 = new File("Orders_" + k);
                    out = new PrintWriter(new FileWriter(file2 + ".txt", false));
                    
                    List<Order> newList = ordHash.get(k);
                    for (Order o : newList)
                    {
                        out.println(o.toFile(o));
                    }
                    out.flush();
                    
                }
                    out.close();
                    File listFile = new File("OrdersList.txt");
                    out = new PrintWriter(new FileWriter(listFile, false));
                    for(String s : ordHash.keySet())
                    {
                        out.println("Orders_" + s);
                    }
                    out.flush();
                    out.close();
                
        } catch (IOException e) 
            {
                System.out.println("Write failed.");
            } finally {
                if (out != null)
                {
                    out.close();
                }
            }
            
        }
    
    
    @Override
    public void readOrders(String orderFile)
        {
            try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(orderFile)));
            String temp;
            String newFile;
            String[] tempStrings;

            while (sc.hasNextLine()) 
            {
                newFile = sc.nextLine() + ".txt";
                Scanner nsc = new Scanner(new BufferedReader(new FileReader(newFile)));
                List<Order> newOrderList = new ArrayList<>();
                String fileDate = null;
                while (nsc.hasNextLine())
                {
                    
                    
                    //parses line and creates all the object variables
                    fileDate = newFile.substring(7, 15);
                    temp = nsc.nextLine();
                    tempStrings = temp.split("::");
                    int orderID = Integer.parseInt(tempStrings[0]);
                    String customer = tempStrings[1];
                    String state = tempStrings[2];
                    double tax = Double.parseDouble(tempStrings[3]);
                    String material = tempStrings[4];
                    double area = Double.parseDouble(tempStrings[5]);
                    double materialDoub = Double.parseDouble(tempStrings[6]);
                    double laborDoub = Double.parseDouble(tempStrings[7]);
                    BigDecimal materialCostSqFt = BigDecimal.valueOf(materialDoub);
                    BigDecimal laborCostSqFt = BigDecimal.valueOf(laborDoub);
                    //BigDecimal laborCost = BigDecimal.valueOf(Double.parseDouble(tempStrings[8]));
                    //BigDecimal materialCost = BigDecimal.valueOf(Double.parseDouble(tempStrings[9]));
                    //BigDecimal totalTax = BigDecimal.valueOf(Double.parseDouble(tempStrings[10]));
                    //BigDecimal total = BigDecimal.valueOf(Double.parseDouble(tempStrings[11]));
                    
                    Order newOrder = new Order(fileDate, orderID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
                    newOrderList.add(newOrder);
                    
                }
                ordHash.put(fileDate, newOrderList);
            }
            sc.close();
            
        } catch (FileNotFoundException e) {
            
        }
        }
    
    @Override
    public void addNewOrder(List<Order> newOrders) {
        LocalDate local = LocalDate.now();
        String month = Integer.toString(local.getMonthValue());
        String day = Integer.toString(local.getDayOfMonth());
        String year = Integer.toString(local.getYear());
        String orderDate = month + day + year;

        ordHash.put(orderDate, newOrders);
    
        }
    
    @Override
    public List<String> retrieveStates()
        {
        List<String> List = psDAO.states;
        return List;
        }
    
    @Override
    public List<String> retrieveMaterials()
        {
        List<String> List = ppDAO.materialList;
        return List;
        }
    
    @Override
    public Order retrieveOrder(int orderID, String date)
       {
       List<Order> newOrderList = ordHash.get(date);
       Order getOrder = null;
       for (Order o : newOrderList)
       {
           if (o.getOrderID() == orderID)
           {
               getOrder = o;
           }
               
       }
        return getOrder;
       }
    
    @Override
    public Order changeOrder(Order order, String newCustomer, String newState, String newMaterial, double newArea)
        {
        double tax = psDAO.retrieveTax(newState);
        BigDecimal materialCostSqFt = ppDAO.retrieveMaterialCost(newMaterial);
        BigDecimal laborCostSqFt = ppDAO.retrieveLaborCost(newMaterial);
        
        order.setCustomer(newCustomer);
        order.setState(newState, tax);
        order.setMaterial(newMaterial, materialCostSqFt, laborCostSqFt);
        order.setArea(newArea);
        return order;
        }
    
    @Override
    public void removeOrder(int orderID, String date)
        {
        List<Order> newOrderList = ordHash.get(date);
        for (Order o : newOrderList)
        {
           if (o.getOrderID() == orderID)
           {
               ordHash.get(date).remove(o);
               o = null;
               break;
           }
        }
        }
    
     @Override
    public List<Order> createNewOrder(String customer, String state, double area, String material) {
        LocalDate local = LocalDate.now();
        String month = Integer.toString(local.getMonthValue());
        String day = Integer.toString(local.getDayOfMonth());
        String year = Integer.toString(local.getYear());
        String orderDate = month + day + year;

        List<Order> orders = new ArrayList<>();

        if (ordHash.containsKey(orderDate)) {
            int size = ordHash.get(orderDate).size();
            int last = ordHash.get(orderDate).get(size - 1).getOrderID();
            int newID = last + 1;
            double tax = psDAO.retrieveTax(state);
            BigDecimal materialCostSqFt = ppDAO.retrieveMaterialCost(material).setScale(2);
            BigDecimal laborCostSqFt = ppDAO.retrieveLaborCost(material).setScale(2);

            Order newOrder = new Order(newID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
            orders = ordHash.get(orderDate);
            orders.add(newOrder);

        } else {
            int newID = 1;
            double tax = psDAO.retrieveTax(state);
            BigDecimal materialCostSqFt = ppDAO.retrieveMaterialCost(material).setScale(2);
            BigDecimal laborCostSqFt = ppDAO.retrieveLaborCost(material).setScale(2);

            Order newOrder = new Order(newID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
            orders.add(newOrder);
        }
        return orders;
    }
    
     @Override
    public List<String> showAllOrders() {
        List<Order> orderList;
        List<String> orders = new ArrayList<>();
        for (String s : ordHash.keySet()) {
            orderList = ordHash.get(s);
            orderList = orderList
                    .stream()
                    .sorted((o1, o2) -> o1.getDate().compareTo(o2.getDate()))
                    .collect(Collectors.toList());
            for (Order o : orderList) {
                orders.add(o.toString(o));
            }
        }
        return orders;
    }

    @Override
    public List<String> showOrdersByDate(String userDate) {
        List<Order> orderList = new ArrayList<>();
        List<String> orders = new ArrayList<>();

        for (List<Order> l : ordHash.values()) {
            for (Order o : l) {
                orderList.add(o);
            }
        }

        orderList = orderList.stream().filter((a) -> a.getDate().equals(userDate))
                .collect(Collectors.toList());

        orderList.stream().forEach((o) -> {
            orders.add(o.toString(o));
        });
        return orders;
    }

   
    }
