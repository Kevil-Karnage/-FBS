package SystemFiles;

public class Strings {
    private String title = "FBS";
    private String none = "null";
    private String[] files = {
            "src/SystemFiles/Film/Films",
            "src/SystemFiles/Serial/Serials",
            "src/SystemFiles/Book/Books"
    };
    private String[] feels = {
            "Выбрать...",
            "Офигенно",
            "Классно",
            "Хорошо",
            "Средне",
            "Более-менее",
            "Такое себе",
            "Больше не смотреть",
            "на 1 раз"
    };
    private String[] genres = {
            "Выбрать...",
            "Драма",
            "Комедия",
            "Хоррор",
            "Экшн"
    };
    private String[] saves = {
            "Сохранено", "Такой фильм уже есть", "Не хватает данных"
    };

    private String[] looked = {
            "Просмотрено", "Не просмотрено"
    };

    public String title() {
        return title;
    }

    public String none() {
        return none;
    }

    public String file(int i) {
        return files[i];
    }

    public String feel(int i) {
        return feels[i];
    }

    public String genre(int i) {
        return genres[i];
    }

    public String save(int i) {
        return saves[i];
    }

    public String looked(int i) {
        return looked[i];
    }

    public String[] getFiles() {
        return files;
    }

    public String[] getFeels() {
        return feels;
    }

    public String[] getGenres() {
        return genres;
    }

    public String[] getSaves() {
        return saves;
    }

    public String[] getLooked() {
        return looked;
    }


}
