package politicas;

import java.util.Set;

public class PoliticaOPT implements PoliticaSubstituicao {
    
    @Override
    public String getNome() { return "MIN"; }

    @Override
    public void inicializar(int capacidade) { }

    @Override
    public int selecionarVitima(Set<Integer> memoria, int[] requisicoes, int indiceAtual) {
        int vitima = -1;
        int distanciaMaxima = -1;

        for (int paginaEmMemoria : memoria) {
            int proximaOcorrencia = Integer.MAX_VALUE;

            for (int j = indiceAtual + 1; j < requisicoes.length; j++) {
                if (requisicoes[j] == paginaEmMemoria) {
                    proximaOcorrencia = j;
                    break;
                }
            }

            if (proximaOcorrencia == Integer.MAX_VALUE) {
                return paginaEmMemoria;
            }

            if (proximaOcorrencia > distanciaMaxima) {
                distanciaMaxima = proximaOcorrencia;
                vitima = paginaEmMemoria;
            }
        }
        return vitima;
    }

    @Override
    public void adicionarPagina(int pagina) { }

    @Override
    public void removerPagina(int pagina) { }

    @Override
    public void aoAcessarPagina(int pagina) { }
}