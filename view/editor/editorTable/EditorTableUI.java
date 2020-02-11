/*     */ package view.editor.editorTable;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.plaf.basic.BasicTableUI;
/*     */ import javax.swing.table.TableCellRenderer;
/*     */ import model.EditorTableModel;
/*     */ import model.Instrument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditorTableUI
/*     */   extends BasicTableUI
/*     */ {
/*  23 */   private EditorTable m_Table = null;
/*  24 */   private EditorTableModel m_TableModel = null;
/*     */ 
/*     */   
/*     */   private JScrollPane m_scrollPane;
/*     */ 
/*     */   
/*     */   public EditorTableUI(EditorTable p_table, EditorTableModel p_model) {
/*  31 */     this.m_Table = p_table;
/*  32 */     this.m_TableModel = p_model;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScrollPane(JScrollPane p_scrollPane) {
/*  40 */     this.m_scrollPane = p_scrollPane;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paint(Graphics p_GraphicsTable, JComponent c) {
/*  47 */     JViewport viewport = this.m_scrollPane.getViewport();
/*  48 */     Point pViewPortPosition = viewport.getViewPosition();
/*  49 */     Dimension dViewportSize = viewport.getExtentSize();
/*  50 */     int colFirstCol = this.m_Table.columnAtPoint(pViewPortPosition);
/*  51 */     Double dViewPortPositionX = Double.valueOf(pViewPortPosition.getX());
/*  52 */     int colLastCol = this.m_Table.columnAtPoint(new Point(dViewportSize.width + 
/*  53 */           dViewPortPositionX.intValue(), 0));
/*  54 */     Rectangle tableRectangle = p_GraphicsTable.getClipBounds();
/*     */     
/*  56 */     int firstRow = this.m_Table.rowAtPoint(new Point(0, tableRectangle.y));
/*  57 */     int lastRow = this.m_Table.rowAtPoint(new Point(0, tableRectangle.y + tableRectangle.height));
/*     */ 
/*     */     
/*  60 */     if (lastRow < 0)
/*     */     {
/*  62 */       lastRow = this.m_Table.getRowCount() - 1;
/*     */     }
/*  64 */     if (colLastCol < 0)
/*     */     {
/*  66 */       colLastCol = this.m_Table.getColumnCount() - 1;
/*     */     }
/*  68 */     for (int i = firstRow; i <= lastRow; i++)
/*     */     {
/*  70 */       paintRow(i, p_GraphicsTable, colFirstCol, colLastCol + 1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paintRow(int p_iRow, Graphics p_TableGraphicsViewport, int p_iFirstCol, int p_iLastCol) {
/*  78 */     Rectangle tableRectangle = p_TableGraphicsViewport.getClipBounds();
/*  79 */     Instrument instr = this.m_TableModel.getInstrument(Integer.valueOf(p_iRow));
/*  80 */     boolean bHasAnyNote = instr.hasAnyNotes();
/*  81 */     Rectangle cellRectangle = null;
/*  82 */     for (int iColumn = p_iFirstCol; iColumn < p_iLastCol; iColumn++) {
/*     */       
/*  84 */       boolean bBump = false;
/*  85 */       if (bHasAnyNote) {
/*     */         
/*  87 */         if (instr.hasNote(iColumn)) {
/*     */           
/*  89 */           cellRectangle = this.m_Table.getCellWithNoteRect(p_iRow, iColumn);
/*  90 */           bBump = true;
/*     */         }
/*     */         else {
/*     */           
/*  94 */           cellRectangle = this.m_Table.getCellRect(p_iRow, iColumn, true);
/*     */         } 
/*     */       } else {
/*     */         
/*  98 */         cellRectangle = this.m_Table.getCellRect(p_iRow, iColumn, true);
/*     */       } 
/*     */       
/* 101 */       if (cellRectangle.intersects(tableRectangle))
/*     */       {
/* 103 */         paintCell(p_iRow, iColumn, p_TableGraphicsViewport, cellRectangle);
/*     */       }
/* 105 */       if (bBump)
/*     */       {
/* 107 */         iColumn++;
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void paintCell(int row, int column, Graphics g, Rectangle cellRectangle) {
/* 116 */     int verticalMargin = this.m_Table.getRowMargin();
/* 117 */     int horizontalMargin = this.m_Table.getColumnModel().getColumnMargin();
/* 118 */     Color c = g.getColor();
/* 119 */     g.setColor(this.m_Table.getGridColor());
/*     */     
/* 121 */     g.drawRect(cellRectangle.x, cellRectangle.y, cellRectangle.width - 1, 
/* 122 */         cellRectangle.height - 1);
/* 123 */     g.setColor(c);
/* 124 */     cellRectangle.setBounds(cellRectangle.x + horizontalMargin / 2, cellRectangle.y + 
/* 125 */         verticalMargin / 2, cellRectangle.width - horizontalMargin, cellRectangle.height - 
/* 126 */         verticalMargin);
/*     */     
/* 128 */     TableCellRenderer cellRenderer = this.m_Table.getCellRenderer(row, column);
/* 129 */     Component cellComponent = this.m_Table.prepareRenderer(cellRenderer, row, column);
/* 130 */     if (cellComponent.getParent() == null)
/*     */     {
/* 132 */       this.rendererPane.add(cellComponent);
/*     */     }
/* 134 */     this.rendererPane.paintComponent(g, cellComponent, this.m_Table, cellRectangle.x, cellRectangle.y, 
/* 135 */         cellRectangle.width, cellRectangle.height, true);
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\view\editor\editorTable\EditorTableUI.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */