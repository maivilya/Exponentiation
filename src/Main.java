import java.util.Scanner;

public class Main {
    private final static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
    }

    private static double exponent(int A, int B){
        double result = 1;

        if (B >= 1){
            for (int i = 0; i < B; i++){
                result *= A;
            }
        } else if (B <= -1){
            B *= -1;
            double tempResult = 1;
            for (int i = 0; i < B; i++){
                tempResult *= A;
            }
            result = 1/tempResult;
        } else if (B == 0) {
            result = 1;
        } else if (A == 0 && B==0){
            result = 0;
        }
        return result;
    }

    private static int getNumberTerminal(){
        return scan.nextInt();
    }
}