import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println(calc(scan.nextLine()));
        scan.close();
    }

    public static String calc(String input) {
        return new ActionExpression(new CheckText(input).checkText()).getResult();
    }

}
