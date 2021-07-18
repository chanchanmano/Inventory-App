package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements Initializable {

	//creating fxml links to textfields
	@FXML public  TextField nameProd;
	@FXML public TextField priceProd;
	@FXML public TextField quanProd;
	@FXML public TextField infoProd;
	@FXML public ListView listProd;
	@FXML public Label labelInfo;
	@FXML public Label labeldisplayprofloss;
	@FXML public Label labelprofloss;
	@FXML public Label inventory;
	@FXML public Label displayinventory;


	double buy;
	double sell;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		labelInfo.setText("");
		labelprofloss.setText("Profit/Loss:");
		labeldisplayprofloss.setText("");
		inventory.setText("Inven. Cost:");
		displayinventory.setText("");

	}

	public void searchProdB(ActionEvent actionEvent) {
		String forsearch= infoProd.getText();
		ObservableList<String> listofitems = listProd.getItems();;
		for (String each: listofitems) {
			if(each.startsWith(forsearch)){
				String price=each.substring(each.indexOf("p:")+2,each.indexOf("|  q:"));
				String quantity=each.substring(each.indexOf("q:")+2,each.indexOf("| Tot:"));
				String tot=each.substring(each.indexOf("Tot:")+4,each.length());
				String info=forsearch+": Bought at a price of ₹"+price+"\n"+"The quantity of the product is "+quantity+"\n"
						+ "Total cost of purchase is ₹"+tot;
				System.out.println(info);
				labelInfo.setText(info);
			}
		}

	}

	public void shipProdB(ActionEvent actionEvent) {
		String selected= (String) listProd.getSelectionModel().getSelectedItem();
		if(selected!=null){
			listProd.getItems().remove(selected);
			double totextract=Double.parseDouble(selected.substring(selected.indexOf("Tot:")+4,selected.length()));
			int quantity=Integer.parseInt(selected.substring(selected.indexOf("q:")+2,selected.indexOf(" | Tot:")));
			double sellingprice=Double.parseDouble(priceProd.getText());
			sell+=(quantity*sellingprice);
			System.out.println(sell);
			changeprofloss();


		}else{
			System.out.println("No task selected..");
		}
	}

	public void addProdB(ActionEvent actionEvent) {
		String text = nameProd.getText();
		if(!text.equals("")){
			String a=nameProd.getText();
			String b=quanProd.getText();
			String c=priceProd.getText();
			String bc=String.valueOf(Integer.parseInt(b)*Integer.parseInt(c));
			String final1=a+" | "+" p:"+c+" | "+" q:"+b+" | Tot: "+bc;
			listProd.getItems().add(final1);
			nameProd.setText("");
			priceProd.setText("");
			quanProd.setText("");
			buy+=Double.parseDouble(bc);
			changeprofloss();
			changeinventory();
		}else{
			System.out.println("No input..");
		}
	}

	private void changeinventory() {
		displayinventory.setText(String.valueOf(buy));
	}

	private void changeprofloss() {
		Double profloss=sell-buy;
		labeldisplayprofloss.setText(String.valueOf(profloss));

	}
}
