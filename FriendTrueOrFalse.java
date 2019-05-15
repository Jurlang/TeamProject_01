package TeamProject_01;

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
		if (main.chk[num] == true)
			return true;
		return false;
	}
}
