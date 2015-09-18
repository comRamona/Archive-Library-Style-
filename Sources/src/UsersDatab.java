import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class UsersDatab {
	private Connection con;
	private String url;

	public UsersDatab() {

		url = "jdbc:mysql://localhost/Arhiva?" + "user=arhiva&password=parola";

		getConnection(url);
	}

	private Connection getConnection(String url) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (java.lang.ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url);
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		return con;
	}

	protected void create() {

		try {
			DatabaseMetaData dbm = con.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "users", null);
			if (!tables.next()) // tabled does not exists
			{

				String sql = "CREATE TABLE users(user VARCHAR(40), parola VARCHAR(40), drepturi VARCHAR(40),PRIMARY KEY(user))";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.executeUpdate();

				// create basic users if they do not exist: user and arhivar
				User u;

				u = new User("user", "", "cauta");
				save(u);

				u = new User("arhivar", "modify", "modifica");
				save(u);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	protected void save(User u) {
		try {
			String sql = "INSERT INTO users VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, u.getUser());
			ps.setString(2, u.getParola());
			ps.setString(3, u.getStatus());
			ps.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	protected void update(User u, String newPass) {
		try {
			String sql = "UPDATE users SET parola=? where user=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newPass);
			ps.setString(2, u.getUser());
			ps.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	protected User search(User u) {
		User res = new User(u.getUser(), "", "none");
		try {
			String sql = "Select * FROM users WHERE user LIKE '%" + u.getUser()
					+ "%'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			if (rs.next()) {

				@SuppressWarnings("unused")
				String user = rs.getString("user");
				String parola = rs.getString("parola");
				if (!parola.equals(u.getParola())) {
					res.setStatus("wpass");
				} else {
					String dr = rs.getString("drepturi");
					res.setStatus(dr);

				}

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return res;

	}

}