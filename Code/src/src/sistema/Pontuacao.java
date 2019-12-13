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
		List<String[]> dadosDoLeitor = coletandoDados(nomeDoLeitor);
		List<String> estilosDoLeitor = new ArrayList<>();
		
		for (int i = 0; i < dadosDoLeitor.size(); i++) {
			if (dadosDoLeitor.get(i)[2].equals("Ficção")) {
				estilos.put("Ficção", estilos.get("Ficção")+1);
			}
			if (dadosDoLeitor.get(i)[2].equals("não-ficção")) {
				estilos.put("não-ficção", estilos.get("não-ficção")+1);
			}
			if (dadosDoLeitor.get(i)[2].equals("Autoajuda")) {
				estilos.put("Autoajuda", estilos.get("Autoajuda")+1);
			}
			if (dadosDoLeitor.get(i)[2].equals("Infantojuvenil")) {
				estilos.put("Infantojuvenil", estilos.get("Infantojuvenil")+1);
			}
			if (dadosDoLeitor.get(i)[2].equals("Negócios")) {
				estilos.put("Negócios", estilos.get("Negócios")+1);
			}
		}
		
		if (estilos.get("Ficção") >= 5) {
			estilosDoLeitor.add("Ficção");
		}
		
		if (estilos.get("não-ficção") >= 5) {
			estilosDoLeitor.add("não-ficção");
		}
		
		if (estilos.get("Negócios") >= 5) {
			estilosDoLeitor.add("Negócios");
		}
		
		if (estilos.get("Autoajuda") >= 5) {
			estilosDoLeitor.add("Autoajuda");
		}
		
		if (estilos.get("Infantojuvenil") >= 5) {
			estilosDoLeitor.add("Infantojuvenil");
		}
		
		return estilosDoLeitor;
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

	public List<String[]> coletandoDados(String nomeDoLeitor) throws FileNotFoundException, IOException {
		List<String[]> dadosDoLeitor = lendoOArquivo("Registro de Leitura", nomeDoLeitor);
		
		return dadosDoLeitor;
	}
}
