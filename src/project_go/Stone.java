package project_go;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.transform.Translate;

public class Stone extends Group
{
	private Ellipse e;
	private int type;
	private Translate pos;

	public Stone(int type)
	{
		pos = new Translate();
		this.type = type;
		
		if (type == 1)
		{
			e = new Ellipse();
			getChildren().addAll(e);
			e.getTransforms().add(pos);
			e.setStroke(Color.WHITE);
			e.setFill(Color.WHITE);
		}
		else
		{
			e = new Ellipse();
			getChildren().addAll(e);
			e.getTransforms().add(pos);
			e.setStroke(Color.BLACK);
			e.setFill(Color.BLACK);
		}
			
	}

	@Override
	public void resize(double width, double height)
	{
		e.setCenterX(width / 2);
		e.setCenterY(height / 2);
		e.setRadiusX(width / 2);
		e.setRadiusY(height / 2);
	}

	@Override
	public void relocate(double x, double y)
	{
		super.relocate(x, y);
		pos.setX(x);
		pos.setY(y);
	}
}
