package model.field;

import java.util.ArrayList;

import model.cell.Cell;

public interface FieldLayout
{
	ArrayList<Cell> setLayout(int rowCount, int columnCount);

	Cell getCellAt(ArrayList<Cell> field, int x, int y);

	void setCellSizes(ArrayList<Cell> field, int fieldWidth, int fieldHeight);
}
