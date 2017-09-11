package conways_life;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Life {

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			JFrame mainFrame = new JFrame("Conway`s Life");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLayout(new BorderLayout());
			mainFrame.setSize(new Dimension(607, 696));
			mainFrame.setResizable(false);
			mainFrame.add(new CellsPanel(), BorderLayout.LINE_START);
			mainFrame.add(new ButtonPanel(), BorderLayout.SOUTH);
			mainFrame.setVisible(true);
		});

		while (CellsPanel.oneGenerationLifeTime >= 0) {

		}
	}
}
