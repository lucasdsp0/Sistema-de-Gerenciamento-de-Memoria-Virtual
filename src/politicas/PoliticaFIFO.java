package politicas;

import java.util.*;

public class PoliticaFIFO implements PoliticaSubstituicao {
    private Queue<Integer> fila;

    @Override
    public String getNome() { return "FIFO"; }

    @Override
    public void inicializar(int capacidade) {
        this.fila = new LinkedList<>();
    }

    @Override
    public int selecionarVitima(Set<Integer> memoria, int[] reqs, int idx) {
        return fila.isEmpty() ? -1 : fila.peek();
    }

    @Override
    public void adicionarPagina(int pagina) {
        fila.offer(pagina);
    }

    @Override
    public void removerPagina(int pagina) {
        if (!fila.isEmpty() && fila.peek() == pagina) {
            fila.poll();
        }
    }

    @Override
    public void aoAcessarPagina(int pagina) { }
}