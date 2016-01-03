package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

public class AlterarVenda extends JFrame {
	private JLabel jlbCodigo = new JLabel("Codigo");
	private JLabel jlbProduto = new JLabel("Produto");
	private JComboBox<String> jcbProduto = new JComboBox<>();
	private JLabel jlbQuantidade = new JLabel("Quantidade");
	private JLabel jlbData = new JLabel("Data");
	private JTextField jtfCodigo = new JTextField();
	private JTextField jtfQuantidade = new JTextField();
	private JTextField jtfData = new JTextField();
	private JButton jbtFechar = new JButton("Fechar");
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	private JButton jbtSalvar = new JButton("Salvar");
	private JTextField jtfPosicao = new JTextField();
	private ObjectSet<Venda> vendas = bancoDeDados.query(Venda.class);
	private JLabel jlbCliente = new JLabel("Cliente");
	private ObjectSet<Cliente> clientes = bancoDeDados.query(Cliente.class);
	private JComboBox<String> jcbCliente = new JComboBox<>();

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor1(int a) {
		for (Integer i = 0; i < vendas.size(); i++) {

			if (i.equals(a)) {
				jtfCodigo.setText(String.valueOf(vendas.get(i).getCodigo()));
				jtfQuantidade.setText(String.valueOf(vendas.get(i).getQuantidade()));
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				String data = df.format(vendas.get(i).getData());
				jtfData.setText(data);

				jcbProduto.setSelectedItem(vendas.get(i).getProduto().getNome());
				jtfPosicao.setText(String.valueOf(a));
				jcbCliente.setSelectedItem(vendas.get(i).getCliente().getNome());
			}
		}

	}

	AlterarVenda() {
		setTitle("Vendas");
		setLayout(null);
		posicionaObjeto(jlbCodigo, 100, 10, 80, 25);
		posicionaObjeto(jtfCodigo, 150, 10, 170, 25);
		posicionaObjeto(jlbProduto, 95, 40, 80, 25);
		posicionaObjeto(jcbProduto, 150, 40, 170, 25);
		posicionaObjeto(jlbQuantidade, 75, 70, 100, 25);
		posicionaObjeto(jtfQuantidade, 150, 70, 170, 25);
		posicionaObjeto(jlbData, 110, 100, 100, 25);
		posicionaObjeto(jtfData, 150, 100, 170, 25);
		posicionaObjeto(jlbCliente, 100, 130, 100, 25);
		posicionaObjeto(jcbCliente, 150, 130, 170, 25);
		posicionaObjeto(jbtFechar, 200, 250, 100, 25);
		posicionaObjeto(jbtSalvar, 30, 250, 100, 35);
		jtfData.setEditable(false);
		ObjectSet<Produto> produtos = bancoDeDados.query(Produto.class);

		jcbCliente.addItem(null);
		for (Cliente cliente : clientes) {
			jcbCliente.addItem(cliente.getNome());
		}

		jcbProduto.addItem(null);
		for (Produto produto : produtos) {
			jcbProduto.addItem(produto.getNome());
		}
		jbtFechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				bancoDeDados.close();
				dispose();
				new CadastrarVendas();
			}
		});
		jbtSalvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				Venda venda = new Venda();
				ObjectSet<Venda> vendas = bancoDeDados.query(Venda.class);
				venda = vendas.get(Integer.valueOf(jtfPosicao.getText()));
				venda.setCodigo(Integer.valueOf(jtfCodigo.getText()));
				venda.setQuantidade(Integer.valueOf(jtfQuantidade.getText()));
				String dataRecebida = jtfData.getText();
				DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date dt = df.parse(dataRecebida);
					venda.setData(dt);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				for (Cliente cliente : clientes) {
					if (cliente.getNome().equals(String.valueOf(jcbCliente.getSelectedItem()))) {
						venda.setCliente(cliente);
					}
				}
				bancoDeDados.store(venda);
				bancoDeDados.close();
				JOptionPane.showMessageDialog(null, "Alterações Realizadas Com Sucesso");
				dispose();
				new CadastrarVendas();
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
