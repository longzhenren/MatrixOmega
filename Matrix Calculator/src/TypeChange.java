import Jama.Matrix;

public class TypeChange {
	/**
     * 线性回归中，判断用户输入的矩阵是否合法
     * @param Stirng a 用户输入的矩阵
     * @return 错误信息
     */
	public static String isLinearlegal(String a)
	{
		StringBuilder reString = new StringBuilder();
		int error = 0;
		int lenth = a.length();
		int column = 1,row = 1;
		int count = 1;
		int allcount = 0;
		for (int i = 0;i <lenth;i++)
		{
			if (row==3)
			{
				reString.append("矩阵不能存在第三行数据，请按照正确格式输入\n");
				return reString.toString();
			}
			if (a.charAt(i)=='\n')
			{
				if (count==1)
				{
					reString.append("请保证点的个数大于1\n");
					return reString.toString();
				}
				if (row==1)
				{
					allcount = count;
				}
				else {
					if (count!=allcount)
					{
						reString.append("请保证矩阵每一行的元素个数相同\n");
					}
				}
				row++;
				column = 1;
				count = 1;
				continue;
			}
			else if (a.charAt(i) == '.')
			{
				if (a.charAt(i-1)>='0'&&a.charAt(i-1)<='9'&&a.charAt(i+1)>='0'&&a.charAt(i+1)<='9')
				{
					
				}
				else {
					reString.append("第" + row + "行" + ",第" + column + "列" + "(第" + count +  "个数)有字符不合法\n");
				}
			}
			else if (a.charAt(i) == ' ')
			{
				if (column==1)
				{
					reString.append("第" + row +"行开头存在空格,请删去\n");
					return reString.toString();
				}
				count ++;
				if (i>0&&a.charAt(i-1) == ' ')
				{
					reString.append(("第" + row + "行" + ",第" + column + "列" + "(第" + count +  "个数)处存在多个空格字符\n"));
				}
			}
			else if (a.charAt(i) == '-')
			{
				
			}
			else if (!(a.charAt(i)>= '0'&&a.charAt(i)<= '9'))
			{
				reString.append("第" + row + "行" + ",第" + column + "列" + "(第" + count +  "个数)有字符不合法\n");
			}			
			column ++;
		}
		return reString.toString();
	}
	/**
     * 一般的矩阵计算中，判断用户输入的矩阵是否合法
     * @param Stirng a 用户输入的矩阵
     * @return 错误信息
     */
	public static String islegal(String a)
	{
		StringBuilder reString = new StringBuilder();
		int error = 0;
		int lenth = a.length();
		int column = 1,row = 1;
		int count = 1;
		int allcount = 0;
		for (int i = 0;i <lenth;i++)
		{
			if (a.charAt(i)=='\n')
			{
				
				if (row==1)
				{
					allcount = count;
				}
				else {
					if (count!=allcount)
					{
						reString.append("请保证矩阵每一行的元素个数相同\n");
					}
				}
				row++;
				column = 1;
				count = 1;
				continue;
			}
			else if (a.charAt(i) == '.')
			{
				if (a.charAt(i-1)>='0'&&a.charAt(i-1)<='9'&&a.charAt(i+1)>='0'&&a.charAt(i+1)<='9')
				{
					
				}
				else {
					reString.append("第" + row + "行" + ",第" + column + "列" + "(第" + count +  "个数)有字符不合法\n");
				}
			}
			else if (a.charAt(i) == ' ')
			{
				if (column==1)
				{
					reString.append("第" + row +"行开头存在空格,请删去\n");
					return reString.toString();
				}
				count ++;
				if (i>0&&a.charAt(i-1) == ' ')
				{
					reString.append(("第" + row + "行" + ",第" + column + "列" + "(第" + count +  "个数)处存在多个空格字符\n"));
				}
			}
			else if (a.charAt(i) == '-')
			{
				
			}
			else if (!(a.charAt(i)>= '0'&&a.charAt(i)<= '9'))
			{
				reString.append("第" + row + "行" + ",第" + column + "列" + "(第" + count +  "个数)有字符不合法\n");
			}			
			column ++;
		}
		return reString.toString();
	}
	/**
     * String转矩阵，将用户输入的文本信息处理为Matrix类的实例
     * @param Stirng a 用户输入的矩阵文本
     * @return 矩阵
     */
	public static Matrix TtoD (String a)
	{
		int row=0,column=0;
		int maxrow,maxcolumn;
		
		String t[] = a.split("\n");
		maxrow = t.length;
		
		String temp[];
		temp = t[0].split(" ");
		maxcolumn = temp.length;
		
		double matrix[][] = new double[maxrow][maxcolumn]; 
		for (String b:t)
		{
			temp = b.split(" ");			
			for (String number:temp)
			{
				matrix[row][column++] = Double.parseDouble(number);
			}
			column = 0;
			row++;
		}
		Matrix A = new Matrix(matrix);
//		A.print(1, 4);
		return A;				
	}
	/**
     * 矩阵转文本信息，输出矩阵时使用
     * @param m 需要转文本的矩阵
     * @return String格式的矩阵
     */
	public static String MtoS(Matrix m)
	{
		int lenth = Calculator.n;
		int row = m.getRowDimension();
		int column = m.getColumnDimension();
		double a[][] = m.getArray();
		StringBuilder Matrix = new StringBuilder();
		for (int i=0;i<row;i++)
		{
			for (int j=0;j<column;j++)
			{
				switch(lenth)
				{
					case 1:
						Matrix.append(String.format("%.1f", a[i][j])+" ");
						break;
					case 2:
						Matrix.append(String.format("%.2f", a[i][j])+" ");
						break;
					case 3:
						Matrix.append(String.format("%.3f", a[i][j])+" ");
						break;
					case 4:
						Matrix.append(String.format("%.4f", a[i][j])+" ");
						break;
					case 5:
						Matrix.append(String.format("%.5f", a[i][j])+" ");
						break;
					case 0:
						Matrix.append(String.format("%.0f", a[i][j])+" ");
						break;					
				}				
			}
			Matrix.append("\n");
		}
		return Matrix.toString();
	}
	/**
     * 将Double保留一定的小数位数并转化为文本
     * @param d
     * @return 
     */
	public static String DtoS(double d)
	{
		int lenth = Calculator.n;
		switch(lenth)
		{
			case 0:
				return String.format("%.0f", d);
			case 1:
				return String.format("%.1f", d);
			case 2:
				return String.format("%.2f", d);
			case 3:
				return String.format("%.3f", d);
			case 4:
				return String.format("%.4f", d);
			case 5:
				return String.format("%.5f", d);
		}
		return null;
	}
	/**
     * Matrix类中处理得到的特征值矩阵中，特征值在对角线上
     * 从矩阵中取出特征值并添加文字信息转为String
     * @param m特征值矩阵
     * @return 
     */
	public static String getdiagonal(Matrix m)
	{
		StringBuilder temp = new StringBuilder();
		int lenth = Calculator.n;
		int count = m.getColumnDimension();
		for (int i=0;i<count;i++)
		{
			switch (lenth)
			{
				case 1:
					temp.append("特征值"+(i+1)+":"+String.format("%.1f", m.get(i, i))+"\n");
					break;
				case 2:
					temp.append("特征值"+(i+1)+":"+String.format("%.2f", m.get(i, i))+"\n");
					break;
				case 3:
					temp.append("特征值"+(i+1)+":"+String.format("%.3f", m.get(i, i))+"\n");
					break;
				case 4:
					temp.append("特征值"+(i+1)+":"+String.format("%.4f", m.get(i, i))+"\n");
					break;
				case 5:
					temp.append("特征值"+(i+1)+":"+String.format("%.4f", m.get(i, i))+"\n");
					break;
				case 0:
					temp.append("特征值"+(i+1)+":"+String.format("%.5f", m.get(i, i))+"\n");
					break;
			}
		}
		return temp.toString();
	}
	/**
     * 将特征向量矩阵 添加提示信息后作为String返回
     * @param m特征向量矩阵
     * @return 
     */
	public static String gettz(Matrix m)
	{
		StringBuilder temp = new StringBuilder();
		int i = 1;
		int len = m.getColumnDimension();
		for (i = 1;i<=len;i++)
		{
			temp.append("向量" + i + " ");
		}
		temp.append("\n");
		temp.append(MtoS(m));
		return temp.toString();
	}	
}
