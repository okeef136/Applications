/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookmvc.dao;

import com.thesoftwareguild.addressbookmvc.dto.Address;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Devin Hall
 */
public interface AddressBookDao {

    Address addAddress(Address a);

    List<Address> getAllAddresses();

    void removeAddress(int addressId);

    void saveAddresses();

    List<Address> searchAddresses(Map<SearchTerm, String> criteria);
    
    Address getAddressById (int addressId);
    
    void updateAddress (Address address);
    
}
