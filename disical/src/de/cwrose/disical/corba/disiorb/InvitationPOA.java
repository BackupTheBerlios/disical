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
public abstract class InvitationPOA
    extends org.omg.PortableServer.Servant
    implements org.omg.CORBA.portable.InvokeHandler,
               InvitationOperations
{
    static final String[] _ob_ids_ =
    {
        "IDL:de/cwrose/disical/corba/disiorb/Invitation:1.0",
    };

    public Invitation
    _this()
    {
        return InvitationHelper.narrow(super._this_object());
    }

    public Invitation
    _this(org.omg.CORBA.ORB orb)
    {
        return InvitationHelper.narrow(super._this_object(orb));
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
            "delete",
            "destroy",
            "getFromUser",
            "getInvitationDate",
            "getStatus",
            "getToUser",
            "persist",
            "setFromUser",
            "setInvitation",
            "setInvitationDate",
            "setStatus",
            "setToUser"
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
        case 0: // delete
            return _OB_op_delete(in, handler);

        case 1: // destroy
            return _OB_op_destroy(in, handler);

        case 2: // getFromUser
            return _OB_op_getFromUser(in, handler);

        case 3: // getInvitationDate
            return _OB_op_getInvitationDate(in, handler);

        case 4: // getStatus
            return _OB_op_getStatus(in, handler);

        case 5: // getToUser
            return _OB_op_getToUser(in, handler);

        case 6: // persist
            return _OB_op_persist(in, handler);

        case 7: // setFromUser
            return _OB_op_setFromUser(in, handler);

        case 8: // setInvitation
            return _OB_op_setInvitation(in, handler);

        case 9: // setInvitationDate
            return _OB_op_setInvitationDate(in, handler);

        case 10: // setStatus
            return _OB_op_setStatus(in, handler);

        case 11: // setToUser
            return _OB_op_setToUser(in, handler);
        }

        throw new org.omg.CORBA.BAD_OPERATION();
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_delete(org.omg.CORBA.portable.InputStream in,
                  org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        delete();
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_destroy(org.omg.CORBA.portable.InputStream in,
                   org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        destroy();
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getFromUser(org.omg.CORBA.portable.InputStream in,
                       org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        User _ob_r = getFromUser();
        out = handler.createReply();
        UserHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getInvitationDate(org.omg.CORBA.portable.InputStream in,
                             org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        Date _ob_r = getInvitationDate();
        out = handler.createReply();
        DateHelper.write(out, _ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getStatus(org.omg.CORBA.portable.InputStream in,
                     org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        short _ob_r = getStatus();
        out = handler.createReply();
        out.write_short(_ob_r);
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_getToUser(org.omg.CORBA.portable.InputStream in,
                     org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        User[] _ob_r = getToUser();
        out = handler.createReply();
        seqUserHelper.write(out, _ob_r);
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
    _OB_op_setFromUser(org.omg.CORBA.portable.InputStream in,
                       org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        User _ob_a0 = UserHelper.read(in);
        setFromUser(_ob_a0);
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
    _OB_op_setInvitationDate(org.omg.CORBA.portable.InputStream in,
                             org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        Date _ob_a0 = DateHelper.read(in);
        setInvitationDate(_ob_a0);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setStatus(org.omg.CORBA.portable.InputStream in,
                     org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        short _ob_a0 = in.read_short();
        setStatus(_ob_a0);
        out = handler.createReply();
        return out;
    }

    private org.omg.CORBA.portable.OutputStream
    _OB_op_setToUser(org.omg.CORBA.portable.InputStream in,
                     org.omg.CORBA.portable.ResponseHandler handler)
    {
        org.omg.CORBA.portable.OutputStream out = null;
        User[] _ob_a0 = seqUserHelper.read(in);
        setToUser(_ob_a0);
        out = handler.createReply();
        return out;
    }
}