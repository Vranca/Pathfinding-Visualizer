package model.cell;

import java.awt.Rectangle;

import model.cell.paint.CellPaint;

public class SquareCell extends CellLookAndFeel
{

	public SquareCell(CellPaint cellPaint)
	{
		super(cellPaint);

		cellShape = new Rectangle(10, 10);
	}

	@Override
	public void setLocation(int row, int column)
	{
		Rectangle shape = (Rectangle) cellShape;
		shape.setLocation(column * (int) shape.getWidth(), row * (int) shape.getHeight());
	}

	@Override
	public void setSize(int width, int height)
	{
		((Rectangle) cellShape).setSize(width, height);
	}

}
