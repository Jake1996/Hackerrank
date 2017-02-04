import java.util.Scanner;

public class LonelyInteger {
	public static int arr[] = new int[100];
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		for(int i=0;i<n;i++) arr[i] = in.nextInt();
		for(int i=0;i<n;i++) {
			boolean flag = false;
			for(int j=0;j<n&&!flag;j++) {
				if(i!=j&&arr[i]==arr[j]) flag = true;
			}
			if(!flag) {System.out.println(arr[i]);break;}
		}
		in.close();
	}

}
