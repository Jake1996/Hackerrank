import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BiggerIsBetter {
	public static String noans = "no answer";
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int q = in.nextInt();
		while(q--!=0) {
			String str = in.next();
			char nums[] = str.toCharArray();
			boolean flag = false;
			int p=0;            
			for(int i=nums.length-2; i>=0; i--){
				if(nums[i]<nums[i+1]){
					p=i;
					flag = true;
					break;
				}    
			}

			int j = 0;
			for(int i=nums.length-1; i>p; i--){
				if(nums[i]> nums[p]){
					j=i;
					break;
				}    
			}
			boolean sec = false;
			if(p==0 && j==0){
				reverse(nums, 0, nums.length-1);
				sec = true;
			}
			if(!sec) {
				char temp=nums[p];
				nums[p]=nums[j];
				nums[j]=temp;

				if(p<nums.length-1){
					reverse(nums, p+1, nums.length-1);
				}
			}

			if(flag) {
				System.out.println(new String(nums));
			}
			else {
				System.out.println(noans);
			}
		}
		in.close();

	}
	public static void reverse(char[] nums, int left, int right){
		while(left<right){
			char temp = nums[left];
			nums[left]=nums[right];
			nums[right]=temp;
			left++;
			right--;
		}
	}
	static class FastReader
	{
		BufferedReader br;
		StringTokenizer st;

		public FastReader()
		{
			br = new BufferedReader(new
					InputStreamReader(System.in));
		}

		String next()
		{
			while (st == null || !st.hasMoreElements())
			{
				try
				{
					st = new StringTokenizer(br.readLine());
				}
				catch (IOException  e)
				{
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt()
		{
			return Integer.parseInt(next());
		}

		long nextLong()
		{
			return Long.parseLong(next());
		}

		double nextDouble()
		{
			return Double.parseDouble(next());
		}

		String nextLine()
		{
			String str = "";
			try
			{
				str = br.readLine();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return str;
		}
		void close() {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	} 
}
