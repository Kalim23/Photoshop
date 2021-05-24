package grafika1;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Color;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Panel_multiprogowanie extends JPanel {
	public int s1, s2;
	public JButton ok;
	public Panel_multiprogowanie() {
		setLayout(null);
		
		JLabel supLabel = new JLabel("78");
		supLabel.setBounds(10, 11, 48, 14);
		add(supLabel);
		
		JLabel sdoLabel = new JLabel("200");
		sdoLabel.setBounds(10, 46, 48, 14);
		add(sdoLabel);
		
		JSlider sliderUp = new JSlider();
		sliderUp.setBounds(79, 10, 200, 15);
		add(sliderUp);
		
		JSlider sliderDown = new JSlider();
		sliderDown.setBounds(79, 46, 200, 15);
		add(sliderDown);
		
		ok = new JButton("OK");
		ok.setBounds(190, 84, 89, 23);
		add(ok);
		
		sliderUp.setMaximum(255);
		sliderUp.setMinimum(0);
		sliderDown.setMaximum(255);
		sliderDown.setMinimum(0);
		
		sliderUp.setValue(78);
		sliderDown.setValue(200);
		
		sliderUp.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent ce) {
	        	s1 = ((JSlider) ce.getSource()).getValue();
	        	supLabel.setText(String.valueOf(s1));
	        }
		});
		
		sliderDown.addChangeListener(new ChangeListener() {
	        @Override
	        public void stateChanged(ChangeEvent ce) {
	        	s2 = ((JSlider) ce.getSource()).getValue();
	        	sdoLabel.setText(String.valueOf(s2));
	        }
		});
		
	}
}