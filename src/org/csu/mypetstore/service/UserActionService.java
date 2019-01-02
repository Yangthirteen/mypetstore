package org.csu.mypetstore.service;

import org.csu.mypetstore.persistence.UserActionDAO;
import org.csu.mypetstore.persistence.impl.UserActionDAOImpl;

import java.sql.Date;
import java.sql.Time;

public class UserActionService {

    private UserActionDAO userActionDAO;

    public UserActionService(){
        userActionDAO=new UserActionDAOImpl();
    }

    public void record(String username,String action, String object, String date){
        userActionDAO.recordAction(username,action, object, date);
    }
}
