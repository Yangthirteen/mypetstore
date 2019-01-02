package org.csu.mypetstore.persistence.impl;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    public static final String getProduct="SELECT PRODUCTID,NAME,DESCN as \"description\",CATEGORY as \"categoryId\" FROM PRODUCT WHERE PRODUCTID = ?";
    public static final String getProductList="select PRODUCTID,NAME,DESCN as \"description\",CATEGORY as \"categoryId\" from PRODUCT WHERE lower(name) like ?";
    public static final String getProductListByCategory="SELECT PRODUCTID,NAME,DESCN as \"description\",CATEGORY as \"categoryId\" FROM PRODUCT WHERE CATEGORY = ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> products=new ArrayList<Product>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(getProductListByCategory);
            preparedStatement.setString(1,categoryId);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product=new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                products.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public Product getProduct(String productId) {
        Product product=null;
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(getProduct);
            preparedStatement.setString(1,productId);
            ResultSet resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                product=new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> productList=new ArrayList<Product>();
        try {
            Connection connection= DBUtil.getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(getProductList);
            preparedStatement.setString(1,keywords);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                Product product=new Product();
                product.setProductId(resultSet.getString(1));
                product.setName(resultSet.getString(2));
                product.setDescription(resultSet.getString(3));
                product.setCategoryId(resultSet.getString(4));
                productList.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(preparedStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
