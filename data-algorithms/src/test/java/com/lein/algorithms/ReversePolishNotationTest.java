package com.lein.algorithms;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import org.junit.Test;

/**
 * @Author Lein
 * @Description Unit test for Reverse PolishNotationT.
 */
public class ReversePolishNotationTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void reversePolishNotationTest() throws Exception {
        String infixExpression = "10+2.5*(3.15+1)+7*2.99";
        ReversePolishNotation rpn = new ReversePolishNotation();
        System.out.println(rpn.ExpStr2Array(infixExpression).toString());
        LinkedList<String> postfixExp = rpn.infixToPostfix(rpn.ExpStr2Array(infixExpression));
        System.out.println(postfixExp.toString());
        String result = rpn.calc(postfixExp);
        assertEquals(result,"41.305");
    }
}
