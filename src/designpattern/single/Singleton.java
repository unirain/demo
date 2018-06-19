package designpattern.single;

/********************************************************************************
 *
 * Title: 单利模式
 *
 * Description: 恶汉模式---直接初始化实例
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class Singleton {
    private static final ShopCar SHOP_CAR=new ShopCar();

    public  static ShopCar getShopCar(){
        return SHOP_CAR;
    }
}
