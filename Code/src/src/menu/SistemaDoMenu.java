package src.menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import src.bancoDeDados.BancoDeLivros;
import src.sistema.SistemaDeLeitura;
import src.sistema.SistemaDePontuacao;

public class SistemaDoMenu {
	private BancoDeLivros _isBL;
	private SistemaDePontuacao _isSP;
	private SistemaDeLeitura _isSL;
	private String _isUsernameDoLeitor;
	
	public SistemaDoMenu(String usernameDoLeitor) throws FileNotFoundException, IOException {
		_isBL = new BancoDeLivros();
		_isSP = new SistemaDePontuacao();
		_isSL = new SistemaDeLeitura();
		_isUsernameDoLeitor = usernameDoLeitor;
	}
	
	protected void menu () {
		System.out.println("\nMenu");
		System.out.println("1 - Visualizar Perfil");
		System.out.println("2 - Ranking dos Leitores");
	    System.out.println("3 - Lista de Livros");
	    System.out.println("4 - Visualizar Informacoes de um Livro");
	    System.out.println("5 - Marcar um Livro como lido");
		System.out.println("6 - Sair\n");
	}
	
	protected void visualizacaoDoPerfil ( ) throws FileNotFoundException, IOException {
		System.out.println("Username do Leitor: "+_isUsernameDoLeitor);
		System.out.println("Pontuacao do Leitor: "+_isSP.calculandoPontuacao(_isUsernameDoLeitor));
	    System.out.println("Trofeus conquistados: ");
	    
	    List<String> estilosDoleitor = _isSP.identificandoEstilosDoLeitor(_isUsernameDoLeitor);
	    
	    if (estilosDoleitor == null)  System.out.println("--> Nao recebeu nenhum trofeu ainda");
	    else {
	    	for (String estilo : estilosDoleitor) { System.out.println("--> Recebeu o trofeu de Leitor de "+estilo); }
	    }
	    
	    System.out.println("\n1 - Voltar para o Menu");
	}
	
	protected void visualizacaoDoRanking ( ) throws FileNotFoundException, IOException {
		System.out.println("Ranking dos Leitores\n");
		List<String> rakingDoLeitores = _isSP.rankingDosPontuadores();
		
		if (rakingDoLeitores == null) System.out.println("Ninguem pontuou ainda");
		
		else {
			for (String leitor : rakingDoLeitores) { System.out.println(leitor); }
		}
		
		System.out.println("\n1 - Voltar para o Menu");
	}
	
	protected void visualizacaoDaListaDeLivros ( ) {
		List<String> listaDeLivros = _isBL.getListaDeLivros();
		
		for (String nomeDoLivro : listaDeLivros) { System.out.println("--> "+nomeDoLivro); }
		
		System.out.println("\n1 - Visualizar as informacoes de um livro");
	    System.out.println("2 - Voltar para o Menu");
	}
	
	protected void visualizacaoDasInformacoesDeUmLivro (String nomeDoLivro) {
		String[] livro = _isBL.getLivro(nomeDoLivro);
		
		if (livro == null) {
			System.out.println("Esse livro nao existe em nosso banco");
			System.out.println("2 - Voltar para o Menu");
		}
		else {
			System.out.println("Nome: "+livro[0]);
			System.out.println("Escritor: "+livro[1]);
			System.out.println("Estilo: "+livro[2]);
			System.out.println("Paginas: "+livro[3]);
			System.out.println("\n1 - Marcar livro como lido");
		    System.out.println("2 - Voltar para o Menu");
		}
	}
	
	protected String marcarLivroLido (String nomeDoLivro) throws IOException {
		return _isSL.livroLido(nomeDoLivro, _isUsernameDoLeitor);
	}
	
}
