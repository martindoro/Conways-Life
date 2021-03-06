package conways_life;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;

public class Life {

	static CellsPanel cellsPanel = new CellsPanel();
	static JFrame mainFrame;

	public static void main(String[] args) throws InterruptedException {
		EventQueue.invokeLater(() -> {
			mainFrame = new JFrame("Conway`s Life by Martin Doro");
			mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mainFrame.setLayout(new BorderLayout());
			mainFrame.setSize(new Dimension(676, 698));
			mainFrame.setResizable(false);
			mainFrame.add(cellsPanel, BorderLayout.CENTER);
			mainFrame.add(new ButtonPanel(), BorderLayout.SOUTH);
			mainFrame.add(new RandomSliderPanel(), BorderLayout.WEST);
			mainFrame.setVisible(true);
		});
	}
}
