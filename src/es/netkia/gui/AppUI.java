package es.netkia.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import es.netkia.factory.LanguageFactory;
import es.netkia.io.FileManager;
import es.netkia.io.IconManager;
import es.netkia.io.PropertiesManager;
import es.netkia.languages.ILanguage;
import es.netkia.languages.Language;
import es.netkia.main.App;
import es.netkia.main.ScriptGenerator;
import net.miginfocom.swing.MigLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class AppUI {

	public JFrame frame;
	private JTabbedPane tabbedPane;
	private JPanel panelGenerator;
	private JPanel panelGeneratorTop;
	private JPanel panelGeneratorBot;
	private JPanel panelGeneratorRight;
	private JPanel panelGeneratorLeft;
	private JPanel panelSettings;

	private JButton buttonBrowseLoadPath;
	private JButton buttonBrowseScriptsPath;
	private JButton buttonRefreshList;
	private JButton buttonRemoveFromList;
	private JButton buttonNewFile;
	private JButton buttonEditFile;
	private JButton buttonDeleteFile;
	private JButton buttonGenerate;
	private JButton buttonResetSettings;
	private JButton buttonApplySettings;

	private JLabel labelLoadPath;
	private JLabel labelScriptsPath;
	private JLabel labelProgress;
	private JLabel labelSuccess;
	private JLabel labelErrors;
	private JLabel labelFileInformation;
	private JLabel labelFileNameMeta;
	private JLabel labelFileTypeMeta;
	private JLabel labelFileSizeMeta;
	private JLabel labelFileModMeta;
	private JLabel labelFileStatus;
	private JLabel labelFileName;
	private JLabel labelFileType;
	private JLabel labelFileSize;
	private JLabel labelFileMod;

	private JSeparator separatorGT;
	private JSeparator separatorGB;
	private JSeparator separatorGL;

	private JTextField textfieldScriptsPath;
	private JTextField textfieldLoadPath;

	private JProgressBar progressBarGenerate;
	private DefaultListModel<String> fileListModel;
	private JList<String> listFileList;
	private JScrollPane scrollpaneFileList;
	private JFileChooser fileChooser;

	private PropertiesManager propertiesManager;
	private ILanguage language;
	private JLabel labelValidationIcon;
	private JLabel labelValidationStatus;
	private JSeparator separatorGR;
	private JTextArea textareaLoadFileLines;

	private HashMap<String,ImageIcon> icons;
	private JPanel panelSettingsCenter;
	private JPanel panelSettingsCenterLanguage;
	private JPanel panelSettingsCenterScript;
	private JComboBox<String> comboboxLanguage;
	private JPanel panelSettingsCenterButtons;
	private JPanel panelSettingsCenterPaths;
	private JLabel labelPctfree;
	private JTextField textfieldMaxextents;
	private JLabel labelMaxextents;
	private JTextField textfieldPctfree;
	private JLabel labelPctused;
	private JLabel labelPctincrease;
	private JLabel labelInitrans;
	private JLabel labelFreelists;
	private JLabel labelMaxtrans;
	private JLabel labelGroups;
	private JLabel labelInitial;
	private JLabel labelBufferPool;
	private JLabel labelMinextents;
	private JLabel labelNext;
	private JLabel labelCellFlashCache;
	private JLabel labelFlashCache;
	private JTextField textfieldPctused;
	private JTextField textfieldInitrans;
	private JTextField textfieldMaxtrans;
	private JTextField textfieldInitial;
	private JTextField textfieldNext;
	private JTextField textfieldMinextents;
	private JTextField textfieldPctincrease;
	private JTextField textfieldFreelists;
	private JTextField textfieldGroups;
	private JTextField textfieldBufferPool;
	private JTextField textfieldFlashCache;
	private JTextField textfieldCellFlashCache;
	private JLabel labelDefaultLoadPath;
	private JTextField textfieldDefaultLoadPath;
	private JLabel labelDefaultScriptsPath;
	private JTextField textfieldDefaultScriptsPath;
	private JButton buttonBrowseDefaultLoadPath;
	private JButton buttonBrowseDefaultScriptsPath;

	/**
	 * Create the application.
	 */
	public AppUI(PropertiesManager properties) {
		this.propertiesManager = properties;
		this.propertiesManager.initialize();

		this.language = LanguageFactory.build(this.propertiesManager.getDefaultLanguage());

		this.icons = IconManager.getAppIcons();

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/**
		 * FRAME SETTINGS
		 */
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		frame.setResizable(false);
		frame.setIconImage(new ImageIcon(System.getProperty("user.dir")+"/resources/icons/logo.png").getImage());


		/**
		 * PANELS
		 */
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, "name_12829169313188");

		panelGenerator = new JPanel();
		tabbedPane.addTab(this.language.get("tabGenerator"), null, panelGenerator, null);
		panelGenerator.setLayout(new BorderLayout(0, 0));

		panelGeneratorTop = new JPanel();
		panelGenerator.add(panelGeneratorTop, BorderLayout.NORTH);
		panelGeneratorTop.setLayout(new MigLayout("", "[][grow][]", "[][][]"));

		panelGeneratorBot = new JPanel();
		panelGenerator.add(panelGeneratorBot, BorderLayout.SOUTH);
		panelGeneratorBot.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][grow][grow][][]", "[][][]"));

		panelGeneratorRight = new JPanel();
		panelGeneratorRight.setBorder(null);
		panelGenerator.add(panelGeneratorRight, BorderLayout.CENTER);
		panelGeneratorRight.setLayout(new MigLayout("", "[][grow][grow][grow][grow][grow][grow][grow]", "[][][][][][][][][grow]"));

		panelGeneratorLeft = new JPanel();
		panelGenerator.add(panelGeneratorLeft, BorderLayout.WEST);
		panelGeneratorLeft.setLayout(new MigLayout("", "[grow][][]", "[][][grow][][][][]"));

		panelSettings = new JPanel();
		tabbedPane.addTab(this.language.get("tabSettings"), null, panelSettings, null);
		panelSettings.setLayout(new BorderLayout(0, 0));

		panelSettingsCenter = new JPanel();
		panelSettings.add(panelSettingsCenter, BorderLayout.CENTER);
		panelSettingsCenter.setLayout(new MigLayout("", "[grow][grow][grow]", "[][][][grow]"));

		panelSettingsCenterLanguage = new JPanel();
		panelSettingsCenterLanguage.setBorder(new TitledBorder(null, this.language.get("settingsLangTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSettingsCenter.add(panelSettingsCenterLanguage, "cell 1 0,grow");
		panelSettingsCenterLanguage.setLayout(new MigLayout("", "[grow]", "[]"));

		comboboxLanguage = new JComboBox<String>();
		comboboxLanguage.addItem("English");
		comboboxLanguage.addItem("Spanish");
		panelSettingsCenterLanguage.add(comboboxLanguage, "cell 0 0,growx");

		panelSettingsCenterScript = new JPanel();
		panelSettingsCenterScript.setBorder(new TitledBorder(null, this.language.get("settingsScriptValuesTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSettingsCenter.add(panelSettingsCenterScript, "cell 1 1,grow");
		panelSettingsCenterScript.setLayout(new MigLayout("", "[][grow][][grow]", "[][][][][][][]"));

		labelPctfree = new JLabel("PCTFREE");
		panelSettingsCenterScript.add(labelPctfree, "cell 0 0,alignx trailing");

		textfieldPctfree = new JTextField();
		panelSettingsCenterScript.add(textfieldPctfree, "cell 1 0,growx,gapx 0 5");
		textfieldPctfree.setColumns(10);

		labelMaxextents = new JLabel("MAXEXTENTS");
		panelSettingsCenterScript.add(labelMaxextents, "cell 2 0,alignx trailing");

		textfieldMaxextents = new JTextField();
		panelSettingsCenterScript.add(textfieldMaxextents, "cell 3 0,growx");
		textfieldMaxextents.setColumns(10);

		labelPctused = new JLabel("PCTUSED");
		panelSettingsCenterScript.add(labelPctused, "cell 0 1,alignx trailing");

		textfieldPctused = new JTextField();
		panelSettingsCenterScript.add(textfieldPctused, "cell 1 1,growx,gapx 0 5");
		textfieldPctused.setColumns(10);

		labelPctincrease = new JLabel("PCTINCREASE");
		panelSettingsCenterScript.add(labelPctincrease, "cell 2 1,alignx trailing");

		textfieldPctincrease = new JTextField();
		panelSettingsCenterScript.add(textfieldPctincrease, "cell 3 1,growx");
		textfieldPctincrease.setColumns(10);

		labelInitrans = new JLabel("INITRANS");
		panelSettingsCenterScript.add(labelInitrans, "cell 0 2,alignx trailing");

		textfieldInitrans = new JTextField();
		panelSettingsCenterScript.add(textfieldInitrans, "cell 1 2,growx,gapx 0 5");
		textfieldInitrans.setColumns(10);

		labelFreelists = new JLabel("FREELISTS");
		panelSettingsCenterScript.add(labelFreelists, "cell 2 2,alignx trailing");

		textfieldFreelists = new JTextField();
		panelSettingsCenterScript.add(textfieldFreelists, "cell 3 2,growx");
		textfieldFreelists.setColumns(10);

		labelMaxtrans = new JLabel("MAXTRANS");
		panelSettingsCenterScript.add(labelMaxtrans, "cell 0 3,alignx trailing");

		textfieldMaxtrans = new JTextField();
		panelSettingsCenterScript.add(textfieldMaxtrans, "cell 1 3,growx,gapx 0 5");
		textfieldMaxtrans.setColumns(10);

		labelGroups = new JLabel("GROUPS");
		panelSettingsCenterScript.add(labelGroups, "cell 2 3,alignx trailing");

		textfieldGroups = new JTextField();
		panelSettingsCenterScript.add(textfieldGroups, "cell 3 3,growx");
		textfieldGroups.setColumns(10);

		labelInitial = new JLabel("INITIAL");
		panelSettingsCenterScript.add(labelInitial, "cell 0 4,alignx trailing");

		textfieldInitial = new JTextField();
		panelSettingsCenterScript.add(textfieldInitial, "cell 1 4,growx,gapx 0 5");
		textfieldInitial.setColumns(10);

		labelBufferPool = new JLabel("BUFFER POOL");
		panelSettingsCenterScript.add(labelBufferPool, "cell 2 4,alignx trailing");

		textfieldBufferPool = new JTextField();
		panelSettingsCenterScript.add(textfieldBufferPool, "cell 3 4,growx");
		textfieldBufferPool.setColumns(10);

		labelNext = new JLabel("NEXT");
		panelSettingsCenterScript.add(labelNext, "cell 0 5,alignx trailing");

		textfieldNext = new JTextField();
		panelSettingsCenterScript.add(textfieldNext, "cell 1 5,growx,gapx 0 5");
		textfieldNext.setColumns(10);

		labelFlashCache = new JLabel("FLASH CACHE");
		panelSettingsCenterScript.add(labelFlashCache, "cell 2 5,alignx trailing");

		textfieldFlashCache = new JTextField();
		panelSettingsCenterScript.add(textfieldFlashCache, "cell 3 5,growx");
		textfieldFlashCache.setColumns(10);

		labelMinextents = new JLabel("MINEXTENTS");
		panelSettingsCenterScript.add(labelMinextents, "cell 0 6,alignx trailing");

		textfieldMinextents = new JTextField();
		panelSettingsCenterScript.add(textfieldMinextents, "cell 1 6,growx,gapx 0 5");
		textfieldMinextents.setColumns(10);

		labelCellFlashCache = new JLabel("CELL FLASH CACHE");
		panelSettingsCenterScript.add(labelCellFlashCache, "cell 2 6,alignx trailing");

		textfieldCellFlashCache = new JTextField();
		panelSettingsCenterScript.add(textfieldCellFlashCache, "cell 3 6,growx");
		textfieldCellFlashCache.setColumns(10);

		panelSettingsCenterPaths = new JPanel();
		panelSettingsCenterPaths.setBorder(new TitledBorder(null, this.language.get("settingsPathsTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSettingsCenter.add(panelSettingsCenterPaths, "cell 1 2,grow");
		panelSettingsCenterPaths.setLayout(new MigLayout("", "[][grow][]", "[][][][]"));

		labelDefaultLoadPath = new JLabel(this.language.get("settingsDefaultLoadPath"));
		panelSettingsCenterPaths.add(labelDefaultLoadPath, "cell 0 0,alignx left");

		textfieldDefaultLoadPath = new JTextField();
		textfieldDefaultLoadPath.setEditable(false);
		panelSettingsCenterPaths.add(textfieldDefaultLoadPath, "cell 0 1 2 1,growx");
		textfieldDefaultLoadPath.setColumns(10);

		buttonBrowseDefaultLoadPath = new JButton(this.language.get("browse"));
		panelSettingsCenterPaths.add(buttonBrowseDefaultLoadPath, "cell 2 1");

		labelDefaultScriptsPath = new JLabel(this.language.get("settingsDefaultScriptsPath"));
		panelSettingsCenterPaths.add(labelDefaultScriptsPath, "cell 0 2,alignx left");

		textfieldDefaultScriptsPath = new JTextField();
		textfieldDefaultScriptsPath.setEditable(false);
		panelSettingsCenterPaths.add(textfieldDefaultScriptsPath, "cell 0 3 2 1,growx");
		textfieldDefaultScriptsPath.setColumns(10);

		buttonBrowseDefaultScriptsPath = new JButton(this.language.get("browse"));
		panelSettingsCenterPaths.add(buttonBrowseDefaultScriptsPath, "cell 2 3");

		panelSettingsCenterButtons = new JPanel();
		panelSettingsCenter.add(panelSettingsCenterButtons, "cell 1 3,growx,aligny top");
		panelSettingsCenterButtons.setLayout(new MigLayout("", "[grow][][]", "[]"));


		/**
		 * BUTTONS
		 */
		buttonBrowseLoadPath = new JButton(this.language.get("browse"));
		panelGeneratorTop.add(buttonBrowseLoadPath, "cell 2 0");

		buttonBrowseScriptsPath = new JButton(this.language.get("browse"));
		panelGeneratorTop.add(buttonBrowseScriptsPath, "cell 2 1");

		buttonRefreshList = new JButton(this.icons.get("refresh"));
		buttonRefreshList.setToolTipText(this.language.get("refreshTooltip"));
		panelGeneratorLeft.add(buttonRefreshList, "cell 1 0,growx,width 38::38,height 38::38");

		buttonRemoveFromList = new JButton(this.icons.get("remove"));
		buttonRemoveFromList.setToolTipText(this.language.get("removeTooltip"));
		buttonRemoveFromList.setEnabled(false);
		panelGeneratorLeft.add(buttonRemoveFromList, "cell 1 1,growx,width 38::38,height 38::38");

		buttonNewFile = new JButton(this.icons.get("newFile"));
		buttonNewFile.setToolTipText(this.language.get("newFileTooltip"));
		panelGeneratorLeft.add(buttonNewFile, "cell 1 3,growx,width 38::38,height 38::38");

		buttonEditFile = new JButton(this.icons.get("editFile"));
		buttonEditFile.setEnabled(false);
		buttonEditFile.setToolTipText(this.language.get("editFileTooltip"));
		panelGeneratorLeft.add(buttonEditFile, "cell 1 4,width 38::38,height 38::38");

		buttonDeleteFile = new JButton(this.icons.get("deleteFile"));
		buttonDeleteFile.setEnabled(false);
		buttonDeleteFile.setToolTipText(this.language.get("deleteFileTooltip"));
		panelGeneratorLeft.add(buttonDeleteFile, "cell 1 5,width 38::38,height 38::38");

		buttonGenerate = new JButton(this.language.get("generateButton"));
		buttonGenerate.setFont(new Font("Open Sans", Font.PLAIN, 14));
		buttonGenerate.setEnabled(false);
		panelGeneratorLeft.add(buttonGenerate, "cell 0 6 2 1,height pref+5%,grow");

		buttonResetSettings = new JButton(this.language.get("settingsResetToDefaults"));
		panelSettingsCenterButtons.add(buttonResetSettings, "cell 1 0,growx");

		buttonApplySettings = new JButton(this.language.get("settingsApply"));
		panelSettingsCenterButtons.add(buttonApplySettings, "cell 2 0,growx");


		/**
		 * LABELS
		 */
		labelLoadPath = new JLabel(this.language.get("loadpath"));
		panelGeneratorTop.add(labelLoadPath, "cell 0 0,alignx trailing");

		labelScriptsPath = new JLabel(this.language.get("scriptspath"));
		panelGeneratorTop.add(labelScriptsPath, "cell 0 1,alignx trailing");

		labelProgress = new JLabel(this.language.get("progressbar"));
		panelGeneratorBot.add(labelProgress, "cell 7 1,alignx left");

		labelSuccess = new JLabel("");
		panelGeneratorBot.add(labelSuccess, "cell 8 1,alignx right");

		labelErrors = new JLabel("");
		labelErrors.setHorizontalAlignment(SwingConstants.TRAILING);
		panelGeneratorBot.add(labelErrors, "cell 9 1,alignx right");

		labelFileInformation = new JLabel(this.language.get("fileInfo"));
		labelFileInformation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelGeneratorRight.add(labelFileInformation, "cell 0 0 8 1,alignx center");

		labelFileStatus = new JLabel(this.language.get("fileStatusNone"));
		labelFileStatus.setHorizontalAlignment(SwingConstants.TRAILING);
		labelFileStatus.setForeground(Color.GRAY);
		labelFileStatus.setFont(new Font("Tahoma", Font.ITALIC, 10));
		panelGeneratorRight.add(labelFileStatus, "cell 0 1 8 1,alignx center");

		labelFileNameMeta = new JLabel(this.language.get("fileName"));
		panelGeneratorRight.add(labelFileNameMeta, "cell 0 2");

		labelFileName = new JLabel("");
		panelGeneratorRight.add(labelFileName, "cell 1 2 7 1,alignx left");

		labelFileTypeMeta = new JLabel(this.language.get("fileType"));
		panelGeneratorRight.add(labelFileTypeMeta, "cell 0 3");

		labelFileType = new JLabel("");
		panelGeneratorRight.add(labelFileType, "cell 1 3 7 1,alignx left");

		labelFileSizeMeta = new JLabel(this.language.get("fileSize"));
		panelGeneratorRight.add(labelFileSizeMeta, "cell 0 4");

		labelFileSize = new JLabel("");
		panelGeneratorRight.add(labelFileSize, "cell 1 4 7 1,alignx left");

		labelFileModMeta = new JLabel(this.language.get("fileModified"));
		panelGeneratorRight.add(labelFileModMeta, "cell 0 5,gapx 0px 10px");

		labelFileMod = new JLabel("");
		panelGeneratorRight.add(labelFileMod, "cell 1 5 7 1,alignx left");

		separatorGR = new JSeparator();
		panelGeneratorRight.add(separatorGR, "cell 0 6 8 1,growx,gapy 5px 5px");

		labelValidationStatus = new JLabel(this.language.get("fileValidationStatus"));
		labelValidationStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelGeneratorRight.add(labelValidationStatus, "flowx,cell 0 7 8 1,alignx center");

		labelValidationIcon = new JLabel(this.icons.get("idle"));
		labelValidationIcon.setText("");
		panelGeneratorRight.add(labelValidationIcon, "cell 0 7,alignx left,gapx 10px");

		textareaLoadFileLines = new JTextArea();
		textareaLoadFileLines.setEditable(false);
		JScrollPane scrollpaneFileLines = new JScrollPane (textareaLoadFileLines);
		scrollpaneFileLines.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollpaneFileLines.setViewportBorder(new TitledBorder(null, this.language.get("filePreviewTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGeneratorRight.add(scrollpaneFileLines, "cell 0 8 8 1,grow");


		/**
		 * SEPARATORS
		 */
		separatorGT = new JSeparator();
		panelGeneratorTop.add(separatorGT, "cell 0 2 3 1,growx,gap 0px 0px 5px");

		separatorGB = new JSeparator();
		panelGeneratorBot.add(separatorGB, "cell 0 0 10 1,growx,gapx 0 0,gapy 0 5");

		separatorGL = new JSeparator();
		separatorGL.setOrientation(SwingConstants.VERTICAL);
		panelGeneratorLeft.add(separatorGL, "cell 2 0 1 7,gapx 5,growy");


		/**
		 * TEXT FIELDS
		 */
		textfieldLoadPath = new JTextField();
		textfieldLoadPath.setColumns(10);
		textfieldLoadPath.setText(this.propertiesManager.getDefaultLoadPath());
		textfieldLoadPath.setEditable(false);
		panelGeneratorTop.add(textfieldLoadPath, "flowx,cell 1 0,growx");

		textfieldScriptsPath = new JTextField();
		textfieldScriptsPath.setColumns(10);
		textfieldScriptsPath.setEditable(false);
		panelGeneratorTop.add(textfieldScriptsPath, "flowx,cell 1 1,growx");


		/**
		 * FILE LIST
		 */
		fileListModel = new DefaultListModel<String>();
		listFileList = new JList<String>(fileListModel);
		listFileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollpaneFileList = new JScrollPane(listFileList);
		scrollpaneFileList.setViewportBorder(new TitledBorder(null, this.language.get("fileListTitle"), TitledBorder.LEADING, TitledBorder.TOP, null, null));
		scrollpaneFileList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelGeneratorLeft.add(scrollpaneFileList, "cell 0 0 1 6,width 200px::200px,alignx leading,growy");


		/**
		 * ALL COMPONENTS LEFT
		 */
		fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Select directory where load data is contained");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);

		progressBarGenerate = new JProgressBar();
		progressBarGenerate.setStringPainted(true);
		panelGeneratorBot.add(progressBarGenerate, "cell 7 2 3 1,growx,aligny center");


		/**
		 * EVENT HANDLERS
		 */
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ev) {
				if(tabbedPane.getSelectedIndex() == 1) {
					AppUI.this.loadSettings();
				}
			}
		});

		listFileList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent ev) {
				if(!ev.getValueIsAdjusting()) {
					AppUI.this.updateFileInfo();
				}
			}
		});

		buttonBrowseLoadPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.fileChooser.setCurrentDirectory(FileManager.getFile(textfieldLoadPath.getText()));
				AppUI.this.updatePath(textfieldLoadPath);
				AppUI.this.populateFileList();
			}
		});

		buttonBrowseDefaultLoadPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.fileChooser.setCurrentDirectory(FileManager.getFile(textfieldDefaultLoadPath.getText()));
				AppUI.this.updatePath(textfieldDefaultLoadPath);
				AppUI.this.populateFileList();
			}
		});

		buttonBrowseScriptsPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.fileChooser.setCurrentDirectory(FileManager.getFile(textfieldScriptsPath.getText()));
				AppUI.this.updatePath(textfieldScriptsPath);
			}
		});

		buttonBrowseDefaultScriptsPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.fileChooser.setCurrentDirectory(FileManager.getFile(textfieldDefaultScriptsPath.getText()));
				AppUI.this.updatePath(textfieldDefaultScriptsPath);
			}
		});

		buttonRefreshList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.populateFileList();
				AppUI.this.updateFileInfo();
			}
		});

		buttonRemoveFromList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.fileListModel.removeElementAt(listFileList.getSelectedIndex());
				AppUI.this.updateFileInfo();
			}
		});

		buttonNewFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.createFile();
			}
		});

		buttonEditFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.editFile();
			}
		});

		buttonDeleteFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.deleteFile();
				AppUI.this.updateFileInfo();
			}
		});

		buttonGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				new Thread() {
					public void run() {
						AppUI.this.generateScripts();
					}  
				}.start();
			}
		});

		buttonApplySettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.applySettings();
			}
		});

		buttonResetSettings.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent ev) {
				AppUI.this.resetSettings();
			}
		});


		/**
		 * INITIALIZE VALUES
		 */
		textfieldLoadPath.setText(this.propertiesManager.getDefaultLoadPath());
		textfieldScriptsPath.setText(this.propertiesManager.getDefaultScriptsPath());

		populateFileList();
	}

	/**
	 * SETTINGS
	 */
	private void loadSettings() {
		HashMap<String,String> properties = this.propertiesManager.getDefaultAppProperties();

		comboboxLanguage.setSelectedItem(this.language.get("codeName"));

		textfieldPctfree.setText	   (properties.get("pctfree"));
		textfieldPctused.setText	   (properties.get("pctused"));
		textfieldInitrans.setText	   (properties.get("initrans"));
		textfieldMaxtrans.setText	   (properties.get("maxtrans"));
		textfieldInitial.setText	   (properties.get("initial"));
		textfieldNext.setText		   (properties.get("next"));
		textfieldMinextents.setText	   (properties.get("minextents"));
		textfieldMaxextents.setText	   (properties.get("maxextents"));
		textfieldPctincrease.setText   (properties.get("pctincrease"));
		textfieldFreelists.setText	   (properties.get("freelists"));
		textfieldGroups.setText		   (properties.get("groups"));
		textfieldBufferPool.setText	   (properties.get("bufferpool"));
		textfieldFlashCache.setText	   (properties.get("flashcache"));
		textfieldCellFlashCache.setText(properties.get("cellflashcache"));

		textfieldDefaultLoadPath.setText	(this.propertiesManager.getDefaultLoadPath());
		textfieldDefaultScriptsPath.setText	(this.propertiesManager.getDefaultScriptsPath());
	}

	private void applySettings() {
		this.buttonApplySettings.setEnabled(false);

		boolean needsReload = false;
		HashMap<String,String> newProperties = new HashMap<String,String>();

		newProperties.put("pctfree" 		,textfieldPctfree.getText());
		newProperties.put("pctused" 		,textfieldPctused.getText());
		newProperties.put("initrans" 		,textfieldInitrans.getText());
		newProperties.put("maxtrans" 		,textfieldMaxtrans.getText());
		newProperties.put("initial" 		,textfieldInitial.getText());
		newProperties.put("next" 			,textfieldNext.getText());
		newProperties.put("minextents" 		,textfieldMinextents.getText());
		newProperties.put("maxextents" 		,textfieldMaxextents.getText());
		newProperties.put("pctincrease" 	,textfieldPctincrease.getText());
		newProperties.put("freelists" 		,textfieldFreelists.getText());
		newProperties.put("groups" 			,textfieldGroups.getText());
		newProperties.put("bufferpool" 		,textfieldBufferPool.getText());
		newProperties.put("flashcache" 		,textfieldFlashCache.getText());
		newProperties.put("cellflashcache" 	,textfieldCellFlashCache.getText());

		newProperties.put("defaultloadpath"		,textfieldDefaultLoadPath.getText());
		newProperties.put("defaultscriptspath"	,textfieldDefaultScriptsPath.getText());
		if(!this.language.get("codeName").equalsIgnoreCase((String)comboboxLanguage.getSelectedItem())) {
			needsReload = true;
			newProperties.put("defaultlanguage"		, Language.getCode((String)comboboxLanguage.getSelectedItem()));
		}

		this.propertiesManager.writeDefaultAppProperties(newProperties);

		if(needsReload) {
			this.frame.dispose();

			AppUI window = new AppUI(new PropertiesManager());
			window.frame.setTitle(App.NAME +" "+ App.VERSION);
			window.frame.setVisible(true);
		}

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		this.buttonApplySettings.setEnabled(true);
	}

	private void resetSettings() {
		this.buttonResetSettings.setEnabled(false);

		HashMap<String,String> newProperties = new HashMap<String,String>();

		newProperties.put("pctfree" 		, "10");
		newProperties.put("pctused" 		, "40");
		newProperties.put("initrans" 		, "1");
		newProperties.put("maxtrans" 		, "255");
		newProperties.put("initial" 		, "65536");
		newProperties.put("next" 			, "1048576");
		newProperties.put("minextents" 		, "1");
		newProperties.put("maxextents" 		, "2147483645");
		newProperties.put("pctincrease" 	, "0");
		newProperties.put("freelists" 		, "1");
		newProperties.put("groups" 			, "1");
		newProperties.put("bufferpool" 		, "DEFAULT");
		newProperties.put("flashcache" 		, "DEFAULT");
		newProperties.put("cellflashcache" 	, "DEFAULT");

		newProperties.put("defaultloadpath"		, "APP.resources.load");
		newProperties.put("defaultscriptspath"	, "APP.resources.scripts");
		newProperties.put("defaultlanguage"		, "en");

		this.propertiesManager.writeDefaultAppProperties(newProperties);

		this.frame.dispose();

		AppUI window = new AppUI(new PropertiesManager());
		window.frame.setTitle(App.NAME +" "+ App.VERSION);
		window.frame.setVisible(true);

		try {Thread.sleep(1000);} catch (InterruptedException e) {e.printStackTrace();}
		this.buttonResetSettings.setEnabled(true);
	}

	/**
	 * FILE ACTIONS
	 */
	private void createFile() {
		String fileName = "";
		boolean isValid = false;
		while(!isValid) {
			fileName = JOptionPane.showInputDialog(this.frame, this.language.get("inputFileName"));
			isValid = FileManager.validateFileName(fileName);

			if(isValid) {
				FileManager.createNewFile(textfieldLoadPath.getText()+File.separator+fileName+FileManager.DAT);
				populateFileList();
			}
			else {
				if(!isValid && fileName!=null) JOptionPane.showMessageDialog(this.frame, language.get("fileNameInvalid"), "Error", JOptionPane.ERROR_MESSAGE);
				else if(fileName==null) break;
			}
		}
	}

	private void editFile() {
		FileManager.openFile(getSelectedFileLoadPath());
	}

	private void deleteFile() {
		UIManager.put("OptionPane.yesButtonText", language.get("yesButton"));
		UIManager.put("OptionPane.noButtonText", language.get("noButton"));
		int dialogResult = JOptionPane.showConfirmDialog(this.frame, language.get("confirmDeleteMessage"),"",JOptionPane.YES_OPTION);
		if(dialogResult == JOptionPane.YES_OPTION){
			FileManager.deleteFile(getSelectedFileLoadPath());
			populateFileList();
		}
	}

	private void validateSelectedFile() {
		labelValidationIcon.setIcon(this.icons.get("idle"));
		textareaLoadFileLines.setText("");

		boolean isValid = FileManager.validateLoadFileContents(getSelectedFileLoadPath());
		int lineCount = 0;

		if(isValid) labelValidationIcon.setIcon(this.icons.get("valid"));
		else labelValidationIcon.setIcon(this.icons.get("invalid"));

		// print lines
		List<String> lines = FileManager.readAllLines(FileManager.getFile(getSelectedFileLoadPath()));

		for(String line : lines) {
			textareaLoadFileLines.append((lineCount+1) +"| "+ line +"\n");

			if(lineCount == ((lines.get(0).equalsIgnoreCase("stg")) ? 3:4)) break;
			else lineCount++;
		}
	}

	/**
	 * UI ACTIONS
	 */
	private void populateFileList() {
		fileListModel.removeAllElements();
		updateSuccessCount(0);
		updateErrorCount(0);

		File[] files = FileManager.getAllFiles(textfieldLoadPath.getText(), ".dat");

		for (File file : files) {
			fileListModel.addElement(file.getName());
		}

		if(files.length < 1) buttonGenerate.setEnabled(false);
		else buttonGenerate.setEnabled(true);
		progressBarGenerate.setValue(0);
	}

	private String getSelectedFileLoadPath() {
		return textfieldLoadPath.getText() + File.separator + fileListModel.get(listFileList.getSelectedIndex());
	}

	private void setFileInfo(String name, String type, String size, String text) {
		labelFileName.setText(name);
		labelFileType.setText(type);
		labelFileSize.setText(size);
		labelFileMod.setText(text);
	}

	/**
	 * UPDATES
	 */
	private void updatePath(JTextField tf) {
		int response = fileChooser.showOpenDialog(AppUI.this.frame);
		if(response == JFileChooser.APPROVE_OPTION) {
			tf.setText(fileChooser.getSelectedFile().getAbsolutePath());
		}
	}

	private void updateFileInfo() {
		HashMap<String,String> fileProperties;
		File file;

		if(listFileList.getSelectedIndex() != -1) {
			file = FileManager.getFile(getSelectedFileLoadPath());

			if(file != null ) {
				fileProperties = FileManager.getFileProperties(file);
				labelFileStatus.setText(this.language.get("fileStatusSelected"));

				buttonRemoveFromList.setEnabled(true);
				buttonEditFile.setEnabled(true);
				buttonDeleteFile.setEnabled(true);

				setFileInfo(
						fileProperties.get("name"),
						fileProperties.get("type")+" file",
						fileProperties.get("size")+" bytes",
						fileProperties.get("lastModified"));

				validateSelectedFile();
			}
			else {
				buttonRemoveFromList.setEnabled(false);
				buttonEditFile.setEnabled(false);
				buttonDeleteFile.setEnabled(false);

				labelFileStatus.setText(this.language.get("fileStatusNull"));
				setFileInfo("","","","");
			}
		}
		else {
			buttonRemoveFromList.setEnabled(false);
			buttonEditFile.setEnabled(false);
			buttonDeleteFile.setEnabled(false);

			labelValidationIcon.setIcon(this.icons.get("idle"));
			textareaLoadFileLines.setText("");
			labelFileStatus.setText(this.language.get("fileStatusNone"));
			setFileInfo("","","","");
		}

		if(listFileList.getModel().getSize() <= 0) buttonGenerate.setEnabled(false);
		else buttonGenerate.setEnabled(true);
	}

	private void updateSuccessCount(int n) {
		labelSuccess.setText(this.language.get("successCount")+": "+n);
	}

	private void updateErrorCount(int n) {
		labelErrors.setText(this.language.get("errorCount")+": "+n);
	}

	/**
	 * GENERATE SCRIPTS
	 */
	private void generateScripts() {
		frame.setEnabled(false);

		int successCount = 0,
				errorCount = 0;
		updateSuccessCount(successCount);
		updateErrorCount(errorCount);

		int listCount = listFileList.getModel().getSize();
		progressBarGenerate.setMaximum(listCount);

		for(int i=0; i<listCount; i++) {
			// Look & Feel :v
			try {Thread.sleep(30);} catch (InterruptedException e) {}

			// Select first item
			listFileList.setSelectedIndex(0);

			// Load it
			File file = FileManager.getFile(getSelectedFileLoadPath());
			if(file != null) {
				boolean generated = ScriptGenerator.load(file, textfieldScriptsPath.getText(), propertiesManager.getProperties());

				if(generated) updateSuccessCount(++successCount);
				else updateErrorCount(++errorCount);
			}
			else updateErrorCount(++errorCount);

			fileListModel.remove(0);
			progressBarGenerate.setValue(i+1);
		}
		listFileList.setSelectedIndex(-1);
		updateFileInfo();

		frame.setEnabled(true);
	}
}
