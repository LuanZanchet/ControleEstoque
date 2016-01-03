package TrabalhoFinal;

import java.util.Date;

public class ContaAPagar extends Conta {
	private Fornecedor fornecedor;

	public ContaAPagar() {

	}

	public ContaAPagar(Double valor, Date dataEmissao, Date dataVencimento, Fornecedor fornecedor) {
		this.setValor(valor);
		this.setDataEmissao(dataEmissao);
		this.setDataVencimento(dataVencimento);
		this.fornecedor = fornecedor;

	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}
}
