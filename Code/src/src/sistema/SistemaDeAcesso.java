package src.sistema;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.bancoDeDados.BancoDeDados;

public class SistemaDeAcesso {
	private String _isKey;
	private BancoDeDados _isBD;
	
	public SistemaDeAcesso() throws FileNotFoundException, IOException {
		_isBD = new BancoDeDados();
	}
	
	public String login(String username, String key) throws FileNotFoundException, IOException {
		if (username == "" || _isKey == "") return "Acesso Negado";
		
		_isKey = _isBD.getAcessoUsuario(username);
		
		if (_isKey.equals(key)) return "Acesso Permitido";
		if (_isKey.equals("Username inválido")) return "Username inválido";
		
		return "Acesso Negado";
	}

}
