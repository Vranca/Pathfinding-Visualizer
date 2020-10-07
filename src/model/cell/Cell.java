package model.cell;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

public class Cell
{
	private ArrayList<Cell> neighbours = null;
	private HashMap<Cell, Double> distances = null;
	private double traversalDifficulty = 1;
	private CellLookAndFeel lookAndFeel = null;

	public Cell()
	{
		neighbours = new ArrayList<>();
		distances = new HashMap<>();
	}

	public Cell(double traversalDifficulty)
	{
		neighbours = new ArrayList<>();
		distances = new HashMap<>();
		this.traversalDifficulty = traversalDifficulty;
	}

	public void addNeighbour(Cell neighbour)
	{
		neighbours.add(neighbour);
		distances.put(neighbour, 1.0);
	}

	public void addNeighbour(Cell neighbour, Double distance)
	{
		neighbours.add(neighbour);
		distances.put(neighbour, distance);
	}

	public final double getTraversalDifficulty()
	{
		return traversalDifficulty;
	}

	public final void setTraversalDifficulty(double traversalDifficulty)
	{
		this.traversalDifficulty = traversalDifficulty;
	}

	public final CellLookAndFeel getLookAndFeel()
	{
		return lookAndFeel;
	}

	public final void setLookAndFeel(CellLookAndFeel lookAndFeel)
	{
		this.lookAndFeel = lookAndFeel;
	}

	public final ArrayList<Cell> getNeighbours()
	{
		return neighbours;
	}

	public final HashMap<Cell, Double> getDistances()
	{
		return distances;
	}

	public double getDistanceOfNeighbour(Cell neighbour)
	{
		return neighbour.getTraversalDifficulty() * distances.get(neighbour);
	}

	public final void paint(Graphics g)
	{
		lookAndFeel.paint(g);
	}

	public final void setUnvisited()
	{
		lookAndFeel.setBasicPaint();
	}

	public final void setVisited()
	{
		lookAndFeel.setVisitedPaint();
	}

	public final void setStart()
	{
		lookAndFeel.setStartPaint();
	}

	public final void setEnd()
	{
		lookAndFeel.setEndPaint();
	}

	public final void setPath()
	{
		lookAndFeel.setPathPaint();
	}

	public final void setImpassable()
	{
		lookAndFeel.setImpassablePaint();
		traversalDifficulty = Double.MAX_VALUE;
	}

	public final void setChecking()
	{
		lookAndFeel.setCheckingPaint();
	}

	public final void setSize(int width, int height)
	{
		lookAndFeel.setSize(width, height);
	}

	public final void setLocation(int row, int column)
	{
		lookAndFeel.setLocation(row, column);
	}

	public final int getWidth()
	{
		return lookAndFeel.getWidth();
	}

	public final int getHeight()
	{
		return lookAndFeel.getHeight();
	}

	public final boolean contains(Point2D p)
	{
		return lookAndFeel.contains(p);
	}
}
