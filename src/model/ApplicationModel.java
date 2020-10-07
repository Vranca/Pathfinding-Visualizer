package model;

import java.util.Observable;

import model.field.FieldLayout;
import model.field.FieldModel;
import model.field.SquareLayout;
import pathfinding.DjikstraAlgorithm;

public class ApplicationModel extends Observable
{
	FieldModel fieldModel = null;

	public ApplicationModel()
	{
		fieldModel = new FieldModel();
		fieldModel.setFieldSize(40, 40);
		fieldModel.setLayout(new SquareLayout());
		fieldModel.setAlgorithm(new DjikstraAlgorithm());
		fieldModel.setCellSizes(400, 400);
	}

	public final FieldModel getFieldModel()
	{
		return fieldModel;
	}

	public final void setFieldModel(FieldModel fieldModel)
	{
		this.fieldModel = fieldModel;
	}

	public final void setFieldLayout(FieldLayout layout)
	{
		fieldModel.setLayout(layout);
	}
}
