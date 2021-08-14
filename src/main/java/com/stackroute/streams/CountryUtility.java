package com.stackroute.streams;

import java.util.*;
import java.util.stream.Collectors;

/* Complete the class as per the requirements given in PROBLEM.md */
public class CountryUtility {
    public boolean searchCountry(List<String> countries, String searchCountry)
    {
        return countries.stream()
                .anyMatch(a-> {
                    return a.contains(searchCountry);
                });

    }



    public Optional<List<String>> sortCountries(List<String> countries)
    {
        if(countries==null)
            return Optional.empty();

        countries=countries
                .stream()
                .sorted()
                .distinct()
                .collect(Collectors.toList());
        return Optional.of(countries);

    }


    public Set<String> getDistinctCountriesInUpperCaseSortedByLength(List<String> countries)
    {
        countries=countries.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length()>o2.length())
                    return 1;
                else if(o1.length()<o2.length())
                    return -1;
                else
                    return 0;
            }
        })
                .distinct()
                .collect(Collectors.toList());

        LinkedHashSet<String> set=new LinkedHashSet<>(countries);

        return set;
    }



    public String getCountryByCapital(Map<String, String> countries, String searchCapital)throws CountryNotFoundException
    {
        /*return result=countries
                .getOrDefault(searchCapital,"countryNotFound");*/

        Set<String> capitals = countries.keySet();
        boolean isPresent = capitals.stream().anyMatch(s -> s.equalsIgnoreCase(searchCapital));
        if (isPresent) {
            Optional<String> result = countries.entrySet().stream()
                    .filter(capital -> capital.getKey().equalsIgnoreCase(searchCapital))
                    .map(Map.Entry::getValue)
                    .findFirst();
            return result.get();
        }else{
            throw new CountryNotFoundException();
        }

        //return result;

    }
}
