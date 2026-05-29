//pacchetto di appartenenza
package calcolatrice;

//librerie
import java.util.*;
import javax.swing.*;

class Funzioni
{
	//variabili
	private char opsChar[] = {'+', '-', 'x', '/', '='};
	private double espr = 0, ris = 0;
	private ArrayList<Double> nums = new ArrayList<Double>();
	private ArrayList<Character> ops = new ArrayList<Character>();
	
	protected double calcolo(String txt)
	{
		saveOps(txt);
		saveNums(txt);
		espr = calcoloEspr();
		ris = espr;
		
		return ris;
	}
	
	//presa delle operazioni
	private void saveOps(String txt)
	{
		ops.clear();
		for(int i = 0; i < txt.length(); i++)
		{
			if(txt.charAt(i) == '=' || i == 0)
				continue;
			if(!Character.isDigit(txt.charAt(i)))
				ops.add(txt.charAt(i));
		}
	}
	
	//presa dei numeri
	private void saveNums(String txt)
	{
		double n = 0;
		nums.clear();
		
		for(int i = 0; i < txt.length(); i++)
			if(Character.isDigit(txt.charAt(i)))
			{
				n *= 10;
				n += Double.parseDouble("" + txt.charAt(i));
			}
			else
			{
				if(i == 0)
					continue;
				nums.add(n);
				n = 0;
			}
	}
	
	//calcolo espressione generica
	private double calcoloEspr()
	{
		while(ops.size() > 0)
		{
			//calcolo molt e div
			for(int i = 0; i < ops.size(); i++)
			{
				if( ((char)ops.get(i) == 'x' || (char)ops.get(i) == '/') &&  (char)ops.get(i) == 'x')
				{
					nums.set(i, (double)nums.get(i) * (double)nums.get(i + 1));
					nums.remove(i + 1);
					ops.remove(i);
					break;
				}
				if( ((char)ops.get(i) == 'x' || (char)ops.get(i) == '/') &&  (char)ops.get(i) == '/') 
				{
					nums.set(i, (double)nums.get(i) / (double)nums.get(i + 1));
					nums.remove(i + 1);
					ops.remove(i);
					break;
				}
			}
			//calcolo somm e sottr
			for(int i = 0; i < ops.size(); i++ )
			{
				//calcolo somm e div
				if( ((char)ops.get(i) == '+' || (char)ops.get(i) == '-') &&  (char)ops.get(i) == '+')
				{
					nums.set(i, (double)nums.get(i) + (double)nums.get(i + 1));
					nums.remove(i + 1);
					ops.remove(i);
					break;
				}
				if( ((char)ops.get(i) == '+' || (char)ops.get(i) == '-') &&  (char)ops.get(i) == '-')
				{
					nums.set(i, (double)nums.get(i) - (double)nums.get(i + 1));
					nums.remove(i + 1);
					ops.remove(i);
					break;
				}
			}
		}
	
		if(nums.size() == 0)
			return 0;
		else
			return (double)nums.get(0);
	}
	
	//controllo errori prima di procedere 
	protected boolean err(String txt, JButton source)
	{
		boolean lastChar = false, bName = false;
		//boolean z = false;
		
		for(int i = 0; i < opsChar.length; i++)	
		{
			if(txt.charAt( txt.length() - 1) == opsChar[i])
				lastChar = true;
			if(source.getText().charAt(0) == opsChar[i])
				bName = true;
		}
		
		if( !((lastChar && bName) || (txt.length() == 1 && bName)) )
			return true;
		else
			return false;
	}
}
