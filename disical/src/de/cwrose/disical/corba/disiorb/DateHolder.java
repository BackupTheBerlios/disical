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
final public class DateHolder implements org.omg.CORBA.portable.Streamable
{
    public Date value;

    public
    DateHolder()
    {
    }

    public
    DateHolder(Date initial)
    {
        value = initial;
    }

    public void
    _read(org.omg.CORBA.portable.InputStream in)
    {
        value = DateHelper.read(in);
    }

    public void
    _write(org.omg.CORBA.portable.OutputStream out)
    {
        DateHelper.write(out, value);
    }

    public org.omg.CORBA.TypeCode
    _type()
    {
        return DateHelper.type();
    }
}
