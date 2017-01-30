package com.interview.design.factory;

public class SquareFactory implements ShapeFactory
{

	@Override
	public Shape create()
	{
		// TODO Auto-generated method stub
		return new Square();
	}

}
