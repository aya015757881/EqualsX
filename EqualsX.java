package aya;
import java.util.Scanner;
public class EqualsX
{
	static int count,val;
	
	public static void main(String[] args)
	{
		Scanner key=new Scanner(System.in);
		int[] sequence=new int[17];
		while(true)
		{
			count=0;
			System.out.print("请输入目标值x=");
			val=key.nextInt();
			for(int i=0;i<9;i++)
				sequence[2*i]=i+1;
			func(sequence,1);
			System.out.println("最终满足条件的为以上"+count+"个等式\n\n");
		}
	}
	
	public static void func(int[] sequence,int pos)
	{
		for(sequence[pos]=10;sequence[pos]<13;sequence[pos]++)
			if(pos<15)
				func(sequence,pos+2);
			else
				equalsX(sequence);
	}
	
	public static void equalsX(int[] sequence)
	{
		int bound=0,result,num=0;
		int[] temp=new int[17];
		for(int i=0,j=0;i<17;i++)
			if(sequence[i]!=12)
				temp[j++]=sequence[i];
		result=firstNum(temp);
		for(bound=nextBound(temp,bound);bound<16;bound=nextBound(temp,bound))
		{
			num=boundNum(temp,bound);
			if(temp[bound]==10)
				result+=num;
			else
				result-=num;
		}
		if(result==val)
			printSolution(temp);
	}
	
	public static int firstNum(int[] sequence)
	{
		int i,sum=0;
		for(i=0;i<17&&sequence[i]>0;i++)
			if(sequence[i]>9)
				break;
		i--;
		for(int s=1;i>=0;i--,s*=10)
			sum+=sequence[i]*s;
		return sum;
	}
	
	public static int nextBound(int[] sequence,int bound)
	{
		bound++;
		while(bound<16&&sequence[bound]<=9)
			bound++;
		return bound;
	}
	
	public static int boundNum(int[] sequence,int bound)
	{
		int sum=0;
		while(bound<16&&sequence[bound+1]<=9&&sequence[bound+1]>0)
			bound++;
		for(int s=1;bound>=0&&sequence[bound]<=9;s*=10,bound--)
			sum+=sequence[bound]*s;
		return sum;
	}
	
	public static void printSolution(int[] sequence)
	{
		count++;
		System.out.print("第"+count+"种情况：");
		for(int i=0;i<17&&sequence[i]>0;i++)
			if(sequence[i]<10)
				System.out.print(sequence[i]);
			else if(sequence[i]==10)
				System.out.print("+");
			else if(sequence[i]==11)
				System.out.print("-");
		System.out.println("="+val);
	}
}

