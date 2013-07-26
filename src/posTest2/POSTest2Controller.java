/*
 * Copyright 2013 NTS New Technology Systems GmbH. All Rights reserved.
 * NTS PROPRIETARY/CONFIDENTIAL. Use is subject to NTS License Agreement.
 * Address: Doernbacher Strasse 126, A-4073 Wilhering, Austria
 * Homepage: www.ntswincash.com
 */
package posTest2;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import jpos.config.JposEntry;
import jpos.config.simple.xml.SimpleXmlRegPopulator;

public class POSTest2Controller implements Initializable {

	@FXML private TableView<Device> deviceTable;
	@FXML private TableColumn<Device, String> category;
    @FXML private TableColumn<Device, String> logicalName;
    @FXML private TableColumn<Device, String> vendor;
    @FXML private TableColumn<Device, String> productName;
    
    /*The Devices Pane */
    @FXML private AnchorPane beltPane;
    @FXML private AnchorPane billAcceptorPane;
    @FXML private AnchorPane billDispenserPane;
    @FXML private AnchorPane biometricsPane;
    @FXML private AnchorPane bumpBarPane;
    @FXML private AnchorPane cashChangerPane;
    @FXML private AnchorPane cashDrawerPane;
    @FXML private AnchorPane CATPane;
    @FXML private AnchorPane checkScannerPane;
    @FXML private AnchorPane coinAcceptorPane;
    @FXML private AnchorPane coinDispenserPane;
    @FXML private AnchorPane electronicJournalPane;
    @FXML private AnchorPane electronicValueRWPane;
    @FXML private AnchorPane fiscalPrinterPane;
    @FXML private AnchorPane gatePane;
    @FXML private AnchorPane hardTotalsPane;
    @FXML private AnchorPane imageScannerPane;
    @FXML private AnchorPane itemDispenserPane;
    @FXML private AnchorPane keylockPane;
    @FXML private AnchorPane lightsPane;
    @FXML private AnchorPane lineDisplayPane;
    @FXML private AnchorPane MICRPane;
    @FXML private AnchorPane motionSensorPane;
    @FXML private AnchorPane MSRPane;
    @FXML private AnchorPane PINPadPane;
    @FXML private AnchorPane pointCardRWPane;
    @FXML private AnchorPane POSKeyboardPane;
    @FXML private AnchorPane POSPowerPane;
    @FXML private AnchorPane POSPrinterPane;
    @FXML private AnchorPane remoteOrderDisplayPane;
    @FXML private AnchorPane RFIDPane;
    @FXML private AnchorPane scalePane;
    @FXML private AnchorPane scannerPane;
    @FXML private AnchorPane signatureCapturePane;
    @FXML private AnchorPane smartCardRWPane;
    @FXML private AnchorPane toneIndicatorPane;
    
    //@FXML private AnchorPane aPane;
    
    
    //List for the ConfiguredDevices Page
    ObservableList<Device> devicesList;
    
    /*
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
    */
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpApplication();
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
		//Init all Device Tabs
		
		setUpBeltPane();
		setUpBillAcceptorPane();
		setUpBillDispenserPane();
		setUpBiometricsPane();
		setUpBumpBarPane();
		setUpCashChangerPane();
		setUpCashDrawerPane();
		setUpCATPane();
		setUpCheckScannerPane();
		setUpCoinAcceptorPane();
		setUpCoinDispenserPane();
		setUpElectronicJournalPane();
		setUpElectronicValueRWPane();
		setUpFiscalPrinterPane();
		setUpGatePane();
		setUpHardTotalsPane();
		setUpImageScannerPane();
		setUpItemDispenserPane();
		setUpKeylockPane();
		setUpLightsPane();
		setUpLineDisplayPane();
		setUpMICRPane();
		setUpMotionSensorPane();
		setUpMSRPane();
		setUpPINPadPane();
		setUpPointCardRWPane();
		setUpPOSKeyboardPane();
		setUpPOSPowerPane();
		setUpPOSPrinterPane();
		setUpRemoteOrderDisplayPane();
		setUpRFIDScannerPane();
		setUpScalePane();
		setUpScannerPane();
		setUpSignatureCapturePane();
		setUpSmartCardRWPane();
		setUpToneIndicatorPane();
	}
	
	/*
	 * 
	 * Configure the Device Tabs
	 * 
	 */
	
	private void setUpBeltPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/Belt.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		beltPane.getChildren().clear();
		beltPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpBillAcceptorPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/BillAcceptor.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		billAcceptorPane.getChildren().clear();
		billAcceptorPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpBillDispenserPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/BillDispenser.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		billDispenserPane.getChildren().clear();
		billDispenserPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpBiometricsPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/Biometrics.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		biometricsPane.getChildren().clear();
		biometricsPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpBumpBarPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/BumpBar.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		bumpBarPane.getChildren().clear();
		bumpBarPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpCashChangerPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/CashChanger.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		cashChangerPane.getChildren().clear();
		cashChangerPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpCashDrawerPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/CashDrawer.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		cashDrawerPane.getChildren().clear();
		cashDrawerPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpCATPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/CAT.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		CATPane.getChildren().clear();
		CATPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpCheckScannerPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/CheckScanner.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		checkScannerPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}

	private void setUpCoinAcceptorPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/CoinAcceptor.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		coinAcceptorPane.getChildren().clear();
		coinAcceptorPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpCoinDispenserPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/CoinDispenser.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		coinDispenserPane.getChildren().clear();
		coinDispenserPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpElectronicJournalPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/ElectronicJournal.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		electronicJournalPane.getChildren().clear();
		electronicJournalPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpElectronicValueRWPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/ElectronicValueRW.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		electronicValueRWPane.getChildren().clear();
		electronicValueRWPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpFiscalPrinterPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/FiscalPrinter.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		fiscalPrinterPane.getChildren().clear();
		fiscalPrinterPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}

	private void setUpGatePane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/Gate.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		gatePane.getChildren().clear();
		gatePane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpHardTotalsPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/HardTotals.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		hardTotalsPane.getChildren().clear();
		hardTotalsPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}

	private void setUpImageScannerPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/ImageScanner.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		imageScannerPane.getChildren().clear();
		imageScannerPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}

	private void setUpItemDispenserPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/ItemDispenser.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		itemDispenserPane.getChildren().clear();
		itemDispenserPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpKeylockPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/Keylock.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		keylockPane.getChildren().clear();
		keylockPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}

	private void setUpLightsPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/Lights.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lightsPane.getChildren().clear();
		lightsPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpLineDisplayPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/LineDisplay.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		lineDisplayPane.getChildren().clear();
		lineDisplayPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpMICRPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/MICR.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		MICRPane.getChildren().clear();
		MICRPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpMotionSensorPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/MotionSensor.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		motionSensorPane.getChildren().clear();
		motionSensorPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpMSRPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/MSR.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		MSRPane.getChildren().clear();
		MSRPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpPINPadPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/PINPad.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		PINPadPane.getChildren().clear();
		PINPadPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpPointCardRWPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/PointCardRW.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		pointCardRWPane.getChildren().clear();
		pointCardRWPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpPOSKeyboardPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/POSKeyboard.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		POSKeyboardPane.getChildren().clear();
		POSKeyboardPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpPOSPowerPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/POSPower.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		POSPowerPane.getChildren().clear();
		POSPowerPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}

	private void setUpPOSPrinterPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/POSPrinter.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		POSPrinterPane.getChildren().clear();
		POSPrinterPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpRemoteOrderDisplayPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/RemoteOrderDisplay.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		remoteOrderDisplayPane.getChildren().clear();
		remoteOrderDisplayPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpRFIDScannerPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/RFIDScanner.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		RFIDPane.getChildren().clear();
		RFIDPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpScalePane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/Scale.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		scalePane.getChildren().clear();
		scalePane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpScannerPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/Scanner.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		scannerPane.getChildren().clear();
		scannerPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpSignatureCapturePane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/SignatureCapture.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		signatureCapturePane.getChildren().clear();
		signatureCapturePane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpSmartCardRWPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/SmartCardRW.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		smartCardRWPane.getChildren().clear();
		smartCardRWPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	private void setUpToneIndicatorPane(){
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/ToneIndicator.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		toneIndicatorPane.getChildren().clear();
		toneIndicatorPane.getChildren().addAll(root.getChildrenUnmodifiable());
	}
	
	
	/* ************************************************************************
	 ************************* Action Handler**********************************
	 *************************************************************************/
	
	/*
	 * LineDiplay Action Handler
	 */
	@FXML
	public void handle(ActionEvent e) {
		/*
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("gui/testpage.fxml"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		ObservableList<Node> e2 = root.getChildrenUnmodifiable();
		aPane.getChildren().clear();
		aPane.getChildren().addAll(root.getChildrenUnmodifiable());
		*/
    }
	
	
	
	
}
