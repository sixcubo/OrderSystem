package com.system.servlet;

import java.io.File;

/**
 * @author nanfang
 * @create 2021/01/12/16:42
 */
public class test {
    public static void main(String[] args) {
        String address="F:\\桌面\\OrderSystem\\out\\artifacts\\OrderSystem_war_exploded\\images";
        System.out.println(address);
        File file = new File(address);
        //判断文件是否存在
        if (file.exists() == true){
            System.out.println("图片存在，可执行删除操作");
            Boolean flag = false;
            flag = file.delete();
            if (flag){
                System.out.println("成功删除图片"+file.getName());
            }else {
                System.out.println("删除失败");
            }
        }else {
            System.out.println("图片不存在，终止操作");
        }
    }
}
