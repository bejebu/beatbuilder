/*    */ package tools;
/*    */ 
/*    */ import javax.swing.JOptionPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ErrorHandler
/*    */ {
/*    */   public static void showError(Exception ex) {
/* 11 */     StackTraceElement elm = ex.getStackTrace()[0];
/* 12 */     String strMessage = String.valueOf(ex.getClass().getName()) + " \n\nIn" + 
/* 13 */       elm.getClassName() + "." + elm.getMethodName() + 
/* 14 */       "\n\n" + ex.getMessage();
/* 15 */     JOptionPane.showMessageDialog(null, strMessage, "BeatBuilder", 
/* 16 */         0);
/*    */   }
/*    */ }


/* Location:              C:\Users\WalkerP\Downloads\BeatBuilder.jar!\tools\ErrorHandler.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */