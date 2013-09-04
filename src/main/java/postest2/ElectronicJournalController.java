package postest2;

import java.awt.Dimension;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import jpos.ElectronicJournal;
import jpos.JposException;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ElectronicJournalController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox asyncMode;
	@FXML
	@RequiredState(JposState.OPENED)
	public CheckBox flagWhenIdle;

	@FXML
	public ComboBox<Boolean> storageEnabled;
	@FXML
	public ComboBox<String> station;
	@FXML
	public ComboBox<Boolean> waterMark;
	@FXML
	public ComboBox<String> retrieveCurrentMarker_markerType;
	@FXML
	public ComboBox<String> retrieveMarker_markerType;
	@FXML
	public ComboBox<String> retrieveMarkerByDateTime_markerType;

	@FXML
	public TextField initializeMedium_mediumID;
	@FXML
	public TextField printContent_fromMarker;
	@FXML
	public TextField printContent_toMarker;
	@FXML
	public TextField printContentFile_fileName;
	@FXML
	public TextField queryContent_fromMarker;
	@FXML
	public TextField queryContent_toMarker;
	@FXML
	public TextField queryContent_fileName;
	@FXML
	public TextField addMarker_marker;
	@FXML
	public TextField retrieveCurrentMarker_marker;
	@FXML
	public TextField retrieveMarker_marker;
	@FXML
	public TextField retrieveMarker_sessionNumber;
	@FXML
	public TextField retrieveMarker_documentNumber;
	@FXML
	public TextField retrieveMarkerByDateTime_marker;
	@FXML
	public TextField retrieveMarkerByDateTime_dateTime;
	@FXML
	public TextField retrieveMarkerByDateTime_markerNumber;
	@FXML
	public TextField retrieveMarkersDateTime_marker;
	@FXML
	public TextField retrieveMarkersDateTime_dateTime;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		service = new ElectronicJournal();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("ElectronicJournal");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((ElectronicJournal) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((ElectronicJournal) service).setDeviceEnabled(false);
			}
		} catch (JposException je) {
			System.err.println("ElectronicJournalPanel: CheckBoxListener: Jpos Exception" + je);
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		handleDeviceEnable(e);
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper ejcm = new ElectronicJournalConstantMapper();
			String msg = DeviceProperties.getProperties(service, ejcm);
			JTextArea jta = new JTextArea(msg);
			@SuppressWarnings("serial")
			JScrollPane jsp = new JScrollPane(jta) {
				@Override
				public Dimension getPreferredSize() {
					return new Dimension(460, 390);
				}
			};
			JOptionPane.showMessageDialog(null, jsp, "Information", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception jpe) {
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(),
					"Exception", JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	// Shows statistics of device if they are supported by the device
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((ElectronicJournal) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (JposException jpe) {
			jpe.printStackTrace();
			JOptionPane.showMessageDialog(null, "Statistics are not supported!", "Statistics",
					JOptionPane.ERROR_MESSAGE);
		}

		statistics = "";
	}

	@FXML
	public void handleAsyncMode(ActionEvent e) {
		// System.out.println("async");
		try {
			((ElectronicJournal) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleFlagWhenIdle(ActionEvent e) {
		// System.out.println("flag");
		try {
			((ElectronicJournal) service).setFlagWhenIdle(flagWhenIdle.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetStation(ActionEvent e) {
		// System.out.println("stati");
		try {
			((ElectronicJournal) service).setStation(ElectronicJournalConstantMapper
					.getConstantNumberFromString(station.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetWaterMark(ActionEvent e) {
		// System.out.println("warter");
		try {
			((ElectronicJournal) service).setWaterMark(waterMark.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetStorageEnabled(ActionEvent e) {
		// System.out.println("stor");
		try {
			((ElectronicJournal) service).setStorageEnabled(storageEnabled.getSelectionModel()
					.getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleEraseMedium(ActionEvent e) {
		// System.out.println("erase");
		try {
			((ElectronicJournal) service).eraseMedium();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleInitializeMedium(ActionEvent e) {
		// System.out.println("init");
		if (initializeMedium_mediumID.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicJournal) service).initializeMedium(initializeMedium_mediumID.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleCancelPrintContent(ActionEvent e) {
		// System.out.println("canprint");
		try {
			((ElectronicJournal) service).cancelPrintContent();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handlePrintContent(ActionEvent e) {
		// System.out.println("printCon");
		if (printContent_fromMarker.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicJournal) service).printContent(printContent_fromMarker.getText(),
						printContent_toMarker.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handlePrintContentFile(ActionEvent e) {
		// System.out.println("printConFile");
		if (printContentFile_fileName.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicJournal) service).printContentFile(printContentFile_fileName.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSuspendPrintContent(ActionEvent e) {
		// System.out.println("suspendPrint");
		try {
			((ElectronicJournal) service).suspendPrintContent();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleResumePrintContent(ActionEvent e) {
		// System.out.println("resumePrint");
		try {
			((ElectronicJournal) service).resumePrintContent();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleQueryContent(ActionEvent e) {
		// System.out.println("queryCon");
		if (queryContent_fileName.getText().isEmpty() || queryContent_fromMarker.getText().isEmpty()
				|| queryContent_toMarker.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((ElectronicJournal) service).queryContent(queryContent_fileName.getText(),
						queryContent_fromMarker.getText(), queryContent_toMarker.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSuspendQueryContent(ActionEvent e) {
		// System.out.println("suspendquery");
		try {
			((ElectronicJournal) service).suspendQueryContent();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleResumeQueryContent(ActionEvent e) {
		// System.out.println("resumequery");
		try {
			((ElectronicJournal) service).resumeQueryContent();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleCancelQueryContent(ActionEvent e) {
		// System.out.println("cancelQuery");
		try {
			((ElectronicJournal) service).cancelQueryContent();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleAddMarker(ActionEvent e) {
		// System.out.println("addmarker");
		if (addMarker_marker.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((ElectronicJournal) service).addMarker(addMarker_marker.getText());
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleRetrieveCurrentMarker(ActionEvent e) {
		// System.out.println("retcurmarker");
		String[] marker = new String[1];
		try {
			((ElectronicJournal) service).retrieveCurrentMarker(ElectronicJournalConstantMapper
					.getConstantNumberFromString(retrieveCurrentMarker_markerType.getSelectionModel()
							.getSelectedItem()), marker);
			retrieveCurrentMarker_marker.setText(marker[0]);
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleRetrieveMarker(ActionEvent e) {
		// System.out.println("retmark");
		if (retrieveMarker_sessionNumber.getText().isEmpty()
				|| retrieveMarker_documentNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			String[] marker = new String[1];
			try {
				((ElectronicJournal) service).retrieveMarker(ElectronicJournalConstantMapper
						.getConstantNumberFromString(retrieveCurrentMarker_markerType.getSelectionModel()
								.getSelectedItem()),
						Integer.parseInt(retrieveMarker_sessionNumber.getText()), Integer
								.parseInt(retrieveMarker_documentNumber.getText()), marker);
				retrieveMarker_marker.setText(marker[0]);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleRetrieveMarkerByDateTime(ActionEvent e) {
		// System.out.println("retmarkbydt");
		if (retrieveMarkerByDateTime_dateTime.getText().isEmpty()
				|| retrieveMarkerByDateTime_markerNumber.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			String[] marker = new String[1];
			try {
				((ElectronicJournal) service).retrieveMarkerByDateTime(ElectronicJournalConstantMapper
						.getConstantNumberFromString(retrieveCurrentMarker_markerType.getSelectionModel()
								.getSelectedItem()), retrieveMarkerByDateTime_dateTime.getText(),
						retrieveMarkerByDateTime_markerNumber.getText(), marker);
				retrieveMarkerByDateTime_marker.setText(marker[0]);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleRetrieveMarkersDateTime(ActionEvent e) {
		// System.out.println("retdt");
		if (retrieveMarkersDateTime_marker.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			String[] dateTime = new String[1];
			try {
				((ElectronicJournal) service).retrieveMarkersDateTime(
						retrieveMarkersDateTime_marker.getText(), dateTime);
				retrieveMarkersDateTime_dateTime.setText(dateTime[0]);
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	/*
	 * Set Up ComboBoxes
	 */

	private void setUpStorageEnabled() {
		storageEnabled.getItems().clear();
		storageEnabled.getItems().add(true);
		storageEnabled.getItems().add(false);
		storageEnabled.setValue(true);
	}

	private void setUpStation() {
		station.getItems().clear();
		station.getItems().add(ElectronicJournalConstantMapper.EJ_S_JOURNAL.getConstant());
		station.getItems().add(ElectronicJournalConstantMapper.EJ_S_RECEIPT.getConstant());
		station.getItems().add(ElectronicJournalConstantMapper.EJ_S_SLIP.getConstant());
		station.setValue(ElectronicJournalConstantMapper.EJ_S_JOURNAL.getConstant());
	}

	private void setUpWaterMark() {

		waterMark.getItems().clear();
		waterMark.getItems().add(true);
		waterMark.getItems().add(false);
		waterMark.setValue(true);
	}

	private void setUpRetrieveCurrentMarkerMarkerType() {
		retrieveCurrentMarker_markerType.getItems().clear();
		retrieveCurrentMarker_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG.getConstant());
		retrieveCurrentMarker_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_SESSION_END.getConstant());
		retrieveCurrentMarker_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_DOCUMENT.getConstant());
		retrieveCurrentMarker_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_HEAD.getConstant());
		retrieveCurrentMarker_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_TAIL.getConstant());
		retrieveCurrentMarker_markerType.setValue(ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG
				.getConstant());

	}

	private void setUpRetrieveMarkerMarkerType() {
		retrieveMarker_markerType.getItems().clear();
		retrieveMarker_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG.getConstant());
		retrieveMarker_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_SESSION_END.getConstant());
		retrieveMarker_markerType.getItems()
				.add(ElectronicJournalConstantMapper.EJ_MT_DOCUMENT.getConstant());
		retrieveMarker_markerType.setValue(ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG.getConstant());
	}

	private void setUpRetrieveMarkerByDateTimeMarkerType() {

		retrieveMarkerByDateTime_markerType.getItems().clear();
		retrieveMarkerByDateTime_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG.getConstant());
		retrieveMarkerByDateTime_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_SESSION_END.getConstant());
		retrieveMarkerByDateTime_markerType.getItems().add(
				ElectronicJournalConstantMapper.EJ_MT_DOCUMENT.getConstant());
		retrieveMarkerByDateTime_markerType.setValue(ElectronicJournalConstantMapper.EJ_MT_SESSION_BEG
				.getConstant());
	}

	private void setUpComboBoxes() {
		setUpStorageEnabled();
		setUpStation();
		setUpWaterMark();
		setUpRetrieveCurrentMarkerMarkerType();
		setUpRetrieveMarkerByDateTimeMarkerType();
		setUpRetrieveMarkerMarkerType();
	}
}
