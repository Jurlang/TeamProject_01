package TeamProject_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // 일단 로그인이 되고, 회원가입이 되는 것 까지만!!
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//객체를 싱글톤으로 만들어줘야 된다잉?!
public class DBConn {
	private DBConn() {
	}

	private static DBConn dbConn = new DBConn();

	public static DBConn getInstance() {
		return dbConn;
	}

	// DBConnection하는걸 만들긔~
	private Connection getConnection() {
		Connection conn = null;
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "qwer";
		String passwd = "1234";
		String driver = "oracle.jdbc.driver.OracleDriver";

		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// select 만들긔
	public ArrayList<Member_Class> selectAll() {
		ArrayList<Member_Class> list = new ArrayList<Member_Class>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Member_Class m = new Member_Class();
				m.setId(rs.getString("id"));
				m.setPw(rs.getString("pw"));
				m.setName(rs.getString("name"));
				m.setBir(rs.getString("bir"));
				m.setEml(rs.getString("eml"));
				list.add(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return list;
	}
	// select는 관리자가 보는 용도이기 때문에 게임 유저가 쓰진 않으므로 select는 뺀돠!
	// 고로 insert를 넣어주면 됨!

	// 아이디를 검색해서 회원정보를 볼 수 있는 소스코드!
	public Member_Class selectOne(String id) {
		Member_Class m = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from userinfo where id=?";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				m = new Member_Class();
				m.setId(rs.getString("id"));
				m.setPw(rs.getString("pw"));
				m.setName(rs.getString("name"));
				m.setBir(rs.getString("birth"));
				m.setEml(rs.getString("mail"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}

		return m;
	}

	public void insert(Member_Class m) { // 회원가입
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into userinfo(userno, id,pw,name,birth,mail) values(?,?,?,?,to_date(?),?)";
		String countsql = "select count(*) from userinfo";
		String usercharinfo_init_sql = "insert into usercharinfo values (?, 0, 1)";
		String myfriend_init_01_sql = "insert into myfriend values ( ?, 1, 0 )";
		String myfriend_init_02_sql = "insert into myfriend values ( ?, 2, 0 )";
		String myfriend_init_03_sql = "insert into myfriend values ( ?, 3, 0 )";
		String money_init_sql = "insert into money values ( ?, 0, 0, 0, 1)";
		try {

			conn = getConnection();
			stmt = conn.createStatement();

			rs = stmt.executeQuery(countsql);
			rs.next();
			int resultcount = rs.getInt("count(*)");
			ps = conn.prepareStatement(sql);
			ps.setInt(1, resultcount);
			ps.setString(2, m.getId());
			ps.setString(3, m.getPw());
			ps.setString(4, m.getName());
			ps.setString(5, m.getBir());
			ps.setString(6, m.getEml());
			int n = ps.executeUpdate();
			if (n == 1)
				System.out.println("입력성공");// 이거 문구는 바꿀거면 바꾸자
			else
				System.out.println("입력실패");
			dbClose(ps);
			ps = conn.prepareStatement(usercharinfo_init_sql);
			ps.setInt(1, resultcount);
			ps.executeUpdate();
			dbClose(ps);
			ps = conn.prepareStatement(myfriend_init_01_sql);
			ps.setInt(1, resultcount);
			ps.executeUpdate();
			dbClose(ps);
			ps = conn.prepareStatement(myfriend_init_02_sql);
			ps.setInt(1, resultcount);
			ps.executeUpdate();
			dbClose(ps);
			ps = conn.prepareStatement(myfriend_init_03_sql);
			ps.setInt(1, resultcount);
			ps.executeUpdate();
			dbClose(ps);
			ps = conn.prepareStatement(money_init_sql);
			ps.setInt(1, resultcount);
			ps.executeUpdate();
			dbClose(ps);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs, stmt);
		}
	}

	public int confirm(String id, String pw) { // 로그인 소스(?) confirm=확인하다
		int n = -2;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userno, pw from userinfo where id=?";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				int un = rs.getInt(1);
				String dbPw = rs.getString(2);
				if (dbPw.equals(pw))
					n = un;
				else
					n = -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return n;
	}

	public Login_info_Class info_load(int userno) {
		Login_info_Class a = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt = null;
		String itemSql = "select name, mul from item";
		String friendNameSql = "select name from friend";
		String friendLvSql = "select lv from myfriend where userno = ?";
		String elseinfoSql = "select m.curmoney, m.allmoney,  m.automoney, m.tabmoney, u.lv, u.itemno  from    money m, usercharinfo u where m.userno = ?  and u.userno = ?";

		String[] itemname = new String[4];
		int[] itemfunc = new int[4];

		String[] friendname = new String[4];

		int[] friendlevel = new int[3];

		int curmoney;
		int allmoney;
		int automoney;
		int tabmoney;

		int mylevel;
		int myitem;

		conn = getConnection();

		try {
			int i = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(itemSql);
			while (rs.next()) {
				if (i != 0) {
					itemname[i - 1] = rs.getString("name");
					itemfunc[i - 1] = rs.getInt("mul");
				}
				i++;
			}
			dbClose(rs, stmt);

			i = 0;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(friendNameSql);
			while (rs.next()) {
				friendname[i] = new String();
				friendname[i] = rs.getString(1);
				i++;
			}
			dbClose(rs, stmt);

			i = 0;
			ps = conn.prepareStatement(friendLvSql);
			ps.setInt(1, userno);
			rs = ps.executeQuery();
			while (rs.next()) {
				friendlevel[i] = rs.getInt(1);
				i++;
			}
			dbClose(rs, ps);

			ps = conn.prepareStatement(elseinfoSql);
			ps.setInt(1, userno);
			ps.setInt(2, userno);
			rs = ps.executeQuery();
			rs.next();
			curmoney = rs.getInt(1);
			allmoney = rs.getInt(2);
			automoney = rs.getInt(3);
			tabmoney = rs.getInt(4);
			mylevel = rs.getInt(5);
			myitem = rs.getInt(6);
			dbClose(conn, ps, rs);

			a = new Login_info_Class(itemname, itemfunc, friendname, friendlevel, curmoney, allmoney, automoney,
					tabmoney, mylevel, myitem);
			return a;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}

		return a;
	}

	public void info_save(Update_info_Class info) {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getConnection();

		int curmoney = info.curmoney;
		int tabmoney = info.tabmoney;
		int allmoney = info.allmoney;
		int automoney = info.automoney;
		int mylevel = info.mylevel;
		int myitem = info.myitem;
		int[] myfriendlevel = info.myfriendlevel;
		int userno = info.userno;

		String money_sql = "update money set curMoney=?, allmoney = ?, autoMoney =?, tabMoney =? where userNo=?";
		String myfriend_sql = "update myFriend set lv =? where userNo=? and fno = ?";
		String userCharInfo_sql = "update userCharInfo set itemNo =?, lv = ? where userNo=?";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(money_sql);
			ps.setInt(1, curmoney);
			ps.setInt(2, allmoney);
			ps.setInt(3, automoney);
			ps.setInt(4, tabmoney);
			ps.setInt(5, userno);
			int n = ps.executeUpdate();
			dbClose(conn, ps);
			for (int i = 0; i < 3; i++) {
				conn = getConnection();
				ps = conn.prepareStatement(myfriend_sql);
				ps.setInt(1, myfriendlevel[i]);
				ps.setInt(2, userno);
				ps.setInt(3, i + 1);
				n = ps.executeUpdate();
				dbClose(conn, ps);
			}
			conn = getConnection();
			ps = conn.prepareStatement(userCharInfo_sql);
			ps.setInt(1, myitem);
			ps.setInt(2, mylevel);
			ps.setInt(3, userno);

			n = ps.executeUpdate();

			if (n == 1)
				System.out.println("수정성공");
			else
				System.out.println("수정실패");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	private void dbClose(ResultSet ps, Statement rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {

		}
	}

	@SuppressWarnings("unused")
	private void dbClose(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (Exception e) {

		}
	}

	private void dbClose(PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {

		}
	}

	private void dbClose(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {

		}
	}

	private void dbClose(Connection conn, PreparedStatement ps) {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
		}
	}

	private void dbClose(Connection conn, PreparedStatement ps, ResultSet rs, Statement stmt) {
		try {
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		} catch (Exception e) {
		}
	}
}