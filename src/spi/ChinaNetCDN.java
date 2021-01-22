package spi;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2021/1/22 0022
 *
 *******************************************************************************/
public class ChinaNetCDN implements UploadCDN{
    @Override
    public void upload(String url) {
        System.out.println("upload to chinaNet cdn");
    }
}
