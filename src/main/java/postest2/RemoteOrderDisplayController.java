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

import jpos.JposException;
import jpos.RemoteOrderDisplay;

import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class RemoteOrderDisplayController extends CommonController implements Initializable {

	@FXML
	@RequiredState(JposState.ENABLED)
	public CheckBox asyncMode;

	@FXML
	@RequiredState(JposState.ENABLED)
	public TabPane functionPane;

	@FXML
	public TextField autoToneDuration;
	@FXML
	public TextField autoToneFrequency;
	@FXML
	public TextField eventTypes;
	@FXML
	public TextField timeout;
	@FXML
	public TextField videoMode;
	@FXML
	public TextField clearVideoRegion_row;
	@FXML
	public TextField clearVideoRegion_column;
	@FXML
	public TextField clearVideoRegion_height;
	@FXML
	public TextField clearVideoRegion_width;
	@FXML
	public TextField controlClock_hour;
	@FXML
	public TextField controlClock_min;
	@FXML
	public TextField controlClock_sec;
	@FXML
	public TextField controlClock_row;
	@FXML
	public TextField controlClock_column;
	@FXML
	public TextField copyVideoRegion_row;
	@FXML
	public TextField copyVideoRegion_column;
	@FXML
	public TextField copyVideoRegion_height;
	@FXML
	public TextField copyVideoRegion_width;
	@FXML
	public TextField copyVideoRegion_targetRow;
	@FXML
	public TextField copyVideoRegion_targetCol;
	@FXML
	public TextField displayData_row;
	@FXML
	public TextField displayData_column;
	@FXML
	public TextField displayData_data;
	@FXML
	public TextField drawBox_row;
	@FXML
	public TextField drawBox_column;
	@FXML
	public TextField drawBox_height;
	@FXML
	public TextField drawBox_width;
	@FXML
	public TextField restoreVideoRegion_targetRow;
	@FXML
	public TextField restoreVideoRegion_targetCol;
	@FXML
	public TextField saveVideoRegion_row;
	@FXML
	public TextField saveVideoRegion_column;
	@FXML
	public TextField saveVideoRegion_height;
	@FXML
	public TextField saveVideoRegion_width;
	@FXML
	public TextField updateVideoRegionAttribute_row;
	@FXML
	public TextField updateVideoRegionAttribute_col;
	@FXML
	public TextField updateVideoRegionAttribute_height;
	@FXML
	public TextField updateVideoRegionAttribute_width;
	@FXML
	public TextField setCursor_row;
	@FXML
	public TextField setCursor_column;
	@FXML
	public TextField videoSound_frequency;
	@FXML
	public TextField videoSound_duration;
	@FXML
	public TextField videoSound_numberOfCycles;
	@FXML
	public TextField videoSound_interSoundWait;
	@FXML
	public TextField clearVideo_attribute;
	@FXML
	public TextField clearVideoRegion_attribute;
	@FXML
	public TextField drawBox_attribute;
	@FXML
	public TextField controlClock_attribute;
	@FXML
	public TextField displayData_attribute;
	@FXML
	public TextField updateVideoRegionAttribute_attribute;

	@FXML
	public ComboBox<String> currentUnitID;
	@FXML
	public ComboBox<Boolean> mapCharacterSet;
	@FXML
	public ComboBox<String> checkHealth_level;
	@FXML
	public ComboBox<String> clearVideo_units;
	@FXML
	public ComboBox<String> clearVideoRegion_units;
	@FXML
	public ComboBox<String> selectCharacterSet_units;
	@FXML
	public ComboBox<Integer> selectCharacterSet_characterSet;
	@FXML
	public ComboBox<String> controlClock_units;
	@FXML
	public ComboBox<String> controlClock_function;
	@FXML
	public ComboBox<Integer> controlClock_clockID;
	@FXML
	public ComboBox<String> controlClock_mode;
	@FXML
	public ComboBox<String> controlCursor_units;
	@FXML
	public ComboBox<String> controlCursor_function;
	@FXML
	public ComboBox<String> copyVideoRegion_units;
	@FXML
	public ComboBox<String> displayData_units;
	@FXML
	public ComboBox<String> drawBox_units;
	@FXML
	public ComboBox<String> drawBox_borderType;
	@FXML
	public ComboBox<String> freeVideoRegion_units;
	@FXML
	public ComboBox<Integer> freeVideoRegion_bufferID;
	@FXML
	public ComboBox<String> resetVideo_units;
	@FXML
	public ComboBox<String> restoreVideoRegion_units;
	@FXML
	public ComboBox<Integer> restoreVideoRegion_bufferID;
	@FXML
	public ComboBox<String> saveVideoRegion_units;
	@FXML
	public ComboBox<Integer> saveVideoRegion_bufferID;
	@FXML
	public ComboBox<String> updateVideoRegionAttribute_function;
	@FXML
	public ComboBox<String> setCursor_units;
	@FXML
	public ComboBox<String> transactionDisplay_units;
	@FXML
	public ComboBox<String> transactionDisplay_function;
	@FXML
	public ComboBox<String> videoSound_units;
	@FXML
	public ComboBox<String> updateVideoRegionAttribute_units;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		setUpTooltips();
		service = new RemoteOrderDisplay();
		RequiredStateChecker.invokeThis(this, service);
		setUpLogicalNameComboBox("RemoteOrderDisplay");
	}

	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	@FXML
	public void handleDeviceEnable(ActionEvent e) {
		try {
			if (deviceEnabled.isSelected()) {
				((RemoteOrderDisplay) service).setDeviceEnabled(true);
				setUpComboBoxes();
			} else {
				((RemoteOrderDisplay) service).setDeviceEnabled(false);
			}
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null, je.getMessage());
			je.printStackTrace();
		}
	}

	@Override
	@FXML
	public void handleOCE(ActionEvent e) {
		super.handleOCE(e);
		try {
			if(getDeviceState(service) == JposState.CLAIMED){
				deviceEnabled.setSelected(true);
				handleDeviceEnable(e);
			}
		} catch (JposException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleInfo(ActionEvent e) {
		try {
			IMapWrapper rodcm = new RemoteOrderDisplayConstantMapper();
			String msg = DeviceProperties.getProperties(service, rodcm);

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
			JOptionPane.showMessageDialog(null, "Exception in Info\nException: " + jpe.getMessage(), "Exception",
					JOptionPane.ERROR_MESSAGE);
			System.err.println("Jpos exception " + jpe);
		}
	}

	/**
	 * Shows statistics of device if they are supported by the device
	 */
	@Override
	@FXML
	public void handleStatistics(ActionEvent e) {
		String[] stats = new String[] { "", "U_", "M_" };
		try {
			((RemoteOrderDisplay) service).retrieveStatistics(stats);
			DOMParser parser = new DOMParser();
			parser.parse(new InputSource(new java.io.StringReader(stats[1])));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new ByteArrayInputStream(stats[1].getBytes()));

			printStatistics(doc.getDocumentElement(), "");

			JOptionPane.showMessageDialog(null, statistics, "Statistics", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, ioe.getMessage());
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			JOptionPane.showMessageDialog(null, saxe.getMessage());
			saxe.printStackTrace();
		} catch (ParserConfigurationException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
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
		try {
			((RemoteOrderDisplay) service).setAsyncMode(asyncMode.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetAutoToneDuration(ActionEvent e) {
		if (autoToneDuration.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).setAutoToneDuration(Integer.parseInt(autoToneDuration.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetAutoToneFrequency(ActionEvent e) {
		if (autoToneFrequency.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).setAutoToneFrequency(Integer.parseInt(autoToneFrequency.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetCurrentUnitID(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).setCurrentUnitID(RemoteOrderDisplayConstantMapper
					.getConstantNumberFromString(currentUnitID.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetEventTypes(ActionEvent e) {
		if (eventTypes.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).setEventType(Integer.parseInt(eventTypes.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetMapCharacterSet(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).setMapCharacterSet(mapCharacterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleSetTimeout(ActionEvent e) {
		if (timeout.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).setTimeout(Integer.parseInt(timeout.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetVideoMode(ActionEvent e) {
		if (videoMode.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).setVideoMode(Integer.parseInt(videoMode.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleCheckHealth(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).checkHealth(CommonConstantMapper
					.getConstantNumberFromString(checkHealth_level.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleClearInput(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).clearInput();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleClearOutput(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).clearOutput();
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleClearVideo(ActionEvent e) {
		if (clearVideo_attribute.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).clearVideo(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(clearVideo_units.getSelectionModel().getSelectedItem()), Integer
						.parseInt(clearVideo_attribute.getText()));
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleClearVideoRegion(ActionEvent e) {
		if (clearVideoRegion_row.getText().isEmpty() || clearVideoRegion_column.getText().isEmpty()
				|| clearVideoRegion_height.getText().isEmpty() || clearVideoRegion_width.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).clearVideoRegion(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(clearVideoRegion_units.getSelectionModel().getSelectedItem()),
						Integer.parseInt(clearVideoRegion_row.getText()), Integer.parseInt(clearVideoRegion_column
								.getText()), Integer.parseInt(clearVideoRegion_height.getText()), Integer
								.parseInt(clearVideoRegion_width.getText()), Integer
								.parseInt(clearVideoRegion_attribute.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSelectCharacterSet(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).selectChararacterSet(RemoteOrderDisplayConstantMapper
					.getConstantNumberFromString(selectCharacterSet_units.getSelectionModel().getSelectedItem()),
					selectCharacterSet_characterSet.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleControlClock(ActionEvent e) {
		if (controlClock_hour.getText().isEmpty() || controlClock_min.getText().isEmpty()
				|| controlClock_sec.getText().isEmpty() || controlClock_row.getText().isEmpty()
				|| controlClock_column.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).controlClock(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(controlClock_units.getSelectionModel().getSelectedItem()),
						RemoteOrderDisplayConstantMapper.getConstantNumberFromString(controlClock_function
								.getSelectionModel().getSelectedItem()), controlClock_clockID.getSelectionModel()
								.getSelectedItem(), Integer.parseInt(controlClock_hour.getText()), Integer
								.parseInt(controlClock_min.getText()), Integer.parseInt(controlClock_sec.getText()),
						Integer.parseInt(controlClock_row.getText()), Integer.parseInt(controlClock_column.getText()),
						Integer.parseInt(controlClock_attribute.getText()), RemoteOrderDisplayConstantMapper
								.getConstantNumberFromString(controlClock_mode.getSelectionModel().getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleControlCursor(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).controlCursor(RemoteOrderDisplayConstantMapper
					.getConstantNumberFromString(controlCursor_units.getSelectionModel().getSelectedItem()),
					RemoteOrderDisplayConstantMapper.getConstantNumberFromString(controlCursor_function
							.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleCopyVideoRegion(ActionEvent e) {
		if (copyVideoRegion_row.getText().isEmpty() || copyVideoRegion_column.getText().isEmpty()
				|| copyVideoRegion_height.getText().isEmpty() || copyVideoRegion_width.getText().isEmpty()
				|| copyVideoRegion_targetRow.getText().isEmpty() || copyVideoRegion_targetCol.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).copyVideoRegion(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(copyVideoRegion_units.getSelectionModel().getSelectedItem()),
						Integer.parseInt(copyVideoRegion_row.getText()), Integer.parseInt(copyVideoRegion_column
								.getText()), Integer.parseInt(copyVideoRegion_height.getText()), Integer
								.parseInt(copyVideoRegion_width.getText()), Integer.parseInt(copyVideoRegion_targetRow
								.getText()), Integer.parseInt(copyVideoRegion_targetCol.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleDisplayData(ActionEvent e) {
		if (displayData_row.getText().isEmpty() || displayData_row.getText().isEmpty()
				|| displayData_data.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).displayData(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(displayData_units.getSelectionModel().getSelectedItem()), Integer
						.parseInt(displayData_row.getText()), Integer.parseInt(displayData_column.getText()), Integer
						.parseInt(displayData_attribute.getText()), displayData_data.getText());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleDrawBox(ActionEvent e) {
		if (drawBox_row.getText().isEmpty() || drawBox_column.getText().isEmpty() || drawBox_height.getText().isEmpty()
				|| drawBox_width.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).drawBox(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(drawBox_units.getSelectionModel().getSelectedItem()), Integer
						.parseInt(drawBox_row.getText()), Integer.parseInt(drawBox_column.getText()), Integer
						.parseInt(drawBox_height.getText()), Integer.parseInt(drawBox_width.getText()), Integer
						.parseInt(drawBox_attribute.getText()), RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(drawBox_borderType.getSelectionModel().getSelectedItem()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleFreeVideoRegion(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).freeVideoRegion(RemoteOrderDisplayConstantMapper
					.getConstantNumberFromString(freeVideoRegion_units.getSelectionModel().getSelectedItem()),
					freeVideoRegion_bufferID.getSelectionModel().getSelectedItem());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleResetVideo(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).resetVideo(RemoteOrderDisplayConstantMapper
					.getConstantNumberFromString(resetVideo_units.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleRestoreVideoRegion(ActionEvent e) {
		if (restoreVideoRegion_targetCol.getText().isEmpty() || restoreVideoRegion_targetRow.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).restoreVideoRegion(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(restoreVideoRegion_units.getSelectionModel().getSelectedItem()),
						Integer.parseInt(restoreVideoRegion_targetRow.getText()), Integer
								.parseInt(restoreVideoRegion_targetCol.getText()), restoreVideoRegion_bufferID
								.getSelectionModel().getSelectedItem());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSaveVideoRegion(ActionEvent e) {
		if (saveVideoRegion_row.getText().isEmpty() || saveVideoRegion_column.getText().isEmpty()
				|| saveVideoRegion_height.getText().isEmpty() || saveVideoRegion_width.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).saveVideoRegion(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(saveVideoRegion_units.getSelectionModel().getSelectedItem()),
						Integer.parseInt(saveVideoRegion_row.getText()), Integer.parseInt(saveVideoRegion_column
								.getText()), Integer.parseInt(saveVideoRegion_height.getText()), Integer
								.parseInt(saveVideoRegion_height.getText()), saveVideoRegion_bufferID
								.getSelectionModel().getSelectedItem());
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleSetCursor(ActionEvent e) {
		if (setCursor_row.getText().isEmpty() || setCursor_column.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).setCursor(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(setCursor_units.getSelectionModel().getSelectedItem()), Integer
						.parseInt(setCursor_row.getText()), Integer.parseInt(setCursor_column.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleTransactionDisplay(ActionEvent e) {
		try {
			((RemoteOrderDisplay) service).transactionDisplay(RemoteOrderDisplayConstantMapper
					.getConstantNumberFromString(transactionDisplay_units.getSelectionModel().getSelectedItem()),
					RemoteOrderDisplayConstantMapper.getConstantNumberFromString(transactionDisplay_function
							.getSelectionModel().getSelectedItem()));
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
			e1.printStackTrace();
		}
	}

	@FXML
	public void handleUpdateVideoRegionAttribute(ActionEvent e) {
		if (updateVideoRegionAttribute_row.getText().isEmpty() || updateVideoRegionAttribute_col.getText().isEmpty()
				|| updateVideoRegionAttribute_height.getText().isEmpty()
				|| updateVideoRegionAttribute_width.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).updateVideoRegionAttribute(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(updateVideoRegionAttribute_units.getSelectionModel()
								.getSelectedItem()), RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(updateVideoRegionAttribute_function.getSelectionModel()
								.getSelectedItem()), Integer.parseInt(updateVideoRegionAttribute_row.getText()),
						Integer.parseInt(updateVideoRegionAttribute_col.getText()), Integer
								.parseInt(updateVideoRegionAttribute_height.getText()), Integer
								.parseInt(updateVideoRegionAttribute_width.getText()), Integer
								.parseInt(updateVideoRegionAttribute_attribute.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	@FXML
	public void handleVideoSound(ActionEvent e) {
		if (videoSound_frequency.getText().isEmpty() || videoSound_duration.getText().isEmpty()
				|| videoSound_numberOfCycles.getText().isEmpty() || videoSound_interSoundWait.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Every Field should have a value!");
		} else {
			try {
				((RemoteOrderDisplay) service).videoSound(RemoteOrderDisplayConstantMapper
						.getConstantNumberFromString(videoSound_units.getSelectionModel().getSelectedItem()), Integer
						.parseInt(videoSound_frequency.getText()), Integer.parseInt(videoSound_duration.getText()),
						Integer.parseInt(videoSound_numberOfCycles.getText()), Integer
								.parseInt(videoSound_interSoundWait.getText()));
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	/*
	 * Set up ComboBoxes
	 */

	private void setUpComboBoxes() {
		setUpCheckHealthLevel();
		setUpClearVideoRegionUnits();
		setUpControlClockClockID();
		setUpCurrentUnitID();
		setUpMapCharacterSet();
		setUpClearVideoUnits();
		setUpSelectCharacterSetUnits();
		setUpSelectCharacterSetCharacterSet();
		setUpControlClockUnits();
		setUpControlClockFunction();
		setUpControlClockMode();
		setUpControlCursorUnits();
		setUpControlCursorFunction();
		setUpCopyVideoRegionUnits();
		setUpDisplayDataUnits();
		setUpDrawBoxUnits();
		setUpDrawBoxBorderType();
		setUpFreeVideoRegionUnits();
		setUpFreeVideoRegionBufferID();
		setUpResetVideoUnits();
		setUpRestoreVideoRegionUnits();
		setUpRestoreVideoRegionBufferID();
		setUpSaveVideoRegionUnits();
		setUpSaveVideoRegionBufferID();
		setUpUpdateVideoRegionAttributeFunction();
		setUpSetCursorUnits();
		setUpTransactionDisplayUnits();
		setUpTransactionDisplayFunction();
		setUpVideoSoundUnits();
		setUpUpdateVideoRegionAttributeUnits();
	}

	private void setUpCurrentUnitID() {
		setUID(currentUnitID);
	}

	private void setUpMapCharacterSet() {
		mapCharacterSet.getItems().clear();
		mapCharacterSet.getItems().add(true);
		mapCharacterSet.getItems().add(false);
		mapCharacterSet.setValue(true);
	}

	private void setUpCheckHealthLevel() {
		checkHealth_level.getItems().clear();
		checkHealth_level.getItems().add(CommonConstantMapper.JPOS_CH_INTERNAL.getConstant());
		checkHealth_level.getItems().add(CommonConstantMapper.JPOS_CH_EXTERNAL.getConstant());
		checkHealth_level.getItems().add(CommonConstantMapper.JPOS_CH_INTERACTIVE.getConstant());
		checkHealth_level.setValue(CommonConstantMapper.JPOS_CH_INTERNAL.getConstant());
	}

	private void setUpClearVideoUnits() {
		setUID(clearVideo_units);
	}

	private void setUpClearVideoRegionUnits() {
		setUID(clearVideoRegion_units);
	}

	private void setUpSelectCharacterSetUnits() {
		setUID(selectCharacterSet_units);
	}

	/**
	 * Sets the CharacterSetComboBox Values corresponding to the allowed Values
	 * for this device.
	 */
	private void setUpSelectCharacterSetCharacterSet() {
		selectCharacterSet_characterSet.getItems().clear();
		try {
			for (int i = 0; i < ((RemoteOrderDisplay) service).getCharacterSetList().split(",").length; i++) {
				selectCharacterSet_characterSet.getItems().add(
						Integer.parseInt((((RemoteOrderDisplay) service).getCharacterSetList().split(","))[i]));
				if (i == 0) {
					selectCharacterSet_characterSet.setValue(Integer.parseInt((((RemoteOrderDisplay) service)
							.getCharacterSetList().split(","))[i]));
				}
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the CharacterSetList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	private void setUpControlClockUnits() {
		setUID(controlClock_units);
	}

	private void setUpControlClockFunction() {
		controlClock_function.getItems().clear();
		controlClock_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_START.getConstant());
		controlClock_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_PAUSE.getConstant());
		controlClock_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_RESUME.getConstant());
		controlClock_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_MOVE.getConstant());
		controlClock_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_STOP.getConstant());
		controlClock_function.setValue(RemoteOrderDisplayConstantMapper.ROD_CLK_START.getConstant());
	}

	private void setUpControlClockClockID() {
		controlClock_clockID.getItems().clear();
		try {
			for (int i = 0; i < ((RemoteOrderDisplay) service).getClocks(); i++) {
				controlClock_clockID.getItems().add(i);
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the ClockList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
		controlClock_clockID.setValue(1);
	}

	private void setUpControlClockMode() {
		controlClock_mode.getItems().clear();
		controlClock_mode.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_SHORT.getConstant());
		controlClock_mode.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_NORMAL.getConstant());
		controlClock_mode.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_12_LONG.getConstant());
		controlClock_mode.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CLK_24_LONG.getConstant());
		controlClock_mode.setValue(RemoteOrderDisplayConstantMapper.ROD_CLK_SHORT.getConstant());
	}

	private void setUpControlCursorUnits() {
		setUID(controlCursor_units);
	}

	private void setUpControlCursorFunction() {
		controlCursor_function.getItems().clear();
		controlCursor_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CRS_LINE.getConstant());
		controlCursor_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CRS_LINE_BLINK.getConstant());
		controlCursor_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CRS_BLOCK.getConstant());
		controlCursor_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CRS_BLOCK_BLINK.getConstant());
		controlCursor_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_CRS_OFF.getConstant());
		controlCursor_function.setValue(RemoteOrderDisplayConstantMapper.ROD_CRS_LINE.getConstant());
	}

	private void setUpCopyVideoRegionUnits() {
		setUID(copyVideoRegion_units);
	}

	private void setUpDisplayDataUnits() {
		setUID(displayData_units);
	}

	private void setUpDrawBoxUnits() {
		setUID(drawBox_units);
	}

	private void setUpDrawBoxBorderType() {
		drawBox_borderType.getItems().clear();
		drawBox_borderType.getItems().add(RemoteOrderDisplayConstantMapper.ROD_BDR_SINGLE.getConstant());
		drawBox_borderType.getItems().add(RemoteOrderDisplayConstantMapper.ROD_BDR_DOUBLE.getConstant());
		drawBox_borderType.getItems().add(RemoteOrderDisplayConstantMapper.ROD_BDR_SOLID.getConstant());
		drawBox_borderType.setValue(RemoteOrderDisplayConstantMapper.ROD_BDR_SINGLE.getConstant());
	}

	private void setUpFreeVideoRegionUnits() {
		setUID(freeVideoRegion_units);
	}

	private void setUpFreeVideoRegionBufferID() {
		freeVideoRegion_bufferID.getItems().clear();
		try {
			for (int i = 0; i < ((RemoteOrderDisplay) service).getVideoSaveBuffers(); i++) {
				freeVideoRegion_bufferID.getItems().add(i);
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the BufferIDList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
		freeVideoRegion_bufferID.setValue(1);
	}

	private void setUpResetVideoUnits() {
		setUID(resetVideo_units);
	}

	private void setUpRestoreVideoRegionUnits() {
		setUID(restoreVideoRegion_units);
	}

	private void setUpRestoreVideoRegionBufferID() {
		restoreVideoRegion_bufferID.getItems().clear();
		try {
			for (int i = 0; i < ((RemoteOrderDisplay) service).getVideoSaveBuffers(); i++) {
				restoreVideoRegion_bufferID.getItems().add(i);
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the BufferIDList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
		restoreVideoRegion_bufferID.setValue(1);
	}

	private void setUpSaveVideoRegionUnits() {
		setUID(saveVideoRegion_units);
	}

	private void setUpSaveVideoRegionBufferID() {
		saveVideoRegion_bufferID.getItems().clear();
		try {
			for (int i = 0; i < ((RemoteOrderDisplay) service).getVideoSaveBuffers(); i++) {
				saveVideoRegion_bufferID.getItems().add(i);
			}
		} catch (JposException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error occured when getting the BufferIDList", "Error occured!",
					JOptionPane.WARNING_MESSAGE);
		}
		saveVideoRegion_bufferID.setValue(1);
	}

	private void setUpUpdateVideoRegionAttributeFunction() {
		updateVideoRegionAttribute_function.getItems().clear();
		updateVideoRegionAttribute_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UA_SET.getConstant());
		updateVideoRegionAttribute_function.getItems().add(
				RemoteOrderDisplayConstantMapper.ROD_UA_INTENSITY_ON.getConstant());
		updateVideoRegionAttribute_function.getItems().add(
				RemoteOrderDisplayConstantMapper.ROD_UA_INTENSITY_OFF.getConstant());
		updateVideoRegionAttribute_function.getItems().add(
				RemoteOrderDisplayConstantMapper.ROD_UA_REVERSE_ON.getConstant());
		updateVideoRegionAttribute_function.getItems().add(
				RemoteOrderDisplayConstantMapper.ROD_UA_REVERSE_OFF.getConstant());
		updateVideoRegionAttribute_function.getItems().add(
				RemoteOrderDisplayConstantMapper.ROD_UA_BLINK_ON.getConstant());
		updateVideoRegionAttribute_function.getItems().add(
				RemoteOrderDisplayConstantMapper.ROD_UA_BLINK_OFF.getConstant());
		updateVideoRegionAttribute_function.setValue(RemoteOrderDisplayConstantMapper.ROD_UA_SET.getConstant());
	}

	private void setUpSetCursorUnits() {
		setUID(setCursor_units);
	}

	private void setUpTransactionDisplayUnits() {
		setUID(transactionDisplay_units);
	}

	private void setUpTransactionDisplayFunction() {
		transactionDisplay_function.getItems().clear();
		transactionDisplay_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_TD_TRANSACTION.getConstant());
		transactionDisplay_function.getItems().add(RemoteOrderDisplayConstantMapper.ROD_TD_NORMAL.getConstant());
		transactionDisplay_function.setValue(RemoteOrderDisplayConstantMapper.ROD_TD_TRANSACTION.getConstant());
	}

	private void setUpVideoSoundUnits() {
		setUID(videoSound_units);
	}

	private void setUpUpdateVideoRegionAttributeUnits() {
		setUID(updateVideoRegionAttribute_units);
	}

	private void setUID(ComboBox<String> checkBox) {
		checkBox.getItems().clear();
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_1.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_2.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_3.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_4.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_5.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_6.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_7.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_8.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_9.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_10.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_11.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_12.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_13.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_14.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_15.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_16.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_17.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_18.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_19.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_20.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_21.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_22.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_23.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_24.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_25.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_26.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_27.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_28.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_29.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_30.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_31.getConstant());
		checkBox.getItems().add(RemoteOrderDisplayConstantMapper.ROD_UID_32.getConstant());
		checkBox.setValue(RemoteOrderDisplayConstantMapper.ROD_UID_1.getConstant());
	}
}