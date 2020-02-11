/*     */ package view.editor.editorTable;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JMenuItem;
/*     */ import javax.swing.JPopupMenu;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JSeparator;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import javax.swing.ToolTipManager;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import javax.swing.table.DefaultTableCellRenderer;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableModel;
/*     */ import model.DrumTrack;
/*     */ import model.Drumkit;
/*     */ import model.EditorTableModel;
/*     */ import model.Instrument;
/*     */ import model.Note;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditorTable
/*     */   extends JTable
/*     */   implements MouseListener, KeyListener
/*     */ {
/*     */   public class CustomTableHeader
/*     */     extends JTableHeader
/*     */   {
/*     */     public CustomTableHeader(JTable table) {
/* 249 */       setColumnModel(table.getColumnModel());
/* 250 */       table.getColumnModel().getSelectionModel()
/* 251 */         .addListSelectionListener(new ListSelectionListener()
/*     */           {
/*     */             
/*     */             public void valueChanged(ListSelectionEvent e)
/*     */             {
/* 256 */               EditorTable.CustomTableHeader.this.repaint();
/*     */             }
/*     */           });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void columnSelectionChanged(ListSelectionEvent e) {
/* 266 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   private class RowHeaderMouseListener
/*     */     implements MouseListener
/*     */   {
/* 273 */     JPopupMenu popup = new JPopupMenu();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public RowHeaderMouseListener() {
/* 279 */       JMenuItem menuSetVolume = new JMenuItem("Set Note Volume...");
/* 280 */       menuSetVolume.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent p_Event)
/*     */             {
/* 284 */               if ((EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgSet == null)
/*     */               {
/* 286 */                 (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgSet = new SetNoteVolume();
/*     */               }
/* 288 */               Integer iVolume = (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgSet.showDialog();
/* 289 */               if (iVolume != null)
/*     */               {
/* 291 */                 EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this).setNoteVolumes(iVolume);
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 298 */       this.popup.add(menuSetVolume);
/*     */       
/* 300 */       JMenuItem menuItemAdjVol = new JMenuItem("Adjust Note Volume...");
/* 301 */       menuItemAdjVol.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent p_Event)
/*     */             {
/* 305 */               if ((EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgAdjust == null)
/*     */               {
/* 307 */                 (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgAdjust = new AdjustNoteVolume();
/*     */               }
/* 309 */               Integer iAdjustment = (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgAdjust.showDialog();
/* 310 */               if (iAdjustment != null)
/*     */               {
/* 312 */                 EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this).adjustNoteVolumes(iAdjustment);
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 318 */       this.popup.add(menuItemAdjVol);
/*     */       
/* 320 */       JMenuItem menuItemA = new JMenuItem("Randomize Note Volumes...");
/* 321 */       menuItemA.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent p_Event)
/*     */             {
/* 325 */               if ((EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgRandomize == null)
/*     */               {
/* 327 */                 (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgRandomize = new RandomizeNoteVolume();
/*     */               }
/* 329 */               Map<String, Integer> mRange = (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgRandomize.showDialog();
/* 330 */               if (mRange != null)
/*     */               {
/* 332 */                 EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this).randomizeNoteVolumes(mRange);
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 340 */       this.popup.add(menuItemA);
/*     */       
/* 342 */       JSeparator separator_1 = new JSeparator();
/* 343 */       this.popup.add(separator_1);
/*     */       
/* 345 */       JMenuItem menuItemT = new JMenuItem("Transpose Notes...");
/* 346 */       menuItemT.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent p_Event)
/*     */             {
/* 350 */               if ((EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgTransposeNotes == null)
/*     */               {
/* 352 */                 (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgTransposeNotes = new Transpose();
/*     */               }
/* 354 */               Integer iInstIndex = (EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).dlgTransposeNotes.showDialog((EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this)).m_DrumkitInstruments);
/* 355 */               if (iInstIndex != null)
/*     */               {
/* 357 */                 EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this).transposeNotes(iInstIndex.intValue());
/*     */               }
/*     */             }
/*     */           });
/*     */ 
/*     */       
/* 363 */       this.popup.add(menuItemT);
/* 364 */       this.popup.add(new JSeparator());
/*     */       
/* 366 */       JMenuItem menuItem3 = new JMenuItem("Remove All Notes");
/* 367 */       menuItem3.addActionListener(new ActionListener()
/*     */           {
/*     */             public void actionPerformed(ActionEvent p_Event)
/*     */             {
/* 371 */               EditorTable.RowHeaderMouseListener.access$0(EditorTable.RowHeaderMouseListener.this).deleteNotes();
/*     */             }
/*     */           });
/*     */ 
/*     */ 
/*     */       
/* 377 */       this.popup.add(menuItem3);
/*     */     }
/*     */ 
/*     */     
/*     */     public void mouseEntered(MouseEvent me) {}
/*     */     
/*     */     public void mouseExited(MouseEvent me) {}
/*     */     
/*     */     public void mousePressed(MouseEvent me) {}
/*     */     
/*     */     public void mouseReleased(MouseEvent me) {}
/*     */     
/*     */     public void mouseClicked(MouseEvent e) {
/* 390 */       this.popup.show(e.getComponent(), e.getX(), e.getY()); }
/*     */   }
/*     */   private DefaultTableCellRenderer m_CellRender = null; private List<Instrument> m_DrumkitInstruments = null; private Integer m_NewNoteVeloticy = new Integer(70); private EditorTableModel m_DataModel = null; private JList<String> m_RowHeader = null; private DrumTrack m_DrumTrack = null; private EditorTableUI m_EditorTableUI = null; private AdjustNoteVolume dlgAdjust = null; private SetNoteVolume dlgSet = null; private RandomizeNoteVolume dlgRandomize = null; private Transpose dlgTransposeNotes = null; private JPopupMenu popup = null; private JScrollPane m_ScrollPane = null; private int m_iColumnWidth = 10; private static int TABLE_ROW_HEIGHT = 20; public EditorTable(EditorTableModel p_Model, DrumTrack p_DrumTrack) { this.m_DataModel = p_Model; this.m_DrumTrack = p_DrumTrack; this.m_DrumkitInstruments = p_Model.getInstruments(); setRowHeight(TABLE_ROW_HEIGHT); setCellSelectionEnabled(false); setRowSelectionAllowed(false); setColumnSelectionAllowed(true); setSelectionMode(1); setGridColor(new Color(241, 241, 241)); setBackground(UIManager.getColor("Panel.background")); setModel((TableModel)this.m_DataModel); this.m_CellRender = new EditorTableCellRenderer(this.m_DataModel, this.m_DrumTrack); setPreferredScrollableViewportSize(getPreferredSize()); setColumnWidths(Integer.valueOf(this.m_iColumnWidth)); setAutoResizeMode(0); getTableHeader().setReorderingAllowed(false); getTableHeader().setResizingAllowed(false); addMouseListener(this); addKeyListener(this); this.m_RowHeader = new JList(p_Model.getInstrumentNames().toArray()); this.m_RowHeader.setFixedCellHeight(getRowHeight()); this.m_RowHeader.setBackground(UIManager.getColor("Panel.background")); this.m_RowHeader.setCellRenderer(new RowHeaderRenderer(this)); this.m_RowHeader.addMouseListener(new RowHeaderMouseListener()); this.m_EditorTableUI = new EditorTableUI(this, p_Model); setUI(this.m_EditorTableUI); this.popup = new JPopupMenu(); JMenuItem menuSetVolume = new JMenuItem("Set Note Volume..."); menuSetVolume.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent p_Event) { if (EditorTable.this.dlgSet == null)
/*     */               EditorTable.this.dlgSet = new SetNoteVolume();  Integer iVolume = EditorTable.this.dlgSet.showDialog(EditorTable.this.getSelectedNoteVolume()); if (iVolume != null)
/*     */               EditorTable.this.setNoteVolume(iVolume);  }
/*     */         }); this.popup.add(menuSetVolume); JMenuItem menuItemAdjVol = new JMenuItem("Adjust Note Volume..."); menuItemAdjVol.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent p_Event) { if (EditorTable.this.dlgAdjust == null)
/*     */               EditorTable.this.dlgAdjust = new AdjustNoteVolume();  Integer iAdjustment = EditorTable.this.dlgAdjust.showDialog(); if (iAdjustment != null)
/*     */               EditorTable.this.adjustNoteVolume(iAdjustment);  }
/*     */         }); this.popup.add(menuItemAdjVol); ToolTipManager.sharedInstance().unregisterComponent(this); ToolTipManager.sharedInstance().unregisterComponent(getTableHeader()); } public void setScrollPane(JScrollPane p_scrollPane) { this.m_EditorTableUI.setScrollPane(p_scrollPane); this.m_ScrollPane = p_scrollPane; } public JList<String> getRowHeader() { return this.m_RowHeader; } public TableCellRenderer getCellRenderer(int p_iRow, int p_iColumn) { return this.m_CellRender; } public void setNewNoteVelocity(Integer p_iVelocity) { this.m_NewNoteVeloticy = p_iVelocity; } public void mouseClicked(MouseEvent me) { if (me.getButton() == 3) {
/*     */       int iRow = getSelectionModel().getLeadSelectionIndex(); int iColumn = getColumnModel().getSelectionModel().getLeadSelectionIndex(); Instrument instr = this.m_DrumkitInstruments.get(iRow); if (instr.hasNote(iColumn))
/*     */         this.popup.show(this, me.getX(), me.getY()); 
/*     */     }  if (me.getClickCount() == 2) {
/*     */       this.m_DrumTrack.setDirty(true);
/*     */       JTable table = (JTable)me.getSource();
/*     */       Point pnt = me.getPoint();
/*     */       int iRow = table.rowAtPoint(pnt);
/*     */       int iColumn = table.columnAtPoint(pnt);
/*     */       Instrument instr = this.m_DrumkitInstruments.get(iRow);
/*     */       if (instr.hasNote(iColumn)) {
/*     */         instr.removeNote(iColumn);
/*     */       } else if (iColumn > 0 && instr.hasNote(iColumn - 1)) {
/*     */         instr.removeNote(iColumn - 1);
/*     */       } else {
/*     */         instr.addNote(iColumn, this.m_NewNoteVeloticy.intValue());
/*     */       } 
/*     */       this.m_DataModel.fireTableCellUpdated(iRow, iColumn);
/*     */       this.m_DataModel.fireTableCellUpdated(iRow, iColumn + 1);
/*     */       if (iColumn > 0)
/*     */         this.m_DataModel.fireTableCellUpdated(iRow, iColumn - 1); 
/*     */     }  } public void mouseEntered(MouseEvent me) {} public void mouseExited(MouseEvent me) {} public void mousePressed(MouseEvent me) {} public void mouseReleased(MouseEvent me) {} private class RowHeaderRenderer extends JLabel implements ListCellRenderer
/*     */   {
/* 423 */     RowHeaderRenderer(JTable table) { JTableHeader header = table.getTableHeader();
/* 424 */       setOpaque(true);
/* 425 */       setBorder(UIManager.getBorder("TableHeader.cellBorder"));
/* 426 */       setHorizontalAlignment(2);
/* 427 */       setForeground(header.getForeground());
/* 428 */       setBackground(Color.LIGHT_GRAY);
/* 429 */       setFont(header.getFont()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
/* 438 */       if (isSelected) {
/*     */         
/* 440 */         setBackground(Color.BLUE);
/*     */       } else {
/*     */         
/* 443 */         setBackground(UIManager.getColor("Panel.background"));
/*     */       } 
/*     */       
/* 446 */       setText((value == null) ? "" : value.toString());
/* 447 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void adjustNoteVolumes(Integer p_iAmount) {
/* 456 */     String instrument = this.m_RowHeader.getSelectedValue();
/* 457 */     Instrument instr = this.m_DataModel.getInstrument(instrument);
/* 458 */     instr.modifyVelocity(p_iAmount.intValue());
/* 459 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void randomizeNoteVolumes(Map<String, Integer> p_mRange) {
/* 466 */     String instrument = this.m_RowHeader.getSelectedValue();
/* 467 */     Instrument instr = this.m_DataModel.getInstrument(instrument);
/* 468 */     instr.randomizeNoteVolumes(p_mRange);
/* 469 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNoteVolumes(Integer p_iVolume) {
/* 476 */     String instrument = this.m_RowHeader.getSelectedValue();
/* 477 */     Instrument instr = this.m_DataModel.getInstrument(instrument);
/* 478 */     instr.setVelocity(p_iVolume.intValue());
/* 479 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void setNoteVolume(Integer p_iVolume) {
/* 486 */     Note note = getSelectedNote();
/* 487 */     note.setVelocity(p_iVolume.intValue());
/* 488 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void adjustNoteVolume(Integer p_iAmount) {
/* 495 */     Note note = getSelectedNote();
/* 496 */     note.modifyVelocity(p_iAmount.intValue());
/* 497 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void deleteNotes() {
/* 504 */     String instrument = this.m_RowHeader.getSelectedValue();
/* 505 */     Instrument instr = this.m_DataModel.getInstrument(instrument);
/* 506 */     instr.clearNotes();
/* 507 */     resetColumnSelection();
/* 508 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFirstSelectedColumn() {
/* 515 */     int[] columns = getSelectedColumns();
/* 516 */     if (columns != null && columns.length > 0)
/*     */     {
/* 518 */       return columns[0];
/*     */     }
/* 520 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLastSelectedColumn() {
/* 527 */     int[] columns = getSelectedColumns();
/* 528 */     if (columns != null && columns.length > 0)
/*     */     {
/* 530 */       return columns[columns.length - 1];
/*     */     }
/* 532 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRangeSelected() {
/* 539 */     int[] columns = getSelectedColumns();
/* 540 */     if (columns == null || columns.length < 2)
/*     */     {
/* 542 */       return false;
/*     */     }
/* 544 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isNoneSelected() {
/* 551 */     int[] columns = getSelectedColumns();
/* 552 */     if (columns == null || columns.length < 1)
/*     */     {
/* 554 */       return true;
/*     */     }
/* 556 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void insertBars(int p_iBars) {
/* 563 */     int iStart = getFirstSelectedColumn();
/* 564 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 566 */       inst.insertBars(iStart, p_iBars);
/*     */     }
/* 568 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paste() {
/* 575 */     int iStart = getFirstSelectedColumn();
/* 576 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 578 */       inst.paste(iStart, this.m_DrumTrack.getPPQ());
/*     */     }
/* 580 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pasteInsert() {
/* 587 */     int iStart = getFirstSelectedColumn();
/* 588 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 590 */       inst.pasteInsert(iStart, this.m_DrumTrack.getPPQ());
/*     */     }
/* 592 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deletebars() {
/* 599 */     int iStart = getFirstSelectedColumn();
/* 600 */     int iEnd = getLastSelectedColumn();
/* 601 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 603 */       inst.deletebars(iStart, iEnd, this.m_DrumTrack.getPPQ());
/*     */     }
/* 605 */     resetColumnSelection();
/* 606 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void deletenotes() {
/* 613 */     int iStart = getFirstSelectedColumn();
/* 614 */     int iEnd = getLastSelectedColumn();
/* 615 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 617 */       inst.deletenotes(iStart, iEnd);
/*     */     }
/* 619 */     resetColumnSelection();
/* 620 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copy() {
/* 627 */     int iStart = getFirstSelectedColumn();
/* 628 */     int iEnd = getLastSelectedColumn();
/* 629 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 631 */       inst.copy(iStart, iEnd, this.m_DrumTrack.getPPQ());
/*     */     }
/* 633 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cutnotes() {
/* 640 */     int iStart = getFirstSelectedColumn();
/* 641 */     int iEnd = getLastSelectedColumn();
/* 642 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 644 */       inst.cutnotes(iStart, iEnd, this.m_DrumTrack.getPPQ());
/*     */     }
/* 646 */     resetColumnSelection();
/* 647 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cutbars() {
/* 654 */     int iStart = getFirstSelectedColumn();
/* 655 */     int iEnd = getLastSelectedColumn();
/* 656 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 658 */       inst.cutbars(iStart, iEnd, this.m_DrumTrack.getPPQ());
/*     */     }
/* 660 */     resetColumnSelection();
/* 661 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void adjustAllNoteVolumes(int p_iAmount) {
/* 668 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 670 */       inst.modifyVelocity(p_iAmount);
/*     */     }
/* 672 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void randomizeNotes(int p_iFactor) {
/* 679 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 681 */       inst.randomizeNotes(p_iFactor, this.m_DrumTrack.getPPQ());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void quantizeNotes() {
/* 689 */     for (Instrument inst : this.m_DataModel.getInstruments())
/*     */     {
/* 691 */       inst.quantizeNotes();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void transposeNotes(int p_iToIndex) {
/* 698 */     String instrument = this.m_RowHeader.getSelectedValue();
/* 699 */     Instrument fromInst = this.m_DataModel.getInstrument(instrument);
/* 700 */     Instrument toInst = this.m_DataModel.getInstruments().get(p_iToIndex);
/* 701 */     for (Note note : fromInst.getNotes().values()) {
/*     */       
/* 703 */       if (!toInst.hasNote(note.getBeat())) {
/*     */         
/* 705 */         Note newNote = new Note(toInst, note.getBeat(), note.getTick(), note.getVelocity().intValue());
/* 706 */         toInst.addNote(newNote);
/*     */       } 
/*     */     } 
/*     */     
/* 710 */     fromInst.clearNotes();
/* 711 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearNotes() {
/* 717 */     this.m_DataModel.clearNotes();
/* 718 */     repaint();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColumnWidths(Integer p_iWidth) {
/* 725 */     this.m_iColumnWidth = p_iWidth.intValue();
/*     */     
/* 727 */     for (int i = 0; i < getColumnModel().getColumnCount(); i++) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 736 */       getColumnModel().getColumn(i).setMinWidth(p_iWidth.intValue() - 1);
/* 737 */       getColumnModel().getColumn(i).setPreferredWidth(p_iWidth.intValue() - 1);
/* 738 */       getColumnModel().getColumn(i).setMaxWidth(p_iWidth.intValue() - 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void resetColumnSelection() {
/* 750 */     int iColumn = getColumnModel().getSelectionModel().getAnchorSelectionIndex();
/*     */ 
/*     */     
/* 753 */     getColumnModel().getSelectionModel().setSelectionInterval(iColumn, iColumn);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSelectedColumn(int iColumn) {
/* 759 */     int iFirstColumn = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 771 */     getColumnModel().getSelectionModel().setSelectionInterval(iColumn, iColumn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSelectedColumnRange(int p_iStart, int p_iEnd) {
/* 776 */     getColumnModel().getSelectionModel().setSelectionInterval(p_iStart, p_iEnd);
/* 777 */     int iFirstColumn = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Note getSelectedNote() {
/* 793 */     int iRow = getSelectionModel().getLeadSelectionIndex();
/* 794 */     int iColumn = getColumnModel().getSelectionModel().getLeadSelectionIndex();
/* 795 */     Instrument instr = this.m_DrumkitInstruments.get(iRow);
/* 796 */     Note note = instr.getNote(iColumn);
/* 797 */     return note;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getSelectedNoteVolume() {
/* 804 */     Note note = getSelectedNote();
/* 805 */     if (note != null)
/*     */     {
/* 807 */       return note.getVelocity().intValue();
/*     */     }
/* 809 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getCellWithNoteRect(int row, int column) {
/* 816 */     int iWidth = this.m_iColumnWidth - 1;
/*     */     
/* 818 */     if (column >= getColumnCount())
/*     */     {
/* 820 */       return new Rectangle(getWidth(), row * TABLE_ROW_HEIGHT, iWidth * 2, TABLE_ROW_HEIGHT);
/*     */     }
/*     */     
/* 823 */     return new Rectangle(iWidth * column, row * TABLE_ROW_HEIGHT, iWidth * 2, TABLE_ROW_HEIGHT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getCellRect(int row, int column, boolean includeSpacing) {
/* 831 */     int iWidth = this.m_iColumnWidth - 1;
/*     */     
/* 833 */     if (column >= getColumnCount())
/*     */     {
/* 835 */       return new Rectangle(getWidth(), row * TABLE_ROW_HEIGHT, iWidth, TABLE_ROW_HEIGHT);
/*     */     }
/*     */     
/* 838 */     return new Rectangle(iWidth * column, row * TABLE_ROW_HEIGHT, iWidth, TABLE_ROW_HEIGHT);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDrumKit(Drumkit p_kit) {
/* 846 */     this.m_DrumkitInstruments = this.m_DataModel.getInstruments();
/* 847 */     this.m_RowHeader = new JList(this.m_DrumkitInstruments.toArray());
/*     */     
/* 849 */     this.m_DataModel.fireTableDataChanged();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyPressed(KeyEvent e) {
/* 857 */     if (e.getKeyCode() == 32 || e.getKeyCode() == 10) {
/*     */       
/* 859 */       this.m_DrumTrack.setDirty(true);
/* 860 */       int iRow = getSelectionModel().getLeadSelectionIndex();
/* 861 */       int iColumn = getColumnModel().getSelectionModel().getLeadSelectionIndex();
/*     */       
/* 863 */       Instrument instr = this.m_DrumkitInstruments.get(iRow);
/* 864 */       if (instr.hasNote(iColumn)) {
/*     */         
/* 866 */         instr.removeNote(iColumn);
/* 867 */       } else if (iColumn > 0 && instr.hasNote(iColumn - 1)) {
/*     */         
/* 869 */         instr.removeNote(iColumn - 1);
/*     */       }
/*     */       else {
/*     */         
/* 873 */         instr.addNote(iColumn, this.m_NewNoteVeloticy.intValue());
/*     */       } 
/* 875 */       this.m_DataModel.fireTableCellUpdated(iRow, iColumn);
/* 876 */       this.m_DataModel.fireTableCellUpdated(iRow, iColumn + 1);
/* 877 */       if (iColumn > 0)
/*     */       {
/* 879 */         this.m_DataModel.fireTableCellUpdated(iRow, iColumn - 1);
/*     */       }
/* 881 */       e.consume();
/*     */     } 
/* 883 */     if (e.getKeyCode() == 525) {
/*     */       
/* 885 */       int iRow = getSelectionModel().getLeadSelectionIndex();
/* 886 */       int iColumn = getColumnModel().getSelectionModel().getLeadSelectionIndex();
/* 887 */       Instrument instr = this.m_DrumkitInstruments.get(iRow);
/* 888 */       if (instr.hasNote(iColumn))
/*     */       {
/* 890 */         this.popup.show(this, iColumn * 9 + 14, iRow * TABLE_ROW_HEIGHT);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyReleased(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void keyTyped(KeyEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getSelectedEventCount() {
/* 917 */     Long lCount = Long.valueOf(0L);
/* 918 */     int iStart = getFirstSelectedColumn();
/* 919 */     int iEnd = getLastSelectedColumn();
/* 920 */     for (Instrument instr : this.m_DrumkitInstruments) {
/*     */       
/* 922 */       for (int i = iStart; i <= iEnd; i++) {
/*     */         
/* 924 */         if (instr.hasNote(i))
/*     */         {
/* 926 */           lCount = Long.valueOf(lCount.longValue() + 1L);
/*     */         }
/*     */       } 
/*     */     } 
/* 930 */     return lCount.longValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\EditorTable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */