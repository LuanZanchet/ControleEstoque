package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.toedter.calendar.JCalendar;

public class Calendario extends JFrame {
	private JCalendar calendario;
	private JButton jbtOK, jbtFechar;
	JTextField jtfData;
	NovoProduto novoproduto;

	Calendario(final JTextField jtfData) {
		setLayout(null);

		calendario = new JCalendar();
		calendario.setBounds(0, 0, 300, 300);
		calendario.setBackground(Color.white);
		getContentPane().add(calendario);

		jbtOK = new JButton("Confirmar");
		jbtOK.setBounds(30, 310, 100, 25);
		getContentPane().add(jbtOK);

		jbtFechar = new JButton("Fechar");
		jbtFechar.setBounds(170, 310, 100, 25);
		getContentPane().add(jbtFechar);

		jbtOK.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String data = df.format(calendario.getDate());
				jtfData.setText(data);
				dispose();
			}
		});

		jbtFechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		setSize(307, 400);
		setVisible(true);
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}
}
