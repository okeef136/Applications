/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;

import com.swcguild.dvdlibrarymvc.dto.DVD;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 *
 * @author lildocta
 */
public interface DVDInterface
    {


    void addNote(String note, String title);

    int getAverNotes();

    int getAverYear();

    List getByMPAA(String rating);

    List getByRelease(int year);

    DVD getDVDById(Integer DVDId);

    Collection<DVD> getDVDs();

    Map getDirector(String director);

    String getNewest();

    String getOldest();

    void removeDVD(int id);
    
    void updateDVD(DVD dvd);
    
    DVD addDVD(DVD d);
    
    void deleteNote(ArrayList toDelete, DVD dvd);
    
    List<DVD> searchDVDs(Map<SearchTerm, String> criteria);
    }
