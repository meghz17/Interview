import java.util.ArrayList;
import java.util.HashMap;


public class Backtracking 
{
	public static final int N = 8;
	
	public boolean knightsTour(int sol[][], int xMove[], int yMove[], int x, int y, int movei) 
	{
		int k, next_x, next_y;
		
		if (movei == N * N)
			return true;
	
		System.out.println(movei);
		
		for (k = 0; k < 8; k++) 
		{
			next_x = x + xMove[k];
			next_y = y + yMove[k];
			if (isKnightSafe(sol, next_x, next_y)) 
			{
				sol[next_x][next_y] = movei;
				if (knightsTour(sol, xMove, yMove, next_x, next_y, movei + 1))
					return true;
				else
					sol[next_x][next_y] = -1;// backtracking
			}
		}
		
		return false;
	}

	private boolean isKnightSafe(int[][] sol, int x, int y) 
	{
		return (x >= 0 && x < N && y >= 0 &&
                y < N && sol[x][y] == -1);
	}
	
	public ArrayList<String> letterCombinations(int[] arr)
    {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "");
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        
    
        ArrayList<String> result = new ArrayList<String>();
        
        StringBuilder str = new StringBuilder();
        
        return letterHelper(result, arr, map, 0, str);
    }
    
    public ArrayList<String> letterHelper(ArrayList<String> result, int[] arr, HashMap<Integer, String> map, int index, StringBuilder str)
    {
        if(index >= arr.length)
        {
            result.add(new String(str));
            return result;
        }
        
        
        
        if(arr[index] == 1)
        {
            result = letterHelper(result, arr, map, index+1, str);
        }
        else
        {
            String s = map.get(arr[index]);
            for(int i = 0; i < s.length(); i++)
            {
                str.append(s.charAt(i));
                result = letterHelper(result, arr, map, index+1, str);
                str.deleteCharAt(str.length() - 1);
            }
        }
        
        
        
        return result;
    }
	
	
}
