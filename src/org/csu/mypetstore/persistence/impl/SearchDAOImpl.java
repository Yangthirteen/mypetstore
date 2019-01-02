package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.SearchDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class SearchDAOImpl implements SearchDAO {
    //传入关键字，连接数据库查询
    public String searchMessage(String keyword) {
        //sql语句
        String sql = "SELECT * FROM product WHERE name like '%"+keyword+"%'";
        String rrs = "";
        try {
            Connection conn = DBUtil.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery(sql);
            //循环结果集中的每一条记录
            while (rs.next()) {
                rrs = rrs + "#" + rs.getString("name");
            }
            //去掉第一个#
            /*if (rrs.length() > 0) {
                rrs = rrs.substring(1);
                rrs = rrs.substring(rrs.indexOf("#"));
                rrs = rrs.substring(1);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rrs;
    }
}
	/*public static void main(String[] args) throws SQLException {
		System.out.println(searchMessage("冰红茶"));
	}*/
