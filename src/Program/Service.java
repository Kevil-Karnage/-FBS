package Program;

import SystemFiles.Colors;
import SystemFiles.Film.Film;
import SystemFiles.Strings;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Service {
    private Strings s;
    private Colors c;

    public Service(Strings s, Colors c) {
        this.s = s;
        this.c = c;
    }

    public String addFilm (Film film) throws FileNotFoundException {
        Film[] films = readFilmsFromFile(s.file(0));
        int n = filmArrayContainsFilm(films, film);
        if (n == -1) {
            Film[] updatedFilms = Arrays.copyOf(films, films.length + 1);
            updatedFilms[updatedFilms.length - 1] = film;
            writeFilmsToFile(s.file(0), updatedFilms);
            return s.save(0);
        } else {
            return s.save(2);
        }
    }

    private int filmArrayContainsFilm(Film[] array, Film film) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getName().equals(film.getName())) {
                return i;
            }
        }
        return -1;
    }

    public void writeFilmsToFile(String fileName, Film[] films) throws FileNotFoundException {
        String[] s = new String[films.length];
        for (int i = 0; i < films.length; i++) {
            s[i] = films[i].toString();
        }
        writeArrayToFile(fileName, s);
    }

    public void writeArrayToFile(String fileName, String[] arr)
            throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(stringArrayToString(arr));
        }
    }

    public Film[] readFilmsFromFile(String fileName) {
        List<Film> lines = null;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(new Film(scanner.nextLine().split(" ")));
            }
            // обязательно, чтобы закрыть открытый файл
        } catch (FileNotFoundException e) {
        }
        return lines.toArray(new Film[0]);
    }

    public String stringArrayToString(String[] a) {
        String s = "";
        for (int i = 0; i < a.length; i++) {
            s += a[i] + "\n";
        }
        return s;
    }

    public String[] readLinesFromFile(String fileName) {
        List<String> lines = null;
        try (Scanner scanner = new Scanner(new File(fileName), "UTF-8")) {
            lines = new ArrayList<>();
            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
            // обязательно, чтобы закрыть открытый файл
        } catch (FileNotFoundException e) {
        }
        return lines.toArray(new String[0]);
    }

    public Film[] searchFilms(String filmName, String genre, boolean isLooked, String feel) {
        Film[] films = readFilmsFromFile(s.file(0));
        List<List<Film>> searchedFilms = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<Film> list = new ArrayList<>();
            searchedFilms.add(list);
        }
        for (int i = 0; i < films.length; i++) {
            int n = 0;
            if (films[i].getName().equals(filmName) && filmName != null) {
                n++;
            }
            if (films[i].getGenre().equals(genre) && !genre.equals(s.none())) {
                n++;
            }
            if (films[i].isLooked() == isLooked) {
                n++;
                if (!isLooked && films[i].getFeel().equals(feel)) {
                    n++;
                }
            }
            searchedFilms.get(n).add(films[i]);
        }
        List<Film> filmsTable = new ArrayList<>();
        for (List<Film> l: searchedFilms) {
            filmsTable.addAll(l);
        }
        return filmListToFilmArray(filmsTable);
    }

    public Film[] filmListToFilmArray(List<Film> list) {
        Film[] array = new Film[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public String[] filmArrayToStringArrayForFile(Film[] film) {
        String[] string = new String[film.length];
        for (int i = 0; i < film.length; i++) {
            string[i] = film[i].toString();
        }
        return string;
    }

    public String[][] filmArrayToStringMatrix(Film[] films) {
        String[][] s = new String[films.length][4];
        for (int i = 0; i < films.length; i++) {
            s[i] = films[i].toStringArray();
        }
        return s;
    }
}
