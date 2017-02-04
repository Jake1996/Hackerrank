import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class SherlockandMinimax {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		int[] arr = new int[n];
		for(int i=0;i<n;i++)
			arr[i] = in.nextInt();
		Arrays.sort(arr);
		int p = in.nextInt();
		int q = in.nextInt();
		in.close();
		arr = foo(arr,p,q);
		int maxRangel=0,maxRangeh=0;
		int k;
		for(int i=1;i<arr.length;i++) {
			k = (arr[i]-arr[i-1])%2;
			if(arr[i]-arr[i-1]-k>maxRangeh-maxRangel) {
				maxRangeh = arr[i];
				maxRangel = arr[i-1];
			}
		}
		System.out.println((maxRangeh+maxRangel)/2);
	}
	public static int[] foo(int arr[],int p,int q) {
		ArrayList<Integer> l = new ArrayList<>();
		if(arr[0]>=p) {
			l.add(arr[0]-2*(arr[0]-p));
		}
		else if(arr.length!=1){
			int j=0;
			while(j<arr.length&&arr[j]<p) j++;
			if(j>=arr.length) j=arr.length-1;
			int k = arr[j]+arr[j-1];
			k/=2;
			if(k>=p&&j>0) l.add(arr[j-1]);
			else l.add(arr[j]-2*(arr[j]-p));
		}
		for(int i=0;i<arr.length;i++) {
			if(arr[i]>p&&arr[i]<q) {
				l.add(arr[i]);
			}
		}
		if(arr[arr.length-1]<=q) {
			l.add(arr[arr.length-1]+2*(q-arr[arr.length-1]));
		}
		else if(arr.length!=1){
			int j=arr.length-1;
			while(j>=0&&arr[j]>q) j--;
			if(j<0) j=0;
			int k = arr[j]+arr[j+1];
			k/=2;
			if(k<=q) l.add(arr[j+1]);
			else l.add(arr[j]+2*(q-arr[j]));
		}
		//System.out.println(l.toString());
		arr = new int[l.size()];
		for(int i=0;i<l.size();i++) {
			arr[i] = l.get(i);
		}
		return arr;
	}

}
