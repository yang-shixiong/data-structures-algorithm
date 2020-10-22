package com.yang.stack;

/**
 * Description:
 *
 * @author mark
 * Date 2020/10/21
 */
public class MiddleCal {

    public static void main(String[] args) {
        String str = "7*2*(2-10)+60-5+3-20";
        StackCal numStack = new StackCal(10);
        StackCal operaStack = new StackCal(10);
        String k = "";
        for (int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(numStack.isNum(c)){
                k += c;

                if(i == str.length() -1){
                    numStack.add(Integer.parseInt(k));
                    k = "";
                }else if(!numStack.isNum(str.charAt(i + 1))){
                    numStack.add(Integer.parseInt(k));
                    k = "";
                }

            }else {
                if (!(operaStack.isEmpty() || c == '(')) {
                    if(c == ')'){
                        while (true){
                            int oper = operaStack.pop();
                            if(oper != '('){
                                int num2 = numStack.pop();
                                int num1 = numStack.pop();
                                int cal = operaStack.cal(num1, num2, oper);
                                numStack.add(cal);
                            }else {
                                break;
                            }
                        }
                    }else {
                        if (operaStack.priority(c) <= operaStack.priority((char) operaStack.peek())) {
                            int num2 = numStack.pop();
                            int num1 = numStack.pop();
                            int pop = operaStack.pop();
                            int cal = operaStack.cal(num1, num2, pop);
                            numStack.add(cal);
                        }
                        operaStack.add(c);
                    }

                }else {
                    operaStack.add(c);
                }
            }
        }

        while (!operaStack.isEmpty()){
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            int opera = operaStack.pop();
            int cal = operaStack.cal(num1, num2, opera);
            numStack.add(cal);
        }

        System.out.println("result = " + numStack.pop());
    }
}

class StackCal extends Stack {
    public StackCal(int maxSize) {
        super(maxSize);
    }

    public int priority(char value) {
        if (value == '*') {
            return 2;
        } else if (value == '/') {
            return 2;
        } else if (value == '+') {
            return 1;
        } else if (value == '-') {
            return 1;
        } else if (value == '(') {
            return 0;
        } else {
            System.out.println("not support");
            return -1;
        }
    }

    public int peek(){
        if(isEmpty()){
            System.out.println("empty");
            return -1;
        }
        return arr[top];
    }

    public boolean isNum(char value){

        return !(value == '*' || value == '/' || value == '+' || value == '-' || value == '(' || value == ')');
    }

    public int cal(int num1, int num2, int opera){
        if (opera == '*') {
            return num1 * num2;
        } else if (opera == '/') {
            return num1 / num2;
        } else if (opera == '+') {
            return num1 + num2;
        } else if (opera == '-') {
            return num1 - num2;
        }else {
            System.out.println("not support");
            return -1;
        }
    }
}