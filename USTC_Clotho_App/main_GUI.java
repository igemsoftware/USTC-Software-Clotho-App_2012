/*---------------------------------------------------*/
// This java class is for USTC Clotho App            //
// Author: Francis Chen                              //
// Usage: This class is mainly for main GUI          //
// Copyrights Reserved                               //
/*---------------------------------------------------*/
package USTC_Clotho_App;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class main_GUI{

	public main_GUI(){
		try {
			loadDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Unable To Load The Database","Error!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		//method to initiate main GUI components
		initComponents();
		//method to initiate main frame events
		initFrameEvents();
		//method to initiate tab panel events
		initTabEvents();
		//method to initiate text field events
		initTextEvents();
		//method to initiate all the button events
		initButtonEvents();
		//method to initiate file menu events
		initFileMenuEvents();
		//method to initiate search menu events
		initSearchMenuEvents();
		//method to initiate help menu events
		initHelpMenuEvents();
		//method to initiate about menu events
		initAboutMenuEvents();
		//method to initiate popup menu events
		initPopupMenuEvents();
	}

	
	/*-----main function of the class------*/
	public static void main(String args[]){

		new main_GUI();
		//System.out.print(aTest.modeTwoBt.getWidth()+"\t"+aTest.modeTwoBt.getHeight());	
	}

		
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	/*-----------method to initiate the mainFrame GUI--------*/
	
	@SuppressWarnings("serial")
	private void initComponents(){
		/*---------Initializing mainFrame and its container---*/
		mainFrame = new JFrame("Regulon Lib");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container contentPane = mainFrame.getContentPane();
		
		/*------Initializing main panel---------*/
		panel_1 = new JPanel(){
			public void paintComponent(Graphics g){
				g.drawImage(panelbackground.getImage(),0,0,getSize().width,getSize().height,this);
			}
		};

		/*-----Initializing JTable----------*/
		
		inputMatrixTable = new JTable();
		Object[][] tableContents = new Object[tableSize][tableSize];
		for(int i = 0;i < tableSize; i++){
			for(int j = 0;j < tableSize;j++){
				tableContents[i][j] = null;
			}
		}
		String[] tableColumnString = new String[tableSize];
		for(int i = 0; i < tableSize; i++){
			tableColumnString[i] = "regulator " + (i+1);
		}
		
		//initiate inputMatrixTable
		DefaultTableModel aNewTable = new DefaultTableModel(tableContents,tableColumnString);
		inputMatrixTable.setModel(aNewTable);
		inputMatrixTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		inputMatrixTable.setGridColor(new Color(102, 102, 102));
		inputMatrixTable.setBackground(new Color(255,255,255));
		inputMatrixTable.setRowHeight(25);
		for(int i = 0;i < inputMatrixTable.getColumnCount();i++){
			inputMatrixTable.getColumnModel().getColumn(i).setPreferredWidth(75);
		}
		inputMatrixTable.setSelectionBackground(new Color(204, 204, 204));
		inputMatrixTable.setToolTipText("Please input regulation in the matrix");
		JComboBox matrixElement = new JComboBox();
		matrixElement.addItem("");
		matrixElement.addItem("0");
		matrixElement.addItem("+");
		matrixElement.addItem("-");
		
		
		/*------add JComcoBox to  each elements-----*/
		for(int i = 0; i < 10; i++){
			inputMatrixTable.getColumnModel().getColumn(i).setCellEditor(new DefaultCellEditor(matrixElement));
		}

		//initiate matrixScroll
		matrixScrollPane = new JScrollPane(); 
		matrixScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		matrixScrollPane.setViewportView(inputMatrixTable);
		matrixScrollPane.setOpaque(false);
		matrixScrollPane.setBorder(new LineBorder(new Color(224, 210, 181), 0,true));
		//initiate matrixSizeLabel
		matrixSizeLabel = new JLabel();
		matrixSizeLabel.setText("Matrix Size");
		matrixSizeLabel.setFont(new Font("Consolas", 1, 14));
		
		//initiate USTClogo label and input size text field
		USTClogo = new JLabel();
		sizeInput = new JTextField();
		sizeInput.setBorder(new LineBorder(new Color(224, 210, 181), 0,true));
		
		/*------initializing JMenu "File"----*/
		File = new JMenu("File");
		File.setForeground(new Color(255, 255, 255));
		
		New = new JMenuItem("New",'N');
		save = new JMenuItem("Save",'S');
		save.setEnabled(false);
		clear = new JMenuItem("Clear",'C');
		exit = new JMenuItem("Exit",'E');
		File.add(New);
		File.add(save);
		File.add(clear);
		File.add(exit);
		
		/*------initializing JMenu "Search"----*/
		Search = new JMenu("Search");
		Search.setForeground(new Color(255, 255, 255));
		Operon_Operon = new JMenuItem("Search in Operon-Operon");
		Gene_Promoter = new JMenuItem("Search in Gene-Promoter");
		Search.add(Operon_Operon);
		Search.add(Gene_Promoter);
		
		/*-------initializing JMenu "Help"----*/
		Help = new JMenu("Help");
		Help.setForeground(new Color(255, 255, 255));
		howToUse = new JMenuItem("How To Use");
		aboutDatabase = new JMenu("About Database");
		Help.add(howToUse);
		Help.add(aboutDatabase);
		
		/*------initializing JMenu "About"----*/
		About = new JMenu("About");
		About.setForeground(new Color(255, 255, 255));
		aboutUSTC_2012 = new JMenuItem("About 2012 USTC-Software Team");
		aboutUSTC = new JMenuItem("About USTC");
		About.add(aboutUSTC_2012);
		About.add(aboutUSTC);
		
		/*------initializing JPopupMenu "aNewMenu"-----*/
		aNewMenu = new JPopupMenu();
		save_1 = new JMenuItem("save");
		New_1 = new JMenuItem("new");
		clear_1 = new JMenuItem("clear");
		exit_1 = new JMenuItem("exit");
		aNewMenu.add(save_1);
		aNewMenu.add(New_1);
		aNewMenu.add(clear_1);
		aNewMenu.add(exit_1);
		
		/*------initializing JMenuBar mainMenu-------*/
		mainMenu = new JMenuBar();
		mainMenu.add(File);
		mainMenu.add(Search);
		mainMenu.add(Help);
		mainMenu.add(About);
		mainMenu.setBackground(new Color(150,143,124));
		
		/*-----initializing  Buttons---------*/
		commitSizeBt = new JButton();
		//commitSizeBt.setText("confirm");
		commitSizeBt.setBackground(new Color(153, 153, 153));
		commitSizeBt.setFont(new java.awt.Font("Consolas", 1, 14));
		commitSizeBt.setBorder(new javax.swing.border.LineBorder(
				new Color(204, 204, 204), 0, true));
		commitSizeBt.setToolTipText("Press it to confirm matrix size");
		commitSizeBt.setOpaque(false);
		commitSizeBt.setIcon(confirmbackground);
		
		modeOneBt = new JButton();
		modeOneBt.setBackground(new Color(153, 153, 153));
		modeOneBt.setFont(new java.awt.Font("Consolas", 1, 14));
		//modeOneBt.setText("Mode 1");
		modeOneBt.setBorder(new javax.swing.border.LineBorder(
				new Color(204, 204, 204), 0, true));
		modeOneBt.setToolTipText("Press it to search in Operon-Operon database");
		modeOneBt.setIcon(modeonebackground);
		modeOneBt.setOpaque(false);
		
		
		modeTwoBt = new JButton();
		modeTwoBt.setBackground(new Color(153, 153, 153));
		modeTwoBt.setFont(new java.awt.Font("Consolas", 1, 14));
		//modeTwoBt.setText("Mode 2");
		modeTwoBt.setBorder(new javax.swing.border.LineBorder(
				new Color(204, 204, 204), 0, true));
		modeTwoBt.setToolTipText("Press it to search in Gene-Promter database");
		modeTwoBt.setOpaque(false);
		modeTwoBt.setIcon(modetwobackground);
		
		/*------initializing labels--------*/
		modeOneLabel = new JLabel();
		modeOneLabel.setText("Using Operon-Operon");
		modeTwoLabel = new JLabel();
		modeTwoLabel.setText("Using Gene-Promoter");
		USTClogo = new JLabel();
		USTClogo.setIcon(new ImageIcon(USTCLOGO));

		/*------adding to panel_1--------*/
		GroupLayout layout = new GroupLayout(panel_1);
		panel_1.setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(53, 53,
																		53)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(37,
																										37,
																										37)
																								.addComponent(
																										matrixSizeLabel)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										sizeInput,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										83,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(18,
																										18,
																										18)
																								.addComponent(
																										commitSizeBt,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										95,
																										javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addComponent(
																						USTClogo,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						450,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						matrixScrollPane)))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(87,
																										87,
																										87)
																								.addComponent(
																										modeOneBt))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addGap(65,
																										65,
																										65)
																								.addComponent(
																										modeOneLabel,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										140,
																										javax.swing.GroupLayout.PREFERRED_SIZE)))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		123,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										modeTwoLabel)
																								.addGap(50,
																										50,
																										50))
																				.addGroup(
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						layout.createSequentialGroup()
																								.addComponent(
																										modeTwoBt)
																								.addGap(74,
																										74,
																										74)))))
								.addContainerGap(32,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(USTClogo,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										90,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														commitSizeBt,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														27,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														sizeInput,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														27,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														matrixSizeLabel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														30,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(matrixScrollPane,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										222,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														modeOneLabel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														28,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														modeTwoLabel,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														28,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(modeOneBt)
												.addComponent(modeTwoBt))
								.addContainerGap(51, Short.MAX_VALUE)));

		/*------adding to mainTabbedPane-----*/
		mainTabbedPane = new JTabbedPane();

		mainTabbedPane.setTabPlacement(JTabbedPane.TOP);
		mainTabbedPane.addTab("Main Page", panel_1);
		mainTabbedPane.setBackground(new Color(150,143,124));
		mainFrame.setJMenuBar(mainMenu);
		contentPane.add(mainTabbedPane,BorderLayout.CENTER);
		mainFrame.setSize(600,400);
		mainFrame.pack();
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
		mainFrame.getLayeredPane().add(framebackLabel,new Integer(Integer.MIN_VALUE));
		framebackLabel.setBounds(0, 0, framebackground.getIconWidth(),framebackground.getIconHeight());
		((JPanel)mainFrame.getContentPane()).setOpaque(false);
		panel_1.getRootPane().setOpaque(false);
		//initiate string buffers
		outPutBuffers = new Vector<StringBuffer>();
		for(int i = 0; i < 1000; i++){
			outPutBuffers.add(null);
		}
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*-------------method to load the Operon_Operon database-------*/
	
	public void loadDataBase() throws IOException{

		/*---------First Part: Loading Operon_Operon Database-------*/
		
		//-----------------read file-----------------------//
		FileReader infile1 = new FileReader(PATHFORPARTS);
		BufferedReader in1 = new BufferedReader(infile1);
		
		
		//----- initializing numOfOperons-----------//
		numOfOperons = Integer.parseInt(in1.readLine());
		
		
		//--------------initializing operonNames-----//
		operonNames = new Vector<String>();
		for(int i = 0;i < numOfOperons; i++){
			operonNames.add(in1.readLine());
		}
		
				
		//---------------------generate database------------------//
		operonDataBase = new int[numOfOperons][numOfOperons];
		for(int i = 0;i < numOfOperons; i++){
			for(int j = 0;j < numOfOperons;j++){
				operonDataBase[i][j] = Integer.parseInt(in1.readLine());
			}
		}
		in1.close();
		
		
		/*--------Second part: Loading Gene_Promoter Database------*/
		
		//-----------------read file-----------------------//
		FileReader infile2 = new FileReader(PATHFORBIOBRICKS);
		BufferedReader in2 = new BufferedReader(infile2);
		
		//------------- initializing numOfGenes and numOfPromoters-------//
		numOfGenes = Integer.parseInt(in2.readLine());
		numOfPromoters = Integer.parseInt(in2.readLine());
		
		
		//------------------initializing geneNames--------------//
		geneNames = new Vector<String>();
		for(int i = 0;i < numOfGenes; i++){
			geneNames.add(in2.readLine());
		}
		
		
		//------------------initializing promoterNames-------------//
		promoterNames = new Vector<String>();
		for(int i = 0;i < numOfPromoters;i++){
			promoterNames.add(in2.readLine());
		}
		
		//--------------------generate database------------------//
		genepromoterDataBase = new int[numOfPromoters][numOfGenes];
		for(int i = 0;i < numOfPromoters; i++){
			for(int j = 0;j < numOfGenes;j++){
				genepromoterDataBase[i][j] = Integer.parseInt(in2.readLine());
			}
		}
		in2.close();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*------special function for confirm button and file menu---------*/
	public void triggerConfirm(){
		if(sizeInput.getText().equals("")){
			//System.out.print("Please Input The Matrix Size!\n");
			JOptionPane.showMessageDialog(null,"Please Input The Matrix Size!","Warning",JOptionPane.WARNING_MESSAGE);
			return;
		}
		int length = sizeInput.getText().length();
		for(int i = 0;i < length; i++){
			if(sizeInput.getText().charAt(i) > 57 || sizeInput.getText().charAt(i) < 48){
				JOptionPane.showMessageDialog(null,"Wrong Input!","Error",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		if(Integer.parseInt(sizeInput.getText()) > 10){
			JOptionPane.showMessageDialog(null,"Out Of Range!","Error",JOptionPane.ERROR_MESSAGE);
		}
		matrixsize = Integer.parseInt(sizeInput.getText());
		int numRow = inputMatrixTable.getRowCount();
		int numColumn = inputMatrixTable.getColumnCount();
		@SuppressWarnings("serial")
		DefaultTableCellRenderer whiteRenderer = new DefaultTableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table,  
			Object value, boolean isSelected, boolean hasFocus,  
			int row, int column) { 
			Component   cell   =   super.getTableCellRendererComponent   
			(table,   value,   isSelected,   hasFocus,   row,   column); 
			cell.setBackground(Color.WHITE);
			return   cell; 
			} 
		}; 
		for(int i = 0;i < numColumn;i++){
			inputMatrixTable.getColumnModel().getColumn(i).setCellRenderer(whiteRenderer);
;				}
		
		for(int i = 0;i < numRow;i++){
			for(int j = 0;j < numColumn;j++){
				inputMatrixTable.setValueAt(null, i, j);
				
			}
		}
		@SuppressWarnings("serial")
		DefaultTableCellRenderer grayRenderer = new DefaultTableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table,  
			Object value, boolean isSelected, boolean hasFocus,  
			int row, int column) { 
			Component   cell   =   super.getTableCellRendererComponent   
			(table,   value,   isSelected,   hasFocus,   row,   column); 
			if(row < matrixsize && column < matrixsize){
				cell.setBackground(new Color(201,160,96));
			}
			else
				cell.setBackground(Color.WHITE);
			return   cell; 
			} 
		}; 
		for(int i = 0;i < numColumn;i++){
			inputMatrixTable.getColumnModel().getColumn(i).setCellRenderer(grayRenderer);
		}
		//System.out.print(matrixsize+"\n\n");
		
	}
	
	/*--------trigger functions: trigger new event for main menu and popup menu---*/
	public void triggerNew(){
		int numOfTabs = mainTabbedPane.getTabCount();
		if(numOfTabs == 1){
			save.setEnabled(false);
		}
		mainTabbedPane.setSelectedComponent(panel_1);
		sizeInput.setText(null);
		int numRow = inputMatrixTable.getRowCount();
		int numColumn = inputMatrixTable.getColumnCount();
		for(int i = 0; i < numRow; i++){
			for(int j = 0; j < numColumn; j++){
				inputMatrixTable.setValueAt(null,i, j);
			}
		}
		@SuppressWarnings("serial")
		DefaultTableCellRenderer whiteRenderer = new DefaultTableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table,  
			Object value, boolean isSelected, boolean hasFocus,  
			int row, int column) { 
			Component   cell   =   super.getTableCellRendererComponent   
			(table,   value,   isSelected,   hasFocus,   row,   column); 
			cell.setBackground(Color.WHITE);
			return   cell; 
			} 
		}; 
		for(int i = 0; i < numColumn;i++){
			inputMatrixTable.getColumnModel().getColumn(i).setCellRenderer(whiteRenderer);
		}
	}
	/*--------trigger functions: trigger save event for main menu and popup menu---*/
	public void triggerSave(){
		int numOfTabs = mainTabbedPane.getTabCount();
		int currentTab = 0;
		for(int i = 0; i < numOfTabs; i++){
			if(mainTabbedPane.getComponent(i) == mainTabbedPane.getSelectedComponent()){
				currentTab = i;
				break;
			}
		}
		if(numOfTabs == 1){
			JOptionPane.showMessageDialog(null,"No Result To Be Saved!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(currentTab == 0){
			JOptionPane.showMessageDialog(null,"Please Turn To The Result Tab!\n","Warning!",JOptionPane.WARNING_MESSAGE);
			return;
		}
		
		String currentTitle = mainTabbedPane.getTitleAt(currentTab);
		String temp = String.valueOf(currentTitle.charAt(currentTitle.length()-1));
		int currentIndex = Integer.parseInt(temp);
		
		JFileChooser saveFile = new JFileChooser();
		//saveFile.setFileSelectionMode(JFileChooser.SAVE_DIALOG);
		saveFile.setApproveButtonText("Save");
		String defaultFileName = currentTitle + ".txt";
		saveFile.setSelectedFile(new File(defaultFileName));
		saveFile.setFileHidingEnabled(false);
		saveFile.setFileFilter(new FileFilter(){
			@Override
			public String getDescription(){ 
				return "*.dat";
			}

			@Override
			public boolean accept(File f) {
				return true;
			}
		}
		);
		saveFile.addChoosableFileFilter(new FileFilter(){
			@Override
			public String getDescription(){ 
				return "*.txt";
			}

			@Override
			public boolean accept(java.io.File f) {
				// TODO Auto-generated method stub
				return true;
			}
		}
		);
		int result = saveFile.showDialog(null,"Save");
		if (result == JFileChooser.CANCEL_OPTION)
			return;
		File targetFile = saveFile.getSelectedFile(); 
		String path = targetFile.getPath(); 
		Boolean existFile = true;
		try {
			@SuppressWarnings("unused")
			FileReader tempReader = new FileReader(path);
			
		} catch (FileNotFoundException e) {
			existFile = false;
		}
		
		if(existFile){
			JOptionPane.showMessageDialog(null,"The File Already Exists!","Error!",JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		//String fileName = saveFile.getName(targetFile);
		File newFile = new File(path);
		try {
			FileWriter out = new FileWriter(newFile);
			out.write(outPutBuffers.get(currentIndex).toString());
			out.close();
		}
		catch (IOException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,"Failed to save the file!","Error!",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/*--------trigger functions: trigger clear event for main menu and popup menu---*/
	public void triggerClear(){
		sizeInput.setText(null);
		save.setEnabled(false);
		index = 1;
		for(int i = 0;i < outPutBuffers.size();i++){
			outPutBuffers.setElementAt(null, i);
		}
		int numRow = inputMatrixTable.getRowCount();
		int numColumn = inputMatrixTable.getColumnCount();
		for(int i = 0; i < numRow; i++){
			for(int j = 0; j < numColumn; j++){
				inputMatrixTable.setValueAt(null,i, j);
			}
		}
		int numOfTabs = mainTabbedPane.getTabCount();
		if(numOfTabs <= 1)
			return;
		else{
			for(int i = numOfTabs-1; i >= 1; i--){
				mainTabbedPane.remove(i);
			}
		}
		@SuppressWarnings("serial")
		DefaultTableCellRenderer whiteRenderer = new DefaultTableCellRenderer() { 
			public Component getTableCellRendererComponent(JTable table,  
			Object value, boolean isSelected, boolean hasFocus,  
			int row, int column) { 
			Component   cell   =   super.getTableCellRendererComponent   
			(table,   value,   isSelected,   hasFocus,   row,   column); 
			cell.setBackground(Color.WHITE);
			return   cell; 
			} 
		}; 
		for(int i = 0; i < numColumn;i++){
			inputMatrixTable.getColumnModel().getColumn(i).setCellRenderer(whiteRenderer);
		}
	}
	/*--------trigger functions: trigger exit event for main menu and popup menu----*/
	public void triggerExit(){
		System.exit(0);
	}

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*------------method to initiate button events---------*/
	public void initButtonEvents(){
		 
		//initiate commit size button, press it to enter the matrix size//
		commitSizeBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerConfirm();
			}
		});
		
		
		//initiate mode One button, press it to use mode one//
		modeOneBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
	
				if(matrixsize == 0){
					//System.out.println("Please Input Matrix Size First!\n");
					JOptionPane.showMessageDialog(null,"Please Input Matrix Size First!\n","Error!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				final int[][] TargetMatrix = new int[matrixsize][matrixsize];
				
				for(int i = 0; i < matrixsize;i++){
					for(int j = 0;j < matrixsize;j++){
						String element = (String)inputMatrixTable.getValueAt(i,j);
						if(element == "+"){
							TargetMatrix[i][j] = 1;
						}
						else if(element == "-"){
							TargetMatrix[i][j] = -1;
						}
						else if(element == "0"){
							TargetMatrix[i][j] = 0;
						}
						else{
							JOptionPane.showMessageDialog(null,"Please Enter Matrix In The Right Place!\n","Error!",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//System.out.print(TargetMatrix[i][j]+"\t");	
					}
					//System.out.print("\n");
				}
			
				try {
					Operon_Operon aTest = new Operon_Operon(matrixsize,
														TargetMatrix,
														operonDataBase,
														numOfOperons,
														operonNames);
					StringBuffer aBuffer = new StringBuffer();
					JTextPane newTextPanel = new JTextPane();
					JScrollPane newScrollPane = new JScrollPane(newTextPanel);
					//System.out.println("There are "+aTest.numberPossibleChoices[0]+" possible choices\n");
					newTextPanel.replaceSelection("(Mode 1 is used:\nAll these data are from Operon-Operon database.)\n\n");
					aBuffer.append("(Mode 1 is used:\nAll these data are from Operon_Operon database.)\n\n");
					Date currentDate = new Date();
					SimpleDateFormat timeForm = new SimpleDateFormat( 
							"yyyy-MM-dd HH:mm:ss");
					String currentTimes = timeForm.format(currentDate);
					newTextPanel.replaceSelection("Data Generated Time: " + currentTimes + "\n\n");
					aBuffer.append("Data Generated Time: " + currentTimes + "\n\n");
					String result = "";
					switch(aTest.numberPossibleChoices[0]){
					case 0:
						result = "There is no possible choice\n\n";
						break;
					case 1:
						result = "There is only one possible choice\n\n";
						break;
					default:
						result = "There are "+aTest.numberPossibleChoices[0]+" possible choices\n\n";
					}
					aBuffer.append(result);
					newTextPanel.replaceSelection(result);
					for(int i = 0;i < aTest.numberPossibleChoices[0]; i++){
						//System.out.print(i+"\n");
						newTextPanel.replaceSelection("choice "+(i+1)+":\n");
						aBuffer.append("Choice"+(i+1)+"\n");
						for(int j = 0;j<matrixsize ; j++){
							//System.out.print(aTest.result[i][j]+"\t");
							//System.out.println(operonNames.get(aTest.result[i][j])+"\t");
							newTextPanel.replaceSelection("Operon " + (j+1) + ": " + operonNames.get(aTest.result[i][j])+"\n");
							aBuffer.append("Operon " + (j+1) + ": " + operonNames.get(aTest.result[i][j])+"\n");
						}
						//System.out.print("\n\n");
						newTextPanel.replaceSelection("\n");
						aBuffer.append("\n");
					}
					newTextPanel.setEditable(false);
					newTextPanel.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							 if (e.getButton() != MouseEvent.BUTTON3) 
								 return;
							 aNewMenu.show(e.getComponent(),e.getX(),e.getY());
						}
					}
					);
					mainTabbedPane.addTab("Result"+index,newScrollPane);
					mainTabbedPane.setSelectedComponent(newScrollPane);
					save.setEnabled(true);
					outPutBuffers.setElementAt(aBuffer, index);  //buffer index is the same with the tab index
					index++;
				} 
	
				catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Cannot Open Operon_Operon Database!\n","Error!",JOptionPane.ERROR_MESSAGE);
				}	
			}
			
		});
		
		
		//initiate mode two button, press it to use mode two------*/
		modeTwoBt.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				
				if(matrixsize == 0){
					//System.out.println("Please Input Matrix Size First!\n");
					JOptionPane.showMessageDialog(null,"Please Input Matrix Size First!\n","Error!",JOptionPane.ERROR_MESSAGE);
					return;
				}
				final int[][] TargetMatrix = new int[matrixsize][matrixsize];
				
				for(int i = 0; i < matrixsize;i++){
					for(int j = 0;j < matrixsize;j++){
						String element = (String)inputMatrixTable.getValueAt(i,j);
						if(element == "+"){
							TargetMatrix[i][j] = 1;
						}
						else if(element == "-"){
							TargetMatrix[i][j] = -1;
						}
						else if(element == "0"){
							TargetMatrix[i][j] = 0;
						}
						else{
							JOptionPane.showMessageDialog(null,"Please Enter Matrix In The Right Place!\n","Error!",JOptionPane.ERROR_MESSAGE);
							return;
						}
						//System.out.print(TargetMatrix[i][j]+"\t");	
					}
					//System.out.print("\n");
				}
				try{
					Gene_Promoter aTest = new Gene_Promoter(matrixsize,
															TargetMatrix,
															genepromoterDataBase,
															numOfGenes,
															numOfPromoters,
															geneNames,
															promoterNames);
					StringBuffer aBuffer = new StringBuffer();
					JTextPane newTextPanel = new JTextPane();
					JScrollPane newScrollPane = new JScrollPane(newTextPanel);
					//System.out.println("There are "+aTest.numberPossibleChoices[0]+" possible choices\n");
					newTextPanel.replaceSelection("(Mode 2 is used:\nAll these data are from Gene_Promoter database.)\n\n");
					aBuffer.append("(Mode 2 is used:\nAll these data are from Gene_Promoter database.)\n\n");
					Date currentDate = new Date();
					SimpleDateFormat timeForm = new SimpleDateFormat( 
							"yyyy-MM-dd HH:mm:ss");
					String currentTimes = timeForm.format(currentDate);
					newTextPanel.replaceSelection("Data Generated Time: " + currentTimes + "\n\n");
					aBuffer.append("Data Generated Time: " + currentTimes + "\n\n");
					String result = "";
					switch(aTest.numberPossibleChoices[0]){
					case 0:
						result = "There is no possible choice\n\n";
						break;
					case 1:
						result = "There is only one possible choice\n\n";
						break;
					default:
						result = "There are "+aTest.numberPossibleChoices[0]+" possible choices\n\n";
					}
					aBuffer.append(result);
					newTextPanel.replaceSelection(result);
					for(int i = 0; i < aTest.numberPossibleChoices[0];i++){
						//System.out.print(i+"\n");
						newTextPanel.replaceSelection("choice "+(i+1)+":\n");
						aBuffer.append("Choice"+(i+1)+"\n");
						for(int j = 0; j < matrixsize; j++){
							//System.out.println(aTest.result[i][0][j]+"\t"+promoterNames.get(aTest.result[i][0][j])+"\n");
							//System.out.println(aTest.result[i][1][j]+"\t"+geneNames.get(aTest.result[i][1][j])+"\n");
							newTextPanel.replaceSelection("Biobrick " + (j+1) + ": ");
							aBuffer.append("Biobrick " + (j+1) + ": ");
							newTextPanel.replaceSelection(promoterNames.get(aTest.result[i][0][j]) + " + ");
							aBuffer.append(promoterNames.get(aTest.result[i][0][j]) + " + ");
							newTextPanel.replaceSelection(geneNames.get(aTest.result[i][1][j]) + "\n");
							aBuffer.append(geneNames.get(aTest.result[i][1][j]) + "\n");
						}
						//System.out.print("\n\n");
						newTextPanel.replaceSelection("\n");
						aBuffer.append("\n");
					}
					newTextPanel.setEditable(false);
					newTextPanel.addMouseListener(new MouseAdapter(){
						public void mouseClicked(MouseEvent e){
							 if (e.getButton() != MouseEvent.BUTTON3) 
								 return;
							 aNewMenu.show(e.getComponent(),e.getX(),e.getY());
						}
					}
					);
					mainTabbedPane.addTab("Result"+index,newScrollPane);
					mainTabbedPane.setSelectedComponent(newScrollPane);
					save.setEnabled(true);
					outPutBuffers.setElementAt(aBuffer, index);  //buffer index is the same with the tab index
					index++;
				}
				catch(IOException e2){
					JOptionPane.showMessageDialog(null,"Cannot Open Gene_Promoter Database!\n","Error!",JOptionPane.ERROR_MESSAGE);
				}
			}	
		});
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*--------method to initiate frame close events-----*/
	public void initFrameEvents(){
		/*-----when press X, close the frame -------*/
		mainFrame.addWindowListener(new WindowAdapter() {
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			    }
			 }
		);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*---------method to initiate tab events----------*/
	public void initTabEvents(){
		
		//if double click the tab, it will close-----*/
		mainTabbedPane.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e) {
				int tabIndex = mainTabbedPane.indexAtLocation(e.getX(), e.getY());
				if(e.getClickCount() == 2 && tabIndex != -1 && tabIndex != 0){
					mainTabbedPane.remove(tabIndex);
					outPutBuffers.setElementAt(null, tabIndex);   //when close the tab, remove its buffer
				}
				int numOfTabs = mainTabbedPane.getTabCount();
				if(numOfTabs == 1){
					save.setEnabled(false);
				}
			}
			
		}
		);
	}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*--------method to initiate text events---------*/
	public void initTextEvents(){
		sizeInput.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent I){
				if(I.getKeyCode() == KeyEvent.VK_ENTER){
					triggerConfirm();
				}
			}
		}
		);	
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*--------method to initiate file menu events-------*/
	public void initFileMenuEvents(){
		
		//initiate JMenuItem "New"
		New.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerNew();
			}
			
		});
		
		//initiate JMenuItem "Save"
		
		save.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerSave();
			}
		});
		
		//initiate JMenuItem "clear"
		clear.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerClear();
			}	
		});
		
		//initiate JMenuItem "Exit"
		exit.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				triggerExit();
			}
		});
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*---------method to initiate Search menu events----*/
	public void initSearchMenuEvents(){
		
		//initializing JMenuItem "Operon_Operon"
		Operon_Operon.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Search_Database searchInO_O = new Search_Database(numOfOperons,
																numOfOperons,
																operonNames,
																operonNames,
																operonDataBase);
				searchInO_O.setTitle("Search In Operon-Operon");
				searchInO_O.setResizable(false);
				searchInO_O.setVisible(true);
			}
		}
		);
		
		//initializing JMenuItem "Gene_Promoter"
		Gene_Promoter.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				Search_Database searchInG_P = new Search_Database(numOfGenes,
																numOfPromoters,
																geneNames,
																promoterNames,
																genepromoterDataBase);
				searchInG_P.setTitle("Search In Gene-Promoter");
				searchInG_P.setResizable(false);
				searchInG_P.setVisible(true);
			}
		}
		);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*-------method to initiate help menu events----*/
	public void initHelpMenuEvents(){
		
		//initiate JMenuItem "how to use" 
		howToUse.addMouseListener(new MouseAdapter(){
			public void	mousePressed(MouseEvent e){ 
				JOptionPane.showMessageDialog(null,"\n","How To Use",JOptionPane.PLAIN_MESSAGE);
			}
		}
		);
		
		//initiate JMenuItem "About DataBase"
		aboutDatabase.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				LinkLabel AboutDatabase= new LinkLabel("RegulonDB", "http://regulondb.ccg.unam.mx/");  
				Object[] message = { "DataBases are from RegulonDB",AboutDatabase};
				JOptionPane pane = new JOptionPane(message, JOptionPane.PLAIN_MESSAGE);
				JDialog dialog = pane.createDialog(pane, "About Database");  
                dialog.setVisible(true); 
			}
		}
		);
		
	}
	
	/*------method to initiate about menu events-----*/
	public void initAboutMenuEvents(){
		//initiate JMenuItem "aboutUSTC_2012"
		aboutUSTC_2012.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				LinkLabel AboutUSTC_2012 = new LinkLabel("2012 USTC_SoftWare Wiki","http://2012.igem.org/Team:USTC-Software");
				Object[] message = {"2012 USTC_Software Team consists of XX members,visit our wiki",AboutUSTC_2012};
				JOptionPane pane = new JOptionPane(message,JOptionPane.PLAIN_MESSAGE);
				JDialog dialog = pane.createDialog(pane, "About 2012 USTC-Software Team");
				dialog.setVisible(true);
			}
		});
		
		//initiate JMenuItem "aboutUSTC"
		aboutUSTC.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				LinkLabel AboutUSTC = new LinkLabel("USTC Website","http://www.ustc.edu.cn/");
				Object[] message = {"USTC, namely Univeristy of Science Technology.\n Visit the website",AboutUSTC};
				JOptionPane pane = new JOptionPane(message,JOptionPane.PLAIN_MESSAGE);
				JDialog dialog = pane.createDialog(pane,"About USTC");
				dialog.setVisible(true);
			}
		}
		);
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*---------method to initiate popup menu events-------*/
	public void initPopupMenuEvents(){
			//initiate JMenuItem "New_1"
			New_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					triggerNew();
				}
				
			});
			
			//initiate JMenuItem "Save_1"		
			save_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					triggerSave();
				}
			});
			
			//initiate JMenuItem "clear_1"
			clear_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					triggerClear();
				}	
			});	
			//initiate JMenuItem "Exit_1"
			exit_1.addMouseListener(new MouseAdapter(){
				public void mousePressed(MouseEvent e){
					triggerExit();
				}
			});
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
 /*------    variables in the program --------*/
	//variables for gui components
	private static JPanel panel_1;
	private static JLabel matrixSizeLabel;
	private static JTable inputMatrixTable;
	private static JFrame mainFrame;
	private static JTextField sizeInput;
	private static int matrixsize;
	private static JButton commitSizeBt;
	private static JButton modeOneBt;
	private static JButton modeTwoBt;
	private static JMenuBar mainMenu; 
	private static JMenu File;
	private static JMenu Search;
	private static JMenu Help;
	private static JMenu About;
	private static JPopupMenu aNewMenu;
	private static JTabbedPane mainTabbedPane;
	private static JScrollPane matrixScrollPane;
	private static JLabel modeOneLabel;
	private static JLabel modeTwoLabel;
	private static JLabel USTClogo;
	private static final int tableSize = 10;
	
	//variables for gui pictures
	private static final String PATH = "";
	private static final String USTCLOGO = PATH + "images/logo ustc.png";
	private static final String FRAMEBACKGROUND = PATH + "images/frameBack.png";
	private static final String PANELBACKGROUND = PATH + "images/panelBack.png";
	private static final String CONFIRMBUTTON = PATH + "images/confirm.png";
	private static final String MODEONE = PATH + "images/mode1.png";
	private static final String MODETWO = PATH + "images/mode2.png";
	private static ImageIcon framebackground = new ImageIcon(FRAMEBACKGROUND);
	private static JLabel framebackLabel = new JLabel(framebackground);
	
	private static ImageIcon panelbackground = new ImageIcon(PANELBACKGROUND);
	private static ImageIcon confirmbackground = new ImageIcon(CONFIRMBUTTON);
	private static ImageIcon modeonebackground = new ImageIcon(MODEONE);
	private static ImageIcon modetwobackground = new ImageIcon(MODETWO);

	//JMenuItems for Menu "File"
	private static JMenuItem New;
	private static JMenuItem save;
	private static JMenuItem clear;
	private static JMenuItem exit;
	
	
	//JMenuItems for Menu "Database"
	private static JMenuItem Operon_Operon;
	private static JMenuItem Gene_Promoter;
	
	//JMenuItems for Menu "Help"
	private static JMenuItem howToUse;
	private static JMenuItem aboutDatabase;
	
	//JMenuItems for Menu "About"
	private static JMenuItem aboutUSTC_2012;
	private static JMenuItem aboutUSTC;
	
	
	//JMenuItems for JPopupMenu "aNewMenu"
	private static JMenuItem New_1;
	private static JMenuItem save_1;
	private static JMenuItem clear_1;
	private static JMenuItem exit_1;
	
	static int index = 1;    //tab index
	
	/*------variables for Operon_Operon database-------*/
	public static int[][] operonDataBase;
	public static int numOfOperons;
	public static Vector<String> operonNames;
	
	
	/*------variables for Gene_Promoter database------*/
	public static int[][] genepromoterDataBase;
	public static int numOfPromoters;
	public static int numOfGenes;
	public static Vector<String> geneNames;
	public static Vector<String> promoterNames;
	public static Vector<StringBuffer> outPutBuffers;
	
	private static String PATHFORPARTS = PATH + "database/USTC_SOFTWARE_PARTS_DATA.txt";
	private static String PATHFORBIOBRICKS = PATH + "database/USTC_SOFTWARE_BIOBRICKS_DATA.txt";
}
