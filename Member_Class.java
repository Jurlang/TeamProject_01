package TeamProject_01;
//���̵�(20�ڸ�), ���(20�ڸ�), �̸�(20�ڸ�), �������(6�ڸ�), �̸���(50�ڸ�)

//������Ʈ ȸ������ !
public class Member_Class {
	private String id;
	private String pw;
	private String name;
	private int bir;
	private String eml;
	
	public Member_Class() {}
	public Member_Class(String id, String pw, String name, int bir, String eml) {
		super();
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.bir = bir;
		this.eml = eml;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBir() {
		return bir;
	}
	public void setBir(int bir) {
		this.bir = bir;
	}
	public String getEml() {
		return eml;
	}
	public void setEml(String eml) {
		this.eml = eml;
	}
	@Override
	public String toString() {
		return id + " " + pw + " " + name + " " + bir + " " + eml;
	}
	
}
