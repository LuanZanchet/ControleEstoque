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

public class CadastrarFornecedor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jlbPesquisar = new JLabel("Pesquisar por:");
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbCodigo = new JRadioButton("Código");
	private JRadioButton jrbNome = new JRadioButton("Nome/Empresa");
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

	CadastrarFornecedor() {
		setTitle("Fornecedores");
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
		dtmTabela.setValueAt("Fornecedor", 0, 1);
		dtmTabela.setValueAt("Telefone", 0, 2);

		ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
		for (Fornecedor fornecedor : fornecedores) {
			dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
			dtmTabela.setValueAt(fornecedor.getCodigo(), n, 0);
			dtmTabela.setValueAt(fornecedor.getNome(), n, 1);
			dtmTabela.setValueAt(fornecedor.getTelefone(), n, 2);
			n++;
		}

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jrbNome.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o nome do fornecedor que deseja pesquisar");
					} else {
						ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
						Fornecedor fornecedor = new Fornecedor();
						int aux = 0;
						for (Fornecedor fornecedor1 : fornecedores) {
							if (fornecedor1.getNome().equals(jtfPesquisar.getText())) {
								fornecedor = fornecedor1;
								aux = 1;

							}
						}
						if (aux == 0 || jtfPesquisar.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nenhum Usuario Encontrado");
						} else {
							ObjectSet<Fornecedor> fornecedoress = bancoDeDados.queryByExample(fornecedor);
							int linha = 1;
							dtmTabela.setRowCount(1);
							for (Fornecedor fornecedor2 : fornecedoress) {
								dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
								dtmTabela.setValueAt(fornecedor.getCodigo(), linha, 0);
								dtmTabela.setValueAt(fornecedor.getNome(), linha, 1);
								dtmTabela.setValueAt(fornecedor.getTelefone(), linha, 2);
								linha++;

							}
						}
					}
				} else if (jrbCodigo.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o código do Fornecedor que deseja pesquisar");
					} else {
						ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
						Fornecedor fornecedor = new Fornecedor();
						int aux = 0;
						for (Fornecedor fornecedor1 : fornecedores) {
							if (fornecedor1.getCodigo().equals(Integer.parseInt(jtfPesquisar.getText()))) {
								fornecedor = fornecedor1;
								aux = 1;

							}
						}
						if (aux == 0) {
							JOptionPane.showMessageDialog(null, "Nenhum Usuario Encontrado");
						} else {
							ObjectSet<Fornecedor> fornecedoress = bancoDeDados.queryByExample(fornecedor);
							int linha = 1;
							dtmTabela.setRowCount(1);
							for (Fornecedor fornecedor2 : fornecedoress) {
								dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
								dtmTabela.setValueAt(fornecedor.getCodigo(), linha, 0);
								dtmTabela.setValueAt(fornecedor.getNome(), linha, 1);
								dtmTabela.setValueAt(fornecedor.getTelefone(), linha, 2);
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
				new NovoFornecedor();

			}
		});
		jbtConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionado = jtbTabela.getSelectedRow();

				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "Não pode Visualizar essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o fornecedor que deseja Visualizar");
				} else {
					if (aux == 1)
						selecionado = procurar;
					bancoDeDados.close();
					VisualizarFornecedor tela = new VisualizarFornecedor();
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
					JOptionPane.showMessageDialog(null, "Selecione o Fornecedor que deseja alterar");
				} else {
					if (aux == 1)
						selecionado = procurar;
					bancoDeDados.close();
					AlterarFornecedor tela = new AlterarFornecedor();
					tela.setValor1(selecionado - 1);
					dispose();
				}
			}
		});
		jbtMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();
				new CadastrarFornecedor();
			}

		});
		jbtExcluir.addActionListener(new ActionListener() {
			ObjectSet<ContaAPagar> contasAPagar = bancoDeDados.query(ContaAPagar.class);
			ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);

			public void actionPerformed(ActionEvent arg0) {
				selecionado = jtbTabela.getSelectedRow();
				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "Não pode excluir essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione o Fornecedor que deseja excluir");
				} else {
					if (aux == 1)
						selecionado = procurar;
					Integer auxx = 0;
					ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
					for (ContaAPagar conta : contasAPagar) {
						if (conta.getFornecedor().getNome().equals(fornecedores.get(selecionado - 1).getNome())) {
							if (conta.getDataVencimento() != null) {
								auxx = 1;
							}
						}
					}
					if (auxx == 0) {
						for (ContaAPagar conta : contasAPagar) {
							if (conta.getFornecedor().getNome().equals(fornecedores.get(selecionado - 1).getNome())) {
								bancoDeDados.delete(conta);
							}
						}
						bancoDeDados.delete(fornecedores.get(selecionado - 1));
						bancoDeDados.close();
						dispose();
						new CadastrarFornecedor();
					}
					if (auxx == 1) {
						JOptionPane.showMessageDialog(null, "Não pode excluir esse Fornecedor");
						JOptionPane.showMessageDialog(null, "Existe conta(s) a pagar pendente(s)");
					}
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
		setVisible(true);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	}

}
