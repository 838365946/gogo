package com.example.main.util;

import java.io.*;

/**
 * Created by Administrator on 2018/12/10.
 */
public class CompanyDes {
//写入公司介绍
    public String WriteDes(String des,String c_name) throws IOException {
        File file=new File("C:\\Users\\Administrator\\CompanyDes");
        FileOutputStream fileOutputStream=new FileOutputStream(c_name+".txt");
        OutputStreamWriter osw=new OutputStreamWriter(fileOutputStream);
        osw.flush();
        osw.write(des);
        osw.close();
        fileOutputStream.close();
        return file.getPath()+"c_name.txt";
    }
//读取公司介绍
    public StringBuffer ReadDes(String filepath){
        File file=new File(filepath);
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

}
