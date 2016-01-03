package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class EsqueceuSenha extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel jlbUsuario, jlbPalavra;
	private JTextField jtfUsuario, jtfPalavra;
	private JButton jbtRequisitar;
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");

	EsqueceuSenha() {
		setLayout(null);

		jlbUsuario = new JLabel("Usuário:");
		jlbUsuario.setBounds(15, 150, 100, 25);
		getContentPane().add(jlbUsuario);

		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(115, 150, 150, 25);
		getContentPane().add(jtfUsuario);

		jlbPalavra = new JLabel("Palavra Secreta:");
		jlbPalavra.setBounds(15, 180, 150, 25);
		getContentPane().add(jlbPalavra);

		jtfPalavra = new JTextField();
		jtfPalavra.setBounds(115, 180, 150, 25);
		getContentPane().add(jtfPalavra);

		jbtRequisitar = new JButton("Requisitar Senha");
		jbtRequisitar.setBounds(100, 210, 150, 25);
		jbtRequisitar.addActionListener(this);
		getContentPane().add(jbtRequisitar);

		setTitle("Recuperação de Senha");
		setSize(320, 310);
		setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {
		int aux = 0, i = 0;
		String posicao = null;
		ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);

		for (Usuario usuario : usuarios) {

			if (usuario.getNome().equals(jtfUsuario.getText())
					&& usuario.getPalavraSecreta().equals(jtfPalavra.getText())) {
				aux = 1;
				posicao = usuario.getNome();
			}
			i++;

		}
		if (aux == 0) {
			JOptionPane.showMessageDialog(this, "A combinação Usuario/Palavra Secreta está incorreta");
		}
		if (aux == 1) {
			bancoDeDados.close();
			DigiteSenha senha = new DigiteSenha();
			senha.setValor(posicao);
			dispose();
		}

	}
}