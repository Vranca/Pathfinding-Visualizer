package controller;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.event.MouseInputListener;

import model.field.FieldModel;
import view.FieldView;

public class FieldController
{
	private FieldModel model = null;
	private FieldView view = null;

	public FieldController(FieldModel model, FieldView view)
	{
		this.model = model;
		this.view = view;
		model.addObserver(view);

		view.addComponentListener(new FieldResizeListener());
		FieldMouseListener mouseListener = new FieldMouseListener();
		view.addMouseListener(mouseListener);
		view.addMouseMotionListener(mouseListener);
	}

	private class FieldResizeListener extends ComponentAdapter
	{
		@Override
		public void componentResized(ComponentEvent e)
		{
			model.setCellSizes(view.getWidth(), view.getHeight());
		}
	}

	private class FieldMouseListener implements MouseInputListener
	{

		@Override
		public void mouseClicked(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e)
		{
			if (SwingUtilities.isLeftMouseButton(e))
			{
				if (e.isControlDown())
				{
					model.setImpassableCell(e.getX(), e.getY());
				} else
				{
					model.setStartCell(e.getX(), e.getY());
				}
			} else if (SwingUtilities.isRightMouseButton(e))
			{
				model.setEndCell(e.getX(), e.getY());
			} else
			{
				model.findPath();
			}
		}

		@Override
		public void mouseReleased(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e)
		{
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e)
		{
			if (e.isControlDown())
				model.setImpassableCellByDrag(e.getX(), e.getY());
		}

		@Override
		public void mouseMoved(MouseEvent e)
		{
			// TODO Auto-generated method stub
		}

	}
}
