package edu.umgc.skhalar.ui.components;

import java.awt.Font;
import java.awt.FontMetrics;
import java.io.Serial;

import javax.swing.JLabel;

/**
 * A JLabel where the font resizes to fit the container
 */
public class AutoSizeLabel extends JLabel {
	@Serial
	private static final long serialVersionUID = 1L;

	public AutoSizeLabel setTextWithAutoSize(String text) {
        setText(text);

        // Get the label's size
        int labelWidth = getWidth();
        int labelHeight = getHeight();

        // Start with a reasonable font size
        int fontSize = 100;
        Font font = getFont();

        // Keep reducing font size until the text fits
        while (fontSize > 0) {
            font = new Font(font.getName(), font.getStyle(), fontSize);
            final FontMetrics metrics = getFontMetrics(font);
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();

            // Check if text fits within label bounds
            if (textWidth <= labelWidth && textHeight <= labelHeight) {
                setFont(font);
                break;
            }
            fontSize--;
        }

        repaint();
        return this;
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        // Readjust text size when label is resized
        if (getText() != null && !getText().isEmpty()) {
            setTextWithAutoSize(getText());
        }
    }
}