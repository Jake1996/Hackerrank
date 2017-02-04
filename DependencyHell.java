import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DependencyHell {
	public static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
	public static ArrayList<ArrayList<Integer>> rgraph = new ArrayList<>();
	public static boolean visited[] = new boolean[20001];
	public static int incoming[] = new int[20001];
	public static ArrayList<Integer> programs = new ArrayList<>();
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int q = in.nextInt();
		int v;
		while(q--!=0) {
			graph.clear();
			rgraph.clear();
			programs.clear();
			int n=in.nextInt();
			for(int i=0;i<n;i++)  {
				visited[i] = false;
				graph.add(new ArrayList<>());
				rgraph.add(new ArrayList<>());
				incoming[i] = 0;
			}
			int m = in.nextInt();
			for(int i=0;i<n;i++) {
				int t = in.nextInt();
				while(t--!=0) {
					v = in.nextInt()-1;
					graph.get(i).add(v);
					rgraph.get(v).add(i);
					incoming[i]++;
				}
			}
			for(int i=0;i<m;i++) {
				programs.add(in.nextInt()-1);
			}
			dependency();
			ArrayList<Integer> ans = topologicalSort(n);
			int count=0;
			for(int i=0;i<n;i++) {
				if(visited[i]) count++;
			}
			System.out.println(count);
			for(Integer i :ans)
			{
				if(visited[i]==true)
					System.out.print((i+1) +" ");
			}
			System.out.println();
		}
		in.close();
	}
	public static void dependency() {
		Stack<Integer> s = new Stack<>();
		for(Integer i: programs) {
			s.add(i);
			visited[i] = true;
		}
		int v;
		while(!s.isEmpty()) {
			v = s.pop();
			for(Integer u:graph.get(v)) {
				if(!visited[u]) {
					s.add(u);
					visited[u] = true;
					//might add intp pq
				}
			}
		}
		
	}
	public static ArrayList<Integer> topologicalSort(int n) {
		TreeSet<Integer> s = new TreeSet<>();
		for(int i=0;i<n;i++) {
			if(incoming[i]==0) {
				s.add(i);
			}
		}
		int v;
		ArrayList<Integer> sorted = new ArrayList<>();
		while(!s.isEmpty()) {
			v = s.pollFirst();
			sorted.add(v);
			for(Integer i: rgraph.get(v)) {
				incoming[i]--;
				if(incoming[i]==0) s.add(i);
			}
		}
		return sorted;
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
