/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import core.Contribution;
import core.Donor;
import core.Fund;
import dao.ContributionDAO;
import dao.DBConnection;
import dao.DonorDAO;
import dao.FundDAO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author legen
 */
public class ContributionFrame extends javax.swing.JFrame {

    private DBConnection conn;
    private ContributionDAO conDAO;
    private DonorDAO donDAO;
    private FundDAO fundDAO;
    private List<Integer> intList;
    private List<Donor> donorList;
    private List<Contribution> contributionList;
    private List<Fund> fundList;
    private List<String> tempList;
    private int fundID;
    /**
     * Creates new form ContributionFrame
     */
    public ContributionFrame(DBConnection myConn)
    {
        initComponents();
        tempList = new ArrayList<String>();
        this.conn = myConn;
        donDAO = new DonorDAO(this.conn);
        conDAO = new ContributionDAO(this.conn);
        fundDAO = new FundDAO(this.conn);
        IDTextField.setVisible(false);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        dateTextField.requestFocus();

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        dateTextField.setText(sdf.format(date));
        
        //Added by Max Duhan to fix non right justifing amounts
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        try{
            contributionList = conDAO.getAllContributions();
            ContributionTableModel model = new ContributionTableModel(contributionList);
            contributionTable.setModel(model);
            
            contributionTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);// Max Duhan

            donorList = donDAO.getAllDonors();
            nameTextField.setText(donorList.get(0).getF_name() + " " + donorList.get(0).getL_name());

            intList = donDAO.getAllEnvNums();
            envComboBox.setModel(new DefaultComboBoxModel(intList.toArray()));

            fundList = fundDAO.getAllFunds();

            //look into setting names for funds
            for(int i = 0; i < fundList.size(); i++)
            {
                tempList.add(fundList.get(i).getName());
            }

            fundID = setGeneral(tempList);
            fundComboBox.setModel(new DefaultComboBoxModel(tempList.toArray()));
            fundComboBox.setSelectedIndex(fundID);

        } catch(Exception ex)
        {
            Logger.getLogger(ContributionFrame.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error 2: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }

        AutoCompletion.enable(fundComboBox);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addPanel = new javax.swing.JPanel();
        nameTextField = new javax.swing.JTextField();
        amountLabel = new javax.swing.JLabel();
        amountTextField = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox<>();
        addButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        resetButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        dateLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        fundComboBox = new javax.swing.JComboBox<>();
        fundLabel = new javax.swing.JLabel();
        dateTextField = new javax.swing.JTextField();
        envIDLabel = new javax.swing.JLabel();
        envComboBox = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        noteTextPane = new javax.swing.JTextPane();
        noteLabel = new javax.swing.JLabel();
        IDTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        contributionTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        addPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Add/Update/Delete Contribution"));

        nameTextField.setEditable(false);
        nameTextField.setFocusable(false);

        amountLabel.setText("Amount:");

        amountTextField.setPreferredSize(new java.awt.Dimension(6, 25));
        amountTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                amountTextFieldFocusLost(evt);
            }
        });

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Check", "Currency", "Coin", "ACH", " " }));

        addButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        updateButton.setText("Update");
        updateButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                updateButtonFocusLost(evt);
            }
        });

        resetButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        dateLabel.setText("Date:");

        nameLabel.setText("Name:");

        fundComboBox.setEditable(true);
        fundComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General Fund", " " }));
        fundComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                fundComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });

        fundLabel.setText("Fund:");

        envIDLabel.setText("Envelope ID:");

        envComboBox.setEditable(true);
        envComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));
        envComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                envComboBoxItemStateChanged(evt);
            }
        });
        envComboBox.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
                envComboBoxPopupMenuWillBecomeVisible(evt);
            }
        });

        jScrollPane2.setViewportView(noteTextPane);

        noteLabel.setText("Note:");

        IDTextField.setEditable(false);
        IDTextField.setFocusable(false);

        javax.swing.GroupLayout addPanelLayout = new javax.swing.GroupLayout(addPanel);
        addPanel.setLayout(addPanelLayout);
        addPanelLayout.setHorizontalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addPanelLayout.createSequentialGroup()
                .addContainerGap(169, Short.MAX_VALUE)
                .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(dateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(fundLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(62, 62, 62)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(envIDLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(envComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(amountLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(36, 36, 36)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(nameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addComponent(noteLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(addPanelLayout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(332, 332, 332)))
                .addContainerGap(295, Short.MAX_VALUE))
        );
        addPanelLayout.setVerticalGroup(
            addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel)
                    .addComponent(envIDLabel)
                    .addComponent(envComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateLabel)
                    .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addPanelLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteButton)
                            .addComponent(addButton)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fundLabel)
                        .addComponent(fundComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(amountLabel)
                        .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noteLabel)))
                .addGap(18, 18, 18)
                .addGroup(addPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(updateButton)
                    .addComponent(resetButton)))
        );

        contributionTable.setAutoCreateRowSorter(true);
        contributionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Con. ID", "Env. ID", "Date", "Amount", "Type", "Fund", "Note"
            }
        ));
        contributionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contributionTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(contributionTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );

        addPanel.getAccessibleContext().setAccessibleName("Edit Contribution");
        addPanel.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void envComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_envComboBoxItemStateChanged
        try{
            donorList = donDAO.getAllDonors();
            int i = envComboBox.getSelectedIndex();
            nameTextField.setText(donorList.get(i).getF_name() + " " + donorList.get(i).getL_name());
        } catch (Exception ex) {
            Logger.getLogger(ContributionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_envComboBoxItemStateChanged

    private void amountTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_amountTextFieldFocusLost
        addButton.requestFocus();
    }//GEN-LAST:event_amountTextFieldFocusLost

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        reset(donorList);

        try {
            fundList = fundDAO.getAllFunds();
            //look into setting names for funds
            for(int i = 0; i < fundList.size(); i++)
            {
                tempList.add(fundList.get(i).getName());
            }

            fundID = setGeneral(tempList);
            fundComboBox.setModel(new DefaultComboBoxModel(tempList.toArray()));
            fundComboBox.setSelectedIndex(fundID);

        } catch (Exception ex) {
            Logger.getLogger(ContributionFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_resetButtonActionPerformed

    private void contributionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contributionTableMouseClicked
        int i = contributionTable.getSelectedRow();
        int rowNum = contributionTable.convertRowIndexToModel(i);

        TableModel model = contributionTable.getModel();

        String typeVal = model.getValueAt(rowNum, 5).toString();
        int typeBoxI = getIndexInComboBox(typeVal, typeComboBox);
        typeComboBox.setSelectedIndex(typeBoxI);

        String fundVal = (String) model.getValueAt(rowNum, 4);
        int fundBoxI = getIndexInComboBox(fundVal, fundComboBox);
        fundComboBox.setSelectedIndex(fundBoxI);

        String envNum = model.getValueAt(rowNum,2).toString();
        int envBoxI = getIndexInComboBox(envNum, envComboBox);
        envComboBox.setSelectedIndex(envBoxI);
        
        amountTextField.setText(model.getValueAt(rowNum, 3).toString());

        dateTextField.setText(model.getValueAt(rowNum, 1).toString());
        
        if(model.getValueAt(rowNum, 6) == null)
            noteTextPane.setText("");
        else
            noteTextPane.setText(model.getValueAt(rowNum, 6).toString());
        
        if(model.getValueAt(rowNum, 0) == null)
            IDTextField.setText("");
        else
            IDTextField.setText(model.getValueAt(rowNum, 0).toString());

    }//GEN-LAST:event_contributionTableMouseClicked

    //Searches a combo box for the index of a string value and returns it
    private int getIndexInComboBox(String toFind, JComboBox jBox)
    {
        String item;
        boolean found = false;
        int index = -1;
       for(int i = 0; i < jBox.getItemCount() && !found; i++)
       {
           item = jBox.getItemAt(i).toString();
           if(item.equals(toFind))
           {
               index = i;
               found = true;
           }
       }
       
       return index;
    }

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed

        try {
            
            String sDate = dateTextField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            java.util.Date date = sdf.parse(sDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            Contribution contribution =   new Contribution(Double.parseDouble
                (amountTextField.getText()),sqlDate,noteTextPane.getText(),
                    typeComboBox.getSelectedItem().toString(),fundComboBox.
                            getSelectedItem().toString(),Integer.parseInt
                                (envComboBox.getSelectedItem().toString()));
            conDAO.addContribution(contribution);

         }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Value Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
         finally{
             reset(donorList);
         }

    }//GEN-LAST:event_addButtonActionPerformed

    private void updateButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_updateButtonFocusLost
        envComboBox.requestFocus();
    }//GEN-LAST:event_updateButtonFocusLost

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here
         try {
            String sDate = dateTextField.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            java.util.Date date = sdf.parse(sDate);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            Contribution contribution =   new Contribution(Integer.parseInt(IDTextField.getText()),
                    Double.parseDouble(amountTextField.getText()),sqlDate,noteTextPane.getText(),
                    typeComboBox.getSelectedItem().toString(),fundComboBox.
                            getSelectedItem().toString(),Integer.parseInt
                                (envComboBox.getSelectedItem().toString()));
            conDAO.deleteContribution(contribution);

         }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Value Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
         finally{
             reset(donorList);
         }

    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try{
        String envID =envComboBox.getSelectedItem( ).toString();
        String sDate = dateTextField.getText();
        int id=Integer.parseInt(IDTextField.getText());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        java.util.Date date = sdf.parse(sDate);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        Contribution contribution=new Contribution(id,Double.parseDouble
                (amountTextField.getText()),sqlDate, noteTextPane.getText( ),typeComboBox.getSelectedItem( ).toString(),fundComboBox.getSelectedItem( ).toString(),Integer.parseInt( envID ));
        conDAO.updateContribution(contribution);
    }
    catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Value Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Database Error : " + ex, "Error", JOptionPane.ERROR_MESSAGE);}
    finally{
             reset(donorList);
         }

    }//GEN-LAST:event_updateButtonActionPerformed

    private void envComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_envComboBoxPopupMenuWillBecomeVisible

        // TODO add your handling code here:
        reloadEnvNumList();

    }//GEN-LAST:event_envComboBoxPopupMenuWillBecomeVisible

    private void fundComboBoxPopupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_fundComboBoxPopupMenuWillBecomeVisible
        // TODO add your handling code here:
        reloadFundList();
    }//GEN-LAST:event_fundComboBoxPopupMenuWillBecomeVisible

     private void reloadEnvNumList()
    {
        try{
            List<Integer> envNumList;
            envNumList = donDAO.getAllEnvNums();
            envComboBox.setModel(new DefaultComboBoxModel(envNumList.toArray()));
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error reloading envelope number list on the contributions tab: " + e);
        }
    }
     
      private void reloadFundList()
    {
        try{
            List<Fund> fundList;
            List<String> fundListS = new ArrayList<>();
            fundList = fundDAO.getAllFunds();
            int index = fundComboBox.getSelectedIndex();
            for(int i = 0; i < fundList.size(); i++)
            {
                fundListS.add(fundList.get(i).getName());
            }
            
            fundComboBox.setModel(new DefaultComboBoxModel(fundListS.toArray()));
            
            if(index > fundComboBox.getItemCount())
                index = 0;
            
            fundComboBox.setSelectedIndex(index);
            
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error reloading fund list on the contributions tab: " + e);
        }
    }
     
    private int setGeneral(List<String> b)
    {
        int k = 0;

        for(int i = 0; i < b.size(); i++)
        {
            //System.out.println(b.get(i));
            if(b.get(i).equals("General"))
            {
                k = i;
                //System.out.println("Found General at index: " + k);
                return k;
            }
        }
        return k;
    }

    private void reset(List<Donor> a)
    {

        envComboBox.setSelectedIndex(0);
        nameTextField.setText(a.get(0).getF_name() + " " + a.get(0).getL_name());
        amountTextField.setText("");
        typeComboBox.setSelectedIndex(0);
        noteTextPane.setText("");
        envComboBox.requestFocus();
        fundComboBox.setSelectedIndex(5);

        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        Date date = new Date();
        dateTextField.setText(sdf.format(date));

        try{
            contributionList = conDAO.getAllContributions();
            ContributionTableModel model = new ContributionTableModel(contributionList);
            contributionTable.setModel(model);
        } catch (Exception ex)
        {
            JOptionPane.showMessageDialog(this, "Error 2: " + ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
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
            java.util.logging.Logger.getLogger(ContributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ContributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ContributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ContributionFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ContributionFrame(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDTextField;
    private javax.swing.JButton addButton;
    private javax.swing.JPanel addPanel;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField amountTextField;
    private javax.swing.JTable contributionTable;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JComboBox<String> envComboBox;
    private javax.swing.JLabel envIDLabel;
    private javax.swing.JComboBox<String> fundComboBox;
    private javax.swing.JLabel fundLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JTextPane noteTextPane;
    private javax.swing.JButton resetButton;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables
}
