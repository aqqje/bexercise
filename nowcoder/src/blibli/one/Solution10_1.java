package blibli.one;

/**
 * @author AqqJe
 * @description 给定一个整数数组,判断其中是否有3个数和为N
 */
public class Solution10_1 {

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String str = scanner.nextLine();
        String[] temp = str.split(",");
        String[] numstr = temp[0].split(" ");
        int[] nums = new int[numstr.length];
        for (int i = 0; i < nums.length; i++)
            nums[i] = Integer.valueOf(numstr[i]);
        int target = Integer.valueOf(temp[1]);
        java.util.Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            int start = i + 1, end = nums.length - 1;
            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == target){
                    System.out.println("True");
                    return;
                }  else if (sum < target)
                    start++;
                else
                    end--;
            }
        }
        System.out.println("False");
    }
}

