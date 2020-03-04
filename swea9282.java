package hw200304;
import java.util.Scanner;

public class swea9282 {
	static int N,M;
	static int[][] map;
	static int[][][][] cache;
	static int cnt;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new int[N+1][M+1];
			cache = new int[N+1][M+1][N+1][M+1];
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M;j++) {
					map[i][j]=sc.nextInt();
				}
			}
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=M-1;j++) {
					map[i][j+1] += map[i][j];
				}
			}
			for(int i=1;i<=N-1;i++) {
				for(int j=1;j<=M;j++) {
					map[i+1][j] += map[i][j];
				}
			}
			cnt=0;
			cnt += method(1,1,N,M);
			System.out.println("#"+t+" "+cnt);
		}
	}
	static int method(int r, int c, int rlen, int clen) {
		if(rlen==1&&clen==1) {
			return 0;
		}
		if(cache[r][c][rlen][clen]!=0) {
			return cache[r][c][rlen][clen];
		}
		cache[r][c][rlen][clen] = Integer.MAX_VALUE;
		//갈랐으면 이제 가른거 저장.
		int sum= map[r+rlen-1][c+clen-1] - map[r+rlen-1][c-1]
				- map[r-1][c+clen-1] + map[r-1][c-1];
		
		for(int i=1;i<rlen;i++) {
			cache[r][c][rlen][clen] = Math.min(cache[r][c][rlen][clen],  sum + method(r,c,i,clen)
								+ method(r+i,c,rlen-i,clen));
		}
		for(int j=1;j<clen;j++) {
			cache[r][c][rlen][clen] = Math.min(cache[r][c][rlen][clen], sum + method(r,c,rlen,j)
								+ method(r,c+j,rlen,clen-j));
		}
		return cache[r][c][rlen][clen];
	}
}
