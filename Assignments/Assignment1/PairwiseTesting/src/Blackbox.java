/**
 * Blackbox
 * Tanner Turba
 * 9/13/2023
 * This class contains the methods to test using from the PairwiseTesting class.
 */
public class Blackbox {
    public String importantFunction(int pressure, int volume, int velocity, boolean lowFuel ) {
        if (pressure < 10) {
            if (volume > 300) {
                if (velocity == 5) {
                    System.out.println(" A bug happened! ");
                    return " A bug happened! ";
                }
            }
            else if (lowFuel) {
                System.out.println(" Everything looks good! ");
                return " Everything looks good! ";
            }
        }
        else {
            System.out.println(" Everything looks good! ");
            return " Everything looks good! ";
        }
        return null;
    }  
}
