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
final public class InvitationHolder implements org.omg.CORBA.portable.Streamable
{
    public Invitation value;

    public
    InvitationHolder()
    {
    }

    public
    InvitationHolder(Invitation initial)
    {
        value = initial;
    }

    public void
    _read(org.omg.CORBA.portable.InputStream in)
    {
        value = InvitationHelper.read(in);
    }

    public void
    _write(org.omg.CORBA.portable.OutputStream out)
    {
        InvitationHelper.write(out, value);
    }

    public org.omg.CORBA.TypeCode
    _type()
    {
        return InvitationHelper.type();
    }
}