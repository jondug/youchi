package com.youchi.tool;

import com.youchi.mapper.UserMapper;
import com.youchi.model.User;
import com.youchi.responseMessage.ResponseMessage;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("userUpload")
public class SQLTool {

    @Autowired
    private UserMapper userMapper;

    /**
     * 查询User表中所有的数据
     *
     * @return
     */
    public List getAllByDb() {

        List list = new ArrayList();
//        try {
        User user = new User();
        final List<User> users = userMapper.selectList(null);
        return users;
    }

    /**
     * excel导入User
     *
     * @param file 文件
     * @return
     */
    @PostMapping("excelToSql")
    public ResponseMessage getAllByExcel(MultipartFile file) {
        final String substring = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
        if (!substring.equals(".xls")) {
            return ResponseMessage.error("格式不正确");
        }
        final File file1 = new File(file.getOriginalFilename());
        //MultipartFile 转 File
        try (InputStream in = file.getInputStream(); OutputStream os = new FileOutputStream(file1)) {
            // 得到文件流。以文件流的方式输出到新文件
            // 可以使用byte[] ss = multipartFile.getBytes();代替while
            byte[] buffer = new byte[4096];
            int n;
            while ((n = in.read(buffer, 0, 4096)) != -1) {
                os.write(buffer, 0, n);
            }
            // 读取文件第一行
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
            // 输出路径
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Workbook rwb = Workbook.getWorkbook(file1);
            Sheet rs = rwb.getSheet(0);//或者rwb.getSheet(0)
            int clos = rs.getColumns();//得到所有的列
            int rows = rs.getRows();//得到所有的行
            Field[] fields = User.class.getDeclaredFields();

            User user = new User();
            for (int i = 1; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    for (int k = 0; k < fields.length; k++) {
                        Field field = fields[k];
                        String fieldName = field.getName();
                        if (fieldName.equals(rs.getCell(j, 0).getContents())) {
                            Class clazz = user.getClass();
                            Method m2 = clazz.getDeclaredMethod("set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1), String.class);
                            m2.invoke(user, rs.getCell(j, i).getContents());
                        }
                    }
                }
                System.out.println(user);
                userMapper.insert(user);
                user = new User();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ResponseMessage.success("ok");

    }



    @GetMapping("sqlToExcel")
    public ResponseMessage sqlToExcel() throws IOException {

        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet("User");
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 20);
        // 产生表格标题行
        XSSFRow row = sheet.createRow(0);
        final Field[] headers = User.class.getDeclaredFields();
        final List<User> users = userMapper.selectList(null);
        for (short i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell(i);
            XSSFRichTextString text = new XSSFRichTextString(headers[i].getName());
            cell.setCellValue(text);
        }
        try {
            // 遍历集合数据，产生数据行 iterator返回一个迭代器
            Iterator<User> it = users.iterator();
            int index = 0;
            //Returns true if the iteration has more elements.
            while (it.hasNext()) {
                index++;
                row = sheet.createRow(index);
                User t = (User) it.next();//it.next()返回迭代的下一个元素
                // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
                Field[] fields = t.getClass().getDeclaredFields();
                for (short i = 0; i < fields.length; i++) {
                    XSSFCell cell = row.createCell(i);
                    Field field = fields[i];
                    String fieldName = field.getName();
                    String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});
//                    System.out.println(value);
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    // 其它数据类型都当作字符串简单处理
                    if (value != null && value != "") {
                        textValue = value.toString();
                    }
                    if (textValue != null) {
                        XSSFRichTextString richString = new XSSFRichTextString(textValue);
                        cell.setCellValue(richString);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
            String fileName = "user";

            String path = "D:\\file\\uploads\\";
//            String Dir = request.getServletContext().getRealPath(path);
            //判断文件夹是否存在
            File parent = new File(path + fileName);
            if (!parent.exists()) {
                parent.mkdirs();
            }
            File file = new File(parent, fileName+".xlsx");
            OutputStream os = new FileOutputStream(file);
            workbook.write(os);
            os.flush();
            os.close();
        }
        return ResponseMessage.success("ok");
    }


    /**
     * 通过Id判断是否存在
     *
     * @param id
     * @return
     */
    public boolean isExist(int id) {
        try {
            ResultSet rs = (ResultSet) userMapper.selectById(id);
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;
    }

    @GetMapping("user")
    public List getUser() {
        return userMapper.selectList(null);
    }

}
