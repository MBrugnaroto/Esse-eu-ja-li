package src.sistema;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.bancoDeDados.TratamentoDeArquivos;

public class Pontuacao extends TratamentoDeArquivos{
	private Map<String, Integer> estilos;
	
	public Pontuacao() {
		estilos = new HashMap<>();
		estilos.put("Ficção", 0);
		estilos.put("não-ficção", 0);
		estilos.put("Autoajuda", 0);
		estilos.put("Infantojuvenil", 0);
		estilos.put("Negócios", 0);
	}

	public Integer calculandoPontuacao(String nomeDoLeitor) throws FileNotFoundException, IOException {
		return calculandoTotalDePontos(coletandoDados(nomeDoLeitor));
	}
	
	public List<String> identificandoEstilosDoLeitor(String nomeDoLeitor) throws FileNotFoundException, IOException {
		return verificandoOsEstilosDoLeitor(nomeDoLeitor);
	}
	
	public List<String[]> coletandoDados(String nomeDoLeitor) throws FileNotFoundException, IOException {
		List<String[]> dadosDoLeitor = lendoOArquivo("Registro de Leitura", nomeDoLeitor);
		
		return dadosDoLeitor;
	}

	protected List<String> verificandoOsEstilosDoLeitor(String nomeDoLeitor) throws FileNotFoundException, IOException {
		List<String[]> dadosDoLeitor = coletandoDados(nomeDoLeitor);
		
		for (int i = 0; i < dadosDoLeitor.size(); i++) {
			verificandoEstilo(dadosDoLeitor, i, "Ficção");
			verificandoEstilo(dadosDoLeitor, i, "não-ficção");
			verificandoEstilo(dadosDoLeitor, i, "Autoajuda");
			verificandoEstilo(dadosDoLeitor, i, "Infantojuvenil");
			verificandoEstilo(dadosDoLeitor, i, "Negócios");
		}
		
		return listandoOsTrofeusPorEstilo();
	}
	
	protected List<String> listandoOsTrofeusPorEstilo() {
		List<String> estilosDoLeitor = new ArrayList<>();
		
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Ficção");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "não-ficção");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Negócios");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Autoajuda");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Infantojuvenil");
		
		return estilosDoLeitor;
	}
	
	protected void contandoNumeroDeLivrosDoMesmoEstilo(List<String> estilosDoLeitor, String estilo) {
		if (estilos.get(estilo) >= 5) {
			estilosDoLeitor.add(estilo);
		}
	}

	protected void verificandoEstilo(List<String[]> dadosDoLeitor, int i, String estilo) {
		if (dadosDoLeitor.get(i)[2].equals(estilo)) {
			estilos.put(estilo, estilos.get(estilo)+1);
		}
	}

	protected int calculandoTotalDePontos(List<String[]> dados) {
		int numeroDePaginas = 0;
		int pontuacao = 0;
		
		for (int i = 0; i < dados.size(); i++) {
			numeroDePaginas = Integer.parseInt(dados.get(i)[3]);
			
			if (numeroDePaginas < 100)
				pontuacao = pontuacao + 1;
			else
				pontuacao = pontuacao + (int) Math.floor(numeroDePaginas/100) + 1;
		}
		
		return pontuacao;
	}
}
