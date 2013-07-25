/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package posTest2;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jpos.CashDrawer;
import jpos.config.JposEntry;
import jpos.config.simple.xml.SimpleXmlRegPopulator;
import jpos.loader.JposServiceInstance;
import jpos.profile.JposDevCats;

public class POSTest2Controller implements Initializable {

	@FXML private TableView<Device> deviceTable;
	@FXML private TableColumn<Device, String> category;
    @FXML private TableColumn<Device, String> logicalName;
    @FXML private TableColumn<Device, String> vendor;
    @FXML private TableColumn<Device, String> productName;
    
    /*The logicalName checkBoxes */
    @FXML private ComboBox<String> logicalName_belt;
    @FXML private ComboBox<String> logicalName_billAcceptor;
    @FXML private ComboBox<String> logicalName_billDispenser;
    @FXML private ComboBox<String> logicalName_biometrics;
    @FXML private ComboBox<String> logicalName_bumpBar;
    @FXML private ComboBox<String> logicalName_cashChanger;
    @FXML private ComboBox<String> logicalName_cashDrawer;
    @FXML private ComboBox<String> logicalName_CAT;
    @FXML private ComboBox<String> logicalName_checkScanner;
    @FXML private ComboBox<String> logicalName_coinAcceptor;
    @FXML private ComboBox<String> logicalName_coinDispenser;
    @FXML private ComboBox<String> logicalName_electronicJournal;
    @FXML private ComboBox<String> logicalName_electronicValueRW;
    @FXML private ComboBox<String> logicalName_fiscalPrinter;
    @FXML private ComboBox<String> logicalName_gate;
    @FXML private ComboBox<String> logicalName_hardTotals;
    @FXML private ComboBox<String> logicalName_imageScanner;
    @FXML private ComboBox<String> logicalName_itemDispenser;
    @FXML private ComboBox<String> logicalName_keylock;
    @FXML private ComboBox<String> logicalName_lights;
    @FXML private ComboBox<String> logicalName_LineDisplay;
    @FXML private ComboBox<String> logicalName_MICR;
    @FXML private ComboBox<String> logicalName_motionSensor;
    @FXML private ComboBox<String> logicalName_MSR;
    @FXML private ComboBox<String> logicalName_PINPad;
    @FXML private ComboBox<String> logicalName_pointCardRW;
    @FXML private ComboBox<String> logicalName_POSKeyboard;
    @FXML private ComboBox<String> logicalName_POSPower;
    @FXML private ComboBox<String> logicalName_POSPrinter;
    @FXML private ComboBox<String> logicalName_remoteOrderDisplay;
    @FXML private ComboBox<String> logicalName_RFIDScanner;
    @FXML private ComboBox<String> logicalName_scale;
    @FXML private ComboBox<String> logicalName_scanner;
    @FXML private ComboBox<String> logicalName_signatureCapture;
    @FXML private ComboBox<String> logicalName_smartCardRW;
    @FXML private ComboBox<String> logicalName_toneIndicator;
    
    
    //List for the ConfiguredDevices Page
    ObservableList<Device> devicesList;
    
    //Services for all 
    private JposServiceInstance beltService;
    private JposServiceInstance billAcceptorService;
    private JposServiceInstance billDispenserService;
    private JposServiceInstance biometricsService;
    private JposServiceInstance bumpBarService;
    private JposServiceInstance cashChangerService;
    private JposServiceInstance cashDrawerService;
    private JposServiceInstance CATService;
    private JposServiceInstance checkScannerService;
    private JposServiceInstance coinAcceptorService;
    private JposServiceInstance coinDispenserService;
    private JposServiceInstance electronicJournalService;
    private JposServiceInstance electronicValueRWService;
    private JposServiceInstance fiscalPrinterService;
    private JposServiceInstance gateService;
    private JposServiceInstance hardTotalsService;
    private JposServiceInstance imageScannerService;
    private JposServiceInstance itemDispenserService;
    private JposServiceInstance keylockService;
    private JposServiceInstance lightsService;
    private JposServiceInstance lineDisplayService;
    private JposServiceInstance MICRService;
    private JposServiceInstance motionSensorService;
    private JposServiceInstance MSRService;
    private JposServiceInstance PINPadService;
    private JposServiceInstance pointCardRWService;
    private JposServiceInstance POSKeyboardService;
    private JposServiceInstance POSPowerService;
    private JposServiceInstance POSPrinterService;
    private JposServiceInstance remoteOrderDisplayService;
    private JposServiceInstance RFIDScannerService;
    private JposServiceInstance scaleService;
    private JposServiceInstance scannerService;
    private JposServiceInstance signatureCaptureService;
    private JposServiceInstance smartCardRWService;
    private JposServiceInstance toneIndicatorService;
    
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpApplication();
		

	    CashDrawer c = new CashDrawer();
		
	}
	
	/**
	 * Sets the DevicesList and the LogicalNamesList
	 */
	private void setUpLists() {
		SimpleXmlRegPopulator pop = new SimpleXmlRegPopulator("jpos.xml");
		pop.load();
		Enumeration<JposEntry> enu = pop.getEntries();
		
	    ArrayList<Device> devices = new ArrayList<Device>();
	    
		while(enu.hasMoreElements()){
			JposEntry je=(JposEntry)enu.nextElement();
			
				String devCat = je.getPropertyValue(JposEntry.DEVICE_CATEGORY_PROP_NAME).toString();
				String devLogName = je.getLogicalName();
				String devVendor = je.getPropertyValue(JposEntry.VENDOR_NAME_PROP_NAME).toString();
				String devProductName = je.getPropertyValue(JposEntry.PRODUCT_NAME_PROP_NAME).toString();
				
				Device device = new Device(devCat, devLogName, devVendor, devProductName);
				
				devices.add(device);
				
		}
		
		devicesList = FXCollections.observableList(devices);
	}
	
	/**
	 * Fill devices table with the Data from the DeviceList
	 */
	private void setUpDeviceTable() {
		
		category.setCellValueFactory(new PropertyValueFactory<Device, String>("category"));
		logicalName.setCellValueFactory(new PropertyValueFactory<Device, String>("logicalName"));
		vendor.setCellValueFactory(new PropertyValueFactory<Device, String>("vendor"));
		productName.setCellValueFactory(new PropertyValueFactory<Device, String>("productName"));
		
		deviceTable.setItems(devicesList);
	}
	
	/**
	 * Initializes all GUI-Elements
	 */
	private void setUpApplication(){
		setUpLists();
		setUpDeviceTable();
		
		//Init all logicalName Comboboxes
		
		setUpBeltComboBox();
		setUpBillAcceptorComboBox();
		setUpBillDispenserComboBox();
		setUpBiometricsComboBox();
		setUpBumpBarComboBox();
		setUpCashChangerComboBox();
		setUpCashDrawerComboBox();
		setUpCATrComboBox();
		setUpCheckScannerComboBox();
		setUpCoinAcceptorComboBox();
		setUpCoinDispenserComboBox();
		setUpElectronicJournalComboBox();
		setUpElectronicValueRWComboBox();
		setUpFiscalPrinterComboBox();
		setUpGateComboBox();
		setUpHardTotalsComboBox();
		setUpImageScannerComboBox();
		setUpItemDispenserComboBox();
		setUpKeylockComboBox();
		setUpLightsComboBox();
		setUpLineDisplayComboBox();
		setUpMICRComboBox();
		setUpMotionSensorComboBox();
		setUpMSRComboBox();
		setUpPINPadComboBox();
		setUpPointCardRWComboBox();
		setUpPOSKeyboardComboBox();
		setUpPOSPowerComboBox();
		setUpPOSPrinterComboBox();
		setUpRemoteOrderDisplayComboBox();
		setUpRFIDScannerComboBox();
		setUpScaleComboBox();
		setUpScannerComboBox();
		setUpSignatureCaptureComboBox();
		setUpSmartCardRWComboBox();
		setUpToneIndicatorComboBox();
	}
	
	/*
	 * 
	 * Configure the LogicalName ComboBoxes
	 * 
	 */
	private ObservableList<String> getLogicalNames(String category){
		ArrayList<String> logicalNames = new ArrayList<String>();
		for (Device d : devicesList){
			if (d.getCategory().equals(category)){
				logicalNames.add(d.getLogicalName());
			}
		}
		return FXCollections.observableArrayList(logicalNames);
		
	}
	
	private void setUpBeltComboBox(){
		logicalName_belt.setItems(getLogicalNames("Belt"));
	}
	
	private void setUpBillAcceptorComboBox(){
		logicalName_billAcceptor.setItems(getLogicalNames("BillAcceptor"));
	}
	
	private void setUpBillDispenserComboBox(){
		logicalName_billDispenser.setItems(getLogicalNames("BillDispenser"));
	}
	
	private void setUpBiometricsComboBox(){
		logicalName_biometrics.setItems(getLogicalNames("Biometrics"));
	}
	
	private void setUpBumpBarComboBox(){
		logicalName_bumpBar.setItems(getLogicalNames(JposDevCats.BUMPBAR_DEVCAT.toString()));
	}
	
	private void setUpCashChangerComboBox(){
		logicalName_cashChanger.setItems(getLogicalNames(JposDevCats.CASHCHANGER_DEVCAT.toString()));
	}
	
	private void setUpCashDrawerComboBox(){
		logicalName_cashDrawer.setItems(getLogicalNames(JposDevCats.CASHDRAWER_DEVCAT.toString()));
	}
	
	private void setUpCATrComboBox(){
		logicalName_CAT.setItems(getLogicalNames(JposDevCats.CAT_DEVCAT.toString()));
	}
	
	private void setUpCheckScannerComboBox(){
		logicalName_checkScanner.setItems(getLogicalNames(JposDevCats.CHECKSCANNER_DEVCAT.toString()));
	}

	private void setUpCoinAcceptorComboBox(){
		logicalName_coinAcceptor.setItems(getLogicalNames("CoinAcceptor"));
	}
	
	private void setUpCoinDispenserComboBox(){
		logicalName_coinDispenser.setItems(getLogicalNames(JposDevCats.COINDISPENSER_DEVCAT.toString()));
	}
	
	private void setUpElectronicJournalComboBox(){
		logicalName_electronicJournal.setItems(getLogicalNames("ElectronicJournal"));
	}
	
	private void setUpElectronicValueRWComboBox(){
		logicalName_electronicValueRW.setItems(getLogicalNames("ElectronicValueRW"));
	}
	
	private void setUpFiscalPrinterComboBox(){
		logicalName_fiscalPrinter.setItems(getLogicalNames(JposDevCats.FISCALPRINTER_DEVCAT.toString()));
	}

	private void setUpGateComboBox(){
		logicalName_gate.setItems(getLogicalNames("Gate"));
	}
	
	private void setUpHardTotalsComboBox(){
		logicalName_hardTotals.setItems(getLogicalNames(JposDevCats.HARDTOTALS_DEVCAT.toString()));
	}

	private void setUpImageScannerComboBox(){
		logicalName_imageScanner.setItems(getLogicalNames("ImageScanner"));
	}

	private void setUpItemDispenserComboBox(){
		logicalName_itemDispenser.setItems(getLogicalNames("ItemDispenser"));
	}
	
	private void setUpKeylockComboBox(){
		logicalName_keylock.setItems(getLogicalNames(JposDevCats.KEYLOCK_DEVCAT.toString()));
	}

	private void setUpLightsComboBox(){
		logicalName_lights.setItems(getLogicalNames("Lights"));
	}
	
	private void setUpLineDisplayComboBox(){
		logicalName_LineDisplay.setItems(getLogicalNames(JposDevCats.LINEDISPLAY_DEVCAT.toString()));
	}
	
	private void setUpMICRComboBox(){
		logicalName_MICR.setItems(getLogicalNames(JposDevCats.MICR_DEVCAT.toString()));
	}
	
	private void setUpMotionSensorComboBox(){
		logicalName_motionSensor.setItems(getLogicalNames(JposDevCats.MOTIONSENSOR_DEVCAT.toString()));
	}
	
	private void setUpMSRComboBox(){
		logicalName_MSR.setItems(getLogicalNames(JposDevCats.MSR_DEVCAT.toString()));
	}
	
	private void setUpPINPadComboBox(){
		logicalName_PINPad.setItems(getLogicalNames(JposDevCats.PINPAD_DEVCAT.toString()));
	}
	
	private void setUpPointCardRWComboBox(){
		logicalName_pointCardRW.setItems(getLogicalNames(JposDevCats.POINTCARDRW_DEVCAT.toString()));
	}
	
	private void setUpPOSKeyboardComboBox(){
		logicalName_POSKeyboard.setItems(getLogicalNames(JposDevCats.POSKEYBOARD_DEVCAT.toString()));
	}
	
	private void setUpPOSPowerComboBox(){
		logicalName_POSPower.setItems(getLogicalNames(JposDevCats.POSPOWER_DEVCAT.toString()));
	}

	private void setUpPOSPrinterComboBox(){
		logicalName_POSPrinter.setItems(getLogicalNames(JposDevCats.POSPRINTER_DEVCAT.toString()));
	}
	
	private void setUpRemoteOrderDisplayComboBox(){
		logicalName_remoteOrderDisplay.setItems(getLogicalNames(JposDevCats.REMOTEORDERDISPLAY_DEVCAT.toString()));
	}
	
	private void setUpRFIDScannerComboBox(){
		logicalName_RFIDScanner.setItems(getLogicalNames("RFIDScanner"));
	}
	
	private void setUpScaleComboBox(){
		logicalName_scale.setItems(getLogicalNames(JposDevCats.SCALE_DEVCAT.toString()));
	}
	
	private void setUpScannerComboBox(){
		logicalName_scanner.setItems(getLogicalNames(JposDevCats.SCANNER_DEVCAT.toString()));
	}
	
	private void setUpSignatureCaptureComboBox(){
		logicalName_signatureCapture.setItems(getLogicalNames(JposDevCats.SIGNATURECAPTURE_DEVCAT.toString()));
	}
	
	private void setUpSmartCardRWComboBox(){
		logicalName_smartCardRW.setItems(getLogicalNames("SmartCardRW"));
	}
	
	private void setUpToneIndicatorComboBox(){
		logicalName_toneIndicator.setItems(getLogicalNames(JposDevCats.TONEINDICATOR_DEVCAT.toString()));
	}
	
	/* ************************************************************************
	 ************************* Action Handler**********************************
	 *************************************************************************/
	
	/*
	 * LineDiplay Action Handler
	 */
	public void handleOpen(ActionEvent e) {
		//JposServiceInstance service = 
        //label.setText("Accepted");
    }
	
	
	
	
}
