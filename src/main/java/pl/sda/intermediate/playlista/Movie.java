package pl.sda.intermediate.playlista;

public class Movie extends Playable {

    private String name;

    public Movie(String name) {
        this.name = name;
    }

    @Override
    public String play() {
        return String.format("Movie: %s", name);
    }

}
