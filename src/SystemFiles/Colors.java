package SystemFiles;

import java.awt.*;

public class Colors {
    private Color[] backgrounds = {
            new Color(255, 255, 255),
            new Color(212,255,115),
            new Color(76,255,80),
            new Color(255,125,51)
    };
    private Color[] foregrounds = {
            new Color(0, 0, 0)
    };

    public Color background(int i) {
        return backgrounds[i];
    }

    public Color foreground(int i) {
        return foregrounds[i];
    }
}
