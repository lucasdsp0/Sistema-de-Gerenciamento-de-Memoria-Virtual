package dominio;

public class Configuracao {
    private final int memoriaFisicaBytes;
    private final int memoriaVirtualBytes;
    private final int numPaginasVirtuais;

    public Configuracao(int mf, int mv, String arch, int p) {
        this.memoriaFisicaBytes = mf;
        this.memoriaVirtualBytes = mv;
        this.numPaginasVirtuais = p;
    }

    public int getTamanhoPagina() {
        if (numPaginasVirtuais == 0) return 0;
        return memoriaVirtualBytes / numPaginasVirtuais;
    }

    public int getNumFrames() {
        int tamPagina = getTamanhoPagina();
        if (tamPagina == 0) return 0;
        return memoriaFisicaBytes / tamPagina;
    }

    public int getTamanhoSwap() {
        return memoriaVirtualBytes - memoriaFisicaBytes;
    }

    public int getNumPaginasVirtuais() {
        return numPaginasVirtuais;
    }
}