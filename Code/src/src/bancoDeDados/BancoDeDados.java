package src.bancoDeDados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class BancoDeDados extends TratamentoDeArquivos {
	private int _ifNumeroDeRegistroDoUsuario;
	private List<String[]> dadosDoArquivo;
	
	public BancoDeDados () throws FileNotFoundException, IOException {
		dadosDoArquivo = lendoOArquivo();
		_ifNumeroDeRegistroDoUsuario = 0;
	}
	
	public String getAcessoUsuario(String username) throws FileNotFoundException, IOException {
		String resultadoDaVerificacao = procuraUsuarioNoBancoDeUsuarios(username);
		
		if (resultadoDaVerificacao.equals("Username válido")) {
			return getKey();
		}
		else
			return resultadoDaVerificacao;
	}

	private String procuraUsuarioNoBancoDeUsuarios(String username) throws FileNotFoundException, IOException {
		int i = 0;
		String _isUsuario = "";
		
		while (!_isUsuario.equals(username) && i < dadosDoArquivo.size()) {
			_isUsuario = dadosDoArquivo.get(i)[0];
			i++;
		}

		if (_isUsuario.equals(username)) {
			_ifNumeroDeRegistroDoUsuario = i-1;
			return "Username válido";
		}
		return "Username inválido";
	}
	
	private String getKey() throws FileNotFoundException, IOException {
		return dadosDoArquivo.get(_ifNumeroDeRegistroDoUsuario)[1];
	}

}
