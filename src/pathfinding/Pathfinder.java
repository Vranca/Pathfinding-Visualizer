package pathfinding;

import java.util.ArrayList;
import java.util.Observable;

import model.cell.Cell;

public class Pathfinder extends Observable
{
	private PathfindingAlgorithm algorithm = null;
	private Path path = null;

	public Pathfinder()
	{
		// TODO Auto-generated constructor stub
	}

	public Pathfinder(PathfindingAlgorithm algorithm)
	{
		this.algorithm = algorithm;
	}

	public final PathfindingAlgorithm getAlgorithm()
	{
		return algorithm;
	}

	public final void setAlgorithm(PathfindingAlgorithm algorithm)
	{
		this.algorithm = algorithm;
	}

	public Path findPath(ArrayList<Cell> field, Cell startCell, Cell endCell)
	{
		path = algorithm.findPath(field, startCell, endCell);
		return path;
	}

	public void drawPathTo(Cell endCell)
	{
		for (Cell pathCell : path.getPathTo(endCell))
		{
			pathCell.setPath();
			setChanged();
			notifyObservers(pathCell);

			try
			{
				Thread.sleep(50);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
