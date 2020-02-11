/*      */ package view.editor;
/*      */ 
/*      */ import java.awt.BorderLayout;
/*      */ import java.awt.Component;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.EventQueue;
/*      */ import java.awt.GraphicsDevice;
/*      */ import java.awt.GraphicsEnvironment;
/*      */ import java.awt.Insets;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.ItemEvent;
/*      */ import java.awt.event.ItemListener;
/*      */ import java.awt.event.WindowEvent;
/*      */ import java.awt.event.WindowListener;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.net.URL;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.HashMap;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import javax.sound.midi.InvalidMidiDataException;
/*      */ import javax.sound.midi.MetaEventListener;
/*      */ import javax.sound.midi.MetaMessage;
/*      */ import javax.sound.midi.MidiChannel;
/*      */ import javax.sound.midi.MidiEvent;
/*      */ import javax.sound.midi.MidiSystem;
/*      */ import javax.sound.midi.MidiUnavailableException;
/*      */ import javax.sound.midi.Sequence;
/*      */ import javax.sound.midi.Sequencer;
/*      */ import javax.sound.midi.ShortMessage;
/*      */ import javax.sound.midi.Soundbank;
/*      */ import javax.sound.midi.Synthesizer;
/*      */ import javax.sound.midi.Track;
/*      */ import javax.swing.DefaultComboBoxModel;
/*      */ import javax.swing.ImageIcon;
/*      */ import javax.swing.JButton;
/*      */ import javax.swing.JComboBox;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JFrame;
/*      */ import javax.swing.JLabel;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuBar;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JScrollPane;
/*      */ import javax.swing.JSeparator;
/*      */ import javax.swing.JSlider;
/*      */ import javax.swing.JSpinner;
/*      */ import javax.swing.JToolBar;
/*      */ import javax.swing.KeyStroke;
/*      */ import javax.swing.SpinnerNumberModel;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.border.EmptyBorder;
/*      */ import javax.swing.event.ChangeEvent;
/*      */ import javax.swing.event.ChangeListener;
/*      */ import javax.swing.filechooser.FileNameExtensionFilter;
/*      */ import model.DrumTrack;
/*      */ import model.Drumkit;
/*      */ import model.EditorTableModel;
/*      */ import model.Instrument;
/*      */ import model.MRUlist;
/*      */ import model.Note;
/*      */ import tools.ErrorHandler;
/*      */ import tools.FileUtilities;
/*      */ import view.editor.drumkitEditor.DrumKitEditor;
/*      */ import view.editor.editorTable.AdjustNoteVolume;
/*      */ import view.editor.editorTable.EditorTable;
/*      */ import view.editor.editorTable.InsertBars;
/*      */ import view.editor.editorTable.RandomizeNotes;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class BeatBuilder
/*      */   extends JFrame
/*      */   implements ActionListener, ItemListener, WindowListener, MetaEventListener
/*      */ {
/*   96 */   private DrumTrack m_DrumTrack = new DrumTrack();
/*      */   
/*      */   private JPanel m_contentPane;
/*      */   private EditorTable m_EditorTable;
/*  100 */   private final JFileChooser m_FileChooser = new JFileChooser();
/*  101 */   private Sequencer m_Sequencer = null;
/*      */   
/*  103 */   private Integer m_iNewNoteVelocity = new Integer(70);
/*      */   private JSpinner spinnerVelocity;
/*  105 */   private LinkedHashMap<String, Drumkit> m_DrumKits = new LinkedHashMap<>();
/*      */   
/*  107 */   private String m_strActiveDrumkitName = null;
/*  108 */   private Integer m_iTempo = Integer.valueOf(120);
/*  109 */   EditorTableModel m_EditorTableModel = null;
/*  110 */   JScrollPane m_ScrollPane = null;
/*      */   JSpinner m_SpinnerTempo;
/*  112 */   JSlider m_sliderZoom = null;
/*  113 */   JComboBox<String> m_ComboTimeSig = null;
/*  114 */   JComboBox<String> m_ComboBoxPlayMode = null;
/*  115 */   JComboBox<String> comboBoxKits = null;
/*  116 */   HashMap<String, String> m_AppConfig = new HashMap<>();
/*  117 */   String m_strWorkingDIrectory = "";
/*  118 */   MRUlist m_MRUlist = null;
/*  119 */   JMenu mnFileMenu = null;
/*      */   
/*  121 */   JMenuItem mntmMRU1 = new JMenuItem();
/*  122 */   JMenuItem mntmMRU2 = new JMenuItem();
/*  123 */   JMenuItem mntmMRU3 = new JMenuItem();
/*  124 */   JMenuItem mntmMRU4 = new JMenuItem();
/*  125 */   JMenuItem mntmMRU5 = new JMenuItem();
/*      */   
/*  127 */   Drumkit m_CurrentDrumKit = null;
/*      */   
/*  129 */   Tracker m_Tracker = null;
/*      */   
/*  131 */   int m_iStartingPosition = 0;
/*  132 */   int m_iEndingPosition = 0;
/*  133 */   int m_iPlaybackOffset = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void main(String[] args) {
/*  140 */     EventQueue.invokeLater(new Runnable()
/*      */         {
/*      */ 
/*      */           
/*      */           public void run()
/*      */           {
/*      */             try {
/*  147 */               BeatBuilder frame = new BeatBuilder();
/*  148 */               frame.setVisible(true);
/*  149 */             } catch (Exception e) {
/*      */               
/*  151 */               e.printStackTrace();
/*      */             } 
/*      */           }
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BeatBuilder() {
/*  164 */     addWindowListener(this);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  170 */     ImageIcon icon = new ImageIcon(BeatBuilder.class.getResource("BeatBuilder.gif"));
/*  171 */     setIconImage(icon.getImage());
/*      */     
/*  173 */     this.m_AppConfig = FileUtilities.loadINIfile();
/*      */     
/*  175 */     initializeMidi2();
/*      */     
/*  177 */     this.m_FileChooser.addChoosableFileFilter(new FileNameExtensionFilter("MIDI file", new String[] { "mid" }));
/*  178 */     this.m_FileChooser.setAcceptAllFileFilterUsed(false);
/*      */     
/*  180 */     String strWorkingDir = this.m_AppConfig.get("WorkingDirectory");
/*  181 */     if (strWorkingDir != null && !strWorkingDir.isEmpty()) {
/*      */       
/*  183 */       this.m_strWorkingDIrectory = strWorkingDir;
/*  184 */       this.m_FileChooser.setCurrentDirectory(new File(this.m_strWorkingDIrectory));
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  202 */     this.m_CurrentDrumKit = FileUtilities.getStandardDrumKit();
/*  203 */     this.m_MRUlist = new MRUlist(this.m_AppConfig);
/*      */     
/*  205 */     this.m_EditorTableModel = new EditorTableModel(this.m_CurrentDrumKit);
/*      */     
/*  207 */     setTitle("BeatBuilder");
/*  208 */     setBackground(UIManager.getColor("Panel.background"));
/*  209 */     setDefaultCloseOperation(3);
/*      */     
/*  211 */     GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
/*  212 */       .getDefaultScreenDevice();
/*  213 */     int width = gd.getDisplayMode().getWidth();
/*  214 */     setBounds(10, 50, width - 20, 640);
/*      */     
/*  216 */     this.m_contentPane = new JPanel();
/*  217 */     this.m_contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  218 */     this.m_contentPane.setLayout(new BorderLayout(0, 0));
/*  219 */     setContentPane(this.m_contentPane);
/*      */     
/*  221 */     JMenuBar menuBar = new JMenuBar();
/*  222 */     setJMenuBar(menuBar);
/*      */     
/*  224 */     this.mnFileMenu = new JMenu("File");
/*  225 */     this.mnFileMenu.setMnemonic(70);
/*  226 */     menuBar.add(this.mnFileMenu);
/*      */     
/*  228 */     JMenuItem mntmNew = new JMenuItem("New");
/*  229 */     mntmNew.addActionListener(this);
/*  230 */     mntmNew.setMnemonic(78);
/*  231 */     mntmNew.setActionCommand("new");
/*  232 */     mntmNew.setAccelerator(KeyStroke.getKeyStroke(78, 2));
/*  233 */     this.mnFileMenu.add(mntmNew);
/*      */     
/*  235 */     JMenuItem mntmOpen = new JMenuItem("Open...");
/*  236 */     mntmOpen.setAccelerator(KeyStroke.getKeyStroke(79, 2));
/*  237 */     mntmOpen.setActionCommand("open");
/*  238 */     mntmOpen.setMnemonic(79);
/*  239 */     mntmOpen.addActionListener(this);
/*  240 */     this.mnFileMenu.add(mntmOpen);
/*      */     
/*  242 */     JSeparator separator_s = new JSeparator();
/*  243 */     this.mnFileMenu.add(separator_s);
/*      */     
/*  245 */     JMenuItem mntmSave = new JMenuItem("Save");
/*  246 */     mntmSave.setActionCommand("save");
/*  247 */     mntmSave.addActionListener(this);
/*  248 */     mntmSave.setMnemonic(83);
/*  249 */     mntmSave.setAccelerator(KeyStroke.getKeyStroke(83, 2));
/*  250 */     this.mnFileMenu.add(mntmSave);
/*      */     
/*  252 */     JMenuItem mntmSaveAs = new JMenuItem("Save As...");
/*  253 */     mntmSaveAs.setActionCommand("saveas");
/*  254 */     mntmSaveAs.setMnemonic(65);
/*  255 */     mntmSaveAs.addActionListener(this);
/*  256 */     this.mnFileMenu.add(mntmSaveAs);
/*      */     
/*  258 */     JMenuItem mntmSaveSelection = new JMenuItem("Save Selection As...");
/*  259 */     mntmSaveSelection.setActionCommand("saveselection");
/*  260 */     mntmSaveSelection.addActionListener(this);
/*  261 */     mntmSaveSelection.setMnemonic(86);
/*  262 */     this.mnFileMenu.add(mntmSaveSelection);
/*      */     
/*  264 */     JSeparator separator_mru = new JSeparator();
/*  265 */     this.mnFileMenu.add(separator_mru);
/*      */     
/*  267 */     this.mntmMRU1.setActionCommand("mru_1");
/*  268 */     this.mntmMRU1.addActionListener(this);
/*  269 */     this.mntmMRU1.setVisible(false);
/*  270 */     this.mnFileMenu.add(this.mntmMRU1);
/*  271 */     this.mntmMRU2.setActionCommand("mru_2");
/*  272 */     this.mntmMRU2.addActionListener(this);
/*  273 */     this.mntmMRU2.setVisible(false);
/*  274 */     this.mnFileMenu.add(this.mntmMRU2);
/*  275 */     this.mntmMRU3.setActionCommand("mru_3");
/*  276 */     this.mntmMRU3.addActionListener(this);
/*  277 */     this.mntmMRU3.setVisible(false);
/*  278 */     this.mnFileMenu.add(this.mntmMRU3);
/*  279 */     this.mntmMRU4.setActionCommand("mru_4");
/*  280 */     this.mntmMRU4.addActionListener(this);
/*  281 */     this.mntmMRU4.setVisible(false);
/*  282 */     this.mnFileMenu.add(this.mntmMRU4);
/*  283 */     this.mntmMRU5.setActionCommand("mru_5");
/*  284 */     this.mntmMRU5.addActionListener(this);
/*  285 */     this.mntmMRU5.setVisible(false);
/*  286 */     this.mnFileMenu.add(this.mntmMRU5);
/*      */     
/*  288 */     for (int mruItem = 0; mruItem < 5; mruItem++) {
/*      */       
/*  290 */       String strFile = this.m_MRUlist.getFileNameAt(mruItem);
/*  291 */       if (strFile != null)
/*      */       {
/*  293 */         switch (mruItem) {
/*      */           
/*      */           case 0:
/*  296 */             this.mntmMRU1.setText(strFile);
/*  297 */             this.mntmMRU1.setVisible(true);
/*      */             break;
/*      */           case 1:
/*  300 */             this.mntmMRU2.setText(strFile);
/*  301 */             this.mntmMRU2.setVisible(true);
/*      */             break;
/*      */           case 2:
/*  304 */             this.mntmMRU3.setText(strFile);
/*  305 */             this.mntmMRU3.setVisible(true);
/*      */             break;
/*      */           case 3:
/*  308 */             this.mntmMRU4.setText(strFile);
/*  309 */             this.mntmMRU4.setVisible(true);
/*      */             break;
/*      */           case 4:
/*  312 */             this.mntmMRU5.setText(strFile);
/*  313 */             this.mntmMRU5.setVisible(true);
/*      */             break;
/*      */         } 
/*      */       
/*      */       }
/*      */     } 
/*  319 */     JSeparator separator_ext = new JSeparator();
/*  320 */     this.mnFileMenu.add(separator_ext);
/*      */     
/*  322 */     JMenuItem mntmExit = new JMenuItem("Exit");
/*  323 */     mntmExit.addActionListener(this);
/*  324 */     mntmExit.setActionCommand("exit");
/*  325 */     mntmExit.setMnemonic(88);
/*  326 */     this.mnFileMenu.add(mntmExit);
/*      */     
/*  328 */     JMenu mnEdit = new JMenu("Edit");
/*  329 */     mnEdit.setMnemonic(69);
/*  330 */     menuBar.add(mnEdit);
/*      */     
/*  332 */     JMenuItem mntmCutMenuItem = new JMenuItem("Cut Notes");
/*  333 */     mntmCutMenuItem.setAccelerator(KeyStroke.getKeyStroke(88, 2));
/*  334 */     mntmCutMenuItem.setActionCommand("cutnotes");
/*  335 */     mntmCutMenuItem.addActionListener(this);
/*  336 */     mnEdit.add(mntmCutMenuItem);
/*      */     
/*  338 */     JMenuItem mntmCutBarsMenuItem = new JMenuItem("Cut Time");
/*      */ 
/*      */     
/*  341 */     mntmCutBarsMenuItem.setMnemonic(67);
/*  342 */     mntmCutBarsMenuItem.setActionCommand("cutbars");
/*  343 */     mntmCutBarsMenuItem.addActionListener(this);
/*  344 */     mnEdit.add(mntmCutBarsMenuItem);
/*      */     
/*  346 */     JSeparator separator_cc = new JSeparator();
/*  347 */     mnEdit.add(separator_cc);
/*      */     
/*  349 */     JMenuItem mntmCopyMenuItem = new JMenuItem("Copy");
/*  350 */     mntmCopyMenuItem
/*  351 */       .setAccelerator(KeyStroke.getKeyStroke(67, 2));
/*  352 */     mntmCopyMenuItem.setActionCommand("copy");
/*  353 */     mntmCopyMenuItem.addActionListener(this);
/*  354 */     mnEdit.add(mntmCopyMenuItem);
/*      */     
/*  356 */     JMenuItem mntmPasteMenuItem = new JMenuItem("Paste");
/*  357 */     mntmPasteMenuItem.setAccelerator(
/*  358 */         KeyStroke.getKeyStroke(86, 2));
/*  359 */     mntmPasteMenuItem.setActionCommand("paste");
/*  360 */     mntmPasteMenuItem.addActionListener(this);
/*  361 */     mnEdit.add(mntmPasteMenuItem);
/*      */     
/*  363 */     JMenuItem mntmPasteIMenuItem = new JMenuItem("Paste Insert");
/*  364 */     mntmPasteIMenuItem.setActionCommand("pasteinsert");
/*  365 */     mntmPasteIMenuItem.setMnemonic(80);
/*  366 */     mntmPasteIMenuItem.addActionListener(this);
/*  367 */     mnEdit.add(mntmPasteIMenuItem);
/*      */     
/*  369 */     JSeparator separator_ct = new JSeparator();
/*  370 */     mnEdit.add(separator_ct);
/*      */     
/*  372 */     JMenuItem mntmDeleteMenuItem = new JMenuItem("Delete Notes");
/*  373 */     mntmDeleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(127, 0));
/*  374 */     mntmDeleteMenuItem.setActionCommand("deletenotes");
/*  375 */     mntmDeleteMenuItem.addActionListener(this);
/*  376 */     mnEdit.add(mntmDeleteMenuItem);
/*      */     
/*  378 */     JMenuItem mntmDeleteBarsMenuItem = new JMenuItem("Delete Time");
/*  379 */     mntmDeleteBarsMenuItem.setAccelerator(KeyStroke.getKeyStroke(68, 
/*  380 */           2));
/*      */ 
/*      */     
/*  383 */     mntmDeleteBarsMenuItem.setActionCommand("deletebars");
/*  384 */     mntmDeleteBarsMenuItem.addActionListener(this);
/*  385 */     mnEdit.add(mntmDeleteBarsMenuItem);
/*      */     
/*  387 */     JSeparator separator_ci = new JSeparator();
/*  388 */     mnEdit.add(separator_ci);
/*      */     
/*  390 */     JMenuItem mntmInsertBars = new JMenuItem("Insert Time...");
/*  391 */     mntmInsertBars.setAccelerator(KeyStroke.getKeyStroke(73, 2));
/*  392 */     mntmInsertBars.setActionCommand("insertbars");
/*  393 */     mntmInsertBars.addActionListener(this);
/*  394 */     mnEdit.add(mntmInsertBars);
/*      */     
/*  396 */     JMenu mnTools = new JMenu("Tools");
/*  397 */     mnTools.setMnemonic(84);
/*  398 */     menuBar.add(mnTools);
/*      */     
/*  400 */     JMenuItem mntmAdjustAllVolumes = new JMenuItem("Adjust All Volumes...");
/*  401 */     mntmAdjustAllVolumes.setActionCommand("adjustallvolumes");
/*  402 */     mntmAdjustAllVolumes.setMnemonic(65);
/*  403 */     mntmAdjustAllVolumes.addActionListener(this);
/*  404 */     mnTools.add(mntmAdjustAllVolumes);
/*      */     
/*  406 */     JMenuItem mntmRandomizeNotes = new JMenuItem("Randomize Notes...");
/*  407 */     mntmRandomizeNotes.setActionCommand("randomizenotes");
/*  408 */     mntmRandomizeNotes.setMnemonic(82);
/*  409 */     mntmRandomizeNotes.addActionListener(this);
/*  410 */     mnTools.add(mntmRandomizeNotes);
/*      */     
/*  412 */     JMenuItem mntmQuantizeNotes = new JMenuItem("Quantize Notes...");
/*  413 */     mntmQuantizeNotes.setActionCommand("quantizenotes");
/*  414 */     mntmQuantizeNotes.setMnemonic(81);
/*  415 */     mntmQuantizeNotes.addActionListener(this);
/*  416 */     mnTools.add(mntmQuantizeNotes);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  422 */     mnTools.add(new JSeparator());
/*      */     
/*  424 */     JMenuItem mntmEventCount = new JMenuItem("Show Event Count...");
/*  425 */     mntmEventCount.setActionCommand("eventcount");
/*  426 */     mntmQuantizeNotes.setMnemonic(67);
/*  427 */     mntmEventCount.addActionListener(this);
/*  428 */     mnTools.add(mntmEventCount);
/*      */     
/*  430 */     JMenuItem mntmEventCountSelected = new JMenuItem("Show Event Count For Selection...");
/*  431 */     mntmEventCountSelected.setActionCommand("eventcountselection");
/*  432 */     mntmEventCountSelected.setAccelerator(KeyStroke.getKeyStroke(113, 0));
/*  433 */     mntmEventCountSelected.addActionListener(this);
/*  434 */     mnTools.add(mntmEventCountSelected);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  445 */     JMenu mnHelp = new JMenu("Help");
/*  446 */     mnHelp.setMnemonic(72);
/*  447 */     menuBar.add(mnHelp);
/*      */     
/*  449 */     JMenuItem mntmWelcome = new JMenuItem("Welcome...");
/*  450 */     mntmWelcome.setActionCommand("welcome");
/*  451 */     mntmWelcome.setMnemonic(87);
/*  452 */     mntmWelcome.addActionListener(this);
/*  453 */     mnHelp.add(mntmWelcome);
/*      */     
/*  455 */     JMenuItem mntmHelp = new JMenuItem("Show Help...");
/*  456 */     mntmHelp.setAccelerator(KeyStroke.getKeyStroke(112, 0));
/*  457 */     mntmHelp.setActionCommand("help");
/*  458 */     mntmHelp.addActionListener(this);
/*  459 */     mnHelp.add(mntmHelp);
/*      */     
/*  461 */     JMenuItem mntmInfo = new JMenuItem("Sound System Info...");
/*  462 */     mntmInfo.setActionCommand("sysinfo");
/*  463 */     mntmInfo.setMnemonic(83);
/*  464 */     mntmInfo.addActionListener(this);
/*  465 */     mnHelp.add(mntmInfo);
/*      */     
/*  467 */     JMenuItem mntmAbout = new JMenuItem("About...");
/*  468 */     mntmAbout.setActionCommand("about");
/*  469 */     mntmAbout.setMnemonic(65);
/*  470 */     mntmAbout.addActionListener(this);
/*  471 */     mnHelp.add(mntmAbout);
/*      */     
/*  473 */     JToolBar toolBar = new JToolBar();
/*  474 */     toolBar.setFloatable(false);
/*      */     
/*  476 */     JButton btnNewButton = new JButton(new ImageIcon(BeatBuilder.class.getResource("new.gif")));
/*  477 */     btnNewButton.setToolTipText("New");
/*  478 */     btnNewButton.addActionListener(this);
/*  479 */     btnNewButton.setActionCommand("new");
/*  480 */     btnNewButton.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  482 */     toolBar.add(btnNewButton);
/*      */     
/*  484 */     JButton btnOpenButton = new JButton(
/*  485 */         new ImageIcon(BeatBuilder.class.getResource("open.gif")));
/*  486 */     btnOpenButton.addActionListener(this);
/*  487 */     btnOpenButton.setActionCommand("open");
/*  488 */     btnOpenButton.setToolTipText("Open");
/*  489 */     btnOpenButton.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  491 */     toolBar.add(btnOpenButton);
/*      */     
/*  493 */     JButton btnSave = new JButton(new ImageIcon(BeatBuilder.class.getResource("save.gif")));
/*  494 */     btnSave.addActionListener(this);
/*  495 */     btnSave.setActionCommand("save");
/*  496 */     btnSave.setToolTipText("Save");
/*  497 */     btnSave.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  499 */     toolBar.add(btnSave);
/*      */     
/*  501 */     JSeparator separator_4 = new JSeparator();
/*  502 */     separator_4.setOrientation(1);
/*  503 */     separator_4.setPreferredSize(new Dimension(10, 20));
/*  504 */     separator_4.setMaximumSize(new Dimension(10, 20));
/*  505 */     toolBar.add(separator_4);
/*      */     
/*  507 */     JButton btnCut = new JButton(new ImageIcon(BeatBuilder.class.getResource("cut.gif")));
/*  508 */     btnCut.addActionListener(this);
/*  509 */     btnCut.setActionCommand("cutnotes");
/*  510 */     btnCut.setToolTipText("Cut Notes");
/*  511 */     btnCut.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  513 */     toolBar.add(btnCut);
/*      */     
/*  515 */     JButton btnCopy = new JButton(new ImageIcon(BeatBuilder.class.getResource("copy.gif")));
/*  516 */     btnCopy.addActionListener(this);
/*  517 */     btnCopy.setActionCommand("copy");
/*  518 */     btnCopy.setToolTipText("Copy");
/*  519 */     btnCopy.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  521 */     toolBar.add(btnCopy);
/*      */     
/*  523 */     JButton btnPaste = new JButton(new ImageIcon(BeatBuilder.class.getResource("paste.gif")));
/*  524 */     btnPaste.addActionListener(this);
/*  525 */     btnPaste.setActionCommand("pasteinsert");
/*  526 */     btnPaste.setToolTipText("Paste");
/*  527 */     btnPaste.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  529 */     toolBar.add(btnPaste);
/*      */     
/*  531 */     JButton btnDelete = new JButton(new ImageIcon(BeatBuilder.class.getResource("delete.gif")));
/*  532 */     btnDelete.addActionListener(this);
/*  533 */     btnDelete.setActionCommand("deletenotes");
/*  534 */     btnDelete.setToolTipText("Delete Notes");
/*  535 */     btnDelete.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  537 */     toolBar.add(btnPaste);
/*      */     
/*  539 */     JSeparator separator_a = new JSeparator();
/*  540 */     separator_a.setOrientation(1);
/*  541 */     separator_a.setPreferredSize(new Dimension(10, 20));
/*  542 */     separator_a.setMaximumSize(new Dimension(10, 20));
/*  543 */     toolBar.add(separator_a);
/*      */     
/*  545 */     JButton btnPlayButton = new JButton(
/*  546 */         new ImageIcon(BeatBuilder.class.getResource("play.gif")));
/*  547 */     btnPlayButton.addActionListener(this);
/*  548 */     btnPlayButton.setActionCommand("play");
/*  549 */     btnPlayButton.setToolTipText("Play");
/*  550 */     btnPlayButton.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  552 */     toolBar.add(btnPlayButton);
/*      */     
/*  554 */     JButton btnStopButton = new JButton(
/*  555 */         new ImageIcon(BeatBuilder.class.getResource("stop.gif")));
/*  556 */     btnStopButton.setToolTipText("Stop");
/*  557 */     btnStopButton.setMargin(new Insets(0, 0, 0, 0));
/*      */     
/*  559 */     btnStopButton.addActionListener(new ActionListener()
/*      */         {
/*      */           public void actionPerformed(ActionEvent p_Event)
/*      */           {
/*  563 */             BeatBuilder.this.m_Sequencer.stop();
/*  564 */             BeatBuilder.this.stopTracker();
/*  565 */             BeatBuilder.this.m_EditorTable.setSelectedColumnRange(BeatBuilder.this.m_iStartingPosition, BeatBuilder.this.m_iEndingPosition);
/*      */           }
/*      */         });
/*  568 */     toolBar.add(btnStopButton);
/*      */     
/*  570 */     JLabel lblPlaybackMode = new JLabel("  Play Mode: ");
/*  571 */     toolBar.add(lblPlaybackMode);
/*      */     
/*  573 */     this.m_ComboBoxPlayMode = new JComboBox<>();
/*  574 */     this.m_ComboBoxPlayMode.setModel(new DefaultComboBoxModel<>(new String[] { "All", 
/*  575 */             "Selection", "Start at Selected", "End At Selected" }));
/*  576 */     this.m_ComboBoxPlayMode.setMaximumSize(new Dimension(100, 22));
/*  577 */     this.m_ComboBoxPlayMode.setPreferredSize(new Dimension(100, 22));
/*  578 */     toolBar.add(this.m_ComboBoxPlayMode);
/*      */     
/*  580 */     JSeparator separator_2 = new JSeparator();
/*  581 */     separator_2.setOrientation(1);
/*  582 */     toolBar.add(separator_2);
/*      */     
/*  584 */     JLabel label = new JLabel("Time Signature: ");
/*  585 */     toolBar.add(label);
/*      */     
/*  587 */     this.m_ComboTimeSig = new JComboBox<>();
/*  588 */     this.m_ComboTimeSig.setModel(new DefaultComboBoxModel<>(new String[] { "2/2", "3/2", 
/*  589 */             "4/2", "2/4", "3/4", "4/4", "5/4", "6/4", "7/4", "3/8", "5/8", "6/8", "7/8", "9/8", 
/*  590 */             "12/8" }));
/*  591 */     this.m_ComboTimeSig.setSelectedIndex(5);
/*  592 */     this.m_ComboTimeSig.setPreferredSize(new Dimension(50, 22));
/*  593 */     this.m_ComboTimeSig.setMaximumSize(new Dimension(50, 22));
/*  594 */     this.m_ComboTimeSig.addItemListener(new ItemListener()
/*      */         {
/*      */ 
/*      */           
/*      */           public void itemStateChanged(ItemEvent p_ev)
/*      */           {
/*  600 */             if (p_ev.getStateChange() == 1) {
/*      */               
/*  602 */               String strTimeSig = (String)p_ev.getItem();
/*      */               
/*  604 */               String strBeats = strTimeSig.substring(0, strTimeSig.indexOf("/"));
/*  605 */               String strType = strTimeSig.substring(strTimeSig.indexOf("/") + 1);
/*  606 */               BeatBuilder.this.m_DrumTrack.setNotesPerMeasure(Integer.valueOf(strBeats).intValue());
/*  607 */               BeatBuilder.this.m_DrumTrack.setNoteType(Integer.valueOf(strType).intValue());
/*  608 */               BeatBuilder.this.m_DrumTrack.setTimeSignature(Integer.valueOf(strBeats).intValue(), 
/*  609 */                   Integer.valueOf(strType).intValue());
/*      */               
/*  611 */               BeatBuilder.this.m_EditorTable.setColumnWidths(Integer.valueOf(BeatBuilder.this.m_sliderZoom.getValue()));
/*  612 */               BeatBuilder.this.m_EditorTable.repaint();
/*      */             } 
/*      */           }
/*      */         });
/*      */ 
/*      */ 
/*      */     
/*  619 */     toolBar.add(this.m_ComboTimeSig);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  634 */     JSeparator separator_6 = new JSeparator();
/*  635 */     separator_6.setOrientation(1);
/*  636 */     separator_6.setPreferredSize(new Dimension(10, 22));
/*  637 */     separator_6.setMaximumSize(new Dimension(10, 22));
/*  638 */     toolBar.add(separator_6);
/*      */     
/*  640 */     JLabel lblNewLabel = new JLabel("   New Note Volume: ");
/*  641 */     lblNewLabel.setHorizontalAlignment(4);
/*  642 */     toolBar.add(lblNewLabel);
/*      */     
/*  644 */     this.m_contentPane.add(toolBar, "North");
/*      */     
/*  646 */     this.spinnerVelocity = new JSpinner();
/*  647 */     this.spinnerVelocity.setMaximumSize(new Dimension(43, 22));
/*  648 */     this.spinnerVelocity.setPreferredSize(new Dimension(43, 22));
/*  649 */     lblNewLabel.setLabelFor(this.spinnerVelocity);
/*  650 */     this.spinnerVelocity.setModel(new SpinnerNumberModel(70, 1, 127, 1));
/*  651 */     this.spinnerVelocity.addChangeListener(new ChangeListener()
/*      */         {
/*      */           public void stateChanged(ChangeEvent e)
/*      */           {
/*  655 */             BeatBuilder.this.m_iNewNoteVelocity = (Integer)BeatBuilder.this.spinnerVelocity.getValue();
/*  656 */             BeatBuilder.this.m_EditorTable.setNewNoteVelocity(BeatBuilder.this.m_iNewNoteVelocity);
/*      */           }
/*      */         });
/*      */     
/*  660 */     toolBar.add(this.spinnerVelocity);
/*      */     
/*  662 */     JSeparator separator_7 = new JSeparator();
/*  663 */     separator_7.setOrientation(1);
/*  664 */     separator_7.setPreferredSize(new Dimension(10, 22));
/*  665 */     separator_7.setMaximumSize(new Dimension(10, 22));
/*  666 */     toolBar.add(separator_7);
/*      */     
/*  668 */     JLabel lblNewLabel_1 = new JLabel("  Tempo: ");
/*  669 */     lblNewLabel_1.setHorizontalAlignment(4);
/*  670 */     toolBar.add(lblNewLabel_1);
/*      */     
/*  672 */     this.m_SpinnerTempo = new JSpinner();
/*  673 */     this.m_SpinnerTempo.setMaximumSize(new Dimension(43, 22));
/*  674 */     this.m_SpinnerTempo.setPreferredSize(new Dimension(43, 22));
/*  675 */     this.m_SpinnerTempo.setModel(new SpinnerNumberModel(120, 40, 240, 1));
/*  676 */     lblNewLabel_1.setLabelFor(this.m_SpinnerTempo);
/*  677 */     toolBar.add(this.m_SpinnerTempo);
/*  678 */     this.m_SpinnerTempo.addChangeListener(new ChangeListener()
/*      */         {
/*      */           public void stateChanged(ChangeEvent e)
/*      */           {
/*  682 */             BeatBuilder.this.m_iTempo = (Integer)BeatBuilder.this.m_SpinnerTempo.getValue();
/*  683 */             BeatBuilder.this.m_DrumTrack.setTempo(BeatBuilder.this.m_iTempo.intValue());
/*      */           }
/*      */         });
/*      */     
/*  687 */     JLabel lblZoom = new JLabel("  Zoom: ");
/*  688 */     toolBar.add(lblZoom);
/*      */     
/*  690 */     this.m_sliderZoom = new JSlider();
/*  691 */     this.m_sliderZoom.setOpaque(false);
/*  692 */     this.m_sliderZoom.setPaintTicks(true);
/*  693 */     this.m_sliderZoom.setMajorTickSpacing(2);
/*  694 */     this.m_sliderZoom.setSnapToTicks(true);
/*  695 */     this.m_sliderZoom.setMinimum(4);
/*  696 */     this.m_sliderZoom.setMaximum(16);
/*  697 */     this.m_sliderZoom.setValue(10);
/*  698 */     this.m_sliderZoom.setMaximumSize(new Dimension(90, 22));
/*  699 */     this.m_sliderZoom.setPreferredSize(new Dimension(90, 22));
/*  700 */     this.m_sliderZoom.addChangeListener(new ChangeListener()
/*      */         {
/*      */           public void stateChanged(ChangeEvent e)
/*      */           {
/*  704 */             BeatBuilder.this.m_EditorTable.setColumnWidths(Integer.valueOf(BeatBuilder.this.m_sliderZoom.getValue()));
/*  705 */             BeatBuilder.this.m_EditorTable.repaint();
/*      */           }
/*      */         });
/*      */     
/*  709 */     toolBar.add(this.m_sliderZoom);
/*      */     
/*  711 */     this.m_EditorTable = new EditorTable(this.m_EditorTableModel, this.m_DrumTrack);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  716 */     this.m_ScrollPane = new JScrollPane((Component)this.m_EditorTable);
/*  717 */     this.m_ScrollPane.getHorizontalScrollBar().setBlockIncrement(20);
/*  718 */     this.m_ScrollPane.getHorizontalScrollBar().setUnitIncrement(5);
/*      */     
/*  720 */     this.m_EditorTable.setScrollPane(this.m_ScrollPane);
/*      */     
/*  722 */     this.m_contentPane.add(this.m_ScrollPane, "Center");
/*      */ 
/*      */     
/*  725 */     this.m_ScrollPane.setRowHeaderView(this.m_EditorTable.getRowHeader());
/*  726 */     this.m_ScrollPane.setBackground(UIManager.getColor("Panel.background"));
/*      */     
/*  728 */     if (this.m_AppConfig.get("welcomeshown") == null) {
/*      */       
/*  730 */       this.m_AppConfig.put("welcomeshown", "true");
/*      */       
/*  732 */       SwingUtilities.invokeLater(new Runnable()
/*      */           {
/*      */             
/*      */             public void run()
/*      */             {
/*  737 */               TipWindow tipWindow = new TipWindow();
/*  738 */               tipWindow.showDialog();
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void actionPerformed(ActionEvent quitEvent) {
/*  750 */     this.m_Sequencer.stop();
/*  751 */     stopTracker();
/*      */     
/*  753 */     String actionCommand = quitEvent.getActionCommand();
/*      */     
/*  755 */     if (actionCommand.equals("exit")) {
/*      */       
/*  757 */       this.m_Sequencer.close();
/*      */       
/*  759 */       processWindowEvent(new WindowEvent(this, 201));
/*      */     } 
/*      */     
/*  762 */     if (actionCommand.equals("open")) {
/*      */       
/*  764 */       if (this.m_DrumTrack.isDirty())
/*      */       {
/*  766 */         if (JOptionPane.showConfirmDialog(this, 
/*  767 */             "You have unsaved changes, are you sure you want to continue?", "Confirm", 
/*  768 */             0, 2) != 0) {
/*      */           return;
/*      */         }
/*      */       }
/*      */ 
/*      */       
/*  774 */       int returnVal = this.m_FileChooser.showOpenDialog(this);
/*      */       
/*  776 */       if (returnVal == 0) {
/*      */         
/*  778 */         File file = this.m_FileChooser.getSelectedFile();
/*  779 */         this.m_strWorkingDIrectory = file.getPath();
/*  780 */         this.m_AppConfig.put("WorkingDirectory", this.m_strWorkingDIrectory);
/*  781 */         List<Note> notes = null;
/*      */ 
/*      */         
/*      */         try {
/*  785 */           notes = FileUtilities.loadMIDIFile(this.m_Sequencer, file, this.m_CurrentDrumKit, 
/*  786 */               this.m_DrumTrack);
/*  787 */         } catch (InvalidMidiDataException ex) {
/*      */           
/*  789 */           ErrorHandler.showError(ex);
/*      */           return;
/*  791 */         } catch (IOException ioex) {
/*      */           
/*  793 */           ErrorHandler.showError(ioex);
/*      */           return;
/*      */         } 
/*  796 */         updateMRUList(file);
/*  797 */         this.m_CurrentDrumKit.setNotes(notes);
/*      */         
/*  799 */         this.m_ScrollPane.getHorizontalScrollBar().setValue(0);
/*  800 */         this.m_ScrollPane.getVerticalScrollBar().setValue(0);
/*  801 */         this.m_ScrollPane.repaint();
/*  802 */         this.m_EditorTable.repaint();
/*      */         
/*  804 */         this.m_DrumTrack.setFileName(file);
/*  805 */         this.m_DrumTrack.setNew(false);
/*  806 */         this.m_DrumTrack.setDirty(false);
/*  807 */         setTitle("BeatBuilder [" + this.m_DrumTrack.getTrackName() + "]");
/*  808 */         this.m_SpinnerTempo.setValue(Integer.valueOf(this.m_DrumTrack.getTempo()));
/*      */         
/*  810 */         this.m_ComboTimeSig.setSelectedItem(this.m_DrumTrack.getTimeSignatureAsString());
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  815 */     if (actionCommand.equals("save")) {
/*      */       
/*  817 */       if (this.m_DrumTrack.isNew()) {
/*      */         
/*  819 */         int returnVal = this.m_FileChooser.showSaveDialog(this);
/*      */         
/*  821 */         if (returnVal == 0) {
/*      */           
/*  823 */           File file = this.m_FileChooser.getSelectedFile();
/*  824 */           this.m_strWorkingDIrectory = file.getPath();
/*  825 */           this.m_AppConfig.put("WorkingDirectory", this.m_strWorkingDIrectory);
/*  826 */           if (!file.getName().toUpperCase().endsWith(".MID")) {
/*      */             
/*  828 */             String strFileName = String.valueOf(file.getPath()) + ".mid";
/*  829 */             file = new File(strFileName);
/*      */           } 
/*      */           
/*      */           try {
/*  833 */             FileUtilities.saveMIDIFile(file, generateSequence(this.m_DrumTrack.getPPQ()));
/*  834 */           } catch (IOException ex) {
/*      */             
/*  836 */             JOptionPane.showMessageDialog(this, ex.getMessage());
/*      */           } 
/*  838 */           this.m_DrumTrack.setFileName(file);
/*  839 */           this.m_DrumTrack.setNew(false);
/*  840 */           this.m_DrumTrack.setDirty(false);
/*  841 */           updateMRUList(file);
/*      */         } 
/*      */       } else {
/*      */         
/*  845 */         File file = new File(this.m_DrumTrack.getFileName());
/*      */         
/*      */         try {
/*  848 */           FileUtilities.saveMIDIFile(file, generateSequence(this.m_DrumTrack.getPPQ()));
/*  849 */         } catch (IOException ex) {
/*      */           
/*  851 */           JOptionPane.showMessageDialog(this, ex.getMessage());
/*      */         } 
/*  853 */         this.m_DrumTrack.setFileName(file);
/*      */       } 
/*      */       
/*  856 */       this.m_DrumTrack.setDirty(false);
/*  857 */       this.m_DrumTrack.setNew(false);
/*      */     } 
/*      */     
/*  860 */     if (actionCommand.equals("saveas") || actionCommand.equals("saveselection")) {
/*      */       
/*  862 */       int returnVal = this.m_FileChooser.showSaveDialog(this);
/*      */       
/*  864 */       if (returnVal == 0) {
/*      */         
/*  866 */         File file = this.m_FileChooser.getSelectedFile();
/*  867 */         this.m_strWorkingDIrectory = file.getPath();
/*  868 */         this.m_AppConfig.put("WorkingDirectory", this.m_strWorkingDIrectory);
/*  869 */         if (!file.getName().toUpperCase().endsWith(".MID")) {
/*      */           
/*  871 */           String strFileName = String.valueOf(file.getPath()) + ".mid";
/*  872 */           file = new File(strFileName);
/*      */         } 
/*      */         
/*      */         try {
/*  876 */           if (actionCommand.equals("saveselection")) {
/*      */             
/*  878 */             int[] columns = this.m_EditorTable.getSelectedColumns();
/*  879 */             int iStart = columns[0];
/*  880 */             int iEnd = columns[columns.length - 1];
/*  881 */             FileUtilities.saveMIDIFile(file, 
/*  882 */                 generateSequence(this.m_DrumTrack.getPPQ(), Integer.valueOf(iStart), Integer.valueOf(iEnd)));
/*      */           } else {
/*      */             
/*  885 */             FileUtilities.saveMIDIFile(file, generateSequence(this.m_DrumTrack.getPPQ()));
/*      */           } 
/*  887 */         } catch (IOException ex) {
/*      */           
/*  889 */           JOptionPane.showMessageDialog(this, ex.getMessage());
/*      */         } 
/*  891 */         this.m_DrumTrack.setFileName(file);
/*  892 */         this.m_DrumTrack.setNew(false);
/*  893 */         this.m_DrumTrack.setDirty(false);
/*  894 */         updateMRUList(file);
/*      */         
/*  896 */         setTitle("BeatBuilder [" + this.m_DrumTrack.getTrackName() + "]");
/*      */       } 
/*      */     } 
/*      */     
/*  900 */     if (actionCommand.equals("play"))
/*      */     {
/*      */ 
/*      */ 
/*      */       
/*  905 */       play((String)this.m_ComboBoxPlayMode.getSelectedItem());
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  910 */     if (actionCommand.equalsIgnoreCase("editkits")) {
/*      */ 
/*      */       
/*  913 */       DrumKitEditor drumKitEditor = new DrumKitEditor(this, this.m_DrumKits);
/*  914 */       drumKitEditor.setVisible(true);
/*      */     } 
/*  916 */     if (actionCommand.equalsIgnoreCase("insertbars")) {
/*      */       
/*  918 */       InsertBars dlgInsert = new InsertBars();
/*      */       
/*  920 */       Integer iBars = dlgInsert.showDialog();
/*  921 */       if (iBars != null)
/*      */       {
/*  923 */         this.m_EditorTable.insertBars(iBars.intValue());
/*      */       }
/*      */     } 
/*  926 */     if (actionCommand.equalsIgnoreCase("copy"))
/*      */     {
/*  928 */       this.m_EditorTable.copy();
/*      */     }
/*  930 */     if (actionCommand.equalsIgnoreCase("cutnotes"))
/*      */     {
/*  932 */       this.m_EditorTable.cutnotes();
/*      */     }
/*  934 */     if (actionCommand.equalsIgnoreCase("cutbars"))
/*      */     {
/*  936 */       this.m_EditorTable.cutbars();
/*      */     }
/*  938 */     if (actionCommand.equalsIgnoreCase("paste"))
/*      */     {
/*  940 */       this.m_EditorTable.paste();
/*      */     }
/*  942 */     if (actionCommand.equalsIgnoreCase("pasteinsert"))
/*      */     {
/*  944 */       this.m_EditorTable.pasteInsert();
/*      */     }
/*  946 */     if (actionCommand.equalsIgnoreCase("deletenotes"))
/*      */     {
/*  948 */       this.m_EditorTable.deletenotes();
/*      */     }
/*  950 */     if (actionCommand.equalsIgnoreCase("deletebars"))
/*      */     {
/*  952 */       this.m_EditorTable.deletebars();
/*      */     }
/*      */     
/*  955 */     if (actionCommand.equalsIgnoreCase("new")) {
/*      */       
/*  957 */       if (this.m_DrumTrack.isDirty())
/*      */       {
/*  959 */         if (JOptionPane.showConfirmDialog(this, 
/*  960 */             "You have unsaved changes, are you sure you want to continue?", "Confirm", 
/*  961 */             0, 2) != 0) {
/*      */           return;
/*      */         }
/*      */       }
/*      */       
/*  966 */       this.m_DrumTrack.initialize();
/*  967 */       this.m_EditorTable.clearNotes();
/*  968 */       this.m_SpinnerTempo.setValue(Integer.valueOf(this.m_DrumTrack.getTempo()));
/*  969 */       setTitle("BeatBuilder");
/*      */     } 
/*  971 */     if (actionCommand.equalsIgnoreCase("adjustallvolumes")) {
/*      */ 
/*      */       
/*  974 */       AdjustNoteVolume dlgAdjust = new AdjustNoteVolume();
/*      */       
/*  976 */       Integer iAdjustment = dlgAdjust.showDialog();
/*  977 */       if (iAdjustment != null)
/*      */       {
/*  979 */         this.m_EditorTable.adjustAllNoteVolumes(iAdjustment.intValue());
/*      */       }
/*      */     } 
/*      */     
/*  983 */     if (actionCommand.equals("randomizenotes")) {
/*      */       
/*  985 */       RandomizeNotes dlgRandomize = new RandomizeNotes();
/*  986 */       Integer iFactor = dlgRandomize.showDialog();
/*  987 */       if (iFactor != null)
/*      */       {
/*  989 */         this.m_EditorTable.randomizeNotes(iFactor.intValue());
/*      */       }
/*      */     } 
/*      */     
/*  993 */     if (actionCommand.equals("quantizenotes"))
/*      */     {
/*  995 */       this.m_EditorTable.quantizeNotes();
/*      */     }
/*      */     
/*  998 */     if (actionCommand.equalsIgnoreCase("about")) {
/*      */       
/* 1000 */       About dlgAbout = new About();
/* 1001 */       dlgAbout.showDialog();
/*      */     } 
/* 1003 */     if (actionCommand.equalsIgnoreCase("welcome")) {
/*      */       
/* 1005 */       TipWindow dlgTips = new TipWindow();
/* 1006 */       dlgTips.setVisible(true);
/*      */     } 
/* 1008 */     if (actionCommand.equalsIgnoreCase("help")) {
/*      */       
/* 1010 */       HelpWindow dlgHelp = new HelpWindow();
/* 1011 */       dlgHelp.showDialog();
/*      */     } 
/* 1013 */     if (actionCommand.equalsIgnoreCase("sysinfo")) {
/*      */       
/* 1015 */       SystemInfo dlgInfo = new SystemInfo();
/* 1016 */       dlgInfo.showDialog();
/*      */     } 
/* 1018 */     if (actionCommand.equalsIgnoreCase("eventcountselection")) {
/*      */       
/* 1020 */       Long lCount = Long.valueOf(this.m_EditorTable.getSelectedEventCount());
/* 1021 */       JOptionPane.showMessageDialog(this, "Number of Note Events: " + lCount.toString(), 
/* 1022 */           "BeatBuilder", -1);
/*      */     } 
/*      */     
/* 1025 */     if (actionCommand.equalsIgnoreCase("eventcount")) {
/*      */       
/* 1027 */       Long lCount = Long.valueOf(0L);
/* 1028 */       for (Instrument instr : this.m_EditorTableModel.getInstruments())
/*      */       {
/*      */         
/* 1031 */         lCount = Long.valueOf(lCount.longValue() + instr.getNotes().size());
/*      */       }
/* 1033 */       JOptionPane.showMessageDialog(this, "Number of Note Events: " + lCount.toString(), 
/* 1034 */           "BeatBuilder", -1);
/*      */     } 
/* 1036 */     if (actionCommand.startsWith("mru_")) {
/*      */       
/* 1038 */       if (this.m_DrumTrack.isDirty())
/*      */       {
/* 1040 */         if (JOptionPane.showConfirmDialog(this, 
/* 1041 */             "You have unsaved changes, are you sure you want to continue?", "Confirm", 
/* 1042 */             0, 2) != 0) {
/*      */           return;
/*      */         }
/*      */       }
/*      */ 
/*      */       
/* 1048 */       String strMRUnum = actionCommand.substring(4);
/* 1049 */       String strFile = this.m_MRUlist.getFileAt(Integer.valueOf(strMRUnum).intValue());
/*      */       
/* 1051 */       File file = new File(strFile);
/* 1052 */       this.m_strWorkingDIrectory = file.getPath();
/* 1053 */       List<Note> notes = null;
/*      */ 
/*      */       
/*      */       try {
/* 1057 */         notes = 
/* 1058 */           FileUtilities.loadMIDIFile(this.m_Sequencer, file, this.m_CurrentDrumKit, this.m_DrumTrack);
/* 1059 */       } catch (InvalidMidiDataException ex) {
/*      */         
/* 1061 */         ErrorHandler.showError(ex);
/*      */         return;
/* 1063 */       } catch (IOException ioex) {
/*      */         
/* 1065 */         ErrorHandler.showError(ioex);
/*      */         return;
/*      */       } 
/* 1068 */       this.m_CurrentDrumKit.setNotes(notes);
/*      */       
/* 1070 */       this.m_ScrollPane.getHorizontalScrollBar().setValue(0);
/* 1071 */       this.m_ScrollPane.getVerticalScrollBar().setValue(0);
/* 1072 */       this.m_ScrollPane.repaint();
/* 1073 */       this.m_EditorTable.repaint();
/*      */       
/* 1075 */       this.m_DrumTrack.setFileName(file);
/* 1076 */       this.m_DrumTrack.setNew(false);
/* 1077 */       this.m_DrumTrack.setDirty(false);
/* 1078 */       setTitle("BeatBuilder [" + this.m_DrumTrack.getTrackName() + "]");
/* 1079 */       this.m_SpinnerTempo.setValue(Integer.valueOf(this.m_DrumTrack.getTempo()));
/*      */       
/* 1081 */       this.m_ComboTimeSig.setSelectedItem(this.m_DrumTrack.getTimeSignatureAsString());
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void play(String p_strPlayMode) {
/* 1103 */     Sequence sequence = null;
/*      */     
/* 1105 */     this.m_iStartingPosition = this.m_EditorTable.getFirstSelectedColumn();
/* 1106 */     this.m_iEndingPosition = this.m_EditorTable.getLastSelectedColumn();
/* 1107 */     this.m_iPlaybackOffset = 0;
/*      */     
/* 1109 */     if (p_strPlayMode.equals("All") || this.m_EditorTable.isNoneSelected()) {
/*      */       
/* 1111 */       sequence = generateSequence(this.m_DrumTrack.getPPQ());
/*      */     
/*      */     }
/* 1114 */     else if (p_strPlayMode.equals("Selection")) {
/*      */       
/* 1116 */       if (this.m_EditorTable.isRangeSelected()) {
/*      */         
/* 1118 */         this.m_iPlaybackOffset = this.m_iStartingPosition;
/* 1119 */         sequence = generateSequence(this.m_DrumTrack.getPPQ(), 
/* 1120 */             Integer.valueOf(this.m_EditorTable.getFirstSelectedColumn()), 
/* 1121 */             Integer.valueOf(this.m_EditorTable.getLastSelectedColumn()));
/*      */       
/*      */       }
/* 1124 */       else if (this.m_EditorTable.isNoneSelected()) {
/*      */         
/* 1126 */         sequence = generateSequence(this.m_DrumTrack.getPPQ());
/*      */       } else {
/*      */         
/* 1129 */         this.m_iPlaybackOffset = this.m_iStartingPosition;
/* 1130 */         sequence = generateSequence(this.m_DrumTrack.getPPQ(), 
/* 1131 */             Integer.valueOf(this.m_EditorTable.getFirstSelectedColumn()), (Integer)null);
/*      */       }
/*      */     
/*      */     }
/* 1135 */     else if (p_strPlayMode.equals("Start at Selected")) {
/*      */       
/* 1137 */       this.m_iPlaybackOffset = this.m_iStartingPosition;
/* 1138 */       sequence = generateSequence(this.m_DrumTrack.getPPQ(), 
/* 1139 */           Integer.valueOf(this.m_EditorTable.getFirstSelectedColumn()), (Integer)null);
/*      */     }
/*      */     else {
/*      */       
/* 1143 */       sequence = generateSequence(this.m_DrumTrack.getPPQ(), (Integer)null, 
/* 1144 */           Integer.valueOf(this.m_EditorTable.getLastSelectedColumn()));
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*      */     try {
/* 1150 */       this.m_Sequencer.setSequence(sequence);
/* 1151 */       this.m_Sequencer.setTempoInBPM(this.m_iTempo.intValue());
/* 1152 */       this.m_Sequencer.start();
/* 1153 */       startTracker();
/* 1154 */     } catch (InvalidMidiDataException e) {
/*      */       
/* 1156 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Sequence generateSequence(int p_iPPQ) {
/* 1165 */     return generateSequence(p_iPPQ, (Integer)null, (Integer)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private Sequence generateSequence(int p_iPPQ, Integer p_iStart, Integer p_iEnd) {
/* 1172 */     Sequence sequence = null;
/*      */     
/* 1174 */     List<MidiEvent> events = new ArrayList<>();
/*      */     
/* 1176 */     ShortMessage instrumentChange = new ShortMessage();
/* 1177 */     byte[] timesig = new byte[4];
/* 1178 */     timesig[0] = (byte)this.m_DrumTrack.getNotesPerMeasure();
/* 1179 */     Double d = Double.valueOf(Math.sqrt(this.m_DrumTrack.getNoteType().doubleValue()));
/*      */     
/* 1181 */     timesig[1] = (byte)d.intValue();
/*      */     
/* 1183 */     timesig[2] = 24;
/* 1184 */     timesig[3] = 8;
/*      */ 
/*      */     
/*      */     try {
/* 1188 */       MetaMessage msgSg = new MetaMessage(88, timesig, 4);
/* 1189 */       instrumentChange.setMessage(192, 9, 10, 0);
/* 1190 */       MidiEvent instChange = new MidiEvent(instrumentChange, 0L);
/* 1191 */       events.add(0, instChange);
/*      */       
/* 1193 */       MidiEvent timesigE = new MidiEvent(msgSg, 0L);
/* 1194 */       events.add(0, timesigE);
/*      */       
/* 1196 */       int tempoInMPQ = 60000000 / this.m_DrumTrack.getTempo();
/* 1197 */       byte[] data = new byte[3];
/* 1198 */       data[0] = (byte)(tempoInMPQ >> 16 & 0xFF);
/* 1199 */       data[1] = (byte)(tempoInMPQ >> 8 & 0xFF);
/* 1200 */       data[2] = (byte)(tempoInMPQ & 0xFF);
/*      */       
/* 1202 */       MetaMessage msgTempo = new MetaMessage(81, data, 3);
/*      */       
/* 1204 */       MidiEvent tempoE = new MidiEvent(msgTempo, 0L);
/* 1205 */       events.add(0, tempoE);
/*      */     }
/* 1207 */     catch (InvalidMidiDataException e) {
/*      */       
/* 1209 */       e.printStackTrace();
/*      */     } 
/*      */     
/* 1212 */     if (p_iStart == null && p_iEnd == null) {
/*      */       
/* 1214 */       for (Instrument instr : this.m_EditorTableModel.getInstruments())
/*      */       {
/* 1216 */         events.addAll(instr.toMidiEvents(p_iPPQ));
/*      */       }
/* 1218 */     } else if (p_iStart != null && p_iEnd == null) {
/*      */       
/* 1220 */       for (Instrument instr : this.m_EditorTableModel.getInstruments())
/*      */       {
/* 1222 */         events.addAll(instr.toMidiEventsFromStartpoint(p_iPPQ, p_iStart));
/*      */       }
/* 1224 */     } else if (p_iStart == null && p_iEnd != null) {
/*      */       
/* 1226 */       for (Instrument instr : this.m_EditorTableModel.getInstruments())
/*      */       {
/* 1228 */         events.addAll(instr.toMidiEventsToEndpoint(p_iPPQ, p_iEnd.intValue()));
/*      */       }
/*      */     } else {
/*      */       
/* 1232 */       for (Instrument instr : this.m_EditorTableModel.getInstruments())
/*      */       {
/* 1234 */         events.addAll(instr.toMidiEvents(p_iPPQ, p_iStart.intValue(), p_iEnd.intValue()));
/*      */       }
/*      */     } 
/*      */     
/* 1238 */     Collections.sort(events, new MidiEventComparator());
/*      */ 
/*      */     
/*      */     try {
/* 1242 */       sequence = new Sequence(this.m_DrumTrack.getDivType(), this.m_DrumTrack.getPPQ(), 1);
/* 1243 */       Track track = sequence.getTracks()[0];
/* 1244 */       for (MidiEvent event : events)
/*      */       {
/* 1246 */         track.add(event);
/*      */       }
/*      */     }
/* 1249 */     catch (InvalidMidiDataException e) {
/*      */       
/* 1251 */       e.printStackTrace();
/*      */     } 
/* 1253 */     return sequence;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private class MidiEventComparator
/*      */     implements Comparator<MidiEvent>
/*      */   {
/*      */     public int compare(MidiEvent event1, MidiEvent event2) {
/* 1268 */       Long lTick1 = Long.valueOf(event1.getTick());
/* 1269 */       Long lTick2 = Long.valueOf(event2.getTick());
/* 1270 */       return lTick1.compareTo(lTick2);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void itemStateChanged(ItemEvent event) {
/* 1280 */     if (event.getStateChange() == 1) {
/*      */       
/* 1282 */       String item = (String)event.getItem();
/* 1283 */       setActiveDrumKit(item);
/* 1284 */       this.m_AppConfig.put("SelectedKit", item);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Drumkit setActiveDrumKit(String p_strDrumKitName) {
/* 1294 */     Drumkit kit = this.m_DrumKits.get(p_strDrumKitName);
/*      */     
/* 1296 */     if (!p_strDrumKitName.equals(this.m_strActiveDrumkitName)) {
/*      */       
/* 1298 */       Drumkit currentKit = this.m_DrumKits.get(this.m_strActiveDrumkitName);
/* 1299 */       kit.transferNotes(currentKit);
/*      */     } 
/* 1301 */     this.m_strActiveDrumkitName = p_strDrumKitName;
/* 1302 */     this.m_EditorTableModel.setDrumKit(kit);
/* 1303 */     this.m_EditorTable.setDrumKit(kit);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1310 */     repaint();
/* 1311 */     this.m_EditorTable.repaint();
/* 1312 */     return kit;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getActiveDrumKit() {
/* 1319 */     return this.m_strActiveDrumkitName;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowClosing(WindowEvent e) {
/* 1326 */     stopTracker();
/*      */     
/* 1328 */     setDefaultCloseOperation(2);
/*      */     
/* 1330 */     if (this.m_DrumTrack.isDirty())
/*      */     {
/* 1332 */       if (JOptionPane.showConfirmDialog(this, 
/* 1333 */           "You have unsaved changes, are you sure you want to exit?", "Confirm exit.", 
/* 1334 */           0, 2) == 1) {
/*      */         
/* 1336 */         setDefaultCloseOperation(0);
/*      */         
/*      */         return;
/*      */       } 
/*      */     }
/* 1341 */     if (this.m_Sequencer != null) {
/*      */       
/* 1343 */       this.m_Sequencer.stop();
/* 1344 */       this.m_Sequencer.close();
/*      */     } 
/* 1346 */     if (this.m_AppConfig != null) {
/*      */       
/* 1348 */       this.m_MRUlist.toINIfile(this.m_AppConfig);
/* 1349 */       FileUtilities.saveINIfile(this.m_AppConfig);
/*      */     } 
/* 1351 */     System.exit(1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowOpened(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowClosed(WindowEvent e) {
/* 1366 */     stopTracker();
/* 1367 */     if (this.m_Sequencer != null) {
/*      */       
/* 1369 */       this.m_Sequencer.stop();
/* 1370 */       this.m_Sequencer.close();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowIconified(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowDeiconified(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowActivated(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void windowDeactivated(WindowEvent e) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void updateMRUList(File p_File) {
/* 1406 */     this.m_MRUlist.addItem(p_File);
/* 1407 */     setMRUItems();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void setMRUItems() {
/* 1420 */     for (int mruItem = 0; mruItem < 5; mruItem++) {
/*      */       
/* 1422 */       String strFile = this.m_MRUlist.getFileNameAt(mruItem);
/* 1423 */       if (strFile != null)
/*      */       {
/* 1425 */         switch (mruItem) {
/*      */           
/*      */           case 0:
/* 1428 */             this.mntmMRU1.setText(strFile);
/* 1429 */             this.mntmMRU1.setVisible(true);
/*      */             break;
/*      */           case 1:
/* 1432 */             this.mntmMRU2.setText(strFile);
/* 1433 */             this.mntmMRU2.setVisible(true);
/*      */             break;
/*      */           case 2:
/* 1436 */             this.mntmMRU3.setText(strFile);
/* 1437 */             this.mntmMRU3.setVisible(true);
/*      */             break;
/*      */           case 3:
/* 1440 */             this.mntmMRU4.setText(strFile);
/* 1441 */             this.mntmMRU4.setVisible(true);
/*      */             break;
/*      */           case 4:
/* 1444 */             this.mntmMRU5.setText(strFile);
/* 1445 */             this.mntmMRU5.setVisible(true);
/*      */             break;
/*      */         } 
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void initializeMidi2() {
/*      */     try {
/* 1458 */       this.m_Sequencer = MidiSystem.getSequencer(false);
/* 1459 */       Synthesizer synth = MidiSystem.getSynthesizer();
/* 1460 */       synth.open();
/* 1461 */       synth.unloadAllInstruments(synth.getDefaultSoundbank());
/* 1462 */       URL file = BeatBuilder.class.getResource("Standard.sf2");
/* 1463 */       Soundbank sb = MidiSystem.getSoundbank(file);
/*      */       
/* 1465 */       boolean bLoaded = synth.loadAllInstruments(sb); byte b; int i; MidiChannel[] arrayOfMidiChannel;
/* 1466 */       for (i = (arrayOfMidiChannel = synth.getChannels()).length, b = 0; b < i; ) { MidiChannel channel = arrayOfMidiChannel[b];
/*      */         
/* 1468 */         channel.programChange(0, 0);
/* 1469 */         channel.controlChange(91, 0);
/* 1470 */         channel.controlChange(93, 0);
/*      */         b++; }
/*      */       
/* 1473 */       this.m_Sequencer.open();
/* 1474 */       this.m_Sequencer.getTransmitter().setReceiver(synth.getReceiver());
/* 1475 */       this.m_Sequencer.addMetaEventListener(this);
/*      */     }
/* 1477 */     catch (MidiUnavailableException|InvalidMidiDataException|IOException e) {
/*      */       
/* 1479 */       e.printStackTrace();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void startTracker() {
/* 1488 */     if (this.m_Tracker == null) {
/*      */       
/* 1490 */       this.m_Tracker = new Tracker(null);
/* 1491 */       this.m_Tracker.start();
/*      */ 
/*      */     
/*      */     }
/* 1495 */     else if (this.m_Tracker.isInterrupted() || !this.m_Tracker.isAlive()) {
/*      */       
/* 1497 */       this.m_Tracker.start();
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void stopTracker() {
/* 1503 */     if (this.m_Tracker != null) {
/*      */       
/* 1505 */       this.m_Tracker.kill();
/*      */       
/*      */       try {
/* 1508 */         this.m_Tracker.join();
/* 1509 */       } catch (InterruptedException interruptedException) {}
/*      */ 
/*      */ 
/*      */       
/* 1513 */       this.m_Tracker = null;
/*      */     } 
/*      */   }
/*      */   
/*      */   private class Tracker extends Thread { private int m_iInterval;
/*      */     
/*      */     private Tracker() {
/* 1520 */       this.m_iInterval = 100;
/*      */     }
/*      */ 
/*      */     
/*      */     public void run() {
/* 1525 */       while (BeatBuilder.this.m_Sequencer.isRunning()) {
/*      */         
/* 1527 */         Long lTick = Long.valueOf(BeatBuilder.this.m_Sequencer.getTickPosition());
/* 1528 */         Long lColumn = new Long(lTick.longValue() / BeatBuilder.this.m_DrumTrack.getPPQ() * 16L);
/* 1529 */         BeatBuilder.this.m_EditorTable.setSelectedColumn(lColumn.intValue() + BeatBuilder.this.m_iPlaybackOffset);
/*      */         
/*      */         try {
/* 1532 */           sleep(Math.max(240 - BeatBuilder.this.m_DrumTrack.getTempo(), 50));
/* 1533 */         } catch (InterruptedException interruptedException) {}
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setInterval(int p_iTempo) {
/* 1543 */       int iBeatsPerSecond = p_iTempo / 4;
/*      */       
/* 1545 */       int iSecondsPerBeat = 60 - p_iTempo / 4;
/*      */     }
/*      */ 
/*      */     
/*      */     public void kill() {
/* 1550 */       interrupt();
/*      */     } }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void meta(MetaMessage event) {
/* 1559 */     if (event.getType() == 47) {
/*      */       
/* 1561 */       stopTracker();
/* 1562 */       this.m_EditorTable.setSelectedColumnRange(this.m_iStartingPosition, this.m_iEndingPosition);
/* 1563 */       repaint();
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\BeatBuilder.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */