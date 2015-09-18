import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.UIManager;

public class Adaugare extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7832890262295003223L;
	private JTextField tfNr;
	private JTextField tfStartDos;
	private JTextField tfEndDos;
	private JTextField tfFileDos;
	private JTextField tfVolDos;
	private JTextField tfInvDos;
	private JTextField tfPersDos;
	private JTextField tfRaft;
	private JTextField tfSubraft;
	private JTextField tfPastrare;
	private String nr, startDos, endDos, fileDos, volDos, invDos, persDos,
			raft, subraft, pastrare;
	private String obs, ref, arh;
	private JTextArea taObs;
	private JTextArea taReferitor;
	InstantaDosar dos;
	DosarDb datab;
	private JTextField tfArh;

	public Adaugare() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);

		addComponents();
		String file = "Arhiva.accdb";
		datab = new DosarDb(file);
		datab.create();
		nr = "";
		startDos = "";
		endDos = "";
		fileDos = "";
		volDos = "";
		invDos = "";
		persDos = "";
		raft = "";
		subraft = "";
		pastrare = "";
		obs = "";
		ref = "";
		arh = "";

	}

	public void addComponents() {

		JLabel lblNr = new JLabel("Nr. dosar");
		lblNr.setBounds(24, 53, 189, 31);
		lblNr.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblNr);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		lblNr.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		border = BorderFactory.createLineBorder(Color.black, 2);

		JLabel lblStartDos = new JLabel("Data inceperii");
		lblStartDos.setBounds(24, 137, 189, 31);
		lblStartDos.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblStartDos);

		lblStartDos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		JLabel lblEndDos = new JLabel("Data incheierii");
		lblEndDos.setBounds(24, 221, 189, 31);
		lblEndDos.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblEndDos);
		lblEndDos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		JLabel lblFileDos = new JLabel("File");
		lblFileDos.setBounds(24, 305, 189, 31);
		lblFileDos.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblFileDos);
		lblFileDos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		JLabel lblVolDos = new JLabel("Vol.");
		lblVolDos.setBounds(24, 389, 189, 31);
		lblVolDos.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblVolDos);
		lblVolDos.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		JLabel lblInvDos = new JLabel("Inventariat");
		lblInvDos.setBounds(24, 554, 189, 31);
		lblInvDos.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblInvDos);
		lblInvDos.setBorder(border);

		JLabel lblPersDos = new JLabel("Persoana");
		lblPersDos.setBounds(24, 641, 189, 31);
		lblPersDos.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblPersDos);
		lblPersDos.setBorder(border);

		JLabel lblRaft = new JLabel("Raft");
		lblRaft.setBounds(24, 725, 189, 31);
		lblRaft.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblRaft);
		lblRaft.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		JLabel lblSubraft = new JLabel("Subraft");
		lblSubraft.setBounds(24, 809, 189, 31);
		lblSubraft.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblSubraft);
		lblSubraft.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		JLabel lblObs = new JLabel("Observatii");
		lblObs.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		lblObs.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblObs.setBounds(657, 240, 145, 38);
		add(lblObs);

		JLabel lblReferitor = new JLabel("Referitor");
		lblReferitor.setBounds(657, 20, 134, 31);
		lblReferitor.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblReferitor);
		lblReferitor.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));

		JLabel lblPastrare = new JLabel("Pastrare");
		lblPastrare.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		lblPastrare.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblPastrare.setBounds(24, 473, 189, 31);
		add(lblPastrare);

		tfNr = new JTextField();
		tfNr.setColumns(10);
		tfNr.setBounds(237, 40, 321, 45);
		add(tfNr);

		tfStartDos = new JTextField();
		tfStartDos.setColumns(10);
		tfStartDos.setBounds(237, 125, 321, 45);
		tfStartDos.setText("Exemplu: 01.12.1995");
		add(tfStartDos);

		tfEndDos = new JTextField();
		tfEndDos.setColumns(10);
		tfEndDos.setBounds(237, 210, 321, 45);
		tfEndDos.setText("Exemplu: 01.12.1996");
		add(tfEndDos);

		tfFileDos = new JTextField();
		tfFileDos.setColumns(10);
		tfFileDos.setBounds(237, 295, 321, 45);
		add(tfFileDos);

		tfVolDos = new JTextField();
		tfVolDos.setColumns(10);
		tfVolDos.setBounds(237, 380, 321, 45);
		add(tfVolDos);

		tfPastrare = new JTextField();
		tfPastrare.setColumns(10);
		tfPastrare.setBounds(237, 465, 321, 45);
		add(tfPastrare);

		tfInvDos = new JTextField();
		tfInvDos.setColumns(10);
		tfInvDos.setBounds(237, 550, 321, 45);
		add(tfInvDos);

		tfPersDos = new JTextField();
		tfPersDos.setColumns(10);
		tfPersDos.setBounds(237, 635, 321, 45);
		add(tfPersDos);

		tfRaft = new JTextField();
		tfRaft.setColumns(10);
		tfRaft.setBounds(237, 805, 321, 45);
		add(tfRaft);

		tfSubraft = new JTextField();
		tfSubraft.setColumns(10);
		tfSubraft.setBounds(237, 720, 321, 45);
		add(tfSubraft);
		taReferitor = new JTextArea();
		taReferitor.setLocation(446, 0);
		taReferitor.setBorder(new LineBorder(UIManager
				.getColor("TextField.darkShadow")));

		JScrollPane scrollPane1 = new JScrollPane(taReferitor);

		scrollPane1.setBounds(579, 63, 321, 150);

		add(scrollPane1);

		taObs = new JTextArea();
		taObs.setBorder(new LineBorder(UIManager
				.getColor("TextField.darkShadow")));
		taObs.setRows(2);

		JScrollPane scrollPane2 = new JScrollPane(taObs);

		scrollPane2.setBounds(579, 320, 321, 159);
		add(scrollPane2);

		JButton btnSalveaza = new JButton("Salveaza");
		btnSalveaza.setBounds(657, 515, 153, 70);
		add(btnSalveaza, BorderLayout.EAST);

		JButton buttonReset = new JButton("Resetare campuri");
		buttonReset.setBounds(641, 638, 189, 43);
		buttonReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clear();
			}
		});
		add(buttonReset);

		JLabel lblNrAhriva = new JLabel("Nr. ahriva");
		lblNrAhriva.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		lblNrAhriva.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		lblNrAhriva.setBounds(24, 884, 189, 31);
		add(lblNrAhriva);

		tfArh = new JTextField();
		tfArh.setColumns(10);
		tfArh.setBounds(237, 880, 321, 45);
		add(tfArh);

		JLabel lblConfNomenclaturii = new JLabel("conf. nomenclaturii");
		lblConfNomenclaturii.setBounds(24, 89, 179, 15);
		add(lblConfNomenclaturii);

		btnSalveaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

	}

	public void save() {
		nr = tfNr.getText().toUpperCase().trim();
		startDos = tfStartDos.getText().toUpperCase().trim();
		endDos = tfEndDos.getText().toUpperCase().trim();
		fileDos = tfFileDos.getText().toUpperCase().trim();
		volDos = tfVolDos.getText().toUpperCase().trim();
		invDos = tfInvDos.getText().toUpperCase().trim();
		persDos = tfPersDos.getText().toUpperCase().trim();
		raft = tfRaft.getText().toUpperCase().trim();
		subraft = tfSubraft.getText().toUpperCase().trim();
		pastrare = tfPastrare.getText().toUpperCase().trim();
		obs = taObs.getText().toUpperCase().trim();
		ref = taReferitor.getText().toUpperCase().trim();
		arh = tfArh.getText().toUpperCase().trim();

		if (verif()) {
			dos = new InstantaDosar(nr, startDos, endDos, fileDos, volDos,
					invDos, persDos, raft, subraft, pastrare, obs, ref, arh);
			datab.save(dos);
			clear();

		}

	}

	public boolean verif() {
		boolean v = true;
		if ((nr.equals("") && startDos.equals("") && endDos.equals("")
				&& fileDos.equals("") && volDos.equals("") && invDos.equals("")
				&& persDos.equals("") && raft.equals("") && subraft.equals("")
				&& pastrare.equals("") && obs.equals("") && ref.equals(""))) {
			v = false;
			JOptionPane.showMessageDialog(null,
					"Introduceti mai intai datele de salvat!");

		}
		if ((endDos.contains("EXEMPLU") || startDos.contains("EXEMPLU"))
				&& v == true) { // don't show if there are other errors
			v = false;
			JOptionPane.showMessageDialog(null, "Introduceti data corecta!");
		}

		if ((!startDos.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) && v == true
				&& !startDos.equals("")) {
			v = false;
			JOptionPane.showMessageDialog(null,
					"Introduceti data in format dd.mm.yyyy !");
		}
		if ((!endDos.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) && v == true
				&& !endDos.equals("")) {
			v = false;
			JOptionPane.showMessageDialog(null,
					"Introduceti data in format dd.mm.yyyy !");
		}

		if (startDos.matches("\\d{2}\\.\\d{2}\\.\\d{4}")
				&& endDos.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
			DateFormat sourceFormat = new SimpleDateFormat("dd.MM.yyyy");

			try {
				Date date = sourceFormat.parse(startDos);
				Date date2 = sourceFormat.parse(endDos);

				if (date2.before(date)) {
					v = false;
					JOptionPane
							.showMessageDialog(null,
									"Data incheierii trebuie sa fie inaintea datei initiale!");
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return v;
	}

	public void clear() {
		tfNr.setText("");
		tfStartDos.setText("");
		tfEndDos.setText("");
		tfFileDos.setText("");
		tfVolDos.setText("");
		tfInvDos.setText("");
		tfPersDos.setText("");
		tfRaft.setText("");
		tfSubraft.setText("");
		tfPastrare.setText("");
		taObs.setText("");
		taReferitor.setText("");
		tfArh.setText("");
	}
}
