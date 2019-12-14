package src.bancoDeDados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class BancoDeUsuarios extends TratamentoDeArquivos {
	private int _ifNumeroDeRegistroDoUsuario;
	private List<String[]> _isDadosDoArquivo;
	
	public BancoDeUsuarios () throws FileNotFoundException, IOException {
		_isDadosDoArquivo = lendoOArquivo("Registro de Usuários");
		_ifNumeroDeRegistroDoUsuario = 0;
	}
	
	public String getAcessoUsuario(String username) throws FileNotFoundException, IOException {
		String resultadoDaVerificacao = procuraUsuarioNoBancoDeUsuarios(username);
		
		if (resultadoDaVerificacao.equals("Username válido")) return getKey();
		else return resultadoDaVerificacao;
	}

	private String procuraUsuarioNoBancoDeUsuarios(String username) throws FileNotFoundException, IOException {
		int i = 0;
		String _isUsuario = "";
		
		while (!_isUsuario.equals(username) && i < _isDadosDoArquivo.size()) {
			_isUsuario = _isDadosDoArquivo.get(i)[0];
			i++;
		}

		if (_isUsuario.equals(username)) {
			_ifNumeroDeRegistroDoUsuario = i-1;
			return "Username válido";
		}
		return "Username inválido";
	}
	
	private String getKey() throws FileNotFoundException, IOException {
		return _isDadosDoArquivo.get(_ifNumeroDeRegistroDoUsuario)[1];
	}

}
