package politicas;

import java.util.*;

public class PoliticaRAND implements PoliticaSubstituicao {
    private final Random random = new Random();
    private List<Integer> listaMemoria;

    @Override
    public String getNome() { return "RAND"; }

    @Override
    public void inicializar(int capacidade) {
        this.listaMemoria = new ArrayList<>(capacidade);
    }

    @Override
    public int selecionarVitima(Set<Integer> memoria, int[] reqs, int idx) {
        if (listaMemoria.isEmpty()) return -1;
        int indexAleatorio = random.nextInt(listaMemoria.size());
        return listaMemoria.get(indexAleatorio);
    }

    @Override
    public void adicionarPagina(int pagina) {
        listaMemoria.add(pagina);
    }

    @Override
    public void removerPagina(int pagina) {
        listaMemoria.remove(Integer.valueOf(pagina));
    }

    @Override
    public void aoAcessarPagina(int pagina) { }
}