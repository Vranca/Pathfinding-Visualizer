package pathfinding;

import java.util.ArrayList;

import model.cell.Cell;

public interface PathfindingAlgorithm
{
	Path findPath(ArrayList<Cell> field, Cell startCell, Cell endCell);
}
