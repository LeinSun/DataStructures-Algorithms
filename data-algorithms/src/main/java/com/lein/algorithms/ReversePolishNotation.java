package com.lein.algorithms;

import com.lein.utils.Utils;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author Lein
 * @Description Postfix Expression that is implemented by Queue and Stack
 */

public class ReversePolishNotation {

  /**
   * 将中缀表达式转换成后缀表达式
   * @param expression 中缀表示数组
   * @return postfixExpression 后缀表达式数组
   */
  public LinkedList<String> infixToPostfix(LinkedList<String> expression){
    LinkedList<String> postfixExpression = new LinkedList<String>();
    Stack<String> expStack = new Stack<String>();
    for (String expItem : expression) {
      //如果是数字，不需要入栈，直接加到后缀表达式上
      if (Utils.isNumeric(expItem)){
        postfixExpression.add(expItem);
      //如果是右括号，弹出栈顶元素并加到表达式，直到栈空或者遇到左括号
      }else if (expItem.equals(")")){
        String popStr = expStack.pop();
        while (!expStack.isEmpty() && !popStr.equals("(")){
          postfixExpression.add(popStr);
          popStr = expStack.pop();
        }
      //如果是左括号，直接入栈
      }else if(expItem.equals("(")){
        expStack.push(expItem);
      //依次弹出栈中优先级高于或等于当前运算符的所有运算符，并加入后缀表达式，
      // 若碰到“ (” 或栈空则停止。之后再把当前运算符入栈。
      } else {
        if (!expStack.isEmpty()) {
          String popStr = expStack.peek();
          while (!expStack.isEmpty() && this.getOPPriority(popStr) - this.getOPPriority(expItem) >= 0) {
            popStr = expStack.pop();
            if (expItem.equals("(")){
              break;
            }
            postfixExpression.add(popStr);
          }
        }
        expStack.push(expItem);
      }
    }
    while (!expStack.isEmpty()) {
      postfixExpression.add(expStack.pop());
    }
    return postfixExpression;
  }

  public static void main(String[] args) throws Exception {
    String infixExpression = "10+2.5*(3.15+1)+7*2.99";
    ReversePolishNotation rpn = new ReversePolishNotation();
    System.out.println(rpn.ExpStr2Array(infixExpression).toString());
    LinkedList<String> postfixExp = rpn.infixToPostfix(rpn.ExpStr2Array(infixExpression));
    System.out.println(postfixExp.toString());
    System.out.println(rpn.calc(postfixExp));

  }

  /**
   * 根据输入的后缀表达式计算
   * @param postfixExp 后缀表达式组成的数组
   * @return
   * @throws Exception
   */
  public String calc(LinkedList<String> postfixExp) throws Exception {
    Stack<String> stack = new Stack<String>();
    for (String expItem : postfixExp) {
      // 扫描到操作数则压入栈
      if (Utils.isNumeric(expItem)){
        stack.push(expItem);
      }else { //扫描到运算符，则弹出两个栈顶元素，执行相应运算，运算结果压回栈顶
        if (stack.size() >= 2){
          BigDecimal result;
          BigDecimal num1 = new BigDecimal(stack.pop());
          BigDecimal num2 = new BigDecimal(stack.pop());
          switch (expItem){
            case "+":
              result = num1.add(num2);
              break;
            case "-":
              result = num1.subtract(num2);
              break;
            case "*":
              result = num1.multiply(num2);
              break;
            case "/":
              result = num2.divide(num1);
              break;
            default:
              throw new Exception("Don't support operator: " + expItem);
          }
          stack.push(result.toString());
        }

      }
    }
    return stack.pop();
  }

  /**
   * 将获取运算符的优选级，加减的优先级是1，乘除法优先级是2
   * @param op
   * @return
   */
  private short getOPPriority(String op){
    String zeroPriorityOPs = "+-";
    String onePriorityOPs = "*/";
    if (zeroPriorityOPs.contains(op))
      return 0;
    if (onePriorityOPs.contains(op))
      return 1;
    return -1;
  }

  /**
   * 表达式字符串转数组
   * @param expression
   * @return
   */
  public LinkedList<String> ExpStr2Array(String expression){
    LinkedList<String> ll = new LinkedList<String>();
    String expItem = "";
    for (char c : expression.toCharArray()) {
      String cStr = String.valueOf(c);
      if (!"()+-*/".contains(cStr)){
        expItem = expItem + cStr;
      }else {
        if (!"".equals(expItem)){
          ll.add(expItem);
        }
        ll.add(String.valueOf(c));
        expItem = "";
      }
    }
    ll.add(expItem);
    return ll;
  }

}
