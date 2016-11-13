/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.DAO;

import fmp.DTO.Order;
import java.io.BufferedReader;
import java.io.File;
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
public class ProdOrderDAODylan implements ProdOrderDAOInterface {

    ProdStateDAO psdao;
    ProdProductDAO ppdao;
    HashMap<String, List<Order>> ordHash = new HashMap<>();

    public ProdOrderDAODylan(ProdStateDAO statedao, ProdProductDAO proddao, String filename) {
        readOrders(filename);
        psdao = statedao;
        ppdao = proddao;
    }
    
    @Override
    public void readOrders(String filename) {
        try {
            Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));
            while (sc.hasNextLine()) {
                String[] current;
                String currentLine = sc.nextLine();
                current = currentLine.split("_");
                String date = current[1];
                Scanner sc2 = new Scanner(new BufferedReader(new FileReader(currentLine + ".txt")));
                List<Order> orders = new ArrayList();
                while (sc2.hasNextLine()) {
                    String[] current2;
                    String currentLine2 = sc2.nextLine();
                    current2 = currentLine2.split("::");
                    int id = Integer.parseInt(current2[0]);
                    String name = current2[1];
                    String state = current2[2];
                    double tax = Double.parseDouble(current2[3]);
                    String material = current2[4];
                    double area = Double.parseDouble(current2[5]);
                    BigDecimal materialcost = BigDecimal.valueOf(Double.parseDouble(current2[6])).setScale(2);
                    BigDecimal laborCost = BigDecimal.valueOf(Double.parseDouble(current2[7])).setScale(2);

                    Order readOrder = new Order(date, id, name, state, tax, material, area, materialcost, laborCost);
                    orders.add(readOrder);

                }
                ordHash.put(date, orders);
            }

        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @Override
    public void writeOrder() {
        PrintWriter out = null;
        try {
            for (String k : ordHash.keySet()) {
                String filename = ("Orders_" + k);
                File file = new File(filename);
                List<Order> newList = ordHash.get(k);
                out = new PrintWriter(new FileWriter(file + ".txt", false));
                for (Order o : newList) {
                    out.println(o.toFile(o));

                }
                out.flush();
                out.close();
            }
            File listFile = new File("ordersList.txt");
            out = new PrintWriter(new FileWriter(listFile), false);
            for (String s : ordHash.keySet()) {
                out.println("Orders_" + s);
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println("WRITE FAILED");
        } finally {
            if (out != null) {
                out.close();
            }
        }
        System.out.println("\nOrders Saved.");
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
            double tax = psdao.retrieveTax(state);
            BigDecimal materialCostSqFt = ppdao.retrieveMaterialCost(material).setScale(2);
            BigDecimal laborCostSqFt = ppdao.retrieveLaborCost(material).setScale(2);

            Order newOrder = new Order(newID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
            orders = ordHash.get(orderDate);
            orders.add(newOrder);

        } else {
            int newID = 1;
            double tax = psdao.retrieveTax(state);
            BigDecimal materialCostSqFt = ppdao.retrieveMaterialCost(material).setScale(2);
            BigDecimal laborCostSqFt = ppdao.retrieveLaborCost(material).setScale(2);

            Order newOrder = new Order(newID, customer, state, area, material, tax, materialCostSqFt, laborCostSqFt);
            orders.add(newOrder);
        }
        return orders;
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
    public Order retrieveOrder(int orderID, String date) {
        Order getOrder = null;
        List<Order> newOrderList = ordHash.get(date);
        for (Order o : newOrderList) {
            if (o.getOrderID() == orderID) {
                getOrder = o;
            }
        }
        return getOrder;
    }

    @Override
    public Order changeOrder(Order getOrder, String name, String state, String material, double area) {
        double tax = psdao.retrieveTax(state);
        BigDecimal materialCostSqFt = ppdao.retrieveMaterialCost(material).setScale(2);
        BigDecimal laborCostSqFt = ppdao.retrieveLaborCost(material).setScale(2);

        getOrder.setCustomer(name);
        getOrder.setState(state, tax);
        getOrder.setArea(area);
        getOrder.setMaterial(material, materialCostSqFt, laborCostSqFt);
        return getOrder;
    }

    @Override
    public void removeOrder(int orderID, String date) {
        List<Order> newOrderList = ordHash.get(date);
        for (Order o : newOrderList) {
            if (o.getOrderID() == orderID) {
                ordHash.get(date).remove(o);
                break;
            }
        }
    }

    @Override
    public List<String> retrieveMaterials() {
        List<String> list = ppdao.materialList;
        return list;
    }

    @Override
    public List<String> retrieveStates() {
        List<String> states = psdao.states;
        return states;
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
