package logica;

import dominio.Configuracao;
import dominio.ResultadoSimulacao;
import politicas.PoliticaSubstituicao;
import java.util.*;
import java.util.stream.Collectors;

public class GerenciadorMemoria {

    public static ResultadoSimulacao executarSimulacao(Configuracao config, PoliticaSubstituicao politica, int[] requisicoes) {
        int capacidadeFrames = config.getNumFrames();
        int totalPaginas = config.getNumPaginasVirtuais();
        
        Set<Integer> paginasEmMemoria = new HashSet<>();
        politica.inicializar(capacidadeFrames);

        int pageFaults = 0;
        long inicio = System.nanoTime();

        for (int i = 0; i < requisicoes.length; i++) {
            int paginaRequisitada = requisicoes[i];

            if (paginasEmMemoria.contains(paginaRequisitada)) {
                politica.aoAcessarPagina(paginaRequisitada);
            } else {
                pageFaults++;

                if (paginasEmMemoria.size() >= capacidadeFrames) {
                    int vitima = politica.selecionarVitima(paginasEmMemoria, requisicoes, i);
                    if (vitima != -1) {
                        paginasEmMemoria.remove(vitima);
                        politica.removerPagina(vitima);
                    }
                }

                if (paginasEmMemoria.size() < capacidadeFrames) {
                    paginasEmMemoria.add(paginaRequisitada);
                    politica.adicionarPagina(paginaRequisitada);
                }
            }
        }

        long fim = System.nanoTime();

        List<Integer> swap = new ArrayList<>();
        for (int p = 0; p < totalPaginas; p++) {
            if (!paginasEmMemoria.contains(p)) {
                swap.add(p);
            }
        }
        
        String swapStr = swap.stream()
                             .map(String::valueOf)
                             .collect(Collectors.joining(" "));

        return new ResultadoSimulacao(fim - inicio, pageFaults, swapStr);
    }
}