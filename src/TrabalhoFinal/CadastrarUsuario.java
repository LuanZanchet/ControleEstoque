package TrabalhoFinal;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class CadastrarUsuario extends JFrame implements ActionListener {
	private JLabel jlbFrase, jlbUsuarios, jlbAtual, jlbNova, jlbConfirmar, jlbNovo;
	private JTextField jtfAtual, jtfNova, jtfConfirmar, jtfNovo;
	private JButton jbtInserir, jbtExcluir, jbtAlterar, jbtFechar;
	private JList<String> jltUsuarios = new JList<>();
	private Vector<String> vcrUsuarios = new Vector<>();
	private ScrollPane rolagem = new ScrollPane();
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	private JLabel jlbPalavra;
	private JTextField jtfPalavra;

	CadastrarUsuario() {
		setLayout(null);

		rolagem.setBounds(20, 70, 250, 200);
		jltUsuarios.setListData(vcrUsuarios);
		rolagem.add(jltUsuarios);
		getContentPane().add(rolagem);

		jlbFrase = new JLabel(">>>Para excluir um usuário, selecione-o na lista 'Usuários' depois clique em 'Excluir'.");
		jlbFrase.setBounds(30, 0, 500, 25);
		getContentPane().add(jlbFrase);

		jlbUsuarios = new JLabel("Usuários:");
		jlbUsuarios.setBounds(20, 40, 100, 25);
		getContentPane().add(jlbUsuarios);

		jlbAtual = new JLabel("Senha Atual:");
		jlbAtual.setBounds(290, 40, 100, 25);
		getContentPane().add(jlbAtual);

		jtfAtual = new JTextField();
		jtfAtual.setBounds(290, 70, 150, 25);
		jtfAtual.setEditable(false);
		getContentPane().add(jtfAtual);

		jlbNova = new JLabel("Nova Senha:");
		jlbNova.setBounds(290, 110, 100, 25);
		getContentPane().add(jlbNova);

		jtfNova = new JTextField();
		jtfNova.setBounds(290, 140, 150, 25);
		getContentPane().add(jtfNova);

		jlbConfirmar = new JLabel("Confirmar Senha:");
		jlbConfirmar.setBounds(290, 180, 100, 25);
		getContentPane().add(jlbConfirmar);

		jtfConfirmar = new JTextField();
		jtfConfirmar.setBounds(290, 210, 150, 25);
		getContentPane().add(jtfConfirmar);

		jlbPalavra = new JLabel("Palavra Secreta");
		jlbPalavra.setBounds(290, 250, 150, 25);
		getContentPane().add(jlbPalavra);

		jtfPalavra = new JTextField();
		jtfPalavra.setBounds(290, 270, 150, 25);
		getContentPane().add(jtfPalavra);

		jlbNovo = new JLabel("Novo Usuário:");
		jlbNovo.setBounds(10, 290, 100, 25);
		getContentPane().add(jlbNovo);

		jtfNovo = new JTextField();
		jtfNovo.setBounds(10, 320, 150, 25);
		getContentPane().add(jtfNovo);

		jbtInserir = new JButton("Inserir");
		jbtInserir.setBounds(170, 320, 80, 25);
		jbtInserir.addActionListener(this);
		getContentPane().add(jbtInserir);

		jbtExcluir = new JButton("Excluir");
		jbtExcluir.setBounds(260, 320, 80, 25);
		jbtExcluir.addActionListener(this);
		getContentPane().add(jbtExcluir);

		jbtAlterar = new JButton("Alterar");
		jbtAlterar.setBounds(350, 320, 80, 25);
		jbtAlterar.addActionListener(this);
		getContentPane().add(jbtAlterar);

		jbtFechar = new JButton("Fechar");
		jbtFechar.setBounds(440, 320, 80, 25);
		jbtFechar.addActionListener(this);
		getContentPane().add(jbtFechar);

		final ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);
		for (Usuario usuario : usuarios) {
			vcrUsuarios.add(usuario.getNome());
		}

		jltUsuarios.setListData(vcrUsuarios);

		jltUsuarios.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1 || e.getClickCount() == 2) {
					jtfAtual.setText(usuarios.get(jltUsuarios.getSelectedIndex()).getSenha());
					jtfPalavra.setText(usuarios.get(jltUsuarios.getSelectedIndex()).getPalavraSecreta());
				}
			}
		});

		setTitle("Cadastro de Usuário");
		setSize(550, 400);
		setVisible(true);
		this.getContentPane().setBackground(Color.white);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getSource() == jbtAlterar) {
			int aux = 0;
			if (jltUsuarios.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "Selecione o Usuário que deseja alterar");
			} else if (jtfPalavra.getText().isEmpty() || jtfNova.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "DIgite uma Senha");
			} else {
				ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);
				Usuario usuario = new Usuario();
				usuario = usuarios.get(jltUsuarios.getSelectedIndex());
				if (jtfNova.getText().equals(jtfConfirmar.getText())) {
					usuario.setSenha(jtfNova.getText());
					aux = 1;
				}
				if (!jtfPalavra.getText().isEmpty()) {
					usuario.setPalavraSecreta(jtfPalavra.getText());
				}
				if (aux == 1) {
					bancoDeDados.store(usuario);
					JOptionPane.showMessageDialog(this, "Alterações Realizadas com Sucesso");
					bancoDeDados.close();
					dispose();
					new CadastrarUsuario();
				} else if (aux == 0) {
					JOptionPane.showMessageDialog(this, "As senhas não conferem");
				}
			}
		}
		if (arg0.getSource() == jbtInserir) {
			ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);
			Integer aux = 0;
			for (Usuario usuario1 : usuarios) {
				if (usuario1.getNome().equals(jtfNovo.getText())) {
					aux = 1;
				}
			}
			if (aux == 0) {
				if (jtfNovo.getText().isEmpty() || jtfNova.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this, "Há campos obrigatórios em Branco");
				} else {
					if (jtfNova.getText().equals(jtfConfirmar.getText())) {
						Usuario usuario = new Usuario();
						usuario.setNome(jtfNovo.getText());
						usuario.setSenha(jtfNova.getText());
						usuario.setPalavraSecreta(jtfPalavra.getText());
						bancoDeDados.store(usuario);
						JOptionPane.showMessageDialog(this, "Usuario Cadastrado com Sucesso");
						bancoDeDados.close();
						dispose();
						new CadastrarUsuario();
					} else {
						JOptionPane.showMessageDialog(this, "As senhas não conferem");
					}
				}
			}
			if (aux == 1) {
				JOptionPane.showMessageDialog(this, "Já Existe um usuário com esse nome");
				aux = 0;
			}
		}
		if (arg0.getSource() == jbtExcluir) {
			if (jltUsuarios.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(this, "Selecione o Usuário que Deseja Excluir");
			} else if (jltUsuarios.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(this, "Você não pode excluir o administrador");
			} else {
				ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);
				bancoDeDados.delete(usuarios.get(jltUsuarios.getSelectedIndex()));
				JOptionPane.showMessageDialog(this, "Operação Realizada com Sucesso");
				bancoDeDados.close();
				dispose();
				new CadastrarUsuario();
			}
		}

		if (arg0.getSource() == jbtFechar) {
			bancoDeDados.close();
			dispose();
		}
	}

}
