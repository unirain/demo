package bloom;

import bloom.net.BloomFileter;
import bloom.net.BloomFilterDemo;
import cn.hutool.bloomfilter.BitMapBloomFilter;
import org.junit.Test;

/********************************************************************************
 *
 * Title:  hutool 布隆过滤器设置
 *
 * Description:
 *
 * @author chenlm
 * create date on 2021/6/18 0018
 *
 *******************************************************************************/
public class HuToolBloomTest {
    @Test
    public void should_bloom(){
        BitMapBloomFilter bitMapBloomFilter=new BitMapBloomFilter(1001);
        boolean ad= bitMapBloomFilter.add("baidu.com");
        boolean cr=bitMapBloomFilter.contains("baidu");
        System.out.println(ad);
        System.out.println(cr);


    }

    @Test
    public void should_bb(){
        BloomFileter fileter=new BloomFileter(100);
        fileter.add("baidu.com");


    }

    @Test
    public void should_kk(){
        BloomFilterDemo.SimpleHash simpleHash=new BloomFilterDemo.SimpleHash(10,50);
        System.out.println(simpleHash.hash("1d"));

    }


}
