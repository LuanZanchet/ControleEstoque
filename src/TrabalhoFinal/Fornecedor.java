package TrabalhoFinal;

import java.util.ArrayList;
import java.util.Collection;

public class Fornecedor implements MetodosObrigatorios {
	private Integer codigo;
	private String nome;
	private Long cnpj;
	private String email;
	private Long telefone;
	private Endereco endereco;
	private Collection<Conta> contaAReceber;

	public Fornecedor() {

	}

	public Fornecedor(Integer codigo, String nome, Long cnpj, String email, Long telefone, Endereco endereco) {

		this.codigo = codigo;
		this.nome = nome;
		this.cnpj = cnpj;
		this.email = email;
		this.telefone = telefone;
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

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getTelefone() {
		return telefone;
	}

	public void setTelefone(Long telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void addConta(Conta conta) {
		if (this.contaAReceber == null) {
			this.contaAReceber = new ArrayList();
		}
		this.contaAReceber.add(conta);
	}

	public void removeConta(Conta conta) {
		if (this.contaAReceber != null && !this.contaAReceber.isEmpty()) {
			this.contaAReceber.remove(conta);
		}
	}

}
