package com.frank.excel.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author 小石潭记
 * @date 2020/10/7 20:31
 * @Description: ${todo}
 */
@Data
@Entity(name = "t_user")
public class User extends BaseRowModel {

    @Id
    @ExcelProperty(value = "ID", index = 0)
    private String id;

    @ExcelProperty(value = "姓名", index = 1)
    private String name;

    @ExcelProperty(value = "年龄", index = 2)
    private Integer age;

}
