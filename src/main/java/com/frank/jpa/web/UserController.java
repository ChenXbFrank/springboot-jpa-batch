package com.frank.jpa.web;

import com.frank.jpa.entity.User;
import com.frank.jpa.repository.UserRepository;
import com.frank.jpa.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 小石潭记
 * @date 2020/10/4 14:25
 * @Description: ${todo}
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService service;

    @GetMapping("/save")
    public void save(){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            User user = new User();
            user.setUserName("小石潭记" + i);
            list.add(user);
        }
        long start = System.currentTimeMillis();
        log.info("开始保存", start);
        // 500 条数据花费9s
        repository.saveAll(list);
        long end = System.currentTimeMillis();
        log.info("耗时{}s", (end-start) / 1000);
    }

    @GetMapping("/saveAll")
    public void saveAll(){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 500; i++) {
            User user = new User();
            user.setUserName("frank" + i);
            list.add(user);
        }
        long start = System.currentTimeMillis();
        log.info("开始保存", start);
        // 500 条数据花费13s  yml开启批量操作9s
        service.batchInsert(list);
        long end = System.currentTimeMillis();
        log.info("耗时{}s", (end-start) / 1000);
    }

    /**
     *  使用原生的jdbcTemplate批量插入数据  速度比上面的两个都快
     */
    @GetMapping("/saveBatch")
    public void saveBatch(){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            User user = new User();
            user.setUserName("frank" + i);
            user.setEmail(i + "qq.com");
            user.setNickName("小石潭记" + i);
            user.setPassWord("password" + i);
            list.add(user);
        }
        long start = System.currentTimeMillis();
        log.info("开始保存", start);
        service.saveBatch(list);
        long end = System.currentTimeMillis();
        log.info("耗时{}", end-start);
    }

    @GetMapping("/user-info")
    public List<User> getUserInfo(String userName, String email) {
        User user = repository.findByUserName(userName);
        log.info("查询的user,{}", user);
        List<User> byUserNameOrEmail = repository.findByUserNameOrEmail(userName, email);
        log.info("查询的byUserNameOrEmail,{}", byUserNameOrEmail);
        Iterable<User> iterable = repository.findAll();
        List<User> list = new ArrayList<>();
        iterable.forEach(single->{list.add(single);});
        return list;
    }

    @GetMapping("/update-user")
    public int updateUserName(String id, String userName) {
        long userId = Long.parseLong(id);
        return repository.updateUserNameById(userId, userName);
    }

}
