package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class AlterarProduto extends JFrame {
	private JTextField auxiliar = new JTextField();
	private JLabel jlbCodigo = new JLabel("Codigo");
	private JLabel jlbNome = new JLabel("Nome");
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	private JLabel jlbValorCompra = new JLabel("Valor Compra");
	private JLabel jlbValorVenda = new JLabel("Valor Venda");
	private JLabel jlbFornecedor = new JLabel("Fornecedor");
	private JLabel jlbDataVencimento = new JLabel("Data de Vencimento");
	private JLabel jlbLucro = new JLabel("Lucro");
	private JLabel jlbValorTotalCompra = new JLabel("Valor total Compra");
	private JLabel jlbValorTotalVenda = new JLabel("Valor total Venda");
	private JTextField jtfCodigo = new JTextField();
	private JTextField jtfNome = new JTextField();
	private JTextField jtfQuantidade = new JTextField();
	private JTextField jtfValorCompra = new JTextField();
	private JTextField jtfValorVenda = new JTextField();
	private JTextField jtfDataDeVencimento = new JTextField();
	private JTextField jtfLucro = new JTextField();
	private JTextField jtfValorTotalCompra = new JTextField();
	private JTextField jtfValorTotalVenda = new JTextField();
	private JComboBox<String> jcbFornecedor = new JComboBox<String>();
	private JButton jbtCadastrar = new JButton("Alterar");
	private JButton jbtFechar = new JButton("Fechar");
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
	ObjectSet<ContaAPagar> contas = bancoDeDados.query(ContaAPagar.class);

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor1(int a) {
		for (Integer i = 0; i < produtos.size(); i++) {

			if (i.equals(a)) {
				jtfCodigo.setText(String.valueOf(produtos.get(i).getCodigo()));
				jtfNome.setText(produtos.get(i).getNome());
				jtfQuantidade.setText(String.valueOf(produtos.get(i).getQuantidade()));
				jtfValorCompra.setText(String.valueOf(produtos.get(i).getValorCompra()));
				jtfValorVenda.setText(String.valueOf(produtos.get(i).getValorVenda()));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String data = df.format(contas.get(i).getDataVencimento());
				jtfDataDeVencimento.setText(data);
				jcbFornecedor.setSelectedItem(contas.get(i).getFornecedor().getNome());
				jtfValorTotalCompra.setText(String.valueOf(produtos.get(i).ValorTotalCompra()));
				jtfValorTotalVenda.setText(String.valueOf(produtos.get(i).ValorTotalVenda()));
				jtfLucro.setText(String.valueOf(produtos.get(i).Lucro()));
				auxiliar.setText(String.valueOf(a));
			}

		}
		jtfDataDeVencimento.setEditable(false);
		jtfValorTotalCompra.setEditable(false);
		jtfValorTotalVenda.setEditable(false);
		jtfLucro.setEditable(false);

	}

	AlterarProduto() {
		setTitle("Produtos");
		setLayout(null);

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
		posicionaObjeto(jtfDataDeVencimento, 150, 190, 170, 23);
		posicionaObjeto(jlbValorTotalCompra, 35, 220, 150, 25);
		posicionaObjeto(jtfValorTotalCompra, 150, 220, 170, 23);
		posicionaObjeto(jlbValorTotalVenda, 45, 250, 170, 25);
		posicionaObjeto(jtfValorTotalVenda, 150, 250, 170, 23);
		posicionaObjeto(jlbLucro, 110, 280, 170, 25);
		posicionaObjeto(jtfLucro, 150, 280, 170, 23);
		posicionaObjeto(jbtCadastrar, 30, 330, 100, 35);
		posicionaObjeto(jbtFechar, 200, 330, 100, 25);
		ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
		jcbFornecedor.addItem(null);
		for (Fornecedor fornecedor : fornecedores) {
			jcbFornecedor.addItem(fornecedor.getNome());

		}

		jbtFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				bancoDeDados.close();
				dispose();
				new CadastrarProduto();

			}
		});
		jbtCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);
				ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);
				ObjectSet<ContaAPagar> contas = bancoDeDados.query(ContaAPagar.class);
				Produto produto1 = new Produto();
				produto1 = produtos.get(Integer.valueOf(auxiliar.getText()));
				produto1.setCodigo(Integer.valueOf(jtfCodigo.getText()));
				produto1.setNome(jtfNome.getText());
				produto1.setQuantidade(Integer.valueOf(jtfQuantidade.getText()));
				for (Fornecedor fornecedor : fornecedores) {
					if (fornecedor.getNome().equals(String.valueOf(jcbFornecedor.getSelectedItem()))) {
						contas.get(Integer.valueOf(auxiliar.getText())).setFornecedor(fornecedor);
					}

				}
				produto1.setValorCompra(Double.valueOf(jtfValorCompra.getText()));
				produto1.setValorVenda(Double.valueOf(jtfValorVenda.getText()));
				bancoDeDados.store(produto1);
				bancoDeDados.store(contas.get(Integer.valueOf(auxiliar.getText())));
				bancoDeDados.close();
				dispose();
				new CadastrarProduto();

			}
		});

		setSize(370, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setVisible(true);

	}

}
