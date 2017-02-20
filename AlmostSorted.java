import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/**
 * https://www.hackerrank.com/challenges/almost-sorted
 * @author jake
 *
 */
public class AlmostSorted {
	public static int arr[] = new int[100000];
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		for(int i=0;i<n;i++ ){
			arr[i] = in.nextInt();
		}
		in.close();
		int arr1[] = Arrays.copyOf(arr, n);
		boolean flag1=true;
		int count=0;
		int i1=-1,i2=-1;
		Arrays.sort(arr1);
		for(int i=0;i<n;i++) {
			if(arr1[i]!=arr[i]) {
				count++;
				if(i1==-1) {
					i1=i;
				}
				else {
					i2=i;
				}
			}
		}
		if(count==0) {
			System.out.println("yes");
		}
		else if(count==2) {
			System.out.println("yes\nswap "+(i1+1)+" "+(i2+1));
		}
		else if(count==1) {
			System.out.println("no");
		}
		else if(i2-i1==count-1||i2-i1==count){
			flag1=true;
			for(int i=0;i<count;i++) {
				if(arr[i1+i]!=arr1[i2-i]) flag1=false;
			}
			if(flag1) {
				System.out.println("yes\nreverse "+(i1+1)+" "+(i2+1));
			}
			else {
				System.out.println("no");
			}
		}
		else {
			System.out.println("no");
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
