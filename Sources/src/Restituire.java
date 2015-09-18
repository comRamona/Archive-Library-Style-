import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultListModel;
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

public class Restituire extends JPanel {

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
	private JButton btnrestituie;
	private JList<InstantaDosar> listres;
	DefaultListModel<InstantaDosar> model;
	private JTextArea taDetalii;
	InstantaDosar dos;
	DosarDb datab;
	JTextField prenumeImp, numeImp;
	@SuppressWarnings("unused")
	private int index;

	public Restituire() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		setSize(1200, 900);
		addComponents();
		String file = "Arhiva.accdb";
		datab = new DosarDb(file);
		datab.create();
		dlist = new ArrayList<InstantaDosar>();
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
		Border border = BorderFactory.createLineBorder(Color.black, 2);
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
		btnCauta.setBounds(714, 12, 153, 70);
		add(btnCauta, BorderLayout.EAST);
		btnCauta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				model.clear();
				cauta();
			}
		});

		JButton btnRestituireNoua = new JButton("Resetare campuri");
		btnRestituireNoua.setBounds(698, 140, 189, 43);
		btnRestituireNoua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		add(btnRestituireNoua);

		model = new DefaultListModel<InstantaDosar>();

		listres = new JList<InstantaDosar>(model);

		listres.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					InstantaDosar afis = (InstantaDosar) listres
							.getSelectedValue();

					try {
						String s = "Nr. " + afis.getNr()
								+ "\nPersoana responsabila: "
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
						btnrestituie.setEnabled(true);

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

		JLabel label = new JLabel("Nr. dosar");
		label.setFont(new Font("DejaVu Sans", Font.BOLD, 22));
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setBounds(24, 44, 189, 31);
		add(label);
		JLabel label2 = new JLabel("conf. nomenclaturii");
		label2.setBounds(38, 76, 179, 15);
		add(label2);

		btnrestituie = new JButton("Restituie dosar selectat");
		btnrestituie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restituie();
			}
		});
		btnrestituie.setBounds(433, 333, 239, 79);
		btnrestituie.setEnabled(false);
		add(btnrestituie);
		btnrestituie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

	}

	public void restituie() {
		InstantaDosar dos1;
		if (listres.getSelectedValue() == null)
			JOptionPane.showMessageDialog(null,
					"Cautati si selectati mai intai o inregistrare!");
		else {
			dos1 = (InstantaDosar) listres.getSelectedValue();

			numeImp = new JTextField();
			prenumeImp = new JTextField();
			Object[] message = { "Nume:", numeImp, "Prenume:", prenumeImp };

			int option = JOptionPane.showConfirmDialog(null, message,
					"Trimite date", JOptionPane.OK_CANCEL_OPTION);
			String x = numeImp.getText().toUpperCase();
			String y = prenumeImp.getText().toUpperCase();
			if (option != JOptionPane.CANCEL_OPTION) {
				datab.restituie(dos1, x, y);
			}
		}

	}

	public void cauta() {
		btnrestituie.setEnabled(false);
		nr = tfNr.getText().toUpperCase();
        persDos=tfPersDos.getText().toUpperCase();
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
		btnrestituie.setEnabled(false);

	}
}
