/**
 * Capture a person who can drink soda.
 * A person can sip or gulp from a can of soda. 
 * The person knows how much soda they have consumed,
 * and know how thirsty they are.
 */
public class Person {
        private String name;
        private int amountDrunk;
        private String thirstStatus;

        public static void main(String [] args){
        
        
        
        }//main

	/**
	 * Construct a new Person with the given name.
	 * So far they will have consumed 0 soda
	 * @param name the name given to this person
	 */
        public Person(String name){
            this.name = name;
            this.amountDrunk = 0;
            this.thirstStatus = "very thirsty";
        }

	/**
	 * Take a sip from s, modifying the amount this has consumed
	 * @param s the can of soda this will gulp from
	 */
        public void sipFrom(SodaCan s){
            int drunk = s.sip();
            this.amountDrunk += drunk;

            if (this.amountDrunk < 175){
                this.thirstStatus = "very thirsty";
            }else if(this.amountDrunk < 375){
                this.thirstStatus = "thirsty";
            }else{
                this.thirstStatus = "satisfied";
            }
        }

	/**
	 * Take a gulp from s, modifying the amount this has consumed
	 * @param s the can of soda this will gulp from
	 */
        public void gulpFrom(SodaCan s){
            int drunk = s.gulp();
            this.amountDrunk += drunk;

            if (this.amountDrunk < 175){
                this.thirstStatus = "very thirsty";
            }else if(this.amountDrunk < 375){
                this.thirstStatus = "thirsty";
            }else{
                this.thirstStatus = "satisfied";
            }
        }

	/**
	 * A person is 
 	 * "very thirsty" if they drank less than 175,
 	 * "thirsty" if they drank less than 375, 
 	 * "satisfied" if they drank at least 375
	 * @return the thirst status of this
	 */
        public String getThirstStatus(){
        

            return this.thirstStatus;

        }

	/**
	 * @return a string representation of this
	 */
	public String toString(){
		return "I am "+this.name+", and I am "+this.getThirstStatus();
	}
}

