package leetcode;


import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

    /**
     * **
     * * 给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
     * * <p>
     * * J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
     * * 示例 1:
     * * <p>
     * * 输入: J = "aA", S = "aAAbbbb"
     * * 输出: 3
     * * 示例 2:
     * * <p>
     * * 输入: J = "z", S = "ZZ"
     * * 输出: 0
     */
    public int numJewelsInStones(String J, String S) {
        //将s转为数组
        char[] sChars = S.toCharArray();
        char[] jChars = J.toCharArray();
        //遍历开始找
        int count = 0;
        for (char jChar : jChars) {
            for (char sChar : sChars) {
                if (jChar == sChar) {
                    count++;
                }
            }
        }
        return count;

    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        Set<Integer> set=new TreeSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i=0;
        for (Integer integer : set) {
            nums[i]=integer;
            i++;
        }
        return i;


    }

    public int numJewelsInStones2(String J, String S) {
        if (S == null || J == null) return 0;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < J.length(); ++i) {
            set.add(J.charAt(i));
        }
        int ans = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (set.contains(S.charAt(i))) {
                ans++;
            }
        }
        return ans;
    }

    @Test
    public void test() throws Exception {
        String j = "aAb";
        String s = "aAAbbbb";
        System.out.println(numJewelsInStones(j, s));
    }
}