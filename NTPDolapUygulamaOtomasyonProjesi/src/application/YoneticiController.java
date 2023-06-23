package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class YoneticiController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buttonKullaniciSil;

    @FXML
    private Button buttonUrunuSil;

    @FXML
    private TableColumn<KullaniciBilgileri, String> kAd;

    @FXML
    private TableColumn<KullaniciBilgileri, String> kAdres;

    @FXML
    private TableColumn<KullaniciBilgileri, Double> kBakiye;

    @FXML
    private TableColumn<KullaniciBilgileri, Integer> kId;

    @FXML
    private TableColumn<KullaniciBilgileri, String> kMail;

    @FXML
    private TableColumn<KullaniciBilgileri, String> kSoyad;

    @FXML
    private TableColumn<KullaniciBilgileri, String> kTel;

    @FXML
    private TableView<KullaniciBilgileri> kullanicilarTableview;

    @FXML
    private TableColumn<Satislar, Integer> sId;

    @FXML
    private TableColumn<Satislar, Integer> sKullaniciId;

    @FXML
    private TableColumn<Satislar, Timestamp> sTarih;

    @FXML
    private TableColumn<Satislar, Integer> sUrunId;

    @FXML
    private TableView<Satislar> satisTableview;

    @FXML
    private TableColumn<Urunler, String> uAciklama;

    @FXML
    private TableColumn<Urunler, String> uAd;

    @FXML
    private TableColumn<Urunler, String> uCinsiyet;

    @FXML
    private TableColumn<Urunler, String> uDurum;

    @FXML
    private TableColumn<Urunler, Double> uFiyat;

    @FXML
    private TableColumn<Urunler, Integer> uID;

    @FXML
    private TableColumn<Urunler, String> uKategori;

    @FXML
    private TableColumn<Urunler, Integer> uKullaniciId;

    @FXML
    private TableColumn<Urunler, String> uOzellik;

    @FXML
    private TableView<Urunler> urunlerTableview;

    @FXML
    void clickKullaniciSil(ActionEvent event) {
    	kullaniciSil(kullaniciId);
    	kullaniciListele(kullanicilarTableview);
    }

    private void kullaniciSil(int kullaniciId2) {
    	Connection con = Baglanti.baglan();
		String sql = "DELETE FROM kullanicibilgi WHERE kullanici_id = ?";
		try {
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, kullaniciId2);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
    private int kullaniciId;
    @FXML
    void clickKullaniciTablo(MouseEvent event) {
    kullaniciId=kullanicilarTableview.getSelectionModel().getSelectedItem().getKullanici_id();
    }
    private int urunId;
    @FXML
    void clickUrunlerTablo(MouseEvent event) {
     urunId=urunlerTableview.getSelectionModel().getSelectedItem().getUrun_Id();
    }
    @FXML
    void clickUrunSil(ActionEvent event) {
    	urunSil(urunId);
    	urunListele(urunlerTableview);
    }
    @FXML
    void initialize() {
    	kullaniciListele(kullanicilarTableview);
    	urunListele(urunlerTableview);
    	satisListele(satisTableview);
    }
    public void kullaniciListele(TableView tablo) {
		String sql ="Select * from kullanicibilgi";

		ObservableList<KullaniciBilgileri> kullaniciListe = FXCollections.observableArrayList();

		try {
			Connection con = Baglanti.baglan();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				kullaniciListe.add(new KullaniciBilgileri(resultSet.getInt("kullanici_id"), resultSet.getString("kullanici_ad"),resultSet.getString("kullanici_soyad"),resultSet.getString("kullanici_telefon"),
						resultSet.getString("kullanici_mail"),resultSet.getString("kullanici_sifre"),resultSet.getString("kullanici_adres"),resultSet.getDouble("kullanici_bakiye")));
			
				kId.setCellValueFactory(new PropertyValueFactory<>("kullanici_id"));
				kAd.setCellValueFactory(new PropertyValueFactory<>("kullanici_ad") );
				kSoyad.setCellValueFactory(new PropertyValueFactory<> ("kullanici_soyad"));
				kTel.setCellValueFactory(new PropertyValueFactory<>("kullanici_telefon"));
				kMail.setCellValueFactory(new PropertyValueFactory<>("kullanici_mail"));
				kAdres.setCellValueFactory(new PropertyValueFactory<>("kullanici_adres"));
				kBakiye.setCellValueFactory(new PropertyValueFactory<>("kullanici_bakiye"));
				
				tablo.setItems(kullaniciListe);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
    
    public void urunListele(TableView tablo)
    {
    	String sql = "SELECT urun.urun_Id, urun.urun_ad, urun.urun_fiyat, urun.urun_acıklama, urun.urun_ozelligi, "
				+ " urun_kullaniciId, kategori.kategori_ad, urun.urun_cinsiyet, urun.urun_durumu,"
				+ " kullanicibilgi.kullanici_ad, kullanicibilgi.kullanici_soyad " + " FROM urun "
				+ " INNER JOIN kategori ON urun.urun_kategoriId = kategori.kategori_ıd "
				+ " INNER JOIN kullanicibilgi ON urun.urun_kullaniciId = kullanicibilgi.kullanici_id";

		ObservableList<Urunler> urunlerliste = FXCollections.observableArrayList();

		try {
			Connection con = Baglanti.baglan();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				urunlerliste.add(new Urunler(resultSet.getInt("urun_Id"), resultSet.getString("urun_ad"),
						resultSet.getDouble("urun_fiyat"), resultSet.getString("urun_acıklama"),
						resultSet.getString("urun_ozelligi"), resultSet.getString("urun_cinsiyet"),
						resultSet.getString("urun_durumu"), resultSet.getString("kategori_ad"),
						resultSet.getString("kullanici_ad"), resultSet.getString("kullanici_soyad"),
						resultSet.getInt("urun_kullaniciId")));
				
				uID.setCellValueFactory(new PropertyValueFactory<>("urun_Id"));
				uAd.setCellValueFactory(new PropertyValueFactory<>("urun_ad"));
				uFiyat.setCellValueFactory(new PropertyValueFactory<>("urun_fiyat"));
				uAciklama.setCellValueFactory(new PropertyValueFactory<>("urun_acıklama"));
				uOzellik.setCellValueFactory(new PropertyValueFactory<>("urun_ozelligi"));
				uKategori.setCellValueFactory(new PropertyValueFactory<>("kategori_ad"));
				uCinsiyet.setCellValueFactory(new PropertyValueFactory<>("urun_cinsiyet"));
				uDurum.setCellValueFactory(new PropertyValueFactory<>("urun_durumu"));
				uKullaniciId.setCellValueFactory(new PropertyValueFactory<>("urun_kullaniciId"));
				
				tablo.setItems(urunlerliste);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    }
    public void satisListele(TableView tablo)
    {
    	String sql ="SELECT * FROM satis ";
		ObservableList<Satislar> satislarListe = FXCollections.observableArrayList();

		try {
			Connection con = Baglanti.baglan();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {
				satislarListe.add(new Satislar(resultSet.getInt("satis_ıd"),resultSet.getTimestamp("satis_tarih"),resultSet.getInt("urun_ıd"),resultSet.getInt("kullanici_ıd")));
				sId.setCellValueFactory(new PropertyValueFactory<>("satis_ıd"));
				sTarih.setCellValueFactory(new PropertyValueFactory<>("satis_tarih"));
				sUrunId.setCellValueFactory(new PropertyValueFactory<>("urun_ıd"));
				sKullaniciId.setCellValueFactory(new PropertyValueFactory<>("kullanici_ıd"));
				tablo.setItems(satislarListe);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    public void urunSil(int urunId)
    {
    	Connection con = Baglanti.baglan();
		String sql = "DELETE FROM urun WHERE urun_Id = ?";

		try {

			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, urunId);
			statement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    }
}
