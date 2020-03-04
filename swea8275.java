/**
 * N : 우리 갯수 1~6
 * X : 하나의 우리에 있을 수 있는 마릿수
 * M : 경근이가 체크한 횟수.. 햄스터를 배치해서 이 M번의 체크를 다 통과하는지 봐야함
 * 
 * 우리 갯수 최대 6개.. 각 우리 마릿수 0~11
 * 최악의 경우 11^6. 완탐 해도 되겠다.
 * 
 * 출력형식>
 * #1 0 5 5
 * 
 * 주의사항 : 
 * (1) M번 다 만족하는 배치가 여러 경우일 때 합계 많은거!
 * (2) 합계 다 같은 경우가 있다면 ? 오름차순 빠른거
 * (3) 만족하는 배치가 없다면 ? -1 출력
 */
package hw200304;

import java.util.Scanner;

public class swea8275 {
	static int[] cage;//가능한 모든 햄스터 배치를 해볼(중복순열)
	static int N,X,M;
	static int[][] input;
	static int max;//햄스터 배치 여러가지 종류 가능하다면? 합이 최대가 되는 경우를 선택!
	static String ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			max = -1;
			ans = "";
			N = sc.nextInt();//총 우리갯수
			X = sc.nextInt();//각 우리 가능마릿수
			M = sc.nextInt();//체크 횟수
			
			cage = new int[N+1];//케이지 번호 1~N
			input = new int[M][3];//left,right,sum
			
			for(int i=0;i<M;i++) {
				input[i][0] = sc.nextInt();//left
				input[i][1] = sc.nextInt();//right
				input[i][2] = sc.nextInt();//sum
			}
			perm(1,0); // 1번 케이지부터 가능한 마릿수 모두 채워서
			System.out.println("#"+t+" "+((max==-1) ? max : ans));
		}
	}
	static void perm(int idx, int sum) {//idx는케이지번호
		if(idx==cage.length) {//체크와 부합하는지?
			if(check()) {//만족한다
				if(max < sum) { // 등호 없는 조건은 ? 나중꺼 무시!
					max = sum;
					ans = makeAns();
				}
			}
			return;
		}
		for(int i=0;i<=X;i++) { // 알아서 0부터 채우기 때문에 다음 경우의 수는 점점 커짐.
			cage[idx] = i;
			perm(idx+1, sum+i);
		}
	}
	
	static String makeAns() {
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<=N;i++) {
			sb.append(cage[i]).append(" ");
		}
		return sb.toString();
	}
	
	static boolean check() {
		for(int i=0;i<M;i++) {
			int tmp = 0;
			for(int j=input[i][0];j<=input[i][1];j++) {
				tmp+=cage[j];
			}
			if(tmp != input[i][2])
				return false;
		}
		return true;
	}
}
