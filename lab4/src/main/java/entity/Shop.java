package entity;

public class Shop {
    private int id;
    private String title;
    private String address;

    public Shop() { }

    public Shop(int id) {
        this.id = id;
    }

    public Shop(String title) {
        this.title = title;
    }

    public Shop(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Shop(String title, String address) {
        this.title = title;
        this.address = address;
    }

    public Shop(int id, String title, String address) {
        this.id = id;
        this.title = title;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String[] toCsvFormat() {
        return new String[]{Integer.toString(id), title};
    }
}
