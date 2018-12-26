public class Main {
    public static void main(String[] args) {
        Engine tracks = new Engine();
        Engine albums = new Engine();

        EngineObject LollyBomb = new Track();
        ((Track) LollyBomb).setName("Lolly Bomb");
        ((Track) LollyBomb).setGenre("rave");
        ((Track) LollyBomb).setSinger("Little Big");

        EngineObject TillIDie = new Track();
        ((Track) TillIDie).setName("TillIDie");
        ((Track) TillIDie).setGenre("rock");
        ((Track) TillIDie).setSinger("MachineGunKelly");

        EngineObject AntipositiveSong = new Track();
        ((Track) AntipositiveSong).setName("Antipositive");
        ((Track) AntipositiveSong).setGenre("rap/rave");
        ((Track) AntipositiveSong).setSinger("Little Big");

        EngineObject Antipositive = new Album();
        ((Album) Antipositive).setName("Antipositive");
        ((Album) Antipositive).addSong((Track) LollyBomb);
        ((Album) Antipositive).setGenre("rap");
        ((Album) Antipositive).setSinger("Little Big");

        EngineObject RocknRofl = new Album();
        ((Album) RocknRofl).setName("Rock'n'Rofl");
        ((Album) RocknRofl).setGenre("rap");
        ((Album) RocknRofl).setSinger("Djarahov");


        tracks.add(TillIDie);
        tracks.add(AntipositiveSong);
        tracks.add(LollyBomb);
        albums.add(RocknRofl);
        albums.add(Antipositive);


        Query singer = new QueryBuilder().setProperty("singer", "Little Big").build();
        Query genre = new QueryBuilder().setProperty("genre", "rap")./*setProperty("singer", "Little Big").*/build();

        System.out.println(tracks.search(singer));
        System.out.println(albums.search(genre));

    }
}

