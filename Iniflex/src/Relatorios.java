import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Relatorios {
	
	// 3.3 � Imprimir todos os funcion�rios com todas suas informa��es
	public static void imprimirListaCompleta(List<Funcionario> funcionarios) {
		System.out.println("========================= LISTA COMPLETA ========================");
		exibirCabecalho();
		funcionarios.forEach(funcionario -> {
			System.out.printf("%-20s %-20s %-15s %-20s\n", funcionario.getNome(), funcionario.getFormattedDate(),
					funcionario.getFormattedSalario(), funcionario.getFuncao());
		});
		System.out.println("-----------------------------------------------------------------");
	}

	// 3.6 - Imprimir os funcion�rios agrupados por fun��o
	public static void imprimirAgrupadosPorFuncao(List<Funcionario> funcionarios) {
		System.out.println("======================= LISTA POR FUN��O ========================");
		Map<String, List<Funcionario>> agrupadosPorFuncao = funcionarios.stream()
				.collect(Collectors.groupingBy(Funcionario::getFuncao));
		exibirCabecalho();
		agrupadosPorFuncao.forEach((funcao, lista) -> {
			lista.forEach(f -> {
				System.out.printf("%-20s %-20s %-15s %-20s\n", f.getNome(), f.getFormattedDate(),
						f.getFormattedSalario(), f.getFuncao());
			});

		});
	}

	// 3.8 � Imprimir os funcion�rios que fazem anivers�rio no m�s 10 e 12.
	public static void imprimirAniversariantes(List<Funcionario> funcionarios) {
		System.out.println("============ LISTA ANIVERSARIANTES DOS MESES 10 E 12 ============");
		exibirCabecalho();
		funcionarios.stream()
				.filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12)
				.forEach(f -> {
					System.out.printf("%-20s %-20s %-15s %-20s\n", f.getNome(), f.getFormattedDate(),
							f.getFormattedSalario(), f.getFuncao());
				});

	}

	// 3.9 � Imprimir o funcion�rio com a maior idade, exibir os atributos: nome e idade
	public static void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
		System.out.println("================= FUNCION�RIO(A) COM MAIOR IDADE =================");
		System.out.printf("%-20s %-20s\n", "Nome", "Idade");
		Funcionario maisIdade = funcionarios.stream().max(Comparator.comparingInt(f -> f.getDataNascimento().getYear()))
				.orElseThrow();
		int idade = calcularIdade(maisIdade.getDataNascimento());
		System.out.printf("%-20s %-20d\n", maisIdade.getNome(), idade);

	}
	
	// 3.10 � Imprimir a lista de funcion�rios por ordem alfab�tica.
	public static void imprimirListaAlfabetica(List<Funcionario> funcionarios) {
		System.out.println("================= LISTA POR ONDEM ALFAB�TICA ====================");
		exibirCabecalho();
		funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(f -> {
			System.out.printf("%-20s %-20s %-15s %-20s\n", f.getNome(), f.getFormattedDate(), f.getFormattedSalario(),
					f.getFuncao());
		});

	}

	// 3.11 - Imprimir o total dos sal�rios dos funcion�rios
	public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
		System.out.println("================= TOTAL SALARIO DOS FUNCION�RIOS =================");
		BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		System.out.println("Total dos sal�rios: " + totalSalarios);
	}
	
	// 3.12 - Imprimir quantos sal�rios m�nimos ganha cada funcion�rio
	public static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
	
		System.out.println("================= LISTA POR SAL�RIOS M�NIMOS ====================");
		System.out.printf("%-20s %-20s\n", "Nome", "Quantidade de Sal�rios m�nimos");
		funcionarios.forEach(f -> {
			BigDecimal salarioFuncionario = f.getSalario();
			BigDecimal salarioMinimo = new BigDecimal("1212.00");
			BigDecimal salariosMinimos = salarioFuncionario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
			System.out.printf("%-20s %-20.2f\n", f.getNome(), salariosMinimos);

		});

	}

	public static void exibirCabecalho() {
		System.out.printf("%-20s %-20s %-15s %-20s\n", "Nome", "Data Nascimento", "Sal�rio", "Fun��o");
		System.out.println("-----------------------------------------------------------------");
	}

	public static int calcularIdade(LocalDate dataNascimento) {
		int anoAtual = LocalDate.now().getYear();
		return anoAtual - dataNascimento.getYear();
	}

}
