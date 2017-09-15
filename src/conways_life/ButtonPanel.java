package conways_life;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonPanel extends JPanel{

	private static final long serialVersionUID = -7081739959526621292L;

	Hashtable<Integer, JLabel> table = new Hashtable<Integer, JLabel>();

	public ButtonPanel() {
		setLayout(new GridLayout(0, 4));

		table.put(0, new JLabel("min"));
		table.put(50, new JLabel("max"));
		JSlider lifeSpeed = new JSlider(0, 50, (int) Math.sqrt(CellsPanel.oneGenerationLifeTime));
		lifeSpeed.setInverted(true);
		lifeSpeed.setLabelTable(table);
		lifeSpeed.setBorder(new TitledBorder("One Generation Lifetime"));
		lifeSpeed.setMinorTickSpacing(10);
		lifeSpeed.setMajorTickSpacing(50);
		lifeSpeed.setPaintTicks(true);
		lifeSpeed.setPaintLabels(true);
		lifeSpeed.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				int lifeTime = source.getValue();
				CellsPanel.setOneGenerationLifeTime((long) Math.pow(lifeTime, 2));
			}
		});

		JButton start = new JButton();
		start.setText("Start Life");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Life.isRunning = true;
			}
		});

		JButton generateNewRandomBoard = new JButton();
		generateNewRandomBoard.setText("Generate NEW Random Board");
		generateNewRandomBoard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Life.mainFrame.add(Life.cellsPanel);
			}
		});

		JButton stop = new JButton();
		stop.setText("Pause Life");
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Life.isRunning = false;
			}
		});
		add(lifeSpeed);
		add(start);
		add(generateNewRandomBoard);
		add(stop);
		setVisible(true);
		repaint();

	}
}
