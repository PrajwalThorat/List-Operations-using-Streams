package com.stackroute.streams;

/* Write test cases for positive and negative scenarios*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryUtilityTests {
    private List<String> list;
    private CountryUtility utility;
    private Map<String, String> mapCountryCapital;

    @BeforeEach
    public void setUp() {
        list = Arrays.asList("India", "Pakistan", "Canada", "USA", "India", "England", "West Indies");
        utility = new CountryUtility();
    }

    @Test
    public void searchCountryTest() {
        assertEquals(true, utility.searchCountry(list, "India"));


    }

    @Test
    public void checkIfListIsEmpty() {
        assertEquals(0, Arrays.asList().size());
    }


    @Test
    public void sortCountriesTest() {
        assertEquals("Optional[[Canada, England, India, Pakistan, USA, West Indies]]", utility.sortCountries(list).toString());
    }

    @Test
    public void sortCountriesTestIfEmpty() {
        assertEquals("Optional[[]]", utility.sortCountries(Arrays.asList()).toString());
    }

    @Test
    public void checkIfListOfCountriesNotNullTest() {
        assertNotNull(list);
    }

    @Test
    public void testSortedByLengthOfCharacters() {
        assertEquals("[USA, India, Canada, England, Pakistan, West Indies]", utility.getDistinctCountriesInUpperCaseSortedByLength(list).toString());

    }

    @Test
    public void testSortedByLengthOfCharactersWithEqualLengths() {
        List<String> list2 = Arrays.asList("England", "America", "Canadaa");
        assertEquals("[England, America, Canadaa]", utility.getDistinctCountriesInUpperCaseSortedByLength(list2).toString());

    }

    @Test
    public void testGetCountryByCapital() {
        mapCountryCapital = new HashMap<>();
        mapCountryCapital.put("London", "England");
        mapCountryCapital.put("Washington", "America");
        mapCountryCapital.put("Ottawa", "Canada");
        mapCountryCapital.put("Delhi", "India");
        assertEquals("Canada", utility.getCountryByCapital(mapCountryCapital, "Ottawa").toString());
        assertEquals("India", utility.getCountryByCapital(mapCountryCapital, "Delhi").toString());


    }

    @Test
    public void checkIfMapNotNull() {
        mapCountryCapital = new HashMap<>();
        mapCountryCapital.put("London", "England");
        try {
            String result = utility.getCountryByCapital(mapCountryCapital, "");
            assertEquals("India", result);
        } catch (CountryNotFoundException e) {
            System.out.println(e);
        }
    }

    @Test
    public void checkIfEmptyForSearchingCountry() {
        list = Arrays.asList();
        assertEquals(false, utility.searchCountry(list, ""));

    }

    @Test
    public void checkIfEmptySortedByLength() {
        list = Arrays.asList();
        assertEquals("[]", utility.getDistinctCountriesInUpperCaseSortedByLength(list).toString());
    }

    @Test
    public void checkIfMapEmpty() {
        mapCountryCapital = new HashMap<>();
        mapCountryCapital.put("London", "England");
        try {
            String result = utility.getCountryByCapital(mapCountryCapital, "New Delhi");
            assertEquals("", result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void CountryNotPresentTestInList() {
        try {
            Boolean result = utility.searchCountry(list, "Argentina");
            if (result != true)
                throw new CountryNotFoundException();
        } catch (CountryNotFoundException e) {
            System.out.println(e);
        }

    }

    @Test
    public void CountryNotPresentTestInMap() {
        mapCountryCapital = new HashMap<>();
        mapCountryCapital.put("London", "England");

        try {
            String result = utility.getCountryByCapital(mapCountryCapital, "Tokyo");
            assertEquals("India", result);
        } catch (CountryNotFoundException e) {
            System.out.println(e);
        }

    }
}
