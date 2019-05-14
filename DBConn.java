package TeamProject_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // �ϴ� �α����� �ǰ�, ȸ�������� �Ǵ� �� ������!!
import java.sql.Statement;
import java.util.ArrayList;

//��ü�� �̱������� �������� �ȴ���?!
public class DBConn {
	private DBConn() {
	}

	private static DBConn dbConn = new DBConn();

	public static DBConn getInstance() {
		return dbConn;
	}

	// DBConnection�ϴ°� �����~
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

	// select �����
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
	// select�� �����ڰ� ���� �뵵�̱� ������ ���� ������ ���� �����Ƿ� select�� ����!
	// ��� insert�� �־��ָ� ��!

	// ���̵� �˻��ؼ� ȸ�������� �� �� �ִ� �ҽ��ڵ�!
	public Member_Class selectOne(String id) {
		Member_Class m = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from member where id=?";

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
				m.setBir(rs.getString("age"));
				m.setEml(rs.getString("tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}

		return m;
	}

	public void insert(Member_Class m) {   //ȸ������
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
	            System.out.println("�Է¼���");// �̰� ������ �ٲܰŸ� �ٲ���
	         else
	            System.out.println("�Է½���");
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
				System.out.println("��������");
			else
				System.out.println("��������");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}

	}

	public int confirm(String id, String pw) { // �α��� �ҽ�(?) confirm=Ȯ���ϴ�
		int n = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select userno, pw from member where id=?";
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
					n = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}
		return n;
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
			if (rs!=null)
				rs.close();
			if(stmt != null)
				stmt.close();
		} catch (Exception e) {
		}
	}
}