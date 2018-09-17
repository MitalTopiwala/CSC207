
public class PrimeTester{

    public static void main(String [] args){
        
        
        try {
			
		int inte = Integer.parseInt(args[0]);
			
                boolean result = isPrime(inte);

                if (result){
                    System.out.println("Prime");
                }else{
                    System.out.println("Not Prime");

                }
		} catch (NumberFormatException e){
			System.out.println("One of the command line arguments is not an integer");
	}//try

        





    }//main


    public static boolean isPrime(int n){

        for(int i=2; i<n; i+=1){

            if (n%i==0){
                return false;
            }
        }
        return true;
    

    }//isPrime



}//PrimeTester
