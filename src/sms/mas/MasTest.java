package sms.mas;

import com.mascloud.model.MoModel;
import com.mascloud.model.StatusReportModel;
import com.mascloud.sdkclient.Client;
import com.mascloud.util.JsonUtil;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2020/9/30
 *
 *******************************************************************************/
public class MasTest {

    @Test
    public void test(){
        Client client = Client.getInstance( );
        // 登录地址需另外提供
        boolean isLoggedin = client.login( "http://112.35.4.197:15000", "yealin", "yealink1105", "厦门亿联网络技术股份有限公司" );
        if( isLoggedin ) {
            System.out.println( "Login successed" );
        } else {
            System.out.println( "Login failed" );
            return;
        }

        // 普通短信
//        int rt = client.sendDSMS( new String[]{ "18106074366" }, "短信内容", "123", 1, "cwaH21lON", null, true );
//        System.out.println( rt );

        // 模板短信
//        int rtm = client.sendTSMS( new String[]{ "18106074366" }, "模板ID", new String[]{ "参数一", "参数二" }, "123", 0, "签名ID", null );
//        System.out.println( rtm );

        // 获取状态报告——开始
        while (true){
            List<StatusReportModel> statusReportlist = client.getReport( );
            System.out.println( "getReport : " + JsonUtil.toJsonString( statusReportlist ) );
            // 获取状态报告——结束

            // 获取上行短信——开始
            List<MoModel> deliverList = client.getMO( );
            System.out.println( "getMO : " + JsonUtil.toJsonString( deliverList ) );

            // 获取上行短信——结束
            try{
                TimeUnit.SECONDS.sleep(5);
            }catch(Exception e){
                e.printStackTrace();

            }

        }





    }
}
