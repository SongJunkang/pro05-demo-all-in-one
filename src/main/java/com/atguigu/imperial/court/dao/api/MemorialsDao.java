package com.atguigu.imperial.court.dao.api;

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

/**
 * @author joakims
 * @create 2022-08-10-11:51
 **/
public interface MemorialsDao {
    List<Memorials> selectAllMemorialsDigest();
}
