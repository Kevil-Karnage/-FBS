package Program;

import SystemFiles.Film.Film;
import Utils.JTableUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class Window extends JFrame{
    private JTabbedPane menu;
    private JPanel contentPanel;
    private JPanel addFilm;
    private JPanel serialsPanel;
    private JPanel filmsPanel;
    private JPanel booksPanel;
    private JTextField filmName;
    private JComboBox filmFeel;
    private JRadioButton filmIsLookedRButton;
    private JButton filmSaveButton;
    private JLabel saveCompleted;
    private JComboBox filmGenreBox;
    private JLabel feelLabel;
    private JTable searchedFilms;
    private JButton filmSearchButton;
    private JPanel searchPanel;

    public Window() {
        this.setTitle("FBS");
        this.setContentPane(contentPanel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();

        filmSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchedFilms.setVisible(false);
                searchPanel.setVisible(false);
                saveCompleted.setVisible(true);

                String name = filmName.getText();
                String genre = (String) (filmGenreBox.getSelectedItem().equals("Выбрать...") ? "null" : filmGenreBox.getSelectedItem());
                boolean isLooked = filmIsLookedRButton.isSelected();
                String feel = (String) (filmFeel.getSelectedItem().equals("Выбрать...") ? "null" : filmFeel.getSelectedItem());

                Film newFilm = null;
                if (!name.isEmpty()) {
                    if (isLooked) {
                        newFilm = new Film(name, genre, true, feel);
                    } else {
                        newFilm = new Film(name, genre, false, "null");
                    }
                }
                if (newFilm != null) {
                    try {
                        String text = Service.addFilm(newFilm);
                        saveCompleted.setText(text);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                } else {
                    saveCompleted.setText("Не хватает данных");
                }
            }
        });

        filmSearchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchedFilms.setVisible(true);
                searchPanel.setVisible(true);
                saveCompleted.setVisible(false);

                Film[] searchedF = Service.searchFilms(
                        filmName.getText(),
                        (String) filmGenreBox.getSelectedItem(),
                        filmIsLookedRButton.isSelected(),
                        (String) filmFeel.getSelectedItem()
                );
                //String[] films = Service.filmArrayToStringArrayForJTable(searchedF);
                String[][] verticalFilms = Service.filmArrayToVerticalStringArrayForJTable(searchedF);
                JTableUtils.writeArrayToJTable(searchedFilms, verticalFilms);
            }
        });

        filmIsLookedRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (filmIsLookedRButton.isSelected()) {
                    filmFeel.setEnabled(true);
                    feelLabel.setEnabled(true);
                } else {
                    filmFeel.setEnabled(false);
                    feelLabel.setEnabled(false);
                }
            }
        });
    }
}
