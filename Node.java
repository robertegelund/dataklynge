class Node {
    private int antPros;
    private int minne;
    
    // Oppretter en node med gitt minnestorrelse og antall prosessorer
    public Node(int antPros, int minne) {
        this.antPros = antPros;
        this.minne = minne;
    }
    
    // Henter antall prosessorer i node 
    public int antProsessorer() {
        return antPros;
    }

    // Sjekker om noden har tilstrekkelig mine for et program
    public boolean nokMinne(int paakrevdMinne) {
        return minne >= paakrevdMinne;
    }
}