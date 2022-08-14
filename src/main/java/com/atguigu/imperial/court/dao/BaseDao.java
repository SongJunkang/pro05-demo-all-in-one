package com.atguigu.imperial.court.dao;

/**
 * @author joakims
 * @create 2022-08-10-0:24
 **/

import com.atguigu.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
   BaseDao 类，所有Dao实现类的基类。
    <T>实体类的类型。
 */

public class BaseDao<T> {

    private QueryRunner runner = new QueryRunner();

    public List<T> getListBeans(String sql, Class<T> entityClass,Object... parameters){

        try {
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection,sql,new BeanListHandler<>(entityClass),parameters);

        } catch (SQLException e) {

            e.printStackTrace();
            throw   new RuntimeException(e);

        }
    }

    public T getSingleBean(String sql, Class<T> entityClass,Object... parameters){

        try {
            Connection connection = JDBCUtils.getConnection();

            return runner.query(connection,sql,new BeanHandler<>(entityClass),parameters);
        } catch (SQLException e) {

            e.printStackTrace();
            throw   new RuntimeException(e);

        }


    }


   public int update(String sql, Object... paraments){
       try {
           Connection connection = JDBCUtils.getConnection();
           int affectedRowNumbers  = runner.update(connection,sql,paraments);

           return affectedRowNumbers;

       } catch (SQLException e) {
           e.printStackTrace();
           throw new RuntimeException(e);

       }

   }
}

