package controller;

import exception.AppException;
import model.HealthRecord;
import service.HealthRecordService;
import service.impl.HealthRecordServiceImpl;
import util.UiStyle;
import util.IconFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class HealthRecordFrame extends JFrame {
    private JTextField txtId, txtPetId, txtDate, txtWeight, txtSymptom;
    private JComboBox<String> comboAppetite, comboStool;
    private JTextArea txtNote;
    private JTable table;
    private DefaultTableModel model;
    private final HealthRecordService service = new HealthRecordServiceImpl();

    public HealthRecordFrame() { initialize(); loadData(); }

    private void initialize() {
        setTitle("PetLife - 健康照護紀錄 CRUD"); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setSize(1080, 680); setLocationRelativeTo(null);
        JPanel root = new JPanel(null); root.setBackground(UiStyle.BG); setContentPane(root);
        JLabel title = UiStyle.title("健康照護紀錄"); title.setIcon(IconFactory.icon(IconFactory.Type.HEALTH, 30, UiStyle.PRIMARY_DARK)); title.setIconTextGap(12); title.setBounds(32, 24, 260, 38); root.add(title);
        JButton back = UiStyle.secondaryButton("返回首頁"); back.setBounds(900, 28, 120, 34); root.add(back); back.addActionListener(e -> { new HomeFrame().setVisible(true); dispose(); });
        JPanel form = new UiStyle.RoundedPanel(28, Color.WHITE); form.setBounds(30, 85, 360, 520); root.add(form);
        JLabel ft = new JLabel("健康資料"); ft.setFont(UiStyle.font(Font.BOLD, 20)); ft.setBounds(24,20,180,30); form.add(ft);
        txtId=input(form,"ID",70,true); txtPetId=input(form,"寵物ID *",115,false); txtDate=input(form,"日期 yyyy-mm-dd",160,false); txtWeight=input(form,"體重 kg",205,false);
        comboAppetite=combo(form,"食慾",new String[]{"正常","偏低","偏高","不吃"},250); comboStool=combo(form,"排泄",new String[]{"正常","軟便","腹瀉","便秘","異常"},295);
        txtSymptom=input(form,"症狀",340,false);
        JLabel nl=new JLabel("備註"); nl.setFont(UiStyle.font(Font.PLAIN,14)); nl.setBounds(24,385,90,24); form.add(nl);
        txtNote=new JTextArea(); txtNote.setLineWrap(true); txtNote.setFont(UiStyle.font(Font.PLAIN,14)); JScrollPane ns=new JScrollPane(txtNote); ns.setBounds(112,385,210,90); form.add(ns);
        addButtons(root);
        model = new DefaultTableModel(new Object[]{"ID","寵物ID","寵物名","日期","體重","食慾","排泄","症狀","備註"},0){ public boolean isCellEditable(int r,int c){return false;}};
        table=new JTable(model); table.setRowHeight(28); table.setFont(UiStyle.font(Font.PLAIN,13)); table.getTableHeader().setFont(UiStyle.font(Font.BOLD,13)); UiStyle.styleTable(table);
        JScrollPane sp=new JScrollPane(table); sp.setBounds(415,160,610,445); root.add(sp);
        table.getSelectionModel().addListSelectionListener(e->{ if(!e.getValueIsAdjusting()) fill(); });
    }
    private void addButtons(JPanel root){ JPanel p=new JPanel(null); p.setBackground(UiStyle.BG); p.setBounds(415,85,610,62); root.add(p); String[] names={"新增","修改","刪除","清除","重新整理"}; for(int i=0;i<names.length;i++){ JButton b=i==0?UiStyle.primaryButton(names[i]):UiStyle.secondaryButton(names[i]); b.setBounds(i*105,10,i==4?120:90,38); p.add(b); final int k=i; b.addActionListener(e->{ if(k==0)add(); else if(k==1)update(); else if(k==2)delete(); else if(k==3)clear(); else loadData(); }); }}
    private JTextField input(JPanel p,String text,int y,boolean ro){ JLabel l=new JLabel(text); l.setFont(UiStyle.font(Font.PLAIN,14)); l.setBounds(24,y,95,28); p.add(l); JTextField f=new JTextField(); f.setFont(UiStyle.font(Font.PLAIN,14)); f.setEditable(!ro); f.setBounds(125,y,197,32); UiStyle.styleField(f); p.add(f); return f; }
    private JComboBox<String> combo(JPanel p,String text,String[] items,int y){ JLabel l=new JLabel(text); l.setFont(UiStyle.font(Font.PLAIN,14)); l.setBounds(24,y,95,28); p.add(l); JComboBox<String> c=new JComboBox<>(items); c.setFont(UiStyle.font(Font.PLAIN,14)); c.setBounds(125,y,197,30); p.add(c); return c; }
    private HealthRecord read(boolean needId){ return new HealthRecord(needId?num(txtId.getText(),"請先選擇資料"):0,num(txtPetId.getText(),"寵物ID請輸入整數"),"",txtDate.getText().trim(),dbl(txtWeight.getText()),comboAppetite.getSelectedItem().toString(),comboStool.getSelectedItem().toString(),txtSymptom.getText().trim(),txtNote.getText().trim()); }
    private void add(){ try{ service.add(read(false)); ok("新增成功"); clear(); loadData(); }catch(Exception e){err(e);} }
    private void update(){ try{ service.update(read(true)); ok("修改成功"); clear(); loadData(); }catch(Exception e){err(e);} }
    private void delete(){ try{ int id=num(txtId.getText(),"請先選擇資料"); if(JOptionPane.showConfirmDialog(this,"確定刪除？","確認",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){ service.delete(id); clear(); loadData(); }}catch(Exception e){err(e);} }
    private void loadData(){ try{ model.setRowCount(0); for(HealthRecord r:service.getAll()) model.addRow(new Object[]{r.getId(),r.getPetId(),r.getPetName(),r.getRecordDate(),r.getWeight(),r.getAppetite(),r.getStoolStatus(),r.getSymptom(),r.getNote()}); }catch(Exception e){err(e);} }
    private void fill(){ int r=table.getSelectedRow(); if(r<0)return; txtId.setText(v(r,0)); txtPetId.setText(v(r,1)); txtDate.setText(v(r,3)); txtWeight.setText(v(r,4)); comboAppetite.setSelectedItem(v(r,5)); comboStool.setSelectedItem(v(r,6)); txtSymptom.setText(v(r,7)); txtNote.setText(v(r,8)); }
    private String v(int r,int c){ Object o=model.getValueAt(r,c); return o==null?"":String.valueOf(o); }
    private void clear(){ txtId.setText(""); txtPetId.setText(""); txtDate.setText(""); txtWeight.setText(""); txtSymptom.setText(""); txtNote.setText(""); table.clearSelection(); }
    private int num(String s,String m){ try{return Integer.parseInt(s.trim());}catch(Exception e){throw new AppException(m);} } private double dbl(String s){ if(s.trim().isEmpty()) return 0; try{return Double.parseDouble(s.trim());}catch(Exception e){throw new AppException("體重請輸入數字");} }
    private void ok(String m){ JOptionPane.showMessageDialog(this,m); } private void err(Exception e){ JOptionPane.showMessageDialog(this,e.getMessage(),"錯誤",JOptionPane.ERROR_MESSAGE); }
}
