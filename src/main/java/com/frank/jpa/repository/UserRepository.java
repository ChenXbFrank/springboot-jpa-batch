package com.frank.jpa.repository;

import com.frank.jpa.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 小石潭记
 * @date 2020/10/4 14:15
 * @Description: ${todo}
 * 1. 使用jpa的 CrudRepository 基本查询
 * 2. 使用jpa的 PagingAndSortingRepository 分页查询和排序
 * 3. 使用jpa的 Repository 自定义声明式查询方法
 *
    public interface PersonQueryRepo extends Repository<Person, Long> {

    // declare query method
    // 声明式查询方法

    // 1. count 计数
    long countByName(String name);

    // 2. get/find/stream/query/read 查询
    Person readFirstByAge(int age);

    // 3. delete/remove 删除
     @Transactional
     int deleteById(long id);

     }
 * 4. 使用jpa的 JpaRepository 使用hql、jpql或sql查询，@Query等注解
     public interface PersonHqlDao extends JpaRepository<Person, Long> {

         // 使用hql 或者 jpql 查询
         @Query("from Person where name = ?1 order by id desc")
         List<Person> listByName(String name);

         // 前几种方法中均未介绍update操作，要完成update操作，可使用以下方法
         // 更新时需要加上 @Transactional 和 @Modifying
         @Transactional
         @Modifying // QueryExecutionRequestException: Not supported for DML operations
         @Query("update Person set name=?2 where id=?1")
         int updateNameById(long id, String name);
     }
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    List<User> findByUserNameOrEmail(@Param("userName")String userName, @Param("email")String email);

    User findByUserName(@Param("userName") String userName);

    @Transactional
    @Modifying // QueryExecutionRequestException: Not supported for DML operations
    @Query("update User set user_name=?2 where id=?1")
    int updateUserNameById(long id, @Param("userName")String userName);
}
