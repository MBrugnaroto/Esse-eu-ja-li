package src.sistema;

import java.io.IOException;

import src.bancoDeDados.BancoDeLivros;
import src.bancoDeDados.TratamentoDeArquivos;

public class Leitura extends TratamentoDeArquivos{

	public String livroLido(String NomeDoLivro, String nomeDoLeitor) throws IOException {
		BancoDeLivros BL = new BancoDeLivros();
		String[] dadosDolivro = BL.getLivro(NomeDoLivro);
		
		if (dadosDolivro == null) {
			return "Esse livro nao existe em nosso banco";
		}
		
		adicionandoLivroNoArquivo(dadosDolivro, nomeDoLeitor);
		
		return "Adicionado no Banco";
	}

}
