package TeamProject_01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // �ϴ� �α����� �ǰ�, ȸ�������� �Ǵ� �� ������!!
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
	public ArrayList<PMember> selectAll() {
		ArrayList<PMember> list = new ArrayList<PMember>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM member";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				PMember m = new PMember();
				m.setId(rs.getString("id"));
				m.setPw(rs.getString("pw"));
				m.setName(rs.getString("name"));
				m.setBir(rs.getInt("bir"));
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
	public PMember selectOne(String id) {
		PMember m = null;
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
				m = new PMember();
				m.setId(rs.getString("id"));
				m.setPw(rs.getString("pw"));
				m.setName(rs.getString("name"));
				m.setBir(rs.getInt("age"));
				m.setEml(rs.getString("tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps, rs);
		}

		return m;
	}

	public void insert(PMember m) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into member(id,pw,name,age,tel) values(?,?,?,?,?)";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, m.getId());
			ps.setString(2, m.getPw());
			ps.setString(3, m.getName());
			ps.setInt(4, m.getBir());
			ps.setString(5, m.getEml());

			int n = ps.executeUpdate();
			if (n == 1)
				System.out.println("�Է¼���");// �̰� ������ �ٲܰŸ� �ٲ���
			else
				System.out.println("�Է½���");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(conn, ps);
		}
	}

	public void update(PMember m) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update member set pw=?, name=?, bir=?, eml=? where id=?";

		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, m.getPw());
			ps.setString(2, m.getName());
			ps.setInt(3, m.getBir());
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
		String sql = "select pw from member where id=?";
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				String dbPw = rs.getString(1);
				if (dbPw.equals(pw))
					n = 1;
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
}