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
public class _UserStub extends org.omg.CORBA.portable.ObjectImpl
                       implements User
{
    private static final String[] _ob_ids_ =
    {
        "IDL:de/cwrose/disical/corba/disiorb/User:1.0",
    };

    public String[]
    _ids()
    {
        return _ob_ids_;
    }

    final public static java.lang.Class _ob_opsClass = UserOperations.class;

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/setLogin:1.0
    //
    public void
    setLogin(String _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("setLogin", true);
                    out.write_string(_ob_a0);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("setLogin", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    _ob_self.setLogin(_ob_a0);
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
    // IDL:de/cwrose/disical/corba/disiorb/User/setName:1.0
    //
    public void
    setName(String _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("setName", true);
                    out.write_string(_ob_a0);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("setName", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    _ob_self.setName(_ob_a0);
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
    // IDL:de/cwrose/disical/corba/disiorb/User/setEmail:1.0
    //
    public void
    setEmail(String _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("setEmail", true);
                    out.write_string(_ob_a0);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("setEmail", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    _ob_self.setEmail(_ob_a0);
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
    // IDL:de/cwrose/disical/corba/disiorb/User/setPasswd:1.0
    //
    public void
    setPasswd(String _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("setPasswd", true);
                    out.write_string(_ob_a0);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("setPasswd", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    _ob_self.setPasswd(_ob_a0);
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
    // IDL:de/cwrose/disical/corba/disiorb/User/getLogin:1.0
    //
    public String
    getLogin()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("getLogin", true);
                    in = _invoke(out);
                    String _ob_r = in.read_string();
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("getLogin", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.getLogin();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getName:1.0
    //
    public String
    getName()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("getName", true);
                    in = _invoke(out);
                    String _ob_r = in.read_string();
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("getName", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.getName();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getEmail:1.0
    //
    public String
    getEmail()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("getEmail", true);
                    in = _invoke(out);
                    String _ob_r = in.read_string();
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("getEmail", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.getEmail();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getPasswd:1.0
    //
    public String
    getPasswd()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("getPasswd", true);
                    in = _invoke(out);
                    String _ob_r = in.read_string();
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("getPasswd", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.getPasswd();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/persist:1.0
    //
    public boolean
    persist()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("persist", true);
                    in = _invoke(out);
                    boolean _ob_r = in.read_boolean();
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("persist", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.persist();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/deleteUser:1.0
    //
    public void
    deleteUser()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("deleteUser", true);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("deleteUser", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    _ob_self.deleteUser();
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
    // IDL:de/cwrose/disical/corba/disiorb/User/createDate:1.0
    //
    public Date
    createDate(String _ob_a0,
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
                    out = _request("createDate", true);
                    out.write_string(_ob_a0);
                    out.write_string(_ob_a1);
                    out.write_string(_ob_a2);
                    out.write_string(_ob_a3);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("createDate", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.createDate(_ob_a0, _ob_a1, _ob_a2, _ob_a3);
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/selectDate:1.0
    //
    public Date
    selectDate(int _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("selectDate", true);
                    out.write_long(_ob_a0);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("selectDate", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.selectDate(_ob_a0);
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/listDatesByTime:1.0
    //
    public Date[]
    listDatesByTime(String _ob_a0,
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
                    out = _request("listDatesByTime", true);
                    out.write_string(_ob_a0);
                    out.write_string(_ob_a1);
                    in = _invoke(out);
                    Date[] _ob_r = seqDateHelper.read(in);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("listDatesByTime", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.listDatesByTime(_ob_a0, _ob_a1);
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/listDatesByLocation:1.0
    //
    public Date[]
    listDatesByLocation(String _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("listDatesByLocation", true);
                    out.write_string(_ob_a0);
                    in = _invoke(out);
                    Date[] _ob_r = seqDateHelper.read(in);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("listDatesByLocation", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.listDatesByLocation(_ob_a0);
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/listDatesBySubject:1.0
    //
    public Date[]
    listDatesBySubject(String _ob_a0)
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("listDatesBySubject", true);
                    out.write_string(_ob_a0);
                    in = _invoke(out);
                    Date[] _ob_r = seqDateHelper.read(in);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("listDatesBySubject", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.listDatesBySubject(_ob_a0);
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/getInvitations:1.0
    //
    public Invitation[]
    getInvitations()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("getInvitations", true);
                    in = _invoke(out);
                    Invitation[] _ob_r = seqInvitationHelper.read(in);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("getInvitations", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    return _ob_self.getInvitations();
                }
                finally
                {
                    _servant_postinvoke(_ob_so);
                }
            }
        }
    }

    //
    // IDL:de/cwrose/disical/corba/disiorb/User/destroy:1.0
    //
    public void
    destroy()
    {
        while(true)
        {
            if(!this._is_local())
            {
                org.omg.CORBA.portable.OutputStream out = null;
                org.omg.CORBA.portable.InputStream in = null;
                try
                {
                    out = _request("destroy", true);
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
                org.omg.CORBA.portable.ServantObject _ob_so = _servant_preinvoke("destroy", _ob_opsClass);
                if(_ob_so == null)
                    continue;
                UserOperations _ob_self = (UserOperations)_ob_so.servant;
                try
                {
                    _ob_self.destroy();
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
