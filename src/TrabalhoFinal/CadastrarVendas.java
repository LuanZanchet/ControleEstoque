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

public class CadastrarVendas extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jlbPesquisar = new JLabel("Pesquisar por:");
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbCodigo = new JRadioButton("C�digo");
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
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	CadastrarVendas() {
		setTitle("Vendas");
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
		dtmTabela.setValueAt("C�digo", 0, 0);
		dtmTabela.setValueAt("Produto", 0, 1);
		dtmTabela.setValueAt("Valor", 0, 2);
		ObjectSet<Venda> vendas = bancoDeDados.query(Venda.class);
		for (Venda venda : vendas) {
			dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
			dtmTabela.setValueAt(venda.getCodigo(), n, 0);
			dtmTabela.setValueAt(venda.getProduto().getNome(), n, 1);
			dtmTabela.setValueAt(venda.getValorTotal(), n, 2);
			n++;
		}

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jrbNome.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o nome do produto que deseja pesquisar");
					} else {
						ObjectSet<Venda> vendas = bancoDeDados.query(Venda.class);
						int aux = 0, linha = 1;
						dtmTabela.setRowCount(1);
						for (Venda venda1 : vendas) {
							if (venda1.getProduto().getNome().equals(jtfPesquisar.getText())) {
								dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
								dtmTabela.setValueAt(venda1.getCodigo(), linha, 0);
								dtmTabela.setValueAt(venda1.getProduto().getNome(), linha, 1);
								dtmTabela.setValueAt(venda1.getValorTotal(), linha, 2);
								linha++;
								aux = 1;
							}
						}
						if (aux == 0 || jtfPesquisar.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nenhuma Venda Encontrada");
						}
					}
				} else if (jrbCodigo.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o nome codigo da venda que deseja pesquisar");
					} else {
						ObjectSet<Venda> vendas = bancoDeDados.query(Venda.class);
						int aux = 0, linha = 1;
						dtmTabela.setRowCount(1);
						for (Venda venda1 : vendas) {
							if (venda1.getCodigo().equals(Integer.valueOf(jtfPesquisar.getText()))) {
								dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
								dtmTabela.setValueAt(venda1.getCodigo(), linha, 0);
								dtmTabela.setValueAt(venda1.getProduto().getNome(), linha, 1);
								dtmTabela.setValueAt(venda1.getValorTotal(), linha, 2);
								linha++;
								aux = 1;
							}
						}
						if (aux == 0 || jtfPesquisar.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nenhuma Venda Encontrada");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione uma op��o para pesquisar");
				}

			}
		});
		jbtNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();
				new NovaVenda();
			}
		});
		jbtConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionado = jtbTabela.getSelectedRow();
				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "N�o pode Visualizar essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o produto que deseja Visualizar");
				} else {
					if (aux == 1)
						selecionado = procurar;
					bancoDeDados.close();
					VisualizarVenda tela = new VisualizarVenda();
					tela.setValor1(selecionado - 1);
					dispose();
				}
			}
		});
		jbtAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionado = jtbTabela.getSelectedRow();
				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "N�o pode alterar essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o produto que deseja alterar");
				} else {
					if (aux == 1)
						selecionado = procurar;
					bancoDeDados.close();
					AlterarVenda tela = new AlterarVenda();
					tela.setValor1(selecionado - 1);
					dispose();
				}
			}
		});
		jbtMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();
				new CadastrarVendas();

			}
		});
		jbtExcluir.addActionListener(new ActionListener() {
			ObjectSet<ContaAReceber> contas = bancoDeDados.query(ContaAReceber.class);

			public void actionPerformed(ActionEvent arg0) {
				selecionado = jtbTabela.getSelectedRow();
				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "N�o pode excluir essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o Produto que deseja excluir");
				} else {
					if (aux == 1)
						selecionado = procurar;
					ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
					ObjectSet<Venda> vendas = bancoDeDados.query(Venda.class);
					for (Produto produto : produtos) {
						if (produto.getNome().equals(vendas.get(selecionado - 1).getProduto().getNome())) {
							produto.setQuantidade(produto.getQuantidade() + vendas.get(selecionado - 1).getQuantidade());
							bancoDeDados.store(produto);
						}
					}
					for (ContaAReceber conta : contas) {
						if (vendas.get(selecionado - 1).getCodigo().equals(conta.getCodigo())) {
							bancoDeDados.delete(conta);
						}
					}
					bancoDeDados.delete(vendas.get(selecionado - 1));
					bancoDeDados.close();
					new CadastrarVendas();
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