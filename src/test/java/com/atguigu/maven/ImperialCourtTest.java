package com.atguigu.maven;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.util.JDBCUtils;

import java.sql.Connection;
import java.util.List;

import org.junit.Test;

/**
 * @author joakims
 * @create 2022-08-07-14:51
 **/
public class ImperialCourtTest {

    private BaseDao<Emp> baseDao = new BaseDao<>();


    @Test
    public void testGetSingleBean(){

        String sql = "select emp_id empId ,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp where emp_id = ?;";
        Emp emp= baseDao.getSingleBean(sql, Emp.class, 1);
        System.out.println("emp " + emp);
    }

    @Test
    public void testGetListBeans(){
        String sql = "select emp_id empId ,emp_name empName,emp_position empPosition,login_account loginAccount,login_password loginPassword from t_emp;";
     
        List <Emp> empList = baseDao.getBeanList(sql,Emp.class);
        for (Emp emp :
                empList) {
            System.out.println("emp = " + emp);
        }
        
    }

    @Test
    public void testGetConnection() {

        Connection connection = JDBCUtils.getConnection();
        System.out.println("connection = " + connection);

        JDBCUtils.releaseConnection(connection);

    }
    @Test
    public void testSubString(){
        String substring  = "aaa.png".substring("aaa.png".lastIndexOf("."));
        System.out.println("substring = "+ substring);
    }



}