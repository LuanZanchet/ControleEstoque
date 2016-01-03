package TrabalhoFinal;

import java.awt.Color;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class ContasAPagar extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel jlbPesquisar = new JLabel("Pesquisar por:");
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JRadioButton jrbFornecedor = new JRadioButton("Fornecedor");
	private JRadioButton jrbData = new JRadioButton("Data");
	private JTextField jtfPesquisar = new JTextField();
	private JTable jtbTabela = new JTable();
	private ScrollPane scpRolagem = new ScrollPane();
	private DefaultTableModel dtmTabela = new DefaultTableModel();
	private JButton jbtPesquisar = new JButton("Pesquisar");
	private JButton jbtExcluir = new JButton("Paga");
	private JButton jbtSair = new JButton("Sair"), jbtMostrar = new JButton("Mostrar Todas");
	private int n = 1, selecionado, procurar = 0, aux = 0;
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	ObjectSet<ContaAPagar> contas = bancoDeDados.query(ContaAPagar.class);

	ContasAPagar() {
		setTitle("Contas A Pagar");
		setLayout(null);
		posicionaObjeto(jlbPesquisar, 10, 10, 100, 25);
		posicionaObjeto(jrbFornecedor, 10, 30, 100, 25);
		posicionaObjeto(jrbData, 10, 50, 150, 25);
		posicionaObjeto(jtfPesquisar, 160, 30, 300, 25);
		scpRolagem.setBounds(10, 80, 550, 300);
		scpRolagem.add(jtbTabela);
		getContentPane().add(scpRolagem);
		posicionaObjeto(jbtPesquisar, 470, 20, 100, 25);
		posicionaObjeto(jbtMostrar, 460, 50, 120, 25);
		posicionaObjeto(jbtExcluir, 350, 400, 100, 25);
		posicionaObjeto(jbtSair, 460, 400, 90, 25);
		jrbFornecedor.setBackground(Color.white);
		jrbData.setBackground(Color.white);
		btgEscolha.add(jrbFornecedor);
		btgEscolha.add(jrbData);
		jtbTabela.setModel(dtmTabela);
		dtmTabela.setColumnCount(3);
		dtmTabela.setRowCount(n);
		dtmTabela.setValueAt("Fornecedor", 0, 0);
		dtmTabela.setValueAt("Valor", 0, 1);
		dtmTabela.setValueAt("Data Vencimento", 0, 2);

		final ObjectSet<ContaAPagar> contas = bancoDeDados.query(ContaAPagar.class);

		for (ContaAPagar conta : contas) {
			dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
			dtmTabela.setValueAt(conta.getFornecedor().getNome(), n, 0);
			dtmTabela.setValueAt(conta.getValor(), n, 1);
			if (conta.getDataVencimento() == null) {
				dtmTabela.setValueAt("Pago", n, 2);
			} else {
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String data = df.format(conta.getDataVencimento());
				dtmTabela.setValueAt(data, n, 2);
			}
			n++;
		}

		jbtPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (jrbFornecedor.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite o nome do fornecedor que deseja pesquisar");
					} else {
						ObjectSet<ContaAPagar> contas = bancoDeDados.query(ContaAPagar.class);
						int aux = 0, linha = 1;
						dtmTabela.setRowCount(1);
						for (ContaAPagar conta1 : contas) {
							if (conta1.getFornecedor().getNome().equals(jtfPesquisar.getText())) {

								dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
								dtmTabela.setValueAt(conta1.getFornecedor().getNome(), linha, 0);
								dtmTabela.setValueAt(conta1.getValor(), linha, 1);
								if (conta1.getDataVencimento() == null) {
									dtmTabela.setValueAt("Pago", linha, 2);
								} else {
									DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
									String data = df.format(conta1.getDataVencimento());
									dtmTabela.setValueAt(data, linha, 2);
								}

								linha++;
								aux = 1;
							}
						}
						if (aux == 0 || jtfPesquisar.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nenhum Fornecedor Encontrado");
						}
					}
				} else if (jrbData.isSelected()) {
					if (jtfPesquisar.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Digite a data da conta que deseja pesquisar");
					} else {
						ObjectSet<ContaAPagar> contas = bancoDeDados.query(ContaAPagar.class);
						int aux = 0, linha = 1;
						dtmTabela.setRowCount(1);
						for (ContaAPagar conta : contas) {
							String dataRecebida1 = jtfPesquisar.getText();
							DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
							try {
								Date dt1 = df1.parse(dataRecebida1);
								if (conta.getDataVencimento().equals(dt1)) {

									dtmTabela.setRowCount(dtmTabela.getRowCount() + 1);
									dtmTabela.setValueAt(conta.getFornecedor().getNome(), linha, 0);
									dtmTabela.setValueAt(conta.getValor(), linha, 1);
									if (conta.getDataVencimento() == null) {
										dtmTabela.setValueAt("Pago", linha, 2);
									} else {
										DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
										String data = df.format(conta.getDataVencimento());
										dtmTabela.setValueAt(data, linha, 2);
									}

									linha++;
									aux = 1;
								}
							} catch (ParseException e2) {
								e2.printStackTrace();
							}
						}
						if (aux == 0 || jtfPesquisar.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Nenhumaa Conta Encontrada");
						}
					}
				}
			}
		});

		jbtMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bancoDeDados.close();
				dispose();
				new ContasAPagar();

			}
		});
		jbtExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				selecionado = jtbTabela.getSelectedRow();
				if (selecionado == 0) {
					JOptionPane.showMessageDialog(null, "Não pode excluir essa linha");
				} else if (selecionado == -1) {
					JOptionPane.showMessageDialog(null, "Selecione a Conta que deseja declarar como paga");
				} else {
					if (aux == 1)
						selecionado = procurar;
					Integer escolha = JOptionPane.showConfirmDialog(null, "Tem certeza que esta conta já foi paga?");
					if (escolha == 0) {
						Date dt = null;

						contas.get(selecionado - 1).setDataVencimento(dt);

						bancoDeDados.store(contas.get(selecionado - 1));
					}
					bancoDeDados.close();
					new ContasAPagar();
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
