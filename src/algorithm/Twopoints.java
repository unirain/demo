package algorithm;

/********************************************************************************
 *
 * Title: 二分法查找
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/5/27
 *
 *******************************************************************************/
public class Twopoints {


    /**
     * 有序数组计算
     *
     * @param array
     * @param value
     * @return
     */
    private int BSearch(int[] array, int value) {
        //起始
        int start = 0;
        int end = array.length - 1;
        //查询范围中位数
        int mid;
        //迭代进行二分查找
        while (start <= end) {
            //取得中卫数
            mid = (start + end) / 2;
            if (array[mid] > value) {
                //太大了
                //加1减1是避免死循环特别是算末位的时候
                //什么时候加1 或者减1 看的是数组的顺序
                end = mid - 1;
            } else if (array[mid] == value) {
                return mid;
            } else {
                //太小了
                start = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 旋转数组
     *
     * @param array
     * @param value
     * @return
     */
    private int BSearchwithXZ(int[] array, int value) {
        //
        int start, mid = 0;
        int end = array.length - 1;
        return 0;


    }


}
