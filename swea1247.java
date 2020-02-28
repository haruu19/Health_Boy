import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class swea1247 {
	static class Pos{
		int x;
		int y;
		Pos(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	static int N;
	static Pos[] a;
	static int[] b;
	static boolean[] v;
	static int ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			a = new Pos[N+2];
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			a[0] = new Pos(n1,n2);
			n1 = sc.nextInt();
			n2 = sc.nextInt();
			a[N+1] = new Pos(n1,n2);
			for(int i=1;i<=N;i++) {
				n1 = sc.nextInt();
				n2 = sc.nextInt();
				a[i] = new Pos(n1,n2);
			}
			v = new boolean[N+1];
			b = new int[N+2];
			b[0] = 0;
			b[N+1] = N+1;
			ans = Integer.MAX_VALUE;
			perm(0);
			System.out.println("#"+t+" "+ans);
		}
	}
	static void perm(int depth) {
		if(depth == N) {
			for(int i=1;i<=N;i++) {
				System.out.print(b[i]+" ");
			}
			System.out.println();
//			int len = 0;
//			for(int i=0;i<=N;i++) {
//				len += Math.abs(a[b[i]].x-a[b[i+1]].x) + Math.abs(a[b[i]].y-a[b[i+1]].y);
//			}
//			ans = Math.min(len, ans);
			return;
		}
		for(int i=1;i<=N;i++) {
			if(v[i])
				continue;
			v[i] = true;
			b[depth+1] = i;
			perm(depth+1);
			v[i] = false;
		}
	}
}