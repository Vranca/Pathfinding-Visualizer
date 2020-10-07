package model.field;

import java.awt.geom.Point2D;
import java.util.ArrayList;

import model.cell.Cell;
import model.cell.HexagonalCell;
import model.cell.paint.basic.GradientCellPaint;

public class HexagonalLayout implements FieldLayout
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
			newCell.setLookAndFeel(new HexagonalCell(new GradientCellPaint()));
			field.add(newCell);
		}

		// Linking neighbors
		for (int row = 0; row < rowCount; row++)
		{
			for (int column = 0; column < columnCount; column++)
			{
				int i = column + row * columnCount;

				// Not Right edge
				if (column < columnCount - 1)
				{
					field.get(i).addNeighbour(field.get(i + 1), 1.0);
				}
				// Not bottom edge
				if (row < rowCount - 1)
				{
					field.get(i).addNeighbour(field.get(i + columnCount), 1.0);
				}
				// not left edge
				if (column > 0)
				{
					field.get(i).addNeighbour(field.get(i - 1), 1.0);
				}
				// Not top edge
				if (row > 0)
				{
					field.get(i).addNeighbour(field.get(i - columnCount), 1.0);
				}

				// Not Right edge and not top edge and even column
				if ((column < columnCount - 1) && (row > 0) && (column % 2 == 0))
				{
					field.get(i).addNeighbour(field.get(i - columnCount + 1), 1.0);
					field.get(i - columnCount + 1).addNeighbour(field.get(i), 1.0);
				}
				// Not Right edge and not bottom edge and odd column
				if ((column < columnCount - 1) && (row < row - 1) && (column % 2 != 0))
				{
					field.get(i).addNeighbour(field.get(i + columnCount + 1), 1.0);
					field.get(i + columnCount + 1).addNeighbour(field.get(i), 1.0);
				}
				// Not Left edge and not bottom edge and odd column
				if ((column > 0) && (row < rowCount - 1) && (column % 2 != 0))
				{
					field.get(i).addNeighbour(field.get(i + columnCount - 1), 1.0);
					field.get(i + columnCount - 1).addNeighbour(field.get(i), 1.0);
				}
				// Not Left edge and not top edge and even column
				if ((column > 0) && (row > 0) && (column % 2 == 0))
				{
					field.get(i).addNeighbour(field.get(i - columnCount - 1), 1.0);
					field.get(i - columnCount - 1).addNeighbour(field.get(i), 1.0);
				}
			}
		}

		return field;
	}

	@Override
	public Cell getCellAt(ArrayList<Cell> field, int x, int y)
	{
		Point2D p = new Point2D.Double(x, y);
		for (Cell cell : field)
		{
			if (cell.contains(p))
			{
				return cell;
			}
		}

		return null;
	}

	@Override
	public void setCellSizes(ArrayList<Cell> field, int fieldWidth, int fieldHeight)
	{
		int cellWidth = (int) (2 * (fieldWidth / (3 * columnCount / 2 + 0.5)));
		int cellHeight = (int) (2 * (fieldHeight / (2 * rowCount + 1)));

		for (Cell cell : field)
		{
			cell.setSize(cellWidth, cellHeight);
		}
	}

}
