package src.sistema;

import java.io.FileNotFoundException;
import java.io.IOException;

import src.bancoDeDados.BancoDeUsuários;

public class SistemaDeLogin {
	private String _isKey;
	private BancoDeUsuários _isBD;
	
	public SistemaDeLogin() throws FileNotFoundException, IOException {
		_isBD = new BancoDeUsuários();
	}
	
	public String login(String username, String key) throws FileNotFoundException, IOException {
		if (username == "" || _isKey == "") return "Acesso Negado";
		
		_isKey = _isBD.getAcessoUsuario(username);
		
		if (_isKey.equals(key)) return "Acesso Permitido";
		if (_isKey.equals("Username inválido")) return "Username inválido";
		
		return "Acesso Negado";
	}

}
