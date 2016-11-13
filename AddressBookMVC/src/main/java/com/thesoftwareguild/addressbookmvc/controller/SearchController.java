/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookmvc.controller;

import com.thesoftwareguild.addressbookmvc.dao.AddressBookDao;
import com.thesoftwareguild.addressbookmvc.dao.SearchTerm;
import com.thesoftwareguild.addressbookmvc.dto.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {

    private AddressBookDao dao;

    @Inject
    public SearchController(AddressBookDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String displaySearchPage() {
        return "search";
    }
    
     @RequestMapping(value = "search/addresses", method = RequestMethod.POST)
    @ResponseBody
    public List<Address> searchAddresses(@RequestBody Map<String, String> searchMap) {
        // Create the map of search criteria to send to the DAO
        Map<SearchTerm, String> criteriaMap = new HashMap<>();

        // Determine which search terms have values, translate the String
        // keys into SearchTerm enums, and set the corresponding values
        // appropriately.
        String currentTerm = searchMap.get("firstName");
        if (currentTerm!= null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.FIRST_NAME, currentTerm);
        }
        currentTerm = searchMap.get("lastName");
        if (currentTerm!= null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.LAST_NAME, currentTerm);
        }
        currentTerm = searchMap.get("streetName");
        if (currentTerm!= null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STREET, currentTerm);
        }
        currentTerm = searchMap.get("city");
        if (currentTerm!= null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.CITY, currentTerm);
        }
        currentTerm = searchMap.get("state");
        if (currentTerm!= null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.STATE, currentTerm);
        }
          currentTerm = searchMap.get("zipcode");
        if (currentTerm!= null && !currentTerm.isEmpty()) {
            criteriaMap.put(SearchTerm.ZIPCODE, currentTerm);
        }

        return dao.searchAddresses(criteriaMap);
    }
}
