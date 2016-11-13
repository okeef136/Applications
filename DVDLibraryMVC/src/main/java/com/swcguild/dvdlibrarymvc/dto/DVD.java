/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dto;

import java.util.ArrayList;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author lildocta
 */
public class DVD {
    private int DVDId;
    
    @NotEmpty(message = "Please enter a valid director.")
    @Length(max = 20, message = "Director names must be no more than 20 characters in length.")
    private String director;
    @NotEmpty(message = "Please enter a valid title.")
    @Length(max = 50, message = "titles must be no more than 50 characters in length.")
    private String title;
    @NotEmpty(message = "Please enter a valid rating.")
    @Length(max = 4, message = "Ratings must be no more than 4 characters in length.")
    private String rating;
    @Min(1000)
    @Max(3000)
    private int year;
    private ArrayList<String> notes = new ArrayList<>();
    @Length(max = 50, message = "Notes names must be no more than 50 characters in length.")
    private String note = "NOTE";
    

    public DVD() {}
    
    public DVD(String director, String title, String rating, int year, ArrayList<String> notes) {
        this.director = director;
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.notes = notes;
    }
     public int getDvdId()
        {
        return DVDId;
        }

    public void setDvdId(int DVDId)
        {
        this.DVDId = DVDId;
        }

    public String getDirector()
    {
        return director;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public String getRating()
    {
        return rating;
    }
    
    public int getYear()
    {
        return year;
    }
    
    public ArrayList<String> getNotes()
    {
        return notes;
    }
    
    public String getNote()
        {
        return note;
        }
    
    public void setNote(String note)
        {
            notes.add(note);
        }

    public void setDirector(String director)
        {
        this.director = director;
        }

    public void setTitle(String title)
        {
        this.title = title;
        }

    public void setRating(String rating)
        {
        this.rating = rating;
        }

    public void setYear(int year)
        {
        this.year = year;
        }

    public void setNotes(ArrayList<String> notes)
        {
        this.notes = notes;
        }
    /**
     * @param fName the fName to set
     */
    
    
}
