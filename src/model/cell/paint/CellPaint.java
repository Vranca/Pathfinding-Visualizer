package model.cell.paint;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;

public abstract class CellPaint
{
	protected Color borderColor1 = null;
	protected Color borderColor2 = null;
	protected Color borderColor3 = null;
	protected Color insideColor1 = null;
	protected Color insideColor2 = null;
	protected Color insideColor3 = null;

	public abstract void paint(Graphics g, Shape cellShape);

	public abstract CellPaint getBasicPaint();

	public abstract CellPaint getVisitedPaint();

	public abstract CellPaint getStartPaint();

	public abstract CellPaint getEndPaint();

	public abstract CellPaint getPathPaint();

	public abstract CellPaint getImpassablePaint();

	public abstract CellPaint getCheckingPaint();
}
