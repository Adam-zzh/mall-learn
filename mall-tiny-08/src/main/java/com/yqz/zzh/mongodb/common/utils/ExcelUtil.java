package com.yqz.zzh.mongodb.common.utils;

import com.yqz.zzh.mongodb.annotation.ExcelField;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zzh
 * @Description
 * @date 2020-09-23 19:30
 */
@Slf4j
@Component
public class ExcelUtil {

    //列名
    public static final String FIELDNAME = "fieldName";
    //列中文名
    public static final String FIELDZNAME = "fieldZName";
    //排列顺序
    public static final String FIELDORDER = "order";

    /**
     * 生成workbook，并进行数据处理
     * @param list 数据
     * @param clazz 类
     * @return
     */
    public static <T> Workbook dealWorkbook(List<T> list,Class clazz) {
        //创建一个excel2003的WorkBook,对应一个Excel文件
        Workbook workbook = new HSSFWorkbook();
        List<Map<String , String>> fieldList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:
        fields) {
            ExcelField annotation = field.getAnnotation(ExcelField.class);
            if (annotation!=null){
                Map<String,String> map = new HashMap<>();
                map.put(FIELDZNAME,annotation.description());
                map.put(FIELDNAME,field.getName());
                map.put(FIELDORDER,String.valueOf(annotation.order()));
                fieldList.add(map);
            }
        }
        try {
            //在Workbook中，创建一个sheet,名为test，对应Excel中的工作薄（sheet）
            Sheet sheet = workbook.createSheet("test");
            dealSheetData(sheet , list , fieldList);
        } catch (Exception e) {
            log.info("导出Excel失败！");
            log.error(e.getMessage());
        }
        return workbook;
    }


    /**
     * 处理sheet的数据内容
     * @param sheet sheet内容
     * @param list 数据
     * @param fieldList 列名
     */
    private static <T> void dealSheetData(Sheet sheet, List<T> list, List<Map<String, String>> fieldList) {
        //在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        Row row = sheet.createRow( 0);
        // 填充表头
        for(int i = 0; i < fieldList.size();i++) {
            Map<String, String> stringStringMap = fieldList.get(i);
            int cellNum = Integer.valueOf(stringStringMap.get(FIELDORDER));
            Cell cell = row.createCell(cellNum);
            Map<String, String> fields = fieldList.get(i);
            cell.setCellValue(fields.get(FIELDZNAME));
            sheet.autoSizeColumn(cellNum);
        }

        // 填充内容
        for (int index = 0; index < list.size(); index++) {
            row = sheet.createRow(index + 1);
            T item = list.get(index);
            for (int i = 0; i < fieldList.size(); i++) {
                Map<String, String> fields = fieldList.get(i);
                Object objValue = getItemFieldValue(fields.get(FIELDNAME) , item);
                String fieldValue = objValue == null ? "" : objValue.toString();
                row.createCell(Integer.valueOf(fields.get(FIELDORDER))).setCellValue(fieldValue);
            }
        }
    }

    /**
     * 得到属性的值
     * @param fieldName
     * @param item
     * @return
     */
    private static <T> Object getItemFieldValue(String fieldName, T item) {
        try {
            Field field = item.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            //属性的值
            return  field.get(item);
        } catch (Exception e) {
            log.error("field {} is error" , fieldName , e);
            return null;
        }

    }

    /*
      *@Description 测试合并单元格
      *@author zzh
      *@Param
      *@return
      **/
    public static <T> Workbook dealWorkbook1(List<T> list, Class clazz) {
        //创建一个excel2003的WorkBook,对应一个Excel文件
        Workbook workbook = new HSSFWorkbook();
        List<Map<String , String>> fieldList = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field:
                fields) {
            ExcelField annotation = field.getAnnotation(ExcelField.class);
            if (annotation!=null){
                Map<String,String> map = new HashMap<>();
                map.put(FIELDZNAME,annotation.description());
                map.put(FIELDNAME,field.getName());
                map.put(FIELDORDER,String.valueOf(annotation.order()));
                fieldList.add(map);
            }
        }
        try {
            //在Workbook中，创建一个sheet,名为test，对应Excel中的工作薄（sheet）
            Sheet sheet = workbook.createSheet("test");
            dealSheetData1(sheet , list , fieldList);
        } catch (Exception e) {
            log.info("导出Excel失败！");
            log.error(e.getMessage());
        }
        return workbook;
    }

    /*
      *@Description 合并单元格
      *@author zzh
      *@Param
      *@return
      **/
    private static <T> void dealSheetData1(Sheet sheet, List<T> list, List<Map<String, String>> fieldList) {
        //在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        Row row = sheet.createRow(0);
        Row row1 = sheet.createRow(1);
        for (int i = 0; i < fieldList.size();i++){
            row.createCell(i+1);
            row1.createCell(i+1);
        }

        CellRangeAddress region = new CellRangeAddress(0, 1, 1, 1);
        CellRangeAddress region1 = new CellRangeAddress(0, 1, 2, 2);
        CellRangeAddress region2 = new CellRangeAddress(0, 0, 3, 4);
        sheet.addMergedRegion(region);
        sheet.addMergedRegion(region1);
        sheet.addMergedRegion(region2);

        row.getCell(3).setCellValue("产品");
        // 填充表头
        for(int i = 0; i < fieldList.size();i++) {
            Map<String, String> stringStringMap = fieldList.get(i);
            int cellNum = Integer.valueOf(stringStringMap.get(FIELDORDER));
            if (cellNum<=2){
                row.getCell(cellNum).setCellValue(stringStringMap.get(FIELDZNAME));
            }else{
                row1.getCell(cellNum).setCellValue(stringStringMap.get(FIELDZNAME));
            }
            sheet.autoSizeColumn(cellNum);
        }

        // 填充内容
        for (int index = 0; index < list.size(); index++) {
            row = sheet.createRow(index + 2);
            T item = list.get(index);
            for (int i = 0; i < fieldList.size(); i++) {
                Map<String, String> fields = fieldList.get(i);
                Object objValue = getItemFieldValue(fields.get(FIELDNAME) , item);
                String fieldValue = objValue == null ? "" : objValue.toString();
                row.createCell(Integer.valueOf(fields.get(FIELDORDER))).setCellValue(fieldValue);
            }
        }
    }
}
