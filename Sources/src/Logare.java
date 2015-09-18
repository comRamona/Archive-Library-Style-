import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class Logare extends JPanel {

	private static final long serialVersionUID = -2415940256285752741L;
	private JTextField user;
	private JPasswordField parola;
	private JTabbedPane tabbedPane;
	private JLabel lblNewLabel;
	private JLabel lblParola;
	private JButton btnLogare;
	private JComponent cautare1, adaugare4, modificare5, imprumutare2,
			restituire3, parola6, stergere51;
	private UsersDatab usdb;

	/**
	 * Create the panel.
	 * 
	 */
	public Logare(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
		setLayout(null);

		usdb = new UsersDatab();

		usdb.create();
		lblNewLabel = new JLabel("Utilizator");
		lblNewLabel.setBounds(155, 130, 70, 15);
		add(lblNewLabel);

		lblParola = new JLabel("Parola");
		lblParola.setBounds(155, 198, 70, 15);
		add(lblParola);

		user = new JTextField();
		user.setBounds(328, 128, 114, 19);
		add(user);
		user.setColumns(10);

		parola = new JPasswordField();
		parola.setBounds(328, 196, 114, 19);
		add(parola);
		parola.setColumns(10);

		btnLogare = new JButton("Autentificare");
		btnLogare.setBounds(254, 306, 141, 56);
		btnLogare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		add(btnLogare);
		user.addKeyListener(new userKeyListener());
		parola.addKeyListener(new userKeyListener());

	}

	public void check() {

		String us = user.getText();
		String pass = String.valueOf(parola.getPassword());
		User x = new User(us, pass, "none");
		int nr = tabbedPane.getTabCount();
		User y = usdb.search(x);
		if (y.isSee()) {
			nr = tabbedPane.getTabCount();
			tabbedPane.setSelectedIndex(0);
			int i = nr - 1;
			while (i != 0) {
				try {
					tabbedPane.removeTabAt(i);
					i--;

				} catch (Exception e) {
					System.out.println("tab " + i + " " + e.getMessage());
				}
			}
			Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (0.9 * screenSize2.getWidth());
			int height = (int) (0.9 * screenSize2.getHeight());
			Dimension d1 = new Dimension(width, height);
			cautare1 = new Cautare();
			cautare1.setPreferredSize(d1);
			JScrollPane cc1 = new JScrollPane(cautare1);
			tabbedPane.addTab("...Cauta", cc1);

			imprumutare2 = new Imprumutare();
			imprumutare2.setPreferredSize(d1);
			JScrollPane ii2 = new JScrollPane(imprumutare2);
			tabbedPane.addTab("Imprumuta", ii2);
			restituire3 = new Restituire();
			restituire3.setPreferredSize(d1);
			JScrollPane r3 = new JScrollPane(restituire3);
			tabbedPane.addTab("Restituie", r3);

			tabbedPane.setSelectedIndex(1);

		}

		if (y.isModify()) {
			nr = tabbedPane.getTabCount();
			int i = nr - 1;

			try {
				while (i != 0) {
					tabbedPane.removeTabAt(i);
					i--;
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
			int width = (int) (0.9 * screenSize2.getWidth());
			int height = (int) (0.9 * screenSize2.getHeight());
			Dimension screenSize = new Dimension(width, height);
			cautare1 = new Cautare();
			cautare1.setPreferredSize(screenSize);
			JScrollPane c1 = new JScrollPane(cautare1);
			tabbedPane.addTab("...Cauta", c1);

			imprumutare2 = new Imprumutare();
			imprumutare2.setPreferredSize(screenSize);
			JScrollPane i2 = new JScrollPane(imprumutare2);
			tabbedPane.addTab("Imprumuta", i2);

			restituire3 = new Restituire();
			restituire3.setPreferredSize(screenSize);
			JScrollPane r3 = new JScrollPane(restituire3);
			tabbedPane.addTab("Restituie", r3);

			adaugare4 = new Adaugare();
			adaugare4.setPreferredSize(screenSize);
			JScrollPane a4 = new JScrollPane(adaugare4);
			tabbedPane.addTab("+Adauga", a4);

			modificare5 = new Modificare();
			modificare5.setPreferredSize(screenSize);
			JScrollPane m5 = new JScrollPane(modificare5);
			tabbedPane.addTab("Modifica", m5);

			stergere51 = new Stergere();
			stergere51.setPreferredSize(screenSize);
			JScrollPane s51 = new JScrollPane(stergere51);
			tabbedPane.addTab("Sterge", s51);

			parola6 = new Parola();
			parola6.setPreferredSize(screenSize);
			JScrollPane p6 = new JScrollPane(parola6);
			tabbedPane.addTab("Schimba parola", p6);

			try {
				tabbedPane.setSelectedIndex(4);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		if (y.isWpass()) {
			JOptionPane.showMessageDialog(null, "Parola gresita.");
		}
		if (y.isNone()) {
			JOptionPane.showMessageDialog(null, "Utilizatorul nu exista.");

		}
		parola.setText("");

	}

	private class userKeyListener implements KeyListener {
		public void keyTyped(KeyEvent e) {

		}

		/** Handle the key-pressed event from the text field. */
		public void keyPressed(KeyEvent e) {

		}

		/** Handle the key-released event from the text field. */
		public void keyReleased(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER)
				check();
		}
	}

}
