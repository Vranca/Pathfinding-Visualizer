package model.field;

import java.util.ArrayList;

import model.cell.Cell;
import model.cell.SquareCell;
import model.cell.paint.basic.GradientCellPaint;

public class SquareLayout implements FieldLayout
{
	private int columnCount;
	private int rowCount;

	@Override
	public ArrayList<Cell> setLayout(int rowCount, int columnCount)
	{
		this.columnCount = columnCount;
		this.rowCount = rowCount;

		ArrayList<Cell> field = new ArrayList<>();

		// Create the field
		for (int i = 0; i < (rowCount * columnCount); i++)
		{
			Cell newCell = new Cell();
			newCell.setLookAndFeel(new SquareCell(new GradientCellPaint()));
			field.add(newCell);
		}

		// Linking neighbors
		for (int i = 0; i < (rowCount * columnCount); i++)
		{
			// Not Right edge
			if ((i % columnCount) != columnCount - 1)
			{
				field.get(i).addNeighbour(field.get(i + 1), 1.0);
			}
			// Not bottom edge
			if (i < ((rowCount - 1) * columnCount))
			{
				field.get(i).addNeighbour(field.get(i + columnCount), 1.0);
			}
			// not left edge
			if (i % columnCount != 0)
			{
				field.get(i).addNeighbour(field.get(i - 1), 1.0);
			}
			// Not top edge
			if (i > columnCount - 1)
			{
				field.get(i).addNeighbour(field.get(i - columnCount), 1.0);
			}
		}

		return field;
	}

	@Override
	public Cell getCellAt(ArrayList<Cell> field, int x, int y)
	{
		int row = (int) (y / field.get(0).getHeight());
		int column = (int) (x / field.get(0).getWidth());

		return field.get(column + row * columnCount);
	}

	@Override
	public void setCellSizes(ArrayList<Cell> field, int fieldWidth, int fieldHeight)
	{
		// TODO Auto-generated method stub
		int cellWidth = (int) (fieldWidth / columnCount);
		int cellHeight = (int) (fieldHeight / rowCount);

		for (Cell cell : field)
		{
			cell.setSize(cellWidth, cellHeight);
		}
	}
}
