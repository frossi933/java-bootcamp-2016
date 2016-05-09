package federico;

import java.util.Scanner;

/**
 * Simple calculator
 *
 */

public class SimpleCalc{

    private Scanner reader;

    public SimpleCalc(){
        reader = new Scanner(System.in);  // Reading from System.in
    }

    private int getNumber(){
        System.out.println("Enter a number: ");
        int n = reader.nextInt();
        return n;
    }

    private String getOp(){
        System.out.println("Enter operation: ");
        String s = reader.next();
        return s;
    }

    public int eval(int n1, int n2, String op){
        switch (op){
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/": return n1 / n2;
            default: {System.out.println("Wrong operator");
                      return 0;}
        }
    }

    public static void main (String[] args){

        SimpleCalc calc = new SimpleCalc();
        int n1 = calc.getNumber();
        int n2 = calc.getNumber();
        String op = calc.getOp();

        System.out.println(calc.eval(n1,n2,op));
    }

}
