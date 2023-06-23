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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import application.User;

public class LoginController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField logEmailText;

    @FXML
    private Button logGririsButton;

    @FXML
    private Button logKayıtButton;

    @FXML
    private PasswordField logSifreTxt;
    
    

    @FXML
    void ClickGirisYap(ActionEvent event) {
    	
    	   String email = logEmailText.getText();
    	   String sifre = logSifreTxt.getText();
    	   String md5Sifre=Sifreleme.hashMD5(sifre);
    	   
    	   Connection con=Baglanti.baglan();
    	   if(!logEmailText.getText().equals("admin"))
    	   {
    	   String sql = "SELECT * FROM kullanicibilgi WHERE kullanici_mail = ? AND kullanici_sifre = ?";
    	
    	   if(con!=null)
    	   {
    	       PreparedStatement statement;
			try {
				statement = con.prepareStatement(sql);
				statement.setString(1, email);
	            statement.setString(2, md5Sifre);
	        	ResultSet resultSet = statement.executeQuery();
	        	if (resultSet.next()) 
	      	     {
	        	   int kullaniciId = resultSet.getInt("kullanici_id");
	        	   User.userId=kullaniciId;
	       
	      	       Alert alert1 = new Alert(AlertType.INFORMATION);
	      	       alert1.setTitle("Bilgi");
	      	       alert1.setHeaderText("Giriş");
	      	       alert1.setContentText("Giriş Başarılı");
	      	       alert1.showAndWait();
	      	      
	      	         try {
	      	           FXMLLoader loader = new FXMLLoader(getClass().getResource("AnaSayfa.fxml"));
	      	           Parent root = loader.load();
	      	           Scene scene = new Scene(root);
	      	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	      	           stage.setScene(scene);
	      	           stage.show();
	      	               
	      	         } catch (IOException e)
	      	         {
	      	            e.printStackTrace();
	      	         }
	      	        } else
	      	        {
	      	         Alert alert2 = new Alert(AlertType.INFORMATION);
	      	         alert2.setTitle("Bilgi");
	      	         alert2.setHeaderText("Giriş");
	      	         alert2.setContentText("Hatalı e-posta veya şifre");
	      	         alert2.showAndWait();
	      	        }
	        	
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	   }
    	   else
    	   {
    		   Alert alert = new Alert(AlertType.INFORMATION);
    	       alert.setTitle("Bilgi");
    	       alert.setHeaderText("Veritabanı");
    	       alert.setContentText("Veritabanına bağlanılamadı.");
    	       alert.showAndWait();
    		  
    	   }
    	   }else
    	   {
    		   String sql = "SELECT * FROM yonetici WHERE yonetici_mail = ? AND yonetici_sifre = ?";
    		   if(con!=null)
        	   {
        	       PreparedStatement statement;
    			try {
    				statement = con.prepareStatement(sql);
    				statement.setString(1, email);
    	            statement.setString(2, md5Sifre);
    	        	ResultSet resultSet = statement.executeQuery();
    	        	if (resultSet.next()) 
   	      	        {
   	      	         try {
   	      	           FXMLLoader loader = new FXMLLoader(getClass().getResource("Yonetici.fxml"));
   	      	           Parent root = loader.load();
   	      	           Scene scene = new Scene(root,811,557);
   	      	           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
   	      	           stage.setScene(scene);
   	      	           stage.show();
   	      	               
   	      	         } catch (IOException e)
   	      	         {
   	      	            e.printStackTrace();

   	      	     }
   	      	        }
    			}
    			catch (SQLException e) {
    				e.printStackTrace();
    			}
        	   } 
    	   }
   }
    @FXML
    void ClickKayıtOlma(ActionEvent event) {
    	    try {
    	    
    	        FXMLLoader loader = new FXMLLoader(getClass().getResource("Kayıt.fxml"));
    	        Parent root = loader.load();

    	        Scene scene = new Scene(root);
    	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	        stage.setScene(scene);
    	        stage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }
    }

    @FXML
    void initialize() {
       

    }


}

