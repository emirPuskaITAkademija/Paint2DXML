package app.action;

import app.PaintWindow;
import app.picture.PaintPanel;
import app.picture.parser.XMLParser;
import app.shape.PaintShape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class OpenListener implements ActionListener {

    private static final String PICTURE_EXTENSION = ".nfs";

    private final XMLParser xMLParser;

    public OpenListener(XMLParser xMLParser) {
        this.xMLParser = xMLParser;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String pictureName = JOptionPane.showInputDialog("Unesite ime slike:");
        if (pictureName == null || pictureName.trim().isEmpty()) {
            return;
        }
        if (!pictureName.endsWith(PICTURE_EXTENSION)) {
            pictureName += PICTURE_EXTENSION;
        }
        List<PaintShape> paintShapes = xMLParser.readPicture(pictureName);
        PaintPanel paintPanel = PaintWindow.getInstance().getPaintPanel();
        paintPanel.getPaintShapes().clear();
        paintPanel.getPaintShapes().addAll(paintShapes);
        paintPanel.repaint();
    }
}
