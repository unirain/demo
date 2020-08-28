package designpattern.proxy.jdkproxy.remotehttp;

import java.util.Map;

/********************************************************************************
 *
 * Title: 查询服务
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/8/17
 *
 *******************************************************************************/
public interface CallQueryService {
    /**
     * 远程查询
     * @param name
     * @return
     * @throws Exception
     */
    Map<String,String> queryMap(String name)throws Exception;


}
