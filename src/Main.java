import java.util.*;
import dominio.Configuracao;
import dominio.ResultadoSimulacao;
import logica.GerenciadorMemoria;
import politicas.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            if (!scanner.hasNext()) return;

            int tamMemoriaFisica = Integer.parseInt(scanner.next());
            int tamMemoriaVirtual = Integer.parseInt(scanner.next());
            String arquitetura = scanner.next();
            int numPaginasVirtuais = Integer.parseInt(scanner.next());

            Configuracao config = new Configuracao(tamMemoriaFisica, tamMemoriaVirtual, arquitetura, numPaginasVirtuais);

            System.out.println(config.getTamanhoPagina());
            System.out.println(config.getNumFrames());
            System.out.println(config.getTamanhoSwap());
            System.out.println();

            int numSequencias = Integer.parseInt(scanner.next());

            List<PoliticaSubstituicao> politicas = new ArrayList<>();
            politicas.add(new PoliticaFIFO());
            politicas.add(new PoliticaRAND());
            politicas.add(new PoliticaLRU());
            politicas.add(new PoliticaOPT());

            for (int i = 0; i < numSequencias; i++) {
                int totalRequisicoes = Integer.parseInt(scanner.next());
                int[] requisicoes = new int[totalRequisicoes];
                
                StringBuilder sbSequencia = new StringBuilder();
                for (int j = 0; j < totalRequisicoes; j++) {
                    requisicoes[j] = Integer.parseInt(scanner.next());
                    sbSequencia.append(requisicoes[j]).append(j < totalRequisicoes - 1 ? " " : "");
                }

                System.out.println(totalRequisicoes);
                System.out.println(sbSequencia.toString());
                
                for (PoliticaSubstituicao politica : politicas) {
                    ResultadoSimulacao resultado = GerenciadorMemoria.executarSimulacao(
                        config, politica, requisicoes
                    );
                    
                    System.out.println(politica.getNome());
                    System.out.println(resultado.getTempoExecucaoSegundos());
                    System.out.println(resultado.getPageFaults());
                    System.out.println(resultado.getEstadoSwap());
                }
                System.out.println();
            }

        } catch (Exception e) {
            System.err.println("Erro ao processar: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}