package TrabalhoFinal;

import java.util.Date;

public class Venda {
	private Integer codigo;
	private Produto produto;
	private Integer quantidade;
	private Double valorTotal;
	private Date data;
	private Cliente cliente;

	public Venda() {
	}

	public Venda(Integer codigo, Produto produto, Integer quantidade, Double valorTotal, Date data, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.produto = produto;
		this.quantidade = quantidade;
		this.valorTotal = valorTotal;
		this.data = data;
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

}
