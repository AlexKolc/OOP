public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        Engine engine1 = new Engine();
        EngineObject Berzerk = new Song();
        ((Song) Berzerk).setName("Berzerk");
        ((Song) Berzerk).setGenre("rap");
        ((Song) Berzerk).setSinger("Eminem");

        EngineObject TillIDie = new Song();
        ((Song) TillIDie).setName("TillIDie");
        ((Song) TillIDie).setGenre("rap/rock");
        ((Song) TillIDie).setSinger("MachineGunKelly");

        EngineObject RapGod = new Song();
        ((Song) RapGod).setName("RapGod");
        ((Song) RapGod).setGenre("rap");
        ((Song) RapGod).setSinger("Eminem");

        EngineObject Eminem = new Album();
        ((Album) Eminem).setName("Eminem");
        ((Album) Eminem).addSong((Song) RapGod);
        ((Album) Eminem).setGenre("Rap");


        engine.add(TillIDie);
        engine.add(Berzerk);
        engine.add(RapGod);
        engine1.add(Eminem);

        Query qr = new QueryBuilder().setProperty("singer", "Eminem").build();
        Query QR = new QueryBuilder().setProperty("genre", "Rap").build();
        System.out.println(engine.search(qr));
        System.out.println(engine1.search(QR));


    }
}

