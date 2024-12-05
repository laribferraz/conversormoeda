import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Conversor {
    private static final String API_KEY = "7d92349ea3a0d28870606c21"; // Substitua pela sua chave de API
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/";

    public double converterMoeda(String moedaBase, String moedaDestino, double valor) throws IOException, InterruptedException {
        URI uri = URI.create(BASE_URL + moedaBase);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            JsonObject jsonObject = new Gson().fromJson(response.body(), JsonObject.class);

            // Verificar se o campo 'rates' existe e se a taxa de câmbio está disponível
            if (jsonObject.has("conversion_rates")) { // Supondo que o campo seja "conversion_rates" agora
                JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");
                if (rates.has(moedaDestino)) {
                    double taxaDeCambio = rates.get(moedaDestino).getAsDouble();
                    return valor * taxaDeCambio;
                } else {
                    throw new RuntimeException("Moeda destino não encontrada na resposta da API.");
                }
            } else {
                throw new RuntimeException("Campo 'conversion_rates' não encontrado na resposta da API.");
            }
        } else {
            throw new RuntimeException("Erro na requisição HTTP: " + response.statusCode());
        }
    }
    }