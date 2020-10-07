package com.frank.excel.web;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.frank.excel.listener.ExcelListener;
import com.frank.excel.model.dto.PersonDto;
import com.frank.excel.util.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 小石潭记
 * @date 2020/10/7 20:01
 * @Description: ${todo}
 */
@RestController
public class UploadDownController {
    /**
     * 导入数据
     * @param file
     */
    @PostMapping(value = "upload")
    public void importExcel(@RequestParam("file") MultipartFile file){
        try{
            InputStream inputStream = file.getInputStream();
            //实例化实现了AnalysisEventListener接口的类
            ExcelListener listener = new ExcelListener();
            //传入参数
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLSX, null, listener);
            //读取信息
            excelReader.read(new Sheet(1, 1, PersonDto.class));
            //获取数据
            List<Object> list = listener.getDatas();
            List<PersonDto> lists = new ArrayList<PersonDto>();
            PersonDto catagory = new PersonDto();
            //转换数据类型,并插入到数据库
            for (int i = 0; i < list.size(); i++) {
                catagory = (PersonDto) list.get(i);
                lists.add(catagory);
            }
            System.out.println(JSON.toJSON(lists));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 下载模板
     */
    @PostMapping(value = "/down")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<PersonDto> list = new ArrayList<>();
            ExcelUtil.writeExcel(response, list, "信息", "Sheet1", new PersonDto());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
