import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
	private static List<Funcionario> funcionarios = new ArrayList<>();
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		inicializarFuncionarios();
		executarMenu();
		scan.close();

	}

	// Cadastrando Funcionários
	private static void inicializarFuncionarios() {
		funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
		funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
		funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
		funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
		funcionarios
				.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
		funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
		funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
		funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
		funcionarios
				.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
		funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

	}
	//Executando menu de opções
	private static void executarMenu() {
		int opcao;
		while (true) {
			System.out.println("Escolha uma opção abaixo: \n");
			System.out.println("1 - Imprimir Relatório\n2 - Remover\n3 - Alterar Salário\n4 - Finalizar");
			opcao = scan.nextInt();
			scan.nextLine();

			switch (opcao) {
			case 1:
				imprimirRelatorio();
				break;
			case 2:
				remover();
				break;
			case 3:
				alterar();
				break;
			case 4:
				sair();
				break;
			default:
				System.out.println("Opção inválida.");
			}

		}

	}

	private static void imprimirRelatorio() {
		Relatorios.imprimirListaCompleta(funcionarios);
		Relatorios.imprimirAgrupadosPorFuncao(funcionarios);
		Relatorios.imprimirAniversariantes(funcionarios);
		Relatorios.imprimirFuncionarioMaiorIdade(funcionarios);
		Relatorios.imprimirListaAlfabetica(funcionarios);
		Relatorios.imprimirTotalSalarios(funcionarios);
		Relatorios.imprimirSalariosMinimos(funcionarios);
	}

	private static void alterar() {
		System.out.println("Salarios Reajustados com 10%....\n");
		funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.10))));
		Relatorios.imprimirListaCompleta(funcionarios);;

	}

	private static void remover() {
		Funcionario funcionarioRemovido = funcionarios.stream().filter(f -> f.getNome().equals("João")).findFirst()
				.orElse(null);
		System.out.println("Funcionário Removido: " + funcionarioRemovido.getNome());

	}

	private static void sair() {
		System.out.println("Finalizando o programa...");
	}

}
