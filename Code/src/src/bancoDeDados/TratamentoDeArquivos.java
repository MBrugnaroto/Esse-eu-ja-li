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

abstract class TratamentoDeArquivos {
	protected List<String[]> lendoOArquivo() throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader("Banco de Usuários/Registro de Usuários"));
		List<String[]> dadosDoArquivo = new ArrayList<>();
		String[] dado;

		br.readLine();
		while(br.ready()){
			dado = br.readLine().split(Pattern.quote("|"));
			dado[0] = dado[0].trim();
			dado[1] = dado[1].trim();
			dadosDoArquivo.add(dado);
		}
		br.close();
		return dadosDoArquivo;
	}
}
