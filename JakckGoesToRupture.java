import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JakckGoesToRupture {
	/**
	 * https://www.hackerrank.com/challenges/jack-goes-to-rapture
	 * @param args
	 */
	public static void main(String[] args) {
		FastReader in = new FastReader();
		ArrayList<ArrayList<edge>> g = new ArrayList<>();
		int n = in.nextInt();
		for(int i=0;i<n;i++) {
			g.add(new ArrayList<edge>());
		}
		int e = in.nextInt();
		edge ref;
		int x,y,w;
		for(int i=0;i<e;i++) {
			x = in.nextInt()-1;
			y = in.nextInt()-1;
			w = in.nextInt();
			ref = new edge(w, x);
			g.get(y).add(ref);
			ref = new edge(w, y);
			g.get(x).add(ref);
		}
		in.close();
		Dijikstra d = new Dijikstra();
		d.graph=g;
		d.dijikstra(0);
		if(d.dist[n-1]==Long.MAX_VALUE) {
			System.out.println("NO PATH EXISTS");
		}
		else {
			System.out.println(d.dist[n-1]);
		}
	}
	
	static class edge{
		long weight;
		int v;
		public edge(long w,int x) {
			weight = w;
			v = x;
		}
	}
	static class Dijikstra {
		public ArrayList<ArrayList<edge>> graph;
		public long[] dist;
		public boolean[] visited;
		public void dijikstra(int source) {
			dist = new long[graph.size()];
			visited = new boolean[graph.size()];
			for(int i=0;i<graph.size();i++) {
				dist[i] = Long.MAX_VALUE;
			}
			dist[source] = 0;
			PriorityQueue<edge> Q = new PriorityQueue<>(new Comparator<edge>() {
				@Override
				public int compare(edge o1, edge o2) {
					if(o1.weight-o2.weight>0) return 1;
					if(o1.weight-o2.weight<0) return -1;
					return 0;
				}
			});
			Q.add(new edge(0, source));
			edge ref;
			int u,v;
			long w;
			long no;
			while(!Q.isEmpty()) {
				ref = Q.remove();
				//w = ref.weight;
				u = ref.v;
				if(visited[u]) continue;
				visited[u] = true;
				for(edge e:graph.get(u)) {
					v = e.v;
					w = e.weight;
					no = w-dist[u];
					if(no<0) no=0;
					if(no +dist[u] < dist[v]) {
						dist[v] = no+dist[u];
						Q.add(new edge(dist[v], v));
					}
				}
			}
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
