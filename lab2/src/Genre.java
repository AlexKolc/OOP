import java.util.ArrayList;

public class Genre {
    private String name;
    private Genre root;
    private ArrayList<Genre> children;

    Genre(String name) {
        this.name = name;
        this.children = new ArrayList<Genre>();
    }

    Genre(String name, Genre parent) {
        this.name = name;
        this.root = parent;
        this.children = new ArrayList<Genre>();
        this.root.addChild(this);
    }

    public void addChild( Genre genre){
        children.add(genre);
    }

    public ArrayList<Genre> getAllChildren() {
        ArrayList<Genre> result = new ArrayList<>();
        result.add(this);
        for(Genre genre: children) {
            result.add(genre);
            result.addAll(genre.getAllChildren());
        }
        return children;
    }

    @Override
    public String toString() {
        return name;
    }
}

