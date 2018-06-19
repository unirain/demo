package designpattern.single;

/********************************************************************************
 *
 * Title: 懒汉模式
 *
 * Description: 不会直接创建实例，需要的时候才会创建，但是需要考虑线程安全问题
 *
 * @author chenlm
 * create date on 2018/6/19
 *
 *******************************************************************************/
public class Singleton2 {
    private static ShopCar shopCar = null;

    /**
     * 两个线程进来  会new 两次
     *
     * @return
     */
    public static ShopCar getShopCar1() {
        if (shopCar == null) {
            shopCar = new ShopCar();
        }
        return shopCar;
    }

    /**
     * 虽然可以保证唯一一个，但是如果getshopcar使用次数多，每个线程都要先获取锁，影响效率
     *
     * @return
     */
    public static synchronized ShopCar getShopCar2() {
        if (shopCar == null) {
            shopCar = new ShopCar();
        }
        return shopCar;
    }

    /**
     * 两个线程都运行if判断里面，一个线程得到锁后开始new。释放锁后，另一个线程也获的锁，则new 对象
     * 即创建了两个对象
     *
     * @return
     */
    public static ShopCar getShopCar3() {
        if (shopCar == null) {
            synchronized (Singleton.class) {
                shopCar = new ShopCar();
            }
        }
        return shopCar;
    }

    /**
     * 完美！
     *
     * @return
     */
    public static ShopCar getShopCar4() {
        if (shopCar == null) {
            synchronized (Singleton.class) {
                if (shopCar == null) {
                    shopCar = new ShopCar();
                }
            }
        }
        return shopCar;
    }


}
