package blibli.one;

/**
 * @author AqqJe
 * @description
 */
public class Solution9_2 {

    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String ip = scanner.nextLine();
        String[] str1 = ip.split("[.]");
        int result = 0;
        int[] ips = new int[4];
        for (int i = 0; i < 4; i++) {
            ips[i] = Integer.valueOf(str1[i]);
        }
        if (ips[0] == 10)
            result = 1;
        else if (ips[0] == 172 && (ips[1] >= 16 && ips[1] < 32))
            result = 1;
        else if (ips[0] == 192 && ips[1] == 168)
            result = 1;
        System.out.println(result);
    }
}
