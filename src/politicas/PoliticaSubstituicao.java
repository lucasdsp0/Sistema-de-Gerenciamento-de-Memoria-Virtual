package politicas;

import java.util.Set;

public interface PoliticaSubstituicao {
    String getNome();
    void inicializar(int capacidade);
    int selecionarVitima(Set<Integer> memoriaAtual, int[] requisicoes, int indiceAtual);
    void adicionarPagina(int pagina);
    void removerPagina(int pagina);
    void aoAcessarPagina(int pagina);
}