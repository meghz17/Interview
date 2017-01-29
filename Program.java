
public class Program {

	public static void main(String[] args) 
	{
		Backtracking backtracking = new Backtracking();
		DynamicProgramming dp = new DynamicProgramming();
		
		int[] arr = {1,5,6,8};
		
		System.out.println(dp.minimumCoinChanging(arr, 12));
	}

}
