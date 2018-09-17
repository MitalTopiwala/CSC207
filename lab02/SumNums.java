import java.io.*; 
import java.util.*;
public class SumNums {

	/**
	 * @param args the command line arguments, we expect user supplied 
	 * integers STARTNUM and ENDNUM
	 */
	
	public static void main(String[] args) {
		try {
			if(args.length!=2){
				System.out.println("Usage: java SumNums STARTNUM ENDNUM");
				return;
			}
			int startNum = Integer.parseInt(args[0]);
			int endNum = Integer.parseInt(args[1]);
			int sum=0;
			sum = sumup(startNum, endNum);
			System.out.println("The sum from "+startNum+ " to "+ endNum + " is: "+ sum);

		} catch (NumberFormatException e){
			System.out.println("One of the command line arguments is not an integer");
		}
	}

	public static int sumup(int s, int e){
                int hold = 0;
                while (s <= e){
                        hold += s;
                        s = s +1;
                }
                return hold;

        }
        // Create static method sumup(s,e) which returns the sum s+(s+1)+...+(e-1)+e
	// or 0 if this sum does not make sense (ie sumup(3,-4)).
}
