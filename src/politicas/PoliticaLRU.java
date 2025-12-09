package politicas;

import java.util.*;

public class PoliticaLRU implements PoliticaSubstituicao {
    private Map<Integer, Boolean> lruCache;

    @Override
    public String getNome() { return "LRU"; }

    @Override
    public void inicializar(int capacidade) {
        this.lruCache = new LinkedHashMap<>(capacidade, 0.75f, true);
    }

    @Override
    public int selecionarVitima(Set<Integer> memoria, int[] reqs, int idx) {
        if (lruCache.isEmpty()) return -1;
        return lruCache.keySet().iterator().next();
    }

    @Override
    public void adicionarPagina(int pagina) {
        lruCache.put(pagina, true);
    }

    @Override
    public void removerPagina(int pagina) {
        lruCache.remove(pagina);
    }

    @Override
    public void aoAcessarPagina(int pagina) {
        lruCache.get(pagina);
    }
}