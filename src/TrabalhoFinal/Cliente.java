package TrabalhoFinal;

import java.util.ArrayList;
import java.util.Collection;

public class Cliente implements MetodosObrigatorios {
	private Integer codigo;
	private String nome;
	private Long cpf;
	private long rg;
	private long telefone;
	private String email;
	private Endereco endereco;

	private Collection<Conta> contas;

	public Cliente() {

	}

	public Cliente(Integer codigo, String nome, Long cpf, long rg, long telefone, String email, Endereco endereco) {

		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.telefone = telefone;
		this.email = email;
		this.endereco = endereco;

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public long getRg() {
		return rg;
	}

	public void setRg(long rg) {
		this.rg = rg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public long getTelefone() {
		return telefone;
	}

	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}

	public void addConta(ContaAReceber conta) {
		if (this.contas == null) {
			this.contas = new ArrayList<Conta>();
		}
		conta.setCliente(this);
		this.contas.add(conta);
	}

	public void removeConta(Conta conta) {
		if (this.contas != null && !this.contas.isEmpty()) {
			this.contas.remove(conta);
		}
	}

}
