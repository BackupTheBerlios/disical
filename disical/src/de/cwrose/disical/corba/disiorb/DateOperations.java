// **********************************************************************
//
// Generated by the ORBacus IDL to Java Translator
//
// Copyright (c) 2001
// IONA Technologies, Inc.
// Waltham, MA, USA
//
// All Rights Reserved
//
// **********************************************************************

// Version: 4.1.0

package de.cwrose.disical.corba.disiorb;

//
// IDL:de/cwrose/disical/corba/disiorb/Date:1.0
//
/***/

public interface DateOperations
{
    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/setStartTime:1.0
    //
    /***/

    void
    setStartTime(long Time);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/setEndTime:1.0
    //
    /***/

    void
    setEndTime(long Time);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/setLocation:1.0
    //
    /***/

    void
    setLocation(String location);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/setSubject:1.0
    //
    /***/

    void
    setSubject(String subject);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/setLogin:1.0
    //
    /***/

    void
    setLogin(User login);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/getStartTime:1.0
    //
    /***/

    long
    getStartTime();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/getEndTime:1.0
    //
    /***/

    long
    getEndTime();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/getLocation:1.0
    //
    /***/

    String
    getLocation();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/getSubject:1.0
    //
    /***/

    String
    getSubject();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/getLogin:1.0
    //
    /***/

    User
    getLogin();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/setIndex:1.0
    //
    /***/

    void
    setIndex(int index);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/getIndex:1.0
    //
    /***/

    int
    getIndex();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/persist:1.0
    //
    /***/

    boolean
    persist();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/deleteDate:1.0
    //
    /***/

    void
    deleteDate()
        throws jdoPersistenceEx;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/changeDate:1.0
    //
    /***/

    void
    changeDate(long start,
               long end,
               String location,
               String subject)
        throws jdoPersistenceEx;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Date/destroy:1.0
    //
    /***/

    void
    destroy();
}
