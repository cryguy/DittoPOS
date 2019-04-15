package DittoPOS.helpers;

import java.util.Scanner;

public class input {
    public interface InputGrabber<T> {

        boolean hasNextInput(Scanner scanner);

        T getNextInput(Scanner scanner);

        String getExpectedInputFormat();

    }

    public static class IntegerInputGrabber implements InputGrabber<Integer> {

        public boolean hasNextInput(Scanner scanner) {
            return !scanner.hasNextInt();
        }

        public Integer getNextInput(Scanner scanner) {
            return scanner.nextInt();
        }

        public String getExpectedInputFormat() {
            return "Please enter a integer. ex - \"100\"";
        }

    }

    public static class DoubleInputGrabber implements InputGrabber<Double> {

        public boolean hasNextInput(Scanner scanner) {
            return !scanner.hasNextDouble();
        }

        public Double getNextInput(Scanner scanner) {
            return scanner.nextDouble();
        }

        public String getExpectedInputFormat() {
            return "Please enter a double value . ex - \"123.45\"";
        }

    }


}
