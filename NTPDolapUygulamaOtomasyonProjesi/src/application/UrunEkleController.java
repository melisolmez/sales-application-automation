package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import application.User;

public class UrunEkleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonYeniUrunEkle;

    @FXML
    private ComboBox<String> cmbCinsiyet;

    @FXML
    private ComboBox<String> cmbEtiket;

    @FXML
    private ComboBox<String> cmbKategori;

    @FXML
    private ComboBox<String> cmbSatinAlma;

    @FXML
    private ImageView imageViewResim;

    @FXML
    private TextField urunAcıklamasiTxt;

    @FXML
    private TextField urunAdiTxt;

    @FXML
    private TextField urunFiyatiTxt;

   
    @FXML
    void clickYeniUrunEkle(ActionEvent event) {

    	urunEkle();
    	try {
	           FXMLLoader loader = new FXMLLoader(getClass().getResource("Anasayfa.fxml"));
	           Parent root = loader.load();
	           Scene scene = new Scene(root);
	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	           stage.setScene(scene);
	           stage.show();
	               
	         } catch (IOException e)
	         {
	            e.printStackTrace();
	         }
    }
  
    @FXML
    void initialize() {
    	
    	String[]cinsiyet= {"Kadın","Erkek"};
    	String[]etiket= {"Etiketli","Etiketsiz"};
    	String[]kategori= {"Giyim","Ayakkabı","Aksesuar","Çanta"};
    	String[]satinAlma= {"Satın Alındı","Satılıyor"};
    	
    	cmbCinsiyet.getItems().addAll(cinsiyet);
    	cmbEtiket.getItems().addAll(etiket);
    	cmbKategori.getItems().addAll(kategori);
    	cmbSatinAlma.getItems().addAll(satinAlma);
     
    }

    public void urunEkle()
    {
    	String sql = "INSERT INTO urun (urun_ad, urun_fiyat, urun_acıklama, urun_ozelligi, urun_kategoriId, urun_cinsiyet, urun_durumu, urun_kullaniciId) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    	try {
    	    Connection con = Baglanti.baglan();
    	    PreparedStatement statement = con.prepareStatement(sql);
    	    
    	    String fiyatMetni = urunFiyatiTxt.getText();
    	    double urunFiyati = Double.parseDouble(fiyatMetni);
    	    
    	    statement.setString(1, urunAdiTxt.getText());
    	    statement.setDouble(2,urunFiyati);
    	    statement.setString(3, urunAcıklamasiTxt.getText());
    	    String selectedEtiket = cmbEtiket.getSelectionModel().getSelectedItem();
    	    if (cmbEtiket != null) {
        	    switch (selectedEtiket) {
        	        case "Etiketli":
        	        	statement.setString(4,"Etiketli");
        	            break;
        	        case "Etiketsiz":
        	        	statement.setString(4,"Etiketsiz");
        	            break;
        	        default:
        	            break;
       	    }
            }
    	    String selectedValue = cmbKategori.getSelectionModel().getSelectedItem();
        	if (cmbKategori != null) {
        	    switch (selectedValue) {
        	        case "Giyim":
        	        	statement.setInt(5,1);
        	            break;
        	        case "Aksesuar":
        	        	statement.setInt(5,2);
        	            break;
        	        case "Çanta":
        	        	statement.setInt(5,4);
        	            break;
        	        case "Ayakkabı":
        	        	statement.setInt(5,3);
        	            break;
        	        default:
        	            break;
       	    }
            } 
        	String selectedCinsiyet = cmbCinsiyet.getSelectionModel().getSelectedItem();
        	if (cmbCinsiyet != null) {
        	    switch (selectedCinsiyet) {
        	        case "Erkek":
        	        	statement.setString(6,"Erkek");
        	            break;
        	        case "Kadın":
        	        	statement.setString(6, "Kadın");
        	            break;
        	        default:
        	            break;
       	    }
            }
        	String selectedSatin = cmbSatinAlma.getSelectionModel().getSelectedItem();
        	if (cmbSatinAlma != null) {
        	    switch (selectedSatin) {
        	        case "Satılıyor":
        	        	statement.setString(7,"Satılıyor");
        	            break;
        	        case "Satın Alındı":
        	        	statement.setString(7, "Satın Alındı");
        	            break;
        	        default:
        	            break;
       	    }
            }
    	    statement.setInt(8, User.userId);
    	    statement.executeUpdate();
    	    statement.close();
    	    con.close();
    	    
    	    System.out.println("Ürün başarıyla eklendi.");
    	    
    	} catch (Exception e) {
    	    System.out.println("Ürün ekleme hatası: " + e.getMessage());
    	}
    	
    	
    }
}
