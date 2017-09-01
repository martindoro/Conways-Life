package conways_life;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Life {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame mainFrame = new JFrame("Conway`s Life");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLayout(new BorderLayout(0, 20));
			mainFrame.getContentPane().add(new CellsPanel(), BorderLayout.WEST);
			mainFrame.getContentPane().add(new ButtonPanel(), BorderLayout.EAST);
			// mainFrame.setPreferredSize(new Dimension(1000, 770));
			mainFrame.pack();
			mainFrame.setVisible(true);
		});
	}
}
