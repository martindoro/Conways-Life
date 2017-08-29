package conways_life;

import java.awt.Color;
import java.awt.Graphics;

public class CellsPanel implements Runnable {

	private Color backgroundColor = new Color(0, 0, 0);
	private Color lineColor = new Color(100, 100, 100);
	private Color livingCellColor = new Color(255, 255, 255);
	private static final int CELL_SIZE = 5;
	private static final int GRID_SIZE = 150;
	private boolean[][] allCells;
	private long oneGenerationLifeTime;
	
	public long getOneGenerationLifeTime() {
		return oneGenerationLifeTime;
	}

	public void setOneGenerationLifeTime(long oneGenerationLifeTime) {
		this.oneGenerationLifeTime = oneGenerationLifeTime;
	}

	@Override
	public void run() {
		new CellsPanel();
	}

	public CellsPanel() {
		allCells = new boolean[GRID_SIZE][GRID_SIZE];
		setOneGenerationLifeTime(1000);
	}

	public void oneGenerationCycle() {
		for (int i = 0; i < GRID_SIZE; i++) {
			for (int j = 0; j < GRID_SIZE; j++) {
				int liveNeighbours = countLivingNeighbours(i, j);
				if (liveNeighbours == 3) {
					allCells[i][j] = true;
				}
				if (liveNeighbours > 3) {
					allCells[i][j] = false;
				}
				if (liveNeighbours < 2) {
					allCells[i][j] = false;
				}
			}
		}
	}

	private int countLivingNeighbours(int i, int j) {
		int count = 0;
		// toto este treba domysliet, krajne bunky su problem
		if (allCells[i - 1][j - 1])
			count++;
		if (allCells[i][j - 1])
			count++;
		if (allCells[i + 1][j - 1])
			count++;
		if (allCells[i + 1][j])
			count++;
		if (allCells[i + 1][j + 1])
			count++;
		if (allCells[i][j + 1])
			count++;
		if (allCells[i - 1][j + 1])
			count++;
		if (allCells[i - 1][j])
			count++;

		return count;
	}

	public void drawCellsPanel(Graphics g) {
		g.setColor(backgroundColor);
		// tu sa spoja ciary a bunky
	}

	public void drawCells(Graphics g) {
		g.setColor(livingCellColor);
		// tu sa budu vykreslovat bunky
	}

	public void drawGridLines(Graphics g) {
		g.setColor(lineColor);
		// tu sa nakreslia ciary
	}
}
