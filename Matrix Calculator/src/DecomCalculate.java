import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Jama.LUDecomposition;
import Jama.Matrix;
import Jama.QRDecomposition;
import Jama.SingularValueDecomposition;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DecomCalculate extends JPanel implements ActionListener
{
	private static DecomCalculate DeCalculate = new DecomCalculate();
    JPanel jpanel;  
    JButton jb1, jb2, jb3,jb4;  
    JTextArea jta = null;  
    JTextArea jtb = null;
    JTextArea jtresult = null;
    JScrollPane jscrollPane,jscrollPane1,jscrollPane2;  
    JTextField jtfield;
    Matrix result;
    Matrix result1;
    Matrix result2;
    JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	JScrollPane scrollPane3;
    /**
     * 此页面的构造函数
     * 私有化构造方法
     */
    private DecomCalculate() {    
    	this.setLayout(new GridLayout(2,2,5,5));		
        //jta文本域作为矩阵的输入处
        jta = new JTextArea("请在这里输入要分解的矩阵~",20, 30);  
        jta.setTabSize(4);  
        jta.setFont(new Font("标楷体", Font.BOLD, 16));  
        jta.setLineWrap(true);
        jta.setWrapStyleWord(false);
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
        jtresult.setLineWrap(true);// 激活自动换行功能  
        jtresult.setWrapStyleWord(false);
        jtresult.setBackground(Color.white);
        scrollPane1 = new JScrollPane();
        this.add(scrollPane1);
        scrollPane1.setViewportView(jta);
        scrollPane2 = new JScrollPane();
        this.add(scrollPane2);
        scrollPane2.setViewportView(jtb);                           
        //分解运算
        jb1 = new JButton("Cholesky分解");  
        jb1.addActionListener(this);  
        jb2 = new JButton("LU分解");  
        jb2.addActionListener(this);  
        jb3 = new JButton("QR分解");  
        jb3.addActionListener(this);  
        jb4 = new JButton("奇异值分解");
        jb4.addActionListener(this);       
        //
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
     * @param ActionEvent e
     */
    public void actionPerformed(ActionEvent e) {  
        if (e.getSource() == jb1) 
        {  
        	String t = TypeChange.islegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵A存在以下问题:\n" + t);
        		return;
        	}
        	result = Calculate.CalculateChoDecom(jta.getText());
        	if (result==null)
        	{
        		jtresult.setText("该矩阵不是正定对称矩阵，请检查输入!");
        	}
        	else {
        		jtresult.setText("");
        		jtb.setText("");
        		jtresult.setText("L = \n" + TypeChange.MtoS(result));       		
        	}
        } 
        else if (e.getSource() == jb2) 
        {  
        	String t = TypeChange.islegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵A存在以下问题:\n" + t);
        		return;
        	}
        	result = Calculate.CalculateLUDecom(jta.getText());
        	if (result==null)
        	{
        		jtresult.setText("该矩阵不可LU分解,请重新输入");
        	}
        	else {
        		Matrix Ma = TypeChange.TtoD(jta.getText());
        		LUDecomposition temp = new LUDecomposition(Ma);
        		result = temp.getL();
        		result1 = temp.getU();
        		jtresult.setText("");
        		jtb.setText("");
        		jtb.setText("L = \n" + TypeChange.MtoS(result));
        		jtresult.setText("U = \n" + TypeChange.MtoS(result1));
        	}
        }
        else if (e.getSource() == jb3) 
        {  
        	String t = TypeChange.islegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵A存在以下问题:\n" + t);
        		return;
        	}
        	Matrix Ma = TypeChange.TtoD(jta.getText());
    		QRDecomposition temp = new QRDecomposition(Ma);
    		result = temp.getQ();
    		result1 = temp.getR();
    		jtresult.setText("");
    		jtb.setText("");
    		jtb.setText("Q = \n" + TypeChange.MtoS(result));
    		jtresult.setText("R = \n" + TypeChange.MtoS(result1));
        }  
        else if (e.getSource() == jb4) 
        {  
        	String t = TypeChange.islegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵A存在以下问题:\n" + t);
        		return;
        	}
        	Matrix Ma = TypeChange.TtoD(jta.getText());
        	SingularValueDecomposition temp = new SingularValueDecomposition(Ma);
        	
    		result = temp.getS();
    		result1 = temp.getU();
    		result2 = temp.getV();
    		jtresult.setText("");
    		jtb.setText("");
    		jtb.setText("S = \n" + TypeChange.MtoS(result));
    		jtresult.setText("U = \n" + TypeChange.MtoS(result1)+"\n V = \n" + TypeChange.MtoS(result2));
        }  
        
    } 
    /**
     * 单例模式
     * @return 返回此页面的实例
     */
    public static DecomCalculate getDecomCalculate()
    {
    	return DeCalculate;
    }
}
