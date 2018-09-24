/**
 * Capture a Can of Soda.
 * A Can of Soda has a type, amount (initially 250) and is initially closed.
 * Once opened, you can sip (take at most 10) or gulp (take at most 50) from
 * the can. Obviously, at all times, the amount of soda in the can is between 0 and 250.
 * An opened can can not be closed.
 */
public class SodaCan{
        private int amount;
        private String type;
        private boolean isOpen;


        public static void main(String [] args){
        SodaCan pink  = new SodaCan("Sprite");
        

        
        pink.gulp();
        
        pink.sip();
        
        
        }//main

        

	/**
	 * Construct a new SodaCan of the specified type.
	 * The new can has 250 units in it, and is closed.
	 * @param t the type of soda, for example "RootBeer", "Coke", "Cherry"
	 */
        public SodaCan(String t){
            this.type = t;
            this.amount = 250;
            this.isOpen = false;
        }//constructor



        /**
         * open this SodaCan
         */
        public void open(){
            this.isOpen = true;
        }//open



	/**
	 * @return whether this is open
	 */
        public boolean isOpen(){
            return this.isOpen;
        }//isOpen
	


        /**
         * remove up to 10cc of soda from this, provided this is open
         * @return the amount of soda actually removed
         */
        public int sip(){
        int amountRemoved = 0;
        if (this.isOpen){
            if (this.amount <10){
                amountRemoved = this.amount;
                this.amount = 0;
            }else{
                amountRemoved = 10;
                this.amount = this.amount -10;
            }
        }

        return amountRemoved;
        }//sip



        /**
         * remove up to 50cc of soda from this, provided this is open
         * @return the amount of soda actually removed
         */
        public int gulp(){
        int amountRemoved = 0;
        if (this.isOpen){
            if (this.amount <50){
                amountRemoved = this.amount;
                this.amount = 0;
            }else{
                amountRemoved = 50 ;
                this.amount = this.amount -50;
            }
        }

        return amountRemoved;
        }//gulp



        /**
         * @return the amount of soda left in this
         */
        public int getAmount(){
        return this.amount;
        }//amountLeft



        /**
         * @return a string representation of this
         */
        public String toString(){
		String openString = (this.isOpen)?"open":"closed";
		return this.type+" "+openString+" "+this.amount;
        }
}
