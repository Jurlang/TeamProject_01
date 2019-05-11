package TeamProject_01;
//아이디(20자리), 비번(20자리), 이름(20자리), 생년월일(6자리), 이메일(50자리)

//프로젝트 회원가입 !
public class PMember {
	private String id;
	private String pw;
	private String name;
	private int bir;
	private String eml;
	
	public PMember() {}
	public PMember(String id, String pw, String name, int bir, String eml) {
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
