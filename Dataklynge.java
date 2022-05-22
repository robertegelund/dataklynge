import java.util.ArrayList;

class Dataklynge {
    private int antNoderPerRack;
    private ArrayList<Rack> racks;

    // Oppretter regneklynge og angir antall noder per rack
    public Dataklynge(int antNoderPerRack) {
        this.antNoderPerRack = antNoderPerRack;
        racks = new ArrayList<>();
    }

    private void opprettRackMedNode(Node node) {
        Rack nyttRack = new Rack(antNoderPerRack);
        nyttRack.settInn(node);
        racks.add(nyttRack);
    }

    // Plasserer en node i et rack med ledig plass, eller i et nytt
    public void settInnNode(Node node) {
        if (racks.size() > 1) {
            for (Rack rack : racks) {
                if ( rack.erLedigPlass() ) {
                    rack.settInn(node);
                    return;
                }
            }
        }
        opprettRackMedNode(node);
    }

    // Returnerer antall racks i dataklyngen
    public int antRack() {
        return racks.size();
    }

    // Beregner og returnerer antall noder i dataklyngen med minne over angitt grense
    public int noderMedNokMinne(int paakrevdMinne) {
        int totAntNoderMedNokMinne = 0;
        for (Rack rack : racks) {
            totAntNoderMedNokMinne += rack.noderMedNokMinne(paakrevdMinne);
        }
        return totAntNoderMedNokMinne;
    }

    // Beregner og returnerer totalt antall prosessorer i dataklyngen
    public int antProsessorer() {
        int totAntProsessorer = 0;
        for (Rack rack : racks) {
            totAntProsessorer += rack.antProsessorer();
        }
        return totAntProsessorer;
    }
}