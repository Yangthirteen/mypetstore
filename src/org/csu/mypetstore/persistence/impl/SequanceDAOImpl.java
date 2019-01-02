package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Sequence;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.SequanceDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class SequanceDAOImpl implements SequanceDAO {

    private static final String getSequence="SELECT name, nextid" +
            " FROM SEQUENCE" +
            " WHERE NAME = ?";

    private static final String updateSequence="UPDATE SEQUENCE" +
            " SET NEXTID = ?" +
            " WHERE NAME = ?";


    @Override
    public Sequence getSequence(Sequence sequence) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(getSequence);
            preparedStatement.setString(1,sequence.getName());
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                sequence=new Sequence();
                sequence.setName(resultSet.getString(1));
                sequence.setNextId(resultSet.getInt(2));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return sequence;
    }

    @Override
    public void updateSequence(Sequence sequence) {
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(updateSequence);
            preparedStatement.setString(1,sequence.getName());
            preparedStatement.setInt(2,sequence.getNextId());
            preparedStatement.executeUpdate();

            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
