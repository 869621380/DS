import java.util.Stack;

public class StackApplication {
    public static void main(String[]args){
        StringBuilder theInfix=new StringBuilder("3.5*4+2.1/3+(3-2)%5");
        theInfix.append(' ');//这一步是为了防止判断数字时出现越界
        StringBuilder thePostfix=infixToPostfix(theInfix);
        System.out.printf("转化后的后缀表达式为:%s\n", thePostfix);
        System.out.println("计算结果为："+calculate(thePostfix));
    }
    public static StringBuilder infixToPostfix(StringBuilder s){
        StringBuilder result= new StringBuilder();
        Stack<Character>theStack=new Stack<>();
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(ch==' ')continue;
            //处理遇到数字的情况
            if(Character.isDigit(ch)){
                //将数字持续读取完毕（包含小数情况）
                while(Character.isDigit(ch)||ch=='.'){
                    result.append(ch);
                    i++;
                    ch=s.charAt(i);
                }
                i--;//回退到当前字符
                result.append(' ');
            }
            //遇到左括号直接进栈
            else if(ch=='('){
                theStack.push(ch);
            }
            //遇到右括号持续出栈直到找到相匹配的左括号
            else if(ch==')'){
                while(!theStack.peek().equals('(')){
                    result.append(theStack.peek()).append(' ');
                    theStack.pop();
                }
                theStack.pop();//弹出左括号
            }
            //遇到运算符与栈顶元素进行比较，若是优先级低于或等于栈顶元素则持续出栈直到优先级高于栈顶元素或栈空
            else {
                if(theStack.empty()){theStack.push(ch);continue;}
                else if(change(ch)<=change(theStack.peek())){
                    while (!theStack.empty()&&change(ch)<=change(theStack.peek())){
                        result.append(theStack.peek()).append(' ');
                        theStack.pop();
                    }
                }
                theStack.push(ch);
            }
        }
        //栈顶剩余的运算符全部出栈
        while(!theStack.empty()){
            result.append(theStack.peek()).append(' ');
            theStack.pop();
        }
        return result;
    }
    public static double calculate(StringBuilder postfix){
        Stack<Double>ans=new Stack<>();
        String[]element=postfix.toString().split("\\s+");
        for (String s:element){
            if(Character.isDigit(s.charAt(0)))
                ans.push(Double.parseDouble(s));
            else{
                double num1=ans.peek();
                ans.pop();
                double num2=ans.peek();
                ans.pop();
                switch (s) {
                    case "+":
                        ans.push(num1 + num2);
                        break;
                    case "-":
                        ans.push(num2 - num1);
                        break;
                    case "*":
                        ans.push(num1 * num2);
                        break;
                    case "/":
                        ans.push(num2 / num1);
                        break;
                    case "^":
                        ans.push(Math.pow(num2, num1));
                        break;
                    case "%":
                        ans.push(num2 % num1);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid operator: " + s);
                }
            }
        }
        return ans.peek();
    }

    //判断运算符的优先级
    private static int change(char a) {
        if (a == '+' || a == '-')return 1;
        if (a == '*' || a == '/' || a == '%')return 2;
        if (a == '^')return 3;
        return 0;
    }
}