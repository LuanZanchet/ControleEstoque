package TrabalhoFinal;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Suporte extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jlbImagem;

	Suporte() {
		setTitle("Suporte");
		setLayout(null);
		jlbImagem = new JLabel(new ImageIcon("suporte.jpg"));
		jlbImagem.setBounds(0, 0, 794, 742);
		getContentPane().add(jlbImagem);

		setSize(794, 742);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
