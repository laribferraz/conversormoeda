import java.io.IOException;
import java.util.Scanner;

public class ConversorMoedas {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1. Dólar para Real");
            System.out.println("2. Real para Dólar");
            System.out.println("3. Dólar para Peso Argentino");
            System.out.println("4. Peso Argentino para Dólar");
            System.out.println("5. Dólar para Peso Colombiano");
            System.out.println("6. Peso Colombiano para Peso Dólar");
            System.out.println("7. Sair");
            System.out.println("Escolha uma opção válida: ");

            opcao = scanner.nextInt();

            if (opcao >= 1 && opcao <= 6) {
                System.out.print("Digite o valor a ser convertido: ");
                double valor = scanner.nextDouble();

                String moedaBase = "USD";
                String moedaDestino = "BRL";
                if (opcao == 1) {
                    moedaBase = "USD";
                    moedaDestino = "BRL";
                } else if (opcao == 2) {
                    moedaBase = "BRL";
                    moedaDestino = "USD";
                } else if (opcao == 3) {
                    moedaBase = "USD";
                    moedaDestino = "ARS";
                } else if (opcao == 4) {
                    moedaBase = "ARS";
                    moedaDestino = "USD";
                } else if (opcao == 5) {
                    moedaBase = "USD";
                    moedaDestino = "COP";
                } else if (opcao == 6) {
                    moedaBase = "COP";
                    moedaDestino = "USD";
                }

                Conversor conversor = new Conversor();
                double resultado = conversor.converterMoeda(moedaBase, moedaDestino, valor);
                System.out.println("Resultado: " + resultado);
            }
        } while (opcao != 7);

        System.out.println("Programa finalizado!!");
    }
}