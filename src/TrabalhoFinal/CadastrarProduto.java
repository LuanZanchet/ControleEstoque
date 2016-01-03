package TrabalhoFinal;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class CadastrarProduto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jlbPesquisar = new JLabel("Pesquisar por:");
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbCodigo = new JRadioButton("Código");
	private JRadioButton jrbNome = new JRadioButton("Nome Produto");
	private JTextField jtfPesquisar = new JTextField();
	private JTable jtbTabela = new JTable();
	private ScrollPane scpRolagem = new ScrollPane();
	private DefaultTableModel dtmTabela = new DefaultTableModel();
	private JButton jbtPesquisar = new JButton("Pesquisar");
	private JButton jbtNovo = new JButton("Novo");
	private JButton jbtAlterar = new JButton("Alterar"), jbtExcluir = new JButton("Excluir");
	private JButton jbtConsultar = new JButton("Consultar"), jbtSair = new JButton("Sair"), jbtMostrar = new JButton(
			"Mostrar Todos");
	private int n = 1, selecionado, procurar = 0, aux = 0;

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");

	CadastrarProduto() {
		setTitle("Produtos");
		setLayout(null);
		posicionaObjeto(jlbPesquisar, 10, 10, 100, 25);
		posicionaObjeto(jrbCodigo, 10, 30, 100, 25);
		posicionaObjeto(jrbNome, 10, 50, 150, 25);
		posicionaObjeto(jtfPesquisar, 160, 30, 300, 25);
		scpRolagem.setBounds(10, 80, 550, 300);
		scpRolagem.add(jtbTabela);
		getContentPane().add(scpRolagem);
		posicionaObjeto(jbtPesquisar, 470, 20, 100, 25);
		posicionaObjeto(jbtMostrar, 460, 50, 120, 25);
		posicionaObjeto(jbtNovo, 20, 400, 70, 25);
		posicionaObjeto(jbtConsultar, 110, 400, 100, 25);
		posicionaObjeto(jbtAlterar, 230, 400, 100, 25);
		posicionaObjeto(jbtExcluir, 350, 400, 100, 25);
		posicionaObjeto(jbtSair, 460, 400, 90, 25);

		jrbCodigo.setBackground(Color.white);
		jrbNome.setBackground(Color.white);

		btgEscolha.add(jrbCodigo);
		btgEscolha.add(jrbNome);

		jtbTabela.setModel(dtmTabela);

		dtmTabela.setColumnCount(3);
		dtmTabela.setRowCount(n);
		dtmTabela.setValueAt("Código", 0, 0);
		dtmTabela.setValueAt("Produto", 0, 1);
		dtmTabela.setValueAt("Preço", 0, 2);

		ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
		for (Produto produto : produtos) {
			dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
			dtmTabela.setValueAt(produto.getCodigo(), n, 0);
			dtmTabela.setValueAt(produto.getNome(), n, 1);
			dtmTabela.setValueAt(produto.getValorVenda(), n, 2);
			n++;
		}

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jrbNome.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o nome do produto que deseja pesquisar");
					} else {
						ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
						Produto produto = new Produto();
						int aux = 0;
						for (Produto produto1 : produtos) {
							if (produto1.getNome().equals(jtfPesquisar.getText())) {
								produto = produto1;
								aux = 1;

							}
						}
						if (aux == 0 || jtfPesquisar.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nenhum Usuario Encontrado");
						} else {
							ObjectSet<Produto> produtoss = bancoDeDados.queryByExample(produto);
							int linha = 1;
							dtmTabela.setRowCount(1);
							for (Produto produto2 : produtoss) {
								dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
								dtmTabela.setValueAt(produto.getCodigo(), linha, 0);
								dtmTabela.setValueAt(produto.getNome(), linha, 1);
								dtmTabela.setValueAt(produto.getValorVenda(), linha, 2);
								linha++;

							}
						}
					}
				} else if (jrbCodigo.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o código do produto que deseja pesquisar");
					} else {
						ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
						Produto produto = new Produto();
						int aux = 0;
						for (Produto produto1 : produtos) {
							if (produto1.getCodigo().equals(Integer.parseInt(jtfPesquisar.getText()))) {
								produto = produto1;
								aux = 1;

							}
						}
						if (aux == 0) {
							JOptionPane.showMessageDialog(null, "Nenhum Produto Encontrado");
						} else {
							ObjectSet<Produto> produtoss = bancoDeDados.queryByExample(produto);
							int linha = 1;
							dtmTabela.setRowCount(1);
							for (Produto produto2 : produtoss) {
								dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
								dtmTabela.setValueAt(produto.getCodigo(), linha, 0);
								dtmTabela.setValueAt(produto.getNome(), linha, 1);
								dtmTabela.setValueAt(produto.getValorVenda(), linha, 2);
								linha++;

							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma opção para pesquisar");
				}
				bancoDeDados.close();
			}

		});
		jbtNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();
				new NovoProduto();

			}
		});
		jbtConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionado = jtbTabela.getSelectedRow();

				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "Não pode Visualizar essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o produto que deseja Visualizar");
				} else {
					if (aux == 1)
						selecionado = procurar;
					bancoDeDados.close();
					VisualizarProduto tela = new VisualizarProduto();
					tela.setValor1(selecionado - 1);
					dispose();

				}

			}
		});
		jbtAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionado = jtbTabela.getSelectedRow();
				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "Não pode alterar essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o produto que deseja alterar");
				} else {
					if (aux == 1)
						selecionado = procurar;
					bancoDeDados.close();
					AlterarProduto tela = new AlterarProduto();
					tela.setValor1(selecionado - 1);
					dispose();
				}
			}
		});
		jbtMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();
				new CadastrarProduto();
			}

		});
		jbtExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				selecionado = jtbTabela.getSelectedRow();
				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "Não pode excluir essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o Produto que deseja excluir");
				} else {
					if (aux == 1)
						selecionado = procurar;
					ObjectSet<ContaAPagar> contas = bancoDeDados.query(ContaAPagar.class);
					ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
					for (ContaAPagar conta : contas) {
						if (conta.getCodigo().equals(produtos.get(selecionado - 1).getCodigo())) {
							bancoDeDados.delete(conta);
						}
					}
					bancoDeDados.delete(produtos.get(selecionado - 1));
					bancoDeDados.close();
					new CadastrarProduto();
					dispose();
				}

			}
		});
		jbtSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();

			}
		});

		setSize(600, 600);
		setLocationRelativeTo(null);
		this.getContentPane().setBackground(Color.white);
		this.setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setVisible(true);
	}

}
