/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DAO;
import fmp.DTO.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
/**
 *
 * @author lildocta
 */
public class TestOrderDAO implements ProdOrderDAOInterface
    {
    ProdStateDAO psDAO;
    ProdProductDAO ppDAO;
    
    
    public TestOrderDAO(ProdStateDAO prodstate, ProdProductDAO prodprod)
        {
        psDAO = prodstate;
        ppDAO = prodprod;
        }
    
    public HashMap<String, List<Order>> ordHash = new HashMap<>();
    
    @Override
    public void writeOrder()
        {
            System.out.println("This is the test version of Flooring Master, orders created here cannot be saved");
        }
    
    public void readOrder(String fileName)
        {
        List<Order> OrdersList = new ArrayList<>();
        List<Order> OrdersList2 = new ArrayList<>();
        
                    String fileDate = "10112016";
                    int orderID = 1;
                    String customer = "Testy";
                    String state = "WI";
                    double tax = 0.05;
                    String material = "Cork";
                    double area = 80;
                    BigDecimal materialCostSqFt = BigDecimal.valueOf(1);
                    BigDecimal laborCostSqFt = BigDecimal.valueOf(1);
                    Order newOrder = new Order(fileDate, orderID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
        
                    OrdersList.add(newOrder);
                    
                    fileDate = "10112016";
                    orderID = 2;
                    customer = "testy2";
                    newOrder = new Order(fileDate, orderID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
                    
                    OrdersList.add(newOrder);
                    
                    fileDate = "09012016";
                    orderID = 1;
                    customer = "BigBoi";
                    newOrder = new Order(fileDate, orderID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
                    
                    OrdersList2.add(newOrder);
                    
                    fileDate = "09012016";
                    orderID = 2;
                    customer = "LilBoi";
                    newOrder = new Order(fileDate, orderID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
                    
                    OrdersList2.add(newOrder);
        
        ordHash.put("10112016", OrdersList);
        ordHash.put("09012016", OrdersList2);
        }
    
    public void addNewOrder(String customer, String state, String material, double area)
        {
            LocalDate newDate = LocalDate.now();
            String month = Integer.toString(newDate.getMonthValue());
            String day = Integer.toString(newDate.getDayOfMonth());
            String year = Integer.toString(newDate.getYear());
            String orderDate = month + day + year;
            
            if(ordHash.containsKey(orderDate))
            {
                int listSize = ordHash.get(orderDate).size();
                int lastID = ordHash.get(orderDate).get(listSize - 1).getOrderID();
                int newID = lastID + 1;
                double tax = psDAO.retrieveTax(state);
                BigDecimal materialCostSqFt = ppDAO.retrieveMaterialCost(material);
                BigDecimal laborCostSqFt = ppDAO.retrieveLaborCost(material);
            
            Order newOrder = new Order(newID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
            List<Order> oldList = ordHash.get(orderDate);
            oldList.add(newOrder);
            ordHash.put(orderDate, oldList);
            } else 
            {
            int newID = 1;
            List<Order> newList = new ArrayList<>();
            double tax = psDAO.retrieveTax(state);
            BigDecimal materialCostSqFt = ppDAO.retrieveMaterialCost(material);
            BigDecimal laborCostSqFt = ppDAO.retrieveLaborCost(material);
            ordHash.put(orderDate, newList);
            
            Order newOrder = new Order(orderDate, newID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
            newList.add(newOrder);
            ordHash.put(orderDate, newList);
            }
            
            
            
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

    @Override
    public void addNewOrder(List<Order> newOrders)
        {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    @Override
    public void readOrders(String filename)
        {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

   
    }
