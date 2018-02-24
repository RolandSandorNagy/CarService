package carservice.view;

import carservice.controller.Controller;
import carservice.enums.ViewState;
import carservice.enums.WorkSheetState;
import carservice.listeners.ConnectionSettingsInputFieldKeyListener;
import carservice.listeners.FilterButtonListener;
import carservice.listeners.FinalizeButtonListener;
import carservice.listeners.InputFieldListener;
import carservice.listeners.MenuButtonListener;
import carservice.listeners.ResetButtonListener;
import carservice.listeners.SubmitButtonListener;
import carservice.listeners.DeleteButtonListener;
import carservice.listeners.FilterInputListener;
import carservice.listeners.RefreshButtonListener;
import carservice.listeners.SaveDataButtonListener;
import carservice.listeners.TableFocusListener;
import carservice.listeners.TablePropertyChangeListener;
import carservice.structs.WorkSheet;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class ViewImp extends JFrame implements View 
{
    private Controller controller;

    private JLayeredPane logoPane;
    private JPanel  contentPanel;
    private JPanel  footerPanel;
    private JPanel  headerPanel;
    private JPanel  mainPanel;
    private JPanel  sideBarPanel;
    private JButton carPartsBtn;
    private JButton colleaguesBtn;
    private JButton customersBtn;
    private JButton exitBtn;
    private JButton refreshBtn;
    private JButton workSheetsBtn;
    private JLabel  authorLabel;
    private JLabel  neptunkodLabel;
    private JLabel  softwareNameLabel;
    private JLabel  versionLabel;
    
    private JLabel  welcomePageLabel;
 
    private JTable  contentTable;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;

    private JLabel colleaguePageLabel;

    private JLabel customersPageLabel;

    private JLabel storePageLabel;
    
    private JLabel workSheetPageLabel;
    private JPanel bottomBarPanel;
    private JButton addWorkSheetBtn;
    private JButton deleteBtn;
    private JButton filterBtn;
    private JButton finalizeBtn;
    private JButton saveDataBtn;
    private JComboBox<String> filterCustomerComboBox;
    private JComboBox<String> filterStateComboBox;
    private JComboBox<String> filterColleagueComboBox;
    private JLabel filterCustomerLabel;
    private JLabel filterStateLabel;
    private JLabel filterColleagueLabel;

    private GroupLayout addWorkSheetFormPanelLayout;    
    private GroupLayout bottomBarLayout;
    private GroupLayout footerPanelLayout;
    private GroupLayout LogoPaneLayout;
    private GroupLayout headerPanelLayout;
    private GroupLayout sideBarPanelLayout;
    private GroupLayout contentPanelLayout;
    private GroupLayout mainPanelLayout;   
    private GroupLayout layout;
    
    private JTextArea addWorkSheetFormCarPartsInputArea;
    private JLabel addWorkSheetFormCarPartsLabel;
    private JComboBox<String> addWorkSheetFormColleagueComboBox;
    private JLabel addWorkSheetFormColleagueLabel;
    private JTextField addWorkSheetFormCustomerNameInputField;
    private JLabel addWorkSheetFormCustomerNameLabel;
    private JTextField addWorkSheetFormCustomerNumberPlateInputField;
    private JLabel addWorkSheetFormCustomerNumberPlateLabel;
    private JTextField addWorkSheetFormCustomerTelInputField;
    private JLabel addWorkSheetFormCustomerTelLabel;
    private JLabel addWorkSheetFormInfoLabel;
    private JPanel addWorkSheetFormPanel;
    private JTextArea addWorkSheetFormProblemInputArea;
    private JLabel addWorkSheetFormProblemLabel;
    private JTextField addWorkSheetFormWorkHoursInputField;
    private JLabel addWorkSheetFormWorkHoursLabel;
    private JLabel addWorkSheetFormWorkHoursLabel2;
    private JLabel addWorkSheetPageLabel;
    private JButton resetFormBtn;
    private JButton submitFormBtn;

    private String addWorkSheetFormCarPartsInputAreaText;
    private String addWorkSheetFormColleagueComboBoxText;
    private String addWorkSheetFormCustomerNameInputFieldText;
    private String addWorkSheetFormCustomerNumberPlateInputFieldText;
    private String addWorkSheetFormCustomerTelInputFieldText;
    private String addWorkSheetFormProblemInputAreaText;
    private String addWorkSheetFormWorkHoursInputFieldText;
        
    private Color white;
    private Color lightGrey;
    private Color darkGrey;
    private Color blue;
    private Color addWorkSheetBtnColor;
    private Color red;
    private Color resetBtnColor;
    private Color submitFormBtnColor;
    private Color green;
    
    private FilterInputListener filterCustomerComboBoxActionListener;
    private FilterInputListener filterStateComboBoxActionListener;
    private FilterInputListener filterColleagueComboBoxActionListener;
    private FilterButtonListener filterBtnActionListener;
    private FinalizeButtonListener finalizeBtnActionListener;
    private DeleteButtonListener deleteBtnActionListener;
    private SaveDataButtonListener saveDataActionListener;
    private MenuButtonListener addWorkSheetBtnActionListener;
    private TablePropertyChangeListener contentTablePropertyChangeListener;
    private TableFocusListener contentTableFocusListener;
    private InputFieldListener addWorkSheetFormCustomerNameInputFieldKeyListener;
    private InputFieldListener addWorkSheetFormCustomerTelInputFieldKeyListener;
    private InputFieldListener addWorkSheetFormCustomerNumberPlateInputFieldKeyListener;
    private InputFieldListener addWorkSheetFormWorkHoursInputFieldKeyListener;
    private InputFieldListener addWorkSheetFormCarPartsInputAreaKeyListener;
    private InputFieldListener addWorkSheetFormProblemInputAreaKeyListener;
    private ResetButtonListener resetFormBtnActionListener;
    private SubmitButtonListener submitFormBtnActionListener;
    private MenuButtonListener colleaguesBtnActionListener;
    private MenuButtonListener workSheetsBtnActionListener;
    private MenuButtonListener customersBtnActionListener;
    private MenuButtonListener carPartsBtnActionListener;
    private RefreshButtonListener refreshBtnActionListener;
    private MenuButtonListener exitBtnActionListener;

    private final String welcomeText = "A program üdvözli Önt!";
    private final String successfulInsertMsg = "Munkalapot eltároltuk.";
    private String successfulModificationMsg = "Módosításokat eltároltuk.";
    private String wrongModificationValueMsg = "Hibás beviteli érték.";
    public String getConnectionSettingsUserInputFieldText;

    private JInternalFrame connectionSettingsFrame;
    private JLabel connectionSettingsConnectionLabel;
    private JLabel connectionSettingsDataBaseLabel;
    private JLabel connectionSettingsUserLabel;
    private JLabel connectionSettingsPasswordLabel;
    private JComboBox connectionSettingsConnectionsComboBox;
    private JTextField connectionSettingsDataBaseInputField;
    private JTextField connectionSettingsUserInputField;
    private JTextField connectionSettingsPasswordInputField;
    private JLabel connectionSettingsFrameLabel;
    private JButton connectionSettingsTestButton;
    private JButton connectionSettingsSaveButton;
    private JLabel connectionSettingsTestLabel;
    private JButton connectionSettingsCancelButton;

    
    public ViewImp()
    {
        super( "Autószerviz" );
    }

    @Override
    public void addController( Controller controller ) 
    {
        this.controller = controller;
    }

    @Override
    public void init() 
    {
        initColors();
        renderFrameComponents();
        postInitForFrame();
        //showView( null, null, ViewState.HOME );
        switchAllSideMenuButtonsExceptExit( false );
    }

    private void switchAllSideMenuButtonsExceptExit( boolean value )
    {
        workSheetsBtn.setEnabled( value );
        colleaguesBtn.setEnabled( value );
        customersBtn.setEnabled( value );
        carPartsBtn.setEnabled( value );
        refreshBtn.setEnabled( value );
    }
    
    private void initColors()
    {
        white = new Color( 255, 255, 255 );        
        lightGrey = new Color( 155, 155, 155 );
        //orange = new Color( 255, 255, 0 );
        darkGrey = new Color( 70, 70, 70 );
        blue = new Color( 140, 140, 255 );
        //blue = new Color( 0, 0, 255 );
        addWorkSheetBtnColor = new Color( 36, 233, 16 );
        red = new Color( 236, 100, 100 );
        //red = new Color( 255, 0, 0 );
        resetBtnColor = red;
        //resetBtnColor = new Color( 236, 133, 16 );
        submitFormBtnColor = new Color( 36, 233, 16 );
        green = darkGrey;        
        //green = new Color( 0, 255, 0 );        
    }
    
    private void postInitForFrame()
    {
        setDefaultCloseOperation( EXIT_ON_CLOSE );
        setBackground( white );
        setMaximizedBounds( new Rectangle( 0, 0, 0, 0 ) );
        setMaximumSize( new Dimension( 1024, 768 ) );
        setMinimumSize( new Dimension( 1024, 768 ) );
        setName( "CarServiceFrame" );
        setPreferredSize( new Dimension( 1024, 768 ) );
        setResizable( false );
        setSize( new Dimension( 1024, 768 ) );
        setLocationRelativeTo( null );
        setVisible( true );        
    }

    @Override
    public void showView( Object[][] content, Object[] headings, ViewState state )
    {
        contentPanel.removeAll();
        switch( state )
        {
            case HOME:                   renderBasicView( welcomeText );                 break;
            case COLLEAGUES:             renderColleaguesView( content, headings );      break;
            case WORKSHEETS:             renderWorkSheetsView( content, headings );      break;
            case CUSTOMERS:              renderCustomersView( content, headings );       break;
            case STORE:                  renderStoreView( content, headings );           break;
            case ADDWORKSHEET:           renderAddWorkSheetView();                       break;
            case WORKSHEETADDED:         renderBasicView( successfulInsertMsg );         break;
            case MODIFICATIONSAVED:      renderBasicView( successfulModificationMsg );   break;
            case WRONGMODIFICATIONVALUE: renderBasicView( wrongModificationValueMsg );   break;
            case CONNECTIONSETTINGS:     renderConnectionSettingsView();                 break;
            case EXIT:                   renderExitView();                               break;
            default: break;
        }
        contentPanel.repaint();
    }

    private void renderBasicView( String mainText ) 
    {
        contentTable = new JTable();
        jScrollPane1 = new JScrollPane();
        welcomePageLabel = new JLabel();
        welcomePageLabel.setFont( new Font( "Tekton Pro Ext", 3, 48 ) );
        welcomePageLabel.setForeground( darkGrey );
        welcomePageLabel.setHorizontalAlignment( SwingConstants.CENTER );
        welcomePageLabel.setText( mainText );
        contentPanelLayout = new GroupLayout( contentPanel );
        contentPanel.setLayout( contentPanelLayout );
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent( welcomePageLabel, GroupLayout.DEFAULT_SIZE, 816, Short.MAX_VALUE )
                .addContainerGap() ) );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( contentPanelLayout.createSequentialGroup()
                .addGap( 149, 149, 149 )
                .addComponent( welcomePageLabel, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE )
                .addContainerGap(266, Short.MAX_VALUE ) ) );
    }

    private void renderColleaguesView( Object[][] content, Object[] headings ) 
    {
        contentTable = new JTable();
        jScrollPane1 = new JScrollPane();
        colleaguePageLabel = new JLabel();
        colleaguePageLabel.setFont( new Font( "Trajan Pro", 3, 24 ) );
        colleaguePageLabel.setForeground( darkGrey );
        colleaguePageLabel.setText( "Munkatársak" );

        contentTable.setModel( new DefaultTableModel( content, headings ) );
        contentTable.setGridColor( blue );           
        contentTable.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        contentTable.setEnabled( false );
        contentTable.setMaximumSize( new Dimension( 816, 400 ) );
        contentTable.setMinimumSize( new Dimension( 816, 400 ) );
        contentTable.setName( "" ); 
        contentTable.setPreferredSize( new Dimension( 816, 400 ) );
        jScrollPane1.setViewportView( contentTable );

        contentPanelLayout = new GroupLayout( contentPanel );
        contentPanel.setLayout( contentPanelLayout );
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
                    .addComponent( jScrollPane1 )
                    .addGroup( contentPanelLayout.createSequentialGroup()
                        .addComponent( colleaguePageLabel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE )
                        .addGap( 0, 617, Short.MAX_VALUE ) ) )
                .addContainerGap() ) );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent( colleaguePageLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                .addContainerGap( 159, Short.MAX_VALUE ) ) );
    }

    private void renderWorkSheetsView( Object[][] content, Object[] headings ) 
    {
        contentTable = new JTable();
        
        jScrollPane1 = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        bottomBarPanel = new JPanel();
        addWorkSheetBtn = new JButton();
        deleteBtn = new JButton();
        filterBtn = new JButton();
        finalizeBtn = new JButton();
        saveDataBtn = new JButton();
        filterCustomerComboBox = new JComboBox();
        filterStateComboBox = new JComboBox();
        filterColleagueComboBox = new JComboBox();
        filterCustomerLabel = new JLabel();
        filterStateLabel = new JLabel();
        filterColleagueLabel = new JLabel();
        workSheetPageLabel = new JLabel(); 
        
        workSheetPageLabel.setFont( new Font( "Trajan Pro", 3, 24 ) );
        workSheetPageLabel.setForeground( darkGrey );
        workSheetPageLabel.setText( "Munkalapok" );

        contentTable.setForeground( blue );
        contentTable.setModel( new DefaultTableModel( content, headings ) {
            @Override
            public boolean isCellEditable( int row, int column ) {
                return (    ( column != 0 ) 
                         && ( column != 1 )
                         && ( column != 7 )
                         && ( column != 8 ) ) ;
            }
        } );
        contentTable.setGridColor( blue );
        jScrollPane1.setViewportView( contentTable );

        bottomBarPanel.setBackground( blue );
        bottomBarPanel.setPreferredSize( new Dimension( 816, 143 ) );

        filterCustomerLabel.setForeground( white );
        filterCustomerLabel.setText( "Ügyfél:" );

        filterStateLabel.setForeground( white );
        filterStateLabel.setText( "Állapot:" );

        filterColleagueLabel.setForeground( white );
        filterColleagueLabel.setText( "Munkatárs:" );

        //filterCustomerComboBox.setMaximumRowCount( 6 );
        filterCustomerComboBox.setModel( new DefaultComboBoxModel<>( controller.getCustomerNamesStringArray() ) );
        filterCustomerComboBox.setMaximumSize( new Dimension( 60, 18 ) );
        filterCustomerComboBox.setMinimumSize( new Dimension( 60, 18 ) );
        filterCustomerComboBox.setPreferredSize( new Dimension( 60, 18 ) );
        filterCustomerComboBoxActionListener = new FilterInputListener( controller );
        filterCustomerComboBox.addActionListener( filterCustomerComboBoxActionListener );

        //filterStateComboBox.setMaximumRowCount( 6 );
        filterStateComboBox.setModel( new DefaultComboBoxModel<>( new String[] { "", "FELDOLGOZANDO", "ALKATRESZREVAR", "FELDOLGOZOTT", "FOLYAMATBAN", "KIFIZETETT" } ) );
        filterStateComboBox.setMaximumSize( new Dimension( 60, 18 ) );
        filterStateComboBox.setMinimumSize( new Dimension( 60, 18 ) );
        filterStateComboBox.setPreferredSize( new Dimension( 60, 18 ) );
        filterStateComboBoxActionListener = new FilterInputListener( controller );
        filterStateComboBox.addActionListener( filterStateComboBoxActionListener );

        //filterColleagueComboBox.setMaximumRowCount( 6 );
        //filterColleagueComboBox.setModel( new DefaultComboBoxModel<>(new String[] { "", "Item 1", "Item 2", "Item 3", "Item 4" } ) );
        filterColleagueComboBox.setModel( new DefaultComboBoxModel<>( controller.getColleagueNamesStringArray() ) );
        filterColleagueComboBox.setMaximumSize( new Dimension( 60, 18 ) );
        filterColleagueComboBox.setMinimumSize( new Dimension( 60, 18 ) );
        filterColleagueComboBox.setPreferredSize( new Dimension( 60, 18 ) );
        filterColleagueComboBoxActionListener = new FilterInputListener( controller );
        filterColleagueComboBox.addActionListener( filterColleagueComboBoxActionListener );
                
        filterBtn.setText( "Szűrés" );
        filterBtn.setBorderPainted( false );
        filterBtn.setEnabled( false );
        filterBtn.setFocusPainted( false );
        filterBtn.setMaximumSize( new Dimension( 66, 66 ) );
        filterBtn.setMinimumSize( new Dimension( 66, 66 ) );
        filterBtn.setPreferredSize( new Dimension( 66, 66 ) );
        filterBtnActionListener = new FilterButtonListener( controller );
        filterBtn.addActionListener( filterBtnActionListener );
        
        finalizeBtn.setText( "Véglegesítés" );
        finalizeBtn.setBorderPainted( false );
        finalizeBtn.setEnabled( false );
        finalizeBtn.setFocusPainted( false );
        finalizeBtn.setMaximumSize( new Dimension( 66, 66 ) );
        finalizeBtn.setMinimumSize( new Dimension( 66, 66 ) );
        finalizeBtn.setPreferredSize( new Dimension( 66, 66 ) );
        finalizeBtnActionListener = new FinalizeButtonListener( controller );
        finalizeBtn.addActionListener( finalizeBtnActionListener );
        
        deleteBtn.setText( "Törlés" );
        deleteBtn.setBorderPainted( false );
        deleteBtn.setEnabled( false );
        deleteBtn.setFocusPainted( false );
        deleteBtn.setMaximumSize( new Dimension( 66, 66 ) );
        deleteBtn.setMinimumSize( new Dimension( 66, 66 ) );
        deleteBtn.setPreferredSize( new Dimension( 66, 66 ) );
        deleteBtnActionListener = new DeleteButtonListener( controller );
        deleteBtn.addActionListener( deleteBtnActionListener );
        
        saveDataBtn.setText( "Mentés" );
        saveDataBtn.setBorderPainted( false);
        saveDataBtn.setEnabled( TablePropertyChangeListener.modification );
        saveDataBtn.setFocusPainted( false);
        saveDataBtn.setMaximumSize( new Dimension( 66, 66 ) );
        saveDataBtn.setMinimumSize( new Dimension( 66, 66 ) );
        saveDataBtn.setPreferredSize( new Dimension( 66, 66 ) );
        saveDataActionListener = new SaveDataButtonListener( controller );
        saveDataBtn.addActionListener(  saveDataActionListener );
        
        bottomBarLayout = new GroupLayout( bottomBarPanel );
        bottomBarPanel.setLayout( bottomBarLayout );
        bottomBarLayout.setHorizontalGroup(
            bottomBarLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( bottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bottomBarLayout.createParallelGroup( GroupLayout.Alignment.LEADING, false )
                    .addGroup(bottomBarLayout.createSequentialGroup()
                        .addGroup(bottomBarLayout.createParallelGroup( GroupLayout.Alignment.TRAILING )
                            .addComponent( filterStateLabel, GroupLayout.Alignment.LEADING )
                            .addComponent( filterCustomerLabel, GroupLayout.Alignment.LEADING ) )
                        .addGap( 55, 55, 55 )
                        .addGroup( bottomBarLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
                            .addComponent( filterCustomerComboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE )
                            .addComponent( filterStateComboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE ) ) )
                    .addGroup( bottomBarLayout.createSequentialGroup()
                        .addComponent( filterColleagueLabel )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                        .addComponent( filterColleagueComboBox, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE ) ) )
                .addGap( 18, 18, 18 )
                .addComponent( filterBtn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE )
                .addGap( 18, 18, 18 )
                .addComponent( finalizeBtn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE )
                .addGap( 18, 18, 18 )
                .addComponent( deleteBtn, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE )
                .addGap( 18, 18, 18 )
                .addComponent( saveDataBtn, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE )
                .addContainerGap( 48, Short.MAX_VALUE ) ) );
        bottomBarLayout.setVerticalGroup(
            bottomBarLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( bottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( bottomBarLayout.createParallelGroup( GroupLayout.Alignment.TRAILING, false )
                    .addGroup( GroupLayout.Alignment.LEADING, bottomBarLayout.createSequentialGroup()
                        .addGroup( bottomBarLayout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                            .addComponent( filterBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                            .addComponent( finalizeBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                            .addComponent( deleteBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                            .addComponent( saveDataBtn, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE ) )
                        .addGap( 22, 22, 22 ) )
                    .addGroup( GroupLayout.Alignment.LEADING, bottomBarLayout.createSequentialGroup()
                        .addGroup(bottomBarLayout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                            .addComponent( filterCustomerLabel )
                            .addComponent( filterCustomerComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addGroup( bottomBarLayout.createParallelGroup(  GroupLayout.Alignment.BASELINE )
                            .addComponent( filterStateLabel )
                            .addComponent( filterStateComboBox,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE ) )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addGroup( bottomBarLayout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                            .addComponent( filterColleagueLabel )
                            .addComponent( filterColleagueComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) ) ) )
                .addContainerGap( 44, Short.MAX_VALUE ) ) );

        addWorkSheetBtn.setBackground( addWorkSheetBtnColor );
        addWorkSheetBtn.setText( "+ Új munkalap" );
        addWorkSheetBtn.setBorderPainted( false );
        addWorkSheetBtn.setFocusPainted( false );
        addWorkSheetBtn.setMaximumSize( new Dimension( 66, 66 ) );
        addWorkSheetBtn.setMinimumSize( new Dimension( 66, 66 ) );
        addWorkSheetBtn.setPreferredSize( new Dimension( 66, 66 ) );
        addWorkSheetBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        addWorkSheetBtnActionListener = new MenuButtonListener( controller, ViewState.ADDWORKSHEET );
        addWorkSheetBtn.addActionListener( addWorkSheetBtnActionListener );

        contentPanelLayout = new GroupLayout( contentPanel );
        contentPanel.setLayout( contentPanelLayout );
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( contentPanelLayout.createParallelGroup( GroupLayout.Alignment.TRAILING )
                    .addComponent( bottomBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                    .addComponent( jScrollPane1, GroupLayout.Alignment.LEADING )
                    .addGroup( GroupLayout.Alignment.LEADING, contentPanelLayout.createSequentialGroup()
                        .addComponent( workSheetPageLabel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                        .addComponent( addWorkSheetBtn, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE ) ) )
                .addContainerGap() ) );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
                    .addComponent( workSheetPageLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE )
                    .addComponent( addWorkSheetBtn, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE ) )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( jScrollPane1, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE )
                .addGap( 18, 18, 18 )
                .addComponent( bottomBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addContainerGap() ) );
                
        contentTablePropertyChangeListener = new TablePropertyChangeListener( controller );
        contentTable.addPropertyChangeListener( contentTablePropertyChangeListener );
 
        contentTableFocusListener = new TableFocusListener( controller, contentTable );
        contentTable.addFocusListener( contentTableFocusListener );
            
        //valami Listener ToDo...
        contentTable.addKeyListener( new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) 
            {
                System.out.println( "Key Typed On Table." );
            }

            @Override
            public void keyPressed(KeyEvent e) 
            {
                System.out.println( "Key Pressed On Table." );
            }

            @Override
            public void keyReleased(KeyEvent e) 
            {
                System.out.println( "Key Released On Table." );
                controller.handleFinalizeButton( contentTable );
                int rowNo = contentTable.getSelectedRow();
                if( !contentTable.getValueAt( rowNo, 8 ).toString().equals( "KIFIZETETT" ) )
                    controller.enableDeleteButton();           
                else controller.disableDeleteButton();
            }
        } );
        contentTable.addMouseListener( new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) 
            {
                System.out.println( "Mose Clicked On Table." );
                controller.handleFinalizeButton( contentTable );
                int rowNo = contentTable.getSelectedRow();
                if( !contentTable.getValueAt( rowNo, 8 ).toString().equals( "KIFIZETETT" ) )
                    controller.enableDeleteButton();           
                else controller.disableDeleteButton();
            }

            @Override
            public void mousePressed(MouseEvent e) 
            {

            }

            @Override
            public void mouseReleased(MouseEvent e) 
            {

            }

            @Override
            public void mouseEntered(MouseEvent e) 
            {

            }

            @Override
            public void mouseExited(MouseEvent e) 
            {

            }
        } );

        contentTable.getColumnModel().getColumn(0).setMinWidth(25);
        contentTable.getColumnModel().getColumn(0).setPreferredWidth(25);
        contentTable.getColumnModel().getColumn(0).setMaxWidth(25);

        contentTable.getColumnModel().getColumn(1).setMinWidth(80);
        contentTable.getColumnModel().getColumn(1).setPreferredWidth(80);
        contentTable.getColumnModel().getColumn(1).setMaxWidth(80);

        contentTable.getColumnModel().getColumn(2).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(3).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(4).setPreferredWidth(40);

        contentTable.getColumnModel().getColumn(5).setMinWidth(100);
        contentTable.getColumnModel().getColumn(5).setPreferredWidth(100);
        contentTable.getColumnModel().getColumn(5).setMaxWidth(100);

        contentTable.getColumnModel().getColumn(6).setMinWidth(40);
        contentTable.getColumnModel().getColumn(6).setPreferredWidth(40);
        contentTable.getColumnModel().getColumn(6).setMaxWidth(40);

        contentTable.getColumnModel().getColumn(7).setMinWidth(80);
        contentTable.getColumnModel().getColumn(7).setPreferredWidth(80);
        contentTable.getColumnModel().getColumn(7).setMaxWidth(80);

        contentTable.getColumnModel().getColumn(8).setMinWidth(120);
        contentTable.getColumnModel().getColumn(8).setPreferredWidth(120);
        contentTable.getColumnModel().getColumn(8).setMaxWidth(120);
        
    }

    private void renderCustomersView( Object[][] content, Object[] headings ) 
    {
        contentTable = new JTable();
        jScrollPane1 = new JScrollPane();
        customersPageLabel = new JLabel();
        customersPageLabel.setFont( new Font( "Trajan Pro", 3, 24 ) );
        customersPageLabel.setForeground( darkGrey );
        customersPageLabel.setText( "Ügyfelek" );

        contentTable.setModel( new DefaultTableModel( content, headings ) );
        contentTable.setGridColor( blue );   
        contentTable.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        contentTable.setEnabled( false);
        contentTable.setMaximumSize( new Dimension( 816, 400 ) );
        contentTable.setMinimumSize( new Dimension( 816, 400 ) );
        contentTable.setName( "" ); 
        contentTable.setPreferredSize( new Dimension( 816, 400 ) );
        jScrollPane1.setViewportView( contentTable );

        contentPanelLayout = new GroupLayout( contentPanel );
        contentPanel.setLayout( contentPanelLayout );
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
                    .addComponent( jScrollPane1 )
                    .addGroup( contentPanelLayout.createSequentialGroup()
                        .addComponent( customersPageLabel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE )
                        .addGap( 0, 617, Short.MAX_VALUE ) ) )
                .addContainerGap() ) );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent( customersPageLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent( jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                .addContainerGap( 159, Short.MAX_VALUE ) ) );
    }

    private void renderStoreView( Object[][] content, Object[] headings ) 
    {
        contentTable = new JTable();
        jScrollPane1 = new JScrollPane();
        storePageLabel = new JLabel();
        storePageLabel.setFont( new Font( "Trajan Pro", 3, 24 ) );
        storePageLabel.setForeground( darkGrey );
        storePageLabel.setText("Alkatrészek");

        contentTable.setModel( new DefaultTableModel( content, headings ) );
        contentTable.setGridColor( blue );   
        contentTable.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
        contentTable.setEnabled( false );
        contentTable.setMaximumSize( new Dimension( 816, 400 ) );
        contentTable.setMinimumSize( new Dimension( 816, 400 ) );
        contentTable.setName( "" );
        contentTable.setPreferredSize( new Dimension(816, 400 ) );
        jScrollPane1.setViewportView( contentTable );

        contentPanelLayout = new GroupLayout( contentPanel );
        contentPanel.setLayout( contentPanelLayout );
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
                    .addComponent( jScrollPane1 )
                    .addGroup( contentPanelLayout.createSequentialGroup()
                        .addComponent( storePageLabel, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE )
                        .addGap( 0, 617, Short.MAX_VALUE ) ) )
                .addContainerGap() ) );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent( storePageLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                .addContainerGap( 159, Short.MAX_VALUE ) ) );
    }

    private void renderAddWorkSheetView() 
    {
        contentTable = new JTable();
        jScrollPane1 = new JScrollPane();

        addWorkSheetFormCarPartsInputArea = new JTextArea();
        addWorkSheetFormCarPartsLabel = new JLabel();
        addWorkSheetFormColleagueComboBox = new JComboBox();
        addWorkSheetFormColleagueLabel = new JLabel();
        addWorkSheetFormCustomerNameInputField = new JTextField();
        addWorkSheetFormCustomerNameLabel = new JLabel();
        addWorkSheetFormCustomerNumberPlateInputField = new JTextField();
        addWorkSheetFormCustomerNumberPlateLabel = new JLabel();
        addWorkSheetFormCustomerTelInputField = new JTextField();
        addWorkSheetFormCustomerTelLabel = new JLabel();
        addWorkSheetFormInfoLabel = new JLabel();
        addWorkSheetFormPanel = new JPanel();
        addWorkSheetFormProblemInputArea = new JTextArea();
        addWorkSheetFormProblemLabel = new JLabel();
        addWorkSheetFormWorkHoursInputField = new JTextField();
        addWorkSheetFormWorkHoursLabel = new JLabel();
        addWorkSheetFormWorkHoursLabel2 = new JLabel();
        addWorkSheetPageLabel = new JLabel();
        jScrollPane1 = new JScrollPane();
        jScrollPane2 = new JScrollPane();
        resetFormBtn = new JButton();
        submitFormBtn = new JButton();
                
        contentPanel.setBackground( blue );

        addWorkSheetPageLabel.setFont( new Font( "Trajan Pro", 3, 24 ) );
        addWorkSheetPageLabel.setForeground( darkGrey );
        addWorkSheetPageLabel.setText( "Új munkalap hozzáadása" );

        addWorkSheetFormCustomerNameLabel.setForeground( red );
        addWorkSheetFormCustomerNameLabel.setText( "* Vevő neve:" );

        addWorkSheetFormCustomerTelLabel.setForeground( red );
        addWorkSheetFormCustomerTelLabel.setText( "* Vevő telefonszáma:" );

        addWorkSheetFormCustomerNumberPlateLabel.setForeground( red );
        addWorkSheetFormCustomerNumberPlateLabel.setText( "* Autó rendszáma:" );

        addWorkSheetFormColleagueLabel.setText( "Munkatárs:" );

        addWorkSheetFormWorkHoursLabel.setText( "Munkaidő (óra):" );

        addWorkSheetFormCustomerNameInputField.setName( "customerName" );
        addWorkSheetFormCustomerNameInputFieldKeyListener = new InputFieldListener( controller, addWorkSheetFormCustomerNameInputField );
        addWorkSheetFormCustomerNameInputField.addKeyListener( addWorkSheetFormCustomerNameInputFieldKeyListener );

        addWorkSheetFormCustomerTelInputField.setName( "customerTel" );
        addWorkSheetFormCustomerTelInputFieldKeyListener = new InputFieldListener( controller, addWorkSheetFormCustomerTelInputField );
        addWorkSheetFormCustomerTelInputField.addKeyListener( addWorkSheetFormCustomerTelInputFieldKeyListener );

        addWorkSheetFormCustomerNumberPlateInputField.setName( "customerNumberPlate" );
        addWorkSheetFormCustomerNumberPlateInputFieldKeyListener = new InputFieldListener( controller, addWorkSheetFormCustomerNumberPlateInputField );
        addWorkSheetFormCustomerNumberPlateInputField.addKeyListener( addWorkSheetFormCustomerNumberPlateInputFieldKeyListener );

        addWorkSheetFormWorkHoursInputField.setName( "hours" );
        addWorkSheetFormWorkHoursInputFieldKeyListener = new InputFieldListener( controller, addWorkSheetFormWorkHoursInputField );
        addWorkSheetFormWorkHoursInputField.addKeyListener( addWorkSheetFormWorkHoursInputFieldKeyListener );

        addWorkSheetFormColleagueComboBox.setModel( new DefaultComboBoxModel<>( controller.getColleaguesNamesStringArray() ) );

        addWorkSheetFormWorkHoursLabel2.setText( "(számmal)" );

        addWorkSheetFormCarPartsLabel.setText( "Alkatrészek (cikkszámok, vesszővel elválasztva):" );

        addWorkSheetFormCarPartsInputArea.setColumns( 20 );
        addWorkSheetFormCarPartsInputArea.setRows( 5 );
        addWorkSheetFormCarPartsInputArea.setName( "carParts" );
        addWorkSheetFormCarPartsInputAreaKeyListener = new InputFieldListener( controller, addWorkSheetFormCarPartsInputArea );
        addWorkSheetFormCarPartsInputArea.addKeyListener( addWorkSheetFormCarPartsInputAreaKeyListener );
        jScrollPane1.setViewportView( addWorkSheetFormCarPartsInputArea );

        addWorkSheetFormProblemLabel.setForeground( red );
        addWorkSheetFormProblemLabel.setText( "* Probléma leírása:" );

        addWorkSheetFormProblemInputArea.setColumns( 20 );
        addWorkSheetFormProblemInputArea.setRows( 5 );
        addWorkSheetFormProblemInputArea.setName( "problem" );
        addWorkSheetFormProblemInputAreaKeyListener = new InputFieldListener( controller, addWorkSheetFormProblemInputArea );
        addWorkSheetFormProblemInputArea.addKeyListener( addWorkSheetFormProblemInputAreaKeyListener );
        jScrollPane2.setViewportView( addWorkSheetFormProblemInputArea );

        resetFormBtn.setBackground( resetBtnColor );
        resetFormBtn.setText( "Újrakezd" );
        resetFormBtn.setBorderPainted( false );
        resetFormBtn.setFocusPainted( false );
        resetFormBtnActionListener = new ResetButtonListener( controller );
        resetFormBtn.addActionListener( resetFormBtnActionListener );

        submitFormBtn.setBackground( submitFormBtnColor );
        submitFormBtn.setText( "Felküld" );
        submitFormBtn.setBorderPainted( false );
        submitFormBtn.setFocusPainted( false );
        submitFormBtnActionListener = new SubmitButtonListener( controller );
        submitFormBtn.addActionListener( submitFormBtnActionListener );
        
        addWorkSheetFormInfoLabel.setFont( new Font( "Tahoma", 0, 10 ) );
        addWorkSheetFormInfoLabel.setForeground( red );
        addWorkSheetFormInfoLabel.setText( "A piros * -gal pirossal jelölt mezők kitöltése kötelező." );

        addWorkSheetFormPanelLayout = new GroupLayout( addWorkSheetFormPanel );
        addWorkSheetFormPanel.setLayout( addWorkSheetFormPanelLayout );
        addWorkSheetFormPanelLayout.setHorizontalGroup(
            addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( addWorkSheetFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
                    .addGroup( GroupLayout.Alignment.TRAILING, addWorkSheetFormPanelLayout.createSequentialGroup()
                        .addGap( 0, 24, Short.MAX_VALUE )
                        .addComponent( resetFormBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE )
                        .addGap( 18, 18, 18 )
                        .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING, false )
                            .addComponent( jScrollPane1 )
                            .addComponent( addWorkSheetFormCarPartsLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                            .addGroup( GroupLayout.Alignment.TRAILING, addWorkSheetFormPanelLayout.createSequentialGroup()
                                .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING, false )
                                    .addComponent( addWorkSheetFormCustomerTelLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                    .addComponent( addWorkSheetFormColleagueLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                    .addComponent( addWorkSheetFormWorkHoursLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                    .addComponent( addWorkSheetFormCustomerNumberPlateLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                    .addComponent( addWorkSheetFormCustomerNameLabel, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE ) )
                                .addGap( 18, 18, 18 )
                                .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING, false )
                                    .addComponent( addWorkSheetFormCustomerNameInputField )
                                    .addComponent( addWorkSheetFormCustomerTelInputField )
                                    .addComponent( addWorkSheetFormCustomerNumberPlateInputField )
                                    .addComponent( addWorkSheetFormColleagueComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                                    .addGroup( GroupLayout.Alignment.TRAILING, addWorkSheetFormPanelLayout.createSequentialGroup()
                                        .addComponent( addWorkSheetFormWorkHoursInputField, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE )
                                        .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED )
                                        .addComponent( addWorkSheetFormWorkHoursLabel2 ) ) ) )
                            .addComponent( addWorkSheetFormProblemLabel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                            .addComponent( jScrollPane2, GroupLayout.Alignment.TRAILING ) )
                        .addGap( 18, 18, 18 )
                        .addComponent( submitFormBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE )
                        .addGap( 27, 27, 27 ) )
                    .addGroup( addWorkSheetFormPanelLayout.createSequentialGroup()
                        .addComponent( addWorkSheetFormInfoLabel, GroupLayout.PREFERRED_SIZE, 337, GroupLayout.PREFERRED_SIZE )
                        .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) ) ) ) );
        addWorkSheetFormPanelLayout.setVerticalGroup(
            addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( addWorkSheetFormPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.TRAILING )
                    .addGroup( addWorkSheetFormPanelLayout.createSequentialGroup()
                        .addGroup( addWorkSheetFormPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent( addWorkSheetFormCustomerNameLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addComponent( addWorkSheetFormCustomerNameInputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                            .addComponent( addWorkSheetFormCustomerTelLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE )
                            .addComponent( addWorkSheetFormCustomerTelInputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                            .addComponent( addWorkSheetFormCustomerNumberPlateLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE )
                            .addComponent( addWorkSheetFormCustomerNumberPlateInputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addGroup( addWorkSheetFormPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE )
                            .addComponent( addWorkSheetFormColleagueLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE )
                            .addComponent( addWorkSheetFormColleagueComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addGroup( addWorkSheetFormPanelLayout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                            .addComponent( addWorkSheetFormWorkHoursLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE )
                            .addComponent( addWorkSheetFormWorkHoursInputField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                            .addComponent( addWorkSheetFormWorkHoursLabel2 ) )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addComponent( addWorkSheetFormCarPartsLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent( jScrollPane1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addComponent( addWorkSheetFormProblemLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE )
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                        .addComponent( jScrollPane2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE ) )
                    .addComponent( submitFormBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                    .addComponent( resetFormBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE ) )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE )
                .addComponent( addWorkSheetFormInfoLabel )
                .addContainerGap() ) );

        contentPanelLayout = new GroupLayout( contentPanel );
        contentPanel.setLayout( contentPanelLayout );
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( contentPanelLayout.createSequentialGroup()
                .addGroup( contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
                    .addGroup( contentPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent( addWorkSheetPageLabel, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE ) )
                    .addGroup( contentPanelLayout.createSequentialGroup()
                        .addGap( 98, 98, 98 )
                        .addComponent( addWorkSheetFormPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) ) )
                .addContainerGap( 122, Short.MAX_VALUE ) ) );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent( addWorkSheetPageLabel,  GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( addWorkSheetFormPanel,  GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addContainerGap() ) );
    }
    
    private void renderExitView() 
    {
        renderBasicView( "Viszont látásra." );        
        controller.exitProgram();
    }
    
    private void renderFrameComponents() 
    {
        createInstancesOfComponentsForViewFrame();
        initMenuBar();
        initFooterPanel();                
        initAuthorLabel();
        initNeptunkodLabel();
        initFooterPanelLayout();
        initHeaderPanel();
        initSoftwareNameLabel();
        initLogoPane();
        initLogoPaneLayout();
        initHeaderPanelLayout();
        initMainPanel();
        initSideBarPanel();
        initColleaguesBtn();
        initWorkSheetsBtn();
        initCustomersBtn();
        initCarPartsBtn();
        initRefreshBtn();
        initExitBtn();
        initVersionLabel();
        initSideBarPanelLayout();
        initContentPanel();
        initContentPanelLayout();
        initMainPanelLayout();
        initLayout();
        pack();
    }

    private void createInstancesOfComponentsForViewFrame()
    {
        logoPane = new JLayeredPane();
        footerPanel = new JPanel();
        headerPanel = new JPanel();
        mainPanel = new JPanel();
        sideBarPanel = new JPanel();
        contentPanel = new JPanel();        
        colleaguesBtn = new JButton();
        workSheetsBtn = new JButton();
        customersBtn = new JButton();
        carPartsBtn = new JButton();
        refreshBtn = new JButton();
        exitBtn = new JButton();
        authorLabel = new JLabel();
        neptunkodLabel = new JLabel();
        softwareNameLabel = new JLabel();
        versionLabel = new JLabel();
    }

    private void initMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
 
        JMenu menu = new JMenu( "File" );
        menu.setMnemonic( KeyEvent.VK_A );

        menuBar.setBackground( darkGrey );
        menu.setForeground( white );

        JMenuItem menuItemNewConnection = new JMenuItem( "Csatlakozás" );
        menuItemNewConnection.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                controller.changeView( ViewState.CONNECTIONSETTINGS );
            }
        } );
        JMenuItem menuItemLooseConnection = new JMenuItem( "Lecsatlakozás" );
        menuItemLooseConnection.setEnabled( false );                
        //menuItemExit.setMnemonic( KeyEvent.VK_Q );
        JMenuItem menuItemSaveData = new JMenuItem( "Módosítások mentése" );
        menuItemSaveData.setEnabled( false );
        //menuItemExit.setMnemonic( KeyEvent.VK_Q );
        JMenuItem menuItemRefresh = new JMenuItem( "Adatok frissítése" );
        menuItemRefresh.setEnabled( false );
        //menuItemExit.setMnemonic( KeyEvent.VK_Q );
        JMenuItem menuItemExit = new JMenuItem( "Kilépés a programból" );
        menuItemExit.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                controller.exitProgram();
            }
        } );
        //menuItemExit.setMnemonic( KeyEvent.VK_Q );

        //JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem();
        //JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem();                

        menu.add( menuItemNewConnection );
        menu.add( menuItemLooseConnection );
        menu.add( menuItemSaveData );
        menu.add( menuItemRefresh );
        menu.add( menuItemExit );
        menuBar.add( menu );                
        setJMenuBar( menuBar );
    }

    private void initFooterPanel() 
    {
        footerPanel.setBackground( blue );
        footerPanel.setMaximumSize( new Dimension( 600, 30 ) );
        footerPanel.setMinimumSize( new Dimension( 600, 30 ) );
        footerPanel.setPreferredSize( new Dimension( 600, 30 ) );
    }

    private void initAuthorLabel() 
    {
        authorLabel.setForeground( white );
        authorLabel.setText( "Készítette: Nagy Roland Sándor" );
    }

    private void initNeptunkodLabel() 
    {
        neptunkodLabel.setForeground( white );
        neptunkodLabel.setText( "Neptun kód: SDKGMD" );
    }

    private void initFooterPanelLayout() 
    {
        footerPanelLayout = new GroupLayout( footerPanel );
        footerPanel.setLayout( footerPanelLayout );
        footerPanelLayout.setHorizontalGroup(
            footerPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( footerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent( authorLabel )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addComponent( neptunkodLabel )
                .addContainerGap() ) );
        footerPanelLayout.setVerticalGroup(
            footerPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( GroupLayout.Alignment.TRAILING, footerPanelLayout.createSequentialGroup()
                .addContainerGap( 25, Short.MAX_VALUE )
                .addGroup( footerPanelLayout.createParallelGroup( GroupLayout.Alignment.BASELINE )
                    .addComponent( authorLabel )
                    .addComponent( neptunkodLabel ) )
                .addContainerGap() ) );
    }

    private void initHeaderPanel() 
    {
        headerPanel.setBackground( blue );
        headerPanel.setMaximumSize(new Dimension(1024, 60));
        headerPanel.setMinimumSize(new Dimension(1024, 60));
        headerPanel.setPreferredSize(new Dimension(1024, 60));
    }

    private void initSoftwareNameLabel() 
    {
        softwareNameLabel.setFont(new Font( "Trajan Pro", 0, 24 ) );
        softwareNameLabel.setForeground( white );
        softwareNameLabel.setText( "Autószerviz" );
    }

    private void initLogoPane() 
    {
        logoPane.setBackground( white );
        logoPane.setMaximumSize( new Dimension( 40, 40 ) );
        logoPane.setMinimumSize( new Dimension( 40, 40 ) );
        logoPane.setOpaque( true );
    }

    private void initLogoPaneLayout() 
    {
        LogoPaneLayout = new GroupLayout( logoPane );
        logoPane.setLayout( LogoPaneLayout );
        LogoPaneLayout.setHorizontalGroup(
            LogoPaneLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGap( 0, 40, Short.MAX_VALUE ) );
        LogoPaneLayout.setVerticalGroup(
            LogoPaneLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGap( 0, 40, Short.MAX_VALUE ) );
    }

    private void initHeaderPanelLayout() 
    {
        headerPanelLayout = new GroupLayout( headerPanel );
        headerPanel.setLayout( headerPanelLayout );
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( headerPanelLayout.createSequentialGroup()
                .addGap( 27, 27, 27 )
                .addComponent( softwareNameLabel, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addComponent( logoPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                .addGap( 31, 31, 31 ) ) );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( GroupLayout.Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( headerPanelLayout.createParallelGroup( GroupLayout.Alignment.TRAILING )
                    .addGroup( GroupLayout.Alignment.LEADING, headerPanelLayout.createSequentialGroup()
                        .addGap( 0, 0, Short.MAX_VALUE )
                        .addComponent( logoPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) )
                    .addComponent( softwareNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) )
                .addContainerGap() ) );
    }

    private void initMainPanel() 
    {
        mainPanel.setBackground( green );
        mainPanel.setMaximumSize( new Dimension( 994, 330 ) );
        mainPanel.setMinimumSize( new Dimension( 994, 330 ) );
        mainPanel.setPreferredSize( new Dimension( 994, 330 ) );
    }

    private void initSideBarPanel() 
    {
        sideBarPanel.setBackground( blue );
        sideBarPanel.setMaximumSize( new Dimension( 120, 308 ) );
        sideBarPanel.setMinimumSize( new Dimension( 120, 308 ) );
    }

    private void initColleaguesBtn() 
    {
        colleaguesBtn.setText( "Munkatársak" );
        colleaguesBtn.setBorderPainted( false );
        colleaguesBtn.setFocusPainted( false );
        colleaguesBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        colleaguesBtnActionListener = new MenuButtonListener( controller, ViewState.COLLEAGUES );
        colleaguesBtn.addActionListener( colleaguesBtnActionListener );
    }

    private void initWorkSheetsBtn() 
    {
        workSheetsBtn.setText( "Munkalapok" );
        workSheetsBtn.setBorderPainted( false );
        workSheetsBtn.setFocusPainted( false );
        workSheetsBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        workSheetsBtnActionListener = new MenuButtonListener( controller, ViewState.WORKSHEETS );
        workSheetsBtn.addActionListener( workSheetsBtnActionListener );
    }

    private void initCustomersBtn() 
    {
        customersBtn.setText( "Ügyfelek" );
        customersBtn.setBorderPainted( false );
        customersBtn.setFocusPainted( false );
        customersBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        customersBtnActionListener = new MenuButtonListener( controller, ViewState.CUSTOMERS );
        customersBtn.addActionListener( customersBtnActionListener );
    }

    private void initCarPartsBtn() 
    {
        carPartsBtn.setText( "Alkatrészek" );
        carPartsBtn.setBorderPainted( false );
        carPartsBtn.setFocusPainted( false );
        carPartsBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        carPartsBtnActionListener = new MenuButtonListener( controller, ViewState.STORE );
        carPartsBtn.addActionListener( carPartsBtnActionListener );
    }

    private void initRefreshBtn() 
    {
        refreshBtn.setText( "Frissítés" );
        refreshBtn.setBorderPainted( false );
        refreshBtn.setFocusPainted( false );
        refreshBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        refreshBtnActionListener = new RefreshButtonListener( controller );
        refreshBtn.addActionListener( refreshBtnActionListener );
    }

    private void initExitBtn() 
    {
        exitBtn.setText("Kilépés");
        exitBtn.setBorderPainted( false );
        exitBtn.setFocusPainted( false );
        exitBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
        exitBtnActionListener = new MenuButtonListener( controller, ViewState.EXIT );
        exitBtn.addActionListener( exitBtnActionListener );
    }

    private void initVersionLabel() 
    {
        versionLabel.setText( "v 1.0" );
        versionLabel.setForeground( white );
    }

    private void initSideBarPanelLayout() 
    {
        sideBarPanelLayout = new GroupLayout( sideBarPanel );
        sideBarPanel.setLayout( sideBarPanelLayout );
        sideBarPanelLayout.setHorizontalGroup(
            sideBarPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( GroupLayout.Alignment.TRAILING, sideBarPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( sideBarPanelLayout.createParallelGroup( GroupLayout.Alignment.TRAILING )
                    .addComponent( workSheetsBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE )
                    .addComponent( colleaguesBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                    .addComponent( customersBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE )
                    .addComponent( carPartsBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE )
                    .addComponent( refreshBtn, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE )
                    .addComponent( exitBtn, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE ) )
                .addContainerGap() )
            .addGroup( GroupLayout.Alignment.TRAILING, sideBarPanelLayout.createSequentialGroup()
                .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addComponent( versionLabel, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE )
                .addGap( 40, 40, 40 ) ) );
        sideBarPanelLayout.setVerticalGroup(
            sideBarPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( sideBarPanelLayout.createSequentialGroup()
                .addGap( 59, 59, 59 )
                .addComponent( workSheetsBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( colleaguesBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( customersBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( carPartsBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( refreshBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( exitBtn, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addComponent( versionLabel ) ) );
    }

    private void initContentPanel() 
    {
        contentPanel.setBackground( blue );
    }

    private void initContentPanelLayout() 
    {
        contentPanelLayout = new GroupLayout( contentPanel );
        contentPanel.setLayout( contentPanelLayout );
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGap( 0, 836, Short.MAX_VALUE ) );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGap( 0, 308, Short.MAX_VALUE ) );
    }

    private void initMainPanelLayout() 
    {
        mainPanelLayout = new GroupLayout( mainPanel );
        mainPanel.setLayout( mainPanelLayout );
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent( sideBarPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                .addGap( 18, 18, 18 )
                .addComponent( contentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addContainerGap() ) );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup( mainPanelLayout.createParallelGroup( GroupLayout.Alignment.TRAILING )
                    .addComponent( contentPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                    .addComponent( sideBarPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE ) )
                .addContainerGap() ) );
    }

    private void initLayout() 
    {
        layout = new GroupLayout( getContentPane() );
        getContentPane().setLayout( layout );
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addComponent(footerPanel, GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE )
            .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
            .addGroup( layout.createSequentialGroup()
                .addContainerGap()
                .addComponent( mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                .addContainerGap( 20, Short.MAX_VALUE ) ) );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING )
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent( headerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED )
                .addComponent( mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE )
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED )
                .addComponent( footerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE ) ) );
    }

    @Override
    public boolean notAllFilterInputNull() 
    {
        return (    ( !( (String)filterCustomerComboBox.getSelectedItem() ).equals( "" ) ) 
                 || ( !( (String)filterStateComboBox.getSelectedItem() ).equals( "" ) )
                 || ( !( (String)filterColleagueComboBox.getSelectedItem() ).equals( "" ) )                
                );
    }

    @Override
    public void enableFilterButton() 
    {
        filterBtn.setEnabled( true );        
        filterBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
    }

    @Override
    public void disableFilterButton() 
    {
        filterBtn.setEnabled( false );
        filterBtn.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
    }

    @Override
    public void enableSaveDataButton() 
    {
        saveDataBtn.setEnabled( true );        
        saveDataBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
    }

    @Override
    public void disableSaveDataButton() 
    {
        saveDataBtn.setEnabled( false );
        saveDataBtn.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
    }
    
    @Override
    public void enableDeleteButton() 
    {
        deleteBtn.setEnabled( true );        
        deleteBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
    }

    @Override
    public void disableDeleteButton() 
    {
        deleteBtn.setEnabled( false );
        deleteBtn.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
    }

    @Override
    public void enableFinalizeButton() 
    {
        finalizeBtn.setEnabled( true );        
        finalizeBtn.setCursor( new Cursor( Cursor.HAND_CURSOR ) );
    }
    
    @Override
    public void disableFinalizeButton() 
    {
        finalizeBtn.setEnabled( false );        
        finalizeBtn.setCursor( new Cursor( Cursor.DEFAULT_CURSOR ) );
    }
    
    @Override
    public void gatherInformationFromForm() 
    {
        addWorkSheetFormCarPartsInputAreaText = addWorkSheetFormCarPartsInputArea.getText();
        addWorkSheetFormColleagueComboBoxText = (String)addWorkSheetFormColleagueComboBox.getSelectedItem();
        addWorkSheetFormCustomerNameInputFieldText = addWorkSheetFormCustomerNameInputField.getText();
        addWorkSheetFormCustomerNumberPlateInputFieldText = addWorkSheetFormCustomerNumberPlateInputField.getText();
        addWorkSheetFormCustomerTelInputFieldText = addWorkSheetFormCustomerTelInputField.getText();
        addWorkSheetFormProblemInputAreaText = addWorkSheetFormProblemInputArea.getText();
        addWorkSheetFormWorkHoursInputFieldText = addWorkSheetFormWorkHoursInputField.getText();
    }

    @Override
    public String getAddWorkSheetFormCarPartsInputAreaText()
    {
        return addWorkSheetFormCarPartsInputAreaText;
    }

    @Override
    public String getAddWorkSheetFormColleagueComboBoxText()
    {
        return addWorkSheetFormColleagueComboBoxText;
    }

    @Override
    public String getAddWorkSheetFormCustomerNameInputFieldText()
    {
        return addWorkSheetFormCustomerNameInputFieldText;
    }

    @Override
    public String getAddWorkSheetFormCustomerNumberPlateInputFieldText()
    {
        return addWorkSheetFormCustomerNumberPlateInputFieldText;
    }

    @Override
    public String getAddWorkSheetFormCustomerTelInputFieldText()
    {
        return addWorkSheetFormCustomerTelInputFieldText;
    }

    @Override
    public String getAddWorkSheetFormProblemInputAreaText()
    {
        return addWorkSheetFormProblemInputAreaText;
    }

    @Override
    public String getAddWorkSheetFormWorkHoursInputFieldText()
    {
        return addWorkSheetFormWorkHoursInputFieldText;
    }

    @Override
    public void highLightCustomerNameInputField() 
    {
        addWorkSheetFormCustomerNameInputField.setBackground( red );
    }

    @Override
    public void highLightCustomerTelInputField() 
    {
        addWorkSheetFormCustomerTelInputField.setBackground( red );
    }

    @Override
    public void highLightCustomerNumberPlateField() 
    {
        addWorkSheetFormCustomerNumberPlateInputField.setBackground( red );
    }

    @Override
    public void highLightProblemInputField() 
    {
        addWorkSheetFormProblemInputArea.setBackground( red );        
    }

    @Override
    //public ArrayList<WorkSheet> getTableContentInArrayList() 
    public ArrayList<WorkSheet> getWorkSheetsFromContentTable() 
    {
        ArrayList<WorkSheet> workSheetsToCompare = new ArrayList<WorkSheet>();    
        
        int rc = contentTable.getRowCount();
        System.out.println( "rowcount: " + rc );
        for( int i = 0; i < rc; i++ ) 
        {
            int id = (int)contentTable.getModel().getValueAt( i, 0 );
            String date = contentTable.getModel().getValueAt( i, 1 ).toString();
            int customer = (int)contentTable.getModel().getValueAt( i, 2 );
            String problem = contentTable.getModel().getValueAt( i, 3 ).toString();
            String parts = contentTable.getModel().getValueAt( i, 4 ).toString();
            int colleague = (int)contentTable.getModel().getValueAt( i, 5 );
            int time = (int)contentTable.getModel().getValueAt(i, 6);
            int price = (int)contentTable.getModel().getValueAt(i, 7);
            WorkSheetState state = convertStringToWorkSheetStateEnum( contentTable.getModel().getValueAt(i, 8).toString() );

            WorkSheet workSheet = new WorkSheet( id, date, customer, problem, parts, colleague, time, price, state ); 
            System.out.println( workSheet );
            workSheetsToCompare.add( workSheet );
        }
        
        return workSheetsToCompare;
    }

    private WorkSheetState convertStringToWorkSheetStateEnum( String stateString ) 
    {
        if( stateString.equals( "FELDOLGOZANDO" ) )
            return WorkSheetState.FELDOLGOZANDO;
        if( stateString.equals( "FELDOLGOZOTT" ) )
            return WorkSheetState.FELDOLGOZOTT;
        if( stateString.equals( "ALKATRESZREVAR" ) )
            return WorkSheetState.ALKATRESZREVAR;
        if( stateString.equals( "FOLYAMATBAN" ) )
            return WorkSheetState.FOLYAMATBAN;
        if( stateString.equals( "KIFIZETETT" ) )
            return WorkSheetState.KIFIZETETT;

        return WorkSheetState.UNDEFINED;
    }

    @Override
    public int getSelectedRowNumber() 
    {
        return contentTable.getSelectedRow();
    }

    @Override
    public int getSelectedColNumber() 
    {
        return contentTable.getSelectedColumn();
    }

    @Override
    public void removeListeners() 
    {
        contentTable.removeFocusListener( contentTableFocusListener );
    }

    @Override
    public ArrayList<Integer> getSelectedWorkSheetsIds() 
    {
        ArrayList<Integer> ids = new ArrayList<>();

        int startRowNo = contentTable.getSelectedRow();
        int numberOfRows = contentTable.getSelectedRowCount();

        for( int i = startRowNo; i < startRowNo + numberOfRows; ++i ) 
        {
            int id = (int)contentTable.getValueAt( i, 0 );
            ids.add( id );
        }
        
        return ids;
    }

    @Override
    public String getFilterCustomerString() 
    {
        return this.filterCustomerComboBox.getSelectedItem().toString();
    }

    @Override
    public String getFilterStateString() 
    {
        return this.filterStateComboBox.getSelectedItem().toString();
    }

    @Override
    public String getFilterColleagueString() 
    {
        return this.filterColleagueComboBox.getSelectedItem().toString();
    }

    private void renderConnectionSettingsView() 
    {
        connectionSettingsFrame = new javax.swing.JInternalFrame();
        connectionSettingsConnectionLabel = new javax.swing.JLabel();
        connectionSettingsDataBaseLabel = new javax.swing.JLabel();
        connectionSettingsUserLabel = new javax.swing.JLabel();
        connectionSettingsPasswordLabel = new javax.swing.JLabel();
        connectionSettingsConnectionsComboBox = new javax.swing.JComboBox<>();
        connectionSettingsDataBaseInputField = new javax.swing.JTextField();
        connectionSettingsUserInputField = new javax.swing.JTextField();
        connectionSettingsPasswordInputField = new javax.swing.JTextField();
        connectionSettingsFrameLabel = new javax.swing.JLabel();
        connectionSettingsTestButton = new javax.swing.JButton();
        connectionSettingsSaveButton = new javax.swing.JButton();
        connectionSettingsTestLabel = new javax.swing.JLabel();
        connectionSettingsCancelButton = new javax.swing.JButton();


        connectionSettingsDataBaseInputField.addKeyListener( new ConnectionSettingsInputFieldKeyListener( this ) ); 
        connectionSettingsUserInputField.addKeyListener( new ConnectionSettingsInputFieldKeyListener( this ) );
        connectionSettingsPasswordInputField.addKeyListener( new ConnectionSettingsInputFieldKeyListener( this ) );        
        
        connectionSettingsFrame.setTitle("Beállítások");
        connectionSettingsFrame.setVisible(true);

        connectionSettingsConnectionLabel.setText("Kapcsolat:");

        connectionSettingsDataBaseLabel.setText("Adatbázis:");

        connectionSettingsUserLabel.setText("Felhasználó:");

        connectionSettingsPasswordLabel.setText("Jelszó:");

        connectionSettingsConnectionsComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "jdbc:mysql://localhost/" }));

        connectionSettingsFrameLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        connectionSettingsFrameLabel.setText("Kapcsolat beállítások:");

        connectionSettingsTestButton.setText("Test");
        connectionSettingsTestButton.setEnabled( false );
        connectionSettingsTestButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String hostString = connectionSettingsConnectionsComboBox.getSelectedItem().toString();
                String dataBaseString = connectionSettingsDataBaseInputField.getText();
                String userString = connectionSettingsUserInputField.getText();        
                String passwordString = connectionSettingsPasswordInputField.getText();
                if( controller.testConnection( hostString, dataBaseString, userString, passwordString ) )
                {
                    connectionSettingsSaveButton.setEnabled( true );                    
                    connectionSettingsTestLabel.setText("Teszt: Sikeres.");
                }
                else
                {
                    connectionSettingsSaveButton.setEnabled( false );                                        
                    connectionSettingsTestLabel.setText("Teszt: Sikertelen.");
                }
            }
        } );
        
        connectionSettingsSaveButton.setText("Save Settings");
        connectionSettingsSaveButton.setEnabled( false );
        connectionSettingsSaveButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                switchAllSideMenuButtonsExceptExit( true );
                controller.refresh();
                controller.changeView( ViewState.HOME );
            }
        } );
                
        connectionSettingsTestLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        connectionSettingsTestLabel.setText("");
        connectionSettingsTestLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        connectionSettingsCancelButton.setText("Mégse");
        connectionSettingsCancelButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                controller.changeView( ViewState.HOME );
            }
        } );

        javax.swing.GroupLayout connectionSettingsFrameLayout = new javax.swing.GroupLayout(connectionSettingsFrame.getContentPane());
        connectionSettingsFrame.getContentPane().setLayout(connectionSettingsFrameLayout);
        connectionSettingsFrameLayout.setHorizontalGroup(
            connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, connectionSettingsFrameLayout.createSequentialGroup()
                .addContainerGap(58, Short.MAX_VALUE)
                .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectionSettingsTestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                            .addComponent(connectionSettingsUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(connectionSettingsUserInputField))
                        .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                            .addComponent(connectionSettingsDataBaseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(connectionSettingsDataBaseInputField))
                        .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                            .addComponent(connectionSettingsConnectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(connectionSettingsConnectionsComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                            .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(connectionSettingsPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(connectionSettingsTestButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                            .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(connectionSettingsSaveButton)
                                    .addGap(40, 40, 40)
                                    .addComponent(connectionSettingsCancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))
                                .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                                    .addGap(18, 18, 18)
                                    .addComponent(connectionSettingsPasswordInputField))))
                        .addComponent(connectionSettingsFrameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(78, 78, 78))
        );
        connectionSettingsFrameLayout.setVerticalGroup(
            connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(connectionSettingsFrameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectionSettingsConnectionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionSettingsConnectionsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(connectionSettingsDataBaseLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionSettingsDataBaseInputField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                        .addComponent(connectionSettingsUserInputField)
                        .addGap(5, 5, 5))
                    .addComponent(connectionSettingsUserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(connectionSettingsFrameLayout.createSequentialGroup()
                        .addComponent(connectionSettingsPasswordInputField)
                        .addGap(5, 5, 5))
                    .addComponent(connectionSettingsPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(connectionSettingsFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(connectionSettingsTestButton)
                    .addComponent(connectionSettingsSaveButton)
                    .addComponent(connectionSettingsCancelButton))
                .addGap(18, 18, 18)
                .addComponent(connectionSettingsTestLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentPanelLayout = new javax.swing.GroupLayout(contentPanel);
        contentPanel.setLayout(contentPanelLayout);
        contentPanelLayout.setHorizontalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPanelLayout.createSequentialGroup()
                .addContainerGap(180, Short.MAX_VALUE)
                .addComponent(connectionSettingsFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        contentPanelLayout.setVerticalGroup(
            contentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPanelLayout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(connectionSettingsFrame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        
    }

    public String getConnectionSettingsHostInputFieldText() 
    {
        return connectionSettingsConnectionsComboBox.getSelectedItem().toString();
    }

    public String getConnectionSettingsDataBaseInputFieldText() 
    {
        return connectionSettingsDataBaseInputField.getText();
    }

    public String getConnectionSettingsUserInputFieldText() 
    {
        return connectionSettingsUserInputField.getText();
    }

    public String getConnectionSettingsPasswordInputFieldText() 
    {
        return connectionSettingsPasswordInputField.getText();
    }

    public void setConnectionSettingsTestButtonEnabled( boolean b ) 
    {
        connectionSettingsTestButton.setEnabled( b );
    }
}
