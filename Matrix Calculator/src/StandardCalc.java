import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class StandardCalc extends JFrame implements ActionListener {
    private final String[] KEYS = {"*", "/", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "|x|", "0", ".", "=", "1/x"};
    private final String[] COMMAND = {"Back", "C"};
    private JButton[] keys = new JButton[KEYS.length];
    private JButton[] commands = new JButton[COMMAND.length];
    private JTextField resultText = new JTextField("0");
    private boolean firstDigit = true;
    private double resultNum = 0.0;
    private String operator = "=";
    private boolean operateValidFlag = true;
    StringBuffer sb = new StringBuffer();
    String vl = null;

    public StandardCalc() {
        setTitle("标准型");
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("标准计算器");
        this.setLocation(500, 300);
        this.setSize(500, 500);
        this.setResizable(false);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("功能");
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("标准型");
        menu.add(menuItem);
        JMenuItem menuItem_1 = new JMenuItem("科学型");
        menu.add(menuItem_1);
        JMenuItem menuItem_2 = new JMenuItem("矩阵");
        menu.add(menuItem_2);
        menuItem_1.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent arg0) {
                                             ScentificCalc science = new ScentificCalc();
                                             science.setVisible(true);
                                         }
                                     }
        );
        menuItem_2.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent arg0) {
                                             ZNBCalculator calc = new ZNBCalculator();
                                             calc.setVisible(true);
                                         }
                                     }
        );
        resultText.setHorizontalAlignment(JTextField.RIGHT);
        resultText.setBackground(Color.white);
        resultText.setFont(new Font("TimesRoman", Font.BOLD, 46));
        JPanel PPanel = new JPanel();
        PPanel.setLayout(new GridLayout(5, 4, 3, 3));
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i] = new JButton(COMMAND[i]);
            PPanel.add(commands[i]);
            commands[i].setFont(new Font("Courir", Font.PLAIN, 20));
        }
        for (int i = 0; i < KEYS.length; i++) {
            keys[i] = new JButton(KEYS[i]);
            PPanel.add(keys[i]);
            keys[i].setFont(new Font("Courir", Font.ITALIC, 20));
        }
        JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(3, 3));
        panel1.add("Center", PPanel);
        JPanel top = new JPanel();
        top.setLayout(new BorderLayout());
        top.add("Center", resultText);

        getContentPane().setLayout(new BorderLayout(3, 5));
        getContentPane().add("North", top);
        getContentPane().add("Center", panel1);
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (label.equals(COMMAND[0])) {
            handleBack();
        } else if (label.equals(COMMAND[1])) {
            handleC();
        } else if ("0123456789.".contains(label)) {
            handleNumber(label);
        } else
            handleOperator(label);
    }

    private void handleBack() {
        String text = resultText.getText();
        int i = text.length();
        if (i > 0) {
            text = text.substring(0, i - 1);
            if (text.length() == 0) {
                resultText.setText("0");
                firstDigit = true;
                operator = "=";
            } else
                resultText.setText(text);
        }
    }

    private void handleNumber(String key) {
        if (firstDigit) {
            resultText.setText(key);
        } else if ((key.equals(".")) && (resultText.getText().indexOf(".") < 0)) {
            resultText.setText(resultText.getText() + ".");
        } else if (!key.equals(".")) {
            resultText.setText(resultText.getText() + key);
        }
        firstDigit = false;
    }

    private void handleC() {
        resultText.setText("0");
        firstDigit = true;
        operator = "=";
    }

    private void handleOperator(String key) {
        if (operator.equals("/")) {
            if (getNumberFromText() == 0.0) {
                operateValidFlag = false;
                resultText.setText("除数不能为零");
            } else
                resultNum /= getNumberFromText();
        } else if (operator.equals("1/x")) {
            if (resultNum == 0.0) {
                operateValidFlag = false;
                resultText.setText("零没有倒数");
            } else
                resultNum = 1 / resultNum;
        } else if (operator.equals("+")) {
            resultNum += getNumberFromText();
        } else if (operator.equals("-")) {
            resultNum -= getNumberFromText();
        } else if (operator.equals("*")) {
            resultNum *= getNumberFromText();
        } else if (operator.equals("|x|")) {
            resultNum = Math.abs(resultNum);
        } else if (operator.equals("=")) {
            resultNum = getNumberFromText();
        }
        if (operateValidFlag) {
            long t1;
            double t2;
            t1 = (long) resultNum;
            t2 = resultNum - t1;
            if (t2 == 0) {
                resultText.setText(String.valueOf(t1));
            } else {
                resultText.setText(String.valueOf(resultNum));
            }
        }
        operator = key;
        firstDigit = true;
        operateValidFlag = true;
    }

    private double getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(resultText.getText()).doubleValue();
        } catch (NumberFormatException ignored) {
        }
        return result;
    }

    public static void main(String args[]) {
        StandardCalc application = new StandardCalc();
        application.setVisible(true);
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
