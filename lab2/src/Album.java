import java.util.ArrayList;

public class Album extends EngineObject {
    private ArrayList<Song> songs = new ArrayList<>();
    private String genre;
    private String name;


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

    public void addSong(Song song) {
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
}
