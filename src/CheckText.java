import java.util.ArrayList;

/*
Класс для проверки соотвествия введёного текста допустимому арифметическому выражению,
возвращает массив строк, у которого в значекнии 0 устанавливается статус,
если ошибка в введёном тексте, то устанавливает номер ошибки из BugsExpression,
если всё нормально, то уставливает в какой системе выражение arabian или roman.
Остальные значения массива от 1 до SIZE_ARRAY - 1 это значения арифметического выражения попорядку.
 */

class CheckText {
    private final int SIZE_ARRAY = 4;
    private String input;
    private ArrayList<String> resultAfterCheck = new ArrayList<>();

    CheckText(String input) {
        this.input = input.replaceAll("\\s", "").toUpperCase();
    }

    ArrayList checkText() {
        resultAfterCheck.add(BugsExpression.UNKNOW_ERROR);
        checkArithmeticExpression();
        return resultAfterCheck;
    }

    private void checkArithmeticExpression() {
        String value = "";
        done:
        for (int i = 0; i < input.length(); i++) {
            if (Character.isDigit(input.charAt(i))) {
                if (i == 0 || !resultAfterCheck.get(BugsExpression.POSITION_STATUS).equals(BugsExpression.ROMAN_VALUES)) {
                    do {
                        value += input.charAt(i);
                        i++;
                    } while (i < input.length() && Character.isDigit(input.charAt(i)));
                    if (isAcceptableValues(value)) {
                        resultAfterCheck.add(value);
                        resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ARABIAN_VALUES);
                        value = "";
                        i--;
                        continue done;
                    } else {
                        resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_ACCEPTABLE_VALUE); //Недопустимое значение числа
                        return;
                    }
                } else {
                    resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_ARABIAN_AND_ROMAN);  // Найдены римские и арабские числа
                    return;
                }
            } else if (RomanNumbers.isAcceptableRomanNumber(input.charAt(i))) {
                if (i == 0 || !resultAfterCheck.get(BugsExpression.POSITION_STATUS).equals(BugsExpression.ARABIAN_VALUES)) {
                    do {
                        value += input.charAt(i);
                        i++;
                    } while (i < input.length() && RomanNumbers.isAcceptableRomanNumber(input.charAt(i)));
                    if (RomanNumbers.isAcceptableRomanNumber(value)) {
                        if (isAcceptableValues(RomanNumbers.convertRomanToArabian(value))) {
                            resultAfterCheck.add(value);
                            resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ROMAN_VALUES);
                            value = "";
                            i--;
                            continue done;
                        } else {
                            resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_ACCEPTABLE_VALUE); //Недопустимое значение числа
                            return;
                        }
                    } else {
                        resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_FORMAT_ROMAN); // Неверный формат римского числа
                        return;
                    }
                } else {
                    resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_ARABIAN_AND_ROMAN); // Найдены римские и арабские числа
                    return;
                }
            } else {
                if (Operations.getNameOperator(Character.toString(input.charAt(i))) != null && i != 0 && i + 1 != input.length()
                        && !Operations.isOperator(resultAfterCheck.get(resultAfterCheck.size() - 1))) {
                    resultAfterCheck.add(Operations.getNameOperator(Character.toString(input.charAt(i))).name());
                    continue done;
                }
            }
            resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_NOT_ARITHMETIC_OPERATION); // не является арифметическим выражением
            return;

        }

        if (resultAfterCheck.size() != SIZE_ARRAY) {
            resultAfterCheck.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_NOT_ACCEPTABLE_EXPRESSION); //Недопустимое арифметическое выражение
            return;
        }
        return;
    }

    //Метод проверяет лежит ли значение в диапозоне от 1 до 10 включительно
    private boolean isAcceptableValues(String value) {
        try {
            if (Integer.parseInt(value) > 0 && Integer.parseInt(value) <= 10) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

}
