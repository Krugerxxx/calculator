/*
    Перечисление разрядов римских чисел, с методами проверки на соответсвие значений им и
    методами конвертации римских числе в арабские и обратно.
*/
enum RomanNumbers {
    I(1), V(5), X(10), L(50), C(100);

    private static final int MAX_MULTIPLY_FOR_TEN = 4;
    private static final int MAX_MULTIPLY_FOR_FIVE = 9;
    private static final String BAD_VALUE = "0";
    private int number;

    RomanNumbers(int number) {
        this.number = number;
    }

    //Метод проверки правильного формата римского числа
    static boolean isAcceptableRomanNumber(String text) {
        try {
            if(convertArabianToRoman(Integer.parseInt(convertRomanToArabian(text))).equals(text)){
                return true;
            }
        }catch (NumberFormatException e){
            return false;
        }
        return false;
    }

    //Метод проверки соответствия символа какому-либо возможному перечислению доступных римских чисел
    static boolean isAcceptableRomanNumber(char symbol) {
        try {
            RomanNumbers.valueOf(Character.toString(symbol));
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    //Метод конвертирует римские числа в арабские
    static String convertRomanToArabian(String input) {
        String output = "";
        int result = 0;
        int newValue = 0;
        int oldValue = 0;
        for (int i = 0; i < input.length(); i++) {
            newValue = RomanNumbers.valueOf(input.substring(i, i + 1)).number;
            if (newValue > oldValue) {
                result += newValue - 2 * oldValue;
            } else {
                result += newValue;
            }
            oldValue = newValue;
        }
        output += result;
        return output;
    }

    //Метод конвертирует арабские числа в римские, если введено число, невозможное для данного перечисления или недопустимое для римских чисел,
    //то возвращает BAD_VALUE
    static String convertArabianToRoman(int input) {
        if (input > getMaxNumber() || input <= 0) {
            return BAD_VALUE;
        }
        String output = "";
        RomanNumbers[] arrayRomanNumbers = RomanNumbers.values();
        int[] rankNumber = new int[arrayRomanNumbers.length];
        for (int i = arrayRomanNumbers.length - 1; i >= 0; i--) {
            if (i % 2 != 0 && input / arrayRomanNumbers[i - 1].number == MAX_MULTIPLY_FOR_TEN) {
                output += arrayRomanNumbers[i - 1].name() + arrayRomanNumbers[i].name();
                input %= arrayRomanNumbers[i - 1].number;
            } else if (i % 2 != 0 && input / arrayRomanNumbers[i - 1].number == MAX_MULTIPLY_FOR_FIVE) {
                output += arrayRomanNumbers[i - 1].name() + arrayRomanNumbers[i + 1].name();
                input %= arrayRomanNumbers[i - 1].number;
            } else if (i % 2 != 0 && input / arrayRomanNumbers[i].number == 1) {
                output += arrayRomanNumbers[i].name();
                input %= arrayRomanNumbers[i].number;
            } else if (i % 2 == 0) {
                for (int j = 0; j < input / arrayRomanNumbers[i].number; j++) {
                    output += arrayRomanNumbers[i].name();
                }
                input %= arrayRomanNumbers[i].number;
            }
            if (input == 0) {
                break;
            }
        }
        return output;
    }

    //Метод определяет максимальное значение для перечисленных римских чисел
    private static int getMaxNumber() {
        int maxNumber = 0;
        RomanNumbers[] numbers = RomanNumbers.values();
        if (numbers.length == 0) {
            maxNumber = 0;
        } else if ((numbers.length - 1) % 2 == 0) {
            maxNumber = numbers[numbers.length - 1].number * MAX_MULTIPLY_FOR_TEN - 1;
        } else {
            maxNumber = numbers[numbers.length - 2].number * MAX_MULTIPLY_FOR_FIVE - 1;
        }
        return maxNumber;
    }

}
