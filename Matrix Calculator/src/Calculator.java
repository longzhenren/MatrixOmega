import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;

public class Calculator extends JFrame implements ActionListener
{
	private JPanel contentPane;
	JMenuBar menuBar;
	JMenu mnNewMenu,changenumber;
	JMenu Help;
	JMenuItem helpinformation;
	JMenuItem wholeinstruction;
	JMenuItem mntmNewMenuItem;
	JMenuItem Linearinstruction;
	JMenuItem change0,change1,change2,change3,change4,change5;
	JPanel panel;
	JButton btnNewButton,btnNewButton_1,btnNewButton_2,btnNewButton_3,btnNewButton_4,btnNewButton_5;
	JPanel panel_1;	
	JInternalFrame internalFrame;
	JPanel CalPanel;
	static int n = 2;
	
	/**
     * 启动计算器
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calculator frame = new Calculator();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
     * 计算器主体部分的构造
     */
	public Calculator() {
		//定义主窗口
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		//菜单部分
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("选项");
		menuBar.add(mnNewMenu);
		changenumber = new JMenu("保留小数点后位数");
		menuBar.add(changenumber);
		Help = new JMenu("帮助");
		menuBar.add(Help);
		helpinformation = new JMenuItem("矩阵格式说明");
		Help.add(helpinformation);
		helpinformation.addActionListener(this);
		wholeinstruction = new JMenuItem("使用方法");
		Help.add(wholeinstruction);
		wholeinstruction.addActionListener(this);
		Linearinstruction = new JMenuItem("回归方程格式说明");
		Help.add(Linearinstruction);
		Linearinstruction.addActionListener(this);
		change0 = new JMenuItem("0位");
		change1 = new JMenuItem("1位");
		change2 = new JMenuItem("2位");
		change3 = new JMenuItem("3位");
		change4 = new JMenuItem("4位");
		change5 = new JMenuItem("5位");
		change0.addActionListener(this);
		change1.addActionListener(this);
		change2.addActionListener(this);
		change3.addActionListener(this);
		change4.addActionListener(this);
		change5.addActionListener(this);
		changenumber.add(change0);
		changenumber.add(change1);
		changenumber.add(change2);
		changenumber.add(change3);
		changenumber.add(change4);
		changenumber.add(change5);
		
		mntmNewMenuItem = new JMenuItem("退出");
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(this);
		//整体布局
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(6, 2, 4, 4));
		//计算有关功能按钮
		btnNewButton = new JButton("基本矩阵运算");
		panel.add(btnNewButton);
		
		btnNewButton_1 = new JButton("特征值和特征向量");
		panel.add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("范数");
		panel.add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("矩阵分解");
		panel.add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("相关数学量");
		panel.add(btnNewButton_4);
		
		btnNewButton_5 = new JButton("回归方程计算");
		panel.add(btnNewButton_5);
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.NORTH);
		//计算容器
		internalFrame = new JInternalFrame("计算区域~");
		contentPane.add(internalFrame, BorderLayout.CENTER);
		internalFrame.setVisible(true);
		CalPanel = BaseCalculate.getBaseCalculate();//CalPanel为计算区域的Component
		internalFrame.add(CalPanel);
		//委托按钮事件
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);
		btnNewButton_2.addActionListener(this);
		btnNewButton_3.addActionListener(this);
		btnNewButton_4.addActionListener(this);		
		btnNewButton_5.addActionListener(this);	
	}
	/**
     * 根据用户的点击切换不同的计算功能页面
     * 以及菜单部分的操作：
     * 切换小数点位数和帮助中给出提示信息
     * @param ActionEvent e
     */
	public void actionPerformed(ActionEvent e)   
	{
		if (e.getSource() == btnNewButton)
		{
			internalFrame.remove(CalPanel);
			CalPanel = BaseCalculate.getBaseCalculate();
			internalFrame.add(CalPanel);
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if (e.getSource() == btnNewButton_1)
		{
			internalFrame.remove(CalPanel);
			CalPanel = EigCalculate.getEigCalculate();
			internalFrame.add(CalPanel);
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if (e.getSource() == btnNewButton_2)
		{
			internalFrame.remove(CalPanel);
			CalPanel = NormCalculate.getNormCalculate();
			internalFrame.add(CalPanel);
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if (e.getSource() == btnNewButton_3)
		{
			internalFrame.remove(CalPanel);
			CalPanel = DecomCalculate.getDecomCalculate();
			internalFrame.add(CalPanel);
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if (e.getSource() == btnNewButton_4)
		{
			internalFrame.remove(CalPanel);
			CalPanel = OtherCalculate.getOtherCalculate();
			internalFrame.add(CalPanel);
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if (e.getSource()== btnNewButton_5)
		{
			internalFrame.remove(CalPanel);
			CalPanel = LinearRegression.getLinearRegression();
			internalFrame.add(CalPanel);
			repaint();
			validate();
			invalidate();
			validate();
		}
		else if (e.getSource() == mntmNewMenuItem)
		{
			int result = JOptionPane.showConfirmDialog(internalFrame,"是否确认退出","确认",JOptionPane.YES_NO_CANCEL_OPTION);
			if (result == 0)
			{
				System.exit(0);
			}			
		}
		else if (e.getSource() == change0)
		{
			n = 0;
		}
		else if (e.getSource() == change1)
		{
			n = 1;
		}
		else if (e.getSource() == change2)
		{
			n = 2;
		}
		else if (e.getSource() == change3)
		{
			n = 3;
		}
		else if (e.getSource() == change4)
		{
			n = 4;
		}
		else if (e.getSource() == change5)
		{
			n = 5;
		}
		else if (e.getSource() == helpinformation)
		{
			JOptionPane.showMessageDialog(internalFrame,
					"每一行对应矩阵中的一行\n"
					+ "使用回车键换行\n(注意：只有用回车键换行才可以进入下一行的输入，因数字过长导致文本框的自动换行不会开始下一行的输入)\n"
					+ "请保证输入矩阵的数字用空格隔开\n例如：\n"
					+ "3 2 1\n"
					+ "2 1 5\n"
					+ "6 9 8\n"
					+ "若最后一行有元素缺失,例如：\n"
					+ "3 2 1\n"
					+ "2 1 5\n"
					+ "6\n"
					+ "则会被补齐为：\n"
					+ "3 2 1\n"
					+ "2 1 5\n"
					+ "6 0 0\n"
					,"矩阵格式说明",JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == wholeinstruction)
		{
			JOptionPane.showMessageDialog(internalFrame,
					"界面左侧的六个按钮可以选择不同的功能\n"
					+ "选择功能之后，请根据提示，在指定位置输入矩阵\n"
					+ "左上↖方框为输入框\n"+"右上↗方框为机动框，可以输入，也可以输出结果，请根据提示进行\n"
					+ "右下↘方框为输出结果框\n"
					+ "输入矩阵前请先删除文本框中的提示信息~\n"
					+ "请保证矩阵符合格式要求\n"
					+ "矩阵格式要求请参照”帮助-矩阵格式说明“\n"
					+ "输入矩阵后点击左下方框中的相关功能键进行计算\n"
					+ "运算结果一般在右下方框中展现，有多个结果的,将在机动框中展现\n"
					,"使用方法",JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getSource() == Linearinstruction)
		{
			JOptionPane.showMessageDialog(internalFrame,
					"请输入一个2行n列的矩阵\n"
					+ "第一行为x的取值\n"
					+ "第二行为x对应的y的取值\n例如：\n"
					+ "3 2 1 6\n"
					+ "2 1 5 8\n"
					+ "若最后一行有元素缺失,例如：\n"
					+ "3 2 1 6\n"
					+ "2 1 5\n"
					+ "则会被补齐为：\n"
					+ "3 2 1 6\n"
					+ "2 1 5 0\n"
					+ "请保证此矩阵符合标准矩阵格式，即数之间用空格隔开\n"
					+ "点击计算后，会在机动框内输出回归方程与相关系数，并在弹出的窗口中生成散点图和拟合方程的图像"
					,"回归方程计算说明",JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
}
