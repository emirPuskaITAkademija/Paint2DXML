package app;

import app.action.ExitListener;
import app.action.OpenListener;
import app.action.SaveListener;
import app.picture.PaintPanel;
import app.picture.parser.dom.DOMParser;
import app.picture.parser.jaxb.JAXBParser;
import app.picture.parser.sax.SAXParser;
import app.picture.parser.stax.StaxParser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class PaintWindow extends JFrame {

    private static PaintWindow INSTANCE = null;

    private final JRadioButton squareRadioButton = new JRadioButton("Kvadrat");
    private final JRadioButton circleRadioButton = new JRadioButton("Krug");

    private final JRadioButton blueRadioButton = new JRadioButton("Plava");
    private final JRadioButton redRadioButton = new JRadioButton("Crvena");

    private final PaintPanel paintPanel = new PaintPanel();

    private PaintWindow() {
        setTitle("Paint 2D");
        setSize(800, 600);
        add(getSettingsPanel(), BorderLayout.NORTH);
        add(paintPanel);
        //Menu bar
        JMenuBar menuBar = new JMenuBar();
        //Prva traka unutar menu bara
        JMenu fileMenu = new JMenu("File");
        //Save, Open, Exit
        JMenu saveMenu = new JMenu("Snimi");
        JMenuItem saxSaveMenuItem = new JMenuItem("SAX save operation");
        saxSaveMenuItem.addActionListener(new SaveListener(new SAXParser()));
        JMenuItem domSaveMenuItem = new JMenuItem("DOM save operation");
        domSaveMenuItem.addActionListener(new SaveListener(new DOMParser()));
        JMenuItem staxSaveMenuItem = new JMenuItem("Stax save operation");
        staxSaveMenuItem.addActionListener(new SaveListener(new StaxParser()));
        JMenuItem jaxbSaveMenuItem = new JMenuItem("JAXB save operation");
        jaxbSaveMenuItem.addActionListener(new SaveListener(new JAXBParser()));
        saveMenu.add(saxSaveMenuItem);
        saveMenu.add(domSaveMenuItem);
        saveMenu.add(staxSaveMenuItem);
        saveMenu.add(jaxbSaveMenuItem);

        JMenu openMenu = new JMenu("Otvori");
        JMenuItem saxOpenMenuItem = new JMenuItem("SAX open operation");
        saxOpenMenuItem.addActionListener(new OpenListener(new SAXParser()));
        JMenuItem domOpenMenuItem = new JMenuItem("DOM open operation");
        domOpenMenuItem.addActionListener(new OpenListener(new DOMParser()));
        JMenuItem staxOpenMenuItem = new JMenuItem("Stax open operation");
        staxOpenMenuItem.addActionListener(new OpenListener(new StaxParser()));
        JMenuItem jaxbOpenMenuItem = new JMenuItem("JAXB open operation");
        jaxbOpenMenuItem.addActionListener(new OpenListener(new JAXBParser()));
        openMenu.add(saxOpenMenuItem);
        openMenu.add(domOpenMenuItem);
        openMenu.add(staxOpenMenuItem);
        openMenu.add(jaxbOpenMenuItem);

        JMenuItem exitMenuItem = new JMenuItem("Izađi");
        exitMenuItem.addActionListener(new ExitListener());

        fileMenu.add(saveMenu);
        fileMenu.add(openMenu);
        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);
    }

    private JPanel getSettingsPanel() {
        JPanel settingsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel shapePanel = new JPanel();
        shapePanel.add(circleRadioButton);
        circleRadioButton.setSelected(true);
        shapePanel.add(squareRadioButton);
        TitledBorder shapeTitledBorder = new TitledBorder("Oblik");
        shapePanel.setBorder(shapeTitledBorder);
        ButtonGroup shapeButtonGroup = new ButtonGroup();
        shapeButtonGroup.add(circleRadioButton);
        shapeButtonGroup.add(squareRadioButton);

        JPanel colorPanel = new JPanel();
        colorPanel.add(redRadioButton);
        redRadioButton.setSelected(true);
        colorPanel.add(blueRadioButton);
        TitledBorder colorTitledBorder = new TitledBorder("Boja");
        colorPanel.setBorder(colorTitledBorder);
        ButtonGroup colorButtonGroup = new ButtonGroup();
        colorButtonGroup.add(redRadioButton);
        colorButtonGroup.add(blueRadioButton);

        settingsPanel.add(shapePanel);
        settingsPanel.add(colorPanel);
        return settingsPanel;
    }

    public PaintPanel getPaintPanel() {
        return paintPanel;
    }

    public JRadioButton getRedRadioButton() {
        return redRadioButton;
    }

    public JRadioButton getBlueRadioButton() {
        return blueRadioButton;
    }

    public JRadioButton getCircleRadioButton() {
        return circleRadioButton;
    }

    public JRadioButton getSquareRadioButton() {
        return squareRadioButton;
    }

    public static PaintWindow getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PaintWindow();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PaintWindow.getInstance().setDefaultCloseOperation(EXIT_ON_CLOSE);
            PaintWindow.getInstance().setVisible(true);
        });
    }

}
