import java.math.BigInteger;
import java.util.Scanner;

/**
 * Jake1996
 */
public class ModifiedFibonacci {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        BigInteger arr[] = new BigInteger[20];
        arr[0] = BigInteger.valueOf(in.nextInt());
        arr[1] = BigInteger.valueOf(in.nextInt());
        int n = in.nextInt();
        in.close();
        for(int i=2;i<n;i++) {
            arr[i] = (arr[i-1].multiply(arr[i-1])).add(arr[i-2]);
        }
        System.out.println(arr[n-1]);
    }
}