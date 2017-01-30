package Singleton;

public class Singleton
{
	// Lazy Initialization
	private static Singleton singletonInstance = null;

	// Restriction of initialization from outside the class
	private Singleton()
	{
		System.out.println("Singleton Instance created");
	}

	// Only exposed method to get an Instance
	public static Singleton getInstance()
	{
		if (singletonInstance == null)
		{
			// Lazy initialization
			singletonInstance = new Singleton();
			return singletonInstance;
		}

		return null;
	}
}
