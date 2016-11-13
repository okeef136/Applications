/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thesoftwareguild.addressbookmvc.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Devin Hall
 */
public class Address
    {

    private int addressId;

    @NotEmpty(message = "You must supply a value for First Name.")
    @Length(max = 50, message = "First Name must be no more than 50 characters in length.")
    private String firstName;
    @NotEmpty(message = "You must supply a value for Last Name.")
    @Length(max = 50, message = "Last Name must be no more than 50 characters in length.")
    private String lastName;
    @NotEmpty(message = "You must supply a value for Street Name.")
    @Length(max = 50, message = "Street Name must be no more than 50 characters in length.")
    private String streetName;
    @NotEmpty(message = "You must supply a value for City.")
    @Length(max = 10, message = "City must be no more than 10 characters in length.")
    private String city;
    @NotEmpty(message = "Please enter a valid State.")
    @Length(max = 20, message = "State must be no more than 2 characters in length.")
    private String state;
    @NotEmpty(message = "Please enter a valid Zipcode.")
    @Length(max = 5, message = "Zipcode must be no more than 5 characters in length.")
    private String zipcode;

    public Address()
        {
        }

    public Address(String firstName, String lastName, String streetName,
            String city, String state, String zipcode)
        {
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        }

    public int getAddressId()
        {
        return addressId;
        }

    public void setAddressId(int addressId)
        {
        this.addressId = addressId;
        }

    /**
     * @return the firstName
     */
    public String getFirstName()
        {
        return firstName;
        }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName)
        {
        this.firstName = firstName;
        }

    /**
     * @return the lastName
     */
    public String getLastName()
        {
        return lastName;
        }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName)
        {
        this.lastName = lastName;
        }

    /**
     * @return the streetName
     */
    public String getStreetName()
        {
        return streetName;
        }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName)
        {
        this.streetName = streetName;
        }

    /**
     * @return the city
     */
    public String getCity()
        {
        return city;
        }

    /**
     * @param city the city to set
     */
    public void setCity(String city)
        {
        this.city = city;
        }

    /**
     * @return the state
     */
    public String getState()
        {
        return state;
        }

    /**
     * @param state the state to set
     */
    public void setState(String state)
        {
        this.state = state;
        }

    /**
     * @return the zipcode
     */
    public String getZipcode()
        {
        return zipcode;
        }

    /**
     * @param zipcode the zipcode to set
     */
    public void setZipcode(String zipcode)
        {
        this.zipcode = zipcode;
        }

    }
