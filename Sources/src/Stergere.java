import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JList;
import javax.swing.JTable;

public class Stergere extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1828238597919910739L;
	private ArrayList<InstantaDosar> dlist;
	private JTextField tfNr;
	private JTextField tfPersDos;
	private String nr, startDos, endDos, fileDos, volDos, invDos, persDos,
			raft, subraft, pastrare;
	private String obs, ref, arh;
	private JList<InstantaDosar> listres;
	private DefaultListModel<InstantaDosar> model;
	private JTextArea taDetalii;
	private InstantaDosar dos;
	private DosarDb datab;
	private JButton btnStergeInregistrareaCurenta;
	private ArrayList<Istoric> ist;
	@SuppressWarnings("unused")
	private int index;

	public Stergere() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize(1200, 900);
		addComponents();
		String file = "Arhiva.accdb";
		datab = new DosarDb(file);
		datab.create();
		dlist = new ArrayList<InstantaDosar>();
		ist = new ArrayList<Istoric>();
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
		index = -1;

	}

	public void addComponents() {
		JLabel lblNr = new JLabel("Nr. dosar");
		lblNr.setBounds(24, 53, 189, 31);
		lblNr.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblNr);
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		lblNr.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		border = BorderFactory.createLineBorder(Color.black, 2);

		JLabel lblPersDos = new JLabel("Persoana");
		lblPersDos.setBounds(24, 140, 189, 31);
		lblPersDos.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		add(lblPersDos);
		lblPersDos.setBorder(border);

		tfNr = new JTextField();
		tfNr.setColumns(10);
		tfNr.setBounds(237, 40, 321, 45);
		add(tfNr);

		tfPersDos = new JTextField();
		tfPersDos.setColumns(10);
		tfPersDos.setBounds(237, 136, 321, 45);
		add(tfPersDos);

		taDetalii = new JTextArea();
		taDetalii.setBorder(new LineBorder(UIManager
				.getColor("TextField.darkShadow")));
		taDetalii.setRows(2);

		JScrollPane scrollPane1 = new JScrollPane(taDetalii);

		scrollPane1.setBounds(38, 333, 341, 512);

		add(scrollPane1);

		JButton btnCauta = new JButton("Cauta");
		btnCauta.setBounds(714, 36, 153, 70);
		add(btnCauta, BorderLayout.EAST);
		btnCauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.clear();
				cauta();
			}
		});

		JButton btnStergereNoua = new JButton("Resetare campuri");
		btnStergereNoua.setBounds(698, 140, 189, 43);
		btnStergereNoua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		add(btnStergereNoua);

		model = new DefaultListModel<InstantaDosar>();

		listres = new JList<InstantaDosar>(model);

		listres.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					InstantaDosar afis = (InstantaDosar) listres
							.getSelectedValue();

					try {
						String s = "Nr. " + afis.getNr() + "\nPersoana: "
								+ afis.getPersDos() + "\nData inceperii" + ": "
								+ afis.getStartDos() + "\nData incheierii: "
								+ afis.getEndDos() + "\nVol.: "
								+ afis.getVolDos() + "" + "\nNr. inventar: "
								+ afis.getInvDos() + "\nTermen pastrare:"
								+ afis.getPastrare() + "" + "\nReferitor:\n"
								+ afis.getRef() + "\nObservatii:\n"
								+ afis.getObs() + "\nRaft" + ": "
								+ afis.getRaft() + "\nSubraft: "
								+ afis.getSubraft() + "\nNr. arhiva: "
								+ afis.getArh();

						tfNr.setText(afis.getNr());

						tfPersDos.setText(afis.getPersDos());
						taDetalii.setText(s);

						index = afis.getId();
						btnStergeInregistrareaCurenta.setEnabled(true);
					} catch (Exception e) {
						// super important, enters here when you clear the model
						index = -1;

					}

				}

			}
		});

		JScrollPane scrollPane = new JScrollPane(listres);

		scrollPane.setBounds(956, 63, 332, 708);
		add(scrollPane);

		JLabel lblInformatiiDosarSelectat = new JLabel(
				"Informatii dosar selectat");
		lblInformatiiDosarSelectat.setFont(new Font("DejaVu Sans", Font.BOLD,
				22));
		lblInformatiiDosarSelectat.setBorder(new LineBorder(new Color(0, 0, 0),
				2, true));
		lblInformatiiDosarSelectat.setBounds(24, 233, 355, 43);
		add(lblInformatiiDosarSelectat);

		JLabel lblSelectatiDosarulDorit = new JLabel("Selectati dosarul dorit");
		lblSelectatiDosarulDorit
				.setFont(new Font("DejaVu Sans", Font.BOLD, 14));
		lblSelectatiDosarulDorit.setBorder(new LineBorder(new Color(0, 0, 0),
				2, true));
		lblSelectatiDosarulDorit.setBounds(977, 12, 211, 38);
		add(lblSelectatiDosarulDorit);

		btnStergeInregistrareaCurenta = new JButton(
				"Sterge inregistrarea selectata");
		btnStergeInregistrareaCurenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sterge();
			}
		});
		btnStergeInregistrareaCurenta.setBounds(409, 342, 256, 54);
		btnStergeInregistrareaCurenta.setEnabled(false);
		add(btnStergeInregistrareaCurenta);

		JLabel label = new JLabel("conf. nomenclaturii");
		label.setBounds(34, 85, 179, 15);
		add(label);

		JLabel lblSauAn = new JLabel("sau an");
		lblSauAn.setBounds(34, 96, 179, 15);
		add(lblSauAn);

	}

	public void results() {
		InstantaDosar p = (InstantaDosar) listres.getSelectedValue();
		ist = datab.cautaImprumut(p);
		JFrame frame = new JFrame("TableDemo");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JPanel newContentPane = new JPanel();
		newContentPane.setOpaque(true); // content panes must be opaque

		String[] col = { "Numar dosar", "Responsabil", "Nume", "Prenume",
				"Data imprumut", "Data restituirii" };
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		JTable table = new JTable(tableModel);
		for (Istoric i : ist)
			tableModel.addRow(i.matr());
		table.setPreferredScrollableViewportSize(new Dimension(800, 800));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		newContentPane.add(scrollPane);
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public void cauta() {
		btnStergeInregistrareaCurenta.setEnabled(false);
		nr = tfNr.getText().toUpperCase();

		dos = new InstantaDosar(nr, startDos, endDos, fileDos, volDos, invDos,
				persDos, raft, subraft, pastrare, obs, ref, arh);
		dlist = datab.search(dos);
		if (!verif()) {
		} else {

			if (dlist.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Nu a fost gasit niciun rezultat.");

			} else {

				listres.clearSelection();
				model.clear();

				for (InstantaDosar i : dlist)
					model.addElement(i);

			}
		}

	}

	public void sterge() {
		InstantaDosar dos = (InstantaDosar) listres.getSelectedValue();
		datab.delete(dos);

	}

	public boolean verif() {
		boolean v = true;

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
									"Data finala trebuie sa fie inaintea celei initiale!");
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

		tfPersDos.setText("");

		taDetalii.setText("");
		listres.clearSelection();
		model.clear();
		index = -1;

	}
}
