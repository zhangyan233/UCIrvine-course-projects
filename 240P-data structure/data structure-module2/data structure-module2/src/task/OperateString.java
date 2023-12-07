package task;

public class OperateString {

    //calculate two numbers in different situations
    public Integer Calculate(Integer num1, Integer num2, char operator) throws Exception {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    throw new Exception("division by zero");
                }
                return num1 / num2;
        }
        return 0;
    }

    public Integer CalculateString(String str) throws Exception {
        //since number and operator are different element type, need two stacks;
        Stack stackOperator = new Stack();
        Stack stackNumber = new Stack();

        //2.invalid operations, throw exception


        //str[i] has four situations:
        for (int i = 0; i < str.length(); i++) {
            //1.white space, continue
            if (str.charAt(i) == ' ') {
                continue;
            } else if (Character.isDigit(str.charAt(i))) {//2.number, find the value of number, push it into stackNumber
                int num = 0;
                while (i < str.length() && Character.isDigit(str.charAt(i))) {
                    int value = Character.getNumericValue(str.charAt(i));
                    num = num * 10 + value;
                    i++;
                }
                i--;//in order to end the circle, str.charAt(i) is not a digit at this time, but don't know what is, so should i-- and i++ find what it is

                stackNumber.push(num);

            } else if (str.charAt(i) == '-' || str.charAt(i) == '+' || str.charAt(i) == '/' || str.charAt(i) == '*') {
                //3. operator;compare with the first operator in a stackOperator, and have four situations:
                //1) first:+ or - now:+ or - use the first operator to calculate the first number and the second number in stackNumber
                //2) first:* or / now:* or/  use the first operator to calculate the first number and the second number in stackNumber
                //3) first:* or / now: + or - use the first operator to calculate the first number and the second number in stackNumber
                //4) first:+or - now:* or / without any process

                char now = str.charAt(i);
                if (stackOperator.size() != 0) {
                    while (stackOperator.size() != 0 && ((((char) stackOperator.peek() == '+' || (char) stackOperator.peek() == '-') && (now == '+' || now == '-'))
                            || ((char) stackOperator.peek() == '*' || (char) stackOperator.peek() == '/'))) {
                        //former: '+','-' now: '+', '-' or '*','/' now: '*' '/' or former:'/' '*' now: '+' '-' calculate
                        int nowNumber = (int) stackNumber.pop();
                        int beforeNumber = (int) stackNumber.pop();
                        stackNumber.push(Calculate(beforeNumber, nowNumber, (char) stackOperator.peek()));
                        stackOperator.pop();
                    }
                }
                stackOperator.push(now);
            } else {//4. invalid operators, throw an exception
                throw new Exception("invalid operators");
            }
        }
        //judge whether two stacks contains unused operators and elements
        while (stackOperator.size() != 0) {
            char ope = (char) stackOperator.pop();
            int nowNumber = (int) stackNumber.pop();
            int beforeNumber = (int) stackNumber.pop();
            stackNumber.push(Calculate(beforeNumber, nowNumber, ope));
        }
        return (Integer) stackNumber.peek();
    }
}
