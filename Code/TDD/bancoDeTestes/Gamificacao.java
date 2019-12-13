package bancoDeTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import src.sistema.Pontuacao;

class Gamificacao {
	
	//US5.2 - Requisito 1:
	@Test
	void selecionandoDados() throws FileNotFoundException, IOException {
		Pontuacao P = new Pontuacao();
		List<String[]> dados = P.coletandoDados("Mateus Brugnaroto");
		assertEquals(dados.get(0)[0], "O Poder da Autorresponsabilidade");
		assertEquals(dados.get(1)[0], "Vamos ve o que vai da pra fechar");
		assertEquals(dados.get(2)[0], "Esse aqui e a pegadinha do jogo");
	}
	@Test
	void selecionandoDadoDeUmUsuarioInexistente() throws FileNotFoundException, IOException {
		Pontuacao P = new Pontuacao();
		List<String[]> dados = P.coletandoDados("Mateus");
		assertEquals(dados.isEmpty(), true);
	}
	
	//US5.2 - Requisito 2:
	@Test
	void calculandoPontosTotal() throws FileNotFoundException, IOException {
		Pontuacao P = new Pontuacao();
		assertEquals(P.calculandoPontuacao("Nanotec"), 18);
	}
}
