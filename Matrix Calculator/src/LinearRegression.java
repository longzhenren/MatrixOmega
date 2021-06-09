import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Jama.Matrix;
import org.jfree.chart.ChartFactory;  
import org.jfree.chart.ChartFrame;  
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;  
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYDotRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;  
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;  
class LineRegression
{
	static double maxx;
	static double minx;
	 /**
     * 最小二乘法
     * @param X
     * @param Y
     * @return y = ax + b, r
     */
    public Map<String, Double> lineRegression(double[] X, double[] Y)
    {
    	max (X);
        // x平方差和
        double Sxx = varianceSum(X);
        // y平方差和
        double Syy = varianceSum(Y);
        // xy协方差和
        double Sxy = covarianceSum(X, Y);
        
        double xAvg = arraySum(X) / X.length;
        double yAvg = arraySum(Y) / Y.length;
        
        double a = Sxy / Sxx;
        double b = yAvg - a * xAvg;
        
        // 相关系数
        double r = Sxy / Math.sqrt(Sxx * Syy);
        Map<String, Double> result = new HashMap<String, Double>();
        result.put("a", a);
        result.put("b", b);
        result.put("r", r);        
        return result;
    }
    /**
     * 保存最大值
     * @param X
     * @return
     */
    private void max(double[] X)
    {
       int lenth = X.length;
       for (int i=0;i<lenth;i++)
       {
    	   if (X[i]>maxx)
    	   {
    		   maxx = X[i];
    	   }
    	   if (X[i]<minx)
    	   {
    		   minx = X[i];
    	   }
       }
    }
    /**
     * 计算方差和
     * @param X
     * @return
     */
    private double varianceSum(double[] X)
    {
        double xAvg = arraySum(X) / X.length;
        return arraySqSum(arrayMinus(X, xAvg));
    }
    
    /**
     * 计算协方差和
     * @param X
     * @param Y
     * @return
     */
    private double covarianceSum(double[] X, double[] Y)
    {
        double xAvg = arraySum(X) / X.length;
        double yAvg = arraySum(Y) / Y.length;
        return arrayMulSum(arrayMinus(X, xAvg), arrayMinus(Y, yAvg));
    }
    
    /**
     * 数组减常数
     * @param X
     * @param x
     * @return
     */
    private double[] arrayMinus(double[] X, double x)
    {
        int n = X.length;
        double[] result = new double[n];
        for(int i = 0; i < n; i++)
        {
            result[i] = X[i] - x;
        }
        
        return result;
    }
    
    /**
     * 数组求和
     * @param X
     * @return
     */
    private double arraySum(double[] X)
    {
        double s = 0 ;
        for( double x : X ) 
        {
            s = s + x ;
        }
        return s ;
    }
    
    /**
     * 数组平方求和
     * @param X
     * @return
     */
    private double arraySqSum(double[] X)
    {
        double s = 0 ;
        for( double x : X ) 
        {
            s = s + Math.pow(x, 2) ; ;
        }
        return s ;
    }   
    /**
     * 数组对应元素相乘求和
     * @param X
     * @return
     */
    private double arrayMulSum(double[] X, double[] Y)
    {
        double s = 0 ;
        for( int i = 0 ; i < X.length ; i++ ) 
        {
            s = s + X[i] * Y[i] ;
        }
        return s ;
    }
    /**
     * 获得散点图的XYDataset类型的数组
     * @param Matrix m
     * @param String title
     * @return XYDataset
     */
	public static XYDataset createxydataset(Matrix a,String title) 
	{  
        DefaultXYDataset xydataset = new DefaultXYDataset();  
        double datas[][] = a.getArray();
        xydataset.addSeries(title, datas);  	 
        return xydataset;  	 
	}
	/**
     * 获得回归方程的XYDataset类型的数组
     * @param Map<String,Double> m
     * @param String title
     * @return XYDataset
     */
	public static XYDataset createxydataset2(Map<String,Double> m,String title) 
	{  
        DefaultXYDataset xydataset = new DefaultXYDataset();          
        double datas[][] = new double [2][2]; 
        if (m!=null)
        {
        	datas[0][0] = maxx;
        	datas[1][0] = maxx*m.get("a") + m.get("b");
        	datas[0][1] = minx;
        	datas[1][1] = minx*m.get("a") + m.get("b");        
        }
        		
        xydataset.addSeries(title, datas);  	 
        return xydataset;  	 
	}
	/**
     * 获得XYDataset类型的数组
     * @param Matrix m
     * @param String title
     * @return XYDataset
     */
	public static JFreeChart createChart(XYDataset xydataset1,XYDataset xydataset2, String title, String xAxis, String yAxis) { 
	    JFreeChart scatterChart = ChartFactory.createScatterPlot(title, xAxis, yAxis, xydataset1, PlotOrientation.VERTICAL, true, false, false);  
	    XYPlot xyplot = scatterChart.getXYPlot();
	    xyplot.setDataset(1, xydataset1);
	    xyplot.setDataset(2, xydataset2);
	    return scatterChart; 
	}
}
public class LinearRegression extends JPanel implements ActionListener{
	private JPanel contentPane;
	private static LinearRegression LRegression = new LinearRegression();
    JPanel jpanel;  
    JPanel jpanel2;  
    JButton jb1, jb2, jb3,jb4;  
    JTextArea jta = null;  
    JTextArea jtb = null;
    JTextArea jtresult = null;
    JScrollPane jscrollPane,jscrollPane1,jscrollPane2;  
    JTextField jtfield;
    Matrix result1,result2;
    JInternalFrame internalFrame;
    JScrollPane scrollPane1;
	JScrollPane scrollPane2;
	JScrollPane scrollPane3;
    /**
     * 此页面的构造函数
     * 私有化构造方法
     */
    private LinearRegression() {      	
    	this.setLayout(new GridLayout(2,2,5,5));		
        //jta文本域作为矩阵的输入处
        jta = new JTextArea("请在这里输入一个2行n列的矩阵~",20, 30);  
        jta.setTabSize(4);  
        jta.setFont(new Font("标楷体", Font.BOLD, 16));  
        jta.setLineWrap(true);
        jta.setWrapStyleWord(false);
        jta.setBackground(Color.white);  
        //jtb文本域输出回归方程
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
        
        jb1 = new JButton("计算回归方程");  
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
        jtresult.setWrapStyleWord(false);// 激活断行不断字功能  
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
        	String t = TypeChange.isLinearlegal(jta.getText());
        	if (t.length()>0)
        	{
        		jtresult.setText("矩阵存在以下问题:\n" + t);
        		return;
        	}
        	
        	//导入矩阵散点图数据集，xydataset1为散点图数据集
        	Matrix m = TypeChange.TtoD(jta.getText());
    		XYDataset xydataset1 = LineRegression.createxydataset(m,"Dot");
    		//导入回归方程数据，xydataset2为直线方程的数据集
    		LineRegression tempLR = new LineRegression();
    		double tempd [][] = m.getArray();
    		Map <String,Double> tempm = tempLR.lineRegression(tempd[0], tempd[1]);
    		XYDataset xydataset2 = LineRegression.createxydataset2(tempm,"Regression Line");
    		JFreeChart jfc = LineRegression.createChart(xydataset1,xydataset2,"All and Regression Line","x","y");
    		//设置图的本身样式和标题字体等
    		XYPlot xyplot = jfc.getXYPlot();
    		XYDotRenderer xydotrenderer1 = new XYDotRenderer();
            xydotrenderer1
                    .setBaseToolTipGenerator(new StandardXYToolTipGenerator());
            xyplot.setRenderer(1, xydotrenderer1);
            XYLineAndShapeRenderer xylineandshaperenderer1 = new XYLineAndShapeRenderer();
            xylineandshaperenderer1
                    .setBaseToolTipGenerator(new StandardXYToolTipGenerator());
            xylineandshaperenderer1.setSeriesPaint(0, Color.BLUE);
            //xyplot.getRenderer(0).setSeriesPaint(0, Color.BLUE);//设置点颜色
            xyplot.setRenderer(2, xylineandshaperenderer1);
            xyplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
            ChartFrame chartFramemts = new ChartFrame("散点图和拟合直线", jfc);
    		chartFramemts.pack();
    		chartFramemts.setVisible(true); 
    		switch (ZNBCalculator.n)
    		{
    			case 1:
    				jtb.setText("回归方程为:y = " + String.format("%.1f", tempm.get("a"))+ "x + " + String.format("%.1f", tempm.get("b")) +"\n"
    	    				+ "相关系数 R  = " + String.format("%.1f", tempm.get("r")));
    				break;
    			case 2:
    				jtb.setText("回归方程为:y = " + String.format("%.2f", tempm.get("a"))+ "x + " + String.format("%.2f", tempm.get("b")) +"\n"
    	    				+ "相关系数 R  = " + String.format("%.2f", tempm.get("r")));
    				break;	
    			case 3:
    				jtb.setText("回归方程为:y = " + String.format("%.3f", tempm.get("a"))+ "x + " + String.format("%.3f", tempm.get("b")) +"\n"
    	    				+ "相关系数 R  = " + String.format("%.3f", tempm.get("r")));
    				break;
    			case 4:
    				jtb.setText("回归方程为:y = " + String.format("%.4f", tempm.get("a"))+ "x + " + String.format("%.4f", tempm.get("b")) +"\n"
    	    				+ "相关系数 R  = " + String.format("%.4f", tempm.get("r")));
    				break;
    			case 5:
    				jtb.setText("回归方程为:y = " + String.format("%.5f", tempm.get("a"))+ "x + " + String.format("%.5f", tempm.get("b")) +"\n"
    	    				+ "相关系数 R  = " + String.format("%.5f", tempm.get("r")));
    				break;
    			case 0:
    				jtb.setText("回归方程为:y = " + String.format("%.0f", tempm.get("a"))+ "x + " + String.format("%.0f", tempm.get("b")) +"\n"
    	    				+ "相关系数 R  = " + String.format("%.0f", tempm.get("r")));
    				break;
    		}   		
        }        
    } 
    /**
     * 单例模式
     * @return 返回此页面的实例
     */
    public static LinearRegression getLinearRegression()
    {
    	return LRegression;
    }
}
