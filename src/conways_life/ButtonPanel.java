package conways_life;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonPanel extends JPanel{

	private static final long serialVersionUID = -7081739959526621292L;

	public ButtonPanel() {
		setLayout(new GridLayout(0, 3));

		JSlider lifeSpeed = new JSlider(0, 100, (int) Math.sqrt(CellsPanel.oneGenerationLifeTime));
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
				CellsPanel.setOneGenerationLifeTime(lifeSpeed.getValue());
			}
		});

		JButton stop = new JButton();
		stop.setText("Pause Life");
		stop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CellsPanel.setOneGenerationLifeTime(0);
			}
		});
		add(lifeSpeed);
		add(start);
		add(stop);
		setVisible(true);
		repaint();

	}
}
