package com.interview.design.factory;

public class CircleFactory implements ShapeFactory
{

	@Override
	public Shape create()
	{
		// TODO Auto-generated method stub
		return new Circle();
	}

}
