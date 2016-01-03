package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class NovoFornecedor extends JFrame {

	private JLabel jlbCodigo = new JLabel("Codigo");
	private JLabel jlbNome = new JLabel("Nome");
	private JLabel jlbCpnj = new JLabel("CPNJ");
	private JLabel jlbEndereco = new JLabel("Rua");
	private JLabel jlbNumero = new JLabel("Numero");
	private JLabel jlbCidade = new JLabel("Cidade");
	private JLabel JlbUf = new JLabel("UF");
	private JLabel jlbTelefone = new JLabel("Telefone");
	private JLabel jlbEmail = new JLabel("Email");
	private JLabel jlbObs = new JLabel("Obs:");
	private JTextField jtfCodigo = new JTextField();
	private JTextField jtfNome = new JTextField();
	private JTextField jtfCpnj = new JTextField();
	private JTextField jtfEndereco = new JTextField();
	private JTextField jtfNumero = new JTextField();
	private JTextField jtfCidade = new JTextField();
	private JTextField jtfTelefone = new JTextField();
	private JTextField jtfEmail = new JTextField();
	private JTextField jtfObs = new JTextField();
	private JButton jbtCadastrar = new JButton("Cadastrar");
	private JButton jbtFechar = new JButton("Fechar");
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	private JComboBox<UF> jcbEstado = new JComboBox<>();
	int opcao = 0;
	private ObjectSet<Fornecedor> fornecedores = bancoDeDados.query(Fornecedor.class);

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	NovoFornecedor() {
		setTitle("Fornecedores");
		setLayout(null);

		posicionaObjeto(jlbCodigo, 100, 10, 80, 25);
		posicionaObjeto(jtfCodigo, 150, 10, 170, 25);
		posicionaObjeto(jlbNome, 105, 40, 80, 25);
		posicionaObjeto(jtfNome, 150, 40, 170, 25);
		posicionaObjeto(jlbCpnj, 115, 70, 80, 25);
		posicionaObjeto(jtfCpnj, 150, 70, 170, 25);
		posicionaObjeto(jlbEndereco, 120, 100, 80, 25);
		posicionaObjeto(jtfEndereco, 150, 100, 170, 25);
		posicionaObjeto(jlbNumero, 100, 130, 80, 25);
		posicionaObjeto(jtfNumero, 150, 130, 170, 25);
		posicionaObjeto(jlbCidade, 105, 160, 80, 25);
		posicionaObjeto(jtfCidade, 150, 160, 170, 25);
		posicionaObjeto(JlbUf, 130, 190, 80, 25);
		posicionaObjeto(jcbEstado, 150, 190, 170, 25);
		posicionaObjeto(jlbTelefone, 95, 220, 80, 25);
		posicionaObjeto(jtfTelefone, 150, 220, 170, 25);
		posicionaObjeto(jlbEmail, 110, 250, 80, 25);
		posicionaObjeto(jtfEmail, 150, 250, 170, 25);
		posicionaObjeto(jbtCadastrar, 40, 330, 120, 40);
		posicionaObjeto(jbtFechar, 220, 340, 100, 25);
		jcbEstado.addItem(UF.AC);
		jcbEstado.addItem(UF.AL);
		jcbEstado.addItem(UF.AM);
		jcbEstado.addItem(UF.AP);
		jcbEstado.addItem(UF.BA);
		jcbEstado.addItem(UF.CE);
		jcbEstado.addItem(UF.DF);
		jcbEstado.addItem(UF.ES);
		jcbEstado.addItem(UF.GO);
		jcbEstado.addItem(UF.MA);
		jcbEstado.addItem(UF.MG);
		jcbEstado.addItem(UF.MS);
		jcbEstado.addItem(UF.MT);
		jcbEstado.addItem(UF.PA);
		jcbEstado.addItem(UF.PB);
		jcbEstado.addItem(UF.PE);
		jcbEstado.addItem(UF.PI);
		jcbEstado.addItem(UF.PR);
		jcbEstado.addItem(UF.RJ);
		jcbEstado.addItem(UF.RN);
		jcbEstado.addItem(UF.RO);
		jcbEstado.addItem(UF.RR);
		jcbEstado.addItem(UF.RS);
		jcbEstado.addItem(UF.SC);
		jcbEstado.addItem(UF.SE);
		jcbEstado.addItem(UF.SP);
		jcbEstado.addItem(UF.TO);

		jbtCadastrar.addActionListener(new ActionListener() {
			Integer aux = 0;

			public void actionPerformed(ActionEvent arg0) {
				if (jtfCodigo.getText().isEmpty() || jtfNome.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Código/Nome do fornecedor obrigatória");
				} else {

					for (Fornecedor fornecedor : fornecedores) {
						if (fornecedor.getCodigo().equals(Integer.valueOf(jtfCodigo.getText()))) {
							aux = 1;
						}
					}

					if (aux == 0) {
						Endereco endereco = new Endereco();
						Fornecedor fornecedor = new Fornecedor();
						fornecedor.setCodigo(Integer.parseInt(jtfCodigo.getText()));
						fornecedor.setNome(jtfNome.getText());
						fornecedor.setEmail(jtfEmail.getText());
						if (!jtfCpnj.getText().isEmpty()) {
							fornecedor.setCnpj(Long.parseLong(jtfCpnj.getText()));
						}
						if (!jtfTelefone.getText().isEmpty()) {
							fornecedor.setTelefone(Long.parseLong(jtfTelefone.getText()));
						}
						endereco.setRua(jtfEndereco.getText());
						if (!jtfNumero.getText().isEmpty()) {
							endereco.setNumero(Integer.parseInt(jtfNumero.getText()));
						}
						endereco.setCidade(jtfCidade.getText());
						endereco.setUf((UF) jcbEstado.getSelectedItem());

						fornecedor.setEndereco(endereco);
						bancoDeDados.store(fornecedor);
						JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!");
						bancoDeDados.close();
						dispose();
						new CadastrarFornecedor();
					}
				}
				if (aux == 1) {
					JOptionPane.showMessageDialog(null, "Este código já está sendo usado por outro fornecedor");
					aux = 0;
				}
			}
		});
		jbtFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bancoDeDados.close();
				dispose();
				new CadastrarFornecedor();

			}
		});

		setSize(410, 410);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setResizable(false);
		this.getContentPane().setBackground(Color.white);
		setVisible(true);

	}

}