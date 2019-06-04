package TeamProject_01;

/*
 * synchronized 하는 소스코드
 * 
 * 용병 및 메인 캐릭터의 on, off 시키기
 * 
 * synchronized를 해주지 않으면 Thread의 충돌이 일어나서
 * 제대로 실행될때도 있지만 안될때도 있다.
 * 
 * 예를 들면, 용병 1, 2, 3을 사면 true로 해줘서 밖에서 움직이게 해야하는데
 * 1,2,3의 용병 데이터 3개의 thread가 충돌이 일어나서 true 및 false 둘 다 들어가는 경우가 생김
 * 그러므로 synchronized를 사용해서 그 충돌을 잡아준다.
 */

public class FriendTrueOrFalse {
	boolean[] chk;
	Game_Main main;
	int num;

	FriendTrueOrFalse(Game_Main main, int num) {
		this.main = main;
		this.chk = main.chk;
		this.num = num;
	}

	synchronized boolean chk_true(int num) {
		// 용병을 사면 그거에 맞게 true 반환. 쓰레드 충돌 방지
		if (main.chk[num] == true)
			return true;
		return false;
	}
}

class MainchChangesyn{
	Game_Main main;
	int num;

	public MainchChangesyn(Game_Main main) {
		this.main = main;
		this.num = main.myitem;
	}

	synchronized int itemnum(int num) {
		// 아이템 번호에 맞게 출력 ( 아이템 착용/미챡용). 쓰레드 충돌 방지
		return num;
	}
}