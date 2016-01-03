package TrabalhoFinal;

public class Usuario implements MetodosObrigatorios {
	private Integer codigo;
	private String nome;
	private String senha;
	private String palavraSecreta;

	public Usuario() {

	}

	public Usuario(Integer codigo, String nome, String senha, String palavraSecreta) {

		this.codigo = codigo;
		this.nome = nome;
		this.senha = senha;
		this.palavraSecreta = palavraSecreta;
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getPalavraSecreta() {
		return palavraSecreta;
	}

	public void setPalavraSecreta(String palavraSecreta) {
		this.palavraSecreta = palavraSecreta;
	}

}
