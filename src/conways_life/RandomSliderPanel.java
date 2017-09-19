package conways_life;

import java.awt.GridLayout;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class RandomSliderPanel extends JPanel {

	private static final long serialVersionUID = -3987198420644975680L;
	Hashtable<Integer, JLabel> randomTable = new Hashtable<Integer, JLabel>();
	public static int randomLiveCell = 33;

	public static int getRandomLiveCell() {
		return randomLiveCell;
	}

	public static void setRandomLiveCell(int randomLiveCell) {
		RandomSliderPanel.randomLiveCell = randomLiveCell;
	}

	public RandomSliderPanel() {
		setLayout(new GridLayout(1, 1));

		randomTable.put(10, new JLabel("10 %"));
		randomTable.put(25, new JLabel("25 %"));
		randomTable.put(50, new JLabel("50 %"));
		randomTable.put(75, new JLabel("75 %"));
		JSlider randomLife = new JSlider(JSlider.VERTICAL, 10, 75, randomLiveCell);
		randomLife.setLabelTable(randomTable);
		randomLife.setBorder(new TitledBorder("Fill"));
		randomLife.setMinorTickSpacing(5);
		randomLife.setMajorTickSpacing(25);
		randomLife.setPaintTicks(true);
		randomLife.setPaintLabels(true);
		randomLife.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				int randomPercentage = source.getValue();
				setRandomLiveCell(randomPercentage);
			}
		});
		add(randomLife);
		setVisible(true);
		repaint();
	}
}
