package com.android.rokibulrony.calculator;


public class Calculator {
    private MainActivity context;
    private EquationSolver equationSolver;
    private Equation eq;
    String a ="";

    public Calculator(MainActivity context) {
        this.context = context;
        this.equationSolver = new EquationSolver(context);
        this.eq = new Equation();
        update();
    }

    public String getText() {
        return eq.getText();
    }
    public void setText(String text) {
        eq.setText(text);
        update();
    }

    public void decimal() {
        if (!Character.isDigit(eq.getLastChar()))
            digit('0');
        if (!eq.getLast().contains("."))
            eq.attachToLast('.');
        update();
    }

    public void delete() {

        if (eq.getLast().length() > 1 && (eq.isRawNumber(0) || eq.getLast().charAt(0) == '-'))
            eq.detachFromLast();
        else
            eq.removeLast();
        update();
    }

    public void digit(char digit) {
        if (eq.isRawNumber(0))
            eq.attachToLast(digit);
        else {
            if (eq.isNumber(0))
                eq.add("*");
            eq.add("" + digit);
        }
        update();
    }

    public void equal() {
        String s;
        try {
            s = equationSolver.formatNumber(equationSolver.solve(getText()));
            a=s;
        } catch (Exception e) {
            s = "Error";
        }
        context.displaySecondary(s);
        eq = new Equation();
    }

    public String getUserInput()
    {
        eq = new Equation();
        String text = eq.getText();
        return text;
    }
    public void add()
    {
            eq.add(a);
    }


    public void answer() {
        if(a!="")
        {
            if(eq.getText() != "") {
                if (eq.isNumber(0))
                    eq.add("*");
                String b =getText() + a;
                context.displayPrimaryScrollLeft(b);
                String s;
                try {
                    s = equationSolver.solveAdvancedOperators(b);
                    a=s;
                } catch (Exception e) {
                    s = "Error";
                }
                //context.displayPrimaryScrollLeft(s);
            }
            else {
                context.displayPrimaryScrollLeft(a);
                context.displaySecondary("Answer");
            }
        }
        else {
            context.displayPrimaryScrollLeft("No Answer");
        }
        eq = new Equation();
    }


    public void num(char number) {
        if (eq.getLast().endsWith("."))
            eq.detachFromLast();
        if (eq.isRawNumber(0) && eq.getLastChar() == '-')
            eq.attachToLast(number);
        else {
            if (eq.isNumber(0))
                eq.add("*");
            eq.add("" + number);
        }
        update();
    }

    public void numOp(char number) {
        if (eq.getLast().endsWith("."))
            eq.detachFromLast();
        if (eq.isRawNumber(0) && eq.getLastChar() == '-')
            eq.attachToLast(number);
        update();
    }

    public void numOpNum(char operator) {
        if (eq.getLast().endsWith("."))
            eq.detachFromLast();
        if (operator != '-' || (eq.isOperator(0) && eq.isStartCharacter(1)))
            while (eq.isOperator(0))
                eq.removeLast();
        if (operator == '-' || !eq.isStartCharacter(0))
            eq.add("" + operator);
        update();
    }

    public void opNum(char operator) {
        if (eq.getLast().endsWith("."))
            eq.detachFromLast();
        if (eq.getLast().equals("-"))
            eq.attachToLast(operator);
        else {
            if (eq.isNumber(0))
                eq.add("*");
            eq.add("" + operator);
        }
        update();
    }

    private void update() {
        context.displayPrimaryScrollRight(getText());
        context.displaySecondary("");
    }
}
