import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	JButton but;
	JTextField CHPZ,CHFZ;
	JLabel l1;
	FlowLayout lay;
	JTable j;
	int bite;
	Frame(int w, int h, int bit) {
		this.bite = bit;
		lay = new FlowLayout();
		CHPZ = new JTextField("ЧФЗ");
		CHFZ = new JTextField("ЧПЗ");
		but = new JButton("Calculate");
		l1 = new JLabel("Представление в ЭВМ:");
		j = new JTable(1,16);
		j.setEnabled(false);
		for(int i = 0; i < 16; i++)
			j.getColumnModel().getColumn(i).setPreferredWidth(20);
		CHPZ.setPreferredSize(new Dimension(100,30));
		CHFZ.setPreferredSize(new Dimension(100,30));
		setLayout(lay);
		but.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if((!CHPZ.getText().equals("ЧФЗ") && !CHPZ.getText().equals("")) && (CHFZ.getText().equals("ЧПЗ") || CHFZ.getText().equals(""))) {
					String number = CHPZ.getText();
					in2CHPZ(number);
					CHFZ.setText(number);
					
				} else if((CHPZ.getText().equals("") || CHPZ.getText().equals("ЧФЗ")) && (CHFZ.getText().equals("")) || CHFZ.getText().equals("ЧПЗ")) {
					JOptionPane.showMessageDialog(Frame.this, "Введите число в формате ЧФЗ или в формате ЧПЗ", "Message",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
			
		});
		add(CHPZ);
		add(but);
		add(CHFZ);
		add(l1);
		add(j);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(w,h);
	}
	void in2CHPZ(String number) {
		String bit = "";
		DefaultTableCellRenderer sign = new DefaultTableCellRenderer();
		sign.setBackground(Color.RED);
		DefaultTableCellRenderer por = new DefaultTableCellRenderer();
		DefaultTableCellRenderer mant = new DefaultTableCellRenderer();
		por.setBackground(Color.green);
		mant.setBackground(Color.blue);
		int pl = 0;
		if(number.charAt(0) == '-') {
			bit+='1';
			int pt = number.indexOf('.');
			String alln = number.substring(1,pt) + number.substring(pt+1, number.length());
			int pory = number.length() - pt;
			bit += Integer.toString(pory,2) + Integer.toString(Integer.parseInt(alln), 2);
			pl = Integer.toString(pory,2).length();
		} else {
			
		}
		if(bit.length() <= bite) {
			System.out.println(bit.length());
			sign.setText(bit.charAt(0) + "");
			j.getColumnModel().getColumn(0).setCellRenderer(sign);
			for(int i = 1; i < pl; i++) {
				por.setText(bit.charAt(i) + "");
				j.getColumnModel().getColumn(i).setCellRenderer(por);
			}
				for(int k = pl; k < bit.length(); k++)  {
					mant.setText(bit.charAt(k) + "");
					j.getColumnModel().getColumn(k).setCellRenderer(mant);
				}
		} else {
			JOptionPane.showMessageDialog(this, "Переполнение разрядной сетки!", "Error!", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
