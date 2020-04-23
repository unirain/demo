package io;

import org.junit.Test;

import java.io.*;

/********************************************************************************
 *
 * Title: io流
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/12/31
 *
 *******************************************************************************/
public class IoTest {
    private String path="C:\\Users\\chenlm\\Desktop\\in.txt";


    /**
     * 按照字节读取
     * @throws Exception
     */
    @Test
    public  void test1()throws Exception{
        File file=new File(path);
        InputStream fileInputStream=new FileInputStream(file);
        //一次读1024
        byte[] tempbyte = new byte[1024];
        int index;
        //read 读取文件到 一定的数据存储到byte数组中
        while (-1!=(index= fileInputStream.read(tempbyte))){
            System.out.write(tempbyte,0,index);
        }
        //流关闭

    }

    /**
     * 按照字符读取
     * @throws Exception
     */
    @Test
    public  void test3()throws Exception{
        File file=new File(path);
        Reader reader=new FileReader(file);
        int index;
        while ((index=reader.read())!=-1){
            System.out.print((char) index);
        }
    }

    /**
     * 按照字符读取到一个数组中
     * @throws Exception
     */
    @Test
    public  void test5()throws Exception{
        File file=new File(path);
        Reader reader=new FileReader(file);
        char[] chars=new char[1024];
        while (reader.read(chars)!=-1){
            System.out.print(chars);
        }
    }

    /**
     * 字符缓冲读取
     * 按行读取
     * readline 是BufferedReader特有方法
     * @throws Exception
     */
    @Test
    public  void test2()throws Exception{
        File file=new File(path);
        InputStream fileInputStream=new FileInputStream(file);
        //InputStreamReader 是重要的中间桥梁
        BufferedReader reader=new BufferedReader(new InputStreamReader(fileInputStream));
        String result;
        while ((result=reader.readLine())!=null){
            System.out.println(result);
        }
    }

    @Test
    public  void test21()throws Exception{

    }




}
