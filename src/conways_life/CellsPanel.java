package conways_life;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CellsPanel extends JPanel {
	private static final long serialVersionUID = -1381479706484952735L;

	private Image bgImage = null;
	private int generation;
	private ArrayList<ArrayList<Boolean>> allCells;
	private Dimension playGridSize = new Dimension(Constants.CELL_GRID_SIZE * (Constants.CELL_SIZE + 1) + 1,
			Constants.CELL_GRID_SIZE * (Constants.CELL_SIZE + 1) + 1);
	
	public CellsPanel() {
		generation = 0;
		addMouseListener(new MouseHandler());
		allCells = new ArrayList<ArrayList<Boolean>>(Constants.CELL_GRID_SIZE);
		for (int i = 0; i < Constants.CELL_GRID_SIZE; i++) {
			allCells.add(new ArrayList<Boolean>());
		}
		for (int i = 0; i < Constants.CELL_GRID_SIZE; i++) {
			for (int j = 0; j < Constants.CELL_GRID_SIZE; j++) {
				allCells.get(i).add(false);
			}
		}
		repaint();
	}

	public void clearCells() {
		generation = 0;
		for (int i = 0; i < Constants.CELL_GRID_SIZE; i++) {
			for (int j = 0; j < Constants.CELL_GRID_SIZE; j++) {
				allCells.get(i).set(j, false);
			}
		}
		repaint();
	}

	public void randomCellGenerator() {
		generation = 0;
		int liveCell = Constants.CELL_GRID_SIZE * Constants.CELL_GRID_SIZE * RandomSliderPanel.getRandomLiveCell()
				/ 100;
		Random randomGenerator = new Random();
		for (int k = 0; k < liveCell; k++) {
			int i = randomGenerator.nextInt(Constants.CELL_GRID_SIZE);
			int j = randomGenerator.nextInt(Constants.CELL_GRID_SIZE);
			allCells.get(i).set(j, true);
		}
		repaint();
	}

	private class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int cellX = ((x + Constants.CELL_SIZE) / (Constants.CELL_SIZE + 1)) - 1;
			int cellY = ((y + Constants.CELL_SIZE) / (Constants.CELL_SIZE + 1)) - 1;
			allCells.get(cellX).set(cellY, true);
			repaint();
		}
	}

	public void oneGenerationCycle() {
		ArrayList<ArrayList<Boolean>> tempCells = new ArrayList<ArrayList<Boolean>>(Constants.CELL_GRID_SIZE);
		for (int i = 0; i < Constants.CELL_GRID_SIZE; i++) {
			tempCells.add(i, new ArrayList<Boolean>(allCells.get(i)));
		}
		for (int i = 0; i < Constants.CELL_GRID_SIZE; i++) {
			for (int j = 0; j < Constants.CELL_GRID_SIZE; j++) {
				int liveNeighbours = countLivingNeighbours(i, j);
				
				if (liveNeighbours == 3) {
					tempCells.get(i).set(j, true);
				}
				if (allCells.get(i).get(j) && liveNeighbours > 3) {
					tempCells.get(i).set(j, false);
				}
				if (allCells.get(i).get(j) && liveNeighbours < 2) {
					tempCells.get(i).set(j, false);
				}
			}
		}
		allCells = tempCells;
		generation++;
		repaint();
	}

	private int countLivingNeighbours(int i, int j) {
		int count = 0;

		int iminus = i - 1;
		int jminus = j - 1;
		int iplus = i + 1;
		int jplus = j + 1;

		if (iminus >= 0) {
			if (jminus >= 0) {
				if (allCells.get(iminus).get(jminus))
					count++;
			}

			if (allCells.get(iminus).get(j))
				count++;

			if (jplus < Constants.CELL_GRID_SIZE) {
				if (allCells.get(iminus).get(jplus))
					count++;
			}
		}

		if (jminus >= 0) {
			if (allCells.get(i).get(jminus))
				count++;
		}

		if (jplus < Constants.CELL_GRID_SIZE) {
			if (allCells.get(i).get(jplus))
				count++;
		}

		if (iplus < Constants.CELL_GRID_SIZE) {
			if (jminus >= 0) {
				if (allCells.get(iplus).get(jminus))
					count++;
			}

			if (allCells.get(iplus).get(j))
				count++;

			if (jplus < Constants.CELL_GRID_SIZE) {
				if (allCells.get(iplus).get(jplus))
					count++;
			}
		}

		return count;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			bgImage = ImageIO.read(new File("src/images/life.png"));
		} catch (IOException e) {
		}
		g.drawImage(bgImage, 0, 0, null);
		setBackground(Constants.BACKGROUND_COLOR);
		drawGridLines(g);
		drawCells(g);
		g.setColor(Constants.GENERATION_COUNTER_COLOR);
		g.setFont(Constants.GENERATION_COUNTER_FONT);
		g.drawString("Generation " + generation, 20, 20);
	}

	public void drawCells(Graphics g) {
		g.setColor(Constants.LIVING_CELL_COLOR);
		for (int i = 0; i < Constants.CELL_GRID_SIZE; i++) {
			for (int j = 0; j < Constants.CELL_GRID_SIZE; j++) {
				if (allCells.get(i).get(j))
					g.fillRect((i * (Constants.CELL_SIZE + 1)) + 1, (j * (Constants.CELL_SIZE + 1) + 1),
							Constants.CELL_SIZE, Constants.CELL_SIZE);
			}
		}
	}

	public void drawGridLines(Graphics g) {
		g.setColor(Constants.LINE_COLOR);
		for (int i = 0; i <= playGridSize.getWidth(); i++) {
			g.drawLine(i, 0, i, (int) playGridSize.getHeight() - 1);
			i += Constants.CELL_SIZE;
		}
		for (int i = 0; i < playGridSize.getHeight(); i++) {
			g.drawLine(0, i, (int) playGridSize.getWidth() - 1, i);
			i += Constants.CELL_SIZE;
		}
	}
}
