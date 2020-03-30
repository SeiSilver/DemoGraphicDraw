package DemoImage;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author admin
 *
 * 
 */

public class ImageShow extends JFrame {

    private JPanel contentPane;
    private JSplitPane splitPane;
    private JPanel panel;
    private JLabel lblControl;
    private JPanel panel_1;
    private JButton btnAdd;
    private JLabel lblCurrent_1;
    private JPanel panel_2;
    private JScrollPane scrollPane;
    private JList list;
    private JButton btnRemove;
    private JButton btnClear;
    private JLabel lblAuto_1;
    private JCheckBox chckbxOnoff;
    private JSlider slider;
    private JLabel lblSecondsimage;
    private JPanel draw;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    ImageShow frame = new ImageShow();
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
    boolean autoSlide = false;
    int rate = 0;
    BufferedImage currentImage = null;
    JFileChooser fchooser = new JFileChooser();
    Vector<String> listF = new Vector<String>();
    int x = 10, y = 20;
    int imgIndex = -1;
    Graphics g = null;
    TimeThread timecounter;
    private JLabel lblView;

    public ImageShow() {
	setVisible(true);
	initComponents();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "GIF", "JPG", "IPEG", "PNG");
	fchooser.setFileFilter(filter);
	fchooser.setMultiSelectionEnabled(true);
	g = this.draw.getGraphics();
	this.slider.setEnabled(false);
	this.slider.setValue(1);
    }

    public void loadImage() {
	if (imgIndex == -1) return;
	String filename = this.listF.elementAt(imgIndex);
	try {
	    currentImage = ImageIO.read(new File(filename));
	} catch (Exception e) {
	    // TODO: handle exception
	    currentImage = null;
	    JOptionPane.showMessageDialog(this, e);
	}
    }

    public void showImage() {
	if (currentImage == null) return;
	g = draw.getGraphics();
	g.clearRect(x, y, this.draw.getWidth() - x, this.draw.getHeight() - y);
	int imgWidth = currentImage.getWidth();
	int imgHeight = currentImage.getHeight();
	double ratio = 1.0 * imgWidth / imgHeight;
	int areaWidth = this.draw.getWidth() - 2 * x;
	int areaHeight = this.draw.getHeight() - 2 * y;
	if (imgWidth <= areaWidth && imgHeight <= areaHeight) {
	    areaWidth = imgWidth;
	    areaHeight = imgHeight;
	} else if (imgWidth > imgHeight) {
	    if (imgWidth < areaWidth) areaWidth = imgWidth;
	    areaHeight = (int) (areaWidth / ratio);
	} else {
	    if (imgHeight < areaHeight) areaHeight = imgHeight;
	    areaWidth = (int) (areaHeight * ratio);
	}
	g.drawImage(currentImage, x, y, areaWidth, areaHeight, draw);
    }

    class TimeThread extends Thread {
	@Override
	public void run() {
	    imgIndex = list.getSelectedIndex();
	    int n = list.getModel().getSize(); // number of files
	    if (n > 0 && autoSlide) { // auto—slide condition
		while (imgIndex < n) { // sliding to the end of the list
		    try {
			loadImage();
			showImage();
			imgIndex++;
			sleep(1000 * rate);
		    } catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			break;
		    }
		}
	    }
	}
    }

    private void initComponents() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 778, 594);
	this.contentPane = new JPanel();
	this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	this.contentPane.setLayout(new BorderLayout(0, 0));
	setContentPane(this.contentPane);
	{
	    this.splitPane = new JSplitPane();
	    this.contentPane.add(this.splitPane, BorderLayout.CENTER);
	    {
		this.panel = new JPanel();
		this.splitPane.setLeftComponent(this.panel);
		this.panel.setLayout(new BorderLayout(0, 0));
		{
		    this.lblControl = new JLabel("Control Panel");
		    this.lblControl.setFont(new Font("Arial", Font.BOLD, 18));
		    this.lblControl.setHorizontalAlignment(SwingConstants.CENTER);
		    this.panel.add(this.lblControl, BorderLayout.NORTH);
		}
		{
		    this.panel_1 = new JPanel();
		    this.panel.add(this.panel_1, BorderLayout.CENTER);
		    this.btnAdd = new JButton("Add Image Files");
		    this.btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    btnAddActionPerformed(e);
			}
		    });
		    this.lblCurrent_1 = new JLabel("Current Files");
		    this.lblCurrent_1.setFont(new Font("Arial", Font.BOLD, 16));
		    this.panel_2 = new JPanel();
		    this.scrollPane = new JScrollPane();
		    this.btnRemove = new JButton("Remove");
		    this.btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    btnRemoveActionPerformed(e);
			}
		    });
		    this.btnClear = new JButton("Clear");
		    this.btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    btnClearActionPerformed(e);
			}
		    });
		    GroupLayout gl_panel_1 = new GroupLayout(this.panel_1);
		    gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
		                    .createSequentialGroup().addContainerGap()
		                    .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
		                                    .addGroup(gl_panel_1.createSequentialGroup()
		                                                    .addComponent(this.panel_2, GroupLayout.PREFERRED_SIZE, 275,
		                                                                    Short.MAX_VALUE)
		                                                    .addContainerGap())
		                                    .addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
		                                                    .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
		                                                                    .addComponent(this.scrollPane,
		                                                                                    Alignment.LEADING,
		                                                                                    GroupLayout.DEFAULT_SIZE, 277,
		                                                                                    Short.MAX_VALUE)
		                                                                    .addComponent(this.lblCurrent_1,
		                                                                                    Alignment.LEADING,
		                                                                                    GroupLayout.PREFERRED_SIZE,
		                                                                                    139,
		                                                                                    GroupLayout.PREFERRED_SIZE)
		                                                                    .addComponent(this.btnAdd, Alignment.LEADING,
		                                                                                    GroupLayout.DEFAULT_SIZE, 283,
		                                                                                    Short.MAX_VALUE))
		                                                    .addGap(10))
		                                    .addGroup(gl_panel_1.createSequentialGroup()
		                                                    .addComponent(this.btnRemove, GroupLayout.PREFERRED_SIZE, 119,
		                                                                    GroupLayout.PREFERRED_SIZE)
		                                                    .addPreferredGap(ComponentPlacement.RELATED, 39,
		                                                                    Short.MAX_VALUE)
		                                                    .addComponent(this.btnClear, GroupLayout.PREFERRED_SIZE, 117,
		                                                                    GroupLayout.PREFERRED_SIZE)
		                                                    .addContainerGap()))));
		    gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
		                    .createSequentialGroup().addContainerGap().addComponent(this.btnAdd)
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addComponent(this.lblCurrent_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(this.scrollPane, GroupLayout.PREFERRED_SIZE, 272, GroupLayout.PREFERRED_SIZE)
		                    .addPreferredGap(ComponentPlacement.UNRELATED)
		                    .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(this.btnRemove)
		                                    .addComponent(this.btnClear))
		                    .addPreferredGap(ComponentPlacement.RELATED)
		                    .addComponent(this.panel_2, GroupLayout.PREFERRED_SIZE, 96, Short.MAX_VALUE)
		                    .addContainerGap()));
		    this.lblAuto_1 = new JLabel("Auto Sliding");
		    this.lblAuto_1.setFont(new Font("Arial", Font.BOLD, 16));
		    this.chckbxOnoff = new JCheckBox("On/Off");
		    this.chckbxOnoff.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    chckbxOnoffMouseClicked(e);
			}
		    });
		    this.chckbxOnoff.setFont(new Font("Arial", Font.BOLD, 16));
		    this.chckbxOnoff.setHorizontalAlignment(SwingConstants.CENTER);
		    this.slider = new JSlider();
		    this.slider.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
			    sliderMouseReleased(e);
			}
		    });
		    this.lblSecondsimage = new JLabel("Seconds/Image");
		    this.lblSecondsimage.setFont(new Font("Arial", Font.BOLD, 13));
		    this.lblSecondsimage.setHorizontalAlignment(SwingConstants.RIGHT);
		    GroupLayout gl_panel_2 = new GroupLayout(this.panel_2);
		    gl_panel_2.setHorizontalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
		                    .createSequentialGroup().addContainerGap()
		                    .addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
		                                    .addGroup(gl_panel_2.createSequentialGroup()
		                                                    .addComponent(this.slider, GroupLayout.DEFAULT_SIZE, 253,
		                                                                    Short.MAX_VALUE)
		                                                    .addContainerGap())
		                                    .addGroup(Alignment.TRAILING,
		                                                    gl_panel_2.createSequentialGroup()
		                                                                    .addComponent(this.lblAuto_1)
		                                                                    .addPreferredGap(ComponentPlacement.RELATED,
		                                                                                    60, Short.MAX_VALUE)
		                                                                    .addComponent(this.chckbxOnoff).addGap(22))))
		                    .addGroup(Alignment.TRAILING,
		                                    gl_panel_2.createSequentialGroup().addGap(96)
		                                                    .addComponent(this.lblSecondsimage, GroupLayout.DEFAULT_SIZE,
		                                                                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                                                    .addGap(87)));
		    gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_2
		                    .createSequentialGroup().addContainerGap()
		                    .addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE).addComponent(this.chckbxOnoff)
		                                    .addComponent(this.lblAuto_1))
		                    .addPreferredGap(ComponentPlacement.RELATED).addComponent(this.lblSecondsimage)
		                    .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		                    .addComponent(this.slider, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
		                    .addContainerGap()));
		    this.panel_2.setLayout(gl_panel_2);
		    {
			this.list = new JList(listF);
			this.list.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
				listMouseClicked(e);
			    }

			});
			this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.scrollPane.setViewportView(this.list);
		    }
		    this.panel_1.setLayout(gl_panel_1);
		}
	    }
	    {
		this.draw = new JPanel();
		this.splitPane.setRightComponent(this.draw);
		this.draw.setLayout(new BorderLayout(0, 0));

		this.lblView = new JLabel("View Panel");
		this.lblView.setFont(new Font("Arial", Font.BOLD, 16));
		this.lblView.setHorizontalAlignment(SwingConstants.CENTER);
		this.draw.add(this.lblView, BorderLayout.NORTH);
	    }
	    this.splitPane.setDividerLocation(300);
	}
    }

    private void btnAddActionPerformed(ActionEvent e) {
	int returnVal = fchooser.showOpenDialog(this);
	if (returnVal == JFileChooser.APPROVE_OPTION) {
	    File[] files = fchooser.getSelectedFiles();
	    for (File file : files) {
		listF.add(file.getAbsolutePath());
	    }
	    list.setSelectedIndex(0);
	    this.list.updateUI();
	}
    }

    private void btnRemoveActionPerformed(ActionEvent e) {
	listF.removeElementAt(list.getSelectedIndex());
	list.updateUI();
    }

    private void btnClearActionPerformed(ActionEvent e) {
	listF.removeAllElements();
	list.updateUI();
    }

    private void listMouseClicked(MouseEvent e) {
	imgIndex = list.getSelectedIndex();
	this.loadImage();
	this.showImage();
    }

    private void chckbxOnoffMouseClicked(MouseEvent e) {
	if (this.chckbxOnoff.isSelected() && list.getSelectedIndex() >= 0) {
	    this.autoSlide = true;
	    this.slider.setEnabled(true);
	    this.rate = this.slider.getValue();
	    slidingImage();
	} else {
	    if (timecounter != null) timecounter = null;
	    autoSlide = false;
	    this.slider.setEnabled(false);
	}
    }

    private void slidingImage() {
	// TODO Auto-generated method stub
	timecounter = new TimeThread();
	timecounter.start();

    }

    private void sliderMouseReleased(MouseEvent e) {
	this.rate = this.slider.getValue();
    }
}
