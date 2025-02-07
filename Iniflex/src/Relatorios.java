import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Relatorios {
	
	// 3.3 – Imprimir todos os funcionários com todas suas informações
	public static void imprimirListaCompleta(List<Funcionario> funcionarios) {
		System.out.println("========================= LISTA COMPLETA ========================");
		exibirCabecalho();
		funcionarios.forEach(funcionario -> {
			System.out.printf("%-20s %-20s %-15s %-20s\n", funcionario.getNome(), funcionario.getFormattedDate(),
					funcionario.getFormattedSalario(), funcionario.getFuncao());
		});
		System.out.println("-----------------------------------------------------------------");
	}

	// 3.6 - Imprimir os funcionários agrupados por função
	public static void imprimirAgrupadosPorFuncao(List<Funcionario> funcionarios) {
		System.out.println("======================= LISTA POR FUNÇÃO ========================");
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

	// 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
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

	// 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade
	public static void imprimirFuncionarioMaiorIdade(List<Funcionario> funcionarios) {
		System.out.println("================= FUNCIONÁRIO(A) COM MAIOR IDADE =================");
		System.out.printf("%-20s %-20s\n", "Nome", "Idade");
		Funcionario maisIdade = funcionarios.stream().max(Comparator.comparingInt(f -> f.getDataNascimento().getYear()))
				.orElseThrow();
		int idade = calcularIdade(maisIdade.getDataNascimento());
		System.out.printf("%-20s %-20d\n", maisIdade.getNome(), idade);

	}
	
	// 3.10 – Imprimir a lista de funcionários por ordem alfabética.
	public static void imprimirListaAlfabetica(List<Funcionario> funcionarios) {
		System.out.println("================= LISTA POR ONDEM ALFABÉTICA ====================");
		exibirCabecalho();
		funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).forEach(f -> {
			System.out.printf("%-20s %-20s %-15s %-20s\n", f.getNome(), f.getFormattedDate(), f.getFormattedSalario(),
					f.getFuncao());
		});

	}

	// 3.11 - Imprimir o total dos salários dos funcionários
	public static void imprimirTotalSalarios(List<Funcionario> funcionarios) {
		System.out.println("================= TOTAL SALARIO DOS FUNCIONÁRIOS =================");
		BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		System.out.println("Total dos salários: " + totalSalarios);
	}
	
	// 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
	public static void imprimirSalariosMinimos(List<Funcionario> funcionarios) {
	
		System.out.println("================= LISTA POR SALÁRIOS MÍNIMOS ====================");
		System.out.printf("%-20s %-20s\n", "Nome", "Quantidade de Salários mínimos");
		funcionarios.forEach(f -> {
			BigDecimal salarioFuncionario = f.getSalario();
			BigDecimal salarioMinimo = new BigDecimal("1212.00");
			BigDecimal salariosMinimos = salarioFuncionario.divide(salarioMinimo, 2, RoundingMode.HALF_UP);
			System.out.printf("%-20s %-20.2f\n", f.getNome(), salariosMinimos);

		});

	}

	public static void exibirCabecalho() {
		System.out.printf("%-20s %-20s %-15s %-20s\n", "Nome", "Data Nascimento", "Salário", "Função");
		System.out.println("-----------------------------------------------------------------");
	}

	public static int calcularIdade(LocalDate dataNascimento) {
		int anoAtual = LocalDate.now().getYear();
		return anoAtual - dataNascimento.getYear();
	}

}
