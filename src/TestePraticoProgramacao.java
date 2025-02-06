import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

//declaração da classe pessoa
class pessoa{
	protected String nome;
	protected LocalDate dataNascimento;
	
	public pessoa(String nome, LocalDate dataNascimento){
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}
}


//declaração da classe funcionario
class funcionario extends pessoa{
	private BigDecimal salario;
	private String funcao;

	public funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
		super(nome, dataNascimento);
		// TODO Auto-generated constructor stub
		this.salario = salario;
		this.funcao = funcao;
	}
	
	public String getNome() {return nome;}
	public LocalDate getDataNascimento() {return dataNascimento;}
	public BigDecimal getSalario() {return salario;}
	public String getFuncao() {return funcao;}
	
	
	public void aumentarSalario(BigDecimal percentual) {
		salario = salario.add(salario.multiply(percentual));
	}
	
	
	
	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return nome + " | " + dataNascimento.format(formatter) + " | " + funcao + " | " + String.format("%,.2f", salario);
	}
}




public class TestePraticoProgramacao{
	public static void main(String[] args) {
		//3.1 – Inserir todos os funcionários, na mesma ordem e informações da tabela
		List<funcionario> funcionarios = new ArrayList<>();
		funcionarios.add(new funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        
        //3.2 – Remover o funcionário “João” da lista. --fiz por meio de um laço for que verificar o nome e caso não seja passa ao proximo quando encontra ele remover e informa o mesmo
        for (Iterator<funcionario> iterator = funcionarios.iterator(); iterator.hasNext();) {
        	funcionario funcionario = iterator.next();
        	if (funcionario.getNome().equals("João")) {
        		iterator.remove();
        		System.out.println("Funcionario João Removido");
        	}
        }
        
        
        //3.3 – Imprimir todos os funcionários com todas suas informações seguindo orientações desejadas
        System.out.println("Todos os Funcionarios Atuais na lista: ");
        funcionarios.forEach(System.out::println);
        
        
        //3.4 – Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor
        System.out.println("\nAumentando em 10% todos os salarios");
        for(funcionario f : funcionarios) {
        	f.aumentarSalario(new BigDecimal("0.10"));
        }
        
        
        //3.5 – Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”.
        System.out.println("\nAgrupando os funcionários por função");
        Map<String, List<funcionario>> porFuncao = new HashMap<>();
        for (funcionario f : funcionarios ) {
        	porFuncao.computeIfAbsent(f.getFuncao(), k -> new ArrayList<>()).add(f);
        }
        
        //3.6 – Imprimir os funcionários, agrupados por função.
        for (Map.Entry<String, List<funcionario>> entry : porFuncao.entrySet()) {
        	System.out.println(entry.getKey() + ": " + entry.getValue());
        }
       
        
        //3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12.
        System.out.println("\nFuncionarios que fazem aniversário nos meses de Outubro(10) e Dezembro(12)");
        for (funcionario f: funcionarios) {
        	int mesAniversario = f.getDataNascimento().getMonthValue();
        	if (mesAniversario == 10 || mesAniversario == 12) {
        		System.out.println(f);
        	}
        }
        
        
        //3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade.
        System.out.println("\nFuncionario com a maior idade");
        funcionario maiorIdade = null;
        for (funcionario f: funcionarios) {
        	if (maiorIdade == null || f.getDataNascimento().isBefore(maiorIdade.getDataNascimento())) {
        		maiorIdade = f;
        	}
        }
        
        if (maiorIdade != null) {
        	int idade = LocalDate.now().getYear() - maiorIdade.getDataNascimento().getYear();
        	System.out.println("O funcionário " + maiorIdade.getNome() + " é o funcionário mais velho com " + idade + " anos de idade");
        }
        
        
        
        //3.10 – Imprimir a lista de funcionários por ordem alfabética.
        System.out.println("\nLista de Funcionários em ordem alfabética");
        List<funcionario> ordemAlfabetica = new ArrayList<>(funcionarios);
        ordemAlfabetica.sort(Comparator.comparing(funcionario::getNome));
        
        for(funcionario f: ordemAlfabetica) {
        	System.out.println(f);
        }
        
        
        //3.11 – Imprimir o total dos salários dos funcionários.
        System.out.println("\nTotal da soma dos salários de todos os funcionários");
        BigDecimal totalSalarios = BigDecimal.ZERO;
        
        for (funcionario f: funcionarios) {
        	totalSalarios = totalSalarios.add(f.getSalario());
        }
        
        System.out.println("O total da soma de todos os salários é: R$ " + String.format("%,.2f", totalSalarios));
        
        
        //3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        System.out.println("\nCalculo de em médias quantos salarios minímos cada funcionário ganhar com salário base de R$ 1.212,00");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        
        for(funcionario f: funcionarios) {
        	BigDecimal salarioMinimos = f.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP);
        	System.out.println("O funcionário " + f.getNome() + " ganhar em média de " + salarioMinimos + " salários minimos por mês");
        }
       
	}
}
