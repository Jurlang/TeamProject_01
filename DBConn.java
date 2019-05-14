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
		String user = "jmh";
		String passwd = "1111";
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
		try {

			conn = getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(countsql);
			rs.next();
			int resultcount = rs.getInt("count(*)");
			System.out.println(resultcount);
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
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs, stmt);
		}
	}

	public void update(Member_Class m) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update member set pw=?, name=?, bir=?, eml=? where id=?";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, m.getPw());
			ps.setString(2, m.getName());
			ps.setString(3, m.getBir());
			ps.setString(4, m.getEml());
			ps.setString(5, m.getId());

			int n = ps.executeUpdate();
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

	public void info_load(int userno, Login_info_Class l) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement stmt = null;
		String itemSql = "select name, mul from item";
		String friendNameSql = "select name from friend";
		String friendLvSql = "select lv from myfriend";
		String elseinfoSql = "select m.curmoney, m.automoney, m.tabmoney, u.lv, u.itemno  from    money m, usercharinfo u where m.userno = 0  and u.userno = ?";
		
		String[] itemname = new String[4];
		int[] itemfunc = new int[4];;
		
		String[] friendname = new String[4];
		
		int[] friendlevel = new int[3];

		int curmoney;
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
				System.out.println(i);
				if (i != 0) {
					itemname[i-1] = rs.getString("name");
					itemfunc[i-1] = rs.getInt("mul");
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
			stmt = conn.createStatement();
			rs = stmt.executeQuery(friendLvSql);
			while (rs.next()) {
				friendlevel[i] = rs.getInt(1);
				i++;
			}
			dbClose(rs, stmt);

			ps = conn.prepareStatement(elseinfoSql);
			ps.setInt(1, userno);
			rs = ps.executeQuery();
			rs.next();
			curmoney = rs.getInt(1);
			automoney = rs.getInt(2);
			tabmoney = rs.getInt(3);
			mylevel = rs.getInt(4);
			myitem = rs.getInt(5);
			dbClose(conn, ps, rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
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

	private void dbClose(PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
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