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
// IDL:de/cwrose/disical/corba/disiorb/Invitation:1.0
//
/***/

public interface InvitationOperations
{
    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/invite:1.0
    //
    /***/

    void
    invite(User usr)
        throws jdoPersistenceEx;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setStartTime:1.0
    //
    /***/

    void
    setStartTime(long Time);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setEndTime:1.0
    //
    /***/

    void
    setEndTime(long Time);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setLocation:1.0
    //
    /***/

    void
    setLocation(String location);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setSubject:1.0
    //
    /***/

    void
    setSubject(String subject);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setDescription:1.0
    //
    /***/

    void
    setDescription(String description);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setFromUser:1.0
    //
    /***/

    void
    setFromUser(User fromUser);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getStartTime:1.0
    //
    /***/

    long
    getStartTime();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getEndTime:1.0
    //
    /***/

    long
    getEndTime();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getLocation:1.0
    //
    /***/

    String
    getLocation();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getSubject:1.0
    //
    /***/

    String
    getSubject();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getDescription:1.0
    //
    /***/

    String
    getDescription();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getFromUser:1.0
    //
    /***/

    User
    getFromUser()
        throws jdoPersistenceEx;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getAllInvited:1.0
    //
    /***/

    Invited[]
    getAllInvited()
        throws jdoPersistenceEx,
               emptySeqEx;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getAllNotifiedInv:1.0
    //
    /***/

    Invited[]
    getAllNotifiedInv()
        throws jdoPersistenceEx,
               emptySeqEx;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/delete:1.0
    //
    /***/

    void
    delete()
        throws jdoPersistenceEx;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/persist:1.0
    //
    /***/

    boolean
    persist();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/destroy:1.0
    //
    /***/

    void
    destroy();
}
