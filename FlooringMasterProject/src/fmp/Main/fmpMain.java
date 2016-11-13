/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fmp.Main;
import fmp.Controller.fmpController;
import fmp.DAO.ProdOrderDAO;
import fmp.DAO.ProdProductDAO;
import fmp.DAO.ProdStateDAO;
import fmp.DAO.TestOrderDAO;
import java.util.Scanner;
/**
 *
 * @author lildocta
 */
public class fmpMain
    {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException 
        {
        
                new fmpController(new ProdOrderDAO(new ProdStateDAO("stateTax.txt"), new ProdProductDAO("product.txt"), "OrdersList.txt")).run();
//            
//                new fmpController(new TestOrderDAO(new ProdStateDAO(), new ProdProductDAO())).run();
//            
        
        }
    }
