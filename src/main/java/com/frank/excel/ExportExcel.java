package com.frank.excel;

import com.alibaba.excel.metadata.Sheet;
import com.frank.excel.model.TableHeaderExcelProperty;
import com.frank.excel.util.ExcelUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 小石潭记
 * @date 2020/10/7 19:34
 * @Description: ${todo}
 */
public class ExportExcel {

    public static void main(String[] args) {
        // 导出excel
        String filePath = "D://测试.xlsx";
        /*List<List<Object>> data = new ArrayList<>();
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        data.add(Arrays.asList("111","222","333"));
        List<String> head = Arrays.asList("表头1", "表头2", "表头3");
        ExcelUtil.writeBySimple(filePath,data,head);*/

        // 导出到单个表 sheet
        /*ArrayList<TableHeaderExcelProperty> data1 = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
            tableHeaderExcelProperty.setName("cmj" + i);
            tableHeaderExcelProperty.setAge(22 + i);
            tableHeaderExcelProperty.setSchool("清华大学" + i);
            data1.add(tableHeaderExcelProperty);
        }

        ExcelUtil.writeWithTemplate(filePath,data1);*/


        // 导出到多个sheet
        ArrayList<ExcelUtil.MultipleSheelPropety> list1 = new ArrayList<>();
        for(int j = 1; j < 4; j++){
            ArrayList<TableHeaderExcelProperty> list = new ArrayList<>();
            for(int i = 0; i < 4; i++){
                TableHeaderExcelProperty tableHeaderExcelProperty = new TableHeaderExcelProperty();
                tableHeaderExcelProperty.setName("cmj" + i);
                tableHeaderExcelProperty.setAge(22 + i);
                tableHeaderExcelProperty.setSchool("清华大学" + i);
                list.add(tableHeaderExcelProperty);
            }

            Sheet sheet = new Sheet(j, 0);
            sheet.setSheetName("sheet" + j);

            ExcelUtil.MultipleSheelPropety multipleSheelPropety = new ExcelUtil.MultipleSheelPropety();
            multipleSheelPropety.setData(list);
            multipleSheelPropety.setSheet(sheet);

            list1.add(multipleSheelPropety);

        }

        ExcelUtil.writeWithMultipleSheel("D://aaa.xlsx",list1);

    }

}
