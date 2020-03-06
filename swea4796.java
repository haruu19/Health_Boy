package hw200306;

import java.util.Scanner;

public class swea4796 {
	static int N,ans;
	static int[] h;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			h = new int[50001];
			ans=0;
			for(int i=1;i<=N;i++) {
				h[i] = sc.nextInt();
			}
			for(int i=2;i<=N-1;i++) {
				if(h[i]>h[i-1]&&h[i]>h[i+1]) {
					int lidx = i-1;
					int ridx = i+1;
					int lcount=1;
					int rcount=1;
					//좌 카운트
					while(lidx-1>=1&&h[lidx]>h[lidx-1]) {
						lidx--;
						lcount++;
					}
					//우 카운트
					while(ridx+1<=N&&h[ridx]>h[ridx+1]) {
						ridx++;
						rcount++;
					}
					ans += lcount * rcount;
					i=ridx;
				}
			}
			System.out.println("#"+t+" "+ans);
		}
	}
}
