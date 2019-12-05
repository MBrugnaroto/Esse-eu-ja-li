package src.bancoDeDados;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class TratamentoDeArquivos {
	private List<String[]> _isDadosDoArquivo;
	
	public TratamentoDeArquivos() {
		_isDadosDoArquivo = null;
	}
	
	protected List<String[]> lendoOArquivo(String bancoSolicitado) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("Banco de Dados/"+bancoSolicitado));
		String[] dado;
		int i = 0;
		br.readLine();
		
		if (br.ready()) _isDadosDoArquivo = new ArrayList<>();
		
		while(br.ready()){
			dado = br.readLine().split(Pattern.quote("|"));
			for (i = 0; i < dado.length; i++) {
				dado[i] = dado[i].trim();
			}
			_isDadosDoArquivo.add(dado);
		}
		br.close();
		return _isDadosDoArquivo;
	}
}
