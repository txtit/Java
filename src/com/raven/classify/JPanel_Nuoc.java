
package com.raven.classify;

import com.raven.compoment.Item;
import com.raven.events.EvenItem;
import com.raven.model.ModelItem;
import com.raven.swing.ScrollBar;
import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Xuan Thao
 */
public class JPanel_Nuoc extends javax.swing.JPanel {

    public void setEvent(EvenItem event) {
        this.eventTA = event;
    }
    int tonggia=0;

    public int getTonggia() {
        return tonggia;
    }
    private EvenItem eventTA;
    public JPanel_Nuoc() {
        initComponents();
        scroll.setVerticalScrollBar(new ScrollBar());
    }

    // them san pham 
    public void addItem(ModelItem data){
       Item item = new Item();
       // goi ham set data de set du lieu cho item
       item.setData(data);
       // them event chuot
       item.addMouseListener(new MouseAdapter() {
           @Override
           // an chuot trai
           public void mousePressed(MouseEvent me) {
               if(SwingUtilities.isLeftMouseButton(me)){
                   eventTA.itemClick(item, data);
               }
           }
       });
       // them panel item
       panelItem.add(item);
       panelItem.repaint();
       panelItem.revalidate(); // cap nhật lại bố cục panel
       
    }
    
    // nhan dien chon item
     public void setSelected(Component item){
        for(Component com:panelItem.getComponents() ){
            Item i = (Item)com;
            if(i.isSelected()){
                i.setSelected(false);
            }
        }
        ((Item) item).setSelected(true);
    }
     // show item
     public void showItem(ModelItem data){
         lbItemName.setText(data.getNameItem());
         textDescription.setText(data.getDescription());
         lbbrand.setText(data.getBrandName());
         DecimalFormat df = new DecimalFormat("$#,## 0.00");
         lbPrice.setText(df.format(data.getPrice()));
     }
      public void addRowTable(DefaultTableModel model,ModelItem data,int i){
                  
//          int i=0;

        // Thêm dữ liệu vào JTable
//        while()s
         int qty = (int) quantity.getValue();
        int gia = (int) data.getPrice()*qty;
        tonggia=0;
        model.addRow(new Object[] {i++, lbItemName.getText(),quantity.getValue(),lbPrice.getText(),gia});
        tonggia+=gia;
     }
     // lay vi tri cua item
     public Point getPanelItemLocation(){
         Point p = scroll.getLocation();
         return new Point(p.x,p.y-scroll.getViewport().getViewPosition().y);
     }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbbrand = new javax.swing.JLabel();
        lbPrice = new javax.swing.JLabel();
        lbItemName = new javax.swing.JLabel();
        scroll = new javax.swing.JScrollPane();
        panelItem = new com.raven.swing.PanelItem();
        textDescription = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        quantity = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1200, 546));

        jPanel1.setOpaque(false);

        lbbrand.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbbrand.setForeground(new java.awt.Color(179, 169, 169));
        lbbrand.setText("brand");

        lbPrice.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbPrice.setForeground(new java.awt.Color(66, 55, 55));
        lbPrice.setText("$0.00");

        lbItemName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lbItemName.setForeground(new java.awt.Color(66, 55, 55));
        lbItemName.setText("Item Name");

        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setViewportView(panelItem);

        textDescription.setBorder(null);
        textDescription.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        textDescription.setForeground(new java.awt.Color(196, 184, 184));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Quantity :");

        quantity.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        quantity.setBorder(null);
        quantity.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Select: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbItemName)
                    .addComponent(lbPrice)
                    .addComponent(lbbrand)
                    .addComponent(textDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jLabel1)
                        .addGap(10, 10, 10)
                        .addComponent(quantity, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)))
                .addGap(6, 6, 6)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 855, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbItemName)
                .addGap(0, 0, 0)
                .addComponent(lbPrice)
                .addGap(0, 0, 0)
                .addComponent(lbbrand)
                .addGap(6, 6, 6)
                .addComponent(textDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(quantity)
                        .addComponent(jLabel2))))
            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 543, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        getAccessibleContext().setAccessibleName("");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbItemName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JLabel lbbrand;
    private com.raven.swing.PanelItem panelItem;
    private javax.swing.JSpinner quantity;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTextPane textDescription;
    // End of variables declaration//GEN-END:variables
}
