import java.util.Scanner;
/**
 * https://www.hackerrank.com/challenges/flipping-the-matrix
 * @author jake
 *
 */
public class FlippingTheMatrix {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int q = in.nextInt();
		while(q--!=0) {
			int n = in.nextInt();
			n = n<<1;
			int arr[][] = new int[n][n];
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					arr[i][j] = in.nextInt();
				}
			}
			n = n>>1;
			long sum = 0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					sum+=Math.max(arr[i][j], Math.max(arr[2*n-i-1][j], Math.max(arr[i][2*n-j-1], arr[2*n-i-1][2*n-j-1])));
				}
			}
			System.out.println(sum);
			
		}
		in.close();
	}

}
