package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class KullaniciBilgileriController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<KullaniciBilgileri> bilgilerimTableview;

    @FXML
    private TextField bilgimAdresTxt;

    @FXML
    private TextField bilgimAdtxt;

    @FXML
    private TextField bilgimBakiyeTxt;

    @FXML
    private PasswordField bilgimEskiSifreTxt;

    @FXML
    private TextField bilgimMailTxt;

    @FXML
    private TextField bilgimSoyadtxt;

    @FXML
    private TextField bilgimTelTxt;

    @FXML
    private PasswordField bilgimYeniSifreTxt;

    @FXML
    private Button buttonBilgilerimiGuncelle;

    @FXML
    private Button buttonGeri;

    @FXML
    private TableColumn<KullaniciBilgileri, String> clmBilgilerimAd;

    @FXML
    private TableColumn<KullaniciBilgileri, String> clmBilgilerimSifre;

    @FXML
    private TableColumn<KullaniciBilgileri, String> clmBilgilerimSoyad;

    @FXML
    private TableColumn<KullaniciBilgileri, String> cmlBilgilerimAdres;

    @FXML
    private TableColumn<KullaniciBilgileri, Double> cmlBilgilerimBakiye;

    @FXML
    private TableColumn<KullaniciBilgileri, String> cmlBilgilerimMail;

    @FXML
    private TableColumn<KullaniciBilgileri, String> cmlBilgilerimTel;
    
    @FXML
    private void bilgileriTxteGetir()
    {
    	bilgileriTextFieldaGetir();
    }

    @FXML
    void clickBilgilerimiGuncelle(ActionEvent event) {
    	
    	 bilgiGuncelle();
    }

    @FXML
    void clickGeri(ActionEvent event) {
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
    	bilgilerimiListele(bilgilerimTableview);
    }
    
    public void bilgilerimiListele(TableView tablo)
    {
    	String sql = "SELECT* from kullanicibilgi where kullanici_Id=? ";
  	   ObservableList<KullaniciBilgileri> bilgiListe=FXCollections.observableArrayList();
  	  
  	  try {
  		Connection con = Baglanti.baglan();
        PreparedStatement state= con.prepareStatement(sql);
        state.setInt(1,User.userId);
        ResultSet resultSet = state.executeQuery();
  		
        while(resultSet.next())
        {
        	bilgiListe.add(new KullaniciBilgileri(resultSet.getInt("kullanici_Id"),resultSet.getString("kullanici_ad"),resultSet.getString("kullanici_soyad"),resultSet.getString("kullanici_telefon"),
        			resultSet.getString("kullanici_mail"),resultSet.getString("kullanici_sifre"),resultSet.getString("kullanici_adres"),resultSet.getDouble("kullanici_bakiye")));
        	
        	clmBilgilerimAd.setCellValueFactory(new PropertyValueFactory<>("kullanici_ad"));
        	clmBilgilerimSoyad.setCellValueFactory(new PropertyValueFactory<>("kullanici_soyad"));
        	cmlBilgilerimTel.setCellValueFactory(new PropertyValueFactory<>("kullanici_telefon"));
        	cmlBilgilerimMail.setCellValueFactory(new PropertyValueFactory<>("kullanici_mail"));
        	cmlBilgilerimAdres.setCellValueFactory(new PropertyValueFactory<>("kullanici_adres"));
        	cmlBilgilerimBakiye.setCellValueFactory(new PropertyValueFactory<>("kullanici_bakiye"));
        	
        	tablo.setItems(bilgiListe);
        }
        
  		  
  	  }catch(Exception e)
  	  {
  		  System.out.println(e.getMessage());
  	  }
    }
    
    public void bilgiGuncelle() {
        
        String yeniAd = bilgimAdtxt.getText();
        String yeniSoyad = bilgimSoyadtxt.getText();
        String yeniTelefon = bilgimTelTxt.getText();
        String yeniMail = bilgimMailTxt.getText();
        String yeniAdres = bilgimAdresTxt.getText();
        String yeniSifre=bilgimYeniSifreTxt.getText();
        String md5YeniSifre=Sifreleme.hashMD5(yeniSifre);
        double yeniBakiye = Double.parseDouble(bilgimBakiyeTxt.getText());
        
        if(sifreKontrol())
        {
        try {
        	
        	     Connection con = Baglanti.baglan();
        	     Statement statement = con.createStatement();
        	     ResultSet resultSet = statement.executeQuery("SELECT * FROM kullanicibilgi WHERE kullanici_Id = " + User.userId);
        	
	        	if (resultSet.next()) 
	        	{
	                String sql = "UPDATE kullanicibilgi SET kullanici_ad = ?, kullanici_soyad = ?, kullanici_telefon = ?, kullanici_mail = ?, kullanici_sifre= ?, kullanici_adres = ?, kullanici_bakiye = ? WHERE kullanici_Id = ?";
	                PreparedStatement statement1 = con.prepareStatement(sql);
	                statement1.setString(1, yeniAd);
	                statement1.setString(2, yeniSoyad);
	                statement1.setString(3, yeniTelefon);
	                statement1.setString(4, yeniMail);
	                statement1.setString(5,md5YeniSifre );
	                statement1.setString(6, yeniAdres);
	                statement1.setDouble(7, yeniBakiye);
	                statement1.setInt(8, User.userId);

	                int affectedRows = statement1.executeUpdate();

	                if (affectedRows > 0) {
	                    bilgilerimiListele(bilgilerimTableview);
	                } else {
	                    System.out.println("Güncelleme işlemi yapılamadı");   
	                    }

	                statement.close();
                }
	        	resultSet.close();
	            statement.close();
	        	 con.close();
	        	}catch (Exception e) {
	                e.printStackTrace();
	        	}
        } 
        else
    	{
    		System.out.println("Eski şifre hatalı girildi");
    	}
    }
    
    public boolean sifreKontrol()
    {
        String eskiSifre=bilgimEskiSifreTxt.getText();
        String md5EskiSifre=Sifreleme.hashMD5(eskiSifre);
        String sifre = null;
        
        Connection con = Baglanti.baglan();
        String sqlSifre = "SELECT kullanici_sifre FROM kullanicibilgi";
        
		try {
			PreparedStatement statement2 = con.prepareStatement(sqlSifre);
			ResultSet resultSet2 = statement2.executeQuery();

			while (resultSet2.next()) {
			    sifre = resultSet2.getString("kullanici_sifre");
			    
			}
			resultSet2.close();
			statement2.close();
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		
    	if(md5EskiSifre.equals(sifre))
    	{
    		return true;
    	}
    	
    	return false;
    }
   
    private String ad;
    private String soyad;
    private String mail;
	private String adres;
	private Double bakiye;
	private String tel;
    public void bilgileriTextFieldaGetir()
    {
      	    	ad=bilgilerimTableview.getSelectionModel().getSelectedItem().getKullanici_ad();
      	    	soyad=bilgilerimTableview.getSelectionModel().getSelectedItem().getKullanici_soyad();
      	    	mail=bilgilerimTableview.getSelectionModel().getSelectedItem().getKullanici_mail();
      	    	adres=bilgilerimTableview.getSelectionModel().getSelectedItem().getKullanici_adres();
      	    	bakiye=bilgilerimTableview.getSelectionModel().getSelectedItem().getKullanici_bakiye();
      	    	tel=bilgilerimTableview.getSelectionModel().getSelectedItem().getKullanici_telefon();	 
      	    	
      	    	bilgimAdtxt.setText(ad);
      	    	bilgimSoyadtxt.setText(soyad);
      	    	bilgimTelTxt.setText(tel);
      	    	bilgimMailTxt.setText(mail);
      	    	bilgimAdresTxt.setText(adres);
                bilgimBakiyeTxt.setText(bakiye.toString());
    	
    }
}
