import java.util.Scanner;

public class MatrixLayerRotation {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int m = in.nextInt();
		int n = in.nextInt();
		int r = in.nextInt();
		int arr[][] = new int[m][n];
		for(int i=0;i<m;i++)
			for(int j=0;j<n;j++)
				arr[i][j] = in.nextInt();
		int layers = Math.min(m, n)/2;
		for(int i=0;i<layers;i++) {
			rotate(arr, m, n, r, i);
		}
		for(int i=0;i<m;i++) {
			for(int j=0;j<n;j++)
				System.out.print(arr[i][j]+" ");
			System.out.println();
		}
		in.close();
	}
	public static void rotate(int arr[][],int m,int n,int r,int layer) {
		r = rotateBy(r, m-2*layer, n-2*layer);
		if(r==0) return;
		int perimeter = perimeter(m-2*layer, n-2*layer);
		int temp[] = new int[perimeter];
		int sx,sy,ex,ey;
		sx = sy = layer;
		ey = m-layer-1;
		ex = n-layer-1;
		int pos=0;
		for(int i=sy;i<=ey;i++) {
			temp[pos++] = arr[i][sx];
		}
		for(int i=sx+1;i<=ex;i++) {
			temp[pos++] = arr[ey][i];
		}
		for(int i=ey-1;i>=sy;i--) {
			temp[pos++] = arr[i][ex];
		}
		for(int i=ex-1;i>=sx+1;i--) {
			temp[pos++] = arr[sy][i];
		}
		pos = temp.length-r;
		for(int i=sy;i<=ey;i++) {
			arr[i][sx] = temp[pos++];
			pos%=temp.length;
		}
		for(int i=sx+1;i<=ex;i++) {
			arr[ey][i] = temp[pos++];
			pos%=temp.length;
		}
		for(int i=ey-1;i>=sy;i--) {
			arr[i][ex] = temp[pos++];
			pos%=temp.length;
		}
		for(int i=ex-1;i>=sx+1;i--) {
			arr[sy][i] = temp[pos++];
			pos%=temp.length;
		}
	}
	public static int rotateBy(int r,int m,int n) {
		return r%perimeter(m, n);
	}
	public static int perimeter(int m,int n) {
		return 2*m+2*n-4;
	}

}
