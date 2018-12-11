package com.example.main.util;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
/**
 * Created by Administrator on 2018/12/10.
 */
@Component
public class CompanyIO {
    //写入公司介绍
    public String WriteDes(String des,int c_id) throws IOException {
        File file=new File("C:/Users/Administrator/company_des");
        if(file.getParentFile().exists()){
            file.mkdir();
        }
        FileOutputStream fileOutputStream=new FileOutputStream(c_id+".txt");
        OutputStreamWriter osw=new OutputStreamWriter(fileOutputStream);
        osw.flush();
        osw.write(des);
        osw.close();
        fileOutputStream.close();
        return "C:/Users/Administrator/company_des/"+c_id+".txt";
    }
    //读取公司介绍
    public StringBuffer ReadDes(String filepath){
        File file=new File(filepath);
        if(file.getParentFile().exists()){
            file.mkdir();
        }
        Reader reader=null;
        StringBuffer sb=new StringBuffer();
        try {
            int count=0;
            reader= new InputStreamReader(new FileInputStream(filepath));
            while ((count=reader.read())!=-1){
                sb.append(count);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
    public String singleFileUpload(MultipartFile file,int id) {
        if (Objects.isNull(file) || file.isEmpty()) {
            return "文件为空!";
        }
        String allName = file.getOriginalFilename();
        System.out.println("上传的文件名： "+allName);
        if(!allName.substring(allName.lastIndexOf(".")).equals(".png")){
            return "文件格式不正确!";
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get("C:/Users/Administrator/" + id+"/"+allName);  //    文件夹 +/+ 文件完整名称  a.txt
            //如果没有files文件夹，则创建
            if (!Files.isWritable(path)) {
                Files.createDirectories(Paths.get("C:/Users/Administrator/" + id));
            }
            //文件写入指定路径
            Files.write(path, bytes);
            System.out.println("存进去的path"+path);
           String paths=String.valueOf(path);
            return paths;
        } catch (IOException e) {
            return "文件上传失败";
        }
    }
    //上传公司图片
    public String UploadImg(MultipartFile[] files, int c_id) {
        String msg;
        String str="";
        int  count=0;

    for (int i=0;i<files.length;i++){
        count++;
            System.out.println("有文件"+i);
            msg=singleFileUpload(files[i],c_id);
            System.out.println(msg);
            str+=msg;
            str+=",";
            if((files.length-count)<0){
               str+=msg;
            }

    }
return str;
    }
}
