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
// IDL:de/cwrose/disical/corba/disiorb/User:1.0
//
/***/

public interface UserOperations
{
    //
    // IDL:de/cwrose/disical/corba/disiorb/User/setLogin:1.0
    //
    /***/

    void
    setLogin(String login);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/setName:1.0
    //
    /***/

    void
    setName(String name);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/setEmail:1.0
    //
    /***/

    void
    setEmail(String email);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/setPasswd:1.0
    //
    /***/

    void
    setPasswd(String passwd);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getLogin:1.0
    //
    /***/

    String
    getLogin();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getName:1.0
    //
    /***/

    String
    getName();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getEmail:1.0
    //
    /***/

    String
    getEmail();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getPasswd:1.0
    //
    /***/

    String
    getPasswd();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/persist:1.0
    //
    /***/

    boolean
    persist();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/changePW:1.0
    //
    /***/

    void
    changePW(String newPW);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getUserInfo:1.0
    //
    /***/

    User
    getUserInfo();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/setUserInfo:1.0
    //
    /***/

    void
    setUserInfo(User userInfo);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/deleteUser:1.0
    //
    /***/

    void
    deleteUser();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/createDate:1.0
    //
    /***/

    Date
    createDate(String start,
               String end,
               String location,
               String subject);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/selectDate:1.0
    //
    /***/

    Date
    selectDate(int index);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/listDatesByTime:1.0
    //
    /***/

    Date[]
    listDatesByTime(String start,
                    String end);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/listDatesByLocation:1.0
    //
    /***/

    Date[]
    listDatesByLocation(String location);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/listDatesBySubject:1.0
    //
    /***/

    Date[]
    listDatesBySubject(String subject);

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getInvitations:1.0
    //
    /***/

    Invitation[]
    getInvitations();

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/destroy:1.0
    //
    /***/

    void
    destroy();
}