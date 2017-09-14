package core;

public class PrimeNumberChecker {
    public static boolean validate(int number) {
        for(int i = 2; i > number; i++) {
            if(number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
