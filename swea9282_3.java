package hw200304;

import java.util.Arrays;
import java.util.Scanner;

public class swea9282_3 {
	static int n,m;
	static int[][] map;
	static int[][][][] dp;
	static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			n = sc.nextInt();
			m = sc.nextInt();
			map = new int[n][m];
			dp = new int[n+1][m+1][n+1][m+1];
			for(int[][][] d1 : dp) {
				for(int[][] d2 : d1) {
					for(int[] d3 : d2) {
						Arrays.fill(d3,Integer.MAX_VALUE);
					}
				}
			}
			for(int i=0;i<n;i++) {
				for(int j=0;j<m;j++) {
					map[i][j] = sc.nextInt();
				}
			}
			for(int i=0;i<n;i++) {
				
			}
			
			//처리
			result = dfs(0,0,n,m);
			//출력
			System.out.println("#"+t+" "+result);
		}
	}
	static int dfs(int y, int x, int h, int w) {
		//종료
		if(w==1&&h==1) {
			return 0;
		}
		if(dp[y][x][h][w]!=Integer.MAX_VALUE) {
			return dp[y][x][h][w];
		}
		//실행
		//기존에 있던 덩어리의 건포도갯수
		int sum = 0;
		for(int i=y;i<y+h;i++) {
			for(int j=x;j<x+w;j++) {
				sum+=map[i][j];
			}
		}
		//가로로 나누어서 비용을 최소비용 을 구한다.
		for(int i=1;i<h;i++) {
			//위쪽비용
			int sum1 = dfs(y,x,i,w);
			//아래쪽비용
			int sum2 = dfs(y+i,x,h-i,w);
			int sum3 = sum + sum1 + sum2;
			dp[y][x][h][w] = Math.min(dp[y][x][h][w], sum3);
		}
		//세로로 나누어서 최소비용 구한다.
		for(int i=1;i<w;i++) {
			//왼쪽비용
			int sum1 = dfs(y,x,h,i);
			//오른쪽비용
			int sum2 = dfs(y,x+i,h,w-i);
			int sum3 = sum + sum1 + sum2;
			dp[y][x][h][w] = Math.min(dp[y][x][h][w],  sum3);
		}
		//재귀호출
		return dp[y][x][h][w];
	}
}
