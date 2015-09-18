import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Launcher extends JFrame {

	private static final long serialVersionUID = -4796392158436621989L;
	Taburi tabs;

	public Launcher() {
		tabs = new Taburi();
		add(tabs, BorderLayout.CENTER);
		// setResizable(false);
		setTitle("Arhiva");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new Launcher();
				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int width = (int) (0.9 * screenSize.getWidth());
				int height = (int) (0.9 * screenSize.getHeight());
				frame.setSize(width, height);
				frame.setVisible(true);
				frame.setResizable(true);

			}
		});

	}
}
