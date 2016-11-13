/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookmvc.dao;

import com.thesoftwareguild.addressbookmvc.dto.Address;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author Devin Hall
 */
public class AddressBookInMemImpl implements AddressBookDao {

    private Map<Integer, Address> addresses = new HashMap<>();
    
    private static int addressIdCounter = 0;
   

    public AddressBookInMemImpl() {
      
    }

    @Override
    public Address addAddress(Address a) {
        a.setAddressId(addressIdCounter);
        
        // assign the current counter values as the contactid
      
        // increment the counter so it is ready for use for the next contact
        addressIdCounter++;
        
        addresses.put(a.getAddressId(), a);
     //   dao.writeAddresses(addresses, file);
        return a;
    }

    @Override
    public void removeAddress(int addressId) {
        
        addresses.remove(addressId);
        
     //   dao.writeAddresses(addresses, file);
    }

    @Override
    public void saveAddresses() {
   //     dao.writeAddresses(addresses, file);
    }

   @Override
    public List<Address> searchAddresses(Map<SearchTerm, String> criteria) {
        // Get all the search terms from the map
        String firstNameCriteria = criteria.get(SearchTerm.FIRST_NAME);
        String lastNameCriteria = criteria.get(SearchTerm.LAST_NAME);
        String streetCriteria = criteria.get(SearchTerm.STREET);
        String cityCriteria = criteria.get(SearchTerm.CITY);
        String stateCriteria = criteria.get(SearchTerm.STATE);
         String zipCriteria = criteria.get(SearchTerm.ZIPCODE);
        // Declare all the predicate conditions
        Predicate<Address> firstNameMatches;
        Predicate<Address> lastNameMatches;
        Predicate<Address> streetMatches;
        Predicate<Address> cityMatches;
        Predicate<Address> stateMatches;
        Predicate<Address> zipMatches;

        // Placeholder predicate - always returns true. Used for search terms
        // that are empty
        Predicate<Address> truePredicate = (c) -> {
            return true;
        };
        // Assign values to predicates. If a given search term is empty, just
        // assign the default truePredicate, otherwise assign the predicate that
        // properly filters for the given term.
        firstNameMatches = (firstNameCriteria == null || firstNameCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getFirstName().equals(firstNameCriteria);

        lastNameMatches = (lastNameCriteria == null || lastNameCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getLastName().equals(lastNameCriteria);

        streetMatches = (streetCriteria == null || streetCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getStreetName().equals(streetCriteria);

        cityMatches = (cityCriteria == null || cityCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getCity().equals(cityCriteria);

        stateMatches = (stateCriteria == null || stateCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getState().equals(stateCriteria);
        
       zipMatches = (zipCriteria == null || zipCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getZipcode().equals(zipCriteria);
        // Return the list of Contacts that match the given criteria. To do this we
        // just AND all the predicates together in a filter operation.
        return addresses.values().stream()
                .filter(firstNameMatches
                        .and(lastNameMatches)
                        .and(streetMatches)
                        .and(cityMatches)
                        .and(stateMatches)
                        .and(zipMatches))
                        .collect(Collectors.toList());
 
    }
    
    @Override
    public List<Address> getAllAddresses() {
        Collection<Address> c = addresses.values();
        return new ArrayList(c);
    }
    
     @Override
    public Address getAddressById(int addressId)
        {
        return addresses.get(addressId);
        }
    
    @Override
    public void updateAddress(Address address)
        {
        addresses.put(address.getAddressId(), address);
        }

}
