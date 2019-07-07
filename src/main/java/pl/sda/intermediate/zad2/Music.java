package pl.sda.intermediate.zad2;

public class Music extends Playable {

    private String name;
    private String author;

    public Music(String name, String author) {
        this.name = name;
        this.author = author;
    }

    @Override
    public String play() {
        return String.format("Music: %s %s", author, name);
    }

}
