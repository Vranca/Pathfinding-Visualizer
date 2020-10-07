package main;

import model.ApplicationModel;
import view.ApplicationView;

public class MainClass
{

	public static void main(String[] args)
	{
		ApplicationModel model = new ApplicationModel();
		new ApplicationView(model);
	}

}
