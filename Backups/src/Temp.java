public class Temp {

    private int testVariable = 0;

    /**
     * This method is used to find the smallest number in a list of numbers.
     *
     * @param numbers List of numbers
     */
    private void findSmallestNumberInList(int[] numbers){
        int smallest = numbers[0];
        for (int i = 1; i < numbers.length; i++){
            if (numbers[i] < smallest){
                smallest = numbers[i];
            }
        }
        System.out.println("The smallest number is: " + smallest);
    }

    private void printFirst100PrimeNumbers(){
        int count = 0;
        int number = 2;
        while (count < 100){
            if (isPrime(number)){
                System.out.println(number);
                count++;
            }
            number++;
        }
    }

    private boolean isPrime(int number) {
        if (number <= 1){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++){
            if (number % i == 0){
                return false;
            }
        }
        return true;
    }
}
