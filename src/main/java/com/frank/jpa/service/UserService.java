package com.frank.jpa.service;

import com.alibaba.fastjson.JSON;
import com.frank.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author 小石潭记
 * @date 2020/10/4 17:03
 * @Description: ${todo}
 */
@Service
@Transactional
@Slf4j
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 批量插入
     *
     * @param list 实体类集合
     * @param <T>  表对应的实体类
     */
    public <T> void batchInsert(List<T> list) {
        if (!ObjectUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                entityManager.persist(list.get(i));
                if (i % 50 == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            entityManager.flush();
            entityManager.clear();
        }
    }

    /**
     * 批量更新
     *
     * @param list 实体类集合
     * @param <T>  表对应的实体类
     */
    public <T> void batchUpdate(List<T> list) {
        if (!ObjectUtils.isEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                entityManager.merge(list.get(i));
                if (i % 50 == 0) {
                    entityManager.flush();
                    entityManager.clear();
                }
            }
            entityManager.flush();
            entityManager.clear();
        }
    }

    public void saveBatch(List<User> list) {
        /*String sql="insert into user " +
                "(user_name)" +
                " values (?)";

        List<Object[]> batchArgs=new ArrayList<Object[]>();

        for (int i = 0; i < list.size(); i++) {
            batchArgs.add(new Object[]{list.get(i)});
        }
        jdbcTemplate.batchUpdate(sql, batchArgs);*/


        StringBuilder insert = new StringBuilder("INSERT INTO `user` (`user_name`, `pass_word`, `nick_name`," +
                "`email`,`reg_time`) VALUES ");
        for (int i = 0; i < list.size(); i++) {
            insert.append("(")
                    .append("'")
                    .append(list.get(i).getUserName())
                    .append("'")
                    .append(",")
                    .append("'")
                    .append(list.get(i).getPassWord())
                    .append("'")
                    .append(",")
                    .append("'")
                    .append(list.get(i).getNickName())
                    .append("'")
                    .append(",")
                    .append("'")
                    .append(list.get(i).getEmail())
                    .append("'")
                    .append(",")
                    .append("'")
                    .append(list.get(i).getRegTime())
                    .append("'")
                    .append(")");
            if (i < list.size() - 1) {
                insert.append(",");
            }
        }
        String sql = (String) JSON.toJSON(insert);
        log.info("SQL语句:{}", JSON.toJSON(insert));
        try {
            jdbcTemplate.execute(sql);
        } catch (Exception e) {
            log.error("sql解析错误", e.getMessage());
        }

    }
}
