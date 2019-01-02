package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.UserActionDAO;

import java.sql.*;

public class UserActionDAOImpl implements UserActionDAO {

    private static final String recordAction="INSERT INTO USERACTION (USERNAME, ACTION, DATE) VALUES (?, ?, ?)";

    @Override
    public void recordAction(String username,String action, String object, String date) {
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(recordAction);
            String a=action(action,object);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,a);
            preparedStatement.setString(3, date);

            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String action(String action, String object) {
        String a=action+object;
        return  a;
    }
}
