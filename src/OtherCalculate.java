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

import Jama.Matrix;

public class OtherCalculate extends JPanel implements ActionListener {
    private static final OtherCalculate OCalculate = new OtherCalculate();
    JPanel jpanel;
    JButton jb1, jb2, jb3, jb4;
    JTextArea jta = null;
    JTextArea jtb = null;
    JTextArea jtresult = null;
    JScrollPane jscrollPane, jscrollPane1, jscrollPane2;
    JTextField jtfield;
    Matrix result;
    JScrollPane scrollPane1;
    JScrollPane scrollPane2;
    JScrollPane scrollPane3;
    double dresult;

    /**
     * 此页面的构造函数
     * 私有化构造方法
     */
    private OtherCalculate() {
        this.setLayout(new GridLayout(2, 2, 5, 5));

        //两个文本域作为两个矩阵的输入处
        jta = new JTextArea("请在这里输入要计算的矩阵~", 20, 30);
        jta.setTabSize(4);
        jta.setFont(new Font("标楷体", Font.BOLD, 16));
        jta.setLineWrap(true);// 激活自动换行功能  
        jta.setWrapStyleWord(false);// 激活断行不断字功能  
        jta.setBackground(Color.white);

        jtb = new JTextArea(20, 30);
        jtb.setTabSize(4);
        jtb.setFont(new Font("标楷体", Font.BOLD, 16));
        jtb.setLineWrap(true);
        jtb.setWrapStyleWord(false);
        jtb.setBackground(Color.white);

        jtresult = new JTextArea(20, 30);
        jtresult.setTabSize(4);
        jtresult.setFont(new Font("标楷体", Font.BOLD, 16));
        jtresult.setLineWrap(true);
        jtresult.setWrapStyleWord(false);
        jtresult.setBackground(Color.white);
        scrollPane1 = new JScrollPane();
        this.add(scrollPane1);
        scrollPane1.setViewportView(jta);
        scrollPane2 = new JScrollPane();
        this.add(scrollPane2);
        scrollPane2.setViewportView(jtb);

        jb1 = new JButton("条件数");
        jb1.addActionListener(this);
        jb2 = new JButton("行列式值");
        jb2.addActionListener(this);
        jb3 = new JButton("矩阵秩");
        jb3.addActionListener(this);
        jb4 = new JButton("矩阵求逆");
        jb4.addActionListener(this);

        //运算符号
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayout(2, 2));
        jpanel.add(jb1);
        jpanel.add(jb2);
        jpanel.add(jb3);
        jpanel.add(jb4);

        this.add(jpanel);
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
            dresult = CalculateCore.CalculateCond(jta.getText());
            jtresult.setText("Conditon = " + TypeChange.DtoS(dresult));
        } else if (e.getSource() == jb2) {
            String t = TypeChange.islegal(jta.getText());
            if (t.length() > 0) {
                jtresult.setText("矩阵A存在以下问题:\n" + t);
                return;
            }
            dresult = CalculateCore.CalculateDet(jta.getText());
            jtresult.setText("Det = " + TypeChange.DtoS(dresult));
        } else if (e.getSource() == jb3) {
            String t = TypeChange.islegal(jta.getText());
            if (t.length() > 0) {
                jtresult.setText("矩阵A存在以下问题:\n" + t);
                return;
            }
            dresult = CalculateCore.CalculateRank(jta.getText());
            jtresult.setText("Rank = " + TypeChange.DtoS(dresult));
        } else if (e.getSource() == jb4) {
            String t = TypeChange.islegal(jta.getText());
            if (t.length() > 0) {
                jtresult.setText("矩阵A存在以下问题:\n" + t);
                return;
            }
            result = CalculateCore.CalculateInv(jta.getText());
            if (result == null) {
                jtresult.setText("该矩阵无法求逆！");
                return;
            }
            jtresult.setText("该矩阵的逆矩阵为\n" + TypeChange.MtoS(result));
        }

    }

    /**
     * 单例模式
     *
     * @return 返回此页面的实例
     */
    public static OtherCalculate getOtherCalculate() {
        return OCalculate;
    }
}
