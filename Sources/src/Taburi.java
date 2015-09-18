import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Taburi extends JPanel {
	/**
	 * 
	 */
	JComponent auth;
	JTextField textField;
	JTabbedPane tabbedPane;
	private static final long serialVersionUID = -6891779864737016852L;

	public Taburi() {
		super(new GridLayout(1, 1));
		tabbedPane = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);

		auth = new Logare(tabbedPane);
		setSize(1000, 1000);
		tabbedPane.addTab("Logare", new JScrollPane(auth));
		// Add the tabbed pane to this panel.
		add(tabbedPane);

		// The following line enables to use scrolling tabs.
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

	}

}
