/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.controller;

import com.swcguild.dvdlibrarymvc.dao.DVDLibraryDataAccess;
import com.swcguild.dvdlibrarymvc.dto.DVD;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Devin Hall
 */
@Controller
public class HomeController {

    private DVDLibraryDataAccess dao;

    @Inject
    public HomeController(DVDLibraryDataAccess dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }

    
    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.GET)
    @ResponseBody
    public DVD getDVD(@PathVariable("id") int id) {
        // Retrieve the Contact associated with the given id and return it
        return dao.getDVDById(id);
    }

    @RequestMapping(value = "/dvd", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DVD createDVD(@Valid @RequestBody DVD dvd) {
        // persist the incoming contact
        dao.addDVD(dvd);
        // The addContact call to the dao assigned a contactId to the incoming
        // Contact and set that value on the object. Now we return the updated
        // object to the caller.
        return dvd;
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDVD(@PathVariable("id") int id) {
        // remove the Contact associated with the given id from the data layer
       
        dao.removeDVD(id);
    }

    @RequestMapping(value = "/dvd/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putDVD(@PathVariable("id") int id, @RequestBody DVD dvd) {
        // set the value of the PathVariable id on the incoming Contact object
        // to ensure that a) the contact id is set on the object and b) that
        // the value of the PathVariable id and the Contact object id are the
        // same.
        dvd.setDvdId(id);
        // update the contact
        dao.updateDVD(dvd);

    }

    @RequestMapping(value = "/dvds", method = RequestMethod.GET)
    @ResponseBody
    public List<DVD> getAllDVDs() {
        // get all of the Contacts from the data layer
        return dao.getDVDs();
    }
}


