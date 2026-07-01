package controller;

import exception.AppException;
import model.Pet;
import service.PetService;
import service.impl.PetServiceImpl;
import util.UiStyle;
import util.IconFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PetFrame extends JFrame {
    private JTextField txtId;
    private JTextField txtName;
    private JComboBox<String> comboSpecies;
    private JTextField txtBreed;
    private JComboBox<String> comboGender;
    private JTextField txtAge;
    private JTextField txtWeight;
    private JTextField txtChipNumber;
    private JTextArea txtHealthNote;
    private JTable table;
    private DefaultTableModel tableModel;
    private final PetService petService = new PetServiceImpl();

    public PetFrame() {
        initialize();
        loadPets();
    }

    private void initialize() {
        setTitle("PetLife - 寵物資料管理 CRUD");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 680);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(null);
        contentPane.setBackground(UiStyle.BG);
        setContentPane(contentPane);

        JLabel title = UiStyle.title("寵物資料管理");
        title.setIcon(IconFactory.icon(IconFactory.Type.PET, 30, UiStyle.PRIMARY_DARK));
        title.setIconTextGap(12);
        title.setBounds(32, 24, 260, 38);
        contentPane.add(title);

        JButton backBtn = UiStyle.secondaryButton("返回首頁");
        backBtn.setIcon(IconFactory.icon(IconFactory.Type.BACK, 16, UiStyle.PRIMARY_DARK));
        backBtn.setBounds(900, 28, 120, 34);
        contentPane.add(backBtn);
        backBtn.addActionListener(e -> {
            new HomeFrame().setVisible(true);
            dispose();
        });

        JPanel formPanel = new UiStyle.RoundedPanel(28, Color.WHITE);
        formPanel.setBounds(30, 85, 360, 520);
        contentPane.add(formPanel);

        JLabel formTitle = new JLabel("寵物基本資料");
        formTitle.setFont(UiStyle.font(Font.BOLD, 20));
        formTitle.setBounds(24, 20, 180, 30);
        formPanel.add(formTitle);

        txtId = addInput(formPanel, "ID", 70, true);
        txtName = addInput(formPanel, "寵物名稱 *", 115, false);
        comboSpecies = addCombo(formPanel, "種類 *", new String[]{"狗", "貓", "其他"}, 160);
        txtBreed = addInput(formPanel, "品種", 205, false);
        comboGender = addCombo(formPanel, "性別", new String[]{"男", "女", "未知"}, 250);
        txtAge = addInput(formPanel, "年齡", 295, false);
        txtWeight = addInput(formPanel, "體重 kg", 340, false);
        txtChipNumber = addInput(formPanel, "晶片號碼", 385, false);

        JLabel noteLabel = new JLabel("健康備註");
        noteLabel.setFont(UiStyle.font(Font.PLAIN, 14));
        noteLabel.setBounds(24, 430, 90, 24);
        formPanel.add(noteLabel);

        txtHealthNote = new JTextArea();
        txtHealthNote.setLineWrap(true);
        txtHealthNote.setFont(UiStyle.font(Font.PLAIN, 14));
        JScrollPane noteScroll = new JScrollPane(txtHealthNote);
        noteScroll.setBounds(112, 430, 210, 60);
        formPanel.add(noteScroll);

        JPanel buttonPanel = new JPanel(null);
        buttonPanel.setBackground(UiStyle.BG);
        buttonPanel.setBounds(415, 85, 610, 62);
        contentPane.add(buttonPanel);

        JButton addBtn = UiStyle.primaryButton("新增");
        addBtn.setIcon(IconFactory.icon(IconFactory.Type.ADD, 16, Color.WHITE));
        addBtn.setBounds(0, 10, 90, 38);
        buttonPanel.add(addBtn);

        JButton updateBtn = UiStyle.secondaryButton("修改");
        updateBtn.setIcon(IconFactory.icon(IconFactory.Type.EDIT, 16, UiStyle.PRIMARY_DARK));
        updateBtn.setBounds(105, 10, 90, 38);
        buttonPanel.add(updateBtn);

        JButton deleteBtn = UiStyle.secondaryButton("刪除");
        deleteBtn.setIcon(IconFactory.icon(IconFactory.Type.DELETE, 16, UiStyle.PRIMARY_DARK));
        deleteBtn.setBounds(210, 10, 90, 38);
        buttonPanel.add(deleteBtn);

        JButton clearBtn = UiStyle.secondaryButton("清除");
        clearBtn.setBounds(315, 10, 90, 38);
        buttonPanel.add(clearBtn);

        JButton refreshBtn = UiStyle.secondaryButton("重新整理");
        refreshBtn.setIcon(IconFactory.icon(IconFactory.Type.REFRESH, 16, UiStyle.PRIMARY_DARK));
        refreshBtn.setBounds(420, 10, 120, 38);
        buttonPanel.add(refreshBtn);

        tableModel = new DefaultTableModel(new Object[]{"ID", "名稱", "種類", "品種", "性別", "年齡", "體重", "晶片號碼", "健康備註"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(28);
        table.setFont(UiStyle.font(Font.PLAIN, 13));
        table.getTableHeader().setFont(UiStyle.font(Font.BOLD, 13));
        UiStyle.styleTable(table);

        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setBounds(415, 160, 610, 445);
        contentPane.add(tableScroll);

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                fillFormFromTable();
            }
        });

        addBtn.addActionListener(e -> addPet());
        updateBtn.addActionListener(e -> updatePet());
        deleteBtn.addActionListener(e -> deletePet());
        clearBtn.addActionListener(e -> clearForm());
        refreshBtn.addActionListener(e -> loadPets());
    }

    private JTextField addInput(JPanel panel, String labelText, int y, boolean readOnly) {
        JLabel label = new JLabel(labelText);
        label.setFont(UiStyle.font(Font.PLAIN, 14));
        label.setBounds(24, y, 90, 28);
        panel.add(label);

        JTextField field = new JTextField();
        field.setFont(UiStyle.font(Font.PLAIN, 14));
        field.setEditable(!readOnly);
        field.setBounds(112, y, 210, 32);
        UiStyle.styleField(field);
        panel.add(field);
        return field;
    }

    private JComboBox<String> addCombo(JPanel panel, String labelText, String[] items, int y) {
        JLabel label = new JLabel(labelText);
        label.setFont(UiStyle.font(Font.PLAIN, 14));
        label.setBounds(24, y, 90, 28);
        panel.add(label);

        JComboBox<String> combo = new JComboBox<>(items);
        combo.setFont(UiStyle.font(Font.PLAIN, 14));
        combo.setBounds(112, y, 210, 30);
        panel.add(combo);
        return combo;
    }

    private void addPet() {
        try {
            petService.addPet(readForm(false));
            showInfo("新增成功");
            clearForm();
            loadPets();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void updatePet() {
        try {
            petService.updatePet(readForm(true));
            showInfo("修改成功");
            clearForm();
            loadPets();
        } catch (Exception e) {
            showError(e);
        }
    }

    private void deletePet() {
        try {
            int id = parseInt(txtId.getText(), "請先選擇要刪除的資料");
            int result = JOptionPane.showConfirmDialog(this, "確定要刪除此筆寵物資料嗎？", "確認刪除", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                petService.deletePet(id);
                showInfo("刪除成功");
                clearForm();
                loadPets();
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    private Pet readForm(boolean needId) {
        int id = needId ? parseInt(txtId.getText(), "請先選擇資料") : 0;
        String name = txtName.getText().trim();
        String species = comboSpecies.getSelectedItem().toString();
        String breed = txtBreed.getText().trim();
        String gender = comboGender.getSelectedItem().toString();
        int age = txtAge.getText().trim().isEmpty() ? 0 : parseInt(txtAge.getText(), "年齡請輸入整數");
        double weight = txtWeight.getText().trim().isEmpty() ? 0 : parseDouble(txtWeight.getText(), "體重請輸入數字");
        String chipNumber = txtChipNumber.getText().trim();
        String healthNote = txtHealthNote.getText().trim();
        return new Pet(id, name, species, breed, gender, age, weight, chipNumber, healthNote);
    }

    private void loadPets() {
        try {
            tableModel.setRowCount(0);
            List<Pet> pets = petService.getAllPets();
            for (Pet p : pets) {
                tableModel.addRow(new Object[]{
                        p.getId(), p.getName(), p.getSpecies(), p.getBreed(), p.getGender(),
                        p.getAge(), p.getWeight(), p.getChipNumber(), p.getHealthNote()
                });
            }
        } catch (Exception e) {
            showError(e);
        }
    }

    private void fillFormFromTable() {
        int row = table.getSelectedRow();
        if (row < 0) return;
        txtId.setText(String.valueOf(tableModel.getValueAt(row, 0)));
        txtName.setText(String.valueOf(tableModel.getValueAt(row, 1)));
        comboSpecies.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 2)));
        txtBreed.setText(String.valueOf(tableModel.getValueAt(row, 3)));
        comboGender.setSelectedItem(String.valueOf(tableModel.getValueAt(row, 4)));
        txtAge.setText(String.valueOf(tableModel.getValueAt(row, 5)));
        txtWeight.setText(String.valueOf(tableModel.getValueAt(row, 6)));
        txtChipNumber.setText(String.valueOf(tableModel.getValueAt(row, 7)));
        txtHealthNote.setText(String.valueOf(tableModel.getValueAt(row, 8)));
    }

    private void clearForm() {
        txtId.setText("");
        txtName.setText("");
        comboSpecies.setSelectedIndex(0);
        txtBreed.setText("");
        comboGender.setSelectedIndex(0);
        txtAge.setText("");
        txtWeight.setText("");
        txtChipNumber.setText("");
        txtHealthNote.setText("");
        table.clearSelection();
    }

    private int parseInt(String value, String message) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            throw new AppException(message);
        }
    }

    private double parseDouble(String value, String message) {
        try {
            return Double.parseDouble(value.trim());
        } catch (Exception e) {
            throw new AppException(message);
        }
    }

    private void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "系統訊息", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showError(Exception e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "錯誤", JOptionPane.ERROR_MESSAGE);
    }
}
