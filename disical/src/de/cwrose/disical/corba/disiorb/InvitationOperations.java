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
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getIndex:1.0
    //
    /***/

    int
    getIndex();

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
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setIndex:1.0
    //
    /***/

    void
    setIndex(int index);

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
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setInvitation:1.0
    //
    /***/

    void
    setInvitation(User[] toUser,
                  Date invitationDate)
        throws jdoPersistenceEx;

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
