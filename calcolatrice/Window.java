//pacchetto di appartenenza
package calcolatrice;

//librerie
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;


public class Window
{
	//variabili
	private boolean ris = false;
	//oggetti
	private Funzioni funz = new Funzioni();
	
	//contenitori
	private JFrame f = new JFrame("Calcolatrice");
	private Container cont = f.getContentPane();
	private JPanel p = new JPanel(new GridLayout(2, 1));
	private JPanel pSchermo = new JPanel(new GridBagLayout());		
	private JPanel pTastiera = new JPanel(new GridLayout(1, 2, 5, 0));	
	
	private JPanel pNums = new JPanel(new GridLayout(4, 3));		
	private JPanel pOps = new JPanel(new GridLayout(5, 1));	
	
	private JPanel pMem = new JPanel(new GridLayout(10, 1));
	private JLabel pMemSaves[] = 
	{
			new JLabel(""),
			new JLabel(""),
			new JLabel(""),
			new JLabel(""),
			new JLabel(""),
			new JLabel(""),
			new JLabel(""),
			new JLabel(""),
			new JLabel(""),
			new JLabel("")
	};
	
	private JPanel pCron = new JPanel(new GridLayout());
	
	//componenti
		//numeri 
	private JButton n1 = new JButton("1");
	private JButton n2 = new JButton("2");
	private JButton n3 = new JButton("3");
	private JButton n4 = new JButton("4");
	private JButton n5 = new JButton("5");
	private JButton n6 = new JButton("6");
	private JButton n7 = new JButton("7");
	private JButton n8 = new JButton("8");
	private JButton n9 = new JButton("9");
	private JButton n0 = new JButton("0");
		//operazioni
	private JButton bAdd = new JButton("+");
	private JButton bSott = new JButton("-");
	private JButton bMolt = new JButton("x");
	private JButton bDiv = new JButton("/");
	private JButton bEq = new JButton("=");
	private JButton bReset = new JButton("Reset");
	private JButton bCanc = new JButton("C");
		//altro
	private JLabel schermoTxt = new JLabel(" ");
		//menu
	private JMenuBar mBar = new JMenuBar();
	private JMenu mMod = new JMenu("Semplice");
	private JMenu mMem = new JMenu("Memoria");
	private JMenuItem semplice = new JMenuItem("Semplice");
	private JMenuItem scientifica = new JMenuItem("Scientifica");
	private JMenuItem cronologia = new JMenuItem("Cronologia");
	private JMenuItem memoria = new JMenuItem("Memoria");
		//gridBagLayout
	private GridBagConstraints gbc = new GridBagConstraints();
	
	
	//gestione eventi
	private ActionListener eAddTxt = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			//oggetto uguale al componente usato per l'evento
			JButton source = (JButton)e.getSource();
			
			if(ris)
			{
				schermoTxt.setText(" ");
				ris = false;
			}
			
			if(funz.err(schermoTxt.getText(), source))
				schermoTxt.setText(schermoTxt.getText() + source.getText());
		}
	};
	
	private ActionListener eReset = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			schermoTxt.setText(" ");
		}
	};
	
	private ActionListener eEq = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(ris)
			{}
			else
			{
				//elimina eventuali operatori lasciati alla fine
				if( !Character.isDigit(schermoTxt.getText().charAt(schermoTxt.getText().length() - 1)) )
					schermoTxt.setText( (String)schermoTxt.getText().substring(0, schermoTxt.getText().length() - 1) );
				
				schermoTxt.setText(schermoTxt.getText() + "=");
				schermoTxt.setText(schermoTxt.getText() + " " + funz.calcolo(schermoTxt.getText()));
				ris = true;
				
				//salvataggio in memoria
				try
				{
					Files.writeString(Path.of("./src/memoria/memoria"), schermoTxt.getText().substring(1) + "  \n", StandardOpenOption.APPEND);
					String testo = "./src/memoria/memoria";
					for(int i = 0; i < testo.length(); i++)
					{
						if(testo.length() > i + 1 && testo.charAt(i) == ' ' && testo.charAt(i + 1) == ' ')
						{
							
						}
					}
				} 
				catch (IOException e1)
				{
					System.out.println("Errore nel salvataggio in memoria");
				}
			}
		}
	};
	
	private ActionListener eCanc = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			if(ris)
			{
				schermoTxt.setText(" ");
				ris = false;
			}
			
			if(schermoTxt.getText().length() == 1)
			{}
			else
				schermoTxt.setText((String)schermoTxt.getText().substring(0, schermoTxt.getText().length() - 1));
		}
	};
	
	private ActionListener eSemplice = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			p.remove(1);
			p.add(pTastiera);
			p.revalidate();
			p.repaint();
		}
	};
	
	private ActionListener eMemList = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			p.remove(1);
			p.add(pMem);
			p.revalidate();
			p.repaint();
		}
	};
	
	private ActionListener eCronList = new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			p.remove(1);
			p.add(pCron);
			p.revalidate();
			p.repaint();
		}
	};
	
	//costruttore
	public Window()
	{
	

		//eventi
		n1.addActionListener(eAddTxt);
		n2.addActionListener(eAddTxt);
		n3.addActionListener(eAddTxt);
		n4.addActionListener(eAddTxt);
		n5.addActionListener(eAddTxt);
		n6.addActionListener(eAddTxt);
		n7.addActionListener(eAddTxt);
		n8.addActionListener(eAddTxt);
		n9.addActionListener(eAddTxt);
		n0.addActionListener(eAddTxt);
		
		bAdd.addActionListener(eAddTxt);
		bSott.addActionListener(eAddTxt);
		bMolt.addActionListener(eAddTxt);
		bDiv.addActionListener(eAddTxt);
		bReset.addActionListener(eReset);	
		bEq.addActionListener(eEq);			
		bCanc.addActionListener(eCanc);
		
		semplice.addActionListener(eSemplice);
		
		memoria.addActionListener(eMemList);
		cronologia.addActionListener(eCronList);
		
		//componenti
			//JMenuBar
		mBar.add(mMod);
		mBar.add(mMem);
			//JMenu e JMenuItem 
		mMod.add(semplice);
		mMod.add(scientifica);
		mMem.add(memoria);
		mMem.add(cronologia);
			//label con testo schermo
		schermoTxt.setFont(new Font("Arial", Font.BOLD, 24));
		
		//contenitori
			//pannelli
				//pannello principale
		p.add(pSchermo);
		p.add(pTastiera);
		
				//pannello schermo e layout
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		pSchermo.add(mBar, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		pSchermo.add(schermoTxt, gbc);
				
				//pannello tastiera
		pTastiera.add(pNums);
		pTastiera.add(pOps);
		
				//pannello memoria
		pMem.setBackground(Color.BLACK);
		pMem.add(pMemSaves[0]);
		pMem.add(pMemSaves[1]);
		pMem.add(pMemSaves[2]);
		pMem.add(pMemSaves[3]);
		pMem.add(pMemSaves[4]);
		pMem.add(pMemSaves[5]);
		pMem.add(pMemSaves[6]);
		pMem.add(pMemSaves[7]);
		pMem.add(pMemSaves[8]);
		pMem.add(pMemSaves[9]);
		
				//pannello cronologia
		pCron.setBackground(Color.BLACK);
		
				//tastiera sezione numeri (reset e eq.)
		pNums.add(n1);
		pNums.add(n2);
		pNums.add(n3);
		pNums.add(n4);
		pNums.add(n5);
		pNums.add(n6);
		pNums.add(n7);
		pNums.add(n8);
		pNums.add(n9);
		pNums.add(bReset);
		pNums.add(n0);
		pNums.add(bEq);
		
				//tastiera sezione operazioni
		pOps.add(bAdd);
		pOps.add(bSott);
		pOps.add(bMolt);
		pOps.add(bDiv);
		pOps.add(bCanc);
		
			//container
		cont.add(p);
		
			//frame
		f.setSize(335, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setMinimumSize(new Dimension(500, 600));
		f.setIconImage(new ImageIcon("./src/img/ImmagineIcona_Calcolatrice.png").getImage());
		f.setVisible(true);
	}
	
	//metodi classe
	public void setVisible(boolean v)
	{
		f.setVisible(v);
	}
	
}
