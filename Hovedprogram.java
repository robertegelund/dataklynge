import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Hovedprogram {
    final static int rackStorrelse = 12;
    final static int maksMinne = 4*1024;
    final static int maksPros = 16;
    static Dataklynge dataklynge; 
    public static void main(String[] args) {
        dataklynge = new Dataklynge(rackStorrelse);
        if(args.length != 0) {
            lesInnFraFil(args[0]);
        } else {
            lesInnFraFil("dataklynge2.txt");
        }
        skrivStatusMelding();
    }

    private static void lesInnFraFil(String filnavn) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(new File(filnavn));
        } catch(FileNotFoundException e) {
            System.out.println("\nFilen ble dessverre ikke funnet.\n");
        }

        while(scanner.hasNextLine()) {
            String linje = scanner.nextLine();
            String[] deler = linje.split(" ");

            int antNoder = Integer.parseInt(deler[0]);
            int prosPerNode = Integer.parseInt(deler[1]);
            int minnePerNode = Integer.parseInt(deler[2]);

            if(prosPerNode > maksPros || minnePerNode > maksMinne) {
                skrivFeilmelding();
                scanner.close();
                return; 
            } 

            for (int i = 0; i < antNoder; i++) {
                Node node = new Node(prosPerNode, minnePerNode);
                dataklynge.settInnNode(node);
            }
        }
        scanner.close();
    }

    private static void skrivFeilmelding() {
        String feilmelding1 = "\nNodene har mindre minneplass eller ";
        String feilmelding2 = "mindre prosessorplass enn forespurt.";
        String feilmelding3 = "Maksminne per node: " + maksMinne + " GB og ";
        String feilmelding4 = "maks antall prosessorer per node: " + maksPros +".";
        String feilmelding = feilmelding1 + feilmelding2;
        System.out.println(feilmelding);
        feilmelding = feilmelding3 + feilmelding4;
        System.out.println(feilmelding);
        System.out.println("Derfor ble ikke alle noder opprettet. Foelgende ble opprettet:");
    }

    private static void skrivStatusMelding() {
        if (dataklynge.antRack() > 1) {
            String nodeMelding = "Noder med minst ";
            System.out.println();
            System.out.println(nodeMelding + " 128 GB: " + dataklynge.noderMedNokMinne(128));
            System.out.println(nodeMelding + " 512 GB: " + dataklynge.noderMedNokMinne(512));
            System.out.println(nodeMelding + " 1024 GB: " + dataklynge.noderMedNokMinne(1024));
            System.out.println();
            System.out.println("Antall prosessorer: " + dataklynge.antProsessorer());
            System.out.println("Antall rack: " + dataklynge.antRack() +"\n");
        } else {
            System.out.println("Det finnes naa hverken noder eller rack i dataklyngen.");
        }
    }
}
