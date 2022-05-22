class Rack {
    private int antNoderPlassert = 0;
    private Node[] noder;
    
    // Oppretter et rack der det senere kan plasseres noder
    public Rack(int rackStorrelse) {
        noder = new Node[rackStorrelse]; 
    }

    // Sjekker om det er plass i racket eller ikke
    public boolean erLedigPlass() {
        if (antNoderPlassert < noder.length) return true;
        return false;
    }
    
    // Plasserer en ny node inn i racket hvis plass
    public void settInn(Node node) {
        if ( erLedigPlass() ) {
            noder[antNoderPlassert] = node;
            antNoderPlassert++;
        }
    }

    // Beregner og returnerer totalt antall prosessorer i nodene i et rack
    public int antProsessorer() {
        int antProsessorer = 0;
        for (int i = 0; i < antNoderPlassert; i++) {
            antProsessorer += noder[i].antProsessorer();
        }
        return antProsessorer;
    }

    // Beregner og returnerer antall noder i racket med minne over gitt grense
    public int noderMedNokMinne(int paakrevdMinne) {
        int antNoderMedNokMinne = 0;
        for (int i = 0; i < antNoderPlassert; i++) {
            if (noder[i].nokMinne(paakrevdMinne)) {
                antNoderMedNokMinne++;
            }
        }
        return antNoderMedNokMinne;
    }
}