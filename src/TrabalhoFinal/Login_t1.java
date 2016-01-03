package TrabalhoFinal;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Login_t1 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel jlbUsuario, jlbSenha, jlbImg;
	private JTextField jtfUsuario;
	private JPasswordField jpfSenha;
	private JButton jbtEsqueceuSenha, jbtLogar, jbtSair;
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	private Integer tentativas = 0;

	Login_t1() {
		setLayout(null);

		jlbImg = new JLabel(new ImageIcon("teste.jpg"));
		jlbImg.setBounds(10, 10, 300, 120);
		getContentPane().add(jlbImg);

		jlbUsuario = new JLabel("Usuário:");
		jlbUsuario.setBounds(15, 150, 100, 25);
		jlbUsuario.setForeground(Color.blue);
		getContentPane().add(jlbUsuario);

		jtfUsuario = new JTextField();
		jtfUsuario.setBounds(85, 150, 150, 25);
		getContentPane().add(jtfUsuario);

		jlbSenha = new JLabel("Senha:");
		jlbSenha.setBounds(15, 180, 100, 25);
		jlbSenha.setForeground(Color.blue);
		getContentPane().add(jlbSenha);

		jpfSenha = new JPasswordField();
		jpfSenha.setBounds(85, 180, 150, 25);
		getContentPane().add(jpfSenha);

		jbtLogar = new JButton(new ImageIcon("botao_login.jpg"));
		jbtLogar.setBounds(100, 210, 80, 25);
		jbtLogar.setBorder(null);
		jbtLogar.addActionListener(this);
		getContentPane().add(jbtLogar);

		jbtSair = new JButton(new ImageIcon("botao_sair.gif"));
		jbtSair.setBounds(260, 230, 40, 40);
		jbtSair.setBorder(null);
		jbtSair.addActionListener(this);
		getContentPane().add(jbtSair);

		jbtEsqueceuSenha = new JButton("Esqueci Minha Senha");
		jbtEsqueceuSenha.setFont(new Font("Serif", Font.BOLD, 10));
		jbtEsqueceuSenha.setForeground(Color.black);
		jbtEsqueceuSenha.setBounds(90, 240, 130, 15);
		jbtEsqueceuSenha.addActionListener(this);
		getContentPane().add(jbtEsqueceuSenha);

		setTitle("Seja Bem-Vindo");
		setSize(320, 310);
		setVisible(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jbtLogar) {
			Integer existe = 0, posicao = 0;
			if (jtfUsuario.getText().isEmpty() && jpfSenha.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Digite seu Usuário e sua Senha");
			} else {
				ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);
				for (Usuario usuario : usuarios) {
					if (usuario.getNome().equals(jtfUsuario.getText()) && usuario.getSenha().equals(jpfSenha.getText())) {
						existe = 1;
						break;
					}
					posicao++;
				}
				Integer aux1 = 0;
				if (existe == 1) {
					for (Usuario usuario : usuarios) {

						if (usuario.getNome().equals(jtfUsuario.getText())
								&& usuario.getSenha().equals(jpfSenha.getText())) {
							aux1 = 1;
						}

					}
					if (aux1 == 1) {
						bancoDeDados.close();
						Admin_tela2 tela = new Admin_tela2();
						tela.setValor(posicao);
						dispose();
					}
				} else {
					JOptionPane.showMessageDialog(this, "Combinação de usuario ou senha incorreta");
					jtfUsuario.setText(null);
					jpfSenha.setText(null);
					tentativas++;
				}
				if (tentativas == 3) {
					JOptionPane.showMessageDialog(this, "Esqueceu sua senha? Tente função Esqueci minha Senha");
					tentativas = 0;
				}

			}
		}
		if (arg0.getSource() == jbtSair) {
			Integer escolha = JOptionPane.showConfirmDialog(this, "Deseja mesmo Sair?");
			if (escolha == 0) {
				bancoDeDados.close();
				dispose();
			}

		}
		if (arg0.getSource() == jbtEsqueceuSenha) {
			bancoDeDados.close();
			dispose();
			new EsqueceuSenha();
		}
	}

	public static void main(String[] args) {
		new Login_t1();
	}
}
