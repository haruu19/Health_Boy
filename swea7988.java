/*
 * 문제 분석
 * >> 두명 사이의 시너지를 두 팀으로 무조건 쪼개야 한다. 가능한가? yes or no
 * 
 * 특이사항 1 : 시너지 정보의 갯수가 K개 라는건 입력이 주어지는데.. 
 * 그래서 전체의 총원이 몇명인지? 주어지지 않는다.
 * >> A B, C D, E F : 최대인원.(2K)
 * 특이사항 2 : 팀의 배치에서 팀 순서는 상관없이 시너지를 반대쪽으로 보내기만 하면 됨.
 * 그럼 기준 정점 시너지 다 저쪽 보내는 BFS
 * 특이사항 3 : 정점과 정점 사이의 시너지 효과를 찢어놓는 작업을 해야하는데
 * 정점을 번호가 아닌 문자열 이름으로 줌;;
 * >>이름에 각각 정점 번호를 붙여서 우리가 익숙한 인접 행렬의 인덱스 형태로
 * 활용할 수 있게 작업을 해보자!
 */

package hw200304;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class swea7988 {
	static int K;//시너지 효과 갯수
	//Alex가 두번 들어와도 중복검사 할 수 있는 자료구조 HashMap
	static HashMap<String, Integer> player;//이름,번호
	static boolean[][] map; // 두 정점 사이에 시너지가 있다 없다를 판단하는 용도의 인접행렬
	static int[] teamInfo; // 각 정점이 지금 어느 팀에 있는지를 기록할 배열
	static boolean ans; // 된다 안된다 기록할 변수
	static int pcnt; // 총 인원이 문제에 입력으로 안들어옴. 세야됨. 
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int t=1;t<=T;t++) {
			K = sc.nextInt();
			map = new boolean[2*K][2*K];
			ans = true;
			player = new HashMap<>();
			pcnt = 0;//player 번호 0번부터 붙이자 !
			for(int i=0;i<K;i++) {
				String name1 = sc.next();
				String name2 = sc.next();
				if(!player.containsKey(name1)) {//기존에 등장했던 애가 아니라면? 뉴페이스! 번호 붙이자!
					player.put(name1, pcnt++);
				}
				if(!player.containsKey(name2)) {//기존에 등장했던 애가 아니라면? 뉴페이스! 번호 붙이자!
					player.put(name2, pcnt++);
				}
				map[player.get(name1)][player.get(name2)] = true;
				map[player.get(name2)][player.get(name1)] = true;
			}
			teamInfo = new int[pcnt]; // 각각이 임의의 팀에 소속될거임. 1 or 2번팀. 아직 안정해졌으면 0번.
			for(int i=0;i<pcnt;i++) {//모든 플레이어에 대해 시너지가 상대편으로 갔는지 봅시다~
				if(teamInfo[i]==0) {
					//팀결정 안된애는? 시너지효과를 처음으로 따지는 애네. 아무팀에 넣자
					teamInfo[i] = 1;
					bfs(i);
				}
				if(!ans)
					break;
			}
			System.out.println("#"+t+" "+(ans?"Yes":"No"));
		}
	}
	static void bfs(int idx) { // 출발하는 정점 시너지를 찢기 시작하자!
		Queue<Integer> queue = new LinkedList<>();
		queue.add(idx);
		
		while(!queue.isEmpty()) {
			int me = queue.poll();// 나를 기준으로
			for(int i=0;i<pcnt;i++) { // 모든 애들을 검사해서 시너지 있는 애들 저쪽으로 보내자!
				if(map[me][i]) { // 나랑 시너지 있는 i다
					if(teamInfo[i]!=0 && teamInfo[i]==teamInfo[me]) {
						//팀이 결정된 애내? 근데 나랑 이미 같은팀? ..
						ans = false;
						return;
					}
					
					if(teamInfo[i]==0) {//나랑 시너지 있는 앤데 팀이 미정이네? 절로가.
						teamInfo[i] = teamInfo[me]==1? 2: 1;
						queue.add(i);// 자 그럼 쟤랑 시너지 있는 애를 또 반대편으로 밀자~
					}
				}
			}
		}
	} 
}
