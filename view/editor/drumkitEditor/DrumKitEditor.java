/*     */ package view.editor.drumkitEditor;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Insets;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.EventListener;
/*     */ import java.util.EventObject;
/*     */ import java.util.Map;
/*     */ import java.util.Vector;
/*     */ import javax.swing.DefaultCellEditor;
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JFormattedTextField;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ import javax.swing.event.CellEditorListener;
/*     */ import javax.swing.table.DefaultTableCellRenderer;
/*     */ import javax.swing.table.TableCellEditor;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableModel;
/*     */ import javax.swing.text.NumberFormatter;
/*     */ import model.DrumKitEditorModel;
/*     */ import model.Drumkit;
/*     */ import model.Instrument;
/*     */ import tools.FileUtilities;
/*     */ import view.editor.BeatBuilder;
/*     */ 
/*     */ public class DrumKitEditor
/*     */   extends JDialog
/*     */   implements ActionListener
/*     */ {
/*  46 */   private final JPanel contentPanel = new JPanel();
/*     */   private JTable table;
/*  48 */   private String[] KitNames = null;
/*  49 */   Map<String, Drumkit> drumKits = null;
/*  50 */   private DrumKitEditorModel editorModel = null;
/*  51 */   private String activeKit = null;
/*  52 */   private BeatBuilder m_ParentFrame = null;
/*  53 */   JComboBox comboKits = null;
/*  54 */   DefaultComboBoxModel comboKitsModel = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DrumKitEditor(BeatBuilder p_Frame, Map<String, Drumkit> p_Kits) {
/*  63 */     super((Frame)p_Frame);
/*  64 */     setResizable(false);
/*  65 */     setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
/*  66 */     setModal(true);
/*  67 */     setDefaultCloseOperation(2);
/*  68 */     this.m_ParentFrame = p_Frame;
/*  69 */     setTitle("Edit Drum Kits");
/*  70 */     this.drumKits = p_Kits;
/*  71 */     this.editorModel = new DrumKitEditorModel(p_Kits);
/*     */     
/*  73 */     this.KitNames = new String[p_Kits.size()];
/*  74 */     this.KitNames = (String[])p_Kits.keySet().toArray((Object[])this.KitNames);
/*  75 */     this.activeKit = this.KitNames[0];
/*  76 */     this.editorModel.setActiveKit(this.activeKit);
/*     */     
/*  78 */     setBounds(100, 100, 480, 500);
/*  79 */     getContentPane().setLayout(new BorderLayout());
/*  80 */     this.contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
/*  81 */     getContentPane().add(this.contentPanel, "Center");
/*  82 */     GridBagLayout gbl_contentPanel = new GridBagLayout();
/*  83 */     gbl_contentPanel.columnWidths = new int[5];
/*  84 */     gbl_contentPanel.rowHeights = new int[4];
/*  85 */     gbl_contentPanel.columnWeights = new double[] { 0.0D, 1.0D, 0.0D, 0.0D, Double.MIN_VALUE };
/*  86 */     gbl_contentPanel.rowWeights = new double[] { 0.0D, 1.0D, 0.0D, Double.MIN_VALUE };
/*  87 */     this.contentPanel.setLayout(gbl_contentPanel);
/*     */     
/*  89 */     this.comboKitsModel = new DefaultComboBoxModel<>(this.KitNames);
/*  90 */     this.comboKits = new JComboBox(this.comboKitsModel);
/*  91 */     this.comboKits.setMaximumRowCount(12);
/*  92 */     this.comboKits.addActionListener(this);
/*  93 */     GridBagConstraints gbc_comboBox = new GridBagConstraints();
/*  94 */     gbc_comboBox.gridwidth = 2;
/*  95 */     gbc_comboBox.insets = new Insets(0, 0, 5, 5);
/*  96 */     gbc_comboBox.fill = 2;
/*  97 */     gbc_comboBox.gridx = 0;
/*  98 */     gbc_comboBox.gridy = 0;
/*  99 */     this.contentPanel.add(this.comboKits, gbc_comboBox);
/*     */ 
/*     */     
/* 102 */     JButton btnNewButton = new JButton("New");
/* 103 */     btnNewButton.setActionCommand("new");
/* 104 */     btnNewButton.addActionListener(this);
/* 105 */     GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
/* 106 */     gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
/* 107 */     gbc_btnNewButton.gridx = 2;
/* 108 */     gbc_btnNewButton.gridy = 0;
/* 109 */     this.contentPanel.add(btnNewButton, gbc_btnNewButton);
/*     */ 
/*     */     
/* 112 */     JButton btnNewButton_1 = new JButton("Delete");
/* 113 */     GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
/* 114 */     gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
/* 115 */     gbc_btnNewButton_1.gridx = 3;
/* 116 */     gbc_btnNewButton_1.gridy = 0;
/* 117 */     this.contentPanel.add(btnNewButton_1, gbc_btnNewButton_1);
/* 118 */     btnNewButton_1.addActionListener(new ActionListener()
/*     */         {
/*     */           
/*     */           public void actionPerformed(ActionEvent arg0)
/*     */           {
/* 123 */             int iRet = JOptionPane.showConfirmDialog((Component)DrumKitEditor.this.m_ParentFrame, "Confirm", 
/* 124 */                 "Delete drum kit?", 0);
/* 125 */             if (iRet == 0) {
/*     */               
/* 127 */               System.out.println(DrumKitEditor.this.drumKits.size());
/* 128 */               DrumKitEditor.this.drumKits.remove(DrumKitEditor.this.activeKit);
/* 129 */               DrumKitEditor.this.KitNames = new String[DrumKitEditor.this.drumKits.size()];
/* 130 */               DrumKitEditor.this.KitNames = (String[])DrumKitEditor.this.drumKits.keySet().toArray((Object[])DrumKitEditor.this.KitNames);
/* 131 */               DrumKitEditor.this.activeKit = DrumKitEditor.this.KitNames[0];
/* 132 */               DrumKitEditor.this.editorModel.setActiveKit(DrumKitEditor.this.activeKit);
/*     */               
/* 134 */               DrumKitEditor.this.comboKitsModel = new DefaultComboBoxModel<>(DrumKitEditor.this.KitNames);
/* 135 */               DrumKitEditor.this.comboKits.setModel(DrumKitEditor.this.comboKitsModel);
/*     */               
/* 137 */               DrumKitEditor.this.comboKits.revalidate();
/* 138 */               DrumKitEditor.this.comboKits.repaint();
/* 139 */               DrumKitEditor.this.table.repaint();
/*     */             } 
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.table = new JTable();
/* 149 */     this.table.setModel((TableModel)this.editorModel);
/* 150 */     this.table.setAutoResizeMode(0);
/*     */     
/* 152 */     this.table.getTableHeader().setReorderingAllowed(false);
/* 153 */     this.table.getTableHeader().setResizingAllowed(false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 160 */     GridBagConstraints gbc_table = new GridBagConstraints();
/* 161 */     gbc_table.insets = new Insets(0, 0, 5, 5);
/* 162 */     gbc_table.gridwidth = 4;
/* 163 */     gbc_table.fill = 1;
/* 164 */     gbc_table.gridx = 0;
/* 165 */     gbc_table.gridy = 1;
/* 166 */     this.contentPanel.add(new JScrollPane(this.table), gbc_table);
/*     */     
/* 168 */     TableColumn colName = this.table.getColumnModel().getColumn(0);
/* 169 */     colName.setHeaderValue("Name");
/* 170 */     colName.setWidth(150);
/* 171 */     colName.setPreferredWidth(150);
/*     */ 
/*     */     
/* 174 */     TableColumn colNote = this.table.getColumnModel().getColumn(1);
/* 175 */     colNote.setHeaderValue("MIDI Inst");
/*     */ 
/*     */     
/* 178 */     TableColumn colColor = this.table.getColumnModel().getColumn(2);
/* 179 */     colColor.setHeaderValue("Color");
/*     */     
/* 181 */     TableColumn colRemove = this.table.getColumnModel().getColumn(3);
/* 182 */     colRemove.setWidth(90);
/* 183 */     colRemove.setPreferredWidth(90);
/*     */     
/* 185 */     colRemove.setMaxWidth(90);
/*     */     
/* 187 */     JButton btnNewButton_2 = new JButton("Add Instrument");
/* 188 */     GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
/* 189 */     btnNewButton_2.addActionListener(new ActionListener()
/*     */         {
/*     */           public void actionPerformed(ActionEvent ev)
/*     */           {
/* 193 */             Drumkit kit = DrumKitEditor.this.editorModel.getActiveKit();
/* 194 */             Instrument instr = new Instrument("instrument " + 
/* 195 */                 kit.getInstruments().size() + '\001', 0, "blue");
/* 196 */             kit.addInstrument(instr);
/* 197 */             DrumKitEditor.this.table.repaint();
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 202 */     gbc_btnNewButton_2.gridwidth = 3;
/* 203 */     gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
/* 204 */     gbc_btnNewButton_2.gridx = 1;
/* 205 */     gbc_btnNewButton_2.gridy = 2;
/* 206 */     this.contentPanel.add(btnNewButton_2, gbc_btnNewButton_2);
/*     */ 
/*     */     
/* 209 */     colNote.setWidth(60);
/* 210 */     colNote.setPreferredWidth(60);
/*     */     
/* 212 */     NumberFormat format = NumberFormat.getInstance();
/* 213 */     NumberFormatter formatter = new NumberFormatter(format);
/* 214 */     formatter.setValueClass(Integer.class);
/* 215 */     formatter.setMinimum(Integer.valueOf(0));
/* 216 */     formatter.setMaximum(Integer.valueOf(255));
/*     */ 
/*     */     
/* 219 */     formatter.setCommitsOnValidEdit(true);
/* 220 */     JFormattedTextField intNum = new JFormattedTextField(formatter);
/* 221 */     colNote.setCellEditor(new DefaultCellEditor(intNum));
/*     */     
/* 223 */     colColor.setWidth(250);
/* 224 */     colColor.setMaxWidth(250);
/*     */     
/* 226 */     JComboBox<String> comboBox = new JComboBox();
/* 227 */     comboBox.addItem("blue");
/* 228 */     comboBox.addItem("red");
/* 229 */     comboBox.addItem("black");
/* 230 */     comboBox.addItem("yellow");
/* 231 */     comboBox.addItem("cyan");
/* 232 */     comboBox.addItem("gray");
/* 233 */     comboBox.addItem("darkgray");
/* 234 */     comboBox.addItem("lightgrey");
/* 235 */     comboBox.addItem("green");
/* 236 */     comboBox.addItem("magenta");
/* 237 */     comboBox.addItem("orange");
/* 238 */     comboBox.addItem("pink");
/* 239 */     colColor.setCellEditor(new DefaultCellEditor(comboBox));
/*     */ 
/*     */     
/* 242 */     DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
/*     */     
/* 244 */     colColor.setCellRenderer(renderer);
/* 245 */     colColor.setWidth(120);
/* 246 */     colColor.setPreferredWidth(120);
/*     */     
/* 248 */     TableButton btnRemoveButton = new TableButton("Remove");
/*     */ 
/*     */     
/* 251 */     colRemove.setCellRenderer(btnRemoveButton);
/* 252 */     colRemove.setCellEditor(btnRemoveButton);
/*     */ 
/*     */ 
/*     */     
/* 256 */     JPanel buttonPane = new JPanel();
/* 257 */     buttonPane.setLayout(new FlowLayout(2));
/* 258 */     getContentPane().add(buttonPane, "South");
/*     */     
/* 260 */     JButton okButton = new JButton("OK");
/* 261 */     okButton.setActionCommand("OK");
/* 262 */     buttonPane.add(okButton);
/* 263 */     getRootPane().setDefaultButton(okButton);
/* 264 */     okButton.addActionListener(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 273 */     String strActionCommand = e.getActionCommand();
/* 274 */     if ("new".equals(strActionCommand)) {
/*     */       
/* 276 */       String strName = JOptionPane.showInputDialog("Drum Kit Name:");
/* 277 */       if (strName == null) {
/*     */         return;
/*     */       }
/*     */       
/* 281 */       if (this.drumKits.containsKey(strName)) {
/*     */         
/* 283 */         JOptionPane.showMessageDialog(this, "A drum kit with this name already exists.");
/*     */         return;
/*     */       } 
/* 286 */       Drumkit kit = new Drumkit(strName);
/* 287 */       this.drumKits.put(strName, kit);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 292 */       this.KitNames = (String[])this.drumKits.keySet().toArray((Object[])this.KitNames);
/* 293 */       this.activeKit = this.KitNames[0];
/* 294 */       this.comboKitsModel.addElement(strName);
/* 295 */       this.comboKitsModel.setSelectedItem(strName);
/*     */ 
/*     */ 
/*     */       
/* 299 */       repaint();
/*     */       
/*     */       return;
/*     */     } 
/* 303 */     if (e.getSource() instanceof JComboBox) {
/*     */       
/* 305 */       JComboBox cb = (JComboBox)e.getSource();
/* 306 */       String kitName = (String)cb.getSelectedItem();
/* 307 */       this.activeKit = kitName;
/* 308 */       this.editorModel.setActiveKit(kitName);
/* 309 */       this.table.repaint();
/*     */       
/*     */       return;
/*     */     } 
/* 313 */     String strEditorDrumKit = this.m_ParentFrame.getActiveDrumKit();
/* 314 */     if (!this.drumKits.containsKey(strEditorDrumKit))
/*     */     {
/* 316 */       this.m_ParentFrame.setActiveDrumKit(this.activeKit);
/*     */     }
/*     */     
/* 319 */     dispose();
/* 320 */     this.m_ParentFrame.repaint();
/* 321 */     FileUtilities.saveDrumKitFile(this.drumKits);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public class TableButton
/*     */     extends JButton
/*     */     implements TableCellRenderer, TableCellEditor, TableButtonListener, ActionListener
/*     */   {
/*     */     private int selectedRow;
/*     */ 
/*     */     
/*     */     private int selectedColumn;
/*     */     
/*     */     Vector<DrumKitEditor.TableButtonListener> listener;
/*     */     
/* 337 */     String selectedInstrument = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public TableButton(String text) {
/* 343 */       super(text);
/* 344 */       this.listener = new Vector<>();
/* 345 */       addActionListener(this);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void actionPerformed(ActionEvent e) {
/* 352 */       tableButtonClicked(this.selectedRow, this.selectedColumn);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addTableButtonListener(DrumKitEditor.TableButtonListener l) {
/* 359 */       this.listener.add(l);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void removeTableButtonListener(DrumKitEditor.TableButtonListener l) {
/* 366 */       this.listener.remove(l);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
/* 375 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int col) {
/* 384 */       this.selectedRow = row;
/* 385 */       this.selectedColumn = col;
/* 386 */       this.selectedInstrument = (String)table.getValueAt(row, 0);
/* 387 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void addCellEditorListener(CellEditorListener arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void cancelCellEditing() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Object getCellEditorValue() {
/* 409 */       return "";
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isCellEditable(EventObject arg0) {
/* 417 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void removeCellEditorListener(CellEditorListener arg0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean shouldSelectCell(EventObject arg0) {
/* 432 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean stopCellEditing() {
/* 440 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getSelectedInstrument() {
/* 447 */       return this.selectedInstrument;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void tableButtonClicked(int row, int col) {
/* 454 */       DrumKitEditor.this.editorModel.getActiveKit().removeInstrument(getSelectedInstrument());
/*     */       
/* 456 */       DrumKitEditor.this.table.repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface TableButtonListener extends EventListener {
/*     */     void tableButtonClicked(int param1Int1, int param1Int2);
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\drumkitEditor\DrumKitEditor.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */