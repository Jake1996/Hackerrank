import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class MatchstickWarehouseTheif {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		int m = in.nextInt();
		ArrayList<node> l = new ArrayList<>();
		int a,b;
		while(m--!=0) {
			a = in.nextInt();
			b = in.nextInt();
			l.add(new node(a,b));
		}
		in.close();
		l.sort(new Comparator<node>() {

			@Override
			public int compare(node o1, node o2) {
				return o2.value-o1.value;
			}
		});
		long count=0;
		int i=0;
		node ref;
		while(n!=0) {
			if(l.size()<=i) break;
			ref = l.get(i);
			if(n>=ref.no) {
				count+=ref.no*ref.value;
				n-=ref.no;
			}
			else {
				count+=n*ref.value;
				n-=n;
			}
			i++;
		}
		System.out.println(count);
	}
	static class node {
		int value;
		int no;
		public node(int a,int b) {
			value=b;
			no=a;
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
