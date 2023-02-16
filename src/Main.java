import java.io.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private final static Scanner scan = new Scanner(System.in);
    private final static String inputFileName = "src/input.txt";

    public static void main(String[] args) throws IOException {

        System.out.print(choosingOperation());
        int userChoosing = scan.nextInt();
        if (userChoosing == 1){
            terminalFunctional();
        } else if (userChoosing == 2){
            writeInFile(inputReader(inputFileName, "a"), inputReader(inputFileName, "b"));
        } else {
            System.out.println(uncorrectedOperation());
        }

    }

    private static String uncorrectedOperation(){
        return "you input uncorrected operation, try again";
    }

    private static String choosingOperation(){
        return "Possible operations: " + "\n"
                + String.format("%-10s\n", "1-Enter 1 to work with the terminal;")
                + String.format("%-10s\n", "2-Enter 2 to work with the file;")
                + "Choose operation: ";
    }

    private static void writeInFile(int variableA, int variableB){
        try(FileWriter fWriter = new FileWriter("src/output.txt")){
            double resultExponent = exponent(variableA, variableB);
            if (fractionalPart(resultExponent) == 0.0) {
                fWriter.write(String.format("%d in degree %d = %d\n",
                        variableA, variableB, convertDoubleToInt(resultExponent)));
            } else {
                fWriter.write(String.format("%d in degree %d = %f\n",
                        variableA, variableB, resultExponent));
                fWriter.close();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    private static int inputReader(String fileName, String variable) throws IOException {
        String variableA = "";
        String variableB = "";
        String str;
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
        while ((str = bReader.readLine()) != null) {
            if (str.contains("a")) {
                variableA = str.chars()
                        .filter(c -> !Character.isLetter(c))
                        .mapToObj(c -> String.valueOf((char) c))
                        .collect(Collectors.joining())
                        .trim();
            } else if (str.contains("b")) {
                variableB = str.chars()
                        .filter(c -> !Character.isLetter(c))
                        .mapToObj(c -> String.valueOf((char) c))
                        .collect(Collectors.joining())
                        .trim();
            } else {
                FileWriter fWriter = new FileWriter("src/error.txt");
                fWriter.write("uncorrected file's format");
            }
        }
        bReader.close();
        if (variable == "a") {
            return Integer.parseInt(variableA);
        } else {
            return Integer.parseInt(variableB);
        }
        }

        private static int countLinesInFile (String fileName) throws FileNotFoundException {
            BufferedReader bReader = new BufferedReader(new FileReader(fileName));
            try {
                String line;
                int count = 0;
                while ((line = bReader.readLine()) != null) {
                    count++;
                }
                return count;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return 0;
        }

        private static void terminalFunctional() {
            System.out.print("Enter tne number: ");
            int A = getNumberTerminal();
            System.out.print("Enter tne degree of a number: ");
            int B = getNumberTerminal();
            double resultExponent = exponent(A, B);
            if (fractionalPart(resultExponent) == 0.0) {
                System.out.printf("%d in degree %d = %d\n", A, B, convertDoubleToInt(resultExponent));
            } else {
                System.out.printf("%d in degree %d = %f\n", A, B, resultExponent);
            }
        }

        // Я добавил этот метод, чтобы в случае, когда дробная часть = 0
        // выводилось число без дробной части
        private static double fractionalPart ( double d){
            return d - Math.floor(d);
        }


        private static int convertDoubleToInt ( double d){
            return (int) d;
        }

        private static double exponent ( int A, int B){
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
            } else if (A != 0 && B == 0) {
                result = 1;
            } else if (A == 0 && B == 0) {
                result = 0;
            }
            return result;
        }

        private static int getNumberTerminal () {
            return scan.nextInt();
        }
    }