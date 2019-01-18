package com.example.main.util;

import com.example.main.model.Experience;
import com.example.main.model.Resume;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;


public class DocUtil {
    private static String propath=System.getProperty("user.dir");
    public static String download(Resume resume) throws IOException, XmlException {
        //Blank Document
        XWPFDocument document= new XWPFDocument();
        File file=new File(propath+"\\src\\main\\resources\\static\\resume\\"+resume.getR_id());
        if(file.getParentFile().exists()){
            file.mkdir();
        }
        //Write the Document in file system
        FileOutputStream out = new FileOutputStream(file.getPath()+"/resume.doc");

        //添加标题
        XWPFParagraph titleParagraph = document.createParagraph();
        //设置段落居中
        titleParagraph.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun titleParagraphRun = titleParagraph.createRun();
        titleParagraphRun.setText("个人简历");
        titleParagraphRun.setColor("000000");
        titleParagraphRun.setFontSize(20);


        //段落
        XWPFParagraph firstParagraph = document.createParagraph();
        XWPFRun run = firstParagraph.createRun();
        run.setText("个人简历");
        run.setColor("696969");
        run.setFontSize(16);

        //设置段落背景颜色
        CTShd cTShd = run.getCTR().addNewRPr().addNewShd();
        cTShd.setVal(STShd.CLEAR);
        cTShd.setFill("97FFFF");


        //换行
        XWPFParagraph paragraph1 = document.createParagraph();
        XWPFRun paragraphRun1 = paragraph1.createRun();
        paragraphRun1.setText("\r");


        //基本信息表格
        XWPFTable infoTable = document.createTable();
        //去表格边框
        infoTable.getCTTbl().getTblPr().unsetTblBorders();


        //列宽自动分割
        CTTblWidth infoTableWidth = infoTable.getCTTbl().addNewTblPr().addNewTblW();
        infoTableWidth.setType(STTblWidth.DXA);
        infoTableWidth.setW(BigInteger.valueOf(9072));


        //表格第一行
        XWPFTableRow infoTableRowOne = infoTable.getRow(0);
        infoTableRowOne.getCell(0).setText("姓名");
        infoTableRowOne.addNewTableCell().setText(": "+resume.getR_name());

        //表格第二行
        XWPFTableRow infoTableRowTwo = infoTable.createRow();
        infoTableRowTwo.getCell(0).setText("工作性质");
        infoTableRowTwo.getCell(1).setText(": "+resume.getR_work_nature());

        //表格第三行
        XWPFTableRow infoTableRowThree = infoTable.createRow();
        infoTableRowThree.getCell(0).setText("期望工作地址");
        infoTableRowThree.getCell(1).setText(": "+resume.getR_work_addr());

        //表格第四行
        XWPFTableRow infoTableRowFour = infoTable.createRow();
        infoTableRowFour.getCell(0).setText("期望工资");
        infoTableRowFour.getCell(1).setText(": "+resume.getR_hope_sal());

        //表格第五行
        XWPFTableRow infoTableRowFive = infoTable.createRow();
        infoTableRowFive.getCell(0).setText("所在行业");
        infoTableRowFive.getCell(1).setText(": "+resume.getR_work_industry());


        XWPFTableRow infoTableRowsix = infoTable.createRow();
        infoTableRowsix.getCell(0).setText("毕业学校");
        infoTableRowsix.getCell(1).setText(": "+resume.getR_edu_school());


        XWPFTableRow infoTableRow7 = infoTable.createRow();
        infoTableRow7.getCell(0).setText("所学专业");
        infoTableRow7.getCell(1).setText(": "+resume.getR_edu_class());

        //表格第五行
        XWPFTableRow infoTableRow8 = infoTable.createRow();
        infoTableRow8.getCell(0).setText("最高学历");
        infoTableRow8.getCell(1).setText(": "+resume.getR_edu_education());

        //表格第五行
        XWPFTableRow infoTableRow9= infoTable.createRow();
        infoTableRow9.getCell(0).setText("在校时间");
        infoTableRow9.getCell(1).setText(": "+resume.getR_edu_startdate()+"-"+resume.getR_edu_overdate());


        //两个表格之间加个换行
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun paragraphRun = paragraph.createRun();
        paragraphRun.setText("\r");



        //工作经历表格
        XWPFTable ComTable = document.createTable();


        //列宽自动分割
        CTTblWidth comTableWidth = ComTable.getCTTbl().addNewTblPr().addNewTblW();
        comTableWidth.setType(STTblWidth.DXA);
        comTableWidth.setW(BigInteger.valueOf(9072));

        //表格第一行
        XWPFTableRow comTableRowOne = ComTable.getRow(0);
        comTableRowOne.getCell(0).setText("开始时间");
        comTableRowOne.addNewTableCell().setText("结束时间");
        comTableRowOne.addNewTableCell().setText("公司名称");
        comTableRowOne.addNewTableCell().setText("工作介绍");

        List<Experience> experiences=resume.getExperiences();
for(Experience e:experiences){
//表格第二行
    XWPFTableRow comTableRowTwo = ComTable.createRow();
    comTableRowTwo.getCell(0).setText(e.getE_date());
    comTableRowTwo.getCell(1).setText(e.getE_date());
    comTableRowTwo.getCell(2).setText(e.getE_comp_name());
    comTableRowTwo.getCell(3).setText(e.getE_comp_position());

}

        CTSectPr sectPr = document.getDocument().getBody().addNewSectPr();
        XWPFHeaderFooterPolicy policy = new XWPFHeaderFooterPolicy(document, sectPr);

        //添加页眉
        CTP ctpHeader = CTP.Factory.newInstance();
        CTR ctrHeader = ctpHeader.addNewR();
        CTText ctHeader = ctrHeader.addNewT();
        String headerText = "快乐招聘";
        ctHeader.setStringValue(headerText);
        XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, document);
        //设置为右对齐
        headerParagraph.setAlignment(ParagraphAlignment.RIGHT);
        XWPFParagraph[] parsHeader = new XWPFParagraph[1];
        parsHeader[0] = headerParagraph;
        policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);


        //添加页脚
        CTP ctpFooter = CTP.Factory.newInstance();
        CTR ctrFooter = ctpFooter.addNewR();
        CTText ctFooter = ctrFooter.addNewT();
        String footerText = "http://www.s4rain.vip";
        ctFooter.setStringValue(footerText);
        XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, document);
        headerParagraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFParagraph[] parsFooter = new XWPFParagraph[1];
        parsFooter[0] = footerParagraph;
        policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);


        document.write(out);
        out.close();
      return   "/src/main/resources/static/resume/"+resume.getR_id()+"/resume.doc";




    }
}
