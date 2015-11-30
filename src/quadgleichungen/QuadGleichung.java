package quadgleichungen;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

/**
 * Dies ist die einzige Klasse des Projektes "Quadratische Gleichung" und generiert einerseits das Design und andererseits die Loesung der Gleichungen.
 * 
 * @author Lukas Schramm
 * @version 1.0
 * 
 */
public class QuadGleichung {

	private JFrame frame1 = new JFrame("Quadratische Gleichung");
	private NumberFormat format1 = NumberFormat.getInstance(); 
	private NumberFormat format2 = NumberFormat.getInstance();
	private NumberFormat format3 = NumberFormat.getInstance();
	private NumberFormatter formatter1 = new NumberFormatter(format1);
    private NumberFormatter formatter2 = new NumberFormatter(format2);
    private NumberFormatter formatter3 = new NumberFormatter(format3);
    private JLabel labelGleichungLinks = new JLabel(" 0 = ");
	private JLabel labelGleichungMitte = new JLabel("²x + ");
	private JLabel labelGleichungRechts = new JLabel("x + ");
	private JFormattedTextField feldA = new JFormattedTextField(formatter1);
	private JFormattedTextField feldB = new JFormattedTextField(formatter2);
	private JFormattedTextField feldC = new JFormattedTextField(formatter3);
	private JButton buttonRechnen = new JButton("Berechne");
	private JLabel labelDiskriminante = new JLabel(" d = ");
	private JLabel labelX1 = new JLabel(" x₁ = ");
	private JLabel labelX2 = new JLabel(" x₂ = ");
	
	public QuadGleichung() {
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(300,200));
		frame1.setMinimumSize(new Dimension(300,200));
		frame1.setMaximumSize(new Dimension(450,300));
		frame1.setResizable(true);
		Container cp = frame1.getContentPane();
		cp.setLayout(new GridLayout(5,1));
		
		JPanel eingabe = new JPanel();
		eingabe.setLayout(new FlowLayout());
		eingabe.add(labelGleichungLinks);
		eingabe.add(feldA);
		eingabe.add(labelGleichungMitte);
		eingabe.add(feldB);
		eingabe.add(labelGleichungRechts);
		eingabe.add(feldC);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0;
        buttonPanel.add(buttonRechnen,c);
        
	    feldA.setHorizontalAlignment(SwingConstants.RIGHT);
	    feldB.setHorizontalAlignment(SwingConstants.RIGHT);
	    feldC.setHorizontalAlignment(SwingConstants.RIGHT);
	    feldA.setValue(new Double(-6464));
	    feldB.setValue(new Double(4000));
	    feldC.setValue(new Double(5000));
	    
	    buttonRechnen.setMargin(new Insets(2, 2, 2, 2));
	    buttonRechnen.addActionListener(new ActionListener() { 
	    	public void actionPerformed(ActionEvent evt) {
	    		berechnen();
	    	}	
	    });
	    
	    format1.setGroupingUsed(false);
	    format2.setGroupingUsed(false);
	    format3.setGroupingUsed(false);
	    formatter1.setAllowsInvalid(false);
	    formatter2.setAllowsInvalid(false);
	    formatter3.setAllowsInvalid(false);
		
		frame1.add(eingabe);
		frame1.add(buttonRechnen);
		frame1.add(labelDiskriminante);
		frame1.add(labelX1);
		frame1.add(labelX2);
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}
	
	/**
	 * Diese Methode liest die Strings ein, loest die Gleichungen und gibt die Ergebnisse aus.<br>
	 * Das Einlesen von Kommazahlen geht etwas komplizierter von statten, weil Java zu bloed dafuer ist.
	 */
	private void berechnen() {
		try {
			String stra[] = feldA.getText().split(",");
			String strb[] = feldB.getText().split(",");
			String strc[] = feldC.getText().split(",");
			double a;
			double b;
			double c;
			if(stra.length==1) {
				a = Double.valueOf(stra[0]);
			} else {
				a = Double.valueOf(stra[0])+Double.valueOf(stra[1])/Math.pow(10,stra[1].length());
			}
			if(strb.length==1) {
				b = Double.valueOf(strb[0]);
			} else {
				b = Double.valueOf(strb[0])+Double.valueOf(strb[1])/Math.pow(10,strb[1].length());
			}
			if(strc.length==1) {
				c = Double.valueOf(strc[0]);
			} else {
				c = Double.valueOf(strc[0])+Double.valueOf(strc[1])/Math.pow(10,strc[1].length());
			}
			double p = b/a;
			double q = c/a;
			double d = (p*p/4)-q;
			double x1;
			double x2;
			labelDiskriminante.setText(" d = "+Math.round(d*1000)/1000.0);
			if(d>0) {
				x1 = -p/2+Math.sqrt(d);
				x2 = -p/2-Math.sqrt(d);
				labelX1.setText(" x₁ = "+Math.round(x1*1000.0)/1000.0);
				labelX2.setText(" x₂ = "+Math.round(x2*1000.0)/1000.0);
			} else if(d==0) {
				x1 = -p/2+Math.sqrt(d);
				labelX1.setText(" x₁ = "+Math.round(x1*1000.0)/1000.0);
				labelX2.setText(" x₂ existiert nicht");
			} else {
				labelX1.setText(" x₁ existiert nicht");
				labelX2.setText(" x₂ existiert nicht");
			}
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Du hast falsche Werte eingetragen."+System.getProperty("line.separator")+"Wenn Du dies nicht korrigierst"+System.getProperty("line.separator")+"bekommst Du kein Ergebnis!", "Falscheingabe", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public static void main(String[] args) {
		new QuadGleichung();
	}
}