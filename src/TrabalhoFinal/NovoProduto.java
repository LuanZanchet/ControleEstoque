package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class NovoProduto extends JFrame {

	private static final long serialVersionUID = -2312892834344675432L;
	private JLabel jlbCodigo = new JLabel("Codigo");
	private JLabel jlbNome = new JLabel("Nome");
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	private JLabel jlbValorCompra = new JLabel("Valor Compra");
	private JLabel jlbValorVenda = new JLabel("Valor Venda");
	private JLabel jlbFornecedor = new JLabel("Fornecedor");
	private JLabel jlbDataVencimento = new JLabel("Data de Vencimento");
	private JTextField jtfCodigo = new JTextField();
	private JTextField jtfNome = new JTextField();
	private JTextField jtfQuantidade = new JTextField();
	private JTextField jtfValorCompra = new JTextField();
	private JTextField jtfValorVenda = new JTextField();
	public JTextField jtfDataDeVencimento = new JTextField();
	private JComboBox<String> jcbFornecedor = new JComboBox<String>();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtFechar = new JButton("Fechar");
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	private ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
	private JButton jbtCalendario = new JButton(new ImageIcon("calendario.jpg"));

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	NovoProduto() {
		setTitle("Produtos");
		setLayout(null);

		jbtCalendario.setBorderPainted(false);
		posicionaObjeto(jlbCodigo, 100, 10, 80, 25);
		posicionaObjeto(jtfCodigo, 150, 10, 170, 25);
		posicionaObjeto(jlbNome, 105, 40, 80, 25);
		posicionaObjeto(jtfNome, 150, 40, 170, 25);
		posicionaObjeto(jlbQuantidade, 75, 70, 100, 25);
		posicionaObjeto(jtfQuantidade, 150, 70, 170, 25);
		posicionaObjeto(jlbValorCompra, 60, 100, 100, 25);
		posicionaObjeto(jtfValorCompra, 150, 100, 170, 25);
		posicionaObjeto(jlbValorVenda, 70, 130, 100, 25);
		posicionaObjeto(jtfValorVenda, 150, 130, 170, 25);
		posicionaObjeto(jlbFornecedor, 75, 160, 100, 25);
		posicionaObjeto(jcbFornecedor, 150, 160, 170, 25);
		posicionaObjeto(jlbDataVencimento, 25, 190, 150, 25);
		posicionaObjeto(jbtCalendario, 325, 190, 30, 30);
		posicionaObjeto(jtfDataDeVencimento, 150, 190, 170, 23);
		posicionaObjeto(jbtCadastrar, 30, 250, 100, 35);
		posicionaObjeto(jbtFechar, 200, 250, 100, 25);
		jtfDataDeVencimento.setEditable(false);
		ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
		for (Fornecedor fornecedor : fornecedores) {
			jcbFornecedor.addItem(fornecedor.getNome());

		}

		jbtCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (jtfCodigo.getText().isEmpty() || jtfDataDeVencimento.getText().isEmpty()
						|| jtfNome.getText().isEmpty() || jtfQuantidade.getText().isEmpty()
						|| jtfValorCompra.getText().isEmpty() || jtfValorVenda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Todos os Campos são Obrigatórios!");
				} else {
					Integer aux = 0, i = 0, posicao = 0;
					for (Produto produto : produtos) {
						if (produto.getCodigo().equals(Integer.valueOf(jtfCodigo.getText()))
								|| produto.getNome().equals(jtfNome.getText())) {
							aux = 1;
							posicao = i;
						}
						i++;
					}
					if (aux == 0) {
						Produto produto = new Produto();
						ContaAPagar conta = new ContaAPagar();
						Fornecedor fornecedor = new Fornecedor();
						produto.setCodigo(Integer.valueOf(jtfCodigo.getText()));
						produto.setNome(jtfNome.getText());
						produto.setQuantidade(Integer.valueOf(jtfQuantidade.getText()));
						produto.setValorCompra(Double.valueOf(jtfValorCompra.getText()));
						produto.setValorVenda(Double.valueOf(jtfValorVenda.getText()));
						conta.setCodigo(produto.getCodigo());

						if (jtfDataDeVencimento.getText().isEmpty()) {
							conta.setDataVencimento(null);
							conta.setDataEmissao(null);// pegar data do pc

						} else {

							String dataRecebida = jtfDataDeVencimento.getText();
							DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
							try {
								Date dt = df.parse(dataRecebida);
								conta.setDataEmissao(dt);// pegar do pc
								conta.setDataVencimento(dt);
							} catch (ParseException e) {

								e.printStackTrace();
							}
						}
						ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
						for (Fornecedor fornecedor1 : fornecedores) {
							if (fornecedor1.getNome().equals(String.valueOf(jcbFornecedor.getSelectedItem()))) {
								fornecedor = fornecedor1;
							}
						}
						conta.setFornecedor(fornecedor);
						conta.setValor(Double.valueOf(jtfValorCompra.getText())
								* Integer.valueOf(jtfQuantidade.getText()));
						bancoDeDados.store(produto);
						bancoDeDados.store(conta);
						JOptionPane.showMessageDialog(null, "Produto Cadastrado com Sucesso!");
						bancoDeDados.close();
						dispose();
						new CadastrarProduto();
						System.out.println(conta.getCodigo());
					}
					if (aux == 1) {
						JOptionPane.showMessageDialog(null, "Este código/nome já está sendo usado por Outro Produto");
						Integer escolha = JOptionPane.showConfirmDialog(null, "Deseja Alterar este produto?");
						aux = 0;
						if (escolha == 0) {
							bancoDeDados.close();
							dispose();
							AlterarProduto alterar = new AlterarProduto();
							alterar.setValor1(posicao);
						}
					}
				}
			}
		});
		jbtFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer escolha = 0;
				if (!jtfCodigo.getText().isEmpty() || !jtfDataDeVencimento.getText().isEmpty()
						|| !jtfNome.getText().isEmpty() || !jtfQuantidade.getText().isEmpty()
						|| !jtfValorCompra.getText().isEmpty() || !jtfValorVenda.getText().isEmpty()) {
					escolha = JOptionPane.showConfirmDialog(null,
							"Existem informações não salvas, deseja realmente sair? ");
				}

				if (escolha == 0) {

					bancoDeDados.close();
					dispose();
					new CadastrarProduto();
				}
			}
		});

		jbtCalendario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Calendario(jtfDataDeVencimento);
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
