package com.elevenfifty.www.indycivichack.Model;

/**
 * Created by jeffhooton on 6/6/15.
 */
public class Singleton {
    private static Singleton ourInstance;

    private static String lastName;
    private static String firstName;
    private static String street;
    private static String city;
    private static String state;
    private static String zip;
    private static String bday;
    private static String age;
    private static String gender;
    private static String iep;

    public static Singleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new Singleton();
        }
        return ourInstance;
    }

    private Singleton() { }

    public static Singleton getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(Singleton ourInstance) {
        Singleton.ourInstance = ourInstance;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        Singleton.lastName = lastName;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        Singleton.firstName = firstName;
    }

    public static String getStreet() {
        return street;
    }

    public static void setStreet(String street) {
        Singleton.street = street;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        Singleton.city = city;
    }

    public static String getState() {
        return state;
    }

    public static void setState(String state) {
        Singleton.state = state;
    }

    public static String getZip() {
        return zip;
    }

    public static void setZip(String zip) {
        Singleton.zip = zip;
    }

    public static String getBday() {
        return bday;
    }

    public static void setBday(String bday) {
        Singleton.bday = bday;
    }

    public static String getAge() {
        return age;
    }

    public static void setAge(String age) {
        Singleton.age = age;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        Singleton.gender = gender;
    }

    public static String getIep() {
        return iep;
    }

    public static void setIep(String iep) {
        Singleton.iep = iep;
    }
}
