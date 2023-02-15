import java.util.Scanner;

public class Main {
    private final static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

    }

    // Я добавил этот метод, чтобы в случае, когда дробная часть = 0
    // выводилось число без дробной части
    private static double fractionalPart(double d){
        return d - Math.floor(d);
    }



    private static int convertDoubleToInt(double d) {
        return (int) d;
    }

    private static double exponent(int A, int B) {
        double result = 1;

        if (B >= 1) {
            for (int i = 0; i < B; i++) {
                result *= A;
            }
        } else if (B <= -1) {
            B *= -1;
            double tempResult = 1;
            for (int i = 0; i < B; i++) {
                tempResult *= A;
            }
            result = 1 / tempResult;
        } else if (A !=0 && B == 0) {
            result = 1;
        } else if (A == 0 && B == 0) {
            result = 0;
        }
        return result;
    }

    private static int getNumberTerminal() {
        return scan.nextInt();
    }
}