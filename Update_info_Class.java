package TeamProject_01;

import java.util.Arrays;

public class Update_info_Class {

	int userno;
	int curmoney;
	int tabmoney;
	int allmoney;
	int automoney;
	int mylevel;
	int myitem;
	int[] myfriendlevel;

	public Update_info_Class() {
		super();
	}

	public Update_info_Class(int userno, int curmoney, int allmoney, int tabmoney, int automoney, int mylevel,
			int myitem, int[] myfriendlevel) {
		super();
		this.userno = userno;
		this.curmoney = curmoney;
		this.allmoney = allmoney;
		this.tabmoney = tabmoney;
		this.automoney = automoney;
		this.mylevel = mylevel;
		this.myitem = myitem;
		this.myfriendlevel = myfriendlevel;
	}

	@Override
	public String toString() {
		return "Update_info_Class [userno=" + userno + ", curmoney=" + curmoney + ", tabmoney=" + tabmoney
				+ ", automoney=" + automoney + ", mylevel=" + mylevel + ", myitem=" + myitem + ", myfriendlevel="
				+ Arrays.toString(myfriendlevel) + "]";
	}

}
