import java.util.Scanner;

public class SamAndSubstrings {
    public static final int MOD = 1000000007;
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        char arr[] = in.next().toCharArray();
        in.close();
        long res = 0;
        long f = 1;
        int l = arr.length;
        for (int i = l - 1; i >= 0; i--) {
            res = (res + (arr[i] - '0') * f * (i + 1)) % MOD;
            f = (f * 10 + 1) % MOD;
        }
        System.out.println(res);
    }
}