package postest2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.WindowEvent;

import javax.swing.JOptionPane;

import jpos.BaseJposControl;
import jpos.JposConst;
import jpos.JposException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public abstract class CommonController implements Initializable {

	/* ************************************************************************
	 * ************************ Action Handler ********************************
	 * ************************************************************************
	 */

	// Common
	@FXML
	public ComboBox<String> logicalName;
	@FXML
	@RequiredState(JposState.CLAIMED)
	public CheckBox deviceEnabled;
	@FXML
	public Button buttonOpen;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonClaim;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonRelease;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonStatistics;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonClose;
	@FXML
	@RequiredState(JposState.OPENED)
	public Button buttonFirmware;
	@FXML
	@RequiredState(JposState.CLOSED)
	public Button buttonOCE;
	@FXML
	@RequiredState(JposState.ENABLED)
	public Button buttonInfo;
	@FXML
	public Text statusLabel;
	@FXML
	@RequiredState(JposState.ENABLED)
	public CheckBox freezeEvents;

	// DirectIO
	@FXML
	public TextField directIO_command;
	@FXML
	public TextField directIO_data;
	@FXML
	public TextField directIO_object;

	BaseJposControl service;

	static String statistics = "";

	@FXML
	public void handleOpen(ActionEvent e) {
		POSTest2.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent arg0) {
				try {
					service.close();
				} catch (JposException e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			if (logicalName.getValue() != null && !logicalName.getValue().isEmpty()) {
				service.open(logicalName.getValue());
				RequiredStateChecker.invokeThis(this, service);
				setStatusLabel();
			} else {
				JOptionPane.showMessageDialog(null, "Choose a device!", "Logical name is empty",
						JOptionPane.WARNING_MESSAGE);
			}

		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleClaim(ActionEvent e) {
		try {
			service.claim(0);
			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to claim \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleRelease(ActionEvent e) {
		try {
			service.release();
			if (deviceEnabled.isSelected()) {
				deviceEnabled.setSelected(false);
			}

			RequiredStateChecker.invokeThis(this, service);
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to release \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleClose(ActionEvent e) {
		try {
			service.close();
			if (!deviceEnabled.isDisable()) {
				deviceEnabled.setSelected(false);
			}

			RequiredStateChecker.invokeThis(this, service);
			setStatusLabel();
		} catch (JposException je) {
			JOptionPane.showMessageDialog(null,
					"Failed to close \"" + logicalName.getSelectionModel().getSelectedItem()
							+ "\"\nException: " + je.getMessage(), "Failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleOCE(ActionEvent e) {
		handleOpen(e);
		handleClaim(e);
	}

	public void handleInfo(ActionEvent e) {

	}

	public void handleStatistics(ActionEvent e) {

	}

	// Method to parse the String XML and print the data for the
	// handleStatistics function
	public static void printStatistics(Node e, String tab) {
		if (e.getNodeType() == Node.TEXT_NODE) {
			statistics += tab + e.getNodeValue() + "\n";
			return;
		}

		if (!(e.getNodeName().equals("Name") || e.getNodeName().equals("Value")
				|| e.getNodeName().equals("UPOSStat") || e.getNodeName().equals("Event")
				|| e.getNodeName().equals("Equipment") || e.getNodeName().equals("Parameter")))
			statistics += tab + e.getNodeName();

		if (e.getNodeValue() != null) {
			statistics += tab + " " + e.getNodeValue();
		}

		NodeList childs = e.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			printStatistics(childs.item(i), " ");
		}
	}

	@FXML
	public void handleFirmware(ActionEvent e) {
		try {
			FirmwareUpdateDlg dlg = new FirmwareUpdateDlg(service);
			dlg.setVisible(true);
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(null, "Exception: " + e2.getMessage(), "Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	@FXML
	public void handleFreezeEvents(ActionEvent e) {
		try {
			service.setFreezeEvents(freezeEvents.selectedProperty().getValue());
		} catch (JposException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage());
		}
	}

	@FXML
	public void handleBrowseDirectIOData(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose DirectIOData");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			directIO_data.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleBrowseDirectIOObject(ActionEvent e) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Choose DirectIOObject");
		File f = chooser.showOpenDialog(null);
		if (f != null) {
			directIO_object.setText(f.getAbsolutePath());
		}
	}

	@FXML
	public void handleDirectIO(ActionEvent e) {
		if (directIO_command.getText().isEmpty() || directIO_data.getText().isEmpty()
				|| directIO_object.getText().isEmpty()) {

			JOptionPane.showMessageDialog(null, "One of the Parameter is not specified!");
		} else {
			try {
				// Reads content from File
				byte[] dataArrByte = readBytesFromFile(directIO_data.getText());
				int[] dataArrInt = new int[dataArrByte.length];
				for (int i = 0; i < dataArrByte.length; i++) {
					dataArrInt[i] = dataArrByte[i];
				}

				byte[] objectArr = readBytesFromFile(directIO_object.getText());

				// Execute DirectIO
				service.directIO(Integer.parseInt(directIO_command.getText()), dataArrInt, objectArr);

				// Write changes to the files
				dataArrByte = new byte[dataArrInt.length];
				for (int i = 0; i < dataArrByte.length; i++) {
					dataArrByte[i] = (byte) dataArrInt[i];
				}
				if (dataArrByte != null) {
					writeBytesToFile(dataArrByte, directIO_data.getText());
				}

				if (objectArr != null) {
					writeBytesToFile(objectArr, directIO_object.getText());
				}

			} catch (JposException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Set StatusLabel corresponding to the Device Status
	 */
	private void setStatusLabel() {
		if (service.getState() == JposConst.JPOS_S_IDLE) {
			statusLabel.setText("JPOS_S_IDLE");
		}

		if (service.getState() == JposConst.JPOS_S_CLOSED) {
			statusLabel.setText("JPOS_S_CLOSED");
		}

		if (service.getState() == JposConst.JPOS_S_BUSY) {
			statusLabel.setText("JPOS_S_BUSY");
		}

		if (service.getState() == JposConst.JPOS_S_ERROR) {
			statusLabel.setText("JPOS_S_ERROR");
		}
	}

	protected void setUpLogicalNameComboBox(String devCategory) {
		if (!LogicalNameGetter.getLogicalNamesByCategory(devCategory).isEmpty()) {
			logicalName.setItems(LogicalNameGetter.getLogicalNamesByCategory(devCategory));
		}
	}

	/**
	 * Read the given binary file, and return its contents as a byte array.
	 * 
	 */
	protected static byte[] readBytesFromFile(String aInputFileName) {
		File file = new File(aInputFileName);
		byte[] result = new byte[(int) file.length()];
		try {
			InputStream input = null;
			try {
				int totalBytesRead = 0;
				input = new BufferedInputStream(new FileInputStream(file));
				while (totalBytesRead < result.length) {
					int bytesRemaining = result.length - totalBytesRead;
					// input.read() returns -1, 0, or more :
					int bytesRead = input.read(result, totalBytesRead, bytesRemaining);
					if (bytesRead > 0) {
						totalBytesRead = totalBytesRead + bytesRead;
					}
				}
			} finally {
				input.close();
			}
		} catch (FileNotFoundException ex) {

			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();

		}
		return result;
	}

	/**
	 * Write a byte array to the given file. Writing binary data is
	 * significantly simpler than reading it.
	 */
	protected static void writeBytesToFile(byte[] aInput, String aOutputFileName) {
		try {
			OutputStream output = null;
			try {
				output = new BufferedOutputStream(new FileOutputStream(aOutputFileName));
				output.write(aInput);
			} finally {
				output.close();
			}
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
			ex.printStackTrace();
		}
	}

}
