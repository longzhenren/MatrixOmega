import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class NormCalculate extends JPanel implements ActionListener {

    private static NormCalculate NCalculate = new NormCalculate();
    JPanel jpanel;
    JButton jb1, jb2, jb3, jb4;
    JTextArea jta = null;
    JTextArea jtb = null;
    JTextArea jtresult = null;
    JScrollPane jscrollPane, jscrollPane1, jscrollPane2;
    JTextField jtfield;
    JScrollPane scrollPane1;
    JScrollPane scrollPane2;
    JScrollPane scrollPane3;
    double result;

    /**
     * 此页面的构造函数
     * 私有化构造方法
     */
    private NormCalculate() {
        this.setLayout(new GridLayout(2, 2, 5, 5));

        //jta文本域作为矩阵的输入处
        jta = new JTextArea("请在这里输入要求范数的矩阵~", 20, 30);
        jta.setTabSize(4);
        jta.setFont(new Font("标楷体", Font.BOLD, 16));
        jta.setLineWrap(true);
        jta.setWrapStyleWord(false);
        jta.setBackground(Color.white);
        jtb = new JTextArea(20, 30);

        jtb.setTabSize(4);
        jtb.setFont(new Font("标楷体", Font.BOLD, 16));
        jtb.setLineWrap(true);// 激活自动换行功能  
        jtb.setWrapStyleWord(false);
        jtb.setBackground(Color.white);
        scrollPane1 = new JScrollPane();
        this.add(scrollPane1);
        scrollPane1.setViewportView(jta);
        scrollPane2 = new JScrollPane();
        this.add(scrollPane2);
        scrollPane2.setViewportView(jtb);
        //范数运算
        jb1 = new JButton("矩阵的1范数");
        jb1.addActionListener(this);
        jb2 = new JButton("矩阵的2范数");
        jb2.addActionListener(this);
        jb3 = new JButton("矩阵的F范数");
        jb3.addActionListener(this);
        jb4 = new JButton("矩阵的无穷范数");
        jb4.addActionListener(this);
        //
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(2, 2));
        jpanel.add(jb1);
        jpanel.add(jb2);
        jpanel.add(jb3);
        jpanel.add(jb4);

        this.add(jpanel);

        jtresult = new JTextArea(20, 30);
        jtresult.setTabSize(4);
        jtresult.setFont(new Font("标楷体", Font.BOLD, 16));
        jtresult.setLineWrap(true);// 激活自动换行功能  
        jtresult.setWrapStyleWord(false);
        jtresult.setBackground(Color.white);
        scrollPane3 = new JScrollPane();
        this.add(scrollPane3);
        scrollPane3.setViewportView(jtresult);
    }


    /**
     * 覆盖接口ActionListener的方法actionPerformed，处理按钮的点击事件进行计算
     *
     * @param ActionEvent e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb1) {
            String t = TypeChange.islegal(jta.getText());
            if (t.length() > 0) {
                jtresult.setText("矩阵A存在以下问题:\n" + t);
                return;
            }
            result = CalculateCore.CalculateNormOne(jta.getText());
            jtresult.setText("该矩阵的1范数 = " + TypeChange.DtoS(result));
        } else if (e.getSource() == jb2) {
            String t = TypeChange.islegal(jta.getText());
            if (t.length() > 0) {
                jtresult.setText("矩阵A存在以下问题:\n" + t);
                return;
            }
            result = CalculateCore.CalculateNormTwo(jta.getText());
            jtresult.setText("该矩阵的2范数 = " + TypeChange.DtoS(result));
        } else if (e.getSource() == jb3) {
            String t = TypeChange.islegal(jta.getText());
            if (t.length() > 0) {
                jtresult.setText("矩阵A存在以下问题:\n" + t);
                return;
            }
            result = CalculateCore.CalculateNormF(jta.getText());
            jtresult.setText("该矩阵的F范数 = " + TypeChange.DtoS(result));
        } else if (e.getSource() == jb4) {
            String t = TypeChange.islegal(jta.getText());
            if (t.length() > 0) {
                jtresult.setText("矩阵A存在以下问题:\n" + t);
                return;
            }
            result = CalculateCore.CalculateNormInf(jta.getText());
            jtresult.setText("该矩阵的无穷范数 = " + TypeChange.DtoS(result));
        }

    }

    /**
     * 单例模式
     *
     * @return 返回此页面的实例
     */
    public static NormCalculate getNormCalculate() {
        return NCalculate;
    }
}
