package com.cultivation.javaBasic.showYourIntelligence;

import com.cultivation.javaBasic.util.Person;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Arrays;
import java.util.Objects;
@SuppressWarnings("unused")
public class PersonForEquals implements Comparable {
    private final String name;
    private final short yearOfBirth;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        PersonForEquals person = (PersonForEquals)obj;
        return person.name == name && person.yearOfBirth == yearOfBirth;
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        int hash = 1;
        hash = hash * 3 + name == null ? 0 : name.hashCode();
        hash = hash * 5 + yearOfBirth;
        return hash;
        // --end-->
    }

    @Override
    public int compareTo(Object o) {
        if(o == null){
            throw new NullPointerException();
        }
        PersonForEquals person = (PersonForEquals) o;
        if(name.compareTo(person.name) == 0){
            return Short.compare(yearOfBirth, person.yearOfBirth);
        }
        return name.compareTo(person.name);
    }
}
