package model.cell;

import java.awt.Polygon;

import model.cell.paint.CellPaint;

public class HexagonalCell extends CellLookAndFeel
{
	private int[] xPoints;
	private int[] yPoints;
	private int nPoints = 6;

	private int xLocation;
	private int yLocation;
	private int width;
	private int height;

	public HexagonalCell(CellPaint cellPaint)
	{
		super(cellPaint);

		xLocation = 0;
		yLocation = 0;
		width = 20;
		height = 20;

		xPoints = new int[] { 5, 15, 20, 15, 5, 0 };
		yPoints = new int[] { 0, 0, 10, 20, 20, 10 };

		cellShape = new Polygon(xPoints, yPoints, nPoints);
	}

	@Override
	public void setLocation(int row, int column)
	{
		xLocation = (column * width) - (column * (width / 4));
		if (column % 2 == 0)
		{
			yLocation = (row * height);
		} else
		{
			yLocation = (row * height) + (height / 2);
		}

		updateShape();
	}

	@Override
	public void setSize(int width, int height)
	{
		this.width = width;
		this.height = height;

		updateShape();
	}

	private void updateShape()
	{
		int rx = (width / 2);
		int ry = (height / 2);
		Polygon hexagon = (Polygon) cellShape;

		xPoints = new int[] { rx / 2 + xLocation, 3 * rx / 2 + xLocation, 2 * rx + xLocation, 3 * rx / 2 + xLocation, rx / 2 + xLocation,
				0 + xLocation };
		yPoints = new int[] { 0 + yLocation, 0 + yLocation, ry + yLocation, 2 * ry + yLocation, 2 * ry + yLocation, ry + yLocation };

		hexagon.xpoints = xPoints;
		hexagon.ypoints = yPoints;
		hexagon.invalidate();
	}

}
