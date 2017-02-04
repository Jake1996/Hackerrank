import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BFSShortestReach {

	public static void main(String[] args) {
		FastReader in  = new FastReader();
		int q = in.nextInt();
		while(q--!=0) {
			int n = in.nextInt();
			int m = in.nextInt();
			int u,v;
			ArrayList<ArrayList<edge>> graph = new ArrayList<>();
			for(int i=0;i<n;i++) graph.add(new ArrayList<>());
			while(m--!=0) {
				u = in.nextInt()-1;
				v = in.nextInt()-1;
				graph.get(u).add(new edge(6, v));
				graph.get(v).add(new edge(6, u));
			}
			int s = in.nextInt()-1;
			Dijikstra d = new Dijikstra();
			d.graph = graph;
			d.dijikstra(s);
			for(int i=0;i<n;i++) {
				if(d.dist[i]==Long.MAX_VALUE) System.out.print("-1 ");
				else if(i!=s) System.out.print(d.dist[i]+" ");
			}
			System.out.println();
		}
		in.close();

	}
	public static class edge{
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
			while(!Q.isEmpty()) {
				ref = Q.remove();
				//w = ref.weight;
				u = ref.v;
				if(visited[u]) continue;
				visited[u] = true;
				for(edge e:graph.get(u)) {
					v = e.v;
					w = e.weight;
					if(dist[u] + w < dist[v]) {
						dist[v] = w + dist[u];
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
