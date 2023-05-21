
package com.raven.bill;

import com.raven.model.ModelItem;
import com.raven.swing.ScrollBar;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

   
    public class Bill extends javax.swing.JFrame {
    DefaultTableModel model = new DefaultTableModel();
     private String dataDate;
    
    int Tong = 0;

    public Bill() {
        
      
          
    }
   
    public Bill(ArrayList<ModelItem> list,int value,String dataDate){
        initComponents();
         jScrollPane1.setVerticalScrollBar(new ScrollBar());
       bill.setModel(model);
      
         setLocationRelativeTo(null);
         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
         //set ngay gio 
         this.dataDate = dataDate;
         
//        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("STT");
        model.addColumn("Tên");
        model.addColumn("Số Lượng");
        model.addColumn("Giá");
        model.addColumn("Tổng");
        model.addColumn("Hành động");
        
        // them hanh dong cho table
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onEdit(int row) {
                System.out.println("Edit row : " +row);
            }

            @Override
            public void onDelete(int row) {
               if(bill.isEnabled()){
                   bill.getCellEditor().stopCellEditing();
               }
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
           model.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "không xóa");
        }
               
            }

            @Override
            public void onView(int row) {
                System.out.println("View row: "+ row);
            }
        };
         bill.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender());
         bill.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor(event));
        z(list,value);
        JDateTime.setText("Time : "+"\n"+dataDate );
          

    }
    public void z(ArrayList<ModelItem> list,int value){
        int i=1;
        System.out.println("z");
        for (ModelItem modelItem : list) {
            model.addRow(new Object[] {i++, modelItem.getNameItem(),value,modelItem.getPrice(),modelItem.getPrice()*value});
            i++;
         Tong +=modelItem.getPrice()*value;
         if(Tong<1000){
             DecimalFormat df = new DecimalFormat(" #,### VND ");
             jTextField_tong.setText(df.format(Tong ));
             
         }else if(Tong>1000){
             DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');
        DecimalFormat df = new DecimalFormat("#,###.## VND  ", symbols);
       jTextField_tong.setText(df.format(Tong ));

            
         }
        
         
        
        }
       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bill = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField_tong = new javax.swing.JTextField();
        imageAvatar1 = new test.ImageAvatar();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        JDateTime = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setName(""); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bill.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        bill.setRowHeight(40);
        bill.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bill);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-3, 231, 540, 285));

        jLabel1.setText("Tổng :");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 529, 76, -1));

        jTextField_tong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_tongActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField_tong, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 523, 105, -1));

        imageAvatar1.setBorderSize(3);
        imageAvatar1.setBorderSpace(2);
        imageAvatar1.setGradientColor1(new java.awt.Color(250, 84, 84));
        imageAvatar1.setGradientColor2(new java.awt.Color(8, 225, 61));
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/menuicon/logo.jpg"))); // NOI18N

        javax.swing.GroupLayout imageAvatar1Layout = new javax.swing.GroupLayout(imageAvatar1);
        imageAvatar1.setLayout(imageAvatar1Layout);
        imageAvatar1Layout.setHorizontalGroup(
            imageAvatar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 329, Short.MAX_VALUE)
        );
        imageAvatar1Layout.setVerticalGroup(
            imageAvatar1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 102, Short.MAX_VALUE)
        );

        jPanel1.add(imageAvatar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 0, 329, 102));

        jLabel2.setFont(new java.awt.Font("SimSun-ExtB", 1, 24)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("SPEAGO RESTAURANT");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(133, 114, 255, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("SPEAGO RESTAURANT");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(186, 145, 149, -1));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("**************************************************************************************");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 555, 462, -1));

        jLabel5.setFont(new java.awt.Font("Serif", 1, 18)); // NOI18N
        jLabel5.setText("HÓA ĐƠN");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(217, 201, -1, -1));

        jLabel7.setText("Tên Khách Hàng: ");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 589, 92, -1));

        jLabel8.setText("Số điện thoại:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 611, 95, -1));

        jLabel9.setText("Khuyến Mãi :");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(247, 598, 90, -1));
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 583, 112, -1));
        jPanel1.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(123, 611, 112, -1));

        jButton1.setBackground(new java.awt.Color(102, 255, 51));
        jButton1.setText("Thanh Toán");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 526, 132, -1));
        jPanel1.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(328, 595, 123, -1));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setText("In Hóa Đơn");
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(187, 526, -1, -1));

        JDateTime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(JDateTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 300, 20));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("***********************************************************************************");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 160, 452, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_tongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_tongActionPerformed
        
    }//GEN-LAST:event_jTextField_tongActionPerformed

    private void billMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billMouseClicked
      PanelAction action = new PanelAction();
    }//GEN-LAST:event_billMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Bill().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel JDateTime;
    private javax.swing.JTable bill;
    private test.ImageAvatar imageAvatar1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField_tong;
    // End of variables declaration//GEN-END:variables
}
