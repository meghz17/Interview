package com.interview.design.factory;


public class FactoryClient
{
	public static void main(String[] args)
	{
		String[] str = { "Circle", "Square" };

		Factory factory = new Factory();

		for (String s : str)
		{
			factory.createShape(s);
		}
	}
}
