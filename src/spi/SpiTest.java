package spi;

import cn.hutool.core.util.ServiceLoaderUtil;
import org.junit.Test;

import java.util.ServiceLoader;

/********************************************************************************
 *
 * Title: spi
 *
 * Description: 不需要改动源码就可以实现扩展，解耦。
 * 实现扩展对原来的代码几乎没有侵入性。
 * 只需要添加配置就可以实现扩展，符合开闭原则。
 *
 * services中文件名要为接口的全路径名，文件内容放实现
 *
 * spi -可自定义实现
 *
 * @author chenlm
 * create date on 2021/1/22 0022
 *
 *******************************************************************************/
public class SpiTest {
    @Test
    public void should_sp(){
        ServiceLoader<UploadCDN> loader= ServiceLoaderUtil.load(UploadCDN.class);
        loader.forEach(u->u.upload("d"));

    }
}
