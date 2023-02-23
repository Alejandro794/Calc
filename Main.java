package Calculator;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] array = input.split(" ");

        if (array.length < 3)
            throw new ArrayIndexOutOfBoundsException("Строка не является математической операцией");
        else if (input.contains("."))
            throw new Exception("Принимаются лишь целые числа и числа в регламентированном формате");
        else if (array.length > 3)
            throw new ArrayIndexOutOfBoundsException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

        String number_0 = array[0];
        String number_2 = array[2];

        if (!number_0.matches("[0-9]+") && number_2.matches("[0-9]+"))
            throw new Exception("Используются одновременно разные системы счисления или введен не регламентированный символ");
        else if (number_0.matches("[0-9]+") && !number_2.matches("[0-9]+"))
            throw new Exception("Используются одновременно разные системы счисления или введен не регламентированный символ");

        try {int arabian_result = arabian(input);
             return Integer.toString(arabian_result);
        } catch (NumberFormatException e) {
             int arabian_result = roman(input);
             return convert_in_roman(arabian_result);
        }
    }

    public static int arabian(String input) throws Exception {
        String[] array = input.split(" ");
        int number_arabian_0 = Integer.parseInt(array[0]);
        int number_arabian_2 = Integer.parseInt(array[2]);
        int result;

        if (number_arabian_0 < 1 || number_arabian_0 > 10 || number_arabian_2 < 1 || number_arabian_2 > 10) {
            throw new Exception("Числа должны быть в диапазоне от 1 до 10");
        } else if (input.contains("+")) {
            result = number_arabian_0 + number_arabian_2;
            return result;
        } else if (input.contains("-")) {
            result = number_arabian_0 - number_arabian_2;
            return result;
        } else if (input.contains("*")) {
            result = number_arabian_0 * number_arabian_2;
            return result;
        } else if (input.contains("/")) {
            result = number_arabian_0 / number_arabian_2;
            return result;
        }
        return 0;
    }

    public static int roman(String input) throws Exception {
        String[] numbers_roman_x = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String[] array = input.split(" ");
        String number_roman_0 = array[0];
        String number_roman_2 = array[2];
        int number_arabian_0;
        int number_arabian_2;
        int result;

        for (String i : numbers_roman_x) if (i.equals(number_roman_0)) number_roman_0 = i;
        number_arabian_0 = Arrays.asList(numbers_roman_x).indexOf(number_roman_0);

        for (String i : numbers_roman_x) if (i.equals(number_roman_2)) number_roman_2 = i;
        number_arabian_2 = Arrays.asList(numbers_roman_x).indexOf(number_roman_2);

        if (number_arabian_0 == -1 || number_arabian_2 == -1) {
            throw new Exception("Числа должны быть в диапазоне от I до X");
        } else if (input.contains("+")) {
            result = number_arabian_0 + number_arabian_2;
            return result;
        } else if (input.contains("-")) {
            result = number_arabian_0 - number_arabian_2;
            if (result >= 1) return result;
            else throw new Exception("В римской системе счисления нет отрицательных чисел");
        } else if (input.contains("*")) {
            result = number_arabian_0 * number_arabian_2;
            return result;
        } else if (input.contains("/")) {
            result = number_arabian_0 / number_arabian_2;
            if (result >= 1) return result;
            else throw new Exception("В римской системе счисления нет отрицательных чисел");
        }
        return 0;
    }

    public static String convert_in_roman(int arabian_result) {
        int[] numbers_arabian_100 = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numbers_roman_c = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder convert = new StringBuilder();
        for (int i = 0; i < numbers_arabian_100.length; i += 1) {
            while (arabian_result >= numbers_arabian_100[i]) {
                arabian_result -= numbers_arabian_100[i];
                convert.append(numbers_roman_c[i]);
            }
        }
        return convert.toString();
    }
}