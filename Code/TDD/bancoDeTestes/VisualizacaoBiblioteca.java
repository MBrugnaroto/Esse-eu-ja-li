package bancoDeTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import src.bancoDeDados.BancoDeLivros;

class VisualizacaoBiblioteca {
	
	//US2 - Requisito 1:
	@Test
	void retornaUmaListaDosLivros() throws FileNotFoundException, IOException {
		BancoDeLivros BL = new BancoDeLivros();
		List<String> dadosDolivro = BL.getListaDeLivros();
		assertEquals(dadosDolivro.get(0), "Summit Lake");
		assertEquals(dadosDolivro.get(1), "Escravidão");
		assertEquals(dadosDolivro.get(2), "The Subtle Art of Not Giving a Fuck");
		assertEquals(dadosDolivro.get(19), "O Poder da Autorresponsabilidade");		
	}
	
	@Test
	void naoExisteLivros() throws FileNotFoundException, IOException {
		BancoDeLivros BL = new BancoDeLivros();
		List<String> dadosDolivro = BL.getListaDeLivros();
		assertNotEquals(dadosDolivro.get(0), "Não há livros na biblioteca");
	}
	
	//US3 - Requisito 1:
	@Test
	void retornaUmLivroExpecifico() throws FileNotFoundException, IOException {
		BancoDeLivros BL = new BancoDeLivros();
		String[] dadosDolivro = BL.getLivro("O Poder da Autorresponsabilidade");
		assertEquals(dadosDolivro[0], "O Poder da Autorresponsabilidade");
		assertEquals(dadosDolivro[1], "Paulo Vieira");
		assertEquals(dadosDolivro[2], "Negócios");
		assertEquals(dadosDolivro[3], "160");
	}
}
