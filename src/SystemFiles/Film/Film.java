package SystemFiles.Film;

public class Film {
    private int ID;
    private String name;
    private String genre;
    private boolean looked;
    private String feel;

    public Film(String name, String genre, boolean looked, String feel) {
        this.name = name;
        this.genre = genre;
        this.looked = looked;
        this.feel = feel;
    }

    public Film(String[] film) {
        this.feel = film[film.length - 1];
        this.looked = film[film.length - 2] == "1" ? true : false;
        this.genre = film[film.length - 3];
        this.name = "";
        for (int i = 0; i < film.length - 4; i++) {
            name += film[i] + " ";
        }
        name += film[film.length - 4];
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isLooked() {
        return looked;
    }

    public void setLooked(boolean looked) {
        this.looked = looked;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }
    public String toStringForJTable() {
        String s = "";
        s += name + " ";
        if (genre.equals("Выбрать...")) {
            s += "null ";
        } else {
            s += genre + " ";
        }
        if (looked) {
            s += "смотрел ";
            if (feel.equals("Выбрать...")) {
                s += "null";
            } else {
                s += feel;
            }
        } else {
            s += "не смотрел";
        }

        return s;
    }

    @Override
    public String toString() {
        return name + " " + genre + " " + (looked ? 1 : 0) + " " + feel;
    }
}
