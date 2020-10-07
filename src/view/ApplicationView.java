package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import controller.FieldController;
import model.ApplicationModel;
import model.field.FieldModel;
import model.field.HexagonalLayout;
import model.field.SquareFullyConnectedLayout;
import model.field.SquareLayout;

public class ApplicationView implements Observer
{
	private JFrame frame = null;
	private ApplicationModel model = null;
	private FieldView fieldView = null;

	public ApplicationView(ApplicationModel model)
	{
		this.model = model;

		frame = new JFrame("Pathfinding visualizer");

		/*
		 * TEST
		 */
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu layoutMenu = new JMenu("Layout");
		JMenuItem squareItem = new JMenuItem("Square");
		squareItem.addActionListener(e -> {
			model.setFieldLayout(new SquareLayout());
			model.getFieldModel().setCellSizes(fieldView.getWidth(), fieldView.getHeight());
		});

		JMenuItem squareFullItem = new JMenuItem("Square Fully Connected");
		squareFullItem.addActionListener(e -> {
			model.setFieldLayout(new SquareFullyConnectedLayout());
			model.getFieldModel().setCellSizes(fieldView.getWidth(), fieldView.getHeight());
		});

		JMenuItem hexItem = new JMenuItem("Hexagonal");
		hexItem.addActionListener(e -> {
			model.setFieldLayout(new HexagonalLayout());
			model.getFieldModel().setCellSizes(fieldView.getWidth(), fieldView.getHeight());
		});

		layoutMenu.add(squareItem);
		layoutMenu.add(squareFullItem);
		layoutMenu.add(hexItem);

		menuBar.add(layoutMenu);

		frame.setLayout(new BorderLayout());

		FieldModel fieldModel = model.getFieldModel();
		fieldView = new FieldView(fieldModel);

		new FieldController(fieldModel, fieldView);

		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		panel.setLayout(new BorderLayout());
		panel.add(fieldView, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.CENTER);

		frame.pack();
		frame.setVisible(true);
	}

	public final ApplicationModel getModel()
	{
		return model;
	}

	@Override
	public void update(Observable o, Object arg)
	{
		// TODO Auto-generated method stub

	}

}
