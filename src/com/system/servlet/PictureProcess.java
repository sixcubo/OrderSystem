package com.system.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author nanfang
 * @create 2021/01/12/14:09
 * @declare 图片处理
 */
public class PictureProcess {
    /*
    * 上传图片
    * */
    public static boolean upload(HttpServletRequest request, HttpServletResponse response){
        FileItemFactory factory = new DiskFileItemFactory();
        // 文件上传处理器
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析请求信息
        List items = null;
        try {
            items = upload.parseRequest(request);
        }
        catch (FileUploadException e) {
            e.printStackTrace();
        }

        // 对请求信息进行判断
        Iterator iter = items.iterator();
        while (iter.hasNext()) {
            FileItem item = (FileItem) iter.next();
            // 信息为普通的格式
            if (item.isFormField()) {
                String fieldName = item.getFieldName();
                String value = item.getString();
                request.setAttribute(fieldName, value);
            }
            // 信息为文件格式
            else {
                Date now = new Date();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy_MM_dd_hh_ss_mm");
                String pubtime = sf.format(now);
                String fileName = item.getName();//获得上传图片的名称
                int index = fileName.lastIndexOf("\\");
                fileName = fileName.substring(index + 1);
                fileName=pubtime+fileName;
                request.setAttribute("realFileName", fileName);
                String basePath =  request.getSession().getServletContext().getRealPath("/images");
                System.out.println(basePath);//打印当前位置
                System.out.println(fileName);
                File file = new File(basePath, fileName);
                try {
                    item.write(file);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }
    public static boolean deletePicture(HttpServletRequest request,HttpServletResponse response){
        //删除指定位置的本地图片(删除成功返回true,失败返回false)
        String address=request.getSession().getServletContext().getRealPath("/images")+"\\"+request.getParameter("pictureName");
        System.out.println(address);
        System.out.println(new File(address).delete());
        return false;
    }
}
