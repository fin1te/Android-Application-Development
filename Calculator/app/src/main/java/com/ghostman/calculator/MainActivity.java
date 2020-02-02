package com.ghostman.calculator;


import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9,btn_0, btn_00, btn_decimal,
            btn_clear, btn_delete, btn_equals, btn_addition, btn_substraction, btn_multiplication, btn_division,
            btn_percentage;
    TextView calculationHistory, calculationResult;
    Boolean clearStatus = true;


    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // locating buttons by their id
        btn_0 = (Button) findViewById(R.id.zeroBtn);
        btn_00 = (Button) findViewById(R.id.doubleZeroBtn);
        btn_1 = (Button) findViewById(R.id.oneBtn);
        btn_2 = (Button) findViewById(R.id.twoBtn);
        btn_3 = (Button) findViewById(R.id.threeBtn);
        btn_4 = (Button) findViewById(R.id.fourBtn);
        btn_5 = (Button) findViewById(R.id.fiveBtn);
        btn_6 = (Button) findViewById(R.id.sixBtn);
        btn_7 = (Button) findViewById(R.id.sevenBtn);
        btn_8 = (Button) findViewById(R.id.eightBtn);
        btn_9 = (Button) findViewById(R.id.nineBtn);
        btn_clear = (Button) findViewById(R.id.clearBtn);
        btn_delete = (Button) findViewById(R.id.deleteBtn);
        btn_decimal = (Button) findViewById(R.id.decimalBtn);
        btn_equals = (Button) findViewById(R.id.equalBtn);
        btn_addition = (Button) findViewById(R.id.additionBtn);
        btn_substraction = (Button) findViewById(R.id.substractBtn);
        btn_multiplication = (Button) findViewById(R.id.multiplyBtn);
        btn_division = (Button) findViewById(R.id.divideBtn);
        btn_percentage = (Button) findViewById(R.id.percentageBtn);
        calculationResult = (TextView) findViewById(R.id.result);
        calculationHistory = (TextView) findViewById(R.id.calculationHistory);

        // adding event listener with the buttons
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("0");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "0");
            }
        });
        btn_00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("00");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "00");
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("1");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "1");
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("2");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "2");
            }
        });
        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("3");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "3");
            }
        });
        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("4");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "4");
            }
        });
        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("5");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "5");
            }
        });
        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("6");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "6");
            }
        });
        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("7");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "7");
            }
        });
        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("8");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "8");
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("9");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + "9");
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationResult.setText("0");
                clearStatus = true;
            }
        });
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("0");
                    clearStatus = true;
                }
                else {
                    int calcStringLength = calculationResult.length();
                    if (calcStringLength > 0)
                        calculationResult.setText(calculationResult.getText().subSequence(0, calcStringLength - 1));
                }
            }
        });
        btn_decimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( clearStatus ) {
                    calculationResult.setText("0.");
                    clearStatus = false;
                }
                else
                    calculationResult.setText(calculationResult.getText() + ".");
            }
        });
        btn_division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationResult.setText(calculationResult.getText() + "/");
                clearStatus = false;
            }
        });
        btn_multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationResult.setText(calculationResult.getText() + "*");
                clearStatus = false;
            }
        });
        btn_substraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationResult.setText(calculationResult.getText() + "-");
                clearStatus = false;
            }
        });
        btn_addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculationResult.setText(calculationResult.getText() + "+");
                clearStatus = false;
            }
        });
        btn_equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String evalString = calculationResult.getText().toString();
                calculationHistory.setText(evalString);
                double result = eval(evalString);
                calculationResult.setText(String.valueOf(result));
                clearStatus = true;
            }
        });
    }
}
