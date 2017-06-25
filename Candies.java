import java.util.Scanner;

public class Candies {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int arr[] = new int[n];
        for(int i=0;i<n;i++) {
            arr[i] = in.nextInt();
        }
        in.close();
        int ans[] = new int[n];
        ans[0] = 1;
        for(int i=1;i<n;i++) {
            if(arr[i-1]<arr[i]) {
                ans[i] = ans[i-1]+1;
            }
            else {
                ans[i] = 1;
            }
        }
        for(int i=n-2;i>=0;i--) {
            if(arr[i+1]<arr[i]&&ans[i]<=ans[i+1]) {
                ans[i] = ans[i+1]+1;
            }
        }
        long answer = 0;
        for(int i=0;i<n;i++) answer+=ans[i];
        System.out.println(answer);
    }   
}