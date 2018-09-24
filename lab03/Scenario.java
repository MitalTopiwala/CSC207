/**
 * The main method of this class plays out a scenario...
 * Jack has two cans of soda,
 * RootBeer and GingerAle, Jill also has two cans, Cherry and Grape.
 * Jack opens his RootBeer first, and gives it to Jill.
 * Jill completely drinks the RootBeer. Jack asks her if she is still
 * thirsty, Jill responds. Now Jill opens her Cherry soda and drinks it until
 * she is satisfied, then gives it to Jack. He takes a sip, but doesn't
 * like it. Jill checks how much is left in her Cherry soda, but decides
 * not to drink any more. Jack asks Jill if he can try her Grape soda.
 * Jack drinks about half of it, then stops and tells Jill how he now feels.
 * Finally, they check the amount available in all of the cans.
 */
public class Scenario {

    public static void main(String [] args){
        Person Jack = new Person("Jack");
        SodaCan RootBeer = new SodaCan("RootBeer");
        SodaCan GingerAle = new SodaCan("GingerAle");
        Person Jill = new Person("Jill");
        SodaCan Cherry = new SodaCan("Cherry");
        SodaCan Grape = new SodaCan("Grape");

        RootBeer.open();
        Jill.gulpFrom(RootBeer);
        Jill.gulpFrom(RootBeer);
        Jill.gulpFrom(RootBeer);
        Jill.gulpFrom(RootBeer);
        Jill.gulpFrom(RootBeer);
        Jill.getThirstStatus();
        
        Cherry.open();
        Jill.gulpFrom(Cherry);
        Jill.gulpFrom(Cherry);
        Jill.sipFrom(Cherry);
        Jill.sipFrom(Cherry);
        Jill.sipFrom(Cherry);

        Jack.sipFrom(Cherry);
        Cherry.getAmount();

        Grape.open();
        Jack.gulpFrom(Grape);
        Jack.gulpFrom(Grape);
        Jack.sipFrom(Grape);
        Jack.sipFrom(Grape);
        Jack.getThirstStatus();
        
        Cherry.getAmount();
        Grape.getAmount();
        GingerAle.getAmount();
        RootBeer.getAmount();
    }
}

 
