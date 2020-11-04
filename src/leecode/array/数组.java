package leecode.array;

import java.util.HashSet;

/**
 * @Description
 * 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。找到并返回最大的集合S，S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 *
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 *
 * 示例 1:
 *
 * 输入: A = [5,4,0,3,1,6,2]
 * 输出: 4
 * 解释:
 * A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
 *
 * 其中一种最长的 S[K]:
 * S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 * 注意:
 *
 * N是[1, 20,000]之间的整数。
 * A中不含有重复的元素。
 * A中的元素大小在[0, N-1]之间。
 *
 * @Author xuexue
 * @Date 2020/1/414:30
 */
public class 数组 {

    //[5,4,0,3,1,6,2] 4  [5,4,0,3,2,1,6]
    /*
    连通：
    5 6 2 0
    4 1
    3
    */


    public int arrayNesting(int[] nums) {
        //创建hashset，判断重复
        HashSet<Integer> hashSet = new HashSet<>();
        //记录最长的数组
        int num = 0;
        //记录最长数组的下标
        //int maxIndex = 0;

        for (int i = 0; i < nums.length; i++) {
            int s = nums[i];
            while (hashSet.add(s)) {
                //将这个元素的值作为数组的下标
                s = nums[s];
            }
            //计算并保存最大数组长度
            if (hashSet.size() > num) {
                num = hashSet.size();
                //maxIndex = i;
            }
            //清空HashSet
            hashSet.clear();
        }
        return num;
    }

    public int arrayNesting2(int[] nums) {
        //创建hashset，判断重复
        HashSet<Integer> hashSet = new HashSet<>();
        //记录最长的数组
        int num = 0;
        //设置标记，是否不需要用set记录它
        boolean[] visited = new boolean[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int s = nums[i];
            if (!visited[i]) {
                while (hashSet.add(s)) {
                    //涉及这个元素的都为同一条连通线，不需要遍历了
                    visited[s] = true;
                    //将这个元素的值作为数组的下标
                    s = nums[s];
                }
                //计算并保存最大数组长度
                if (hashSet.size() > num) {
                    num = hashSet.size();
                }
            }
            //清空HashSet
            hashSet.clear();
        }
        return num;
    }

    public int arrayNesting3(int[] nums) {
        //创建hashset，判断重复
        HashSet<Integer> hashSet = new HashSet<>();
        //记录最长的数组
        int num = 0;

        for (int i = 0; i < nums.length; i++) {
            int s = nums[i];
            if (nums[i] != Integer.MAX_VALUE) {
                while (hashSet.add(s)) {
                    int temp = s;
                    //将这个元素的值作为数组的下标
                    s = nums[s];
                    //涉及这个元素的都为同一条连通线，不需要遍历了，设定一个最大值给它
                    nums[temp] = Integer.MAX_VALUE;
                }
                //计算并保存最大数组长度
                if (hashSet.size() > num) {
                    num = hashSet.size();
                }
                //清空HashSet
                hashSet.clear();
            }
        }
        return num;
    }

    public int arrayNesting4(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != Integer.MAX_VALUE) {
                int start = nums[i], count = 0;
                while (nums[start] != Integer.MAX_VALUE) {
                    int temp = start;
                    start = nums[start];
                    count++;
                    nums[temp] = Integer.MAX_VALUE;
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

}
