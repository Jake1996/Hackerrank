import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class AlgorithmicCrush {

	public static void main(String[] args) {
		FastReader in = new FastReader();
		int n = in.nextInt();
		int m = in.nextInt();
		//SegmentTree s = new SegmentTree(n);
		int a,b,k;
		
		long input1[] = new long[n];
		SegmentTreeMinimumRangeQuery st = new SegmentTreeMinimumRangeQuery();
        long segTree1[] = st.createSegmentTree(input1);
        long lazy1[] =  new long[segTree1.length];
        //st.updateSegmentTreeRangeLazy(input1, segTree1, lazy1, 0, 0, 2);
        
		while(m--!=0) {
			a = in.nextInt()-1;
			b = in.nextInt()-1;
			k = in.nextInt();
			st.updateSegmentTreeRangeLazy(input1, segTree1, lazy1, a, b, k);
			//s.updateSTRange(a, b, k, 0, n-1, 0);
		}
		long max = 0;
		max= st.rangeMinimumQueryLazy(segTree1, lazy1, 0, input1.length, input1.length);
//		for(long i=0;i<n;i++) {
//			max = Math.max(max, s.rangeSumQuery(i, i, 0, n-1, 0));
//		}
		System.out.println(max);
		in.close();

	}
	static class SegmentTreeMinimumRangeQuery {

	    /**
	     * Creates a new segment tree based off input array.
	     */
	    public long[] createSegmentTree(long input[]){
	        //if input len is pow of 2 then size of 
	        //segment tree is 2*len - 1, otherwise
	        //size of segment tree is next (pow of 2 for len)*2 - 1.
	        int len =1;
			while((len=len<<1)<input.length) ;
			len*=2;
	        int nextPowOfTwo = len;
	        long segmentTree[] = new long[nextPowOfTwo*2 -1];
	        
	        for(int i=0; i < segmentTree.length; i++){
	            segmentTree[i] = Long.MIN_VALUE;
	        }
	        constructMinSegmentTree(segmentTree, input, 0, input.length - 1, 0);
	        return segmentTree;
	        
	    }

	    /**
	     * Updates segment tree for certain index by given delta
	     */
	    public void updateSegmentTree(long input[], long segmentTree[], int index, int delta){
	        input[index] += delta;
	        updateSegmentTree(segmentTree, index, delta, 0, input.length - 1, 0);
	    }

	    /**
	     * Updates segment tree for given range by given delta
	     */
	    public void updateSegmentTreeRange(long input[], long segmentTree[], int startRange, int endRange, int delta) {
	        for(int i = startRange; i <= endRange; i++) {
	            input[i] += delta;
	        }
	        updateSegmentTreeRange(segmentTree, startRange, endRange, delta, 0, input.length - 1, 0);
	    }

	    /**
	     * Queries given range for minimum value.
	     */
	    public long rangeMinimumQuery(long []segmentTree,int qlow,int qhigh,int len){
	        return rangeMinimumQuery(segmentTree,0,len-1,qlow,qhigh,0);
	    }

	    /**
	     * Updates given range by given delta lazily
	     */
	    public void updateSegmentTreeRangeLazy(long input[], long segmentTree[], long lazy[], int startRange, int endRange, int delta) {
	        updateSegmentTreeRangeLazy(segmentTree, lazy, startRange, endRange, delta, 0, input.length - 1, 0);
	    }

	    /**
	     * Queries given range lazily
	     */
	    public long rangeMinimumQueryLazy(long segmentTree[], long lazy[], int qlow, int qhigh, int len) {
	        return rangeMinimumQueryLazy(segmentTree, lazy, qlow, qhigh, 0, len - 1, 0);
	    }

	    private void constructMinSegmentTree(long segmentTree[], long input[], int low, int high,int pos){
	        if(low == high){
	            segmentTree[pos] = input[low];
	            return;
	        }
	        int mid = (low + high)/2;
	        constructMinSegmentTree(segmentTree, input, low, mid, 2 * pos + 1);
	        constructMinSegmentTree(segmentTree, input, mid + 1, high, 2 * pos + 2);
	        segmentTree[pos] = Math.max(segmentTree[2*pos+1], segmentTree[2*pos+2]);
	    }
	    
	    private void updateSegmentTree(long segmentTree[], int index, int delta, int low, int high, int pos){
	       
	        //if index to be updated is less than low or higher than high just return.
	        if(index < low || index > high){
	            return;
	        }
	        
	        //if low and high become equal, then index will be also equal to them and update
	        //that value in segment tree at pos
	        if(low == high){
	            segmentTree[pos] += delta;
	            return;
	        }
	        //otherwise keep going left and right to find index to be updated 
	        //and then update current tree position if min of left or right has
	        //changed.
	        int mid = (low + high)/2;
	        updateSegmentTree(segmentTree, index, delta, low, mid, 2 * pos + 1);
	        updateSegmentTree(segmentTree, index, delta, mid + 1, high, 2 * pos + 2);
	        segmentTree[pos] = Math.max(segmentTree[2*pos+1], segmentTree[2*pos + 2]);
	    }

	    private void updateSegmentTreeRange(long segmentTree[], int startRange, int endRange, int delta, int low, int high, int pos) {
	        if(low > high || startRange > high || endRange < low ) {
	            return;
	        }

	        if(low == high) {
	            segmentTree[pos] += delta;
	            return;
	        }

	        int middle = (low + high)/2;
	        updateSegmentTreeRange(segmentTree, startRange, endRange, delta, low, middle, 2 * pos + 1);
	        updateSegmentTreeRange(segmentTree, startRange, endRange, delta, middle + 1, high, 2 * pos + 2);
	        segmentTree[pos] = Math.max(segmentTree[2*pos+1], segmentTree[2*pos+2]);
	    }

	    private long rangeMinimumQuery(long segmentTree[],int low,int high,int qlow,int qhigh,int pos){
	        if(qlow <= low && qhigh >= high){
	            return segmentTree[pos];
	        }
	        if(qlow > high || qhigh < low){
	            return Long.MIN_VALUE;
	        }
	        int mid = (low+high)/2;
	        return Math.max(rangeMinimumQuery(segmentTree, low, mid, qlow, qhigh, 2 * pos + 1),
	                rangeMinimumQuery(segmentTree, mid + 1, high, qlow, qhigh, 2 * pos + 2));
	    }

	    private void updateSegmentTreeRangeLazy(long segmentTree[],
	                                            long lazy[], int startRange, int endRange,
	                                            int delta, int low, int high, int pos) {
	        if(low > high) {
	            return;
	        }

	        //make sure all propagation is done at pos. If not update tree
	        //at pos and mark its children for lazy propagation.
	        if (lazy[pos] != 0) {
	            segmentTree[pos] += lazy[pos];
	            if (low != high) { //not a leaf node
	                lazy[2 * pos + 1] += lazy[pos];
	                lazy[2 * pos + 2] += lazy[pos];
	            }
	            lazy[pos] = 0;
	        }

	        //no overlap condition
	        if(startRange > high || endRange < low) {
	            return;
	        }

	        //total overlap condition
	        if(startRange <= low && endRange >= high) {
	            segmentTree[pos] += delta;
	            if(low != high) {
	                lazy[2*pos + 1] += delta;
	                lazy[2*pos + 2] += delta;
	            }
	            return;
	        }

	        //otherwise partial overlap so look both left and right.
	        int mid = (low + high)/2;
	        updateSegmentTreeRangeLazy(segmentTree, lazy, startRange, endRange,
	                delta, low, mid, 2*pos+1);
	        updateSegmentTreeRangeLazy(segmentTree, lazy, startRange, endRange,
	                delta, mid+1, high, 2*pos+2);
	        segmentTree[pos] = Math.max(segmentTree[2*pos + 1], segmentTree[2*pos + 2]);
	    }

	    private long rangeMinimumQueryLazy(long segmentTree[], long lazy[], int qlow, int qhigh,
	                                      int low, int high, int pos) {

	        if(low > high) {
	            return Long.MIN_VALUE;
	        }

	        //make sure all propagation is done at pos. If not update tree
	        //at pos and mark its children for lazy propagation.
	        if (lazy[pos] != 0) {
	            segmentTree[pos] += lazy[pos];
	            if (low != high) { //not a leaf node
	                lazy[2 * pos + 1] += lazy[pos];
	                lazy[2 * pos + 2] += lazy[pos];
	            }
	            lazy[pos] = 0;
	        }

	        //no overlap
	        if(qlow > high || qhigh < low){
	            return Long.MIN_VALUE;
	        }

	        //total overlap
	        if(qlow <= low && qhigh >= high){
	            return segmentTree[pos];
	        }

	        //partial overlap
	        int mid = (low+high)/2;
	        return Math.max(rangeMinimumQueryLazy(segmentTree, lazy, qlow, qhigh,
	                        low, mid, 2 * pos + 1),
	                rangeMinimumQueryLazy(segmentTree, lazy,  qlow, qhigh,
	                        mid + 1, high, 2 * pos + 2));

	    }

//	    public static void main(String args[]){
//	        SegmentTreeMinimumRangeQuery st = new SegmentTreeMinimumRangeQuery();
//
//	        int input[] = {0,3,4,2,1,6,-1};
//	        int segTree[] = st.createSegmentTree(input);
//
//	        //non lazy propagation example
//	        assert 0 == st.rangeMinimumQuery(segTree, 0, 3, input.length);
//	        assert 1 == st.rangeMinimumQuery(segTree, 1, 5, input.length);
//	        assert -1 == st.rangeMinimumQuery(segTree, 1, 6, input.length);
//	        st.updateSegmentTree(input, segTree, 2, 1);
//	        assert 2 == st.rangeMinimumQuery(segTree, 1, 3, input.length);
//	        st.updateSegmentTreeRange(input, segTree, 3, 5, -2);
//	        assert -1 == st.rangeMinimumQuery(segTree, 5, 6, input.length);
//	        assert 0 == st.rangeMinimumQuery(segTree, 0, 3, input.length);
//
//	        //lazy propagation example
//	        int input1[] = {-1,2,4,1,7,1,3,2};
//	        int segTree1[] = st.createSegmentTree(input1);
//	        int lazy1[] =  new int[segTree.length];
//	        st.updateSegmentTreeRangeLazy(input1, segTree1, lazy1, 0, 3, 1);
//	        st.updateSegmentTreeRangeLazy(input1, segTree1, lazy1, 0, 0, 2);
//	        assert 1 == st.rangeMinimumQueryLazy(segTree1, lazy1, 3, 5, input1.length);
//	    }
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
