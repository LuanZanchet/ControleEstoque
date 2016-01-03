package TrabalhoFinal;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;

public class VisualizarCliente extends JFrame {

	private JLabel jlbCodigo = new JLabel("Codigo");
	private JLabel jlbNome = new JLabel("Nome");
	private JLabel jlbCpf = new JLabel("Cpf");
	private JLabel jlbRg = new JLabel("Rg");
	private JLabel jlbEndereco = new JLabel("Rua");
	private JLabel jlbNumero = new JLabel("Numero");
	private JLabel jlbCidade = new JLabel("Cidade");
	private JLabel JlbEstado = new JLabel("UF");
	private JLabel jlbTelefone = new JLabel("Telefone");
	private JLabel jlbEmail = new JLabel("Email");
	private JLabel jlbObs = new JLabel("Obs:");
	private JTextField jtfCodigo = new JTextField();
	private JTextField jtfNome = new JTextField();
	private JTextField jtfCpf = new JTextField();
	private JTextField jtfRg = new JTextField();
	private JTextField jtfEndereco = new JTextField();
	private JTextField jtfNumero = new JTextField();
	private JTextField jtfCidade = new JTextField();
	private JTextField jtfEstado = new JTextField();
	private JTextField jtfTelefone = new JTextField();
	private JTextField jtfEmail = new JTextField();
	private JTextField jtfObs = new JTextField();
	private JButton jbtFechar = new JButton("Fechar");
	private File MostrarCliente = new File("MostrarCliente.txt");
	private Vector<String> vcrCliente = new Vector<>();
	int opcao = 0, aux = 1, posicao = 0;
	private ObjectContainer bancoDeDados = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(), "banco.db4o");
	ObjectSet<Cliente> clientes = bancoDeDados.query(Cliente.class);

	private void posicionaObjeto(JComponent obj, int x, int y, int w, int h) {
		obj.setBounds(x, y, w, h);
		getContentPane().add(obj);
	}

	public void setValor1(int a) {

		for (Integer i = 0; i < clientes.size(); i++) {

			if (i.equals(a)) {
				jtfCodigo.setText(String.valueOf(clientes.get(i).getCodigo()));
				jtfNome.setText(clientes.get(i).getNome());
				jtfCidade.setText(clientes.get(i).getEndereco().getCidade());
				jtfCpf.setText(String.valueOf(clientes.get(i).getCpf()));
				jtfEmail.setText(clientes.get(i).getEmail());
				jtfEndereco.setText(clientes.get(i).getEndereco().getRua());
				jtfEstado.setText(String.valueOf(clientes.get(i).getEndereco().getUf()));
				jtfNumero.setText(String.valueOf(clientes.get(i).getEndereco().getNumero()));
				jtfRg.setText(String.valueOf(clientes.get(i).getRg()));
				jtfTelefone.setText(String.valueOf(clientes.get(i).getTelefone()));
			}
		}

		jtfCodigo.setEditable(false);
		jtfNome.setEditable(false);
		jtfCpf.setEditable(false);
		jtfRg.setEditable(false);
		jtfEndereco.setEditable(false);
		jtfNumero.setEditable(false);
		jtfCidade.setEditable(false);
		jtfEstado.setEditable(false);
		jtfTelefone.setEditable(false);
		jtfEmail.setEditable(false);
		jtfObs.setEditable(false);

	}

	VisualizarCliente() {
		setTitle("Financeiro");
		setLayout(null);

		posicionaObjeto(jlbCodigo, 100, 10, 80, 25);
		posicionaObjeto(jtfCodigo, 150, 10, 170, 25);
		posicionaObjeto(jlbNome, 105, 40, 80, 25);
		posicionaObjeto(jtfNome, 150, 40, 170, 25);
		posicionaObjeto(jlbCpf, 120, 70, 80, 25);
		posicionaObjeto(jtfCpf, 150, 70, 170, 25);
		posicionaObjeto(jlbRg, 125, 100, 80, 25);
		posicionaObjeto(jtfRg, 150, 100, 170, 25);
		posicionaObjeto(jlbEndereco, 120, 130, 80, 25);
		posicionaObjeto(jtfEndereco, 150, 130, 170, 25);
		posicionaObjeto(jlbNumero, 100, 160, 80, 25);
		posicionaObjeto(jtfNumero, 150, 160, 170, 25);
		posicionaObjeto(jlbCidade, 105, 190, 80, 25);
		posicionaObjeto(jtfCidade, 150, 190, 170, 25);
		posicionaObjeto(JlbEstado, 130, 220, 80, 25);
		posicionaObjeto(jtfEstado, 150, 220, 170, 25);
		posicionaObjeto(jlbTelefone, 95, 250, 80, 25);
		posicionaObjeto(jtfTelefone, 150, 250, 170, 25);
		posicionaObjeto(jlbEmail, 110, 290, 80, 25);
		posicionaObjeto(jtfEmail, 150, 290, 170, 25);

		posicionaObjeto(jbtFechar, 220, 340, 100, 25);

		jbtFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bancoDeDados.close();
				dispose();
				new CadastrarCliente();

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
