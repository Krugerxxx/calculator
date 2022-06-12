//Перечисление возможных операций и методы с ними
enum Operations {
    ADDITION("+") {
        public int action(int x, int y) {
            return x + y;
        }
    }, SUBTRACTION("-") {
        public int action(int x, int y) {
            return x - y;
        }
    }, MULTIPLY("*") {
        public int action(int x, int y) {
            return x * y;
        }
    }, DIVISION("/") {
        public int action(int x, int y) {
            return x / y;
        }
    };

    private String operator;

    //Метод возвращает результат операции с двумя операндами
    public abstract int action(int x, int y);

    Operations(String operator) {
        this.operator = operator;
    }

    static Operations getNameOperator(String value){
        for(Operations o : Operations.values()){
            if (value.equals(o.getOperator())){
                return o;
            }
        }
        return null;
    }

    static boolean isOperator(String value){
        for(Operations o : Operations.values()){
            if(value.equals(o.name())){
                return true;
            }
        }
        return false;
    }

    private String getOperator() {
        return operator;
    }

}
