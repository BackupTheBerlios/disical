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
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getFromUser:1.0
    //
    /***/

    User
    getFromUser();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getToUser:1.0
    //
    /***/

    User[]
    getToUser();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getInvitationDate:1.0
    //
    /***/

    Date
    getInvitationDate();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getStatus:1.0
    //
    /***/

    short
    getStatus();

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setFromUser:1.0
    //
    /***/

    void
    setFromUser(User fromUser);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setToUser:1.0
    //
    /***/

    void
    setToUser(User[] toUser);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setInvitationDate:1.0
    //
    /***/

    void
    setInvitationDate(Date newdate);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setStatus:1.0
    //
    /***/

    void
    setStatus(short newStatus);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setInvitation:1.0
    //
    /***/

    void
    setInvitation(User[] toUser,
                  Date invitationDate);

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/delete:1.0
    //
    /***/

    void
    delete();

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
