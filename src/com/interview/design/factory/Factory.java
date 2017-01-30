package com.interview.design.factory;

public class Factory
{
	// Multiple factories initialization
	ShapeFactory shape;

	public Shape createShape(String str)
	{
		switch (str)
		{
			case "Circle":
			{
				shape = new CircleFactory();
				break;
			}
			case "Square":
			{
				shape = new SquareFactory();
				break;
			}

		}

		return shape.create();
	}
}
