import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {
    private JTextField display;
    private double currentInput;
    private double memory;
    private String operator;

    public Calculator() {
        setTitle(" Calculator Example ");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        currentInput = 0.0;
        memory = 0.0;
        operator = "";

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);

        add(display, BorderLayout.NORTH);
        add(createButtonsPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    private JPanel createButtonsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 5, 5));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "MC", "MR", "MS", "C"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            panel.add(button);
        }

        return panel;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            switch (command) {
                case "C":
                    currentInput = 0.0;
                    operator = "";
                    break;
                case "=":
                    calculateResult();
                    operator = "";
                    break;
                case "+":
                case "-":
                case "*":
                case "/":
                    operator = command;
                    break;
                case ".":
                    if (!display.getText().contains(".")) {
                        display.setText(display.getText() + ".");
                    }
                    break;
                case "MC":
                    memory = 0.0;
                    break;
                case "MR":
                    currentInput = memory;
                    updateDisplay();
                    break;
                case "MS":
                    memory = currentInput;
                    break;
                default:
                    updateCurrentInput(command);
            }

            updateDisplay();
        }

        private void updateCurrentInput(String digit) {
            if (currentInput == 0.0 || display.getText().equals("0")) {
                currentInput = Double.parseDouble(digit);
            } else {
                currentInput = Double.parseDouble(display.getText() + digit);
            }
        }

        private void performOperation() {
            if (!operator.isEmpty()) {
                calculateResult();
                display.setText("0");
            }
        }

        private void calculateResult() {
            String expression = display.getText();
            try {
                currentInput = evaluateExpression(expression);
            } catch (ArithmeticException | NumberFormatException ex) {
                currentInput = 0.0;
                display.setText("Error");
            }
        }

        private double evaluateExpression(String expression) {
            try {
                return Double.parseDouble(new ScriptEngineManager().getEngineByName("JavaScript")
                        .eval(expression).toString());
            } catch (ScriptException e) {
                throw new RuntimeException(e);
            }
        }

        private void updateDisplay() {
            display.setText(Double.toString(currentInput));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
