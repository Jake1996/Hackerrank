import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class q2 {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		int q = in.nextInt();
	    while(q--!=0) {
	      int n = in.nextInt();
	      int arr[][] = new int[n][n];
	      long[] sumc = new long[n];
	      long[] sumr = new long[n];
	      for(int i=0;i<n;i++)
	        for(int j=0;j<n;j++)
	        { 
	        	 arr[i][j] = in.nextInt();
	        	 sumr[i]+=arr[i][j];
	        	 sumc[j]+=arr[i][j];
	        }
	      Arrays.sort(sumr);
	      Arrays.sort(sumc);
	      boolean flag = true;
	      for(int i=0;i<n;i++)
	      {
	      	if(sumc[i]!=sumr[i])
	      		{flag = false;break;}
	      }
	      
	      if(flag) System.out.println("Possible");
	      else System.out.println("Impossible");
	    }
	    in.close();
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
