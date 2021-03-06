package main.guiFrames;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import main.Analyze.AnalyzeSources;
import main.database.MySQLConnector;
import main.database.TableTuple;

import static main.database.MySQLConnector.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * This class implements the main windows of the GUI
 * 
 * @author viviansh
 * @author ward-mattar
 * @author osherh
 */

public class MainFrame {

	private JFrame frame;
	private boolean startFlag;
	private JTextField searchTxt;
	private JTable table;
	private JButton btnSearch;
	private RefineTable inputList;
	private JCheckBox chckbxName;
	private JCheckBox chckbxDate;
	private JCheckBox chckbxReason;
	private JMenuItem mntmAbout;
	private JPopupMenu popupMenu;
	private JMenuItem mnfilter;
	private JMenuItem mnprofile;
	private JMenu mnHelp;
	private JCheckBox chckbxSortby;
	private JCheckBox chckbxFilterBy;
	@SuppressWarnings("unused")
	private static MySQLConnector connector;
	private JComboBox<String> comboBox;
	private JTextPane txtpnChooseOneFrom;
	private JScrollPane js;
	private InfoExtractor personalInfo;
	private JButton btnLoadAnalyzeResults;
	private JLabel lblImage;
	private JCheckBox chckbxSearch;
	private JButton btnAddEvents;
	/**
	 * sources to analyze
	 */
	private static final String src1 = "Tito Ortiz was arrested for driving under the influence after he drove his Porsche Panamera into the concrete median on Jan 6 on the 405 freeway in L.A. at 4am.\n"
			+ "David Cassidy was arrested for driving under influence on Jan 10 after he allegedly took a breathalyzer test and was found to be over twice the legal limit.\n"
			+ "Soulja Boy was arrested for possession of a loaded gun in Los Angeles on Jan 22.\n"
			+ "Justin Bieber was arrested for driving under the influence, driving with an expired license, and non-violently resisting arrest in Miami Beach on July 4th. \n Justin Bieber was arrested again for dangerous driving and assault after an alleged collision between a mini-van and the pop-star's ATV on Sep 1.\n"
			+ "Chris Kattan was arrested for drunk driving on Feb 10 on the 101 Freeway in the San Fernando Valley. Kattan was reportedly weaving erratically on the freeway before running into the back of a Department of Transportation truck.\n"
			+ "Sam Worthington was arrested after punching a paparazzo who had allegedly kicked Worthington's girlfriend in the shin on Feb 23 in New York.\n"
			+ "Chris Pine was arrested for driving under the influence in New Zealand on Mar 1. Pine plead guilty and was forced to pay $93 New Zealand dollars (around $79 U.S.). Pine also had his New Zealand driver's license suspended for six months.\n"
			+ "Chris Brown was arrested for violating his probation on Mar 14 after he was kicked out of rehab for failure to follow the rules of the program.\n"
			+ "Columbus Short was arrested for physically attacking his wife on February 14 2014.\n"
			+ "Columbus Short was arrested for allegedly punching a man in a bar fight on March 21 2014.\n"
			+ "Judge Joe Brown was arrested for contempt of court on March 26 after an outburst he allegedly made while representing a woman during a child support hearing.\n"
			+ "Joe Francis was arrested for allegedly attacking an employee at the Girls Gone Wild offices in L.A on May 16.\n"
			+ "Ray J was arrested after allegedly groping a woman in the lobby on May 30 at the Beverly Wilshire Hotel. However, he reportedly refused to leave and allegedly began behaving belligerently towards police.\n"
			+ "Hope Sollo was arrested after assaulting her half-sister and her nephew on June 21 .\n"
			+ "Shia LaBeouf was arrested after disrupting a performance of Cabaret at New York's Studio 54 on June 26 , and then refusing to leave the theater.\n"
			+ "Rick Ross was arrested for possession of marijuana on June 27.\n"
			+ "Will Hayden was arrested for molestation and rape of a pre-teen girl on Aug 11 . Several additional charges of aggravated rape of different girls were added after the initial charges.\n"
			+ "Keyshia Cole was arrested after allegedly assaulting a woman who had supposedly been spending a lot of time with Cole's boyfriend on Sep 18 .\n"
			+ "Amanda Bynes was arrested for driving under the influence of a controlled substance on Sep 28.\n"
			+ "Michael Phelps was arrested for driving under the influence on Sep 30 in Baltimore after failing a field sobriety test.\n"
			+ "Waka Flocka Flame was arrested after security and police at Atlanta's Hartsfield-Jackson International Airport found a handgun in his carry-on luggage on Oct 10.\n"
			+ "Nicholas Brendon was arrested for allegedly causing a disturbance in the hotel lobby outside of the Tree City Comic Con on Oct 17 at a hotel in Boise, Idaho.\n"
			+ "Suge Knight & Kat Williams  were arrested  for allegedly stealing a camera from a female paparazzo on Oct 29.\n"
			+ "Andy Dick was arrested after allegedly stealing a necklace off a man on Hollywood Blvd on Nov 8. He was charged with grand theft.\n"
			+ "Buddy Valastro was arrested for intoxicated driving on Nov 13. He allegedly told police, You can't arrest me! I'm the Cake Boss.\n"
			+ "Dustin Diamond was arrested for possession of a switchblade which the actor allegedly pulled out during a bar fight in which a man was stabbed on Dec 26.\n";

	private static final String src2 = "Ricardo Medina Jr. was arrested for allegedly stabbing his roommate to death with a sword on Feb 1. No charges against Medina were ever filed.\n"
			+ "John Stamos was arrested for allegedly driving under the influence on June 12 after the Beverly Hills police received numerous calls reporting a possible drunk driver. He pleaded no contest to one misdemeanor account of driving under the influence and was placed under three years of probation.\n"
			+ "Sean Combs was arrested for allegedly assaulting with a deadly weapon on June 22. Diddy's son, Justin, is a defensive back for the Bruins, and the rapper got into an altercation with his son's coach, allegedly attacking him with a kettlebell. Later the Los Angeles County District Attorney declined to file felony charges and passed the case to the Los Angeles City Attorney for a possible misdemeanor prosecution. Diddy was granted an office hearing, which seeks to resolve the matter without any criminal charges.\n"
			+ "Kim Richards was arrested for shoplifting from a Target in Van Nuys, California on Aug 2.\n"
			+ "Mark Salling was arrested for felony possession of child pornography on Dec 29. His representative had no comment on the matter.";

	/**
	 * Launch the application.
	 */
	public static void main(final String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				final MainFrame window = new MainFrame();

				/* right click in table */
				window.table.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						Point point = e.getPoint();
						int currentRow = window.table.rowAtPoint(point);
						int currentcolumn = window.table.columnAtPoint(point);
						window.table.setRowSelectionInterval(currentRow, currentRow);
						window.table.setColumnSelectionInterval(currentcolumn, currentcolumn);
						window.mnprofile
								.setVisible(window.table.getColumnName(window.table.getSelectedColumn()) == "Name");
					}

				});
				window.mnfilter.addActionListener(l -> {
					try {
						if (window.table.getSelectedColumn() != -1 && window.table.getSelectedRow() != -1) {
							String columnName = window.table.getColumnName(window.table.getSelectedColumn());
							if (columnName == "Date")
								columnName = "Year";
							window.inputList.filterBy((DefaultTableModel) window.table.getModel(), columnName,
									(String) window.table.getValueAt(window.table.getSelectedRow(),
											window.table.getSelectedColumn()),
									FilterType.RIGHT_CLICK);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "problem with sql connector", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}

				});

				window.mnprofile.addActionListener(l -> {

					JPanel pan = new JPanel();
					pan.setLayout(new FlowLayout((FlowLayout.LEFT)));
					JTextPane infoText = new JTextPane();
					String name = (String) window.table.getValueAt(window.table.getSelectedRow(),
							window.table.getSelectedColumn());
					infoText.setText(window.personalInfo.getCelebInfoString(name));
					infoText.setBackground(new Color(0, 0, 0, 0));
					pan.add(infoText);
					JDialog mydialog = new JDialog();
					mydialog.setSize(new Dimension(480, 290));
					mydialog.setTitle("Personal info of " + name);
					mydialog.add(pan);
					mydialog.setVisible(true);
				});

				window.frame.setVisible(true);
				window.onClickCheckBox(window.chckbxDate, window.chckbxName, window.chckbxReason);
				window.onClickCheckBox(window.chckbxName, window.chckbxDate, window.chckbxReason);
				window.onClickCheckBox(window.chckbxReason, window.chckbxName, window.chckbxDate);
				/*
				 * chckbx on click
				 * 
				 */
				window.chckbxSortby.addActionListener(l1 -> {
					window.removeSelected();
					window.comboBox.setVisible(false);
					window.txtpnChooseOneFrom.setVisible(false);
					window.chckbxFilterBy.setSelected(false);
					window.chckbxSearch.setSelected(false);
					window.searchTxt.setEditable(false);
					window.chckbxDate.setText("Date");
					window.setVisabilty(window.chckbxSortby.isSelected());
				});
				window.chckbxFilterBy.addActionListener(l2 -> {
					window.removeSelected();
					window.comboBox.setVisible(false);
					window.txtpnChooseOneFrom.setVisible(false);
					window.chckbxSortby.setSelected(false);
					window.searchTxt.setEditable(false);
					window.chckbxSearch.setSelected(false);
					window.chckbxDate.setText("Year");
					window.setVisabilty(window.chckbxFilterBy.isSelected());
				});
				window.chckbxSearch.addActionListener(l2 -> {
					window.removeSelected();
					window.setVisabilty(false);
					window.comboBox.setVisible(false);
					window.searchTxt.setText(window.chckbxSearch.isSelected() ? "" : "Select checkBox to search");
					window.txtpnChooseOneFrom.setVisible(false);
					window.chckbxSortby.setSelected(false);
					window.chckbxFilterBy.setSelected(false);
					window.searchTxt.setEditable(window.chckbxSearch.isSelected());
				});
				
				/*
				 * addEvent button
				 * 
				 */
				window.addEventBtnOnClick();
				/*
				 * search button onclick
				 * 
				 */
				window.btnSearch.addActionListener(e -> {
					final DefaultTableModel model = (DefaultTableModel) window.table.getModel();
					if (!window.startFlag)
						window.btnSearch.setText("Search");
					window.startFlag = true;
					if (window.chckbxSortby.isSelected())
						try {
							window.inputList.sortBy(model, window.selected_chckbx());
						} catch (final SQLException exc) {
							JOptionPane.showMessageDialog(null, "problem with sql connector", "Error",
									JOptionPane.INFORMATION_MESSAGE);
						}
					else if (!window.chckbxFilterBy.isSelected() && !window.chckbxSearch.isSelected())
						try {
							window.inputList.sortBy(model, "none");
						} catch (final SQLException e11) {
							e11.printStackTrace();
						}
					else if (window.chckbxFilterBy.isSelected())
						try {
							window.inputList.filterBy((DefaultTableModel) window.table.getModel(),
									window.selected_chckbx(), (String) window.comboBox.getSelectedItem(),
									FilterType.CHOOSE_FROM_LIST);
						} catch (final SQLException e12) {
							e12.printStackTrace();
						}
					else
						try {
							if ("".equals(window.searchTxt.getText()))
								window.inputList.sortBy(model, "none");
							else
								window.inputList.filterBy((DefaultTableModel) window.table.getModel(), null,
										window.searchTxt.getText(), FilterType.AUTOCOMPLETE);
						} catch (Exception e1) {
							try {
								window.inputList.sortBy(model, "none");
							} catch (SQLException e2) {
								e2.printStackTrace();
							}
						}
					window.table.setVisible(true);
					window.js.setVisible(true);
					window.btnLoadAnalyzeResults.setVisible(true);
				});

				/*
				 * LoadAnalyzeResults button on click
				 */
				window.btnLoadAnalyzeResults.addActionListener(l -> {
					DefaultTableModel model = (DefaultTableModel) window.table.getModel();
					try {
						window.inputList.sortBy(model, "none");
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, "problem with sql connector", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}

				});

				/*
				 * About
				 */

				window.mntmAbout.addActionListener(m -> JOptionPane.showMessageDialog(null,
						"Info Evaluation is a program that reads query results from google search and parses "
								+ "the data,formats it,\nthen analyzes according to the query "
								+ "and returns a structured list of info and interactively suggests"
								+ " new\nqueries in order to achieve the most exact results. "
								+ "\n\n\n\n\nBy: \nVivian Shehadeh\nGenia Shandalov\nOsher Hajaj"
								+ "\nWard Mattar\nMoshiko Elisof\nNetanel Felcher\n",
						"About", JOptionPane.INFORMATION_MESSAGE));
			} catch (final Exception ¢) {
				¢.printStackTrace();
			}

		});
		
		

	}

	protected void removeSelected() {
		chckbxName.setSelected(false);
		chckbxReason.setSelected(false);
		chckbxDate.setSelected(false);

	}

	protected void setVisabilty(final boolean flag) {
		chckbxName.setVisible(flag);
		chckbxReason.setVisible(flag);
		chckbxDate.setVisible(flag);

	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/*
		 * this flag to change the name of search button
		 */
		startFlag = false;

		frame = new JFrame();
		frame.setTitle("Info Evaluation");
		frame.setResizable(false);
		final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screen.width, screen.height - 1);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			connector = new MySQLConnector();
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, "problem with sql connector", "Error", JOptionPane.INFORMATION_MESSAGE);
		}

		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent winEvt) {
				try {
					clearDB();
				} catch (final SQLException e) {
					JOptionPane.showMessageDialog(null, "problem with removing events from Database", "Error",
							JOptionPane.INFORMATION_MESSAGE);
				}
				closeConnection();
				System.exit(0);
			}
		});

		final AnalyzeSources events = new AnalyzeSources();

		events.addSource(src1);
		events.addSource(src2, "2015");

		MySQLConnector.addEvents(events.getData());
		try {
			addAllKeywords(events.getData());
		} catch (SQLException ¢) {
			¢.printStackTrace();
		}

		final JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		popupMenu = new JPopupMenu();
		mnfilter = new JMenuItem("Filter by");
		mnprofile = new JMenuItem("View profile");
		mnprofile.setVisible(false);

		popupMenu.add(mnfilter);
		popupMenu.add(mnprofile);
		/*
		 *
		 * initializing the table
		 *
		 */
		table = new JTable();// {
		table.setComponentPopupMenu(popupMenu);
		table.setShowVerticalLines(false);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBorder(new LineBorder(null));
		for (int count = 1; count <= 10; ++count)
			table.setModel(new DefaultTableModel(new Object[][] { { "Name", "Date", "Reason" } },
					new String[] { "Name", "Date", "Reason" }));
		table.setBounds(30, 120, screen.width / 2 + 100, screen.height / 2);
		table.setVisible(false);
		js = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		js.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		js.setVisible(false);
		js.setBounds(30, 120, screen.width / 2 + 100, screen.height / 2);
		frame.getContentPane().add(js);
		frame.getContentPane().add(js);

		/*
		 * initializing the table
		 *
		 */
		inputList = new RefineTable();
		inputList.addField("Date");
		inputList.addField("Name");
		inputList.addField("Reason");
		inputList.addField("Year");

		btnSearch = new JButton("Load analyze results");

		searchTxt = new JTextField(10);
		searchTxt.setText("Select checkBox to search");
		searchTxt.setColumns(10);
		searchTxt.setEditable(false);

		ArrayList<String> keywords = new ArrayList<String>();
		for (TableTuple t : events.getData()) {
			String year = t.getDate().split("/")[2];
			if (!keywords.contains(year))
				keywords.add(year);
			if (!keywords.contains(t.getName()))
				keywords.add(t.getName());
			for (String s : t.getKeyWords())
				for (String key : s.split(" ")) {
					if (keywords.contains(key))
						continue;
					keywords.add(key);
				}
		}

		AutoComplete.setupAutoComplete(searchTxt, keywords);

		chckbxName = new JCheckBox("Name");
		chckbxName.setVisible(false);

		chckbxDate = new JCheckBox("Date");
		chckbxDate.setVisible(false);

		chckbxReason = new JCheckBox("Reason");
		chckbxReason.setVisible(false);

		chckbxSortby = new JCheckBox("Sort by");

		chckbxFilterBy = new JCheckBox("Filter by");

		comboBox = new JComboBox<>();
		comboBox.setVisible(false);

		txtpnChooseOneFrom = new JTextPane();
		txtpnChooseOneFrom.setText("Choose one from:");
		txtpnChooseOneFrom.setBackground(new Color(0, 0, 0, 0));
		txtpnChooseOneFrom.setVisible(false);

		personalInfo = new InfoExtractor();

		btnLoadAnalyzeResults = new JButton("Load analyze results");
		btnLoadAnalyzeResults.setVisible(false);

		/*
		 * add image
		 */

		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(this.getClass().getResource("search.png"));
			lblImage = new JLabel(new ImageIcon(myPicture.getScaledInstance(220, 220, Image.SCALE_DEFAULT)));
		} catch (IOException ¢) {
			¢.printStackTrace();
		}

		chckbxSearch = new JCheckBox("");

		btnAddEvents = new JButton("Add Events");
		

		final GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(881, Short.MAX_VALUE)
					.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
					.addGap(247))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLoadAnalyzeResults)
							.addGap(92)
							.addComponent(btnAddEvents, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxSearch, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, 744, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(chckbxName)
											.addGap(18)
											.addComponent(chckbxDate, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(chckbxSortby)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(chckbxFilterBy)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(chckbxReason)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(txtpnChooseOneFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(313, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(chckbxSearch)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(chckbxSortby)
								.addComponent(chckbxFilterBy))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(txtpnChooseOneFrom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(chckbxName)
									.addComponent(chckbxDate)
									.addComponent(chckbxReason))
								.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
					.addComponent(lblImage, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
					.addGap(76)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnLoadAnalyzeResults, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAddEvents, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
					.addGap(171))
		);
		frame.getContentPane().setLayout(groupLayout);

	}

	public void onClickCheckBox(final JCheckBox chckbx, final JCheckBox clearbx1, final JCheckBox clearbx2) {
		chckbx.addActionListener(l -> {
			if (chckbx.isSelected()) {
				clearbx1.setSelected(false);
				clearbx2.setSelected(false);
			}
			if (!chckbxFilterBy.isSelected() || !chckbx.isSelected()) {
				comboBox.setVisible(false);
				txtpnChooseOneFrom.setVisible(false);
			} else {
				try {
					inputList.getCategory(comboBox, chckbx.getText());
				} catch (final SQLException ¢) {
					¢.printStackTrace();
				}
				comboBox.setVisible(true);
				txtpnChooseOneFrom.setVisible(true);
			}

		});
	}

	public String selected_chckbx() {
		return chckbxName.isSelected() ? "Name"
				: chckbxDate.isSelected() ? (chckbxFilterBy.isSelected() ? "Year" : "Date")
						: !chckbxReason.isSelected() ? "None" : "Reason";
	}

	/*
	 * create popup menu
	 */
	public JPopupMenu createMyPopUp() {
		final JPopupMenu $ = new JPopupMenu("RightClick");
		$.add(this.mnfilter);
		return $;

	}
	/*
	 * add events button
	 */
	private void addEventBtnOnClick(){
		btnAddEvents.addActionListener(l -> {
			InteractiveFrame frame = new InteractiveFrame();
		});
	}
}
