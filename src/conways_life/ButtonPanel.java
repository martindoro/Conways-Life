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

	private static final long serialVersionUID = -4961176929105495364L;
	static boolean isRunning = true;
	public long oneGenerationLifeTime = 1000;

	Hashtable<Integer, JLabel> speedTable = new Hashtable<Integer, JLabel>();

	public long getOneGenerationLifeTime() {
		return oneGenerationLifeTime;
	}

	public void setOneGenerationLifeTime(long oneGenerationLifeTime) {
		this.oneGenerationLifeTime = oneGenerationLifeTime;
	}

	public ButtonPanel() {
		setLayout(new GridLayout(0, 4));

		speedTable.put(0, new JLabel("min"));
		speedTable.put(50, new JLabel("max"));
		JSlider lifeSpeed = new JSlider(0, 50, (int) Math.sqrt(oneGenerationLifeTime));
		lifeSpeed.setInverted(true);
		lifeSpeed.setLabelTable(speedTable);
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
				setOneGenerationLifeTime((long) Math.pow(lifeTime, 2));
			}
		});

		JButton start = new JButton();
		start.setText("Start Life");
		start.addActionListener(e -> {
			start.setEnabled(false);
			isRunning= true;
			new Thread("Life") {
				@Override
				public void run() {
					while (isRunning) {
						Life.cellsPanel.oneGenerationCycle();
						try {
							Thread.sleep(oneGenerationLifeTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		});

		JButton generateNewRandomBoard = new JButton();
		generateNewRandomBoard.setText("NEW Random Board");
		generateNewRandomBoard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Life.cellsPanel.clearCells();
				Life.cellsPanel.randomCellGenerator();
				Life.mainFrame.validate();
			}
		});

		JButton stop = new JButton();
		stop.setText("Pause Life");
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				isRunning = false;
				start.setEnabled(true);
			}
		});
		add(generateNewRandomBoard);
		add(stop);
		add(lifeSpeed);
		add(start);
		setVisible(true);
		repaint();

	}
}
