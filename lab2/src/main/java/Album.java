import java.util.ArrayList;

public class Album extends EngineObject {
    private ArrayList<Track> songs = new ArrayList<>();
    private String genre;
    private String name;
    private String singer;


    Album() {
        super();
    }

    public void setName(String name) {
        this.name = name;
        if (this.properties.containsKey("name")) {
            this.properties.replace("name", name);
        } else {
            this.properties.put("name", name);
        }
    }

    public void setGenre(String genre) {
        this.genre = genre;
        this.properties.put("genre", genre);
    }

    public void setSinger(String singer) {
        this.singer = singer;
        if (this.properties.containsKey("singer")) {
            this.properties.replace("singer", singer);
        } else {
            this.properties.put("singer", singer);
        }
    }

    public void addSong(Track song) {
        this.songs.add(song);
    }

    @Override
    public String toString() {
        return (String) this.properties.get("name");
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getSinger() {
        return singer;
    }
}
