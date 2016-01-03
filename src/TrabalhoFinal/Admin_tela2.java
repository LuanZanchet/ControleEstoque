package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class Admin_tela2 extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JMenuBar jmbBarra;
	private JMenu jmnArquivo, jmnMovimentacao, jmnFinanceiro, jmnSuporte, jmnSair;
	private JMenuItem jmiCadastrarUsuario, jmiContasReceber, jmiContasPagar, jmiSair, jmiOutro, jmiCadastrarCliente,
			jmiVendas;
	private JMenuItem jmiVisualizarEstoque, jmiContato, jmiCadastrarFornecedor;
	private JButton jbtCliente, jbtFornecedor, jbtProduto, jbtEstoque, jbtContas1, jbtContas, jbtSair, jbtVendas;
	private JLabel jlbCliente, jlbFornecedor, jlbProduto, jlbProduto1, jlbEstoque, jlbVendas, jlbLayout1, jlbLogo1,
			jlbLogo2, jlbLogo3, jlbLogo4;
	private JLabel jlbContas, jlbContas1, jlbContas2, jlbContas3, jlbSair, jlbLayout, jlbUsuario, jlbUsuario1,
			jlbSlogan, jlblogo;
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	private ObjectSet<Usuario> usuarios = bancoDeDados.query(Usuario.class);

	public void setValor(int Usuario) {
		jlbUsuario1.setText(usuarios.get(Usuario).getNome());
		if (Usuario != 0) {
			jmiCadastrarUsuario.setEnabled(false);
		}
		bancoDeDados.close();
	}

	Admin_tela2() {
		setLayout(null);
		jmbBarra = new JMenuBar();
		jmnArquivo = new JMenu("Arquivo");
		jmnMovimentacao = new JMenu("Movimentação de Estoque");
		jmnFinanceiro = new JMenu("Financeiro");
		jmnSuporte = new JMenu("Suporte");
		jmnSair = new JMenu("Sair");
		jmiCadastrarUsuario = new JMenuItem("Usuários");
		jmiCadastrarCliente = new JMenuItem("Clientes");
		jmiCadastrarFornecedor = new JMenuItem("Fornecedores");
		jmiVisualizarEstoque = new JMenuItem("Visualizar Estoque");
		jmiContasReceber = new JMenuItem("Contas à Receber");
		jmiContasPagar = new JMenuItem("Contas à Pagar");
		jmiContato = new JMenuItem("Contato");
		jmiSair = new JMenuItem("Sair");
		jmiOutro = new JMenuItem("Entrar Com Outro Usuário");
		jmiVendas = new JMenuItem("Vendas");

		jmbBarra.add(jmnArquivo);
		jmbBarra.add(jmnMovimentacao);
		jmbBarra.add(jmnFinanceiro);
		jmbBarra.add(jmnSuporte);
		jmbBarra.add(jmnSair);
		jmnArquivo.add(jmiCadastrarUsuario);
		jmnArquivo.add(jmiCadastrarCliente);
		jmnArquivo.add(jmiCadastrarFornecedor);
		jmnMovimentacao.add(jmiVisualizarEstoque);
		jmnMovimentacao.add(jmiVendas);
		jmnFinanceiro.add(jmiContasReceber);
		jmnFinanceiro.add(jmiContasPagar);
		jmnSuporte.add(jmiContato);
		jmnSair.add(jmiOutro);
		jmnSair.addSeparator();
		jmnSair.add(jmiSair);

		jmiCadastrarUsuario.addActionListener(this);
		jmiCadastrarCliente.addActionListener(this);
		jmiCadastrarFornecedor.addActionListener(this);
		jmiVisualizarEstoque.addActionListener(this);
		jmiContasReceber.addActionListener(this);
		jmiContasPagar.addActionListener(this);
		jmiContato.addActionListener(this);
		jmiSair.addActionListener(this);
		jmiOutro.addActionListener(this);
		jmiVendas.addActionListener(this);

		jlbCliente = new JLabel("Cliente");
		jlbCliente.setBounds(300, 130, 51, 51);
		jlbCliente.setBackground(Color.white);
		getContentPane().add(jlbCliente);

		jlbSlogan = new JLabel("Sistema de controle de estoque");
		jlbSlogan.setBounds(30, -30, 200, 100);
		jlbSlogan.setForeground(Color.white);
		getContentPane().add(jlbSlogan);

		jlbUsuario = new JLabel("Logado com:");
		jlbUsuario.setForeground(Color.white);
		jlbUsuario.setBounds(1110, -30, 100, 100);
		getContentPane().add(jlbUsuario);

		jlbUsuario1 = new JLabel();
		jlbUsuario1.setBounds(1200, -30, 100, 100);
		jlbUsuario1.setForeground(Color.white);
		getContentPane().add(jlbUsuario1);

		jbtCliente = new JButton(new ImageIcon("cliente.png"));
		jbtCliente.setBounds(280, 60, 75, 75);
		jbtCliente.setBackground(Color.white);
		jbtCliente.setBorder(null);
		getContentPane().add(jbtCliente);

		jlbFornecedor = new JLabel("Fornecedor");
		jlbFornecedor.setBounds(430, 130, 71, 51);
		jlbFornecedor.setBackground(Color.white);
		getContentPane().add(jlbFornecedor);

		jbtFornecedor = new JButton(new ImageIcon("fornecedor.png"));
		jbtFornecedor.setBounds(410, 60, 75, 75);
		jbtFornecedor.setBackground(Color.white);
		jbtFornecedor.setBorder(null);
		getContentPane().add(jbtFornecedor);

		jlbProduto = new JLabel("Cadastrar");
		jlbProduto.setBounds(550, 130, 71, 25);
		jlbProduto.setBackground(Color.white);
		getContentPane().add(jlbProduto);

		jlbProduto1 = new JLabel("Produto");
		jlbProduto1.setBounds(550, 150, 71, 25);
		jlbProduto1.setBackground(Color.white);
		getContentPane().add(jlbProduto1);

		jbtProduto = new JButton(new ImageIcon("novoproduto.jpg"));
		jbtProduto.setBounds(540, 60, 75, 75);
		jbtProduto.setBackground(Color.white);
		jbtProduto.setBorder(null);
		getContentPane().add(jbtProduto);

		jlbEstoque = new JLabel("Estoque");
		jlbEstoque.setBounds(680, 130, 71, 51);
		jlbEstoque.setBackground(Color.white);
		getContentPane().add(jlbEstoque);

		jbtEstoque = new JButton(new ImageIcon("estoque1.png"));
		jbtEstoque.setBounds(670, 60, 75, 75);
		jbtEstoque.setBackground(Color.white);
		jbtEstoque.setBorder(null);
		getContentPane().add(jbtEstoque);

		jlbContas = new JLabel("Contas");
		jlbContas.setBounds(820, 120, 71, 51);
		jlbContas.setBackground(Color.white);
		getContentPane().add(jlbContas);

		jlbContas1 = new JLabel("a Pagar");
		jlbContas1.setBounds(820, 130, 71, 51);
		jlbContas1.setBackground(Color.white);
		getContentPane().add(jlbContas1);

		jbtContas = new JButton(new ImageIcon("contapagar.jpg"));
		jbtContas.setBounds(800, 60, 75, 75);
		jbtContas.setBackground(Color.white);
		jbtContas.setBorder(null);
		getContentPane().add(jbtContas);

		jlbContas2 = new JLabel("Contas");
		jlbContas2.setBounds(950, 120, 71, 51);
		jlbContas2.setBackground(Color.white);
		getContentPane().add(jlbContas2);

		jlbContas3 = new JLabel("a Receber");
		jlbContas3.setBounds(950, 130, 71, 51);
		jlbContas3.setBackground(Color.white);
		getContentPane().add(jlbContas3);

		jbtContas1 = new JButton(new ImageIcon("contareceber.jpg"));
		jbtContas1.setBounds(930, 60, 94, 75);
		jbtContas1.setBackground(Color.white);
		jbtContas1.setBorder(null);
		getContentPane().add(jbtContas1);

		jlbSair = new JLabel("Sair");
		jlbSair.setBounds(1230, 130, 71, 51);
		jlbSair.setBackground(Color.white);
		getContentPane().add(jlbSair);

		jbtSair = new JButton(new ImageIcon("sair1.png"));
		jbtSair.setBounds(1210, 60, 75, 75);
		jbtSair.setBackground(Color.white);
		jbtSair.setBorder(null);
		getContentPane().add(jbtSair);

		jlbVendas = new JLabel("Vendas");
		jlbVendas.setBounds(1100, 130, 71, 51);
		getContentPane().add(jlbVendas);

		jbtVendas = new JButton(new ImageIcon("vendas1.jpg"));
		jbtVendas.setBounds(1080, 60, 75, 75);
		jbtVendas.setBackground(Color.white);
		jbtVendas.setBorder(null);
		getContentPane().add(jbtVendas);

		jlblogo = new JLabel(new ImageIcon("loggo.png"));
		jlblogo.setBounds(35, 35, 202, 163);
		jlblogo.setBackground(Color.white);
		getContentPane().add(jlblogo);

		jlbLogo1 = new JLabel(new ImageIcon("Logo1.jpg"));
		jlbLogo1.setBounds(1020, 250, 200, 86);
		jlbLogo1.setBackground(Color.white);
		getContentPane().add(jlbLogo1);

		jlbLogo2 = new JLabel(new ImageIcon("Logo2.jpg"));
		jlbLogo2.setBounds(1020, 350, 200, 53);
		jlbLogo2.setBackground(Color.white);
		getContentPane().add(jlbLogo2);

		jlbLogo3 = new JLabel(new ImageIcon("Logo3.jpg"));
		jlbLogo3.setBounds(1020, 420, 200, 64);
		jlbLogo3.setBackground(Color.white);
		getContentPane().add(jlbLogo3);

		jlbLogo4 = new JLabel(new ImageIcon("Logo4.png"));
		jlbLogo4.setBounds(1020, 520, 200, 63);
		jlbLogo4.setBackground(Color.white);
		getContentPane().add(jlbLogo4);

		jlbLayout = new JLabel(new ImageIcon("Layout.jpg"));
		jlbLayout.setBounds(35, 250, 861, 365);
		jlbLayout.setBackground(Color.white);
		getContentPane().add(jlbLayout);

		jlbLayout1 = new JLabel(new ImageIcon("Layout1.png"));
		jlbLayout1.setBounds(1, 1, 1370, 706);
		jlbLayout1.setBackground(Color.white);
		getContentPane().add(jlbLayout1);

		jbtVendas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new CadastrarVendas();
			}
		});

		jbtCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new CadastrarCliente();
			}
		});
		jbtFornecedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CadastrarFornecedor();
			}
		});
		jbtProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new NovoProduto();
			}
		});
		jbtContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ContasAPagar();
			}
		});
		jbtContas1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ContasAReceber();
			}
		});
		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();
			}
		});
		jbtEstoque.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new CadastrarProduto();

			}
		});

		setJMenuBar(jmbBarra);
		setTitle("Refigeração São Roque");
		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == jmiCadastrarUsuario) {

			new CadastrarUsuario();
		}
		if (arg0.getSource() == jmiCadastrarCliente) {
			new CadastrarCliente();
		}
		if (arg0.getSource() == jmiCadastrarFornecedor) {
			new CadastrarFornecedor();
		}
		if (arg0.getSource() == jmiVisualizarEstoque) {
			new CadastrarProduto();
		}

		if (arg0.getSource() == jmiContasReceber) {
			new ContasAReceber();
		}
		if (arg0.getSource() == jmiContasPagar) {
			new ContasAPagar();
		}
		if (arg0.getSource() == jmiContato) {
			new Suporte();
		}
		if (arg0.getSource() == jmiVendas) {
			new CadastrarVendas();
		}

		if (arg0.getSource() == jmiSair) {
			int resultado = JOptionPane.showConfirmDialog(this, "Deseja Realmente Sair?");
			if (resultado == 0) {
				bancoDeDados.close();
				JOptionPane.showMessageDialog(this, "Refrigeração São Roque Consertando seus Eletros!");
				dispose();

			}
		}
		if (arg0.getSource() == jmiOutro) {
			int resultado = JOptionPane.showConfirmDialog(this, "Deseja Trocar de Usuário?");
			if (resultado == 0) {
				bancoDeDados.close();
				dispose();
				new Login_t1();
			}

		}
	}
}
