package com.atguigu.imperial.court.dao.impl;

import com.atguigu.imperial.court.dao.BaseDao;
import com.atguigu.imperial.court.dao.api.MemorialsDao;
import com.atguigu.imperial.court.entity.Memorials;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author joakims
 * @create 2022-08-10-11:51
 **/
public class MemorialsDaoImpl extends BaseDao<Memorials> implements MemorialsDao {

    @Override
    public List<Memorials> selectAllMemorialsDigest() {

        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       concat(left(memorials_content, 10), \"...\") memorialsContentDigest,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id;";

        return getBeanList(sql, Memorials.class);
    }

    @Override
    public Memorials selectMemorialsById(String memorialsId) {
        String sql = "select memorials_id memorialsId,\n" +
                "       memorials_title memorialsTitle,\n" +
                "       memorials_content memorialsContent,\n" +
                "       emp_name memorialsEmpName,\n" +
                "       memorials_create_time memorialsCreateTime,\n" +
                "       memorials_status memorialsStatus,\n" +
                "       feedback_time feedbackTime,\n" +
                "       feedback_content feedbackContent\n" +
                "from t_memorials m left join  t_emp e on m.memorials_emp=e.emp_id " +
                "where memorials_id = ?;";

        return getSingleBean(sql,Memorials.class,memorialsId);
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsId) {
        String sql = "update t_memorials set memorials_status = 1 where memorialsId = ?";


        //update(sql,Memorials.class,memorialsId);
        update(sql,memorialsId);

    }

    @Override
    public void updateMemorialsFeedBack(String memorialsId, String feedbackContent) {

        String feedback_time = new SimpleDateFormat("yyyy-mm-dd").format(new Date());

        String sql = "update t_memorials set memorials_status = 2,feedback_content = ? " +
                ",feedback_time = ? where memorials_id = ? ";
        update(sql,feedbackContent,feedback_time,memorialsId);

    }
}
