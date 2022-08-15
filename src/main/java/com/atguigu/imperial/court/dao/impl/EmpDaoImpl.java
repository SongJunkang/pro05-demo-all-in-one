package com.atguigu.imperial.court.dao.impl;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.dao.api.EmpDao;
import com.atguigu.imperial.court.entity.Emp;

/**
 * @author joakims
 * @create 2022-08-10-11:48
 **/
public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao {

    @Override
    public Emp selectEmpByLoginAccount(String loginAccount, String encodeloginPassword) {
        //1 编写sql语句。
        String sql = "select emp_id empId ," +
                "emp_name empName,emp_position empPosition," +
                "login_account loginAccount," +
                "login_password loginPassword " +
                "from t_emp where login_account = ? and login_password = ?;";

        // 2，调用父类的方法查询单个对象。
        return  super.getSingleBean(sql, Emp.class,loginAccount,encodeloginPassword);

    }
}
