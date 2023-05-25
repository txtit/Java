package com.raven.main;


//import ComboBoxWithTitleExample;
import com.raven.bill.Bill;
import com.raven.bill.TableActionCellEditor;
import com.raven.bill.TableActionCellRender;
import com.raven.bill.TableActionEvent;
import com.raven.classify.JPanel_Nuoc;
import com.raven.classify.JPanel_ThucAn;
import com.raven.classify.JPanel_TrangMieng;
import com.raven.events.EvenItem;
import com.raven.form.FormHome;
import com.raven.model.ModelItem;
import com.raven.swing.ScrollBar;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import com.raven.table.ListtTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.drawer.Drawer;
import javaswingdev.drawer.DrawerController;
import javaswingdev.drawer.DrawerItem;
import javaswingdev.drawer.EventDrawer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import org.jdesktop.animation.timing.interpolation.PropertySetter;
import SQL.*;


public class Main extends javax.swing.JFrame {
//    double gia;
    private FormHome home;
    private Animator animator;
    private Point animatePoint;
    private DrawerController drawer;
    private ModelItem itemSelected;
    private JPanel_ThucAn thucan;
    private JPanel_Nuoc nuoc;
    private JPanel_TrangMieng trangmieng;
    private Bill orderBill;
    private ListtTable listTable;
    private DangNhap login;
    ArrayList<ModelItem> list = new ArrayList<ModelItem>();
    String dataDate;
    DefaultTableModel model = new DefaultTableModel();
    int stt=0;
    int Tong=0;
    String banSelect="";
//    private JTable bill = new JTable();
 
     

    
    public Main() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        //combo box chọn bàn
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jComboBox_ChonBan.setBorder(javax.swing.BorderFactory.createTitledBorder("Chọn bàn")); // Đặt tiêu đề cho JComboBox
        
        init();
        setTime();
        
        // menu bar
         drawer= Drawer.newDrawer(this)
                 .header(new logo())
//                 .separator(2, new Color(173,173,173))
                 .space(5)
//                 .background(new Color(255,51,51))
                 .backgroundTransparent(0.3f)
//                 .drawerBackground(new Color(238,238,143))
                    .enableScroll(true)
                 .addChild(new DrawerItem("Đăng Nhập").icon(new ImageIcon(getClass().getResource("/menuicon/user.png"))).build())
                 .addChild(new DrawerItem("Data item1").icon(new ImageIcon(getClass().getResource("/menuicon/report.png"))).build())
                 .addChild(new DrawerItem("Data item2").icon(new ImageIcon(getClass().getResource("/menuicon/data.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/cont.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/income.png"))).build())
                 .addChild(new DrawerItem("Data item3").icon(new ImageIcon(getClass().getResource("/menuicon/expense.png"))).build())
                
                 
                 .addFooter(new DrawerItem("Đăng Xuất").icon(new ImageIcon(getClass().getResource("/menuicon/exit.png"))).build())
                  .event(new EventDrawer() {
            @Override
            public void selected(int Data, DrawerItem item) {
                
                if (Data == 6) { // Khi nhấn vào item "Đăng Nhập"
                        // Tắt JFrame hiện tại (Main)
                        dispose();

                        // Tạo và hiển thị JFrame mới (NewFrame)
                        login = new DangNhap();
                        login.setVisible(true);
                    } else if(Data == 0 ){
                        
                    }
            }
                  
                 })
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

        jScrollPane3.setVerticalScrollBar(new ScrollBar());
        // tạo table
        bill2.setModel(model);
        model.addColumn("STT");
        model.addColumn("Tên");
        model.addColumn("SL");
        model.addColumn("Giá");
        model.addColumn("Tổng");
        model.addColumn("HĐ");
        TableActionEvent2 event = new TableActionEvent2() {
           
            @Override
            public void onDelete(int row) {
               if(bill2.isEnabled()){
                   bill2.getCellEditor().stopCellEditing();
               }
                int option = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
           model.removeRow(row);
           list.remove(row);
           
        } else {
            JOptionPane.showMessageDialog(null, "không xóa");
        }
               
            }

            

          
        };
         bill2.getColumnModel().getColumn(5).setCellRenderer(new TableActionCellRender2());
         bill2.getColumnModel().getColumn(5).setCellEditor(new TableActionCellEditor2(event));
      
        // taoj home panel
        home = new FormHome();
        thucan = new JPanel_ThucAn();
        nuoc = new JPanel_Nuoc();
        trangmieng = new JPanel_TrangMieng();
          DefaultTableModel model = new DefaultTableModel();
         ModelItem data = new ModelItem();
//         String[] data = {};                 
         
        // tao nut thoat
        winButton.initEvent(this, backGround1);
        mainPanel.setLayout(new BorderLayout());
//        mainPanel.add(home); // them home panel
//        testData_ThucAn();
//        mainPanel.add(thucan);
        mainPanel.add(home);
//        thucan.setVisible(false);
//        home.setVisible(true);

    runData();
    runData_ThucAn();
    runData_Nuoc();
    runData_TrangMieng();
    
    }


    public void setTime(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Date date = new Date();
                    SimpleDateFormat tf = new SimpleDateFormat("HH:mm:ss aa");
                    SimpleDateFormat df = new SimpleDateFormat("EEEE, dd-MM-yyyy");
                    String time = tf.format(date);
                    Jt_Time.setText(time.split(" ")[0]+" "+time.split(" ")[1]);
                    Jt_date.setText(df.format(date));
                }
            }
        }).start();
    }

    private void runData(){
                //table
      
        home.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                       
                
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if(itemSelected != item){
                    
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        home.setSelected(com); // no selected sửa lại cho bấm 2 lần k bị lặp lại
                        home.showItem(item);
//                        com.setEnabled(false);//  enable de ko cho chọn nhieu lan
//                        home.addTable(item);
                     System.out.println(item.getPrice());
//         
                        animator.start();
                                 
                     
                } 
                }
                
                  

                System.out.println(item.getItemID());

            }

        });
       
        
        int ID=1;
        for(int i=0;i<=0;i++){
            home.addItem(new ModelItem(ID++, "Adidas", "This product is very good", 160000, "Adidas", new ImageIcon(getClass().getResource("/image/cappuccinocoffee.jpg"))));
            home.addItem(new ModelItem(ID++, "Nike", "This product is very good", 150000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            home.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 150000, "Adidas", new ImageIcon(getClass().getResource("/image/chocolatecoffee.jpg"))));
            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 170000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            home.addItem(new ModelItem(ID++, "Jordan", "This product is very good", 180000, "Adidas", new ImageIcon(getClass().getResource("/image/coldcoffee.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 190, "Adidas", new ImageIcon(getClass().getResource("/image/XoiGac.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/greentea.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/banhxeo.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 170, "Adidas", new ImageIcon(getClass().getResource("/image/orange_juice.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/okla.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/vegetarianpizza.jpg"))));
//            home.addItem(new ModelItem(ID++, "4DFWD PULSE", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/chocolatecake.jpg"))));
            
        }
    }
    private void runData_ThucAn(){
        thucan.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
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
                  

                System.out.println(item.getItemID());
//                home.setSelected(com);
//                home.showItem(item);
            }
        });
        int ID=1;
        for(int i=0;i<=0;i++){
               
            thucan.addItem(new ModelItem(ID++, "Adidas", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/chickenburger.jpg"))));
            thucan.addItem(new ModelItem(ID++, "Nike", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            thucan.repaint();home.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            thucan.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            thucan.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
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
    private void runData_Nuoc(){
        nuoc.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if(itemSelected != item){
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        nuoc.setSelected(com);
                        nuoc.showItem(item);
                        animator.start();
                } 
                }
                  


            }
        });
        int ID=1;
        for(int i=0;i<=1;i++){
            nuoc.addItem(new ModelItem(ID++, "Adidas", "This product is very good", 160, "Adidas", new ImageIcon(getClass().getResource("/image/cappuccinocoffee.jpg"))));
            nuoc.addItem(new ModelItem(ID++, "Nike", "This product is very good", 150, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            nuoc.repaint();home.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/cappuccinocoffee.jpg"))));
            nuoc.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/orange_juice.jpg"))));
            nuoc.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140, "Adidas", new ImageIcon(getClass().getResource("/image/chocolatecoffee.jpg"))));
            
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
    private void runData_TrangMieng(){
        trangmieng.setEvent(new EvenItem() {
            // override phuong thuc evenitem
            @Override
            public void itemClick(Component com, ModelItem item) {
                if(itemSelected!=null){
                    mainPanel.setImageOld(itemSelected.getImage());
                }
                if(itemSelected != item){
                       if(!animator.isRunning()){
                        itemSelected=item;
                        animatePoint= getLocationOf(com); // cho test
                        mainPanel.setImage(item.getImage()); // lay hinh anh
                        mainPanel.setImageLocation(animatePoint); 
                        mainPanel.setImageSize(new Dimension(180,120));
                        mainPanel.repaint();
                        trangmieng.setSelected(com);
                        trangmieng.showItem(item);
                        animator.start();
                } 
                }
                  


            }
        });
        int ID=1;
        for(int i=0;i<=1;i++){
            trangmieng.addItem(new ModelItem(ID++, "Adidas", "This product is very good", 160000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            trangmieng.addItem(new ModelItem(ID++, "Nike", "This product is very good", 150000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            trangmieng.repaint();home.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            trangmieng.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            trangmieng.addItem(new ModelItem(ID++, "Converse", "This product is very good", 140000, "Adidas", new ImageIcon(getClass().getResource("/image/coke.jpg"))));
            
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
        jButton_Home = new javax.swing.JButton();
        menubar = new javax.swing.JButton();
        Jt_date = new javax.swing.JLabel();
        Jt_Time = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        badgeButton1 = new com.raven.win_button.BadgeButton();
        mainPanel = new com.raven.swing.MainPanel();
        jButton_Chon = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        bill2 = new javax.swing.JTable();
        jTextField_Tong2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jComboBox_ChonBan = new javax.swing.JComboBox<>();

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
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        header.add(winButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(1144, 6, -1, -1));

        jButton_ThucAn.setBackground(new java.awt.Color(255, 153, 0));
        jButton_ThucAn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_ThucAn.setText("Thức Ăn ");
        jButton_ThucAn.setMinimumSize(new java.awt.Dimension(105, 32));
        jButton_ThucAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ThucAnActionPerformed(evt);
            }
        });
        header.add(jButton_ThucAn, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, 124, -1));

        jButton_TrangMieng.setBackground(new java.awt.Color(51, 255, 51));
        jButton_TrangMieng.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_TrangMieng.setText("Tráng Miệng");
        jButton_TrangMieng.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_TrangMiengActionPerformed(evt);
            }
        });
        header.add(jButton_TrangMieng, new org.netbeans.lib.awtextra.AbsoluteConstraints(622, 30, 157, -1));

        jButton_Nuoc.setBackground(new java.awt.Color(51, 204, 255));
        jButton_Nuoc.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_Nuoc.setText("Nước");
        jButton_Nuoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_NuocActionPerformed(evt);
            }
        });
        header.add(jButton_Nuoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, -1, -1));

        jButton_Home.setBackground(new java.awt.Color(204, 204, 204));
        jButton_Home.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton_Home.setText("Home");
        jButton_Home.setMinimumSize(new java.awt.Dimension(30, 40));
        jButton_Home.setOpaque(true);
        jButton_Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_HomeActionPerformed(evt);
            }
        });
        header.add(jButton_Home, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 30, -1, -1));

        menubar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/menuicon/menu-barok.png"))); // NOI18N
        menubar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menubarActionPerformed(evt);
            }
        });
        header.add(menubar, new org.netbeans.lib.awtextra.AbsoluteConstraints(41, 9, -1, -1));

        Jt_date.setFont(new java.awt.Font("Nirmala UI Semilight", 1, 16)); // NOI18N
        Jt_date.setForeground(new java.awt.Color(0, 204, 204));
        Jt_date.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.add(Jt_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 9, 200, 24));

        Jt_Time.setFont(new java.awt.Font("Lucida Console", 1, 16)); // NOI18N
        Jt_Time.setForeground(new java.awt.Color(0, 204, 204));
        Jt_Time.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        header.add(Jt_Time, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 9, 114, 24));

        jButton3.setText("Danh Sách Bàn");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        header.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, -1));

        badgeButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon.png"))); // NOI18N
        badgeButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                badgeButton1MouseClicked(evt);
            }
        });
        header.add(badgeButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 160, 60));

        mainPanel.setBackground(new java.awt.Color(193, 193, 193));

        jButton_Chon.setText("Chọn");
        jButton_Chon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_ChonMouseClicked(evt);
            }
        });
        jButton_Chon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ChonActionPerformed(evt);
            }
        });

        bill2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên", "Số lượng", "Giá", "Tổng", "okla", "Hành Động"
            }
        ));
        bill2.setOpaque(false);
        bill2.setRowHeight(40);
        jScrollPane3.setViewportView(bill2);

        jLabel5.setText("Tổng : ");

        jButton2.setText("Đặt Hàng");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("New");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addComponent(jTextField_Tong2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton1))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(jTextField_Tong2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        jComboBox_ChonBan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bàn 1", "Bàn 2", "Bàn 3", "Bàn 4", "Bàn 5", "Bàn 6", "Bàn 7", "Bàn 8", "Bàn 9", "Bàn 10" }));
        jComboBox_ChonBan.setOpaque(true);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jButton_Chon))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jComboBox_ChonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(866, 866, 866))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox_ChonBan, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(166, 166, 166)
                .addComponent(jButton_Chon)
                .addGap(12, 12, 12)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        home.setVisible(false);
        nuoc.setVisible(false);
        trangmieng.setVisible(false);
//        runData_ThucAn();
//        mainPanel.removeAll();
//         mainPanel.setLayout(new BorderLayout());
        if(!thucan.isShowing()){
         thucan.setVisible(true);
         mainPanel.add(thucan);

        }
//         home.setVisible(false);
        
//        testData_ThucAn();
        thucan.repaint();
//        thucan.setVisible(true);
    }//GEN-LAST:event_jButton_ThucAnActionPerformed

    
    
    private void jButton_HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_HomeActionPerformed
        
//       mainPanel.removeAll();
//       mainPanel.add(home);
//       if(home.isShowing()){
//           System.out.println("okla");
//       }
       nuoc.setVisible(false);
       trangmieng.setVisible(false);
       thucan.setVisible(false);
       if(!home.isShowing()){
            home.setVisible(true);
       }
        
//       testData();
//        runData();
       home.repaint();
    }//GEN-LAST:event_jButton_HomeActionPerformed

    private void menubarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menubarActionPerformed
        if(drawer.isShow()){
            drawer.hide();
        }else {
            drawer.show();
        }
    }//GEN-LAST:event_menubarActionPerformed

    private void jButton_NuocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_NuocActionPerformed
//     mainPanel.removeAll();
    home.setVisible(false);
    thucan.setVisible(false);
    trangmieng.setVisible(false);
    if(!nuoc.isShowing()){
        nuoc.setVisible(true);
        mainPanel.add(nuoc);
    }
     
     nuoc.repaint();
    }//GEN-LAST:event_jButton_NuocActionPerformed

    private void jButton_TrangMiengActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_TrangMiengActionPerformed
 home.setVisible(false);
    thucan.setVisible(false);
    nuoc.setVisible(false);
    if(!trangmieng.isShowing()){
        trangmieng.setVisible(true);
        mainPanel.add(trangmieng);
    }
      mainPanel.add(trangmieng);
      trangmieng.repaint();
    }//GEN-LAST:event_jButton_TrangMiengActionPerformed

    private void jButton_ChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ChonActionPerformed
//       if(home.isShowing()){
         
//        }
    }//GEN-LAST:event_jButton_ChonActionPerformed

    private void jButton_ChonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_ChonMouseClicked
         
        
//        System.out.println(banSelect);
        if(home.isShowing()){
            stt++;
            
            home.addRowTable(model,itemSelected,stt,home.getQuantity());
            jTextField_Tong2.setText( Integer.toString(Tong+=home.getTonggia()) );
            list.add(itemSelected);
        }
        if(thucan.isShowing()){
            stt++;
            thucan.addRowTable(model, itemSelected, stt);
            jTextField_Tong2.setText( Integer.toString(Tong+=thucan.getTonggia()) );
            list.add(itemSelected);
        }
        if(nuoc.isShowing()){
            stt++;
            nuoc.addRowTable(model, itemSelected, stt);
            jTextField_Tong2.setText( Integer.toString(Tong+=nuoc.getTonggia()) );
            list.add(itemSelected);
        }
        if(trangmieng.isShowing()){
            stt++;
            trangmieng.addRowTable(model, itemSelected, stt);
            jTextField_Tong2.setText(Integer.toString(Tong+=trangmieng.getTonggia()));
            list.add(itemSelected);
        }
          
    }//GEN-LAST:event_jButton_ChonMouseClicked

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        if(list.isEmpty()){
            JOptionPane.showMessageDialog(null, "Bạn chưa chọn món ăn!");
        }else{
              dataDate= Jt_Time.getText()+
                "\n  Date: "+Jt_date.getText();
              banSelect=jComboBox_ChonBan.getSelectedItem().toString();
         // bang order
           
         orderBill = new Bill(list,home.getQuantity(),dataDate,banSelect);
         orderBill.setVisible(true);
        }
      
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
      model.setRowCount(0);
      list.clear();
      Tong = 0;
      stt = 0;
      jTextField_Tong2.setText(Integer.toString(Tong));
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
//        listTable = new ListtTable(banSelect);
//        listTable.setVisible(true);\
badgeButton1.setText(Integer.parseInt(badgeButton1.getText()) + 1 + "");
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void badgeButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_badgeButton1MouseClicked
        listTable = new ListtTable(banSelect);
        listTable.setVisible(true);
    }//GEN-LAST:event_badgeButton1MouseClicked

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
    private javax.swing.JLabel Jt_Time;
    private javax.swing.JLabel Jt_date;
    private com.raven.swing.BackGround backGround1;
    private com.raven.swing.BackGround backGround2;
    private com.raven.win_button.BadgeButton badgeButton1;
    private javax.swing.JTable bill2;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton_Chon;
    private javax.swing.JButton jButton_Home;
    private javax.swing.JButton jButton_Nuoc;
    private javax.swing.JButton jButton_ThucAn;
    private javax.swing.JButton jButton_TrangMieng;
    private javax.swing.JComboBox<String> jComboBox_ChonBan;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField_Tong2;
    private com.raven.swing.MainPanel mainPanel;
    private javax.swing.JButton menubar;
    private com.raven.win_button.WinButton winButton;
    // End of variables declaration//GEN-END:variables
}
