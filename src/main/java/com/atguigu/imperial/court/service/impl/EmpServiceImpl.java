package com.atguigu.imperial.court.service.impl;

import com.atguigu.imperial.court.dao.api.EmpDao;
import com.atguigu.imperial.court.dao.impl.EmpDaoImpl;
import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.exception.LoginFailedException;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.util.ImperialCourtConst;
import com.atguigu.imperial.court.util.MD5Util;

public class EmpServiceImpl implements EmpService {
    private EmpDao empDao = new EmpDaoImpl();

    @Override
    public Emp getEmpByLoginAccount(String loginAccount, String loginPassword) {

        //1 对密码进行加密。
        String encodeloginPassword = MD5Util.encode(loginPassword);
        //2 根据账户和加密密码查询数据库
        Emp emp = empDao.selectEmpByLoginAccount(loginAccount,encodeloginPassword);

        //3 检查Emp对象是否为NULl，1->不为null，返回Emp，2>为null,则抛出登录失败  异常。
        if (emp != null) {
            return emp;

        }else {
            throw new LoginFailedException(ImperialCourtConst.LOGIN_FAILED_MESSAGE);

        }

    }
}
