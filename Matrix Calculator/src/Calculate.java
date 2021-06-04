import Jama.Matrix;
import java.awt.Font;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import Jama.CholeskyDecomposition;
import Jama.LUDecomposition;
import Jama.QRDecomposition;
import Jama.SingularValueDecomposition;
public class Calculate {
	/**
     * 字符串转矩阵并进行矩阵加法和错误判断
     * @param String a
     * @param String b
     * @return 返回矩阵运算结果
     */
	public static Matrix CalculateAdd(String a,String b)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		Matrix Mb = TypeChange.TtoD(b);
		int ar = Ma.getRowDimension();
		int ac = Ma.getColumnDimension();
		int br = Mb.getRowDimension();
		int bc = Mb.getColumnDimension();
		if (ar!=br || ac!=bc)return null;
		temp = Ma.plus(Mb);
		return temp;
	}
	/**
     * 字符串转矩阵并进行矩阵减法和错误判断
     * @param String a
     * @param String b
     * @return 返回矩阵运算结果
     */
	public static Matrix CalculateSubtract(String a,String b)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		Matrix Mb = TypeChange.TtoD(b);
		int ar = Ma.getRowDimension();
		int ac = Ma.getColumnDimension();
		int br = Mb.getRowDimension();
		int bc = Mb.getColumnDimension();
		if (ar!=br || ac!=bc)return null;
		temp = Ma.minus(Mb);
		return temp;
	}
	/**
     * 字符串转矩阵并进行矩阵乘法和错误判断
     * @param String a
     * @param String b
     * @return 返回矩阵运算结果
     */
	public static Matrix CalculateMul(String a,String b)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		Matrix Mb = TypeChange.TtoD(b);
		int ar = Ma.getRowDimension();
		int ac = Ma.getColumnDimension();
		int br = Mb.getRowDimension();
		int bc = Mb.getColumnDimension();
		if (ac!=br)return null;
		temp = Ma.times(Mb);
		return temp;
	}
	/**
     * 字符串转矩阵并进行矩阵求逆和错误判断
     * @param String a
     * @return 返回矩阵运算结果
     */
	public static Matrix CalculateInv(String a)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);		
		if (Ma.det()==0)
		{
			return null;
		}
		temp = Ma.inverse();
		return temp;
	}
	/**
     * 字符串转矩阵并进行矩阵点乘法和错误判断
     * @param String a
     * @param String b
     * @return 返回矩阵运算结果
     */
	public static Matrix CalculateArrayMul(String a,String b)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		Matrix Mb = TypeChange.TtoD(b);
		temp = Ma.arrayTimesEquals(Mb);
		return temp;
	}
	/**
     * 字符串转矩阵并进行矩阵除法和错误判断
     * @param String a
     * @param String b
     * @return 返回矩阵运算结果
     */
	public static Matrix CalculateRightDiv(String a,String b)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		Matrix Mb = TypeChange.TtoD(b);
		temp = Ma.arrayRightDivide(Mb);
		return temp;
	}
	/**
     * 字符串转矩阵并求解特征值
     * @param String a
     * @param String b
     * @return 返回特征值在对角线上的矩阵
     */
	public static Matrix CalculateEigD(String a)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		int ar = Ma.getRowDimension();
		int ac = Ma.getColumnDimension();
		if (ar!=ac)
		{
			return null;
		}
		temp = Ma.eig().getD();
		return temp;
	}
	/**
     * 字符串转矩阵并求解特征向量
     * @param String a
     * @return 返回特征向量矩阵
     */
	public static Matrix CalculateEigV(String a)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		int ar = Ma.getRowDimension();
		int ac = Ma.getColumnDimension();
		if (ar!=ac)
		{
			return null;
		}
		temp = Ma.eig().getV();
		return temp;
	}
	/**
     * 字符串转矩阵并求解矩阵条件数
     * @param String a
     * @return 返回条件数
     */
	public static double CalculateCond(String a)
	{
		double temp;
		Matrix Ma = TypeChange.TtoD(a);
		temp = Ma.cond();
		return temp;
	}
	/**
     * 字符串转矩阵并求解矩阵行列式
     * @param String a
     * @return 返回行列式
     */
	public static double CalculateDet(String a)
	{
		double temp;
		Matrix Ma = TypeChange.TtoD(a);
		temp = Ma.det();
		return temp;
	}
	/**
     * 字符串转矩阵并求解矩阵的秩
     * @param String a
     * @return 返回矩阵的秩
     */
	public static double CalculateRank(String a)
	{
		double temp;
		Matrix Ma = TypeChange.TtoD(a);
		temp = Ma.rank();
		return temp;
	}
	/**
     * 字符串转矩阵并求解方程Ax = B
     * @param String a
     * @return 返回矩阵x
     */
	public static Matrix CalculateSolve(String a,String b)
	{
		Matrix temp;
		Matrix Ma = TypeChange.TtoD(a);
		Matrix Mb = TypeChange.TtoD(b);
		int ar = Ma.getRowDimension();
		int ac = Ma.getColumnDimension();
		int br = Mb.getRowDimension();
		int bc = Mb.getColumnDimension();
		if (ar!=ac || ar!=br) {
			return null;
		}
		temp = Ma.solve(Mb);
		return temp;
	}
	/**
     * 字符串转矩阵并求解矩阵1范数
     * @param String a
     * @return 返回1范数
     */
	public static double CalculateNormOne(String a)
	{
		double temp;
		Matrix Ma = TypeChange.TtoD(a);
		temp = Ma.norm1();
		return temp;
	}
	/**
     * 字符串转矩阵并求解矩阵2范数
     * @param String a
     * @return 返回2范数
     */
	public static double CalculateNormTwo(String a)
	{
		double temp;
		Matrix Ma = TypeChange.TtoD(a);
		temp = Ma.norm2();
		return temp;
	}
	/**
     * 字符串转矩阵并求解矩阵F范数
     * @param String a
     * @return 返回F范数
     */
	public static double CalculateNormF(String a)
	{
		double temp;
		Matrix Ma = TypeChange.TtoD(a);
		temp = Ma.normF();
		return temp;
	}
	/**
     * 字符串转矩阵并求解矩阵无穷范数
     * @param String a
     * @return 返回无穷范数
     */
	public static double CalculateNormInf(String a)
	{
		double temp;
		Matrix Ma = TypeChange.TtoD(a);
		temp = Ma.normInf();
		return temp;
	}
	/**
     * 字符串转矩阵并求解Cholesky分解中的L矩阵
     * @param String a
     * @return 返回L矩阵
     */
	public static Matrix CalculateChoDecom(String a)
	{
		Matrix Ma = TypeChange.TtoD(a);
		CholeskyDecomposition temp = new CholeskyDecomposition(Ma);
		if (temp.isSPD())
		{
			return temp.getL();
		}
		else return null;
	}
	/**
     * 字符串转矩阵并求解LU分解中的L矩阵
     * @param String a
     * @return 返回L矩阵
     */
	public static Matrix CalculateLUDecom(String a)
	{
		Matrix Ma = TypeChange.TtoD(a);
		LUDecomposition temp = new LUDecomposition(Ma);
		if (temp.isNonsingular())
		{
			return temp.getL();
		}
		else return null;
	}
}
