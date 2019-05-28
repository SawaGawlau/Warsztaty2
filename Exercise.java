package Model;

public class Exercise {
    int id;
    String title;
    String description;

    public Exercise(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Exercise(){

    }

    public Exercise(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Exercise(int id, String title) {
        this.id = id;
        this.title = title;
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

    public void setDescription(String email) {
    }
}
