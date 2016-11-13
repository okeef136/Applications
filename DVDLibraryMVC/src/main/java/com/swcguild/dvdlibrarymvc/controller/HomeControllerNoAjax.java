/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DVDLibraryDataAccess;
import com.swcguild.dvdlibrarymvc.dto.DVD;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Devin Hall
 */
@Controller
public class HomeControllerNoAjax
    {

    // Reference to our DAO
    private DVDLibraryDataAccess dao;

    // Use annotation-driven constructor injection to inject a DAO implementation
    // into our controller
    @Inject
    public HomeControllerNoAjax(DVDLibraryDataAccess dao)
        {
        this.dao = dao;

        ArrayList<String> notesArray = new ArrayList<>();
        notesArray.add("Great Movie!");
        notesArray.add("Kid approved");

        ArrayList<String> notesArray2 = new ArrayList<>();
        notesArray2.add("Comic book adaption");
        notesArray2.add("Yellow who comes");
        
        ArrayList<String> notesArray3 = new ArrayList<>();
        notesArray3.add("Stupid Movie!");
        notesArray3.add("Kid approved");

        ArrayList<String> notesArray4 = new ArrayList<>();
        notesArray4.add("Terrible Movie!");
        notesArray4.add("Kid approved");
        
        ArrayList<String> notesArray5 = new ArrayList<>();
        notesArray5.add("The CGI will blow your mind");
        notesArray5.add("10/10 best movie of the year");
        
        ArrayList<String> notesArray6 = new ArrayList<>();
        notesArray6.add("Creeeeepy");
        notesArray6.add("10/10 Jake Gyllenhaal is terrifying");
        
        ArrayList<String> notesArray7 = new ArrayList<>();
        notesArray7.add("Great take on time travel");
        notesArray7.add("The aliens don't use time travel very well, pretty dissapointed in them");
        //then add known data into the DAO and our local copy

        dao.addDVD(new DVD("David Yates", "Harry Potter and the Deathly Hallows", "PG-13", 2011, notesArray4));
        dao.addDVD(new DVD("Chris Columbus", "Harry Potter and the Sorcerers Stone", "PG", 2000, notesArray3));
        dao.addDVD(new DVD("Some Dude", "Sin City", "R", 2003, notesArray2));
        dao.addDVD(new DVD("Christopher Nolan", "Interstellar", "PG-13", 2014, notesArray5));
        
        dao.addDVD(new DVD("Dan Gilroy", "Nightcrawler", "R", 2014, notesArray6));
        dao.addDVD(new DVD("Doug Liman", "Edge of Tomorrow", "PG-13", 2014, notesArray7));

        }

    @RequestMapping(value = "/displayDVDLibraryNoAjax", method = RequestMethod.GET)
    public String displayDVDLibraryNoAjax(Model model)
        {
        // Get the list of all Contacts
        List<DVD> aList = dao.getDVDs();
        // Put the list of all Contacts on the Model so Spring MVC can pass it
        // along to the view
        model.addAttribute("dvdLibrary", aList);
        // Return the logical view name
        return "displayDVDLibraryNoAjax";
        }

    @RequestMapping(value = "displayNewDVDFormNoAjax", method = RequestMethod.GET)
    public String displayNewDVDFormNoAjax()
        {
        return "newDVDFormNoAjax";
        }

    // This endpoint gets the submitted form data from the HttpServletRequest,
    // creates a new Contact object, sets the fields on the new Contact
    // object appropriately, add the Contact to the DAO, and then redirects
    // to the displayContactListNoAjax controller endpoint.
    @RequestMapping(value = "/addNewDVDNoAjax", method = RequestMethod.POST)
    public String addNewDVDNoAjax(HttpServletRequest req)
        {
        // Get all of the form data from the request
        String director = req.getParameter("director");
        String title = req.getParameter("title");
        String rating = req.getParameter("rating");
        String year = req.getParameter("year");
        String note = req.getParameter("note");

        // Create a new Contact and set all the fields
        DVD dvd = new DVD();
        dvd.setDirector(director);
        dvd.setTitle(title);
        dvd.setRating(rating);
        dvd.setYear(Integer.parseInt(year));
        ArrayList<String> notez = dvd.getNotes();
        notez.add(note);
        dvd.setNotes(notez);
        // Add the Contact to the DAO
        dao.addDVD(dvd);

        // Redirect to the displayContactListNoAjax controller endpoint - we must
        // use the redirect: here so that Spring MVC routes us to the controller
        // endpoint and not directly to a JSP.
        return "redirect:displayDVDLibraryNoAjax";
        }

    @RequestMapping(value = "/deleteDVDNoAjax", method = RequestMethod.GET)
    public String deleteDVDNoAjax(HttpServletRequest req)
        {
        // Get the id of the contact to be deleted from the HttpServletRequest
        int DVDId = Integer.parseInt(req.getParameter("DVDId")); // Ask DAO to delete the given contact

        dao.removeDVD(DVDId);
// Redirect to the displayContactListNoAjax controller endpoint - we must // use the redirect: here so that Spring MVC routes us to the controller // endpoint.
        return "redirect:displayDVDLibraryNoAjax";
        }

    @RequestMapping(value = "/displayEditDVDFormNoAjax", method = RequestMethod.GET)
    public String displayEditDVDFormNoAjax(HttpServletRequest req, Model model)
        {
        // Get the id of the Contact to be edited
        Integer DVDId = Integer.parseInt(req.getParameter("DVDId")); // Get the Contact from the DAO
        String note = req.getParameter("note");

        DVD dvd = dao.getDVDById(DVDId);
        ArrayList<String> newList = new ArrayList<>();
        newList = dvd.getNotes();
        dvd.setNotes(newList);
        dvd.setNote(note);
// Put the Contact on the Model
        model.addAttribute("dvd", dvd);

        return "editDVDFormNoAjax";
        }

    // This method uses the @ModelAttribute annotation to tell Spring to
    // convert the submitted form data into a Contact object. The method 
    // then hands the Contact to the DAO for update, and finally redirects to
    // the displayContactListNoAjax controller endpoint. 
    @RequestMapping(value = "/editDVDNoAjax", method = RequestMethod.POST)
    public String editDVDNoAjax(@Valid @ModelAttribute("dvd") DVD dvd, BindingResult result)
        {
        if (result.hasErrors())
        {
            return "editDVDFormNoAjax";
        }
        boolean delete = false;
        ArrayList<String> toSave = new ArrayList<>();
        for (String s : dvd.getNotes())
        {
            if (s.equals(""))
            {
                delete = true;
            } else
            {
                toSave.add(s);
            }

        }
        if (delete)
        {
            dvd.setNotes(toSave);
        }
        dao.updateDVD(dvd);

        return "redirect:displayDVDLibraryNoAjax";
        }
    }
