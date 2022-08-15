package com.atguigu.imperial.court.service.impl;

import com.atguigu.imperial.court.dao.api.MemorialsDao;
import com.atguigu.imperial.court.dao.impl.MemorialsDaoImpl;
import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.service.api.MemorialsService;

import java.util.List;

public class MemorialsServiceImpl implements MemorialsService {
    //多态的实现方式，接口的类型的变量指向实现类的类型。
    //也符合面向接口编程的思路，只要接口不变，实现类爱怎么变就怎么变。
    private MemorialsDao memorialsDao = new MemorialsDaoImpl();


    @Override
    public List<Memorials> getAllMemorialsDigest() {


        return memorialsDao.selectAllMemorialsDigest();
    }

    @Override
    public Memorials getMemorialsDetailById(String memorialsId) {
        //memorialsDao.s


        return null;
    }
}
