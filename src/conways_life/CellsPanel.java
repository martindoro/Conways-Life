package conways_life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;

public class CellsPanel extends JPanel {

	private static final long serialVersionUID = -1381479706484952735L;
	private Color backgroundColor = new Color(255, 239, 213);
	private Color lineColor = new Color(255, 228, 196);
	private Color livingCellColor = new Color(238, 203, 173);
	
	private static final int CELL_SIZE = 5;
	private static final int CELL_GRID_SIZE = 100;
	public static long oneGenerationLifeTime = 1000;
	
	private boolean[][] allCells = new boolean[CELL_GRID_SIZE][CELL_GRID_SIZE];
	
	private Dimension playGridSize = new Dimension(CELL_GRID_SIZE * (CELL_SIZE + 1) + 1,
			CELL_GRID_SIZE * (CELL_SIZE + 1) + 1);
	
	public long getOneGenerationLifeTime() {
		return oneGenerationLifeTime;
	}

	public static void setOneGenerationLifeTime(long oneGenerationLifeTime) {
		CellsPanel.oneGenerationLifeTime = oneGenerationLifeTime;
	}

	public CellsPanel() {
		addMouseListener(new MouseHandler());
		int size = CELL_GRID_SIZE;
		int liveCell = size * size / 5;
		Random randomGenerator = new Random();
		for (int k = 0; k < liveCell; k++) {
			int i = randomGenerator.nextInt(size);
			int j = randomGenerator.nextInt(size);
			allCells[i][j] = true;
		}
		repaint();
	}

	private class MouseHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			int cellX = ((x + CELL_SIZE) / (CELL_SIZE + 1)) - 1;
			int cellY = ((y + CELL_SIZE) / (CELL_SIZE + 1)) - 1;
			if (!allCells[cellX][cellY]) {
				allCells[cellX][cellY] = true;
			}
			repaint();
		}
	}

	public void oneGenerationCycle() {
		boolean[][] tempGrid = new boolean[CELL_GRID_SIZE][CELL_GRID_SIZE];
		for (int i = 0; i < CELL_GRID_SIZE; i++) {
			for (int j = 0; j < CELL_GRID_SIZE; j++) {
				int liveNeighbours = countLivingNeighbours(i, j);
				tempGrid[i][j] = false;
				
				if (liveNeighbours == 3) {
					tempGrid[i][j] = true;
				}
				if (allCells[i][j] && liveNeighbours == 2) {
					tempGrid[i][j] = true;
				}
				if (allCells[i][j] && liveNeighbours > 3) {
					tempGrid[i][j] = false;
				}
				if (allCells[i][j] && liveNeighbours < 2) {
					tempGrid[i][j] = false;
				}
			}
		}
		allCells = tempGrid;
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
				if (allCells[iminus][jminus])
					count++;
			}

			if (allCells[iminus][j])
				count++;

			if (jplus < CELL_GRID_SIZE) {
				if (allCells[iminus][jplus])
					count++;
			}
		}

		if (jminus >= 0) {
			if (allCells[i][jminus])
				count++;
		}

		if (jplus < CELL_GRID_SIZE) {
			if (allCells[i][jplus])
				count++;
		}

		if (iplus < CELL_GRID_SIZE) {
			if (jminus >= 0) {
				if (allCells[iplus][jminus])
					count++;
			}

			if (allCells[iplus][j])
				count++;

			if (jplus < CELL_GRID_SIZE) {
				if (allCells[iplus][jplus])
					count++;
			}
		}

		return count;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(backgroundColor);
		drawGridLines(g);
		drawCells(g);
	}

	public void drawCells(Graphics g) {
		g.setColor(livingCellColor);
		for (int i = 0; i < CELL_GRID_SIZE; i++) {
			for (int j = 0; j < CELL_GRID_SIZE; j++) {
				if (allCells[i][j])
					g.fillRect((i * (CELL_SIZE + 1)) + 1, (j * (CELL_SIZE + 1) + 1), CELL_SIZE, CELL_SIZE);
			}
		}
	}

	public void drawGridLines(Graphics g) {
		g.setColor(lineColor);
		for (int i = 0; i <= playGridSize.getWidth(); i++) {
			g.drawLine(i, 0, i, (int) playGridSize.getHeight() - 1);
			i += CELL_SIZE;
		}
		for (int i = 0; i < playGridSize.getHeight(); i++) {
			g.drawLine(0, i, (int) playGridSize.getWidth() - 1, i);
			i += CELL_SIZE;
		}
		
	}
}
