package grafika1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class Panel_Przesuwanie extends JPanel {
	 JTextField textfield;
	 JButton ok;
	 JLabel avalue;
	 int a,b;
	 JTextField textfield2;

	public Panel_Przesuwanie() {
	
		setLayout(null);
		avalue = new JLabel("value (x)");
		avalue.setBounds(10, 14, 78, 14);
		add(avalue);
		textfield = new JTextField();
		textfield.setBounds(98, 11, 168, 20);
		add(textfield);
		textfield.setColumns(10);
		ok = new JButton("OK");
		ok.setBounds(177, 73, 89, 23);
		add(ok);	
		
		textfield2 = new JTextField();
		textfield2.setBounds(98, 42, 168, 20);
		add(textfield2);
		textfield2.setColumns(10);
		
		JLabel bvalue = new JLabel("value(y)");
		bvalue.setBounds(10, 45, 78, 14);
		add(bvalue);
		
	}
}