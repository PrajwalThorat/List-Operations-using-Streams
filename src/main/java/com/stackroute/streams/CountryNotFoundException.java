package com.stackroute.streams;

/* Complete the class as per the requirements given in PROBLEM.md */

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(){
        super("Country not Present");
    }

}