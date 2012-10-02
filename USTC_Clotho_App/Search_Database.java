/*---------------------------------------------------*/
// This java class is for USTC Clotho App            //
// Author: Francis Chen                               //
// Usage: This class is mainly for search database GUI//
// Copyrights Reserved                                //
/*----------------------------------------------------*/

package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import java.util.Vector;

@SuppressWarnings("serial")
public class Search_Database extends JFrame{
	
	
	/*------------constructor---------*/
	public Search_Database(int NumOfRegulator,
						int NumOfRegulatee,
						Vector<String>RegulatorNames,
						Vector<String>RegulateeNames,
						int[][] Database)

	{	
		numOfRegulator = NumOfRegulator;
		numOfRegulatee = NumOfRegulatee;
		regulatorNames = new Vector<String>();
		regulateeNames = new Vector<String>();
		database = new int[numOfRegulatee][numOfRegulator];
		for(int i = 0;i < numOfRegulator;i++){
			regulatorNames.add(RegulatorNames.get(i));
		}
		
		for(int i = 0; i < numOfRegulatee;i++){	
			regulateeNames.add(RegulateeNames.get(i));
		}
		
		for(int i = 0; i < numOfRegulatee;i++){
			for(int j = 0;j < numOfRegulator;j++){
				database[i][j] = Database[i][j];
			}
		}
		//initiate all the components
		initComponents();
		//initiate all the regulator and regulatee names
		initNames();
		//initiate all the table events
		initTableEvents();
		//initiate button events
		initButtonEvents();
		//initiate text events
		initTextEvents();
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*---------------method to initiate all the components-------*/

	public void initComponents(){
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		//setBackground(new Color(255,255,255));
		
		//initiate regulator scrollpane
		regulatorScrollPane = new JScrollPane();
		//regulatorScrollPane.setBackground(new Color(255,255,255));
		regulatorScrollPane.setBorder(BorderFactory
				.createLineBorder(new Color(204, 204, 204),0));
		regulatorScrollPane.setForeground(new Color(204, 204, 204));
		regulatorScrollPane.setViewportBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		regulatorScrollPane.setOpaque(false);
		//initiate regulator table
		regulatorTable = new JTable(numOfRegulator,1){
			public boolean isCellEditable(int row, int column) { return false; }
			/*
			public Component prepareRenderer
		      (TableCellRenderer renderer, int row, int column) {
		        Component c = super.prepareRenderer (renderer, row, column);
		        // We want renderer component to be
		        //transparent so background image is visible
		        if( c instanceof JComponent )
		          ((JComponent)c).setOpaque (false);
		        return c;
		      }*/
		};
		regulatorTable.setOpaque(false);
		regulatorTable.setFont(new Font("Consolas", 0, 12));
		//regulatorTable.setBackground(new Color(224,210,181));
		regulatorTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		regulatorTable.setOpaque(false);
		regulatorTable.setGridColor(new Color(204, 204, 204));
		Object[][] tableContents_1 = new Object[numOfRegulator][1]; 
		regulatorTable.setModel(new DefaultTableModel(
				tableContents_1,new String[]{"Regulator Lib"}
				));
		regulatorTable.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),0));
		regulatorScrollPane.setViewportView(regulatorTable);
		
		//initiate regulator name label
		regulatorNameLabel = new JLabel("Regulator",10);
		regulatorNameLabel.setFont(new Font("Consolas", 3, 14));
		//regulatorNameLabel.setText("Regulator:");
		regulatorNameLabel.setOpaque(false);
		
		//initiate regulator name textfield
		regulatorName = new JTextField();
		regulatorName.setFont(new Font("Consolas", 0, 12));
		regulatorName.setBorder(new LineBorder(new Color(224, 210, 181), 0,true));
		
		//initiate regulatee scroll pane
		regulateeScrollPane = new JScrollPane();
		regulateeScrollPane.setOpaque(false);
		regulateeScrollPane.setBorder(BorderFactory
				.createLineBorder(new Color(204, 204, 204),0));
		regulateeScrollPane.setForeground(new Color(204, 204, 204));
		regulateeScrollPane.setViewportBorder(BorderFactory
				.createBevelBorder(BevelBorder.LOWERED));
		//initiate regulatee table
		regulateeTable = new JTable(numOfRegulatee,1){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulateeTable.setBorder(BorderFactory
				.createBevelBorder(BevelBorder.RAISED));
		regulateeTable.setFont(new Font("Consolas", 0, 12));
		regulateeTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		regulateeTable.setGridColor(new Color(204, 204, 204));
		Object[][] tableContents_2 = new Object[numOfRegulatee][1];
		regulateeTable.setModel(new DefaultTableModel(
				tableContents_2,new String[]{"Regulatee Lib"}
				));
		regulateeTable.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0),0));
		regulateeScrollPane.setViewportView(regulateeTable);
		
		//initiate regulatee candidates scrollpane
		regulateeCanScrollPane = new JScrollPane();
		regulateeCanScrollPane.setOpaque(false);
		regulateeCanScrollPane.setBorder(new LineBorder(
				new Color(204, 204, 204), 0, true));
		regulateeCanScrollPane.setForeground(new Color(204, 204, 204));
		regulateeCanScrollPane.setViewportBorder(BorderFactory
				.createEtchedBorder());
		
		//initiate regulatee candiates table
		Object[][] tableContents_3 = new Object[columnCount][2];
		regulateeCandidates = new JTable(columnCount,2){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulateeCandidates.setModel(new DefaultTableModel(
				tableContents_3,new String[]{"Regulatees","Relation"}
				));
		regulateeCandidates.setBorder(BorderFactory
				.createLineBorder(new Color(0, 0, 0),0));
		regulateeCandidates.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		regulateeCandidates.setFont(new Font("Consolas",0,12));
		regulateeCanScrollPane.setViewportView(regulateeCandidates);
		
		//initiate regulateeName Label
		regulateeNameLabel = new JLabel();
		regulateeNameLabel.setFont(new Font("Consolas", 3, 14));
		regulateeNameLabel.setText("Regulatee");
		regulateeNameLabel.setOpaque(false);
		
		//initiate regulatee Name textfield
		regulateeName = new JTextField();
		regulateeName.setFont(new Font("Consolas", 0, 12));
		regulateeName.setBorder(new LineBorder(new Color(224, 210, 181), 0,true));
		
		//initiate regulator candidates scrollpane
		regulatorCanScrollPane = new JScrollPane();
		regulatorCanScrollPane.setOpaque(false);
		regulatorCanScrollPane.setBorder(new LineBorder(
				new java.awt.Color(204, 204, 204), 0, true));
		regulatorCanScrollPane.setForeground(new Color(204, 204, 204));
		regulatorCanScrollPane.setViewportBorder(BorderFactory
				.createEtchedBorder());
		
		//initiate regulator candidates table
		Object[][] tableContents_4 = new Object[columnCount][2];
		regulatorCandidates = new JTable(columnCount,2){
			public boolean isCellEditable(int row, int column) { return false; }
		};
		regulatorCandidates.setModel(new DefaultTableModel(
				tableContents_4,new String[]{"Regulators","Relation"}
				));
		regulatorCandidates.setBorder(BorderFactory
				.createLineBorder(new Color(0,0,0),0));
		regulatorCandidates.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		regulatorCandidates.setFont(new Font("Consolas",0,12));
		regulatorCanScrollPane.setViewportView(regulatorCandidates);;
		
		//initiate search button
		searchBt = new JButton();
		searchBt.setBackground(new Color(153, 153, 153));
		searchBt.setIcon(new ImageIcon(SEARCHBUTTON));
		searchBt.setBorder(new LineBorder(new Color(224, 210, 181), 0,true));
		searchBt.setOpaque(false);
		searchBt.setToolTipText("Press this to present regulation relation");
		
		//initiate picture label
		pictureLabel = new JLabel();
		
		
		//set layout
		this.getLayeredPane().add(backLabel,new Integer(Integer.MIN_VALUE));
		backLabel.setBounds(0, 0, background.getIconWidth(),background.getIconHeight());
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		
		//initializing layout
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(51, 51, 51)
								.addComponent(regulatorScrollPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										128,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(7, 7, 7)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(
																		layout.createSequentialGroup()
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(
																						regulateeCanScrollPane,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						159,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(
																		layout.createSequentialGroup()
																				.addGap(62,
																						62,
																						62)
																				.addComponent(
																						regulatorNameLabel,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						78,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addComponent(
														regulatorName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														115,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														searchBt,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														100, Short.MAX_VALUE)
												.addComponent(
														pictureLabel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														100,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(22, 22,
																		22)
																.addComponent(
																		regulateeNameLabel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		93,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addGap(44, 44,
																		44))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		regulatorCanScrollPane,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		159,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(
														regulateeName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														115,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(regulateeScrollPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										114,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(54, 54, 54)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addGroup(
														javax.swing.GroupLayout.Alignment.LEADING,
														layout.createSequentialGroup()
																.addGap(34, 34,
																		34)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						regulatorNameLabel,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						27,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						regulateeNameLabel,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						27,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						regulatorName,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						37,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						pictureLabel,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						50,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						regulateeName,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						37,
																						javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(21,
																										21,
																										21)
																								.addGroup(
																										layout.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														regulatorCanScrollPane,
																														javax.swing.GroupLayout.Alignment.TRAILING,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														287,
																														Short.MAX_VALUE)
																												.addComponent(
																														regulateeCanScrollPane,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														287,
																														Short.MAX_VALUE)))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(33,
																										33,
																										33)
																								.addComponent(
																										searchBt,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										100,
																										javax.swing.GroupLayout.PREFERRED_SIZE))))
												.addGroup(
														layout.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
																.addComponent(
																		regulateeScrollPane,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		regulatorScrollPane,
																		javax.swing.GroupLayout.Alignment.TRAILING,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		398,
																		Short.MAX_VALUE)))
								.addGap(27, 27, 27)));




		((JPanel)getContentPane()).setOpaque(false);
		pack();
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/*------method to initiate regulator names and regulatee names--*/
	public void initNames(){
		for(int i = 0;i < numOfRegulator; i++){
			regulatorTable.setValueAt(regulatorNames.get(i), i, 0);
		}
		for(int i = 0;i < numOfRegulatee; i++){
			regulateeTable.setValueAt(regulateeNames.get(i), i, 0);
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*-----triggering functions: for regulator table------*/
	public static void triggerRegulatorEvents(int RowPoint){
		pictureLabel.setIcon(null);
		int numRow = regulateeCandidates.getRowCount();
		regulatorName.setText(null);
		
		for(int i = 0;i < numRow;i++){
			regulateeCandidates.setValueAt(null, i, 0);
			regulateeCandidates.setValueAt(null, i, 1);
		}
		Object[][] tableContents = new Object[columnCount][2];
		DefaultTableModel refreshModel = new DefaultTableModel(tableContents,new String[]{"Regulatees","Relation"});
		regulateeCandidates.setModel(refreshModel);
		
		int rowPoint = RowPoint;
		regulatorName.setText((String) regulatorTable.getValueAt(rowPoint,0));
		int allRegulateeCanRow = 0;
		for(int i = 0;i < numOfRegulatee; i++){
			int temp = database[i][rowPoint];
			if(temp == 1 || temp == -1 || temp == 2)
				allRegulateeCanRow++;
		}
		if(allRegulateeCanRow > 20){
			Object[][] tableContents_1 = new Object[allRegulateeCanRow][2];
			DefaultTableModel aNewModel = new DefaultTableModel(tableContents_1,new String[]{"Regulatees","Relation"});
			regulateeCandidates.setModel(aNewModel);
		}
		int newRegulateeCanRow = 0;
		for(int i = 0;i < numOfRegulatee; i++){
			int temp = database[i][rowPoint];
			switch(temp){
			case 1: 
				regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
				regulateeCandidates.setValueAt("+", newRegulateeCanRow, 1);
				newRegulateeCanRow ++;
				break;
			case -1:
				regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
				regulateeCandidates.setValueAt("-", newRegulateeCanRow, 1);
				newRegulateeCanRow ++;
				break;
			case 2:
				regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
				regulateeCandidates.setValueAt("+-", newRegulateeCanRow, 1);
				newRegulateeCanRow ++;
				break;
			default:
				break;
			}
		}		
	}
	
	/*------triggering functions: for regulatee table ----*/
	public static void triggerRegulateeEvents(int RowPoint){
		pictureLabel.setIcon(null);
		int numRow = regulatorCandidates.getRowCount();
		for(int i = 0;i < numRow;i++){
			regulatorCandidates.setValueAt(null, i, 0);
			regulatorCandidates.setValueAt(null, i, 1);
		}
		Object[][] tableContents = new Object[columnCount][2];
		DefaultTableModel refreshModel = new DefaultTableModel(tableContents,new String[]{"Regulators","Relation"});
		regulatorCandidates.setModel(refreshModel);
		regulateeName.setText(null);
		
		int rowPoint = RowPoint;
		regulateeName.setText((String) regulateeTable.getValueAt(rowPoint,0));
		int allRegulatorCanRow = 0;
		for(int i = 0;i < numOfRegulator; i++){
			int temp = database[rowPoint][i];
			if(temp == 1 || temp == -1 || temp == 2)
				allRegulatorCanRow++;
		}
		if(allRegulatorCanRow > 20){
			Object[][] tableContents_1 = new Object[allRegulatorCanRow][2];
			DefaultTableModel aNewModel = new DefaultTableModel(tableContents_1,new String[]{"Regulators","Relation"});
			regulatorCandidates.setModel(aNewModel);
		};
		int newRegulatorCanRow = 0;
		for(int i = 0;i < numOfRegulator; i++){
			int temp = database[rowPoint][i];
			switch(temp){
			case 1: 
				regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
				regulatorCandidates.setValueAt("+", newRegulatorCanRow, 1);
				newRegulatorCanRow ++;
				break;
			case -1:
				regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
				regulatorCandidates.setValueAt("-", newRegulatorCanRow, 1);
				newRegulatorCanRow ++;
				break;
			case 2:
				regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
				regulatorCandidates.setValueAt("+-", newRegulatorCanRow, 1);
				newRegulatorCanRow ++;
				break;
			default:
				break;
			}
		}
	}
	
	
	/*-----triggering function: regulator candidates table-------*/
	
	public static void triggerRegulatorCanEvents(int RowPoint){
		int rowPoint = RowPoint;
		pictureLabel.setIcon(null);
		if(regulatorCandidates.getValueAt(rowPoint, 0)==null)
			return;
		
		int numRow = regulateeCandidates.getRowCount();
		for(int i = 0;i < numRow;i++){
			regulateeCandidates.setValueAt(null, i, 0);
			regulateeCandidates.setValueAt(null, i ,1);
		}
		regulatorName.setText(null);
		Object[][] tableContents = new Object[columnCount][2];
		DefaultTableModel refreshModel = new DefaultTableModel(tableContents, new String[]{"Regulatees","Relation"});
		regulateeCandidates.setModel(refreshModel);
		
		String selectedRegulator = (String) regulatorCandidates.getValueAt(rowPoint, 0);
		regulatorName.setText(selectedRegulator);
		int itsPosition = 0;
		for(int i = 0;i < numOfRegulator;i++){
			if(selectedRegulator == regulatorNames.get(i)){
				itsPosition = i;
				break;
			}
		}
		int allRegulateeCanRow = 0;
		for(int i = 0;i < numOfRegulatee; i++){
			int temp = database[i][itsPosition];
			if(temp == 1 || temp == -1 || temp == 2)
				allRegulateeCanRow++;
		}
		if(allRegulateeCanRow > 20){
			Object[][] tableContents_1 = new Object[allRegulateeCanRow][2];
			DefaultTableModel aNewModel = new DefaultTableModel(tableContents_1,new String[]{"Regulatees","Relation"});
			regulateeCandidates.setModel(aNewModel);
		}
		int newRegulateeCanRow = 0;
		for(int i = 0;i < numOfRegulatee;i++){
			int temp = database[i][itsPosition];
			switch(temp){
			case 1: 
				regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
				regulateeCandidates.setValueAt("+", newRegulateeCanRow, 1);
				newRegulateeCanRow ++;
				break;
			case -1:
				regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
				regulateeCandidates.setValueAt("-", newRegulateeCanRow, 1);
				newRegulateeCanRow ++;
				break;
			case 2:
				regulateeCandidates.setValueAt(regulateeNames.get(i), newRegulateeCanRow, 0);
				regulateeCandidates.setValueAt("+-", newRegulateeCanRow, 1);
				newRegulateeCanRow ++;
				break;
			default:
				break;
			}
		}
	}
	
	/*------triggering function: regulatee candidates table------*/
	public static void triggerRegulateeCanEvents(int RowPoint){
		int rowPoint = RowPoint;
		pictureLabel.setIcon(null);
		if(regulateeCandidates.getValueAt(rowPoint, 0)==null)
			return;
		
		int numRow = regulatorCandidates.getRowCount();
		regulateeName.setText(null);
		for(int i = 0;i < numRow; i++){
			regulatorCandidates.setValueAt(null, i, 0);
			regulatorCandidates.setValueAt(null, i, 1);
		}
		Object[][] tableContents = new Object[columnCount][2];
		DefaultTableModel refreshModel = new DefaultTableModel(tableContents,new String[]{"Regulators","Relation"});
		regulatorCandidates.setModel(refreshModel);
		
		String selectedRegulatee = (String) regulateeCandidates.getValueAt(rowPoint, 0);
		regulateeName.setText(selectedRegulatee);
		int itsPosition = 0;
		for(int i = 0;i < numOfRegulatee;i++){
			if(selectedRegulatee == regulateeNames.get(i)){
				itsPosition = i;
				break;
			}
		}
		int allRegulatorCanRow = 0;
		for(int i = 0;i < numOfRegulator; i++){
			int temp = database[itsPosition][i];
			if(temp == 1 || temp == -1 || temp == 2)
				allRegulatorCanRow++;
		}
		if(allRegulatorCanRow > 20){
			Object[][] tableContents_1 = new Object[allRegulatorCanRow][2];
			DefaultTableModel aNewModel = new DefaultTableModel(tableContents_1,new String[]{"Regulators","Relation"});
			regulatorCandidates.setModel(aNewModel);
		}
		int newRegulatorCanRow = 0;
		for(int i = 0;i < numOfRegulator;i++){
			int temp = database[itsPosition][i];
			switch(temp){
			case 1: 
				regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
				regulatorCandidates.setValueAt("+", newRegulatorCanRow, 1);
				newRegulatorCanRow ++;
				break;
			case -1:
				regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
				regulatorCandidates.setValueAt("-", newRegulatorCanRow, 1);
				newRegulatorCanRow ++;
				break;
			case 2:
				regulatorCandidates.setValueAt(regulatorNames.get(i), newRegulatorCanRow, 0);
				regulatorCandidates.setValueAt("+-", newRegulatorCanRow, 1);
				newRegulatorCanRow ++;
				break;
			default:
				break;
			}
		}
	}
	
	/*------triggering functions: search Button------------*/
	public static void triggerSearchBt(){
		String regulator = regulatorName.getText();
		String regulatee = regulateeName.getText();
		if(regulator == null){
			JOptionPane.showMessageDialog(null,"Please Enter Regulator Name!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		if(regulatee == null){
			JOptionPane.showMessageDialog(null,"Please Enter Regulatee Name!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		int regulatorPosition = 0;
		Boolean haveThisRegulator = false;
		int regulateePosition = 0;
		Boolean haveThisRegulatee = false;
		for(int i = 0;i < numOfRegulator;i++){
			if(regulator.equals(regulatorNames.get(i))){
				regulatorPosition = i;
				haveThisRegulator = true;
				break;
			}
		}
		for(int i = 0;i < numOfRegulatee;i++){
			if(regulatee.equals(regulateeNames.get(i))){
				regulateePosition = i;
				haveThisRegulatee = true;
				break;
			}
		}
		
		if(haveThisRegulator == false || haveThisRegulatee == false){
			JOptionPane.showMessageDialog(null,"No Search Result In This Database!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		int temp = database[regulateePosition][regulatorPosition];
		switch(temp){
		case 1:
			//JOptionPane.showMessageDialog(null,"Positive Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			pictureLabel.setIcon(null);
			pictureLabel.setIcon(new ImageIcon(POSITIVE));
			break;
		case -1:
			//JOptionPane.showMessageDialog(null,"Negative Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			pictureLabel.setIcon(null);
			pictureLabel.setIcon(new ImageIcon(NEGTIVE));
			break;
		case 2:
			//JOptionPane.showMessageDialog(null,"Positive&Negative Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			pictureLabel.setIcon(null);
			pictureLabel.setIcon(new ImageIcon(POSANDNEG));
			break;
		case -2:
			//JOptionPane.showMessageDialog(null,"Unknown Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			pictureLabel.setIcon(null);
			pictureLabel.setIcon(new ImageIcon(UNKNOWN));
			break;
		default:
			//JOptionPane.showMessageDialog(null,"No Regulation","Result",JOptionPane.PLAIN_MESSAGE);
			pictureLabel.setIcon(null);
			pictureLabel.setIcon(new ImageIcon(NORELATION));
			break;
		}
		
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	/*------method to initiate all the table events----*/
	public void initTableEvents(){
		
	/*--------initializing regulator table events---------------*/	
		//1、initiate regulator table mouse events
		regulatorTable.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerRegulatorEvents(regulatorTable.getSelectedRow());
			}
		});
		
		//2、initiate regulator table key events
		regulatorTable.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN && I.getKeyCode() != KeyEvent.VK_ENTER)
					return;
				int rowPoint = regulatorTable.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_ENTER && rowPoint == regulatorTable.getRowCount() - 1)
					triggerRegulatorEvents(0);
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulatorEvents(regulatorTable.getSelectedRow() - 1);
				}
				else if((I.getKeyCode() == KeyEvent.VK_DOWN || I.getKeyCode() == KeyEvent.VK_ENTER) && rowPoint < numOfRegulator - 1){
					triggerRegulatorEvents(regulatorTable.getSelectedRow() + 1);
				}
			}
		});
		
	/*--------initializing regulatee table events---------------*/	
		//1、initiate regulatee mouse table events
		regulateeTable.addMouseListener(new MouseAdapter(){
			//public void valueChanged(ListSelectionEvent e){
			public void mousePressed(MouseEvent e){
				triggerRegulateeEvents(regulateeTable.getSelectedRow());
			}
		}
		);
		 
		
		//2、initiate regulatee table key events
		regulateeTable.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN && I.getKeyCode() != KeyEvent.VK_ENTER)
					return;
				int rowPoint = regulateeTable.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_ENTER && rowPoint == regulateeTable.getRowCount() - 1)
					triggerRegulateeEvents(0);
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulateeEvents(rowPoint - 1);
				}
				else if((I.getKeyCode() == KeyEvent.VK_DOWN || I.getKeyCode() == KeyEvent.VK_ENTER) && rowPoint < numOfRegulatee - 1){
					triggerRegulateeEvents(rowPoint + 1);
				}
			}
		});
		
		
	/*-----------initiating regulator candidates table events---------*/
		//1、initiate regulator candidates table mouse events
		regulatorCandidates.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerRegulatorCanEvents(regulatorCandidates.getSelectedRow());
			}
		}
		);
		//2、initiate regulator candidates table key events
		regulatorCandidates.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN && I.getKeyCode() != KeyEvent.VK_ENTER)
					return;
				int rowPoint = regulatorCandidates.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_ENTER && rowPoint == regulatorCandidates.getRowCount() - 1)
					triggerRegulatorCanEvents(0);
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulatorCanEvents(rowPoint - 1);
				}
				else if((I.getKeyCode() == KeyEvent.VK_DOWN || I.getKeyCode() == KeyEvent.VK_ENTER) && rowPoint < regulatorCandidates.getRowCount() - 1){
					if(regulatorCandidates.getValueAt(rowPoint+1, 0)==null){
						regulatorName.setText(null);
						Object[][] tableContents = new Object[columnCount][2];
						DefaultTableModel refreshModel = new DefaultTableModel(tableContents,new String[]{"Regulatees","Relation"});
						regulateeCandidates.setModel(refreshModel);
						return;
					}		
					triggerRegulatorCanEvents(rowPoint + 1);
				}
			}
		}
		);
		
	/*-------------initiating regulatee candidates table events----------*/	
		//1、initiate regulatee candidates table mouse events
		regulateeCandidates.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerRegulateeCanEvents(regulateeCandidates.getSelectedRow());
			}
		}
		);
		//2、initiate regulatee candidates table key events
		regulateeCandidates.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_UP && I.getKeyCode() != KeyEvent.VK_DOWN && I.getKeyCode() != KeyEvent.VK_ENTER)
					return;
				int rowPoint = regulateeCandidates.getSelectedRow();
				if(I.getKeyCode() == KeyEvent.VK_ENTER && rowPoint == regulateeCandidates.getRowCount() - 1)
					triggerRegulateeCanEvents(0);
				if(I.getKeyCode() == KeyEvent.VK_UP && rowPoint >= 1){
					triggerRegulateeCanEvents(rowPoint - 1);
				}
				else if((I.getKeyCode() == KeyEvent.VK_DOWN || I.getKeyCode() == KeyEvent.VK_ENTER) && rowPoint < regulateeCandidates.getRowCount() - 1){
					if(regulateeCandidates.getValueAt(rowPoint+1, 0)==null){
						regulateeName.setText(null);
						Object[][] tableContents = new Object[columnCount][2];
						DefaultTableModel refreshModel = new DefaultTableModel(tableContents,new String[]{"Regulators","Relation"});
						regulatorCandidates.setModel(refreshModel);
						return;
					}
					triggerRegulateeCanEvents(rowPoint + 1);
				}
			}
		}
		);
		//end of the function
		
		
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*-------------------initializing button events---------*/
	public void initButtonEvents(){
		//1、initializing search button mouse events
		searchBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerSearchBt();
			}
		}
		);
		//2、initializing search button key events
		searchBt.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() != KeyEvent.VK_ENTER)
					return;
				triggerSearchBt();
			}
		}
		);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*--------------------initializing text events----------*/
	public void initTextEvents(){
		regulatorName.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				pictureLabel.setIcon(null);
				Object[][] tableContents = new Object[columnCount][2];
				DefaultTableModel refreshModel = new DefaultTableModel(tableContents,new String[]{"Regulatees","Relation"});
				regulateeCandidates.setModel(refreshModel);
			}
		}
		);
		regulateeName.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				pictureLabel.setIcon(null);
				Object[][] tableContents = new Object[columnCount][2];
				DefaultTableModel refreshModel = new DefaultTableModel(tableContents,new String[]{"Regulators","Relation"});
				regulatorCandidates.setModel(refreshModel);
			}
		}
		);
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*------variables in the class--------------------*/
	//variables for initiating components
	private static Vector<String> regulatorNames;
	private static Vector<String> regulateeNames;
	private static int numOfRegulator;
	private static int numOfRegulatee;
	private static int[][] database;
	
	
	//variables for initiating gui components
	private static JTable regulatorTable;
	private static JScrollPane regulatorScrollPane;
	private static JTable regulateeTable;
	private static JScrollPane regulateeScrollPane;
	private static JTable regulateeCandidates;
	private static JScrollPane regulateeCanScrollPane;
	private static JTable regulatorCandidates;
	private static JScrollPane regulatorCanScrollPane;
	private static JTextField regulatorName;
	private static JTextField regulateeName;
	private static JButton searchBt;
	
	private static JLabel regulatorNameLabel;
	private static JLabel regulateeNameLabel;
	private static JLabel pictureLabel;
	
	private static final int columnCount = 20;
	
	//variables for initiating gui pictures
	private static final String PATH = "images/";
	private static final String POSITIVE = PATH + "positive.png";
	private static final String NEGTIVE = PATH + "negtive.png";
	private static final String POSANDNEG = PATH + "positive and negtive.png";
	private static final String UNKNOWN = PATH + "unknown.png";
	private static final String NORELATION = PATH + "norelation.png";
	private static final String BACKGROUND = PATH + "searchBack.png";
	private static final String SEARCHBUTTON = PATH + "searchButton.png";
	private static ImageIcon background = new ImageIcon(BACKGROUND);
	private static JLabel backLabel = new JLabel(background);

}