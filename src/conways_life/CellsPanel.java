package conways_life;

public class CellsPanel implements Runnable {

	private static final int CELL_SIZE = 5;
	private static final int GRID_SIZE = 150;
	private boolean[][] allCells;
	private long oneGenerationLifeTime;
	
	@Override
	public void run() {
		new CellsPanel();
	}

	public CellsPanel() {
		
	}

}
