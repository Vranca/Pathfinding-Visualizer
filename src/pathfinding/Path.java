package pathfinding;

import java.util.ArrayList;
import java.util.HashMap;

import model.cell.Cell;

public class Path
{
	HashMap<Cell, Double> pathDistances = null;
	HashMap<Cell, Cell> previousCell = null;

	public Path()
	{
		pathDistances = new HashMap<>();
		previousCell = new HashMap<>();
	}

	public void setPreviousCell(Cell cell, Cell prevCell)
	{
		previousCell.put(cell, prevCell);
	}

	public void setCellDistance(Cell cell, double dist)
	{
		pathDistances.put(cell, dist);
	}

	public Double getDistance(Cell cell)
	{
		return pathDistances.get(cell);
	}

	public Cell getPreviousCell(Cell cell)
	{
		return previousCell.get(cell);
	}

	public boolean isOnPathTo(Cell endCell, Cell cell)
	{
		Cell currCell = endCell;

		do
		{
			if (previousCell.get(currCell) == cell)
			{
				return true;
			} else
			{
				currCell = previousCell.get(currCell);
			}
		} while (currCell != null);

		return false;
	}

	public ArrayList<Cell> getPathTo(Cell endPathCell)
	{
		ArrayList<Cell> path = new ArrayList<>();

		for (Cell pathCell = getPreviousCell(endPathCell); pathCell != null;)
		{
			path.add(0, pathCell);
			pathCell = getPreviousCell(pathCell);
		}

		path.remove(0);

		return path;
	}

}
