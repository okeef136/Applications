/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.UI;

import fmp.DAO.ProdOrderDAOInterface;
import fmp.DTO.Order;
import java.util.List;
import fmp.Controller.fmpController;

/**
 *
 * @author lildocta
 */
public class FlooringIO
    {

    fmpCIO cio = new fmpCIO();
    ProdOrderDAOInterface poDAO;
    fmpController cont;

    public FlooringIO(ProdOrderDAOInterface poDAO)
        {
        this.poDAO = poDAO;
        }

    public String newState(Order order)
        {
        List<String> stateList = poDAO.retrieveStates();
        Boolean valid = false;
        String userState = cio.readString("Enter State (" + order.getState() + "): ");
        
        if (userState.equals(""))
        {
            userState = order.getState();
            return userState;
        } else
        {
        while (!valid)
        {
            for (String s : stateList)
            {
                if (s.equalsIgnoreCase(userState))
                {
                    valid = true;
                }
            }

            if (!valid)
            {
                userState = cio.readString("Please enter a state in our coverage area: ");
            }

        }
        }
        return userState;
        }

    public double newArea(Order order)
        {
        String stringArea = cio.readString("Enter new area (" + order.getArea() + "): ");

        if (!stringArea.equals(""))
        {
            double newArea = Double.parseDouble(stringArea);
            while (newArea < 4)
            {
                stringArea = cio.readString("Area must be greater then 4ftsq");
                newArea = Double.parseDouble(stringArea);

            }
            return newArea;
        } else
        {
            double newArea = order.getArea();
            return newArea;
        }
        }

    public String newCustomer(Order order)
        {
        String newName = cio.readString("Enter new name (" + order.getCustomer() + "): ");
        if (newName.equals(""))
        {
            newName = order.getCustomer();
        }
        return newName;
        }

    public String newMaterial(Order order)
        {
        Boolean valid = false;
        List<String> materialList = poDAO.retrieveMaterials();
        String newMaterial = cio.readString("Enter new material (" + order.getMaterial() + "): ");

        if (!newMaterial.equals(""))
        {
            while (!valid)
            {
                for (String m : materialList)
                {
                    if (m.equalsIgnoreCase(newMaterial))
                    {
                        valid = true;
                    }
                }
                if (!valid)
                {
                    newMaterial = cio.readString("Please enter a material in our catalogue: ");
                }
            }
            return newMaterial;
        } else
        {
            newMaterial = order.getMaterial();
            return newMaterial;
        }

        }

    public String newState()
        {
        List<String> stateList = poDAO.retrieveStates();
        Boolean valid = false;
        String userState = cio.readString("Enter State: ");
        while (!valid)
        {
            for (String s : stateList)
            {
                if (s.equalsIgnoreCase(userState))
                {
                    valid = true;
                }
            }

            if (!valid)
            {
                userState = cio.readString("Please enter a state in our coverage area: ");
            }

        }
        return userState;
        }

    public double newArea()
        {
        String stringArea = cio.readString("Enter new area: ");
        double newArea = Double.parseDouble(stringArea);

        while (newArea < 4)
        {
            stringArea = cio.readString("Area must be between 4 and 100\nPlease enter area: ");
            newArea = Double.parseDouble(stringArea);

        }
        return newArea;

        }

    public String newCustomer()
        {
        String newName = cio.readString("Enter new name: ");
        return newName;
        }

    public String newMaterial()
        {
        Boolean valid = false;
        List<String> materialList = poDAO.retrieveMaterials();
        String newMaterial = cio.readString("Enter new material: ");

        while (!valid)
        {
            for (String m : materialList)
            {
                if (m.equalsIgnoreCase(newMaterial))
                {
                    valid = true;
                }
            }
            if (!valid)
            {
                newMaterial = cio.readString("Please enter a material in our catalogue: ");
            }
        }
        return newMaterial;

        }
    
    public String readDate()
        {
        String date = cio.readString("Please enter the order date: ");
        while (date.length() != 8)
        {
            date = cio.readString("Please enter date in the form MMDDYYYY: ");
        }
        return date;
        }



    }
