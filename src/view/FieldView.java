package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.cell.Cell;
import model.field.FieldModel;

public class FieldView extends JPanel implements Observer
{
	private static final long serialVersionUID = 1L;

	private FieldModel model = null;

	public FieldView(FieldModel model)
	{
		this.model = model;
		setBackground(Color.DARK_GRAY);

		setPreferredSize(new Dimension(400, 400));
	}

	@Override
	public void update(Observable o, Object arg)
	{
		if (arg instanceof Cell)
		{
			((Cell) arg).paint(getGraphics());
		} else
		{
			model.paintField(getGraphics());
			repaint();
		}

	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		model.paintField(g);
	}

}
