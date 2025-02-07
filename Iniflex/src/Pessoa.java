import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Pessoa {
	private String nome;
	private LocalDate dataNascimento;

// Construtor
	public Pessoa(String nome, LocalDate dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

// Getter e Setter para 'nome'
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

// Getter e Setter para 'dataNascimento'
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	 public String getFormattedDate() {
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        return this.dataNascimento.format(formatter);
	    }

}
