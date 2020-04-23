package nio;

import org.junit.Before;
import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

/********************************************************************************
 *
 * Title: selector选择器测试
 *
 * Description:
 * 1.其实io看着操作和nio使用selector选择器写法差别不大，但是要要注意的是，io在如果不使用多线程的情况下，那个acept只能为
 * 一个客户端服务，如果客户端长链接了，但是没有输入数据，则会一直阻塞在那（死循环获取流数据）
 * 2.nio使用了channel+buff+selector的话，他会监听是否有数据（或者有连接），就不会一直被一个客户端阻塞在那，此时指的是读取的时候阻塞，如果读完后程序数据逻辑处理慢，那就不是nio的问题
 * 所以nio 比io更快 更节约资源
 *
 * @author chenlm
 * create date on 2020/1/7
 *
 *******************************************************************************/
public class ServerTest {
    //打开一个选择器实例
    private Selector selector;

    private Charset charset = Charset.forName("UTF-8");

    @Before
    public void before() throws Exception {
        selector = Selector.open();
    }


    /**
     * 客户端会触发连接 +读
     *
     * @throws Exception
     */
    @Test
    public void server() throws Exception {
        selector = Selector.open();
        //打开一个服务端通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //注册到选择器上,非阻塞
        serverSocketChannel.configureBlocking(false);
        //渠道绑定ip
        serverSocketChannel.bind(new InetSocketAddress("127.0.0.1", 32299));
        //监听渠道的接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //总线选择器在监听
        while (selector.select() > 0) {
            //遍历集合之前注册（注册后会有selectkey）的selectkey感兴趣的事件
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                if (key.isAcceptable()) {
                    System.out.println("isAcceptable");
                    //找出客户端，然后绑定到选择器上，让选择器去监听客户端的通道的读事件
                    SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
                    //没配置非阻塞则会一直触发事件，控制栏会一直打印出来isAcceptable
                    clientChannel.configureBlocking(false);
                    //对SocketChannel的读取感兴趣
                    //再次注册，select集合中就会有
                    clientChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isConnectable()) {
                    System.out.println("连接");
                } else if (key.isReadable()) {
                    //读取
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    clientChannel.read(byteBuffer);
                    System.out.println(new String(byteBuffer.array(),0,byteBuffer.position()));
                } else if (key.isWritable()) {
                    System.out.println("isWritable");
                }
                //注意每次迭代末尾的keyIterator.remove()调用。
                // Selector不会自己从已选择键集中移除SelectionKey实例。必须在处理完通道时自己移除。
                // 下次该通道变成就绪时，Selector会再次将其放入已选择键集中。
                keys.remove();

            }


        }


    }

    /**
     * 这个由于scan在test运行的原因，无法获取控制台输入的数据
     *
     * @throws Exception
     */
    @Test
    public void client() throws Exception {
        SocketChannel sc = SocketChannel.open(new InetSocketAddress("127.0.0.1", 32299));
        //设置该sc以非阻塞方式工作
        sc.configureBlocking(false);
        //创建键盘输入流
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextLine()) {
            //读取键盘输入
            String line = scan.nextLine();
            //将键盘输入的内容输出到SocketChannel中
            sc.write(Charset.forName("UTF-8").encode(line));
        }
    }


}
