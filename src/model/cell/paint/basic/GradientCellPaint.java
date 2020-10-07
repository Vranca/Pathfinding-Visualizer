package model.cell.paint.basic;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;

import model.cell.paint.CellPaint;

public class GradientCellPaint extends CellPaint
{
	public GradientCellPaint()
	{
		borderColor1 = Color.BLACK;
		insideColor1 = Color.WHITE;
		insideColor2 = new Color(200, 200, 200);
	}

	@Override
	public void paint(Graphics g, Shape cellShape)
	{
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Inside
		float x1 = (float) cellShape.getBounds2D().getX();
		float y1 = (float) cellShape.getBounds2D().getY();
		float x2 = (float) cellShape.getBounds2D().getWidth() + x1;
		float y2 = (float) cellShape.getBounds2D().getHeight() + y1;

		GradientPaint insidePaint = new GradientPaint(x1, y1, insideColor1, x2, y2, insideColor2);

		g2d.setPaint(insidePaint);
		g2d.fill(cellShape);

		// Border
		g2d.setColor(borderColor1);
		g2d.draw(cellShape);

		g2d.dispose();
	}

	@Override
	public CellPaint getBasicPaint()
	{
		return new GradientCellPaint();
	}

	@Override
	public CellPaint getVisitedPaint()
	{
		return new VisitedGradientCellPaint();
	}

	@Override
	public CellPaint getStartPaint()
	{
		return new StartGradientCellPaint();
	}

	@Override
	public CellPaint getEndPaint()
	{
		return new EndGradientCellPaint();
	}

	@Override
	public CellPaint getPathPaint()
	{
		return new PathGradientCellPaint();
	}

	@Override
	public CellPaint getImpassablePaint()
	{
		return new ImpassableGradientCellPaint();
	}

	@Override
	public CellPaint getCheckingPaint()
	{
		return new CheckingGradientCellPaint();
	}
}
