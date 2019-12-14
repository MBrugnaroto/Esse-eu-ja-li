package bancoDeTestes;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import src.sistema.SistemaDeLeitura;

class LivroLido {
	
	//US4 - Requisito 1:
	@Test
	void adicionaInformacoesDoLivroLido() throws IOException {
		SistemaDeLeitura LL = new SistemaDeLeitura();
		assertEquals(LL.livroLido("O Poder da Autorresponsabilidade", "Mateus Brugnaroto"), "Marcado como lido");
	}
	
	//US4 - Requisito 2:
		@Test
	void adiconaLivroQueNaoEstaNoBanco() throws IOException {
		SistemaDeLeitura LL = new SistemaDeLeitura();
		assertEquals(LL.livroLido("Teco", "Mateus Brugnaroto"), "Esse livro nao existe em nosso banco");
	}
}
