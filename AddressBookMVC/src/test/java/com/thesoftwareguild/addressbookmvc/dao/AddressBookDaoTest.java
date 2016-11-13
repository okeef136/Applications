/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookmvc.dao;

import com.thesoftwareguild.addressbookmvc.dto.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AddressBookDaoTest
    {

    private AddressBookDao dao;

    public AddressBookDaoTest()
        {
        }

    @BeforeClass
    public static void setUpClass()
        {
        }

    @AfterClass
    public static void tearDownClass()
        {
        }

    @Before
    public void setUp()
        {
        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("addressBookDao", AddressBookDao.class);
        }

    @After
    public void tearDown()
        {
        }

    @Test
    public void addGetDeleteAddress()
        {
        // Create new contact
        Address nc = new Address();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setState("MN");
        nc.setCity("Saint Paul");
        nc.setStreetName("1234 Fake Street");
        nc.setZipcode("55105");
        dao.addAddress(nc);
        Address fromDb = dao.getAddressById(nc.getAddressId());
        assertEquals(fromDb, nc);
        dao.removeAddress(nc.getAddressId());
        assertNull(dao.getAddressById(nc.getAddressId()));
        }

    @Test
    public void addUpdateAddress()
        {
        // Create new contact
        Address nc = new Address();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setState("MN");
        nc.setCity("Saint Paul");
        nc.setStreetName("1234 Fake Street");
        nc.setZipcode("55105");
        dao.addAddress(nc);
        dao.updateAddress(nc);
        Address fromDb = dao.getAddressById(nc.getAddressId());
        assertEquals(fromDb, nc);
        }

    @Test
    public void getAllContacts()
        {
        // Create new contact
        Address nc = new Address();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setState("MN");
        nc.setCity("Saint Paul");
        nc.setStreetName("1234 Fake Street");
        nc.setZipcode("55105");
        dao.addAddress(nc);
        // Create new contact
        Address nc2 = new Address();
        nc2.setFirstName("Steve");
        nc2.setLastName("Doe");
        nc2.setState("MN");
        nc2.setCity("Minneapolos");
        nc2.setStreetName("12345 Dumb Street");
        nc2.setZipcode("55414");
        dao.addAddress(nc2);
        List<Address> cList = dao.getAllAddresses();
        assertEquals(cList.size(), 2);
        }

    @Test
    public void searchAddresses()
        {
        // Create new contact
        Address nc = new Address();
        nc.setFirstName("John");
        nc.setLastName("Doe");
        nc.setState("MN");
        nc.setCity("Saint Paul");
        nc.setStreetName("1234 Fake Street");
        nc.setZipcode("55105");
        dao.addAddress(nc);
        // Create new contact
        Address nc2 = new Address();
        nc2.setFirstName("Steve");
        nc2.setLastName("Dumb");
        nc2.setState("MN");
        nc2.setCity("Minneapolis");
        nc2.setStreetName("12345 Dumb Street");
        nc2.setZipcode("55414");
        dao.addAddress(nc2);
        // Create new contact - same last name as first contact but different // company
        
        // Create search criteria
        Map<SearchTerm, String> criteria = new HashMap<>();
        criteria.put(SearchTerm.LAST_NAME, "Doe");
        List<Address> cList = dao.searchAddresses(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc, cList.get(0));
        // New search criteria - look for Smith
        criteria.put(SearchTerm.LAST_NAME, "Dumb");
        cList = dao.searchAddresses(criteria);
        assertEquals(1, cList.size());
        
        // Change company to Microsoft, should get back nc3
        criteria.put(SearchTerm.CITY, "Minneapolis");
        cList = dao.searchAddresses(criteria);
        assertEquals(1, cList.size());
        assertEquals(nc2, cList.get(0));
        // Change company to Apple, should get back nothing
        criteria.put(SearchTerm.CITY, "Saint Paul");
        cList = dao.searchAddresses(criteria);
        assertEquals(0, cList.size());
        }
    }
