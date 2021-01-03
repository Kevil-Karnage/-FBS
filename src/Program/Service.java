package Program;

import SystemFiles.Film.Film;
import Utils.ArrayUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Service {
    private static String fileName = "src/SystemFiles/Film/Films";
    public static String addFilm (Film film) throws FileNotFoundException {
        Film[] films = readFilmsFromFile(fileName);
        int n = filmArrayContainsFilm(films, film);
        if (n == -1) {
            Film[] updatedFilms = Arrays.copyOf(films, films.length + 1);
            updatedFilms[updatedFilms.length - 1] = film;
            writeFilmsToFile(fileName, updatedFilms);
            return "Сохранено";
        } else {
            return "Такой фильм уже есть";
        }
    }

    private static int filmArrayContainsFilm(Film[] array, Film film) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getName().equals(film.getName())) {
                return i;
            }
        }
        return -1;
    }

    public static void writeFilmsToFile(String fileName, Film[] films) throws FileNotFoundException {
        String[] s = new String[films.length];
        for (int i = 0; i < films.length; i++) {
            s[i] = films[i].toString();
        }
        writeArrayToFile(fileName, s);
    }

    public static void writeArrayToFile(String fileName, String[] arr)
            throws FileNotFoundException {
        try (PrintWriter out = new PrintWriter(fileName)) {
            out.println(stringArrayToString(arr));
        }
    }

    public static Film[] readFilmsFromFile(String fileName) {
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

    public static String stringArrayToString(String[] a) {
        String s = "";
        for (int i = 0; i < a.length; i++) {
            s += a[i] + "\n";
        }
        return s;
    }

    public static String[] readLinesFromFile(String fileName) {
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

    public static Film[] searchFilms(String filmName, String genre, boolean isLooked, String feel) {
        Film[] films = readFilmsFromFile(fileName);
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
            if (films[i].getGenre().equals(genre) && !genre.equals("null")) {
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

    public static Film[] filmListToFilmArray(List<Film> list) {
        Film[] array = new Film[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String[] filmArrayToStringArrayForFile(Film[] film) {
        String[] string = new String[film.length];
        for (int i = 0; i < film.length; i++) {
            string[i] = film[i].toString();
        }
        return string;
    }

    public static String[][] filmArrayToVerticalStringArrayForJTable(Film[] film) {
        String[][] string = new String[film.length][1];
        for (int i = 0; i < film.length; i++) {
            string[i][0] = film[i].toStringForJTable();
        }
        return string;
    }


}
