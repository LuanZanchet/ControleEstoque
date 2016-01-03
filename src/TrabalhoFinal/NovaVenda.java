package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class NovaVenda extends JFrame {
	private JLabel jlbCodigo = new JLabel("Codigo");
	private JLabel jlbProduto = new JLabel("Produto");
	private JLabel jlbCliente = new JLabel("Cliente");
	private JComboBox<String> jcbProduto = new JComboBox<>();
	private JComboBox<String> jcbCliente = new JComboBox<>();
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	private JLabel jlbData = new JLabel("Data de Venda");
	private JLabel jlbDataVencimento = new JLabel("Data de Vencimento");
	private JTextField jtfCodigo = new JTextField();
	private JTextField jtfQuantidade = new JTextField();
	private JTextField jtfData = new JTextField();
	private JTextField jtfDataVencimento = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtFechar = new JButton("Fechar");
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	private JRadioButton jrbVista = new JRadioButton("À vista");
	private JRadioButton jrbPrazo = new JRadioButton("À prazo");
	private ButtonGroup btgEscolha = new ButtonGroup();
	private JLabel jlbFormaPagamento = new JLabel("Forma de Pagamento");
	private ObjectSet<Venda> vendas = bancoDeDados.query(Venda.class);
	private JButton jbtCalendario = new JButton(new ImageIcon("calendario.jpg"));
	private JButton jbtCalendario1 = new JButton(new ImageIcon("calendario.jpg"));

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	NovaVenda() {
		setTitle("Vendas");
		setLayout(null);
		jrbPrazo.setBackground(Color.white);
		jrbVista.setBackground(Color.white);
		btgEscolha.add(jrbPrazo);
		btgEscolha.add(jrbVista);
		posicionaObjeto(jlbCodigo, 100, 10, 80, 25);
		posicionaObjeto(jtfCodigo, 150, 10, 170, 25);
		posicionaObjeto(jlbProduto, 95, 40, 80, 25);
		posicionaObjeto(jcbProduto, 150, 40, 170, 25);
		posicionaObjeto(jlbQuantidade, 75, 70, 100, 25);
		posicionaObjeto(jtfQuantidade, 150, 70, 170, 25);
		posicionaObjeto(jlbData, 60, 100, 100, 25);
		posicionaObjeto(jtfData, 150, 100, 170, 25);
		posicionaObjeto(jbtCalendario1, 330, 100, 30, 30);
		posicionaObjeto(jlbCliente, 100, 130, 80, 25);
		posicionaObjeto(jcbCliente, 150, 130, 170, 25);
		posicionaObjeto(jlbFormaPagamento, 150, 160, 170, 25);
		posicionaObjeto(jrbVista, 150, 190, 170, 25);
		posicionaObjeto(jrbPrazo, 150, 220, 170, 25);
		posicionaObjeto(jlbDataVencimento, 25, 250, 150, 25);
		posicionaObjeto(jtfDataVencimento, 150, 250, 170, 25);
		posicionaObjeto(jbtCalendario, 330, 250, 30, 30);
		posicionaObjeto(jbtCadastrar, 30, 310, 100, 35);
		posicionaObjeto(jbtFechar, 200, 310, 100, 25);
		jtfData.setEditable(false);
		jtfDataVencimento.setEditable(false);
		ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
		for (Produto produto : produtos) {
			if (produto.getQuantidade() > 0) {
				jcbProduto.addItem(produto.getNome());
			}
		}
		ObjectSet<Cliente> clientes = bancoDeDados.query(Cliente.class);
		for (Cliente cliente : clientes) {
			jcbCliente.addItem(cliente.getNome());

		}

		jbtCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (jtfCodigo.getText().isEmpty() || jtfData.getText().isEmpty()
						|| (jtfDataVencimento.getText().isEmpty() && jrbPrazo.isSelected())
						|| jtfQuantidade.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos os Campos São Obrigatórios!");
				} else {
					ObjectSet<Cliente> clientes = bancoDeDados.query(Cliente.class);
					ContaAReceber conta = new ContaAReceber();

					Integer aux = 0;
					for (Venda venda : vendas) {
						if (venda.getCodigo().equals(Integer.valueOf(jtfCodigo.getText()))) {
							aux = 1;
						}
					}
					if (aux == 0) {
						if (!jrbPrazo.isSelected() && !jrbVista.isSelected()) {
							JOptionPane.showMessageDialog(null, "Selecione a forma de Pagamento");
						} else {
							if (jcbProduto.getSelectedItem() == null) {
								JOptionPane.showMessageDialog(null, "Selecione o produto que deseja vender");
							} else {
								Venda venda = new Venda();
								Produto produto = new Produto();
								ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
								for (Produto produto1 : produtos) {
									if (produto1.getNome().equals(String.valueOf(jcbProduto.getSelectedItem()))) {
										produto = produto1;
									}
								}
								String dataRecebida = jtfData.getText();
								DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								try {
									Date dt = df.parse(dataRecebida);
									venda.setData(dt);
								} catch (ParseException e) {
									e.printStackTrace();
								}
								for (Cliente cliente1 : clientes) {
									if (cliente1.getNome().equals(String.valueOf(jcbCliente.getSelectedItem()))) {
										venda.setCliente(cliente1);
									}
								}
								venda.setCodigo(Integer.valueOf(jtfCodigo.getText()));
								venda.setProduto(produto);
								venda.setQuantidade(Integer.valueOf(jtfQuantidade.getText()));
								venda.setValorTotal(Double.valueOf(jtfQuantidade.getText()) * produto.getValorVenda());
								if (produto.getQuantidade() >= Integer.valueOf(jtfQuantidade.getText())) {
									Integer quantidade = produto.getQuantidade()
											- Integer.valueOf(jtfQuantidade.getText());
									produto.setQuantidade(quantidade);

									bancoDeDados.store(produto);
									if (jrbPrazo.isSelected()) {
										conta.setValor(venda.getValorTotal());
										conta.setDataEmissao(venda.getData());
										conta.setCodigo(venda.getCodigo());
										String dataRecebida1 = jtfDataVencimento.getText();
										DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
										try {
											Date dt1 = df1.parse(dataRecebida1);
											conta.setDataVencimento(dt1);
										} catch (ParseException e) {
											e.printStackTrace();
										}

										for (Cliente cliente : clientes) {
											if (cliente.getNome().equals(String.valueOf(jcbCliente.getSelectedItem()))) {
												conta.setCliente(cliente);
											}

										}
										bancoDeDados.store(conta);
									}
									bancoDeDados.store(venda);
									bancoDeDados.close();
									JOptionPane.showMessageDialog(null, "Venda Cadastrada Com Sucesso");
									dispose();
									new CadastrarVendas();
								} else if (produto.getQuantidade() < Integer.valueOf(jtfQuantidade.getText())) {
									JOptionPane.showMessageDialog(null, "Quantidade no estoque insuficiente");
									JOptionPane.showMessageDialog(null,
											"Quantidade Existente:" + produto.getQuantidade());
								}
							}
						}
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Este código já esta sendo utilizado por outra venda");
						aux = 0;
					}
				}
			}
		});

		jbtCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Calendario(jtfDataVencimento);
			}
		});

		jbtCalendario1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				new Calendario(jtfData);

			}
		});

		jbtFechar.addActionListener(new ActionListener() {
			Integer opcao = 0;

			public void actionPerformed(ActionEvent arg0) {
				if (!jtfCodigo.getText().isEmpty() || !jtfData.getText().isEmpty()
						|| !jtfQuantidade.getText().isEmpty()) {
					opcao = JOptionPane.showConfirmDialog(null, "Existem informações Não salvas, deseja mesmo sair?");
				}
				if (opcao == 0) {
					bancoDeDados.close();
					dispose();
					new CadastrarVendas();
				}
			}
		});
		setSize(370, 380);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setVisible(true);
	}
}