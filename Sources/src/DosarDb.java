import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class DosarDb {
	private Connection con;
	private String url;
	private ArrayList<InstantaDosar> dlist;

	public DosarDb(String file) {
		dlist = new ArrayList<InstantaDosar>();
		url = "jdbc:mysql://localhost/Arhiva?" + "user=arhiva&password=arhiva";

		// System.out.println(url);
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
			ResultSet tables = dbm.getTables(null, null, "dosare", null);
			if (!tables.next()) // tabled does not exists
			{

				String sql = "CREATE TABLE dosare(id INT NOT NULL AUTO_INCREMENT, "
						+ "NrDos VARCHAR(50), RefDos VARCHAR(50), StartDos VARCHAR(50), "
						+ "EndDos VARCHAR(200), VolDos VARCHAR(100), InvDos VARCHAR(100), "
						+ "PersDos VARCHAR(200), FileDos VARCHAR(200), PastrareDos VARCHAR(200), "
						+ "Raft VARCHAR(200), Subraft VARCHAR(200), Obs VARCHAR(2000), Arh VARCHAR(40), PRIMARY KEY(id))";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.executeUpdate();

			}
			ResultSet impr = dbm.getTables(null, null, "imprumuturi", null);
			if (!impr.next()) {
				String sql = "CREATE TABLE imprumuturi(id INT NOT NULL, nume VARCHAR(20), prenume VARCHAR(50), dimpr timestamp default now(),drest timestamp NULL,PRIMARY KEY(id,nume,prenume,dimpr))";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.executeUpdate();
				String sql2 = "ALTER TABLE imprumuturi ADD FOREIGN KEY(id) REFERENCES dosare(id) ON DELETE CASCADE";
				PreparedStatement ps2 = con.prepareStatement(sql2);
				ps2.executeUpdate();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	protected void imprumuta(InstantaDosar p, String nume, String prenume) {
		try {
			String sql = "INSERT INTO imprumuturi(id,nume,prenume) VALUES (?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, p.getId());
			ps.setString(2, nume);
			ps.setString(3, prenume);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void delete(InstantaDosar i) {
		try {
			String sql = "DELETE FROM dosare WHERE id=" + i.getId();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "Dosarul a fost sters");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	protected ArrayList<Istoric> cautaImprumut(InstantaDosar p) {
		ArrayList<Istoric> rez = new ArrayList<Istoric>();
		try {
			int id = p.getId();
			String sql = "SELECT d.NrDos, d.PersDos,i.nume,i.prenume,i.dimpr,i.drest FROM dosare AS d,imprumuturi AS i WHERE d.id=i.id AND d.id="
					+ id;
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			String nr, pers, nume, prenume, d1, d2;
			java.util.Date date1, date2;
			while (rs.next()) {
				nr = rs.getString("d.NrDos");
				pers = rs.getString("d.PersDos");
				nume = rs.getString("i.nume");
				prenume = rs.getString("i.prenume");
				date1 = rs.getTimestamp("i.dimpr");
				SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYY");
				d1 = sdf.format(date1);
				date2 = rs.getTimestamp("i.drest");
				if (date2 == null)
					d2 = "";
				else
					d2 = sdf.format(date2);
				Istoric entry = new Istoric(nr, pers, nume, prenume, d1, d2);
				rez.add(entry);

			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		return rez;
	}

	protected void restituie(InstantaDosar p, String nume, String prenume) {
		try {
			String sql = "UPDATE imprumuturi SET drest=? where id=? and nume=? and prenume=? and drest is NULL";
			PreparedStatement ps = con.prepareStatement(sql);
			Calendar calendar = Calendar.getInstance();
			java.util.Date now = calendar.getTime();
			java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(
					now.getTime());
			ps.setTimestamp(1, currentTimestamp);
			ps.setInt(2, p.getId());
			ps.setString(3, nume);
			ps.setString(4, prenume);
			int i = ps.executeUpdate();
			if (i <= 0)
				JOptionPane.showMessageDialog(null,
						"Niciun imprumut realizat sub acest nume");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	protected void save(InstantaDosar p) {
		try {
			String sql = "INSERT INTO dosare(NrDos,RefDos,StartDos,"
					+ "EndDos,VolDos,InvDos,PersDos,FileDos,PastrareDos,Raft,Subraft,Obs,Arh) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getNr());
			ps.setString(2, p.getRef());
			ps.setString(3, p.getStartDos());
			ps.setString(4, p.getEndDos());
			ps.setString(5, p.getVolDos());
			ps.setString(6, p.getInvDos());
			ps.setString(7, p.getPersDos());
			ps.setString(8, p.getFileDos());
			ps.setString(9, p.getPastrare());
			ps.setString(10, p.getRaft());
			ps.setString(11, p.getSubraft());
			ps.setString(12, p.getObs());
			ps.setString(13, p.getArh());
			ps.executeUpdate();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	protected void updateP(InstantaDosar p, int id) {
		try {
			String sql = "UPDATE dosare SET nrDos=?,RefDos=?,StartDos=?,"
					+ "EndDos=?,VolDos=?,InvDos=?,PersDos=?,FileDos=?,PastrareDos=?,Raft=?,Subraft=?,Obs=?,Arh=? where id=?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getNr());
			ps.setString(2, p.getRef());
			ps.setString(3, p.getStartDos());
			ps.setString(4, p.getEndDos());
			ps.setString(5, p.getVolDos());
			ps.setString(6, p.getInvDos());
			ps.setString(7, p.getPersDos());
			ps.setString(8, p.getFileDos());
			ps.setString(9, p.getPastrare());
			ps.setString(10, p.getRaft());
			ps.setString(11, p.getSubraft());
			ps.setString(12, p.getObs());
			ps.setString(13, p.getArh());
			ps.setInt(14, id);
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

	}

	protected ArrayList<InstantaDosar> search(InstantaDosar p) {

		try {
			dlist = new ArrayList<InstantaDosar>();
			String sql = "Select * FROM dosare WHERE NrDos LIKE '%" + p.getNr()
					+ "%' AND RefDos LIKE '%" + p.getRef()
					+ "%' AND StartDos LIKE '%" + "" + p.getStartDos()
					+ "%' AND EndDos LIKE '%" + p.getEndDos()
					+ "%' AND VolDos LIKE '%" + p.getVolDos() + "%'"
					+ " AND InvDos LIKE '%" + p.getInvDos()
					+ "%'AND PersDos LIKE '%" + "" + p.getPersDos()
					+ "%' AND FileDos LIKE '%" + p.getFileDos()
					+ "%' AND PastrareDos LIKE '%" + p.getPastrare()
					+ "%' AND Raft LIKE '%" + "" + p.getRaft()
					+ "%' AND Subraft LIKE '%" + p.getSubraft()
					+ "%' AND Obs LIKE '%" + p.getObs() + "%' AND Arh LIKE '%"
					+ p.getArh() + "%'";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			String nr, startDos, endDos, fileDos, volDos, invDos, persDos, raft, subraft, pastrare, obs, ref, arh;
			while (rs.next()) {
				int id = rs.getInt("id");
				nr = rs.getString("NrDos");
				startDos = rs.getString("StartDos");
				endDos = rs.getString("EndDos");
				fileDos = rs.getString("FileDos");
				volDos = rs.getString("VolDos");
				invDos = rs.getString("InvDos");
				persDos = rs.getString("PersDos");
				raft = rs.getString("Raft");
				subraft = rs.getString("Subraft");
				pastrare = rs.getString("PastrareDos");
				obs = rs.getString("Obs");
				ref = rs.getString("RefDos");
				arh = rs.getString("Arh");
				InstantaDosar dos = new InstantaDosar(nr, startDos, endDos,
						fileDos, volDos, invDos, persDos, raft, subraft,
						pastrare, obs, ref, arh);
				dos.setId(id);
				dlist.add(dos);

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		return dlist;

	}

}