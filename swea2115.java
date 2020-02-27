import java.util.Scanner;

public class swea2115 {
	static class Bee{
		int x;
		int y;
		int honey;
		Bee(int x, int y, int honey){
			this.x =x;
			this.y =y;
			this.honey = honey;
		}
	}
	static Bee[] b1;
	static Bee[] b2;
	static int[][] a;
	static int ans;
	static int N;
	static int M;
	static int C;
	static int cnt;
	static int max;
	static boolean[] v;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			a = new int[N][N];
			M = sc.nextInt();
			C = sc.nextInt();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					a[i][j] = sc.nextInt();
				}
			}
			cnt = N - M;
			b1 = new Bee[M];
			b2 = new Bee[M];
			v = new boolean[M];
			ans = 0;
			method(0);
			System.out.println("#"+t+" "+ans);
		}
	}
	static void cal(int depth, Bee[] b) {
		if(depth==M) {
			int sum = 0;
			int sum2 = 0;
			for(int i=0;i<M;i++) {
				if(v[i]) {
					sum+=b[i].honey;
					sum2+= b[i].honey * b[i].honey;
				}
			}
			if(sum<=C) {
				max = Math.max(max, sum2);
			}
			return;
		}
		v[depth]=true;
		cal(depth+1,b);
		v[depth]=false;
		cal(depth+1,b);
	}
	
	static void method(int depth) {
		if(depth==2) {
			int val = 0;
			cal(0,b1);
			val+=max;
			max = 0;
			cal(0,b2);
			val+=max;
			max = 0;
			ans = Math.max(ans, val);
			return;
		}
		for(int i=0;i<N;i++) {
			LOOP:
			for(int j=0;j<=cnt;j++) {
				if(depth==0) {
					for(int k=0;k<M;k++) {
						b1[k] = new Bee(i,j+k,a[i][j+k]);
					}
					method(depth+1);
				}
				if(depth==1) {
					if(b1[0].x == i){
						for(int k=0;k<M;k++) {
							for(int l=j;l<j+M;l++) {
								if(b1[k].y==l)
									continue LOOP;
							}
						}
					}
					for(int k=0;k<M;k++) {
						b2[k] = new Bee(i,j+k,a[i][j+k]);
					}
					method(depth+1);
				}
			}
		}
		
	}

}
