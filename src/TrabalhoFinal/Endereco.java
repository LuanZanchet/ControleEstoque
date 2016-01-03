package TrabalhoFinal;

public class Endereco {
	private String rua;
	private String cidade;
	private Integer numero;
	private UF uf;

	public Endereco() {

	}

	public Endereco(String rua, String cidade, Integer numero, UF uf) {

		this.rua = rua;
		this.cidade = cidade;
		this.numero = numero;
		this.uf = uf;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public UF getUf() {
		return uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

}
