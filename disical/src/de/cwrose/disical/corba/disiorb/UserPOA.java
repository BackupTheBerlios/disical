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
public abstract class UserPOA
    extends org.omg.PortableServer.Servant
    implements org.omg.CORBA.portable.InvokeHandler,
               UserOperations
{
    static final String[] _ob_ids_ =
    {
        "IDL:de/cwrose/disical/corba/disiorb/User:1.0",
    };

    public User
    _this()
    {
        return UserHelper.narrow(super._this_object());
    }

    public User
    _this(org.omg.CORBA.ORB orb)
    {
        return UserHelper.narrow(super._this_object(orb));
    }

    public String[]
    _all_interfaces(org.omg.PortableServer.POA poa, byte[] objectId)
    {
        return _ob_ids_;
    }

    public org.omg.CORBA.portable.OutputStream
    _invoke(String opName,
            org.omg.CORBA.portable.InputStream in,
            org.omg.CORBA.portable.ResponseHandler handler)
    {
        final String[] _ob_names =
        {
            "changePW",
            "createDate",
            "deleteUser",
            "getEmail",
            "getInvitations",
            "getLogin",
            "getName",
            "getPasswd",
            "getUserInfo",
            "listDatesByLocation",
            "listDatesBySubject",
            "listDatesByTime",
            "persist",
            "selectDate",
            "setEmail",
            "setInvitation",
            "setLogin",
            "setName",
            "setPasswd",
            "setUserInfo"
        };

        int _ob_left = 0;
        int _ob_right = _ob_names.length;
        int _ob_index = -1;

        while(_ob_left < _ob_right)
        {
            int _ob_m = (_ob_left + _ob_right) / 2;
            int _ob_res = _ob_names[_ob_m].compareTo(opName);
            if(_ob_res == 0)
            {
                _ob_index = _ob_m;
                break;
            }
            else if(_ob_res > 0)
                _ob_right = _ob_m;
            else
                _ob_left = _ob_m + 1;
        }

        switch(_ob_index)
        {
        case 0: // changePW
            return _OB_op_changePW(in, handler);

        case 1: // createDate
            return _OB_op_createDate(in, handler);

        case 2: // deleteUser
            return _OB_op_deleteUser(in, handler);

        case 3: // getEmail
            return _OB_op_getEmail(in, handler);

        case 4: // getInvitations
            return _OB_op_getInvitations(in, handler);

        case 5: // getLogin
            return _OB_op_getLogin(in, handler);

        case 6: // getName
            return _OB_op_getName(in, handler);

        case 7: // getPasswd
            return _OB_op_getPasswd(in, handler);

        case 8: // getUserInfo
            return _OB_op_getUserInfo(in, handler);

        case 9: // listDatesByLocation
            return _OB_op_listDatesByLocation(in, handler);

        case 10: // listDatesBySubject
            return _OB_op_listDatesBySubject(in, handler);

        case 11: // listDatesByTime
            return _OB_op_listDatesByTime(in, handler);

        case 12: // persist
            return _OB_op_persist(in, handler);

        case 13: // selectDate
            return _OB_op_selectDate(in, handler);

        case 14: // setEmail
            return _OB_op_setEmail(in, handler);

        case 15: // setInvitation
            return _OB_op_setInvitation(in, handler);

        case 16: // setLogin
            return _OB_op_setLogin(in, handler);

        case 17: // setName
            return _OB_op_setName(in, handler);

        case 18: // setPasswd
            return _OB_op_setPasswd(in, handler);

        case 19: // setUserInfo
            return _OB_op_setUserInfo(in, handler);
        }

        throw new org.omg.CORBA.BAD_OPERATION();
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_changePW(org.omg.CORBA.portable.InputStream in,
                    org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        changePW(_ob_a0);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_createDate(org.omg.CORBA.portable.InputStream in,
                      org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        String _ob_a1 = in.read_string();
        String _ob_a2 = in.read_string();
        String _ob_a3 = in.read_string();
        Date _ob_r = createDate(_ob_a0, _ob_a1, _ob_a2, _ob_a3);
        out = handler.createReply();
        DateHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_deleteUser(org.omg.CORBA.portable.InputStream in,
                      org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        deleteUser();
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getEmail(org.omg.CORBA.portable.InputStream in,
                    org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_r = getEmail();
        out = handler.createReply();
        out.write_string(_ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getInvitations(org.omg.CORBA.portable.InputStream in,
                          org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        Invitation[] _ob_r = getInvitations();
        out = handler.createReply();
        seqInvitationHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getLogin(org.omg.CORBA.portable.InputStream in,
                    org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_r = getLogin();
        out = handler.createReply();
        out.write_string(_ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getName(org.omg.CORBA.portable.InputStream in,
                   org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_r = getName();
        out = handler.createReply();
        out.write_string(_ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getPasswd(org.omg.CORBA.portable.InputStream in,
                     org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_r = getPasswd();
        out = handler.createReply();
        out.write_string(_ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getUserInfo(org.omg.CORBA.portable.InputStream in,
                       org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        User _ob_r = getUserInfo();
        out = handler.createReply();
        UserHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_listDatesByLocation(org.omg.CORBA.portable.InputStream in,
                               org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        Date[] _ob_r = listDatesByLocation(_ob_a0);
        out = handler.createReply();
        seqDateHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_listDatesBySubject(org.omg.CORBA.portable.InputStream in,
                              org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        Date[] _ob_r = listDatesBySubject(_ob_a0);
        out = handler.createReply();
        seqDateHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_listDatesByTime(org.omg.CORBA.portable.InputStream in,
                           org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        String _ob_a1 = in.read_string();
        Date[] _ob_r = listDatesByTime(_ob_a0, _ob_a1);
        out = handler.createReply();
        seqDateHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_persist(org.omg.CORBA.portable.InputStream in,
                   org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        boolean _ob_r = persist();
        out = handler.createReply();
        out.write_boolean(_ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_selectDate(org.omg.CORBA.portable.InputStream in,
                      org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        int _ob_a0 = in.read_long();
        Date _ob_r = selectDate(_ob_a0);
        out = handler.createReply();
        DateHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setEmail(org.omg.CORBA.portable.InputStream in,
                    org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        setEmail(_ob_a0);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setInvitation(org.omg.CORBA.portable.InputStream in,
                         org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        User[] _ob_a0 = seqUserHelper.read(in);
        Date _ob_a1 = DateHelper.read(in);
        setInvitation(_ob_a0, _ob_a1);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setLogin(org.omg.CORBA.portable.InputStream in,
                    org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        setLogin(_ob_a0);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setName(org.omg.CORBA.portable.InputStream in,
                   org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        setName(_ob_a0);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setPasswd(org.omg.CORBA.portable.InputStream in,
                     org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        String _ob_a0 = in.read_string();
        setPasswd(_ob_a0);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setUserInfo(org.omg.CORBA.portable.InputStream in,
                       org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        User _ob_a0 = UserHelper.read(in);
        setUserInfo(_ob_a0);
        out = handler.createReply();
        return out;
    }
}
