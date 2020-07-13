package guava;

import com.google.common.collect.*;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/********************************************************************************
 *
 * Title: 谷歌包测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/6/17
 *
 *******************************************************************************/
public class GuavaTest {

    /**
     *
     * 集合的创建
     * @throws Exception
     */
    @Test
    public void test1()throws Exception{
        //普通集合创建
        List<String> list= Lists.newArrayList("333","33");
        Set<String> set= Sets.newHashSet();
        Map<String,String> map= Maps.newConcurrentMap();
        //创建定长的集合
        ImmutableList<String> iList = ImmutableList.of("a", "b", "c");


    }

    /**
     *
     *
     * 创建特殊map  key为string，value为集合类型
     * @throws Exception
     */
    public void test()throws Exception{
        Multimap<String,Integer> multimap =ArrayListMultimap.create();
        multimap.put("dd",1);
        multimap.put("dd",2);
        multimap.get("dd").forEach(System.out::println);

    }

    @Test
    public void test11()throws Exception{


    }

}
