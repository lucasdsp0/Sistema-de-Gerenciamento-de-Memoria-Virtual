package dominio;

public class ResultadoSimulacao {
    private final long tempoExecucaoNano;
    private final int pageFaults;
    private final String estadoSwap;

    public ResultadoSimulacao(long tempoNano, int faults, String swap) {
        this.tempoExecucaoNano = tempoNano;
        this.pageFaults = faults;
        this.estadoSwap = swap;
    }

    public long getTempoExecucaoSegundos() {
        return Math.round(tempoExecucaoNano / 1_000_000_000.0);
    }

    public int getPageFaults() {
        return pageFaults;
    }

    public String getEstadoSwap() {
        return estadoSwap;
    }
    
}
