package model.cell;

import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Point2D;

import model.cell.paint.CellPaint;

public abstract class CellLookAndFeel
{
	protected Shape cellShape = null;
	protected CellPaint cellPaint = null;

	public CellLookAndFeel(CellPaint cellPaint)
	{
		this.cellPaint = cellPaint;
	}

	public final void paint(Graphics g)
	{
		cellPaint.paint(g, cellShape);
	}

	public abstract void setLocation(int row, int column);

	public abstract void setSize(int width, int height);

	public void setBasicPaint()
	{
		cellPaint = cellPaint.getBasicPaint();
	}

	public void setVisitedPaint()
	{
		if (!cellPaint.getClass().equals(cellPaint.getStartPaint().getClass()))
			cellPaint = cellPaint.getVisitedPaint();
	}

	public void setStartPaint()
	{
		cellPaint = cellPaint.getStartPaint();
	}

	public void setEndPaint()
	{
		cellPaint = cellPaint.getEndPaint();
	}

	public void setPathPaint()
	{
		cellPaint = cellPaint.getPathPaint();
	}

	public void setImpassablePaint()
	{
		cellPaint = cellPaint.getImpassablePaint();
	}

	public void setCheckingPaint()
	{
		if (!cellPaint.getClass().equals(cellPaint.getStartPaint().getClass())
				&& !cellPaint.getClass().equals(cellPaint.getEndPaint().getClass()))
			cellPaint = cellPaint.getCheckingPaint();
	}

	public final int getWidth()
	{
		return (int) cellShape.getBounds2D().getWidth();
	}

	public final int getHeight()
	{
		return (int) cellShape.getBounds2D().getHeight();
	}

	public boolean contains(Point2D p)
	{
		return cellShape.contains(p);
	}
}
