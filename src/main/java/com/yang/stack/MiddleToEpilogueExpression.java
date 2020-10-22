package com.yang.stack;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/22
 */
public class MiddleToEpilogueExpression {

    public static void main(String[] args) {
        // [55, 50, 42, 50, 49, 45, 42, 54, 43, 53, 45, 51, 43, 50, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        // 7 2 * 2 1 - * 6 + 5 - 3 + 2 -
        String expression = "7*2*(2-1)+6-5+3-2"; // 2 1 - 7 * 2 * 6 + 5 - 3 + 2 -
//        String expression = "1+((2+3)*4)-5"; //

        StackCal numStack = new StackCal(30);
        StackCal operaStack = new StackCal(30);
        String str = "";
        for (int i = 0; i < expression.length(); i++){
            char value = expression.charAt(i);
            if(numStack.isNum(value)){
                numStack.add(value);
            }else {
                if(operaStack.isEmpty() || value == '('){
                    operaStack.add(value);
                }else if(value == ')'){
                    while (true){
                        char pop = (char)operaStack.pop();
                        if(pop == '('){
                            break;
                        }
                        numStack.add(pop);
                    }
                }else {
                    while (!operaStack.isEmpty() && operaStack.priority(value) <= operaStack.priority((char) operaStack.peek())){
                        int pop = operaStack.pop();
                        numStack.add(pop);
                    }
                    operaStack.add(value);
                }
            }
        }
        while (!operaStack.isEmpty()){
            numStack.add(operaStack.pop());
        }
        numStack.show();
        cal(numStack);
    }

    public static void cal(StackCal stackCal){
        StackCal result = new StackCal(20);
        StackCal newStack = new StackCal(20);
        while (!stackCal.isEmpty()){
            newStack.add(stackCal.pop());
        }
        while (!newStack.isEmpty()){
            int pop = newStack.pop();
            if(result.isNum((char)pop)){
                result.add(pop - 48);
            }else {
                int number2 = result.pop();
                int number1 = result.pop();
                int cal = result.cal(number1, number2, pop);
                result.add(cal);
            }
        }
        System.out.println("result: " + result.pop());
    }
}