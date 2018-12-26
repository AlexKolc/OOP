import java.util.Date;
import java.util.HashMap;

public class Track extends EngineObject {
    private String name;
    private String genre;
    private String singer;

    Track() {
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

    public void setSinger(String singer) {
        this.singer = singer;
        if (this.properties.containsKey("singer")) {
            this.properties.replace("singer", singer);
        } else {
            this.properties.put("singer", singer);
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
        return (String) this.properties.get("name");
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
