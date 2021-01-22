package hiKaricp;

import cn.hutool.core.convert.Convert;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/12/23 0023
 *
 *******************************************************************************/
public class DataSourceGet {

    @Test
    public void  getCon()throws Exception{
        URL url = this.getClass().getClassLoader().getResource("hiKaricp/hikari.properties");
        HikariConfig config = new HikariConfig(URLDecoder.decode(url.getFile(), "utf-8"));
//        DataSource dataSource = new HikariDataSource(config);
        HikariDataSource dataSource = new HikariDataSource();
        Connection connection= dataSource.getConnection();
    }
}
