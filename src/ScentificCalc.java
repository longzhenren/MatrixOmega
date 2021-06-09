import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ScentificCalc extends JFrame implements ActionListener {
    private final String[] COMMAND = {"Back", "C"};
    private final JTextField resultText = new JTextField("0");

    private boolean firstDigit = true;
    private double resultNum = 0.0;
    private String operator = "=";
    private boolean operateValidFlag = true;
    private final String[] KEYS2 = {"X^2", "X^3", "X^Y", "Sqrt", "rt", "X!", "ln", "lg", "%", "sin", "cos", "tan", "PI", "e", "e^X"};
    private final JButton[] keys2 = new JButton[KEYS2.length];
    StringBuffer sb = new StringBuffer();
    String vl = null;
    private final JPanel contentPane;
    private final JPanel contentpane2;

    private final FlowLayout layout;

    public ScentificCalc() {
        super("科学型");
        this.setBackground(Color.LIGHT_GRAY);
        this.setTitle("科学计算器");
        this.setSize(800, 500);
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
        menuItem.addActionListener(new ActionListener() {
                                       public void actionPerformed(ActionEvent e) {
                                           StandardCalc ordinary = new StandardCalc();
                                           ordinary.setVisible(true);
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
        JButton[] commands = new JButton[COMMAND.length];
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i] = new JButton(COMMAND[i]);
            PPanel.add(commands[i]);
            commands[i].setFont(new Font("Courir", Font.PLAIN, 20));
        }
        String[] KEYS = {"*", "/", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "|x|", "0", ".", "=", "1/x"};
        JButton[] keys = new JButton[KEYS.length];
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
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(3, 5));
        contentPane.add("North", top);
        contentPane.add("Center", panel1);


//second
        JPanel ppanel = new JPanel();
        ppanel.setLayout(new GridLayout(5, 3, 3, 3));
        for (int i = 0; i < KEYS2.length; i++) {
            keys2[i] = new JButton(KEYS2[i]);
            ppanel.add(keys2[i]);
            keys2[i].setFont(new Font("Courir", Font.ITALIC, 20));
        }
        contentpane2 = new JPanel();
        contentpane2.setLayout(new BorderLayout(3, 5));
        contentpane2.add("Center", ppanel);

        layout = new FlowLayout();
        JPanel ane = new JPanel();
        ane.setLayout(layout);
        getContentPane().setLayout(new BorderLayout(3, 5));
        getContentPane().add("Center", contentPane);
        getContentPane().add("East", contentpane2);
//event
        for (int i = 0; i < KEYS.length; i++) {
            keys[i].addActionListener(this);
        }
        for (int i = 0; i < COMMAND.length; i++) {
            commands[i].addActionListener(this);
        }
        for (int i = 0; i < KEYS2.length; i++)
            keys2[i].addActionListener(this);
        setVisible(true);
    }

    //make it
    public void actionPerformed(ActionEvent e) {
        String label = e.getActionCommand();
        if (label.equals(COMMAND[0])) {
            handleBack();
        } else if (label.equals(COMMAND[1])) {
            handleC();
        } else if ("0123456789.".indexOf(label) >= 0) {
            handleNumber(label);
        } else if (label.equals("PI"))
            resultText.setText(String.valueOf(3.14159265));
        else if (label.equals("e"))
            resultText.setText(String.valueOf(2.17828));
        else
            handleOperator(label);
    }

    //
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
        } else if (operator.equals("X^2")) {
            resultNum = resultNum * resultNum;
        } else if (operator.equals("X^3")) {
            resultNum = resultNum * resultNum * resultNum;
        } else if (operator.equals("X^Y")) {
            resultNum = Math.pow(resultNum, getNumberFromText());
        } else if (operator.equals("Sqrt")) {
            resultNum = Math.sqrt(resultNum);
        } else if (operator.equals("rt")) {
            resultNum = resultNum * 57.3;
        } else if (operator.equals("X!")) {
            int ee;
            double re = resultNum;
            int c = 1;
            for (ee = 1; ee <= re; ee++) {
                c = c * ee;
                resultNum = c;
            }
        } else if (operator.equals("ln")) {
            resultNum = Math.log(resultNum);
        } else if (operator.equals("lg")) {
            resultNum = Math.log10(resultNum);
        } else if (operator.equals("%")) {
            resultNum %= getNumberFromText();
        } else if (operator.equals("sin")) {
            resultNum = Math.sin(resultNum);
        } else if (operator.equals("cos")) {
            resultNum = Math.cos(resultNum);
        } else if (operator.equals("tan")) {
            resultNum = Math.tan(resultNum);
        } else if (operator.equals("e^X")) {
            resultNum = Math.exp(resultNum);
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
        } catch (NumberFormatException e) {
        }
        return result;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
                                   public void run() {
                                       try {
                                           ScentificCalc application = new ScentificCalc();
                                           application.setVisible(true);
                                       } catch (Exception e) {
                                           e.printStackTrace();
                                       }
                                   }
                               }
        );
    }

}