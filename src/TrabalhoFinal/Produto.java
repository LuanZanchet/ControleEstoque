package TrabalhoFinal;

public class Produto {
	private Integer codigo;
	private String nome;
	private Integer quantidade;
	private Double valorCompra;
	private Double valorVenda;
	private Double lucro;

	public Produto() {

	}

	public Produto(Integer codigo, String nome, Integer quantidade, Double valorCompra, Double valorVenda, Double lucro) {
		this.codigo = codigo;
		this.nome = nome;
		this.quantidade = quantidade;
		this.valorCompra = valorCompra;
		this.valorVenda = valorVenda;
		this.lucro = lucro;
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

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Double getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(Double valorVenda) {
		this.valorVenda = valorVenda;
	}

	public Double getLucro() {
		return lucro;
	}

	public void setLucro(Double lucro) {
		this.lucro = lucro;
	}

	public Double ValorTotalCompra() {
		return this.quantidade * this.valorCompra;
	}

	public Double ValorTotalVenda() {
		return this.quantidade * this.valorVenda;
	}

	public Double Lucro() {
		return (this.valorVenda - this.valorCompra) * 100 / this.valorCompra;

	}

}