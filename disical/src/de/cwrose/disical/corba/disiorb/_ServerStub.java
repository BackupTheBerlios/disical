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
// IDL:de/cwrose/disical/corba/disiorb/Server:1.0
//
public class _ServerStub extends org.omg.CORBA.portable.ObjectImpl
                         implements Server
{
    private static final String[] _ob_ids_ =
    {
        "IDL:de/cwrose/disical/corba/disiorb/Server:1.0",
    };

    public String[]
    _ids()
    {
        return _ob_ids_;
    }

    final public static java.lang.Class _ob_opsClass = ServerOperations.class;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Server/createUser:1.0
    //
    public void
    createUser(String _ob_a0,
               String _ob_a1,
               String _ob_a2,
               String _ob_a3)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("createUser", true);
                    out.write_string(_ob_a0);
                    out.write_string(_ob_a1);
                    out.write_string(_ob_a2);
                    out.write_string(_ob_a3);
                    in = _invoke(out);
                    return;
                }
                catch(org.omg.CORBA.portable.RemarshalException _ob_ex)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _ob_aex)
                {
                    final String _ob_id = _ob_aex.getId();
                    in = _ob_aex.getInputStream();

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _ob_id);
                }
                finally
                {
                    _releaseReply(in);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("createUser", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                ServerOperations _ob_self = (ServerOperations)_ob_so.servant;
                try
                {
                    _ob_self.createUser(_ob_a0, _ob_a1, _ob_a2, _ob_a3);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/Server/login:1.0
    //
    public User
    login(String _ob_a0,
          String _ob_a1)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("login", true);
                    out.write_string(_ob_a0);
                    out.write_string(_ob_a1);
                    in = _invoke(out);
                    User _ob_r = UserHelper.read(in);
                    return _ob_r;
                }
                catch(org.omg.CORBA.portable.RemarshalException _ob_ex)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _ob_aex)
                {
                    final String _ob_id = _ob_aex.getId();
                    in = _ob_aex.getInputStream();

                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: " + _ob_id);
                }
                finally
                {
                    _releaseReply(in);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("login", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                ServerOperations _ob_self = (ServerOperations)_ob_so.servant;
                try
                {
                    return _ob_self.login(_ob_a0, _ob_a1);
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }
}