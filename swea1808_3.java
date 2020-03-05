package hw200305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class swea1808_3 {
	static int X,min;
	static boolean[] btn;
	static ArrayList<Integer> list;
	public static void main (String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		for(int t=1;t<=T;t++) {
			btn = new boolean[10];
			min = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine().trim()," ");
			for(int i=0;i<10;i++) {
				if(Integer.parseInt(st.nextToken())==1)
					btn[i] = true;
			}
			X = Integer.parseInt(br.readLine());//end input
			int size = (int)Math.sqrt(X);
			list = new ArrayList<>();
			//X의 제곱근 이하 의 약수 중 고장난 계산기로 만들 수 있는 약수를 list에 저장.
			makeDivisor(size);
			//이제 X를 재귀적으로 list 안의 약수로 나눈다.
			dfs(X,0);
			System.out.println("#"+t+" "+(min==Integer.MAX_VALUE?-1:min));
		}
	}
	static void dfs(int dividend, int cnt) {
		//종료
		if(dividend==0)
			return;
		//만약 현재 값이 canMake하면  cnt 갱신 후 리턴한다.
		if(canMake(dividend+"")) {
			cnt += len(dividend) + 1; // = 누르는거까지 카운팅
			if(min>cnt) {
				min = cnt; // 계산이 종료되었으므로 min값 갱신
			}
			return;
		}
		//실행
		//재귀
		//list 안의 수로 재귀적으로 나눠줄거임.
		// X = 60, btn-1,2,5 : dividor-> 2, 5 순으로.
		for(int i=0;i<list.size();i++) {
			if(dividend%list.get(i)==0)
				dfs(dividend/list.get(i), cnt + len(list.get(i)) + 1);
		}
	}
	
	static int len(int n) {
		return (int)(Math.log10(n)+1);
	}
	
	static boolean canMake(String n) {
		for(char idx : n.toCharArray()) {
			if(btn[idx-'0']==false)//각 자릿수 중 하나가 계산기에 없는 숫자이면
				return false;// false 리턴.
		}
		return true;
	}
	
	static void makeDivisor(int size) {
		for(int i=2;i<=size;i++) {
			if(X%i==0 && canMake(i+"")) {
				list.add(i);
			}
		}
	}
}
