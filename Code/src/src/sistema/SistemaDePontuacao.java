package src.sistema;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import src.bancoDeDados.TratamentoDeArquivos;

public class SistemaDePontuacao extends TratamentoDeArquivos{
	private Map<String, Integer> estilos;
	
	public SistemaDePontuacao() {
		estilos = new HashMap<>();
	}

	public Integer calculandoPontuacao(String nomeDoLeitor) throws FileNotFoundException, IOException {
		estilos.put("Ficção", 0);
		estilos.put("não-ficção", 0);
		estilos.put("Autoajuda", 0);
		estilos.put("Infantojuvenil", 0);
		estilos.put("Negócios", 0);
		return calculandoTotalDePontos(coletandoDadosDoUsuario(nomeDoLeitor));
	}
	
	public List<String> identificandoEstilosDoLeitor(String nomeDoLeitor) throws FileNotFoundException, IOException {
		return verificandoOsEstilosDoLeitor(nomeDoLeitor);
	}

	public List<String[]> coletandoDadosDoUsuario(String nomeDoLeitor) throws FileNotFoundException, IOException {
		return lendoOArquivo("Registro de Leitura", nomeDoLeitor);
	}
	
	public List<String> rankingDosPontuadores() throws FileNotFoundException, IOException {
		return listandoOsPontuadores(coletandoTodosOsDados());
	}
	
	private List<String[]> coletandoTodosOsDados() throws FileNotFoundException, IOException {
		return lendoOArquivo("Registro de Leitura");
	}
	
	private ArrayList<String> listandoOsPontuadores(List<String[]> registroDePontuacoes) throws FileNotFoundException, IOException {
		Map<String, Integer> listaDePontuadores = new HashMap<String, Integer>();
		
		if (registroDePontuacoes == null) return null;
		else {
			for (String[] registro : registroDePontuacoes) { listaDePontuadores.put(registro[4], calculandoPontuacao(registro[4])); }
		}
		
		return ordenandoDescrescente(listaDePontuadores);
	}

	private ArrayList<String> ordenandoDescrescente(Map<String, Integer> listaDePontuadores) {
		ArrayList<String> lista = new ArrayList<>();
		
		if (listaDePontuadores == null) return null;
		else {
			Map<String, Integer> sortedMap = sortByValue(listaDePontuadores);
		    int i = 1;
			
		    for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
		    	lista.add("Posicao: "+i+" -> Nome: "+entry.getKey()+" | "+"Pontuacao: "+entry.getValue());
		    	i++;
		    	if (i == 11) break;
		    }
		}
	    return lista;
	}

	private List<String> verificandoOsEstilosDoLeitor(String nomeDoLeitor) throws FileNotFoundException, IOException {
		List<String[]> dadosDoLeitor = coletandoDadosDoUsuario(nomeDoLeitor);
		
		if (dadosDoLeitor == null) return null;
		else {
			for (int i = 0; i < dadosDoLeitor.size(); i++) {
				verificandoEstilo(dadosDoLeitor, i, "Ficção");
				verificandoEstilo(dadosDoLeitor, i, "não-ficção");
				verificandoEstilo(dadosDoLeitor, i, "Autoajuda");
				verificandoEstilo(dadosDoLeitor, i, "Infantojuvenil");
				verificandoEstilo(dadosDoLeitor, i, "Negócios");
			}
		}
		
		return listandoOsTrofeusPorEstilo();
	}
	
	private List<String> listandoOsTrofeusPorEstilo() {
		List<String> estilosDoLeitor = new ArrayList<>();
		
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Ficção");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "não-ficção");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Negócios");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Autoajuda");
		contandoNumeroDeLivrosDoMesmoEstilo(estilosDoLeitor, "Infantojuvenil");
		
		return estilosDoLeitor;
	}
	
	private void contandoNumeroDeLivrosDoMesmoEstilo(List<String> estilosDoLeitor, String estilo) {
		if (estilos.get(estilo) >= 5) {
			estilosDoLeitor.add(estilo);
		}
	}

	private void verificandoEstilo(List<String[]> dadosDoLeitor, int i, String estilo) {
		if (dadosDoLeitor.get(i)[2].equals(estilo)) {
			estilos.put(estilo, estilos.get(estilo)+1);
		}
	}

	private int calculandoTotalDePontos(List<String[]> dados) {
		int numeroDePaginas = 0;
		int pontuacao = 0;
		
		if (dados == null) return 0;
		else {
			for (int i = 0; i < dados.size(); i++) {
				numeroDePaginas = Integer.parseInt(dados.get(i)[3]);
				if (numeroDePaginas < 100) pontuacao = pontuacao + 1;
				else pontuacao = pontuacao + (int) Math.floor(numeroDePaginas/100) + 1;
				}
		}
		return pontuacao;
	}
	
	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> unsortMap) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(unsortMap.entrySet());
        
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) { return (o2.getValue()).compareTo(o1.getValue()); }
        });

        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) { result.put(entry.getKey(), entry.getValue()); }

        return result;
	}
}
