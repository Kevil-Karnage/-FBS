package Program;

import SystemFiles.Colors;
import SystemFiles.Film.Film;
import SystemFiles.Strings;
import Utils.JTableUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Window extends JFrame{
    private JTabbedPane menu;
    private JPanel mainPanel;
    private JPanel filmContentPanel;
    private JPanel serialsPanel;
    private JPanel filmsPanel;
    private JPanel booksPanel;
    private JTextField filmNameTextField;
    private JComboBox filmFeelBox;
    private JRadioButton filmLookedRButton;
    private JButton filmSaveButton;
    private JLabel filmWriteLabel;
    private JComboBox filmGenreBox;
    private JLabel filmFeelLabel;
    private JTable searchedFilms;
    private JButton filmSearchButton;
    private JPanel searchPanel;
    private JPanel filmDataPanel;
    private JPanel filmAddDataPanel;
    private JPanel filmButtonsPanel;
    private JPanel filmWritePanel;
    private JPanel filmSearchedPanel;
    private JLabel filmNameLabel;
    private JLabel filmGenreLabel;
    private JLabel filmLookedLabel;

    private Strings s = new Strings();
    private Colors c = new Colors();
    private Service service = new Service(s, c);

    public Window() {
        this.setTitle(s.title());
        this.setContentPane(mainPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();



        filmSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFilmSearch(false);

                String name = filmNameTextField.getText();
                String genre = (String) (filmGenreBox.getSelectedItem().equals(s.genre(0)) ? s.none() : filmGenreBox.getSelectedItem());
                boolean isLooked = filmLookedRButton.isSelected();
                String feel = (String) (filmFeelBox.getSelectedItem().equals(s.feel(0)) ? s.none() : filmFeelBox.getSelectedItem());

                Film newFilm = null;
                if (!name.isEmpty()) {
                    if (isLooked) {
                        newFilm = new Film(name, genre, true, feel);
                    } else {
                        newFilm = new Film(name, genre, false, s.none());
                    }
                }
                if (newFilm != null) {
                    try {
                        String text = service.addFilm(newFilm);
                        filmWriteLabel.setText(text);
                        Color filmWriteColor;
                        if (text.equals(s.save(0))) {
                            filmWriteColor = c.background(2);
                        } else {
                            filmWriteColor = c.background(3);
                        }
                        filmWritePanel.setBackground(filmWriteColor);
                        filmWriteLabel.setBackground(filmWriteColor);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                } else {
                    filmWriteLabel.setText(s.save(2));
                }
            }
        });

        filmSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setFilmSearch(true);

                Film[] searchedF = service.searchFilms(
                        filmNameTextField.getText(),
                        (String) filmGenreBox.getSelectedItem(),
                        filmLookedRButton.isSelected(),
                        (String) filmFeelBox.getSelectedItem()
                );
                String[][] verticalFilms = service.filmArrayToStringMatrix(searchedF);
                JTableUtils.writeArrayToJTable(searchedFilms, verticalFilms);
            }
        });

        filmLookedRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filmLookedRButton.isSelected()) {
                    filmFeelBox.setEnabled(true);
                    filmFeelLabel.setEnabled(true);
                } else {
                    filmFeelBox.setEnabled(false);
                    filmFeelLabel.setEnabled(false);
                }
            }
        });
    }

    private void setFilmSearch(boolean isSearch) {
        if (isSearch) {
            filmWritePanel.setVisible(false);
            filmWriteLabel.setVisible(false);
        } else {
            filmWriteLabel.setVisible(true);
            filmWritePanel.setVisible(true);
        }
    }
}
