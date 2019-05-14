package TeamProject_01;

import java.util.Arrays;

public class Login_info_Class {
	String[] itemname;
	int[] itemfunc;
	
	String[] friendname;
	
	int[] friendlevel;

	int curmoney;
	int automoney;
	int tabmoney;
	
	int mylevel;
	int myitem;
	public Login_info_Class() {
		this.itemname = new String[4];
		this.itemfunc = new int[4];
		this.friendname = new String[3];
		this.friendlevel = new int[3];
	}
	
	public Login_info_Class(String[] itemname, int[] itemfunc, String[] friendname, int[] friendlevel, int curmoney,
			int automoney, int tabmoney, int mylevel, int myitem) {
		super();
		this.itemname = itemname;
		this.itemfunc = itemfunc;
		this.friendname = friendname;
		this.friendlevel = friendlevel;
		this.curmoney = curmoney;
		this.automoney = automoney;
		this.tabmoney = tabmoney;
		this.mylevel = mylevel;
		this.myitem = myitem;
	}

	@Override
	public String toString() {
		return "Login_info_Class [itemname=" + Arrays.toString(itemname) + ", itemfunc=" + Arrays.toString(itemfunc)
				+ ", friendname=" + Arrays.toString(friendname) + ", friendlevel=" + Arrays.toString(friendlevel)
				+ ", curmoney=" + curmoney + ", automoney=" + automoney + ", tabmoney=" + tabmoney + ", mylevel="
				+ mylevel + ", myitem=" + myitem + "]";
	}
}
