import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;

public class Parola extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2506982949969730505L;
	private JTextField user;
	private JPasswordField pveche;
	private JPasswordField pnoua;
	private JPasswordField pnoua2;
	private UsersDatab usdb;

	/**
	 * Create the panel.
	 */
	public Parola() {
		usdb = new UsersDatab();
		setSize(800, 800);
		setLayout(null);

		user = new JTextField();
		user.setBounds(260, 144, 114, 19);
		user.setColumns(10);
		add(user);

		JLabel lblParola = new JLabel("Parola veche");
		lblParola.setBounds(79, 197, 144, 34);
		add(lblParola);

		JLabel label_1 = new JLabel("Utilizator");
		label_1.setBounds(79, 144, 70, 15);
		add(label_1);

		JLabel lblParolaNoua = new JLabel("Parola noua");
		lblParolaNoua.setBounds(79, 266, 144, 15);
		add(lblParolaNoua);

		JLabel lblReintoduceti = new JLabel("Confirma parola noua");
		lblReintoduceti.setBounds(79, 323, 174, 19);
		add(lblReintoduceti);

		pveche = new JPasswordField();
		pveche.setColumns(10);
		pveche.setBounds(260, 205, 114, 19);
		add(pveche);

		pnoua = new JPasswordField();
		pnoua.setColumns(10);
		pnoua.setBounds(260, 264, 114, 19);
		add(pnoua);

		pnoua2 = new JPasswordField();
		pnoua2.setColumns(10);
		pnoua2.setBounds(260, 323, 114, 19);
		add(pnoua2);
		pnoua2.addKeyListener(new userKeyListener());

		JButton btnSchimbaParola = new JButton("Schimba parola");
		btnSchimbaParola.setBounds(149, 396, 144, 62);
		btnSchimbaParola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check();
			}
		});
		add(btnSchimbaParola);
	}

	public void check() {
		String us = user.getText();
		String pass = String.valueOf(pveche.getPassword());
		String n1 = String.valueOf(pnoua.getPassword());
		String n2 = String.valueOf(pnoua2.getPassword());
		User x = new User(us, pass, "none");
		User y = usdb.search(x);
		if (!n1.equals(n2)) {
			JOptionPane.showMessageDialog(null,
					"Cele 2 parole noi nu corespund.");
		} else if (y.isNone()) {
			JOptionPane.showMessageDialog(null, "Utilizatorul nu exista.");
		} else if (y.isWpass()) {
			JOptionPane.showMessageDialog(null, "Parola veche este gresita.");
		} else if (y.isSee()) {
			JOptionPane.showMessageDialog(null,
					"Acest user nu are drepturi de modificare");
		}

		else if (y.isModify()) {
			usdb.update(y, n1);
			JOptionPane.showMessageDialog(null,
					"Modificare efectuata cu success.");
			clear();
		}

	}

	public void clear() {
		user.setText("");
		pveche.setText("");
		pnoua.setText("");
		pnoua2.setText("");
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
