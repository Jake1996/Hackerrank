import java.util.*;
/**
 * @author Jake1996
 * https://www.hackerrank.com/challenges/non-divisible-subset
 */
public class NonDivisibleSubset {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int arr[] = new int[k];
        for(int i=0;i<n;i++) {
            arr[in.nextInt()%k]++;
        }
        in.close();
        int count = 0;
        if(arr[0]>0) count++;
        int stop = (int)Math.ceil((double)k/2);
        for(int i=1;i<stop;i++) {
            count+=Math.max(arr[i],arr[k-i]);
        }
        if(k%2==0) {
            if(arr[k/2]>0) count++;
        }
        System.out.println(count);
    }
}