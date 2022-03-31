package io.daysofcode;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class Main {
	private static String download(String urlStr) throws IOException {
	    URL url = new URL(urlStr);
	    StringBuilder ret = new StringBuilder();
	    try (BufferedInputStream bis = new BufferedInputStream(url.openStream());) {
	    	byte[] buffer = new byte[1024];
		    int count = 0;
		    while ((count = bis.read(buffer, 0, 1024)) != -1) {
		        ret.append(new String(buffer, 0, count));
		    }
	    }	    

	    return ret.toString();
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite a sua API Key, aqui:");
		String apiKey = scanner.nextLine();
		String urlStr = String.format("https://imdb-api.com/en/API/Top250Movies/%s", apiKey);
		try {
			System.out.println("Assim, o conteúdo da resposta desta API do IMDB é:\n");
			System.out.println(download(urlStr));
		} catch (MalformedURLException e) {
			System.out.println("A url não é válida!");
		} catch (IOException e) {
			System.out.println("Erro ao abrir a conexão!");
		} finally {
			System.out.println("\nFim da execução!");
		}
		
		scanner.close();
	}

}
