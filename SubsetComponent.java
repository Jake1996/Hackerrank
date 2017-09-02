import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class SubsetComponent {
	public static long d[];
	public static long ans = 0;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		d = new long[n];
		for (int i = 0; i < n; i++)
			d[i] = parseLong(new BigInteger(in.next()).toString(2),2);
		in.close();
		subsetGen(0, new ArrayList<Long>());
		System.out.println(ans);
	}
	private static long parseLong(String s, int base) {
		return new BigInteger(s, base).longValue();
	}
	public static void subsetGen(int i, ArrayList<Long> arr) {
		if(i<d.length) {
		subsetGen(i + 1, new ArrayList<>(arr)); //not include the ith number
		int j = 0;
		for (; j < arr.size(); j++) {
			if ((arr.get(j)&d[i])!=0) {
				arr.set(j, arr.get(j)|d[i]);
				break;
			}
		}
		if (j == arr.size()) {
			arr.add(d[i]);
		}
		subsetGen(i + 1, arr);
		}else if (i == d.length) {
			ans += noOfComponents(arr);
			return;
		}
	}
	public static int noOfComponents(ArrayList<Long> arr) {
		for (int i = 0; i < arr.size(); i++) {
			for (int j = arr.size() - 1; j > i; j--) {
				if ((arr.get(i)&arr.get(j))!=0) {
					arr.set(i, arr.get(i)|arr.get(j));
					arr.remove(j);
				}
			}
		}
		int total = 64;
		for (int i = 0; i < arr.size(); i++) {
			if(arr.get(i)!=0) {
				total++;
				long k = arr.get(i);
				while (k!=0) {
					k = k &(k-1);
					total--;
				}
			}
		}
		return total;
	}
}
