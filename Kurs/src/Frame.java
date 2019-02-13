import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class Frame extends JFrame{

	private static final long serialVersionUID = 1L;
	JButton but;
	JTextField CHPZ,CHFZ;
	JLabel l1;
	FlowLayout lay;
	JTable j;
	JCheckBox[] jcb;
	int bite;
	Frame(int w, int h, int bit) {
		this.bite = bit;
		lay = new FlowLayout();
		CHPZ = new JTextField("ЧФЗ");
		CHFZ = new JTextField("ЧПЗ");
		but = new JButton("Calculate");
		l1 = new JLabel("Представление в ЭВМ:");
		j = new JTable(1,bite);
		j.setEnabled(false);
		jcb = new JCheckBox[3];
		for(int i = 0; i < bit; i++)
			j.getColumnModel().getColumn(i).setPreferredWidth(5);
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
		CHPZ.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				CHFZ.setText("");
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
		});
		CHFZ.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				CHPZ.setText("");
			}
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
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
		sign.setForeground(Color.black);
		sign.setBackground(Color.RED);
		DefaultTableCellRenderer por = new DefaultTableCellRenderer();
		DefaultTableCellRenderer mant = new DefaultTableCellRenderer();
		por.setBackground(Color.green);
		mant.setBackground(Color.blue);
		por.setForeground(Color.black);
		mant.setForeground(Color.black);
		int pl = 0;
		int st;
		if(number.charAt(0) == '-') {
			bit+='1';
			st = 1;
		} else {
			bit+='0';
			st = 0;
		}
		int pt = number.indexOf('.');
		String alln = number.substring(st,pt) + number.substring(pt+1, number.length());
		int pory = pt;
		System.out.println(alln);
		bit += Integer.toString(pory,2) + Integer.toString(Integer.parseInt(alln), 2);
		pl = Integer.toString(pory,2).length();
		System.out.println(bit);
		if(bit.length() <= bite) {
			System.out.println(bit.length());
			j.setValueAt(bit.charAt(0), 0, 0);
			j.getColumnModel().getColumn(0).setCellRenderer(sign);
			for(int i = 1; i <= pl; i++) {
				j.setValueAt(bit.charAt(i) + "", 0, i);
				j.getColumnModel().getColumn(i).setCellRenderer(por);
			}
			int gr = bite-bit.length() + pl-1;
			System.out.println(Integer.toString(gr) + " " + Integer.toString(bite-bit.length()));
			for(int i = 0; i < bite-bit.length(); i++) {
				j.setValueAt("0", 0, i+pl+1);
				j.getColumnModel().getColumn(i+pl+1).setCellRenderer(mant);
			}
			for(int i = pl+1; i < bit.length(); i++)  {
				j.setValueAt(bit.charAt(i) + "", 0, i+(bite-bit.length()));
				j.getColumnModel().getColumn(i+(bite-bit.length())).setCellRenderer(mant);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Переполнение разрядной сетки!", "Error!", JOptionPane.INFORMATION_MESSAGE);
		}
		j.repaint();
	}
}
