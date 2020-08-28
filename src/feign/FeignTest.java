package feign;

import com.fasterxml.jackson.core.util.JsonParserDelegate;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.template.UriTemplate;
import org.junit.Test;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/********************************************************************************
 *
 * Title: 测试
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/8/17
 *
 *******************************************************************************/
public class FeignTest {
    @Test
    public void test() throws Exception {
        QueryService queryService = Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(QueryService.class, "http://localhost:8010");
        Map<String,String> map=queryService.query("chenlm");
        System.out.println(00);


    }

    @Test
    public void test1() throws Exception {

        Type type=Types.resolve(QueryService.class,QueryService.class,QueryService.class.getMethod("query", String.class).getGenericReturnType());
        System.out.println(type);
    }

    @Test
    public void testurltemp() throws Exception {
        UriTemplate uriTemplate=UriTemplate.create("http://example.com/{foo}", StandardCharsets.UTF_8);
        Map<String, Object> params = new HashMap<>();
        params.put("foo", "bar");
        String result=uriTemplate.expand(params);
        System.out.println(result);
    }
}
