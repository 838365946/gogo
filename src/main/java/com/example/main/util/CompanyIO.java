package com.example.main.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;

/**
 * Created by Administrator on 2018/12/10.
 */
@Component
public class CompanyIO {
//写入公司介绍
    public String WriteDes(String des,int c_id) throws IOException {
        File file=new File("C:/Users/Administrator/CompanyIO");
       if(file.getParentFile().exists()){
           file.mkdir();
       }
        FileOutputStream fileOutputStream=new FileOutputStream(c_id+".txt");
        OutputStreamWriter osw=new OutputStreamWriter(fileOutputStream);
        osw.flush();
        osw.write(des);
        osw.close();
        fileOutputStream.close();
        return file.getPath()+"/"+c_id+".txt";
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
//上传公司图片
public StringBuffer UploadImg(HttpServletRequest request, int c_id) {
    String filename=null;
    StringBuffer sb =new StringBuffer();
    List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("fileName");
    if (files.isEmpty()) {
        System.out.println("list没有文件");
    }
    String path = "C:/Users/Administrator/" + c_id;

    for (MultipartFile file : files) {
        int count=1;
         filename = file.getOriginalFilename();
        int size = (int) file.getSize();
        if (file.isEmpty()) {
            System.out.println("没有文件");
        } else {
            File img = new File(path + '/' + filename);
            if ((!img.getParentFile().exists())) {
                img.getParentFile().mkdir();
            }
            try {
                file.transferTo(img);
                if(files.size()-count>0){
                    sb.append(path+'/'+filename+",");
                }else{
                    sb.append(path+'/'+filename);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("没有文件");
            } catch (IllegalStateException e) {
                System.out.println("没有文件");
            }
        }
    }
    return sb;

}




}
