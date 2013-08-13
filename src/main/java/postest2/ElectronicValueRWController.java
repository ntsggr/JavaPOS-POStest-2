package postest2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

public class ElectronicValueRWController extends CommonController implements Initializable {

	@FXML
	private ComboBox<String> logicalName;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
	
	/* ************************************************************************
	 * ************************ Action Handler *********************************
	 * ***********************************************************************
	 */

	/*
	 * Action Handler
	 */
	@FXML
	public void handle(ActionEvent e) {
	}
}