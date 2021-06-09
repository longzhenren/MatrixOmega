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
public class EigCalculate extends JPanel implements ActionListener
{	
	private static EigCalculate ECalculate = new EigCalculate();
    JPanel jpanel;  
    JButton jb1, jb2, jb3,jb4;  
    JTextArea jta = null;  
    JTextArea jtb = null;
    JTextArea jtresult = null;
    JScrollPane jscrollPane,jscrollPane1,jscrollPane2;  
    JTextField jtfield;
    Matrix result1,result2;
    JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	JScrollPane scrollPane3;
    /**
     * 此页面的构造函数
     * 私有化构造方法
     */
    private EigCalculate() {    
    	this.setLayout(new GridLayout(2,2,5,5));
		
        //jta文本域作为矩阵的输入处
        jta = new JTextArea("请在这里输入要求特征值的矩阵~",20, 30);  
        jta.setTabSize(4);  
        jta.setFont(new Font("标楷体", Font.BOLD, 16));  
        jta.setLineWrap(true);
        jta.setWrapStyleWord(false);
        jta.setBackground(Color.white);  
        //jtb文本域存放特征值
        jtb = new JTextArea(20, 30);  
        jtb.setTabSize(4);  
        jtb.setFont(new Font("标楷体", Font.BOLD, 16));  
        jtb.setLineWrap(true);
        jtb.setWrapStyleWord(false);
        jtb.setBackground(Color.white);  
        
        scrollPane1 = new JScrollPane();
        this.add(scrollPane1);
        scrollPane1.setViewportView(jta);
        scrollPane2 = new JScrollPane();
        this.add(scrollPane2);
        scrollPane2.setViewportView(jtb);
        
        jb1 = new JButton("计算特征值和特征向量");  
        jb1.addActionListener(this);  
               
        //添加按钮
        jpanel = new JPanel();  
        jpanel.setLayout(new GridLayout(1, 1));  
        jpanel.add(jb1);  
        
        this.add(jpanel);
        //jtresult文本域输出特征向量
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
        	
        	result1 = CalculateCore.CalculateEigD(jta.getText());
        	result2 = CalculateCore.CalculateEigV(jta.getText());
        } 
        if (result1 == null)
        {
        	jtresult.setText("请保证矩阵是方阵！");
        	return;
        }
        jtb.setText(TypeChange.getdiagonal(result1));
        jtresult.setText(TypeChange.gettz(result2) + "\n每一列为一个特征向量~" + 
        "\n特征向量分别对应特征值，如向量1对应特征值1\n");
    } 
    /**
     * 单例模式
     * @return 返回此页面的实例
     */
    public static EigCalculate getEigCalculate()
    {
    	return ECalculate;
    }
}
