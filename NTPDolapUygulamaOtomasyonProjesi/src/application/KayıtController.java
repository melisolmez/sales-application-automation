package application;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;


public class KayıtController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField KayıtEpostaTxt;

    @FXML
    private PasswordField KayıtSifreTxt;

    @FXML
    private Button UyeButton;

    @FXML
    private TextField kayıtKullanıcıAdTxt;

    @FXML
    private TextField kayıtKullanıcıSoyadTxt;

    @FXML
    private TextField kayıtKullanıcıTelTxt;
    @FXML
    private TextField kayıtKullanıcıAdresTxt;

    @FXML
    void ClickUyeOl(ActionEvent event) {
    	
    	String eposta = KayıtEpostaTxt.getText();
        String sifre = KayıtSifreTxt.getText();
        String kullaniciAd = kayıtKullanıcıAdTxt.getText();
        String kullaniciSoyad = kayıtKullanıcıSoyadTxt.getText();
        String telefon = kayıtKullanıcıTelTxt.getText();
        String kullaniciAdres=kayıtKullanıcıAdresTxt.getText();
        String md5Sifre =Sifreleme.hashMD5(sifre);

            String sql = "INSERT INTO kullanicibilgi (kullanici_ad,kullanici_soyad,kullanici_telefon,kullanici_mail,kullanici_sifre,kullanici_adres,kullanici_bakiye) VALUES (?,?,?, ?,?,?,?)";
            Connection con=Baglanti.baglan();
            if(con!=null)
     	   {
            	 PreparedStatement statement;
				try {
					statement = con.prepareStatement(sql);
					statement.setString(1, kullaniciAd);
                    statement.setString(2, kullaniciSoyad);
                    statement.setString(3, telefon);
                    statement.setString(4, eposta);
                    statement.setString(5, md5Sifre);
                    statement.setString(6, kullaniciAdres);
                    statement.setString(7, "0");

                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                    	Alert alert1 = new Alert(AlertType.INFORMATION);
     	      	       alert1.setTitle("Bilgi");
     	      	       alert1.setHeaderText("Kayıt");
     	      	       alert1.setContentText("Kullanıcı başarıyla kaydedildi.");
     	      	       alert1.showAndWait();
     	      	    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
                        Parent root = loader.load();
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                        
                    } else {
                    	Alert alert1 = new Alert(AlertType.INFORMATION);
      	      	       alert1.setTitle("Bilgi");
      	      	       alert1.setHeaderText("Kayıt");
      	      	       alert1.setContentText("Kullanıcı kaydedilirken bir hata oluştu.");
      	      	       alert1.showAndWait();
                    }
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
     	   }   			
    }

    @FXML
    void initialize() {
        
    }
  

}


