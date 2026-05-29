//pacchetto di appartenenza
package Main;

//librerie
import javax.swing.*;
import com.formdev.flatlaf.*;

class Main
{
	public static void main(String[] args)
	{
		try 
		{
	    	UIManager.setLookAndFeel(new FlatDarculaLaf());
	    	// Opzionale: arrotonda i bordi dei bottoni per un look più moderno
	    	UIManager.put("Button.arc", 15);
		}
		catch (Exception ex)
		{
	    	System.err.println("Errore nel caricamento del tema");
		}
		
    	// Qui crei la tua interfaccia
    	calcolatrice.Window w = new calcolatrice.Window();
    	w.setVisible(true);
	}
}
