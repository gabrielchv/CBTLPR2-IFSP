// Arquivo: Calculadora.java

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {
    // Componentes visuais
    private final JTextField display;
    private final JButton[] numericButtons = new JButton[10];
    private final JButton[] operatorButtons = new JButton[4];
    private final JButton equalsButton, clearButton, decimalButton;

    // Lógica da calculadora
    private double num1 = 0;
    private double num2 = 0;
    private char operator;
    private boolean isStart = true; // Indica se o número atual está começando

    /**
     * Construtor para inicializar a calculadora.
     * Construir o Form abaixo e possibilitar o cálculo das operações
     * de divisão, multiplicação, subtração e adição. [cite: 6]
     */
    public Calculadora() {
        setTitle("Calcul...");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 350); // Ajuste de tamanho para o layout
        setLayout(null); // Usando layout nulo para posicionar como na imagem [cite: 8]
        setResizable(false);

        // 1. Display (Text Field de Resultado)
        display = new JTextField("0"); // Zera o text field de resultado. [cite: 24]
        display.setBounds(10, 10, 215, 30);
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display);

        // 2. Botões
        // Operadores: /, *, -, +
        operatorButtons[0] = new JButton("/"); // Botão de divisão [cite: 6]
        operatorButtons[1] = new JButton("*"); // Botão de multiplicação [cite: 6]
        operatorButtons[2] = new JButton("-"); // Botão de subtração [cite: 6]
        operatorButtons[3] = new JButton("+"); // Botão de adição [cite: 6]

        // Botões especiais
        equalsButton = new JButton("="); // [cite: 20]
        clearButton = new JButton("C"); // O botão C = Clear e limpa a memória [cite: 24]
        decimalButton = new JButton(".");

        // Botões numéricos: 0-9
        for (int i = 0; i < 10; i++) {
            numericButtons[i] = new JButton(String.valueOf(i));
        }

        // Posicionamento e adição dos botões (aproximação do layout)
        int xOffset = 10;
        int yOffset = 50;
        int btnW = 50;
        int btnH = 50;
        int gap = 5;

        // Linha 1: 7, 8, 9, /
        int[][] positions = {
            {7, 0, 0}, {8, 1, 0}, {9, 2, 0}, {'/', 3, 0},
            {4, 0, 1}, {5, 1, 1}, {6, 2, 1}, {'*', 3, 1},
            {1, 0, 2}, {2, 1, 2}, {3, 2, 2}, {'-', 3, 2},
            {0, 0, 3}, {'.', 1, 3}, {'=', 2, 3}, {'+', 3, 3},
            {'C', 0, 4}
        };

        for (int[] pos : positions) {
            JButton button = null;
            if (pos[0] >= 0 && pos[0] <= 9) { // Números
                button = numericButtons[pos[0]];
            } else if (pos[0] == '/') {
                button = operatorButtons[0];
            } else if (pos[0] == '*') {
                button = operatorButtons[1];
            } else if (pos[0] == '-') {
                button = operatorButtons[2];
            } else if (pos[0] == '+') {
                button = operatorButtons[3];
            } else if (pos[0] == '=') {
                button = equalsButton;
            } else if (pos[0] == 'C') {
                button = clearButton;
            } else if (pos[0] == '.') {
                button = decimalButton;
            }

            if (button != null) {
                button.setBounds(xOffset + pos[1] * (btnW + gap), yOffset + pos[2] * (btnH + gap), btnW, btnH);
                button.addActionListener(this);
                add(button);
            }
        }

        // Ajuste do botão C para ocupar a largura [cite: 22]
        clearButton.setBounds(xOffset, yOffset + 4 * (btnH + gap), 4 * (btnW + gap) - gap, btnH);

        setVisible(true);
    }

    /**
     * Lógica de ação dos botões.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Botões numéricos e decimal
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.equals(".")) {
            if (isStart || display.getText().equals("0")) {
                display.setText(command.equals(".") ? "0." : command);
                isStart = false;
            } else if (!command.equals(".") || !display.getText().contains(".")) {
                display.setText(display.getText() + command);
            }
        }
        // Botão Clear [cite: 24]
        else if (command.equals("C")) {
            num1 = 0;
            num2 = 0;
            operator = ' ';
            display.setText("0"); // zera o text field de resultado. [cite: 24]
            isStart = true;
        }
        // Botões de Operadores (+, -, *, /) [cite: 6]
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            try {
                // Se for a primeira operação, armazena o primeiro número
                if (operator == ' ' || isStart) {
                    num1 = Double.parseDouble(display.getText());
                } else {
                    // Se não for a primeira, calcula o resultado parcial
                    calculate();
                }
                operator = command.charAt(0);
                isStart = true;
            } catch (NumberFormatException nfe) {
                display.setText("Erro de Formato");
                num1 = 0;
                operator = ' ';
            }
        }
        // Botão Igual
        else if (command.equals("=")) {
            calculate();
            operator = ' '; // Limpa o operador após o cálculo final
        }
    }

    /**
     * Realiza a operação matemática e inclui o tratamento de erros (try, catch). [cite: 25]
     */
    private void calculate() {
        if (operator == ' ') return;

        try {
            num2 = Double.parseDouble(display.getText());
            double result = 0;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    // Tratamento de erros para divisão por zero [cite: 25]
                    if (num2 == 0) {
                        throw new ArithmeticException("Divisão por zero");
                    }
                    result = num1 / num2;
                    break;
            }

            display.setText(String.valueOf(result));
            num1 = result;
            isStart = true; // Pronto para a próxima entrada de número
        } catch (ArithmeticException ae) {
            // catch: Trata a exceção de divisão por zero [cite: 25]
            display.setText("ERRO: " + ae.getMessage());
            num1 = 0; // Reseta a memória após o erro
        } catch (NumberFormatException nfe) {
            // catch: Trata outros erros de formato de número [cite: 25]
            display.setText("Erro de Formato");
            num1 = 0;
        } finally {
            // finally (Opcional, mas solicitado): Executa sempre, limpando a memória do num2 [cite: 25]
            num2 = 0;
            // Se quisesse ter certeza que o operador é resetado, mas isso depende da UX: operator = ' ';
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculadora::new);
    }
}