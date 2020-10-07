package pathfinding;

import java.util.ArrayList;
import java.util.Observable;

import model.cell.Cell;

public class DjikstraAlgorithm extends Observable implements PathfindingAlgorithm
{

	@Override
	public Path findPath(ArrayList<Cell> field, Cell startCell, Cell endCell)
	{
		ArrayList<Cell> unvisitedCells = new ArrayList<>();
		Path shortestPath = new Path();

		for (Cell cell : field)
		{
			shortestPath.setCellDistance(cell, Double.MAX_VALUE);
			shortestPath.setPreviousCell(cell, null);
			unvisitedCells.add(cell);
		}
		shortestPath.setCellDistance(startCell, 0);

		while (!unvisitedCells.isEmpty())
		{
			Cell u = minDistanceCell(unvisitedCells, shortestPath);
			unvisitedCells.remove(u);
			u.setChecking();
			setChanged();
			notifyObservers(u);

			if (u == endCell)
				break;

			for (Cell v : u.getNeighbours())
			{
				Double dist = shortestPath.getDistance(u) + u.getDistanceOfNeighbour(v);
				if (dist < shortestPath.getDistance(v))
				{
					shortestPath.setCellDistance(v, dist);
					shortestPath.setPreviousCell(v, u);
				}
			}

			try
			{
				Thread.sleep(10);
			} catch (Exception e)
			{
				e.printStackTrace();
			}

			u.setVisited();
			setChanged();
			notifyObservers(u);
		}

		return shortestPath;
	}

	private Cell minDistanceCell(ArrayList<Cell> unvisitedCells, Path path)
	{
		Cell min = unvisitedCells.get(0);

		for (Cell cell : unvisitedCells)
		{
			if (path.getDistance(cell) < path.getDistance(min))
			{
				min = cell;
			}
		}

		return min;
	}

}
