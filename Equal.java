import java.util.Arrays;
import java.util.Scanner;

public class Equal {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while(t--!=0) {
            int n = in.nextInt();
            int arr[] = new int[n];
            for(int i=0;i<n;i++) {
                arr[i] = in.nextInt();
            }
            Arrays.sort(arr);
            
        }           
        in.close();
    }
}