/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookmvc.controller;

import com.thesoftwareguild.addressbookmvc.dto.Address;
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
import com.thesoftwareguild.addressbookmvc.dao.AddressBookDao;

/**
 *
 * @author Devin Hall
 */
@Controller
public class HomeControllerNoAjax
    {

    // Reference to our DAO
    private AddressBookDao dao;

    // Use annotation-driven constructor injection to inject a DAO implementation
    // into our controller
    @Inject
    public HomeControllerNoAjax(AddressBookDao dao)
        {
        this.dao = dao;

        Address a1 = new Address("Tim", "Burke", "79 Kim Court",
                "Atlanta", "GA", "30311");
        Address a2 = new Address("Robert", "Cooper", "7 Park Meadow Park",
                "Trenton", "NJ", "8695");

        dao.addAddress(a1);
        dao.addAddress(a2);
        }

    // This endpoint simply returns the name of the view that will serve as
    // the main landing page for the new functionality. The name of that view
    // is displayContactListNoAjax.jsp.
    //
    // NOTE: The RequestMapping value, the name of the method, and the name
    // of the JSP are all the same in this case (displayContactListNoAjax).
    // THIS IS NOT A REQUIREMENT!!! These names can be all different if
    // you want them to be.
    //
    // NOTE: This method does takes a Model object as a parameter. This is
    // because this method gets a list of all the Contact objects from the
    // DAO. We need to place this list on the Model so that Spring MVC
    // can pass the list of Contacts on to the view component. We'll
    // use JSTL tags to iterate through the list and print the Contact
    // information to the screen.
    @RequestMapping(value = "/displayAddressBookNoAjax", method = RequestMethod.GET)
    public String displayAddressBookNoAjax(Model model)
        {
        // Get the list of all Contacts
        List<Address> aList = dao.getAllAddresses();
        // Put the list of all Contacts on the Model so Spring MVC can pass it
        // along to the view
        model.addAttribute("addressBook", aList);
        // Return the logical view name
        return "displayAddressBookNoAjax";
        }

    @RequestMapping(value = "displayNewAddressBookNoAjax", method = RequestMethod.GET)
    public String displayNewAddressFormNoAjax()
        {
        return "newAddressFormNoAjax";
        }

    // This endpoint gets the submitted form data from the HttpServletRequest,
    // creates a new Contact object, sets the fields on the new Contact
    // object appropriately, add the Contact to the DAO, and then redirects
    // to the displayContactListNoAjax controller endpoint.
    @RequestMapping(value = "/addNewAddressNoAjax", method = RequestMethod.POST)
    public String addNewAddressNoAjax(HttpServletRequest req)
        {
        // Get all of the form data from the request
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String street = req.getParameter("street");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String zipcode = req.getParameter("zipcode");

        // Create a new Contact and set all the fields
        Address address = new Address();
        address.setFirstName(firstName);
        address.setLastName(lastName);
        address.setStreetName(street);
        address.setCity(city);
        address.setState(state);
        address.setZipcode(zipcode);

        // Add the Contact to the DAO
        dao.addAddress(address);

        // Redirect to the displayContactListNoAjax controller endpoint - we must
        // use the redirect: here so that Spring MVC routes us to the controller
        // endpoint and not directly to a JSP.
        return "redirect:displayAddressBookNoAjax";
        }

    @RequestMapping(value = "/deleteAddressNoAjax", method = RequestMethod.GET)
    public String deleteAddressNoAjax(HttpServletRequest req)
        {
        // Get the id of the contact to be deleted from the HttpServletRequest
        int addressId = Integer.parseInt(req.getParameter("addressId")); // Ask DAO to delete the given contact
        dao.removeAddress(addressId);
// Redirect to the displayContactListNoAjax controller endpoint - we must // use the redirect: here so that Spring MVC routes us to the controller // endpoint.
        return "redirect:displayAddressBookNoAjax";
        }

    @RequestMapping(value = "/displayEditAddressFormNoAjax", method = RequestMethod.GET)
    public String displayEditAddressFormNoAjax(HttpServletRequest req, Model model)
        {
        // Get the id of the Contact to be edited
        int addressId = Integer.parseInt(req.getParameter("addressId")); // Get the Contact from the DAO
        Address address = dao.getAddressById(addressId);
// Put the Contact on the Model
        model.addAttribute("address", address);
        return "editAddressFormNoAjax";
        }

    // This method uses the @ModelAttribute annotation to tell Spring to
// convert the submitted form data into a Contact object for us. The method // then hands the Contact to the DAO for update, and finally redirects to
// the displayContactListNoAjax controller endpoint. 
    @RequestMapping(value = "/editAddressNoAjax", method = RequestMethod.POST)
    public String editAddressNoAjax(@Valid @ModelAttribute("address") Address address, BindingResult result)
        {
        if (result.hasErrors())
        {
            return "editAddressFormNoAjax";
        }
        dao.updateAddress(address);
        return "redirect:displayAddressBookNoAjax";
        }
    }
