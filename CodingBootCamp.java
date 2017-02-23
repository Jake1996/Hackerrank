
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
/**
 * https://www.hackerrank.com/contests/cisco-icode-2016/challenges/coding-boot-camp
 * @author jake
 *
 */
public class CodingBootCamp {
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
	public static void main(String[] args) {
		FastReader in = new FastReader();
		int tc = in.nextInt();
		String c;
		node t;
		while(tc--!=0) {
			ArrayList<node> compulsary = new ArrayList<>();
			ArrayList<node> optional = new ArrayList<>();
			int n = in.nextInt();
			for(int i=0;i<n;i++) {
				t = new node();
				t.start = in.nextInt();
				t.d = in.nextInt();
				t.k = in.nextInt();
				c = in.next();
				if(c.equals("N")) {
					optional.add(t);
				}
				else {
					compulsary.add(t);
				}
			}
			Collections.sort(compulsary, new Comparator<node>() {
				@Override
				public int compare(node o1, node o2) {
					if(o1.start!=o2.start)
						return o1.start-o2.start;
					if(o1.d!=o2.d)
						return o1.d-o2.d;
					return o2.k-o1.k;
					
				}		
			});
			Collections.sort(optional, new Comparator<node>() {
				@Override
				public int compare(node o1, node o2) {
					if(o1.start!=o2.start)
						return o1.start-o2.start;
					if(o1.d!=o2.d)
						return o1.d-o2.d;
					return o2.k-o1.k;
				}		
			});
			
			int time = 0;
			long credits =0;
			boolean flag = true;
			int start,end;
			int j=0;
			ArrayList<node> consider = new ArrayList<>();
			for(int i=0;i<compulsary.size();i++) {
				if(time>compulsary.get(i).start) {
					flag = false;
					break;
				}
				else {
					start = time; 
					end = compulsary.get(i).start;
					if(start!=end) {
						//consider.clear();
						while(j<optional.size()) {
							if(optional.get(j).start>=start&&(optional.get(j).start+optional.get(j).d)<=end) {
								consider.add(optional.get(j));
							}
							j++;
							if(j<optional.size()&&optional.get(j).start>=end) {						
								j--;
								break;
							}
						}
						//use consider and choose the best nodes
						//credits+=process(consider,start,end);
					}
					time=compulsary.get(i).start+compulsary.get(i).d;
					credits+=compulsary.get(i).k;
				}
			}
			//add ending of optional
			//consider.clear();
			while(j<optional.size()) {
				if(optional.get(j).start>=time) {
					consider.add(optional.get(j));
				}
				j++;
			}
			if(!flag) {
				System.out.println("-1");
			}
			else {
				credits+=process(consider,time,-1);
				System.out.println(credits);
			}
		}
		in.close();
	}
	static class node {
		int start;
		int k;
		int d;
	}
	public static long process(ArrayList<node> option,int start,int end) {
		if(option.size()==0) return 0;
		long arr[] = new long[option.size()];
		for(int i=0;i<arr.length;i++) arr[i] = -1;
		long ret = DP(option,0,arr);
		//for(int i=0;i<arr.length;i++) System.out.print(arr[i]+" ");
		return ret;
	}
	public static long DP(ArrayList<node> option,int index,long arr[]){
		//if(index<0 || index >= arr.length) return Integer.MIN_VALUE;
		if(arr[index]==-1) {
			
			int x = BinarySearch(option, option.get(index).start+option.get(index).d, index+1,option.size()-1);
			
			//if(index==2)System.out.println("value if x is : " +x);
			if(index==arr.length-1)
			{
				arr[index] = option.get(index).k;
			}
			else if(x==-1) {
				arr[index] = Math.max(option.get(index).k,DP(option, index+1, arr));
				
			}
			else
				arr[index] = Math.max(option.get(index).k+DP(option, x, arr) ,  DP(option, index+1, arr));
		}
		return arr[index];
	}
	public static int BinarySearch(ArrayList<node> option,int time,int low,int high) {
		while(low<=high)
		{
			int mid = (low+high)/2;
			//if(mid==0)System.out.println("asdasd");
			if(option.get(mid).start>=time &&mid>=1&& option.get(mid-1).start<time)
			{
				return mid;
			}
			else if(option.get(mid).start>=time)
			{
				high = mid-1;
			}
			else 
			{
				low = mid+1;
			}
		}
		return -1;
	}
}

