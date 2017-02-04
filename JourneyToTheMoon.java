import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * https://www.hackerrank.com/challenges/journey-to-the-moon
 * @author jake
 *
 */
public class JourneyToTheMoon {
	public static void main(String args[]) {
		FastReader in = new FastReader();
		int n= in.nextInt();
		int m = in.nextInt();
		Sets s = new Sets(n);
		s.initialize(n);
		int x,y;
		for(int i=0;i<m;i++) {
			x = in.nextInt();
			y = in.nextInt();
			s.union(x, y);
		}
		in.close();
		long count = s.countSets(0, n-1);
		if(count<2) {
			System.out.println("0");
		}
		else {
			count=0;
			for(int i=0;i<n;i++) {
				if(i==s.arr[i]) {
					count+=s.size[i]*(n-s.size[i]);
				}
			}
			count/=2;
			System.out.println(count);
		}
	}
	static class Sets {
	    private int arr[];
	    private int size[];
	    public Sets(int n) {
	        arr = new int[n];
	        size = new int[n];
	    }
	    public void initialize(int n) {
	        for(int i=0;i<n;i++) {
	            arr[i] = i;
	            size[i] = 1;
	        }
	    }
	    public int root(int i) {
	        while(arr[i]!=i) {
	            arr[i] = arr[arr[i]];
	            i = arr[i];
	        }
	        return i;
	    }
	    public void union(int a,int b) {
	        int rootA = root(a);
	        int rootB = root(b);
	        if(rootA==rootB) return;
	        if(size[rootA]<size[rootB]) {
	            arr[rootA] = arr[rootB];
	            size[rootB] += size[rootA];
	        }
	        else {
	            arr[rootB] = arr[rootA];
	            size[rootA] += size[rootB]; 
	        }
	    }
	    public int countSets(int start,int end) {
	        int count = 0;
	        for(int i=start;i<=end;i++)
	            if(i==arr[i])
	            	count++;
	        return count;
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
