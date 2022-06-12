/*POSITION_STATUS хранит в себе состояние ошибок во время проверки, если
первоначальная проверка прошла, то в неё записывается ошибка при вычислении, либо при благополучном исходе результат
 */

class BugsExpression extends Exception {
    static final String ERROR_ARABIAN_AND_ROMAN = "0";
    static final String ERROR_NOT_ARITHMETIC_OPERATION = "1";
    static final String ERROR_NOT_ACCEPTABLE_EXPRESSION = "2";
    static final String ERROR_FORMAT_ROMAN = "3";
    static final String ERROR_ACCEPTABLE_VALUE = "4";
    static final String UNKNOW_ERROR = "5";
    static final String ERROR_NEGATIVE_VALUE = "6";
    static final String VERY_UNKNOW_ERROR = "7";
    static final String ERROR_DIVISION_ZERO = "8";
    static final String ARABIAN_VALUES = "arabian";
    static final String ROMAN_VALUES = "roman";
    static final int POSITION_STATUS = 0;

    final private  String[] ERROR_MESSAGES = {
            "throws Exception //т.к. используются одновременно разные системы счисления",                                                   //[0]
            "throws Exception //т.к. строка не является математической операцией",                                                          //[1]
            "throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)",  //[2]
            "throws Exception //т.к. введён неверный формат римского числа",                                                                //[3]
            "throws Exception //т.к. калькулятор работает только с числами от 1 до 10 включительно или от I до X включительно",             //[4]
            "throws Exception //т.к. неизвестная ошибка",                                                                                   //[5]
            "throws Exception //т.к. в римской системе нет отрицательных чисел",                                                            //[6]
            "throws Exception //т.к. произошло что-то совсем непреодолимое",                                                                //[7]
            "throws Exception //т.к. попытка деления на ноль"                                                                               //[8]
    };

    private String returnMessage;

    BugsExpression(String positionStatus){
        try {
            returnMessage = ERROR_MESSAGES[Integer.parseInt(positionStatus)];
        }catch (NumberFormatException e){
            returnMessage = VERY_UNKNOW_ERROR;
        }
    }

    String getBugMessage(){
        return returnMessage;
    }

}
