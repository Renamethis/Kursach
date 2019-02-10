import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frame extends JFrame{
	JButton but;
	JTextField CHPZ,CHFZ;
	JLabel l1;
	FlowLayout lay;
	Frame(int w, int h) {
		lay = new FlowLayout();
		CHPZ = new JTextField("ЧПЗ");
		CHFZ = new JTextField("ЧФЗ");
		but = new JButton("Calculate");
		l1 = new JLabel("Представление в ЭВМ:");
		CHPZ.setPreferredSize(new Dimension(100,30));
		CHFZ.setPreferredSize(new Dimension(100,30));
		setLayout(lay);
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		add(CHPZ);
		add(but);
		add(CHFZ);
		add(l1);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(w,h);
	}
}
