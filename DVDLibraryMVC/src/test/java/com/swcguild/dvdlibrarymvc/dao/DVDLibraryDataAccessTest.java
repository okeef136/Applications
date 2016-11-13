/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.dto.DVD;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author lildocta
 */
public class DVDLibraryDataAccessTest
    {

    private DVDLibraryDataAccess dao;

    public DVDLibraryDataAccessTest()
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
        dao = ctx.getBean("DVDLibraryDataAccess", DVDLibraryDataAccess.class);
        }

    @After
    public void tearDown()
        {
        }

    @Test
    public void addGetDeleteDVD()
        {
        // Create new contact
        DVD nc = new DVD();
        nc.setDirector("JK Rowling");
        nc.setTitle("Harry Potter Chamber");
        nc.setRating("PG 13");
        nc.setYear(2002);
        ArrayList<String> notesArray = new ArrayList<>();
        notesArray.add("Great Movie!");
        notesArray.add("Kid approved");
        nc.setNotes(notesArray);
        dao.addDVD(nc);
        DVD fromDb = dao.getDVDById(nc.getDvdId());
        assertEquals(fromDb, nc);
        dao.removeDVD(nc.getDvdId());
        assertNull(dao.getDVDById(nc.getDvdId()));
        }

    @Test
    public void addUpdateDVD()
        {
        DVD nc = new DVD();
        nc.setDirector("JK Rowling");
        nc.setTitle("Harry Potter Chamber");
        nc.setRating("PG 13");
        nc.setYear(2002);
        ArrayList<String> notesArray = new ArrayList<>();
        notesArray.add("Great Movie!");
        notesArray.add("Kid approved");
        nc.setNotes(notesArray);
        dao.addDVD(nc);
        nc.setYear(2003);
        dao.updateDVD(nc);
        DVD fromDb = dao.getDVDById(nc.getDvdId());
        assertEquals(fromDb, nc);
        }

    @Test
    public void getAllContacts()
        {
        // Create new contact
        DVD nc = new DVD();
        nc.setDirector("JK Rowling");
        nc.setTitle("Harry Potter Chamber");
        nc.setRating("PG 13");
        nc.setYear(2002);
        ArrayList<String> notesArray = new ArrayList<>();
        notesArray.add("Great Movie!");
        notesArray.add("Kid approved");
        nc.setNotes(notesArray);
        dao.addDVD(nc);
        // Create new contact
        DVD nc2 = new DVD();
        nc2.setDirector("JK Rowling");
        nc2.setTitle("Harry Potter Sorceres Stone");
        nc2.setRating("PG");
        nc2.setYear(2002);
        ArrayList<String> notesArray2 = new ArrayList<>();
        notesArray.add("Pretty Good");
        notesArray.add("Baby Approved");
        nc2.setNotes(notesArray);
        dao.addDVD(nc2);
        List<DVD> cList = dao.getDVDs();
        assertEquals(cList.size(), 2);
        }

    
    }
