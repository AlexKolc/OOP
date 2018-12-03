import java.util.ArrayList;

public class Playlist extends EngineObject {
    private ArrayList<Song> songs = new ArrayList<>();
    private String name;

    Playlist() {
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

    public void addSong(Song song) {
        this.songs.add(song);
    }

    @Override
    public String toString() {
        return (String) this.properties.get("name");
    }
}
