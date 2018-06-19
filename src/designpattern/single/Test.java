package designpattern.single;

/********************************************************************************
 *
 * Title: 
 *
 * Description:
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class Test {
    public static void main(String[] args) {
        ShopCar shopCar=Singleton.getShopCar();
        shopCar.buy();
        ShopCar shopCar1=Singleton.getShopCar();
        shopCar1.buy();
        ShopCar shopCar2=Singleton2.getShopCar4();
        shopCar2.buy();

    }
}
