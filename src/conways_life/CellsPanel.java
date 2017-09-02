package conways_life;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class CellsPanel extends JPanel implements Runnable {

	private static final long serialVersionUID = -3365733816461653809L;

	private Color backgroundColor = new Color(0, 0, 0);
	private Color lineColor = new Color(100, 100, 100);
	private Color livingCellColor = new Color(255, 255, 255);
	
	private static final int CELL_SIZE = 5;
	private static final int CELL_GRID_SIZE = 100;
	public static long oneGenerationLifeTime;
	
	private boolean[][] allCells;
	
	private Dimension playGridSize = new Dimension(CELL_GRID_SIZE * (CELL_SIZE + 1) + 1,
			CELL_GRID_SIZE * (CELL_SIZE + 1) + 1);
	
	public long getOneGenerationLifeTime() {
		return oneGenerationLifeTime;
	}

	public static void setOneGenerationLifeTime(long oneGenerationLifeTime) {
		CellsPanel.oneGenerationLifeTime = oneGenerationLifeTime;
	}

	@Override
	public void run() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = e.getX();
				int y = e.getY();
				int cellX = (x + CELL_SIZE) / (CELL_SIZE + 1);
				int cellY = (y + CELL_SIZE) / (CELL_SIZE + 1);
				if (!allCells[cellX][cellY]) {
					allCells[cellX][cellY] = true;
					repaint();
				}
			}
		});
	}

	public CellsPanel() {
		allCells = new boolean[CELL_GRID_SIZE][CELL_GRID_SIZE];
		setOneGenerationLifeTime(1000);
	}

	public void oneGenerationCycle() throws InterruptedException {
		wait(oneGenerationLifeTime);
		for (int i = 0; i < CELL_GRID_SIZE; i++) {
			for (int j = 0; j < CELL_GRID_SIZE; j++) {
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
		repaint();
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

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(backgroundColor);
		drawGridLines(g);
		drawCells(g);
		// tu sa spoja ciary a bunky
	}

	public void drawCells(Graphics g) {
		g.setColor(livingCellColor);
		for (int i = 0; i < CELL_GRID_SIZE; i++) {
			for (int j = 0; j < CELL_GRID_SIZE; j++) {
				if (allCells[i][j])
					g.drawRect((i * (CELL_SIZE + 1)) + 1, (j * (CELL_SIZE + 1) + 1), CELL_SIZE, CELL_SIZE);
				// vykreslovanie zivych buniek
			}
		}
	}

	public void drawGridLines(Graphics g) {
		g.setColor(lineColor);
		for (int i = 0; i < playGridSize.getWidth(); i++) {
			g.drawLine(i, 0, i, (int) playGridSize.getHeight());
			i += CELL_SIZE + 1;// neviem ci nepriratat este +1, alebo sa to prirata automaticky pri prechode
							// for cyklu
		}
		for (int i = 0; i < playGridSize.getHeight(); i++) {
			g.drawLine(0, i, (int) playGridSize.getWidth(), i);
			i += CELL_SIZE + 1;// detto
		}
		
	}
}
