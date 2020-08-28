package feign;

import java.util.Map;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/8/17
 *
 *******************************************************************************/
public interface QueryService {

    @RequestLine("GET /remote-test?name={name}")
    Map<String,String> query(@Param("name") String name)throws Exception;
}
