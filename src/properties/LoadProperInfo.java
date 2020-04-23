package properties;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Properties;

/********************************************************************************
 *
 * Title: 配置文件的使用
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/4/10
 *
 *******************************************************************************/
public class LoadProperInfo {

    public Properties getInfo() {
        Properties properties = new Properties();
        try {
            //获取文件输入流
            //获取url路径
            URL url = this.getClass().getClassLoader().getResource("properties/test.properties");
            InputStream inputStream = new FileInputStream(URLDecoder.decode(url.getFile(), "utf-8"));
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    @Test
    public void test1()throws Exception{
        Properties properties=  getInfo();
        System.out.println(properties.get("username"));

    }

}
