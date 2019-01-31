package designpattern.Template;

/********************************************************************************
 *
 * Title: 做饭模板
 *
 * Description:模板方法
 *
 * @author chenlm
 * create date on 2019/1/31
 *
 *******************************************************************************/
public abstract class CookTemplate {

    /**
     * 总步骤不允许重写
     */
    public final void cookFoods() {
        this.pourOil();
        this.heatOil();
        this.pourVegetable();
        this.pourSauce();
        this.fry();

    }

    /**
     * 倒油
     */
    public void pourOil() {
        System.out.println("倒油");
    }

    /**
     * 热油
     */
    public void heatOil() {
        System.out.println("热油");
    }

    /**
     * 放菜
     */
    public abstract void pourVegetable();

    /**
     * 倒调味料
     */
    public abstract void pourSauce();

    /**
     * 翻炒
     */
    public void fry() {
        System.out.println("翻炒");

    }


}
