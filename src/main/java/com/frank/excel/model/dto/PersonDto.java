package com.frank.excel.model.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author 小石潭记
 * @date 2020/10/7 21:10
 * @Description: ${todo}
 */
@Data
public class PersonDto extends BaseRowModel {

    /** id */
    @ExcelProperty(index = 0 , value = "id")
    private String id;
    /** 姓名 **/
    @ExcelProperty(index = 1 , value = "姓名")
    private String name;
    /** 生日 **/
    @ExcelProperty(index = 2 , value = "生日" , format = "yyyy-MM-dd")
    private String birth;

}
