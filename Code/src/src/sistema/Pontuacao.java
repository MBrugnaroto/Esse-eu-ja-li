package src.sistema;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import src.bancoDeDados.TratamentoDeArquivos;

public class Pontuacao extends TratamentoDeArquivos{

	public Integer calculandoPontuacao(String nomeDoLeitor) throws FileNotFoundException, IOException {
		return calculandoTotalDePontos(coletandoDados(nomeDoLeitor));
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
