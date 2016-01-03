package TrabalhoFinal;

import java.util.Date;

public class ContaAReceber extends Conta {
	private Cliente cliente;

	public ContaAReceber() {

	}

	public ContaAReceber(Double valor, Date dataEmissao, Date dataVencimento) {
		this.setValor(valor);
		this.setDataEmissao(dataEmissao);
		this.setDataVencimento(dataVencimento);

	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}