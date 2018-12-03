import java.util.Date;
import java.util.HashMap;

public class Song extends EngineObject {
    private String name;
    private String genre;
    private String artist;

    Song() {
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

    public void setSinger(String artist) {
        this.artist = artist;
        if (this.properties.containsKey("artist")) {
            this.properties.replace("artist", artist);
        } else {
            this.properties.put("artist", artist);
        }
    }

    public void setGenre(String genre) {
        this.genre = genre;
        if (this.properties.containsKey("genre")) {
            this.properties.replace("genre", genre);
        } else {
            this.properties.put("genre", genre);
        }
    }

    @Override
    public String toString() {
        return this.properties.get("name");
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

}
