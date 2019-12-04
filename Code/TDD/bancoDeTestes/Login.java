package bancoDeTestes;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import src.bancoDeDados.BancoDeUsuários;
import src.sistema.SistemaDeLogin;

class Login {
	
	//Requisito 1:
	@Test
	void erroLoginSemUsername() throws FileNotFoundException, IOException {
		SistemaDeLogin l1 = new SistemaDeLogin ();
		String acesso = l1.login("", "kado123");
		assertEquals(acesso, "Acesso Negado");
	}
	
	@Test
	void erroLoginSemKey() throws FileNotFoundException, IOException {
		SistemaDeLogin l1 = new SistemaDeLogin ();
		String acesso = l1.login("kadore", "");
		assertEquals(acesso, "Acesso Negado");
	}
	
	@Test
	void loginSimples() throws FileNotFoundException, IOException {
		SistemaDeLogin l1 = new SistemaDeLogin ();
		String acesso = l1.login("kadore", "kado123");
		assertEquals(acesso, "Acesso Permitido");
	}
	
	//Requisito 2:
	@Test
	void retornaUsernameInválido() throws FileNotFoundException, IOException {
		BancoDeUsuários bd = new BancoDeUsuários();
		String informacao = bd.getAcessoUsuario("kdsadsada");
		assertEquals(informacao, "Username inválido");	
	}
	@Test
	void retornaUmaKey() throws FileNotFoundException, IOException {
		BancoDeUsuários bd = new BancoDeUsuários();
		String informacao = bd.getAcessoUsuario("kadore");
		assertEquals(informacao, "kado123");	
	}
	@Test
	void retornaUmaKeyAPartirDoUsername() throws FileNotFoundException, IOException {
		BancoDeUsuários bd = new BancoDeUsuários();
		String informacao = bd.getAcessoUsuario("kadore");
		assertEquals(informacao, "kado123");
	}
	
	//Requisito 3:
	@Test
	void acessoPermitidoIntegradoAoBanco() throws FileNotFoundException, IOException {
		SistemaDeLogin l1 = new SistemaDeLogin ();
		String acesso = l1.login("kadore", "kado123");
		assertEquals(acesso, "Acesso Permitido");
	}
	
	@Test
	void acessoNegadoIntegradoAoBancoSenhaErrada() throws FileNotFoundException, IOException {
		SistemaDeLogin l1 = new SistemaDeLogin ();
		String acesso = l1.login("kadore", "kado1234");
		assertEquals(acesso, "Acesso Negado");
	}
	
	@Test
	void acessoNegadoIntegradoAoBancoUserNameErrado() throws FileNotFoundException, IOException {
		SistemaDeLogin l1 = new SistemaDeLogin ();
		String acesso = l1.login("kadoreee", "kado123");
		assertEquals(acesso, "Username inválido");
	}
}
