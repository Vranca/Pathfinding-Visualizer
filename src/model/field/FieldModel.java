package model.field;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.cell.Cell;
import pathfinding.Path;
import pathfinding.Pathfinder;
import pathfinding.PathfindingAlgorithm;

public class FieldModel extends Observable
{
	private Pathfinder pathfinder = null;
	private FieldLayout layout = null;

	private ArrayList<Cell> field = null;
	private Cell startCell = null;
	private Cell endCell = null;
	private Path shorthestPath = null;

	private int rowCount = 0;
	private int columnCount = 0;

	public FieldModel()
	{
		pathfinder = new Pathfinder();
		rowCount = 20;
		columnCount = 20;
	}

	public void setAlgorithm(PathfindingAlgorithm algorithm)
	{
		pathfinder.setAlgorithm(algorithm);
	}

	public PathfindingAlgorithm getAlgorithm()
	{
		return pathfinder.getAlgorithm();
	}

	public final FieldLayout getLayout()
	{
		return layout;
	}

	public final void setLayout(FieldLayout layout)
	{
		this.layout = layout;
		field = this.layout.setLayout(rowCount, columnCount);

		startCell = null;
		endCell = null;

		setChanged();
		notifyObservers();
	}

	public Path findPath()
	{
		shorthestPath = pathfinder.findPath(field, this.startCell, this.endCell);
		pathfinder.drawPathTo(endCell);

		return shorthestPath;
	}

	public Cell getCellAt(int x, int y)
	{
		return layout.getCellAt(field, x, y);
	}

	public void setCellSizes(int fieldWidth, int fieldHeight)
	{
		layout.setCellSizes(field, fieldWidth, fieldHeight);
		setCellLocations();
	}

	private void setCellLocations()
	{
		for (int row = 0; row < rowCount; row++)
		{
			for (int column = 0; column < columnCount; column++)
			{
				field.get(column + row * columnCount).setLocation(row, column);
			}
		}
	}

	public void setFieldSize(int rowCount, int columnCount)
	{
		this.rowCount = rowCount;
		this.columnCount = columnCount;
	}

	public final void setStartCell(Cell startCell)
	{
		if (this.startCell != null)
		{
			this.startCell.setUnvisited();
			setChanged();
			notifyObservers(this.startCell);
		}
		this.startCell = startCell;
		startCell.setStart();

		setChanged();
		notifyObservers(startCell);
	}

	public final void setEndCell(Cell endCell)
	{
		if (this.endCell != null)
		{
			this.endCell.setUnvisited();
			setChanged();
			notifyObservers(this.endCell);
		}
		this.endCell = endCell;
		endCell.setEnd();

		setChanged();
		notifyObservers(endCell);
	}

	public void paintField(Graphics g)
	{
		for (Cell cell : field)
		{
			cell.paint(g);
		}
	}

	public void setStartCell(int x, int y)
	{
		Cell cell = getCellAt(x, y);
		if (cell != null)
			setStartCell(cell);
	}

	public void setEndCell(int x, int y)
	{
		Cell cell = getCellAt(x, y);
		if (cell != null)
			setEndCell(cell);
	}

	public void setImpassableCell(int x, int y)
	{
		Cell cell = getCellAt(x, y);
		if (cell != null)
		{
			if (cell.getTraversalDifficulty() == Double.MAX_VALUE)
			{
				cell.setTraversalDifficulty(1.0);
				cell.setUnvisited();
			} else
			{
				cell.setImpassable();
			}
			setChanged();
			notifyObservers(cell);
		}
	}

	public void setImpassableCellByDrag(int x, int y)
	{
		Cell cell = getCellAt(x, y);
		if (cell != null)
		{
			cell.setImpassable();

			setChanged();
			notifyObservers(cell);
		}
	}

	@Override
	public synchronized void addObserver(Observer o)
	{
		super.addObserver(o);

		Observable pth = (Observable) pathfinder;
		pth.addObserver(o);

		Observable alg = (Observable) pathfinder.getAlgorithm();
		alg.addObserver(o);
	}
}
