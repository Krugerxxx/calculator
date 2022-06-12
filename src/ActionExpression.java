import java.util.ArrayList;

//Вычисляет результат операции
class ActionExpression {
    private ArrayList<String> inputExpression;

    ActionExpression(ArrayList<String> inputExpression) {
        this.inputExpression = inputExpression;
        try {
            if (inputExpression.get(BugsExpression.POSITION_STATUS).equals(BugsExpression.ARABIAN_VALUES)) {
                for (int i = BugsExpression.POSITION_STATUS + 1; i < inputExpression.size(); i++) {
                    if (Operations.isOperator(inputExpression.get(i))) {
                        inputExpression.set(BugsExpression.POSITION_STATUS, Integer.toString(Operations.valueOf(inputExpression.get(i)).action(Integer.parseInt(inputExpression.get(i - 1)),
                                Integer.parseInt(inputExpression.get(i + 1)))));
                    }
                }
            } else if (inputExpression.get(BugsExpression.POSITION_STATUS).equals(BugsExpression.ROMAN_VALUES)) {
                for (int i = BugsExpression.POSITION_STATUS + 1; i < inputExpression.size(); i++) {
                    if (Operations.isOperator(inputExpression.get(i))) {
                        int res = Operations.valueOf(inputExpression.get(i)).action(Integer.parseInt(RomanNumbers.convertRomanToArabian(inputExpression.get(i - 1))),
                                Integer.parseInt(RomanNumbers.convertRomanToArabian(inputExpression.get(i + 1))));
                        if (res <= 0) {
                            inputExpression.set(BugsExpression.POSITION_STATUS, BugsExpression.ERROR_NEGATIVE_VALUE);
                            throw new BugsExpression(inputExpression.get(BugsExpression.POSITION_STATUS));
                        }else {
                            inputExpression.set(BugsExpression.POSITION_STATUS, RomanNumbers.convertArabianToRoman(res));
                        }
                    }
                }
            } else {
                throw new BugsExpression(inputExpression.get(BugsExpression.POSITION_STATUS));
            }
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            inputExpression.set(BugsExpression.POSITION_STATUS, new BugsExpression(BugsExpression.VERY_UNKNOW_ERROR).getBugMessage());
        } catch (ArithmeticException e) {
            inputExpression.set(BugsExpression.POSITION_STATUS, new BugsExpression(BugsExpression.ERROR_DIVISION_ZERO).getBugMessage());
        } catch (BugsExpression e) {
            inputExpression.set(BugsExpression.POSITION_STATUS, e.getBugMessage());
        }
    }

    String getResult(){
        return inputExpression.get(BugsExpression.POSITION_STATUS);
    }

}
