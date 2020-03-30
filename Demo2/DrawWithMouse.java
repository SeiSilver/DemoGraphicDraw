package Demo2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

/**
 * @author admin
 *
 * 
 */

public class DrawWithMouse extends JFrame {

    private JPanel contentPane;
    private JButton btnSelectColor;
    private JRadioButton rdbtnDrawOnly;
    private JRadioButton rdbtnDrawFill;
    private JTabbedPane tabbedPane;
    private JPanel Lines;
    private JPanel Ellipses;
    private JPanel Rectangles;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    DrawWithMouse frame = new DrawWithMouse();
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
    Graphics g;
    Point p1 = null, p2 = null;
    Color color = Color.red;

    public DrawWithMouse() {
	initComponents();
	this.rdbtnDrawFill.setEnabled(false);
    }

    private void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 683, 506);
	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(this.contentPane);
	this.btnSelectColor = new JButton("Select Color");
	this.btnSelectColor.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		btnSelectColorActionPerformed(e);
	    }
	});
	this.rdbtnDrawOnly = new JRadioButton("Draw Only");
	this.rdbtnDrawOnly.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		rdbtnDrawOnlyActionPerformed(e);
	    }
	});
	this.rdbtnDrawOnly.setSelected(true);
	this.rdbtnDrawFill = new JRadioButton("Draw and Fill");
	this.rdbtnDrawFill.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
		rdbtnDrawFillActionPerformed(e);
	    }
	});
	this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	this.tabbedPane.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		tabbedPaneMouseClicked(e);
	    }
	});
	this.tabbedPane.setName("");

	GroupLayout gl_contentPane = new GroupLayout(this.contentPane);
	gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
	                .createSequentialGroup()
	                .addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
	                                .addGroup(gl_contentPane.createSequentialGroup().addGap(122)
	                                                .addComponent(this.btnSelectColor, GroupLayout.PREFERRED_SIZE, 134,
	                                                                GroupLayout.PREFERRED_SIZE)
	                                                .addGap(18)
	                                                .addComponent(this.rdbtnDrawOnly, GroupLayout.PREFERRED_SIZE, 133,
	                                                                GroupLayout.PREFERRED_SIZE)
	                                                .addPreferredGap(ComponentPlacement.UNRELATED)
	                                                .addComponent(this.rdbtnDrawFill, GroupLayout.PREFERRED_SIZE, 114,
	                                                                GroupLayout.PREFERRED_SIZE))
	                                .addGroup(gl_contentPane.createSequentialGroup().addContainerGap().addComponent(
	                                                this.tabbedPane, GroupLayout.DEFAULT_SIZE, 631, Short.MAX_VALUE)))
	                .addContainerGap()));
	gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
	                .createSequentialGroup().addContainerGap()
	                .addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
	                                .addComponent(this.btnSelectColor, GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
	                                .addComponent(this.rdbtnDrawFill, GroupLayout.PREFERRED_SIZE, 28,
	                                                GroupLayout.PREFERRED_SIZE)
	                                .addComponent(this.rdbtnDrawOnly, GroupLayout.PREFERRED_SIZE, 28,
	                                                GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(ComponentPlacement.RELATED)
	                .addComponent(this.tabbedPane, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE).addContainerGap()));
	{
	    this.Lines = new JPanel();
	    this.Lines.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
		    LineMousePressed(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		    LineMouseReleased(e);
		}
	    });
	    this.Lines.setName("");
	    this.tabbedPane.addTab("Lines", null, this.Lines, null);
	}
	{
	    this.Ellipses = new JPanel();
	    this.Ellipses.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
		    EllipsesMousePressed(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		    EllipsesMouseReleased(e);
		}
	    });
	    this.tabbedPane.addTab("Ellipses", null, this.Ellipses, null);
	}

	this.Rectangles = new JPanel();
	this.Rectangles.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mousePressed(MouseEvent e) {
		RectanglesMousePressed(e);
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
		RectanglesMouseReleased(e);
	    }
	});
	this.tabbedPane.addTab("Rectangles", null, this.Rectangles, null);
	this.contentPane.setLayout(gl_contentPane);
    }

    private void btnSelectColorActionPerformed(ActionEvent e) {
	Color c = JColorChooser.showDialog(this, "Choice a Color", Color.white);
	if (g != null) g.setColor(c);
    }

    private void tabbedPaneMouseClicked(MouseEvent e) {
	int index = this.tabbedPane.getSelectedIndex();
	switch (index) {
	case 0:
	    g = this.Lines.getGraphics();
	    this.rdbtnDrawFill.setSelected(false);
	    this.rdbtnDrawFill.setEnabled(false);
	    break;
	case 1:
	    g = this.Ellipses.getGraphics();
	    this.rdbtnDrawFill.setEnabled(true);
	    this.rdbtnDrawOnly.setSelected(true);
	    this.rdbtnDrawFill.setSelected(false);
	    break;
	case 2:
	    g = this.Rectangles.getGraphics();
	    this.rdbtnDrawFill.setEnabled(true);
	    this.rdbtnDrawOnly.setSelected(true);
	    this.rdbtnDrawFill.setSelected(false);
	    break;
	default:
	    break;
	}

    }

    private void rdbtnDrawFillActionPerformed(ActionEvent e) {
	if (this.rdbtnDrawFill.isSelected()) this.rdbtnDrawOnly.setSelected(false);
    }

    private void rdbtnDrawOnlyActionPerformed(ActionEvent e) {
	if (this.rdbtnDrawOnly.isSelected()) this.rdbtnDrawFill.setSelected(false);
    }

    private void LineMousePressed(MouseEvent e) {
	if (g == null) g = this.Lines.getGraphics();
	p1 = e.getPoint();
    }

    private void LineMouseReleased(MouseEvent e) {
	p2 = e.getPoint();
	g.drawLine(p1.x, p1.y, p2.x, p2.y);
	p1 = p2 = null;
    }

    private void EllipsesMousePressed(MouseEvent e) {
	p1 = e.getPoint();
    }

    private void EllipsesMouseReleased(MouseEvent e) {
	p2 = e.getPoint();
	int left = p1.x < p2.x ? p1.x : p2.x;
	int top = p1.y < p2.y ? p1.y : p2.y;
	int width = Math.abs(p1.x - p2.x);
	int height = Math.abs(p1.y - p2.y);
	if (this.rdbtnDrawOnly.isSelected()) g.drawOval(left, top, width, height);
	else g.fillOval(left, top, width, height);
	p1 = p2 = null;
    }

    private void RectanglesMousePressed(MouseEvent e) {
	p1 = e.getPoint();
    }

    private void RectanglesMouseReleased(MouseEvent e) {
	p2 = e.getPoint();
	int left = p1.x < p2.x ? p1.x : p2.x;
	int top = p1.y < p2.y ? p1.y : p2.y;
	int width = Math.abs(p1.x - p2.x);
	int height = Math.abs(p1.y - p2.y);
	if (this.rdbtnDrawOnly.isSelected()) g.drawRect(left, top, width, height);
	else g.fillRect(left, top, width, height);
	p1 = p2 = null;
    }

}
