package com.raven.main;

import com.raven.classify.JPanel_ThucAn;
import com.raven.events.EvenItem;
import com.raven.form.FormHome;
import com.raven.model.ModelItem;
import com.raven.swing.PanelItem;
import com.raven.swing.ScrollBar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;


public class Main extends javax.swing.JFrame {

    private FormHome home;
    private Animator animator;
    private Point animatePoint;
    private DrawerController drawer;
    private ModelItem itemSelected;
    private JPanel_ThucAn thucan;
    

    
    public Main() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        init();
        
        // menu bar
         drawer= Drawer.newDrawer(this)
                 .header(new logo())
//                 .separator(2, new Color(173,173,173))
                 .space(5)
//                 .background(new Color(255,51,51))
                 .backgroundTransparent(0.3f)
//                 .drawerBackground(new Color(238,238,143))
                    .enableScroll(true)
                 .addChild(new DrawerItem("Data item").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item1").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item2").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                                  

                 .addFooter(new DrawerItem("Đăng Xuất").icon(new ImageIcon(getClass().getResource("/menuicon/exit.png"))).build())
                 .build();
         
         
        // hoat hanh tu foem animatePoint đen Targetpoint
        //duration là so lượng hoạt ảnh của item
        animator=PropertySetter.createAnimator(500, mainPanel, "imageLocation", animatePoint,mainPanel.getTargetLocation());
        
        
        
//        animator.addTarget(new PropertySetter(mainPanel, "imageSize",new Dimension(180,120),mainPanel.getTargetSize()));
        // them timing target để compoment k mất ngay lap tuc khi bam cai khac
        animator.addTarget(new TimingTargetAdapter(){
            @Override
            public void end() {
                mainPanel.setImageOld(null);// set anh cu
                
            }
            
        });
        animator.setResolution(0);
        animator.setAcceleration(.5f);
        animator.setDeceleration(.5f);
      
        }
    
    private void init(){
        // taoj home panel
        home = new FormHome();
        thucan = new JPanel_ThucAn();
        // tao nut thoat
        winButton.initEvent(this, backGround1);
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(home); // them home panel
//        testData_ThucAn();
//        mainPanel.add(thucan);
//        thucan.setVisible(false);
//        home.setVisible(true);

        testData();
    testData_ThucAn();
    
    }

    private void testData(){
        home.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
//                if(itemSelected!=null){
//                    mainPanel.setImageOld(itemSelected.getImage());
//                }
                if(itemSelected != item){
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        home.setSelected(com);
                        home.showItem(item);
                        animator.start();
                } 
                }
                  

//                System.out.println(item.getItemID());
//                home.setSelected(com);
//                home.showItem(item);
            }
        });
        int ID=1;
        for(int i=0;i<=1;i++){
            home.addItem(new ModelItem(ID++, "Adidas", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/cappuccinocoffee.jpg"))));
            home.addItem(new ModelItem(ID++, "Nike", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/tom.jpg"))));
            home.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/mamcom.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/galuoc.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/chocolatecoffee.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 170, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            home.addItem(new ModelItem(ID++, "Jordan", "This product is very good", 180, "Adidas", new ImageIcon(getClass().getResource("/image/coldcoffee.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 190, "Adidas", new ImageIcon(getClass().getResource("/image/XoiGac.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/greentea.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/banhxeo.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 170, "Adidas", new ImageIcon(getClass().getResource("/image/orange_juice.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/okla.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/galuoc.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/vegetarianpizza.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/chocolatecake.jpg"))));
            
        }
    }
    private void testData_ThucAn(){
        thucan.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
//                if(itemSelected!=null){
//                    mainPanel.setImageOld(itemSelected.getImage());
//                }
                if(itemSelected != item){
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        thucan.setSelected(com);
                        thucan.showItem(item);
                        animator.start();
                } 
                }
                  

//                System.out.println(item.getItemID());
//                home.setSelected(com);
//                home.showItem(item);
            }
        });
        int ID=1;
        for(int i=0;i<=0;i++){
            thucan.addItem(new ModelItem(ID++, "Adidas", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/cappuccinocoffee.jpg"))));
            thucan.addItem(new ModelItem(ID++, "Nike", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/tom.jpg"))));
            thucan.repaint();home.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/mamcom.jpg"))));
            thucan.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/mamcom.jpg"))));
            thucan.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/mamcom.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/galuoc.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/chocolatecoffee.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 170, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
//            home.addItem(new ModelItem(ID++, "Jordan", "This product is very good", 180, "Adidas", new ImageIcon(getClass().getResource("/image/coldcoffee.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 190, "Adidas", new ImageIcon(getClass().getResource("/image/XoiGac.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/greentea.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/banhxeo.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 170, "Adidas", new ImageIcon(getClass().getResource("/image/orange_juice.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/okla.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/galuoc.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/vegetarianpizza.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/chocolatecake.jpg"))));
            
        }
    }
    
    // lấy vị trí của item để tạo aniamtion
   private Point getLocationOf(Component com){
       Point p= home.getPanelItemLocation();
       int x = p.x; // lay vi tri chọn trên panel fromhome
       int y = p.y;
       int itemX= com.getX();// lay vi tri x của item
       int itemY= com.getY();// lay vi tri y cua item
       int left = 10; // ddieu chinh de đep hon
       int top = 70;
       return new Point(x+ itemX+left ,top+itemY+y);
   }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backGround1 = new com.raven.swing.BackGround();
        jPanel1 = new javax.swing.JPanel();
        backGround2 = new com.raven.swing.BackGround();
        header = new javax.swing.JPanel();
        winButton = new com.raven.win_button.WinButton();
        jButton_ThucAn = new javax.swing.JButton();
        jButton_TrangMieng = new javax.swing.JButton();
        jButton_Nuoc = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        menubar = new javax.swing.JButton();
        mainPanel = new com.raven.swing.MainPanel();

        javax.swing.GroupLayout backGround1Layout = new javax.swing.GroupLayout(backGround1);
        backGround1.setLayout(backGround1Layout);
        backGround1Layout.setHorizontalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        backGround1Layout.setVerticalGroup(
            backGround1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        backGround2.setBackground(new java.awt.Color(255, 255, 255));

        header.setOpaque(false);

        jButton_ThucAn.setBackground(new java.awt.Color(255, 153, 0));
        jButton_ThucAn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_ThucAn.setText("Thức Ăn ");
        jButton_ThucAn.setMinimumSize(new java.awt.Dimension(105, 32));
        jButton_ThucAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThucAnActionPerformed(evt);
            }
        });

        jButton_TrangMieng.setBackground(new java.awt.Color(51, 255, 51));
        jButton_TrangMieng.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_TrangMieng.setText("Tráng Miệng");

        jButton_Nuoc.setBackground(new java.awt.Color(51, 204, 255));
        jButton_Nuoc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_Nuoc.setText("Nước");

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Home");
        jButton1.setMinimumSize(new java.awt.Dimension(30, 40));
        jButton1.setOpaque(true);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        menubar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuicon/menu-barok.png"))); // NOI18N
        menubar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(menubar)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(winButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ThucAn, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_Nuoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_TrangMieng, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                        .addGap(443, 443, 443))))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(winButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_TrangMieng)
                    .addComponent(jButton_Nuoc)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_ThucAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(headerLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(menubar)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        mainPanel.setBackground(new java.awt.Color(193, 193, 193));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout backGround2Layout = new javax.swing.GroupLayout(backGround2);
        backGround2.setLayout(backGround2Layout);
        backGround2Layout.setHorizontalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backGround2Layout.setVerticalGroup(
            backGround2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backGround2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backGround2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_ThucAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ThucAnActionPerformed
//        home.setVisible(false);
        mainPanel.removeAll();
//         mainPanel.setLayout(new BorderLayout());
//         thucan.setVisible(true);
//         home.setVisible(false);
        mainPanel.add(thucan);
//        testData_ThucAn();
        thucan.repaint();
//        thucan.setVisible(true);
    }//GEN-LAST:event_jButton_ThucAnActionPerformed

    
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       mainPanel.removeAll();
       mainPanel.add(home);
       if(home.isShowing()){
           System.out.println("okla");
       }
//       thucan.setVisible(false);
//         home.setVisible(true);
//       testData();
       home.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void menubarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubarActionPerformed
        if(drawer.isShow()){
            drawer.hide();
        }else {
            drawer.show();
        }
    }//GEN-LAST:event_menubarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.BackGround backGround1;
    private com.raven.swing.BackGround backGround2;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton_Nuoc;
    private javax.swing.JButton jButton_ThucAn;
    private javax.swing.JButton jButton_TrangMieng;
    private javax.swing.JPanel jPanel1;
    private com.raven.swing.MainPanel mainPanel;
    private javax.swing.JButton menubar;
    private com.raven.win_button.WinButton winButton;
    // End of variables declaration//GEN-END:variables
}
