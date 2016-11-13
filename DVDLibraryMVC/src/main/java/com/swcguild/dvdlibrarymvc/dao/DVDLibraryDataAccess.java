/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.dvdlibrarymvc.dao;
import com.swcguild.dvdlibrarymvc.dto.DVD;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 * @author lildocta
 */
public class DVDLibraryDataAccess implements DVDInterface {
    private String file;
    public DVDLibraryDataAccess()
    {
       
    }
    HashMap<Integer, DVD> dvdHash = new HashMap<>();
    int DVDIdCounter = 0;
    
    
    
    @Override
    public List getByRelease(int year)
        {
        //Uses lambdas and streams to filter through release dates
        return dvdHash
                .values()
                .stream()
                .filter(f -> f.getYear() < year)
                .collect(Collectors.toList());
        
        
        }
    
    @Override
    public String getNewest()
        {
        List<DVD> recent = dvdHash
                .values()
                .stream()
                //compares two dvds by release year and then reorders them so the new dvd goes to the top of the list
                .sorted((a,b) -> (b.getYear() - (a.getYear())))
                .collect(Collectors.toList());
        String title = recent.get(0).getTitle();
        int yearInt = recent.get(0).getYear();
        String year = Integer.toString(yearInt);
        return title + ", " + year;
        }
    
    @Override
    public String getOldest()
        {
        List<DVD> recent = dvdHash
                .values()
                .stream()
                //sorts lowest to highest
                .sorted((a,b) -> (a.getYear() - (b.getYear())))
                .collect(Collectors.toList());
        String title = recent.get(0).getTitle();
        int yearInt = recent.get(0).getYear();
        String year = Integer.toString(yearInt);
        return title + ", " + year;
        }
    
    @Override
    public List getByMPAA(String rating)
        {
        return dvdHash
                .values()
                .stream()
                //searches through dvd objects to find specific rating (tried using enum but ran into issues, discuss possible solutions
                .filter(f -> f.getRating().equals(rating))
                .collect(Collectors.toList());
        }
    
    @Override
    public Map getDirector(String director)
        {
        List<DVD> directors = dvdHash
                        .values()
                        .stream()
                        .filter(f -> f.getDirector().equals(director))
                        .collect(Collectors.toList());
        
        //Creates a map of DVDs to list directors and sort them by rating
        Map<String, List<DVD>> byRating
                = directors.stream()
                        .collect(Collectors.groupingBy(DVD::getRating));
        return byRating;
        }
    
    @Override
    public int getAverNotes()
        {
        int average;
        int sum = 0;
        int count = 0;
        for(DVD t: dvdHash.values())
            {
                sum = sum + (t.getYear());
                count += 1;
            }
        average = sum/count;
        return average;
        }
    
    @Override
    public int getAverYear()
        {
        int average;
        int sum = 0;
        int count = 0;
        for(DVD d: dvdHash.values())
            {
                sum = sum + (d.getYear());
                count += 1;
            }
        average = sum/count;
        return average;
        }
    
    @Override
    public List<DVD> getDVDs()
    {
    Collection<DVD> c = dvdHash.values();
        return new ArrayList(c);
    }
    
    @Override
    public void removeDVD(int id)
    {
        dvdHash.remove(id);
    }
    
    @Override
    public DVD addDVD(DVD d)
    {
        d.setDvdId(DVDIdCounter);
        
        DVDIdCounter++;
        
        dvdHash.put(d.getDvdId(), d);
        return d;
    }
    
    
    @Override
    public DVD getDVDById(Integer DVDId)
    {
        return dvdHash.get(DVDId);
    }
    
    @Override
    public void addNote(String note, String title)
    {
        dvdHash.get(title).setNote(note);
    }
    
    @Override
    public void deleteNote(ArrayList toDelete, DVD dvd)
        {
        for(int i = 0; i <= toDelete.size(); i++)
        {
            
        }
        
        }
    
    @Override
    public void updateDVD(DVD dvd)
        {
        dvdHash.put(dvd.getDvdId(), dvd);
        }
    
    @Override
    public List<DVD> searchDVDs(Map<SearchTerm, String> criteria) {
        // Get all the search terms from the map
        String titleCriteria = criteria.get(SearchTerm.TITLE);
        String directorCriteria = criteria.get(SearchTerm.DIRECTOR);
        String ratingCriteria = criteria.get(SearchTerm.RATING);
        String yearCriteria = criteria.get(SearchTerm.YEAR);
        // Declare all the predicate conditions
        Predicate<DVD> titleMatches;
        Predicate<DVD> directorMatches;
        Predicate<DVD> ratingMatches;
        Predicate<DVD> yearMatches;

        // Placeholder predicate - always returns true. Used for search terms
        // that are empty
        Predicate<DVD> truePredicate = (c) -> {
            return true;
        };
        // Assign values to predicates. If a given search term is empty, just
        // assign the default truePredicate, otherwise assign the predicate that
        // properly filters for the given term.
        titleMatches = (titleCriteria == null || titleCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getTitle().equals(titleCriteria);

        directorMatches = (directorCriteria == null || directorCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getDirector().equals(directorCriteria);

        ratingMatches = (ratingCriteria == null || ratingCriteria.isEmpty())
                ? truePredicate
                : (c) -> c.getRating().equals(ratingCriteria);

        yearMatches = (yearCriteria == null || yearCriteria.isEmpty())
                ? truePredicate
                : (c) -> Integer.toString(c.getYear()).equals(yearCriteria);

        
        // Return the list of Contacts that match the given criteria. To do this we
        // just AND all the predicates together in a filter operation.
        return dvdHash.values().stream()
                .filter(titleMatches
                        .and(directorMatches)
                        .and(ratingMatches)
                        .and(yearMatches))
                        .collect(Collectors.toList());
 
    }
    

}
    
    
