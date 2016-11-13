/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.Controller;

import fmp.DAO.ProdOrderDAOInterface;
import fmp.DTO.Order;
import fmp.UI.FlooringIO;
import fmp.UI.fmpCIO;
import java.util.List;

/**
 *
 * @author lildocta
 */
public class fmpController
    {

    public ProdOrderDAOInterface poDAO;
    fmpCIO cio = new fmpCIO();
    FlooringIO fio = null;

    public fmpController(ProdOrderDAOInterface textFile)
        {
        poDAO = textFile;
        fio = new FlooringIO(textFile);
        }

    public void run() throws InterruptedException
        {

        int choice;
        cio.write("=======================\n"
                + "Welcome to FloorMaster!\n"
                + "=======================\n");

        do
        {
            choice = cio.readInteger("FloorMaster Menu\n"
                    + "=================\n"
                    + "1) Add Order\n"
                    + "2) Edit Order\n"
                    + "3) Remove Order\n"
                    + "4) Save Orders\n"
                    + "5) View Orders\n"
                    + "6) Exit\n"
                    + "> ", 1, 5);

            switch (choice)
            {
                case 1:
                    newOrder();
                    break;
                case 2:
                    editOrder();
                    break;
                case 3:
                    removeOrder();
                    break;
                case 4:
                    saveOrders();
                    break;
                case 5:
                    showOrders();
                    run();
                    break;
                case 6:
                    cio.write("=============================\n"
                            + "Thanks for using FloorMaster!\n"
                            + "=============================\n");
                    break;

            }

        } while (choice != 6);
        }

     public void newOrder() throws InterruptedException {
        String name = cio.readString("\nAdd an order:"
                + "\n\nPlease enter customer name: ");
        String state = fio.newState();
        double area = fio.newArea();
        String material = fio.newMaterial();
        poDAO.createNewOrder(name, state, area, material)
                .stream()
                .forEach(o -> cio.write(o.toString(o)));
        String add = cio.readString("\nAdd this order (y/n)?\n");
        if (add.equalsIgnoreCase("y")) {
            poDAO.addNewOrder(poDAO.createNewOrder(name, state, area, material));
        } else {
            run();
        }
     }

    public Order getOrder(int orderID, String date)
        {
        Order newOrder = poDAO.retrieveOrder(orderID, date);
        return newOrder;
        }

    public void editOrder() throws InterruptedException
        {
        Order order = searchValidate();
        int orderID = order.getOrderID();
        String date = order.setDate();
        cio.write(order.toString(order));
        Order order2 = getOrder(orderID, date);

        String newCustomer = fio.newCustomer(order2);
        String newState = fio.newState(order2);
        String newMaterial = fio.newMaterial(order2);
        double newArea = fio.newArea(order2);
        poDAO.changeOrder(order2, newCustomer, newState, newMaterial, newArea);
        cio.write(order.toString(order2));
        Thread.sleep(1000);
        }

    public void removeOrder()
        {
        Order order = searchValidate();
        int orderID = order.getOrderID();
        String date = order.getDate();
        poDAO.removeOrder(orderID, date);
        }

    public void saveOrders()
        {
        poDAO.writeOrder();
        }

    public Order searchValidate()
        {
        Order order2 = null;
        int orderID = cio.readInteger("Enter Order Id: ");
        String date = fio.readDate();
        try
        {
            order2 = getOrder(orderID, date);
        } catch (NullPointerException e)
        {
            cio.write("That did not access a valid order, please try again\n");
            searchValidate();
        }
        if (order2 == null)
        {
            cio.write("That did not access a valid order, please try again\n");
            searchValidate();
        }
        return order2;
        }

    public void showOrders() throws InterruptedException
        {
        int show = cio.readInteger("\n1.)Show All orders"
                + "\n2.)Search order by date"
                + "\n3.)Back to main menu", 1, 3);
        switch (show)
        {
            case 1:
                List<String> orders;
                orders = poDAO.showAllOrders();
                orders.stream().forEach(s -> 
                        {
                            cio.write(s);
                });
                break;
            case 2:
                String userDate = fio.readDate();
                List<String> dateOrders;
                dateOrders = poDAO.showOrdersByDate(userDate);
                dateOrders.stream().forEach(s -> 
                        {
                            cio.write(s);
                });
                break;
            case 3:
                run();
                break;
        }
        }
    }
