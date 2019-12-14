package src.menu;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import src.sistema.SistemaDeLogin;

public class SistemaDeControle {
	private SistemaDeLogin _isLogin;
	private SistemaDoMenu _isSM;
	private Scanner scan;
	private String opcaoDoUsuario;
	
	public SistemaDeControle () throws FileNotFoundException, IOException {
		_isLogin = new SistemaDeLogin();
		scan = new Scanner(System.in);
		login();
	}
	
	private void login () throws FileNotFoundException, IOException {
		System.out.println("Username: ");
		String username = scan.nextLine();
		System.out.println("Key: ");
		String key = scan.nextLine();
		
		String permissaoDeAcesso = _isLogin.login(username, key);
	
		if (permissaoDeAcesso.equals("Acesso Permitido")) {
			_isSM = new SistemaDoMenu(username);
			sistemaLogado();
		}
		else {
			System.out.println(permissaoDeAcesso);
			login();
		}
	}
	
	private void sistemaLogado() throws FileNotFoundException, IOException {
		_isSM.menu();
		System.out.println("OPCAO: ");
		opcaoDoUsuario = scan.nextLine();
		
		if (opcaoDoUsuario.equals("1")) perfil();
		if (opcaoDoUsuario.equals("2")) ranking();
		if (opcaoDoUsuario.equals("3")) listaDeLivros();
		if (opcaoDoUsuario.equals("4")) informacaoLivro();
		if (opcaoDoUsuario.equals("5")) marcarComoLido();
		if (opcaoDoUsuario.equals("6")) sair();
	}
	
	private void sair() {
		System.out.println("--------------- Bye Bye ---------------");
	}
	
	private void marcarComoLido() throws IOException {
		System.out.println("Nome do Livro: ");
		System.out.println(_isSM.marcarLivroLido(scan.nextLine()));
		sistemaLogado();
	}
	
	private void marcarComoLido(String nomeDoLivro) throws IOException {
		System.out.println(_isSM.marcarLivroLido(nomeDoLivro));
		sistemaLogado();
	}
	
	private void informacaoLivro() throws IOException, FileNotFoundException {
		System.out.println("Nome do Livro: ");
		String nomeDoLivro = scan.nextLine();
		_isSM.visualizacaoDasInformacoesDeUmLivro(nomeDoLivro);
		opcaoDoUsuario = scan.nextLine();
		
		if (opcaoDoUsuario.equals("1")) marcarComoLido(nomeDoLivro);
		if (opcaoDoUsuario.equals("2")) sistemaLogado();
	}
	
	private void listaDeLivros() throws IOException, FileNotFoundException {
		_isSM.visualizacaoDaListaDeLivros();
		System.out.println("OPCAO: ");
		opcaoDoUsuario = scan.nextLine();
		
		if (opcaoDoUsuario.equals("1")) informacaoLivro(); 
		if (opcaoDoUsuario.equals("2")) sistemaLogado(); 
	}
	
	private void ranking() throws FileNotFoundException, IOException {
		_isSM.visualizacaoDoRanking();
		System.out.println("OPCAO: ");
		opcaoDoUsuario = scan.nextLine();
		
		if (opcaoDoUsuario.equals("1")) sistemaLogado();
	}
	
	private void perfil() throws FileNotFoundException, IOException {
		_isSM.visualizacaoDoPerfil();
		System.out.println("OPCAO: ");
		opcaoDoUsuario = scan.nextLine();
		
		if (opcaoDoUsuario.equals("1")) sistemaLogado();
	}
}
