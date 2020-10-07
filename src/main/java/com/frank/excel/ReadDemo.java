package com.frank.excel;

import com.alibaba.excel.metadata.Sheet;
import com.frank.excel.model.ModelInfo;
import com.frank.excel.util.ExcelUtil;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 小石潭记
 * @date 2020/10/7 16:29
 * @Description: ${todo}
 */
public class ReadDemo {

    public static void main(String[] args) {
        String filePath = "D://学生表少.xlsx";
        // 默认读取
        // 读取Sheet1的全部数据
        List<Object> objects = ExcelUtil.readLessThan1000Row(filePath);
        System.out.println(objects);

        // 获取Sheet1表头以下的信息
        //第一个1代表sheet1, 第二个1代表从第几行开始读取数据，行号最小值为0
        Sheet sheet = new Sheet(1, 1);
        List<Object> objects1 = ExcelUtil.readLessThan1000RowBySheet(filePath,sheet);
        List<ModelInfo> result = new ArrayList<>();
        for (int i = 0; i < objects1.size(); i++) {
            ModelInfo modelInfo = new ModelInfo();
            List<String> o = (List) objects1.get(i);
            modelInfo.setName(o.get(0));
            modelInfo.setAge(Integer.parseInt(o.get(1)));
            modelInfo.setSchool(o.get(2));
            result.add(modelInfo);
        }
        System.out.println(result);

        // 获取Sheet2的所有信息, 后面的1是不读取第一行的数据
        Sheet sheet2 = new Sheet(2, 1);
        List<Object> objects2 = ExcelUtil.readLessThan1000RowBySheet(filePath, sheet2);
        System.out.println(objects2);


        // 读取大于1000行的数据
        String filePath1 = "D://学生表多.xlsx";
        List<Object> objects3 = ExcelUtil.readMoreThan1000Row(filePath1);
        System.out.println(objects3);

        // 指定读取 读取sheet1 从第三行开始读取
        Sheet sheet3 = new Sheet(1, 2);
        List<Object> objects4 = ExcelUtil.readMoreThan1000RowBySheet(filePath1, sheet3);
        System.out.println(objects4);


    }

}
