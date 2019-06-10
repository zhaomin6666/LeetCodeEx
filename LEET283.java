package LeetCode;

import com.alibaba.fastjson.JSON;

/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 
 * 示例:
 * 
 * 输入: [0,1,0,3,12] 输出: [1,3,12,0,0] 说明:
 * 
 * 必须在原数组上操作，不能拷贝额外的数组。 尽量减少操作次数。
 * 
 * @author zm
 *
 */
public class LEET283 {
	public static void main(String[] args) {
		LEET283 l283 = new LEET283();
		int[] nums = {0, 1, 0, 3, 12};
		l283.moveZeroes2(nums);
		System.out.println(JSON.toJSONString(nums));
	}

	public void moveZeroes(int[] nums) {
		int i = 0, j = 0;
		loop : while (i < nums.length && j < nums.length) {
			if (nums[i] == 0) {
				while (nums[j] == 0) {
					++j;
					if (j >= nums.length) {
						break loop;
					}
				}
				nums[i] = nums[j];
				nums[j] = 0;
			}
			++i;
			++j;
		}
	}
	
	public void moveZeroes2(int[] nums) {
        int i = 0; 
        for(int j = 0; j < nums.length; j++){
            if(nums[j] != 0){
                nums[i] = nums[j];
                i++;
            }
        }
        for(int p = i; p < nums.length; p++){
            nums[p] = 0;
        }
    }
}
