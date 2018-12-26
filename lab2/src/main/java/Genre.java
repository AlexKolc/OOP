import java.util.ArrayList;

public class Genre {
    private String name;
    private Genre parent;
    private ArrayList<Genre> descendants;

    Genre(String name) {
        this.name = name;
        this.descendants = new ArrayList<Genre>();
    }

    Genre(String name, Genre parent) {
        this.name = name;
        this.parent = parent;
        this.descendants = new ArrayList<Genre>();
        this.parent.addDescendants(this);
    }

    public void addDescendants(Genre genre) {
        descendants.add(genre);
    }

    public ArrayList<Genre> getAllDescendants() {
        ArrayList<Genre> result = new ArrayList<>();
        result.add(this);
        for (Genre genre : descendants) {
            result.add(genre);
            result.addAll(genre.getAllDescendants());
        }
        return descendants;
    }

    @Override
    public String toString() {
        return name;
    }
}

