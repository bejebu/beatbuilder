/*     */ package model;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MRUlist
/*     */ {
/*  12 */   private ArrayList<MRUitem> mruList = new ArrayList<>(5);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MRUlist(HashMap<String, String> storedList) {
/*  18 */     addItem(storedList, 1);
/*  19 */     addItem(storedList, 2);
/*  20 */     addItem(storedList, 3);
/*  21 */     addItem(storedList, 4);
/*  22 */     addItem(storedList, 5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getItemCount() {
/*  29 */     return this.mruList.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addItem(HashMap<String, String> storedList, int p_iIndex) {
/*  36 */     String strItem = storedList.get("MRU" + p_iIndex);
/*  37 */     if (strItem != null) {
/*     */       
/*  39 */       String strFileName = strItem.substring(0, strItem.indexOf(":"));
/*  40 */       String strFile = strItem.substring(strItem.indexOf(":") + 1);
/*  41 */       MRUitem item = new MRUitem(strFileName, strFile);
/*  42 */       this.mruList.add(item);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MRUitem addItem(String p_strFileName, String p_strFile) {
/*  50 */     MRUitem item = new MRUitem(p_strFileName, p_strFile);
/*     */     
/*  52 */     if (!this.mruList.contains(item)) {
/*     */       
/*  54 */       this.mruList.add(0, item);
/*  55 */       if (this.mruList.size() > 5)
/*     */       {
/*  57 */         this.mruList.remove(this.mruList.size() - 1);
/*     */       }
/*     */     } else {
/*     */       
/*  61 */       int iIndex = this.mruList.indexOf(item);
/*  62 */       if (iIndex > 0) {
/*     */         
/*  64 */         this.mruList.remove(item);
/*  65 */         this.mruList.add(0, item);
/*     */       } 
/*     */     } 
/*     */     
/*  69 */     return item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MRUitem addItem(File p_File) {
/*  76 */     String p_strFileName = p_File.getName();
/*  77 */     String p_strFile = p_File.getPath();
/*  78 */     return addItem(p_strFileName, p_strFile);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> toFile() {
/*  85 */     ArrayList<String> lMRUitems = new ArrayList<>(5);
/*  86 */     int iIndex = 1;
/*  87 */     for (MRUitem item : this.mruList) {
/*     */       
/*  89 */       lMRUitems.add("MRU" + iIndex + "=" + item.getFileName() + "=" + item.getFile());
/*  90 */       iIndex++;
/*     */     } 
/*  92 */     return lMRUitems;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ArrayList<String> getFileNames() {
/*  99 */     ArrayList<String> lNames = new ArrayList<>(this.mruList.size());
/* 100 */     for (MRUitem item : this.mruList)
/*     */     {
/* 102 */       lNames.add(item.getFileName());
/*     */     }
/* 104 */     return lNames;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileAt(int p_iIndex) {
/* 111 */     if (this.mruList.isEmpty())
/*     */     {
/* 113 */       return null;
/*     */     }
/* 115 */     if (p_iIndex > this.mruList.size())
/*     */     {
/* 117 */       return null;
/*     */     }
/* 119 */     return ((MRUitem)this.mruList.get(p_iIndex - 1)).getFile();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFileNameAt(int p_iIndex) {
/* 126 */     if (this.mruList.isEmpty())
/*     */     {
/* 128 */       return null;
/*     */     }
/* 130 */     if (p_iIndex >= this.mruList.size())
/*     */     {
/* 132 */       return null;
/*     */     }
/* 134 */     return ((MRUitem)this.mruList.get(p_iIndex)).getFileName();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void toINIfile(HashMap<String, String> p_appConfig) {
/* 141 */     int iIndex = 1;
/* 142 */     for (MRUitem item : this.mruList) {
/*     */       
/* 144 */       p_appConfig.put("MRU" + iIndex, String.valueOf(item.getFileName()) + ":" + item.getFile());
/* 145 */       iIndex++;
/*     */     } 
/*     */   }
/*     */   
/*     */   public class MRUitem
/*     */   {
/* 151 */     String m_strFileName = "";
/* 152 */     String m_strFile = "";
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public MRUitem(String p_strFileName, String p_strFile) {
/* 158 */       this.m_strFileName = p_strFileName;
/* 159 */       this.m_strFile = p_strFile;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getFileName() {
/* 166 */       return this.m_strFileName;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getFile() {
/* 173 */       return this.m_strFile;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean equals(Object p_MRU) {
/* 180 */       if (p_MRU instanceof MRUitem)
/*     */       {
/* 182 */         if (((MRUitem)p_MRU).getFile().equals(this.m_strFile))
/*     */         {
/* 184 */           return true;
/*     */         }
/*     */       }
/* 187 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\model\MRUlist.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */