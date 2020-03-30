package Demo1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * @author admin
 *
 * 
 */

public class DrawText extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel ControlPanel;
    private JPanel pDraw;
    private JScrollPane spFontPro;
    private JLabel lblFontProperities;
    private JTextArea textArea;
    private JLabel lblControl;
    private JLabel lblColor;
    private JButton btnChooseColor;
    private JLabel lblFont;
    private JComboBox cbFont;
    private JLabel lblSize;
    private JTextField txtSize;
    private JLabel lblStyle;
    private JComboBox cbStyle;
    private JLabel lblDraw;
    private JTextField txtDrawText;
    private JLabel lblClick;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    DrawText frame = new DrawText();
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the frame.
     */

    Graphics g = null;
    Color color = Color.BLACK;
    Font font = null;
    int fontSize = 16;
    int fontStyle = Font.PLAIN;
    String fontname = Font.SANS_SERIF;
    String drawnText = "";
    JLabel lblpDraw;

    public DrawText() {
	initComponents();
	loadFonts();
	g = this.lblpDraw.getGraphics();
	font = new Font(fontname, fontStyle, fontSize);
	this.showProperities();
    }

    private void showProperities() {
	this.textArea.setText("");
	this.textArea.append("Family: " + font.getFamily() + "\n");
	this.textArea.append("Name: " + font.getName() + "\n");
	this.textArea.append("Style: " + font.getStyle() + "\n");
	this.textArea.append("Size: " + font.getSize() + "\n");
	if (g != null) {
	    FontMetrics fm = g.getFontMetrics();
	    textArea.append("\nFont Metrics \n");
	    this.textArea.append("Ascent: " + fm.getAscent() + "\n");
	    this.textArea.append("Max Ascent: " + fm.getMaxAscent() + "\n");
	    this.textArea.append("Descent: " + fm.getDescent() + "\n");
	    this.textArea.append("Leading: " + fm.getLeading() + "\n");
	    this.textArea.append("Height: " + fm.getHeight() + "\n");
	    this.textArea.append("Max advance: " + fm.getMaxAdvance() + "\n");
	    if (drawnText.length() > 0) this.textArea.append("String Width: " + fm.stringWidth(drawnText) + "\n");

	}
    }

    private void loadFonts() {
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	Font[] fonts = ge.getAllFonts();
	this.cbFont.removeAllItems();
	for (Font i : fonts) {
	    this.cbFont.addItem(i.getFontName());
	}

    }

    private void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 826, 524);
	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(this.contentPane);
	this.ControlPanel = new JPanel();
	this.pDraw = new JPanel();
	this.pDraw.setOpaque(false);
	this.pDraw.setBackground(Color.WHITE);

	this.pDraw.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	this.spFontPro = new JScrollPane();
	GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
	gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING,
	                gl_contentPane.createSequentialGroup().addContainerGap().addGroup(gl_contentPane
	                                .createParallelGroup(Alignment.TRAILING)
	                                .addComponent(this.ControlPanel, GroupLayout.DEFAULT_SIZE, 774, Short.MAX_VALUE)
	                                .addGroup(gl_contentPane.createSequentialGroup()
	                                                .addComponent(this.pDraw, GroupLayout.PREFERRED_SIZE, 517,
	                                                                GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(this.spFontPro,
	                                                                GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)))
	                                .addContainerGap()));
	gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addGroup(gl_contentPane
	                .createSequentialGroup().addContainerGap()
	                .addComponent(this.ControlPanel, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	                                .addComponent(this.spFontPro, GroupLayout.PREFERRED_SIZE, 314, GroupLayout.PREFERRED_SIZE)
	                                .addComponent(this.pDraw, GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE))
	                .addContainerGap()));
	this.pDraw.setLayout(new BorderLayout(0, 0));
	this.lblClick = new JLabel("Click Mouse To Draw Text");
	this.lblClick.setBackground(Color.WHITE);
	this.lblClick.setOpaque(true);
	this.lblClick.setForeground(Color.BLUE);
	this.lblClick.setHorizontalAlignment(SwingConstants.CENTER);
	this.lblClick.setFont(new Font("Arial", Font.BOLD, 16));
	this.pDraw.add(this.lblClick, BorderLayout.NORTH);

	this.lblpDraw = new JLabel("");
	this.lblpDraw.setOpaque(true);
	this.lblpDraw.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		lblpDrawMouseClicked(e);
	    }
	});
	this.pDraw.add(this.lblpDraw, BorderLayout.CENTER);

	this.lblFontProperities = new JLabel("Font Properities");
	this.lblFontProperities.setForeground(Color.BLUE);
	this.lblFontProperities.setHorizontalAlignment(SwingConstants.CENTER);
	this.lblFontProperities.setFont(new Font("Arial", Font.BOLD, 16));
	this.spFontPro.setColumnHeaderView(this.lblFontProperities);

	this.textArea = new JTextArea();
	this.textArea.setEditable(false);
	this.spFontPro.setViewportView(this.textArea);

	this.lblControl = new JLabel("Control panel");
	this.lblControl.setForeground(Color.BLUE);
	this.lblControl.setHorizontalAlignment(SwingConstants.CENTER);
	this.lblControl.setFont(new Font("Arial", Font.BOLD, 16));

	this.lblColor = new JLabel("");
	this.lblColor.setOpaque(true);
	this.lblColor.setBackground(Color.BLACK);
	this.lblColor.setBorder(new LineBorder(new Color(0, 0, 0)));
	this.btnChooseColor = new JButton("Choose Color");
	this.btnChooseColor.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnChooseColorActionPerformed(e);
	    }
	});
	this.lblFont = new JLabel("Font:");
	this.lblFont.setFont(new Font("Arial", Font.BOLD, 16));
	this.cbFont = new JComboBox();
	this.cbFont.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		cbFontActionPerformed(e);
	    }
	});
	this.lblSize = new JLabel("Size:");
	this.lblSize.setFont(new Font("Arial", Font.BOLD, 16));
	this.txtSize = new JTextField();
	this.txtSize.addFocusListener(new FocusAdapter() {
	    @Override
	    public void focusLost(FocusEvent e) {
		txtSizeFocusLost(e);
	    }
	});
	this.txtSize.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		txtFontActionPerformed(e);
	    }
	});
	this.txtSize.setHorizontalAlignment(SwingConstants.CENTER);
	this.txtSize.setColumns(10);
	this.lblStyle = new JLabel("Style");
	this.lblStyle.setFont(new Font("Arial", Font.BOLD, 16));
	this.cbStyle = new JComboBox();
	this.cbStyle.setFont(new Font("Arial", Font.BOLD, 15));
	this.cbStyle.setModel(new DefaultComboBoxModel(new String[] {"PLAIN", "BOLD", "ITALIC", "BOLD ITALIC"}));
	this.cbStyle.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		cbStyleActionPerformed(e);
	    }
	});
	this.lblDraw = new JLabel("Draw Text:");
	this.lblDraw.setHorizontalAlignment(SwingConstants.RIGHT);
	this.lblDraw.setFont(new Font("Arial", Font.BOLD, 16));
	this.txtDrawText = new JTextField();
	this.txtDrawText.setHorizontalAlignment(SwingConstants.CENTER);
	this.txtDrawText.setColumns(10);
	GroupLayout gl_ControlPanel = new GroupLayout(this.ControlPanel);
	gl_ControlPanel.setHorizontalGroup(gl_ControlPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_ControlPanel
	                .createSequentialGroup()
	                .addGroup(gl_ControlPanel.createParallelGroup(Alignment.LEADING)
	                                .addComponent(this.lblControl, GroupLayout.PREFERRED_SIZE, 129,
	                                                GroupLayout.PREFERRED_SIZE)
	                                .addGroup(gl_ControlPanel.createSequentialGroup().addGap(43)
	                                                .addComponent(this.lblColor, GroupLayout.PREFERRED_SIZE, 58,
	                                                                GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(ComponentPlacement.UNRELATED)
	                                                .addGroup(gl_ControlPanel.createParallelGroup(Alignment.LEADING, false)
	                                                                .addGroup(gl_ControlPanel.createSequentialGroup()
	                                                                                .addComponent(this.lblDraw,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                127,
	                                                                                                GroupLayout.PREFERRED_SIZE)
	                                                                                .addPreferredGap(
	                                                                                                ComponentPlacement.UNRELATED)
	                                                                                .addComponent(this.txtDrawText))
	                                                                .addGroup(gl_ControlPanel.createSequentialGroup()
	                                                                                .addComponent(this.btnChooseColor,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                138,
	                                                                                                GroupLayout.PREFERRED_SIZE)
	                                                                                .addPreferredGap(
	                                                                                                ComponentPlacement.RELATED)
	                                                                                .addComponent(this.lblFont,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                42,
	                                                                                                GroupLayout.PREFERRED_SIZE)
	                                                                                .addPreferredGap(
	                                                                                                ComponentPlacement.RELATED)
	                                                                                .addComponent(this.cbFont,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                146,
	                                                                                                GroupLayout.PREFERRED_SIZE)
	                                                                                .addPreferredGap(
	                                                                                                ComponentPlacement.RELATED)
	                                                                                .addComponent(this.lblSize,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                42,
	                                                                                                GroupLayout.PREFERRED_SIZE)
	                                                                                .addPreferredGap(
	                                                                                                ComponentPlacement.RELATED)
	                                                                                .addComponent(this.txtSize,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                49,
	                                                                                                GroupLayout.PREFERRED_SIZE)
	                                                                                .addPreferredGap(
	                                                                                                ComponentPlacement.RELATED)
	                                                                                .addComponent(this.lblStyle,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                42,
	                                                                                                GroupLayout.PREFERRED_SIZE)))
	                                                .addPreferredGap(ComponentPlacement.RELATED).addComponent(this.cbStyle,
	                                                                GroupLayout.PREFERRED_SIZE, 133,
	                                                                GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(39, Short.MAX_VALUE)));
	gl_ControlPanel.setVerticalGroup(gl_ControlPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_ControlPanel
	                .createSequentialGroup()
	                .addGroup(gl_ControlPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_ControlPanel
	                                .createSequentialGroup().addComponent(this.lblControl)
	                                .addPreferredGap(ComponentPlacement.UNRELATED)
	                                .addGroup(gl_ControlPanel.createParallelGroup(Alignment.LEADING)
	                                                .addComponent(this.lblStyle, GroupLayout.PREFERRED_SIZE, 33,
	                                                                GroupLayout.PREFERRED_SIZE)
	                                                .addGroup(gl_ControlPanel.createSequentialGroup().addGroup(gl_ControlPanel
	                                                                .createParallelGroup(Alignment.LEADING, false)
	                                                                .addGroup(gl_ControlPanel
	                                                                                .createParallelGroup(Alignment.BASELINE)
	                                                                                .addComponent(this.lblFont,
	                                                                                                GroupLayout.DEFAULT_SIZE,
	                                                                                                32, Short.MAX_VALUE)
	                                                                                .addComponent(this.cbFont,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                29,
	                                                                                                GroupLayout.PREFERRED_SIZE))
	                                                                .addComponent(this.lblColor, GroupLayout.DEFAULT_SIZE,
	                                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                                .addComponent(this.txtSize, GroupLayout.DEFAULT_SIZE, 33,
	                                                                                Short.MAX_VALUE)
	                                                                .addComponent(this.btnChooseColor,
	                                                                                GroupLayout.DEFAULT_SIZE,
	                                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                                                                .addComponent(this.lblSize, GroupLayout.DEFAULT_SIZE,
	                                                                                GroupLayout.DEFAULT_SIZE,
	                                                                                Short.MAX_VALUE))
	                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
	                                                                .addGroup(gl_ControlPanel
	                                                                                .createParallelGroup(Alignment.BASELINE)
	                                                                                .addComponent(this.lblDraw,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                33,
	                                                                                                GroupLayout.PREFERRED_SIZE)
	                                                                                .addComponent(this.txtDrawText,
	                                                                                                GroupLayout.PREFERRED_SIZE,
	                                                                                                33,
	                                                                                                GroupLayout.PREFERRED_SIZE)))))
	                                .addGroup(gl_ControlPanel.createSequentialGroup().addGap(36).addComponent(this.cbStyle,
	                                                GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
	                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
	this.ControlPanel.setLayout(gl_ControlPanel);
	this.contentPane.setLayout(gl_contentPane);
    }

    private void btnChooseColorActionPerformed(ActionEvent e) {
	Color c = JColorChooser.showDialog(this, "Choose a Color", Color.BLACK);
	if (c != null) {
	    color = c;
	}
	this.lblColor.setBackground(c);
    }

    private void cbFontActionPerformed(ActionEvent e) {
	this.fontname = cbFont.getSelectedItem().toString();
	font = new Font(fontname, fontStyle, fontSize);
	this.showProperities();
    }

    private void cbStyleActionPerformed(ActionEvent e) {
	String str = cbStyle.getSelectedItem().toString();
	if (str.equalsIgnoreCase("BOLD")) {
	    fontStyle = Font.BOLD;
	} else if (str.equalsIgnoreCase("ITALIC")) {
	    fontStyle = Font.ITALIC;
	} else if (str.equalsIgnoreCase("BOLD ITALIC")) {
	    fontStyle = Font.BOLD + Font.ITALIC;
	} else {
	    fontStyle = Font.PLAIN;
	}
	font = new Font(fontname, fontStyle, fontSize);
	this.showProperities();
    }

    private void txtFontActionPerformed(ActionEvent e) {
	if (!this.txtSize.getText().isEmpty()) {
	    fontSize = Integer.valueOf(this.txtSize.getText());
	}
	font = new Font(fontname, fontStyle, fontSize);
	g.setFont(font);
	this.showProperities();
    }

    private void txtSizeFocusLost(FocusEvent e) {
	if (!this.txtSize.getText().isEmpty()) {
	    fontSize = Integer.valueOf(this.txtSize.getText());
	}
	font = new Font(fontname, fontStyle, fontSize);
	g.setFont(font);
	this.showProperities();
    }

    private void lblpDrawMouseClicked(MouseEvent e) {
	int x = e.getX();
	int y = e.getY();
	font = new Font(fontname, fontStyle, fontSize);
	g = this.lblpDraw.getGraphics();
	g.setFont(font);
	g.setColor(color);
	drawnText = this.txtDrawText.getText();
	g.drawString(drawnText, x, y);
	this.showProperities();
    }
}
