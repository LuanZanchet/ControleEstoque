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

public class DigiteSenha extends JFrame implements ActionListener {
	private JLabel jlbSenha, jlbConfirmar, jlbUsuario;
	private JTextField jtfSenha, jtfConfirmar, jtfUsuario;
	private JButton jbtOk;
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");

	public void setValor(String posicao2) {
		jtfUsuario.setText(posicao2);
		jtfUsuario.setEditable(false);
	}

	public DigiteSenha() {
		setLayout(null);

		jlbUsuario = new JLabel("Usuario:");
		jlbUsuario.setBounds(15, 120, 100, 25);
		getContentPane().add(jlbUsuario);

		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(125, 120, 150, 25);
		getContentPane().add(jtfUsuario);

		jlbSenha = new JLabel("Senha:");
		jlbSenha.setBounds(15, 150, 100, 25);
		getContentPane().add(jlbSenha);

		jtfSenha = new JTextField();
		jtfSenha.setBounds(125, 150, 150, 25);
		getContentPane().add(jtfSenha);

		jlbConfirmar = new JLabel("Confirmar Senha:");
		jlbConfirmar.setBounds(15, 180, 120, 25);
		getContentPane().add(jlbConfirmar);

		jtfConfirmar = new JTextField();
		jtfConfirmar.setBounds(125, 180, 150, 25);
		getContentPane().add(jtfConfirmar);

		jbtOk = new JButton("Ok");
		jbtOk.setBounds(100, 210, 50, 25);
		jbtOk.addActionListener(this);
		getContentPane().add(jbtOk);

		setTitle("Recuperação de Senha");
		setSize(320, 310);
		setVisible(true);
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent arg0) {
		int aux = 0;
		ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);
		Usuario usuario = new Usuario();
		for (Usuario usuario1 : usuarios) {
			if (usuario1.getNome().equals(jtfUsuario.getText()) && jtfSenha.getText().equals(jtfConfirmar.getText())) {
				usuario = usuario1;
				aux = 1;
			}
		}
		if (aux == 1) {
			usuario.setSenha(jtfSenha.getText());
			bancoDeDados.store(usuario);
			JOptionPane.showMessageDialog(this, "Senha Alterada com sucesso");
			bancoDeDados.close();
			dispose();
			new Login_t1();
		} else {
			JOptionPane.showMessageDialog(this, "As Senhas não conferem");
		}

	}
}