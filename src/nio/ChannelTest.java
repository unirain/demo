package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/********************************************************************************
 *
 * Title: nio 通道
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/12/26
 *
 *******************************************************************************/
public class ChannelTest {

    /**
     * 管道测试
     * FileChannel 自带阻塞，无法设置非阻塞
     * 使用管道做io 写入写出
     */
    @Test
    public void test1() throws Exception{
        //todo https://blog.csdn.net/Michaeles/article/details/85039414
        //todo  selector  https://www.jb51.net/article/173734.html
        //todo selector https://blog.csdn.net/qq_32331073/article/details/81132937
        String inFile = "C:\\Users\\chenlm\\Desktop\\in.txt";
        String outFile = "C:\\Users\\chenlm\\Desktop\\out.txt";

        //获取输入流
        FileInputStream inputStream=null;
        //获取输出流
        FileOutputStream outputStream=null;
        FileChannel inFileChannel = null;
        FileChannel outFileChannel=null;
        try {
            inputStream = new FileInputStream(inFile);
            outputStream = new FileOutputStream(outFile,true);
            //流的管道
            inFileChannel = inputStream.getChannel();
            outFileChannel = outputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            while (true) {
                //先清理
                buffer.clear();
                //读取
                int r = inFileChannel.read(buffer);
                if (r == -1) {
                    break;
                }
                // //将buffer指针指向头部
                buffer.flip();
                outFileChannel.write(buffer);
                System.out.println();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //FileChannel的close方法会自己关闭io流
           if (inFileChannel!=null){
               inFileChannel.close();
               outFileChannel.close();
           }


        }


    }

    /**
     * 第二种写法
     * @throws Exception
     */
    @Test
    public void test2()throws Exception{
        String inFile = "C:\\Users\\chenlm\\Desktop\\in.txt";
        String outFile = "C:\\Users\\chenlm\\Desktop\\out.txt";
        FileInputStream inputStream=null;
        //获取输出流
        FileOutputStream outputStream=null;
        FileChannel inFileChannel = null;
        FileChannel outFileChannel=null;
        try {
            inputStream = new FileInputStream(inFile);
            outputStream = new FileOutputStream(outFile, true);
            //流的管道
            inFileChannel = inputStream.getChannel();
            outFileChannel = outputStream.getChannel();
            inFileChannel.transferTo(0,inFileChannel.size(),outFileChannel);
        }finally {

        }
    }
}
