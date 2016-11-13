/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookmvc.controller;

import com.thesoftwareguild.addressbookmvc.dao.AddressBookDao;
import com.thesoftwareguild.addressbookmvc.dto.Address;
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
    
    private AddressBookDao dao;

    @Inject
    public HomeController(AddressBookDao dao) {
        this.dao = dao;
    }

    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }
    
    
    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Address getAddress(@PathVariable("id") int id) {
        // Retrieve the Contact associated with the given id and return it
        return dao.getAddressById(id);
    }

    @RequestMapping(value = "/address", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Address createAddress(@Valid @RequestBody Address address) {
        // persist the incoming contact
        dao.addAddress(address);
        // The addContact call to the dao assigned a contactId to the incoming
        // Contact and set that value on the object. Now we return the updated
        // object to the caller.
        return address;
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAddress(@PathVariable("id") int id) {
        // remove the Contact associated with the given id from the data layer
        dao.removeAddress(id);
    }

    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void putAddress(@PathVariable("id") int id, @RequestBody Address address) {
        // set the value of the PathVariable id on the incoming Contact object
        // to ensure that a) the contact id is set on the object and b) that
        // the value of the PathVariable id and the Contact object id are the
        // same.
        address.setAddressId(id);
        // update the contact
        dao.updateAddress(address);

    }

    @RequestMapping(value = "/addresses", method = RequestMethod.GET)
    @ResponseBody
    public List<Address> getAllAddresses() {
        // get all of the Contacts from the data layer
        return dao.getAllAddresses();
    }
}



