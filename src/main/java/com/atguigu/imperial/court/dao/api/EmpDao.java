package com.atguigu.imperial.court.dao.api;

import com.atguigu.imperial.court.entity.Emp;

/**
 * @author joakims
 * @create 2022-08-10-11:47
 **/
public interface EmpDao {

    Emp selectEmpByLoginAccount(String loginAccount, String encodeloginPassword);
}
