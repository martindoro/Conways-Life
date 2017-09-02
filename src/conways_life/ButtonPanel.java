package conways_life;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ButtonPanel extends JPanel{

	private static final long serialVersionUID = -7081739959526621292L;

	public ButtonPanel() {
		setLayout(new GridLayout(2, 0));

		JSlider lifeSpeed = new JSlider(0, 100, (int) Math.sqrt(CellsPanel.oneGenerationLifeTime));
		lifeSpeed.setBorder(new TitledBorder("One Generation Lifetime"));
		lifeSpeed.setMinorTickSpacing(10);
		lifeSpeed.setMajorTickSpacing(50);
		lifeSpeed.setPaintTicks(true);
		lifeSpeed.setPaintLabels(true);
		add(lifeSpeed);
		lifeSpeed.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				int lifeTime = source.getValue();
				CellsPanel.setOneGenerationLifeTime((long) Math.pow(lifeTime, 2));
			}
		});
		setVisible(true);
		repaint();

	}
}
