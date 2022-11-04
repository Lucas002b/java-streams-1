package JavaStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class Carrinho {

	private static List<Produto>listaProduto= new ArrayList<Produto	>(); 
	static {
		listaProduto.add(new Produto(744,"Redragon Kurama",243.35,"Tecnologia",true));
		listaProduto.add(new Produto(850,"Redragon Cobra",190.20,"Tecnologia",true));
		listaProduto.add(new Produto(022,"SSD M.2",102.02,"Tecnologia",true));
		listaProduto.add(new Produto(848,"Monitor 24p 165hz",1153.00,"Tecnologia",false));
		listaProduto.add(new Produto(254,"Oculos de ciclismo",104.02,"Esporte",true));
		listaProduto.add(new Produto(78,"Molinete de pesca",175.00,"Esporte",false));
		listaProduto.add(new Produto(415,"Tenis allstar	",167.00,"Vestuario",true));
		listaProduto.add(new Produto(403,"Luva de motociclista",134.00,"Vestuario",false));
		listaProduto.add(new Produto(625,"Chave de fenda magnetica",55.00,"Equipamentos",true));
		listaProduto.add(new Produto(573,"Tapete geometrico",114.00,"Lazer",true));
	}

	public static void main(String[] args) {
		mostraTitulo("Stream");

		filtroCategoria();
		filtroValor();
		filtroEstoque();
		primeiroEquipamento();
		somaValor();
		grupPcategoria();
		Ordem();
		catCara();
		tresIds();
	}


	//MOSTRA APENAS A CATEGORIA TECNOLOGIA
	private static void filtroCategoria() {
		mostraTitulo("filtroCategoria");
		List<String> resultado = listaProduto.stream()
				.filter(p -> p.getCategoria() == "Tecnologia")
				.map(Produto::getNome)
				.collect(Collectors.toList());

		System.out.println(resultado);
	}

	//MOSTRA APENAS PRODUTOS COM O VALOR MAIOR QUE 200
	private static void filtroValor() {
		mostraTitulo("filtroValor");
		List<String> resultado = listaProduto.stream()
				.filter(p -> p.getValor() > 200.00)
				.filter(p->p.gettemEstoque() == true)
				.map(Produto::getNome)
				.collect(Collectors.toList());

		System.out.println(resultado);
	}

	//MOSTRA APENAS OS PRODUTOS EM ESTOQUE
	private static void filtroEstoque() {
		mostraTitulo("filtroEstoque");
		List<String> resultado = listaProduto.stream()
				.filter(p -> p.gettemEstoque() == true)
				.map(Produto::getNome)
				.collect(Collectors.toList());

		System.out.println(resultado);
	}

	//MOSTRA O PRIMEIRO PRODUTO DA CATEGORIA EQUIPAMENTO
	private static void primeiroEquipamento() {
		mostraTitulo("primeiroEquipamento");
		Produto firstEquip = listaProduto.stream()
				.filter(p -> p.getCategoria()== "Equipamentos")
				.findFirst()
				.orElse(null);
		System.out.println(firstEquip);
	}

	//SOMA O VALOR DE TODOS OS PRODUTOS DA CATEGORIA ESPORTE
	private static void somaValor() {
		mostraTitulo("somaValor");
		Double somado = listaProduto.stream()
				.filter(p->p.getCategoria() == "Esporte")
				.map(Produto::getValor)
				.reduce((double) 0, (accumulator, next) -> accumulator + next);
		System.out.println(somado);
	}

	//MOSTRA EM SUAS DEVIDAS CATEGORIAS
	private static void grupPcategoria() {
		mostraTitulo("gupPcategoria");
		Map<String, List<Produto>> prodCat = listaProduto.stream().collect(Collectors.groupingBy(Produto::getCategoria));
		System.out.println(prodCat);
	}

	//MOSTRA OS PRODUTOS EM ORDEM ALFABETICA
	private static void Ordem() {
		mostraTitulo("Ordenar");
		listaProduto.stream()
		.sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
		.forEach(p -> System.out.println(p));
	}

	//MOSTRA A CATEGORIAC COM O VALOR SOMADO MAIS CARO
	private static void catCara() {
		mostraTitulo("Categoria Cara");
		Entry<String, Double> Cara = listaProduto.stream()
				.collect(Collectors.groupingBy(Produto::getCategoria, 
						Collectors.summingDouble(Produto::getValor)))
				.entrySet()
				.stream()
				.max(Comparator.comparing(Map.Entry::getValue))
				.orElse(null);

		System.out.println(Cara.getKey() + " - " + Cara.getValue());
	}

	//MOSTRA APENAS TRES IDS ESPECIFICOS
	private static void tresIds() {
		mostraTitulo("Ids");

		List<Integer> idEsp = Arrays.asList(850, 403, 625);
		listaProduto.stream()
		.filter(p -> idEsp.contains(p.getId()))
		.toList();

		System.out.println(idEsp);
	}

	//FUNCAO DE TITULO
	private static void mostraTitulo(String titulo) {
		int width = 30;
		String token = "=";
		int space = (((titulo.length() + 2) - width) / 2) * -1;
		String titleFull = token + " ".repeat(space) + titulo + " ".repeat(space) + token;

		System.out.println("\n");
		System.out.println(token.repeat(width));
		System.out.println(titleFull);
		System.out.println(token.repeat(width));
	}
}



