package TeamProject_01;

import java.util.Arrays;

public class Login_info_Class {
	String[] itemname= new String[4];
	int[] itemfunc = new int[4];
	
	String[] friendname = new String[3];
	
	int[] friendlevel = new int[3];

	int curmoney;
	int allmoney;
	
	int automoney;
	int tabmoney;
	
	int mylevel;
	int myitem;
	public Login_info_Class() {
	}
	
	public Login_info_Class(String[] itemname, int[] itemfunc, String[] friendname, int[] friendlevel, int curmoney, int allmoney,
			int automoney, int tabmoney, int mylevel, int myitem) {
		super();
		this.itemname = itemname;
		this.itemfunc = itemfunc;
		this.friendname = friendname;
		this.friendlevel = friendlevel;
		this.curmoney = curmoney;
		this.allmoney = allmoney;
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
