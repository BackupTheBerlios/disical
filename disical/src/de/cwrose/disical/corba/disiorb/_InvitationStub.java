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
public class _InvitationStub extends org.omg.CORBA.portable.ObjectImpl
                             implements Invitation
{
    private static final String[] _ob_ids_ =
    {
        "IDL:de/cwrose/disical/corba/disiorb/Invitation:1.0",
    };

    public String[]
    _ids()
    {
        return _ob_ids_;
    }

    final public static java.lang.Class _ob_opsClass = InvitationOperations.class;

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/fromUser:1.0
    //
    public User
    fromUser()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("fromUser", true);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("fromUser", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                InvitationOperations _ob_self = (InvitationOperations)_ob_so.servant;
                try
                {
                    return _ob_self.fromUser();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/toUser:1.0
    //
    public User[]
    toUser()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("toUser", true);
                    in = _invoke(out);
                    User[] _ob_r = seqUserHelper.read(in);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("toUser", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                InvitationOperations _ob_self = (InvitationOperations)_ob_so.servant;
                try
                {
                    return _ob_self.toUser();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/InvitationDate:1.0
    //
    public Date
    InvitationDate()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("InvitationDate", true);
                    in = _invoke(out);
                    Date _ob_r = DateHelper.read(in);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("InvitationDate", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                InvitationOperations _ob_self = (InvitationOperations)_ob_so.servant;
                try
                {
                    return _ob_self.InvitationDate();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/getStatus:1.0
    //
    public short
    getStatus()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("getStatus", true);
                    in = _invoke(out);
                    short _ob_r = in.read_short();
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("getStatus", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                InvitationOperations _ob_self = (InvitationOperations)_ob_so.servant;
                try
                {
                    return _ob_self.getStatus();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/Invitation/setStatus:1.0
    //
    public void
    setStatus(short _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("setStatus", true);
                    out.write_short(_ob_a0);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("setStatus", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                InvitationOperations _ob_self = (InvitationOperations)_ob_so.servant;
                try
                {
                    _ob_self.setStatus(_ob_a0);
                    return;
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }
}
