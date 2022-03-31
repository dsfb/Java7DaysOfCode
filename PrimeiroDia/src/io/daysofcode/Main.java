package io.daysofcode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class Main {
	/*
	 * Continuar depois:
	 * https://www.mocklab.io/blog/which-java-http-client-should-i-use-in-2020/
	 * https://www.twilio.com/blog/5-ways-to-make-http-requests-in-java
	 * 	Ver a biblioteca jackson com o exemplo dado no último link acima!
	 * */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Digite a sua API Key, aqui:");
		String api_key = scanner.nextLine();
		String url = String.format("https://imdb-api.com/en/API/Top250Movies/%s", api_key);
		
		try {
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest
			        .newBuilder(URI.create(url))
			        .GET()
			        .header("accept", "application/json")
			        .build();
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				System.out.printf("A resposta veio com Status Code: %d.\n", response.statusCode());
				String string = response.body();
				JsonObject obj = new Gson().fromJson(string, JsonObject.class);
				System.out.println("A resposta é um JSON? " + (obj.isJsonObject() ? "Sim." : "Não."));
				System.out.println("\nConteúdo da resposta:\n\n" + obj.toString());
			} else {
				
				System.out.println("Terminou sem exceções com o status: " + response.statusCode());
			}
		} catch (IOException e) {
			System.out.println("Falha na obtenção dos dados da web!");
		} catch (InterruptedException e) {
			System.out.println("Falha na gestão das threads!");
		} finally {
			System.out.println("Fim da execução!");
		}
		scanner.close();
	}

}
