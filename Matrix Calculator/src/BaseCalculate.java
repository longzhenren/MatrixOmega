import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
import Jama.Matrix;

//实现接口ActionListener  
public class BaseCalculate extends JPanel implements ActionListener 
{  	
    private static BaseCalculate BCalculate = new BaseCalculate();
    JPanel jpanel;  
    JButton jb1, jb2, jb3,jb4;  
    JTextArea jta = null;  
    JTextArea jtb = null;
    JTextArea jtresult = null;
    JScrollPane jscrollPane,jscrollPane1,jscrollPane2;  
    JTextField jtfield;
    Matrix result;
    JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	JScrollPane scrollPane3;
    /**
     * 此页面的构造函数 
     * 私有化构造方法
     */
    private BaseCalculate() {    
    	this.setLayout(new GridLayout(2,2,5,5));
		
        //两个文本域作为两个矩阵的输入处
        jta = new JTextArea("请在这里输入矩阵A~",20, 30);  
        jta.setTabSize(4);  
        jta.setFont(new Font("标楷体", Font.BOLD, 16));  
        jta.setLineWrap(true);// 激活自动换行功能  
        jta.setWrapStyleWord(false);
        jta.setBackground(Color.white);  
  
        jtb = new JTextArea("请在这里输入矩阵B~",20, 30);  
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
        
        jb1 = new JButton("矩阵相加(A + B)");  
        jb1.addActionListener(this);  
        jb2 = new JButton("矩阵相减(A - B)");  
        jb2.addActionListener(this);  
        jb3 = new JButton("矩阵相乘(A * B)");  
        jb3.addActionListener(this);  
        jb4 = new JButton("线性方程求解(求解 Ax = B)");
        jb4.addActionListener(this);
        
        //运算符号
        jpanel = new JPanel();  
        jpanel.setLayout(new GridLayout(2, 2));  
        jpanel.add(jb1);  
        jpanel.add(jb2);  
        jpanel.add(jb3);  
        jpanel.add(jb4);
        
        this.add(jpanel);
        //结果输入文本框
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
        	t = TypeChange.islegal(jtb.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵B存在以下问题:\n" + t);
        		return ;
        	}
        	result = Calculate.CalculateAdd(jta.getText(),jtb.getText());  
        	if(result == null)
        	{
        		jtresult.setText("请保证两个矩阵的行列数相等！");
        		return;
        	}
        	jtresult.setText("A + B = \n" + TypeChange.MtoS(result));
        	return ;
        } 
        else if (e.getSource() == jb2) 
        {  
        	String t = TypeChange.islegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵A存在以下问题:\n" + t);
        		return;
        	}
        	t = TypeChange.islegal(jtb.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵B存在以下问题:\n" + t);
        		return ;
        	}
        	result = Calculate.CalculateSubtract(jta.getText(),jtb.getText());
        	
        	if(result == null)
        	{
        		jtresult.setText("请保证矩阵的行列数相等！");
        		return;
        	}
        	jtresult.setText("A - B = \n" + TypeChange.MtoS(result));
        	return ;
        } 
        else if (e.getSource() == jb3) 
        {  
        	String t = TypeChange.islegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵A存在以下问题:\n" + t);
        		return;
        	}
        	t = TypeChange.islegal(jtb.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵B存在以下问题:\n" + t);
        		return ;
        	}
        	result = Calculate.CalculateMul(jta.getText(),jtb.getText());
        	if(result == null)
        	{
        		jtresult.setText("请保证两个矩阵可以相乘！");
        		return;
        	}
        	jtresult.setText("A * B = \n" + TypeChange.MtoS(result));
        	return ;
        }  
        else if (e.getSource() == jb4)
        {
        	String t = TypeChange.islegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵A存在以下问题:\n" + t);
        		return;
        	}
        	t = TypeChange.islegal(jtb.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵B存在以下问题:\n" + t);
        		return ;
        	}
        	result = Calculate.CalculateSolve(jta.getText(),jtb.getText());
        	if (result == null)
        	{
        		jtresult.setText("请保证矩阵A为方阵\n或保证方阵A的阶数等于矩阵B的行数");
        		return;
        	}
        	jtresult.setText("x = \n" + TypeChange.MtoS(result));
        	return ;
        }   
    } 
    
    /**
     * 单例模式
     * @return 返回此页面的实例
     */
    public static BaseCalculate getBaseCalculate()
    {
    	return BCalculate;
    }
}  