package sort;

import org.junit.Test;

/********************************************************************************
 *
 * Title: 排序算法
 *
 * Description:
 *
 * @author chenlm
 * create date on 2019/2/22
 *
 *******************************************************************************/
public class SortTest {
    /**
     * 冒泡算法
     * 比较相邻的两个数，把比较大的放后面
     *
     * @throws Exception
     */
    @Test
    public void test1() throws Exception {
        int[] arr = new int[]{9, 8, 5, 3};
        //让他在比较几次
        for (int i = 0; i < arr.length; i++) {
            //比较相邻的数
            for (int j = 0; j < arr.length - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i : arr) {
            System.out.println(i);
        }

    }

    /**
     * 快速排序
     * 找到数组中最小的值然后放入第一个
     *
     * @throws Exception
     */
    @Test
    public void test2() throws Exception {
        int[] arr = new int[]{9, 8, 5, 3,0,6,2};
        for (int i = 0; i < arr.length; i++) {
            //标识从哪里开始
            int min = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    //找到最小数的索引
                    min = j;
                }
            }
            //交换数
            int temp=arr[i];
            arr[i]=arr[min];
            arr[min]=temp;
        }
        for (int i : arr) {
            System.out.println(i);
        }

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void test3() throws Exception {

    }
}
