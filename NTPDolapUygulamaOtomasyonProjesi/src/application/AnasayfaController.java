package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Comparator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import application.User;
import java.util.Date;
import java.sql.Timestamp;

public class AnasayfaController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;
	@FXML
	private TableColumn<Urunler, String> columnUrunAcıklama;

	@FXML
	private TableColumn<Urunler, String> columnUrunAd;

	@FXML
	private TableColumn<Urunler, String> columnUrunCinsiyet;

	@FXML
	private TableColumn<Urunler, String> columnUrunDurum;

	@FXML
	private TableColumn<Urunler, Double> columnUrunFiyat;

	@FXML
	private TableColumn<Urunler, String> columnUrunKategori;

	@FXML
	private TableColumn<Urunler, String> columnUrunOzellik;

	@FXML
	private TableColumn<Urunler, String> columnUrunSatici;

	@FXML
	private TableColumn<Urunler, Integer> columnKullaniciId;
	@FXML
	private TableColumn<Urunler, String> columnUrunumAcıklama;

	@FXML
	private TableColumn<Urunler, String> columnUrunumCinsiyet;

	@FXML
	private TableColumn<Urunler, String> columnUrunumDurum;

	@FXML
	private TableColumn<Urunler, Double> columnUrunumFiyat;

	@FXML
	private TableColumn<Urunler, String> columnUrunumKategori;

	@FXML
	private TableColumn<Urunler, String> columnUrunumOzellik;

	@FXML
	private TableColumn<Urunler, String> columnUrunumad;

	@FXML
	private TableView<Urunler> anasayfaTableview;
	@FXML
	private Button buttonFitrele;

	@FXML
	private Button buttonBakiyeEkle;

	@FXML
	private Button buttonBilgilerim;

	@FXML
	private Button buttonSatinAl;

	@FXML
	private Button buttonUrunArama;

	@FXML
	private Button buttonUrunEkle;

	@FXML
	private Button buttonUrunuSil;

	@FXML
	private Button buttonKategoriSec;

	@FXML
	private Button buttonFitrelTemizle;

	@FXML
	private Button buttonKategoriTemizle;

	@FXML
	private Button buttonUrunAramaTemzile;

	@FXML
	private CheckBox chckErkek;

	@FXML
	private CheckBox chckKadin;

	@FXML
	private CheckBox chckboxEtiketliUrun;

	@FXML
	private CheckBox chckboxEtiketsizUrun;

	@FXML
	private CheckBox chckboxUcuzPahali;

	@FXML
	private ComboBox<String> comboKategoriSec;

	@FXML
	private Label lblBakiye;

	@FXML
	private TableView<Urunler> satinAldiklarimLTableview;

	@FXML
	private Tab tabAnasayfa;

	@FXML
	private Tab tabHesabim;

	@FXML
	private TextField tutarTxt;

	@FXML
	private TextField urunAramaTxt;

	@FXML
	private TableView<Urunler> urunlerimTableview;
	@FXML
	private TableColumn<Urunler, String> columnAldiklarimUrunAd;

	@FXML
	private TableColumn<Urunler, String> columnAldıklarımAcıklama;

	@FXML
	private TableColumn<Urunler, String> columnAldıklarımCinsiyet;

	@FXML
	private TableColumn<Urunler, Double> columnAldıklarımFiyat;

	@FXML
	private TableColumn<Urunler, String> columnAldıklarımKategori;

	@FXML
	private TableColumn<Urunler, String> columnAldıklarımOzellik;

	@FXML
	private TableColumn<Urunler, String> columnAldıklarımSatıcı;
	@FXML
	private TableColumn<Urunler, Integer> columnUrunumId;

	@FXML
	void clickBakiyeEkle(ActionEvent event) {

	}

	@FXML
	void clickBilgilerim(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("KullaniciBilgileri.fxml"));
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
	void clickSatinAl(ActionEvent event) {
		satinAl();
		listele(anasayfaTableview);
		satinAldıklarımListele(satinAldiklarimLTableview);
	}

	@FXML
	void clickUrunArama(ActionEvent event) {
		urunAra();
	}

	@FXML
	void clickUrunAramaTemizle(ActionEvent event) {
		listele(anasayfaTableview);
		urunAramaTxt.clear();

	}

	@FXML
	void clickUrunEkle(ActionEvent event) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("UrunEkle.fxml"));
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
	void clickUrunuSil(ActionEvent event) {

		urunumuSil();
		urunlerimListele(urunlerimTableview);
		listele(anasayfaTableview);

	}

	@FXML
	void clickFitrele(ActionEvent event) {
		if (chckboxEtiketliUrun.isSelected()) {
			String sql = " where urun.urun_ozelligi='Etiketli'";
			sqlSorgusunaGoreUrunleriListele(sql);

		}
		if (chckboxEtiketsizUrun.isSelected()) {
			String sql = " where urun.urun_ozelligi='Etiketsiz'";
			sqlSorgusunaGoreUrunleriListele(sql);
		}
	}

	@FXML
	void clickFitreTemizle(ActionEvent event) {
		listele(anasayfaTableview);
		chckboxEtiketliUrun.setSelected(false);
		chckboxEtiketsizUrun.setSelected(false);

	}

	@FXML
	void clickKategoriSec(ActionEvent event) {

		if (chckErkek.isSelected()) {
			String sql = " where urun.urun_cinsiyet='Erkek'";
			sqlSorgusunaGoreUrunleriListele(sql);

		}

		if (chckKadin.isSelected()) {
			String sql = " where urun.urun_cinsiyet='Kadın'";
			sqlSorgusunaGoreUrunleriListele(sql);
		}

		String selectedValue = comboKategoriSec.getSelectionModel().getSelectedItem();
		if (comboKategoriSec != null) {
			switch (selectedValue) {
			case "Giyim":
				String sql = " where kategori.kategori_ad='Giyim'";
				sqlSorgusunaGoreUrunleriListele(sql);
				break;
			case "Aksesuar":
				String sqlA = " where kategori.kategori_ad='Aksesuar'";
				sqlSorgusunaGoreUrunleriListele(sqlA);
				break;
			case "Çanta":
				String sqlC = " where kategori.kategori_ad='Çanta'";
				sqlSorgusunaGoreUrunleriListele(sqlC);
				break;
			case "Ayakkabı":
				String sqlAk = " where kategori.kategori_ad='Ayakkabı'";
				sqlSorgusunaGoreUrunleriListele(sqlAk);
				break;
			default:
				break;
			}
		}
	}

	@FXML
	void clickKategoriTemizle(ActionEvent event) {
		listele(anasayfaTableview);
		chckErkek.setSelected(false);
		chckKadin.setSelected(false);
		comboKategoriSec.getSelectionModel().clearSelection();
	}

	@FXML
	void initialize() {

		String[] kategori = { "Giyim", "Ayakkabı", "Aksesuar", "Çanta" };
		comboKategoriSec.getItems().addAll(kategori);
		listele(anasayfaTableview);
		urunlerimListele(urunlerimTableview);
		satinAldıklarımListele(satinAldiklarimLTableview);
		ucuzdanPahaliyaSiralama();

	}

	public void sqlSorgusunaGoreUrunleriListele(String newsql) {
		String constSql = "SELECT urun.urun_Id, urun.urun_ad, urun.urun_fiyat, urun.urun_acıklama, urun.urun_ozelligi, "
				+ "urun.urun_kullaniciId, kategori.kategori_ad, urun.urun_cinsiyet, urun.urun_durumu,"
				+ " kullanicibilgi.kullanici_ad, kullanicibilgi.kullanici_soyad " + "FROM urun "
				+ "INNER JOIN kategori ON urun.urun_kategoriId = kategori.kategori_ıd "
				+ "INNER JOIN kullanicibilgi ON urun.urun_kullaniciId = kullanicibilgi.kullanici_id" + newsql;

		ObservableList<Urunler> urunlerliste = FXCollections.observableArrayList();

		try {
			Connection con = Baglanti.baglan();
			Statement statement = con.createStatement();
			ResultSet resultSet = statement.executeQuery(constSql);

			while (resultSet.next()) {
				urunlerliste.add(new Urunler(resultSet.getInt("urun_Id"), resultSet.getString("urun_ad"),
						resultSet.getDouble("urun_fiyat"), resultSet.getString("urun_acıklama"),
						resultSet.getString("urun_ozelligi"), resultSet.getString("urun_cinsiyet"),
						resultSet.getString("urun_durumu"), resultSet.getString("kategori_ad"),
						resultSet.getString("kullanici_ad"), resultSet.getString("kullanici_soyad"),
						resultSet.getInt("urun_kullaniciId")));
				columnUrunAd.setCellValueFactory(new PropertyValueFactory<>("urun_ad"));
				columnUrunFiyat.setCellValueFactory(new PropertyValueFactory<>("urun_fiyat"));
				columnUrunAcıklama.setCellValueFactory(new PropertyValueFactory<>("urun_acıklama"));
				columnUrunOzellik.setCellValueFactory(new PropertyValueFactory<>("urun_ozelligi"));
				columnUrunKategori.setCellValueFactory(new PropertyValueFactory<>("kategori_ad"));
				columnUrunCinsiyet.setCellValueFactory(new PropertyValueFactory<>("urun_cinsiyet"));
				columnUrunDurum.setCellValueFactory(new PropertyValueFactory<>("urun_durumu"));
				columnUrunSatici.setCellValueFactory(new PropertyValueFactory<>("tamAd"));
				columnKullaniciId.setCellValueFactory(new PropertyValueFactory<>("urun_kullaniciId"));

				anasayfaTableview.setItems(urunlerliste);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void listele(TableView tablo) {
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
				columnUrunAd.setCellValueFactory(new PropertyValueFactory<>("urun_ad"));
				columnUrunFiyat.setCellValueFactory(new PropertyValueFactory<>("urun_fiyat"));
				columnUrunAcıklama.setCellValueFactory(new PropertyValueFactory<>("urun_acıklama"));
				columnUrunOzellik.setCellValueFactory(new PropertyValueFactory<>("urun_ozelligi"));
				columnUrunKategori.setCellValueFactory(new PropertyValueFactory<>("kategori_ad"));
				columnUrunCinsiyet.setCellValueFactory(new PropertyValueFactory<>("urun_cinsiyet"));
				columnUrunDurum.setCellValueFactory(new PropertyValueFactory<>("urun_durumu"));
				columnUrunSatici.setCellValueFactory(new PropertyValueFactory<>("tamAd"));
				columnKullaniciId.setCellValueFactory(new PropertyValueFactory<>("urun_kullaniciId"));
				tablo.setItems(urunlerliste);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void urunlerimListele(TableView tablo) {
		String sql = "SELECT urun.urun_Id, urun.urun_ad, urun.urun_fiyat, urun.urun_acıklama, urun.urun_ozelligi, "
				+ " urun_kullaniciId, kategori.kategori_ad, urun.urun_cinsiyet, urun.urun_durumu,"
				+ " kullanicibilgi.kullanici_ad, kullanicibilgi.kullanici_soyad " + " FROM urun "
				+ " INNER JOIN kategori ON urun.urun_kategoriId = kategori.kategori_ıd "
				+ " INNER JOIN kullanicibilgi ON urun.urun_kullaniciId = kullanicibilgi.kullanici_id"
				+ " where urun.urun_kullaniciId= ? ";

		ObservableList<Urunler> urunlerliste = FXCollections.observableArrayList();
		try {

			Connection con = Baglanti.baglan();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, User.userId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				urunlerliste.add(new Urunler(resultSet.getInt("urun_Id"), resultSet.getString("urun_ad"),
						resultSet.getDouble("urun_fiyat"), resultSet.getString("urun_acıklama"),
						resultSet.getString("urun_ozelligi"), resultSet.getString("urun_cinsiyet"),
						resultSet.getString("urun_durumu"), resultSet.getString("kategori_ad"),
						resultSet.getString("kullanici_ad"), resultSet.getString("kullanici_soyad"),
						resultSet.getInt("urun_kullaniciId")));
				columnUrunumId.setCellValueFactory(new PropertyValueFactory<>("urun_Id"));
				columnUrunumad.setCellValueFactory(new PropertyValueFactory<>("urun_ad"));
				columnUrunumFiyat.setCellValueFactory(new PropertyValueFactory<>("urun_fiyat"));
				columnUrunumAcıklama.setCellValueFactory(new PropertyValueFactory<>("urun_acıklama"));
				columnUrunumOzellik.setCellValueFactory(new PropertyValueFactory<>("urun_ozelligi"));
				columnUrunumKategori.setCellValueFactory(new PropertyValueFactory<>("kategori_ad"));
				columnUrunumCinsiyet.setCellValueFactory(new PropertyValueFactory<>("urun_cinsiyet"));
				columnUrunumDurum.setCellValueFactory(new PropertyValueFactory<>("urun_durumu"));

				tablo.setItems(urunlerliste);

			}
			resultSet.close();
			statement.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void satinAldıklarımListele(TableView tablo) {
		
		String sql = "SELECT urun.urun_Id, urun.urun_ad, urun.urun_fiyat, urun.urun_acıklama, urun.urun_ozelligi, "
				+ " urun_kullaniciId, kategori.kategori_ad, urun.urun_cinsiyet, urun.urun_durumu,"
				+ " kullanicibilgi.kullanici_ad, kullanicibilgi.kullanici_soyad, satis.satis_ıd, satis.satis_tarih, satis.urun_ıd, satis.kullanici_ıd "
				+ " FROM urun " + " INNER JOIN kategori ON urun.urun_kategoriId = kategori.kategori_ıd "
				+ " INNER JOIN kullanicibilgi ON urun.urun_kullaniciId = kullanicibilgi.kullanici_id"
				+ " INNER JOIN satis ON urun.urun_Id = satis.urun_ıd " + " WHERE satis.kullanici_ıd = ?";

		ObservableList<Urunler> urunlerliste = FXCollections.observableArrayList();
		try {

			Connection con = Baglanti.baglan();
			PreparedStatement statement = con.prepareStatement(sql);
			statement.setInt(1, User.userId);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				urunlerliste.add(new Urunler(resultSet.getInt("urun_Id"), resultSet.getString("urun_ad"),
						resultSet.getDouble("urun_fiyat"), resultSet.getString("urun_acıklama"),
						resultSet.getString("urun_ozelligi"), resultSet.getString("urun_cinsiyet"),
						resultSet.getString("urun_durumu"), resultSet.getString("kategori_ad"),
						resultSet.getString("kullanici_ad"), resultSet.getString("kullanici_soyad"),
						resultSet.getInt("urun_kullaniciId")));
				columnAldiklarimUrunAd.setCellValueFactory(new PropertyValueFactory<>("urun_ad"));
				columnAldıklarımFiyat.setCellValueFactory(new PropertyValueFactory<>("urun_fiyat"));
				columnAldıklarımAcıklama.setCellValueFactory(new PropertyValueFactory<>("urun_acıklama"));
				columnAldıklarımOzellik.setCellValueFactory(new PropertyValueFactory<>("urun_ozelligi"));
				columnAldıklarımKategori.setCellValueFactory(new PropertyValueFactory<>("kategori_ad"));
				columnAldıklarımCinsiyet.setCellValueFactory(new PropertyValueFactory<>("urun_cinsiyet"));
				columnAldıklarımSatıcı.setCellValueFactory(new PropertyValueFactory<>("tamAd"));
				

				tablo.setItems(urunlerliste);

			}
			resultSet.close();
			statement.close();
			con.close();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void ucuzdanPahaliyaSiralama() {
		anasayfaTableview.getSortOrder().clear();
		anasayfaTableview.getSortOrder().add(columnUrunFiyat);

	}

	public void urunAra() {
		String arananUrun = urunAramaTxt.getText();

		String sql = "SELECT urun.urun_Id, urun.urun_ad, urun.urun_fiyat, urun.urun_acıklama, urun.urun_ozelligi, "
				+ "urun.urun_kullaniciId, kategori.kategori_ad, urun.urun_cinsiyet, urun.urun_durumu,"
				+ " kullanicibilgi.kullanici_ad, kullanicibilgi.kullanici_soyad " + "FROM urun "
				+ "INNER JOIN kategori ON urun.urun_kategoriId = kategori.kategori_ıd "
				+ "INNER JOIN kullanicibilgi ON urun.urun_kullaniciId = kullanicibilgi.kullanici_id where urun.urun_ad LIKE '%"
				+ arananUrun + "%'";

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
				columnUrunAd.setCellValueFactory(new PropertyValueFactory<>("urun_ad"));
				columnUrunFiyat.setCellValueFactory(new PropertyValueFactory<>("urun_fiyat"));
				columnUrunAcıklama.setCellValueFactory(new PropertyValueFactory<>("urun_acıklama"));
				columnUrunOzellik.setCellValueFactory(new PropertyValueFactory<>("urun_ozelligi"));
				columnUrunKategori.setCellValueFactory(new PropertyValueFactory<>("kategori_ad"));
				columnUrunCinsiyet.setCellValueFactory(new PropertyValueFactory<>("urun_cinsiyet"));
				columnUrunDurum.setCellValueFactory(new PropertyValueFactory<>("urun_durumu"));
				columnUrunSatici.setCellValueFactory(new PropertyValueFactory<>("tamAd"));
				columnKullaniciId.setCellValueFactory(new PropertyValueFactory<>("urun_kullaniciId"));

				anasayfaTableview.setItems(urunlerliste);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private int urunId;
	public void urunumuSil() {
		urunlerimTableview.getSelectionModel().selectedItemProperty()
				.addListener((obs, eskiSeciliUrun, yeniSeciliUrun) -> {
					if (yeniSeciliUrun != null) {
						urunId = yeniSeciliUrun.getUrun_Id();
					}
				});

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

	private double fiyat = 0.0;
	private int saticiKullaniciId;

	public void satinAl() {
		fiyat = anasayfaTableview.getSelectionModel().getSelectedItem().getUrun_fiyat();
		saticiKullaniciId = anasayfaTableview.getSelectionModel().getSelectedItem().getUrun_kullaniciId();
		urunId = anasayfaTableview.getSelectionModel().getSelectedItem().getUrun_Id();

		LoginController idAl = new LoginController();
		Connection con = Baglanti.baglan();

		if (con != null) {
			try {
				String sql = "SELECT kullanici_bakiye FROM kullanicibilgi WHERE kullanici_id = ?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1, User.getUserId());
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					double kullaniciBakiye = resultSet.getDouble("kullanici_bakiye");
					if (kullaniciBakiye >= fiyat) {
						Alert alert1 = new Alert(AlertType.INFORMATION);
						alert1.setTitle("Bilgi");
						alert1.setHeaderText("Satış");
						alert1.setContentText("Satın Almak İstediğinizden Emin misiniz?");

						ButtonType evetButton = new ButtonType("Evet");
						ButtonType iptalButton = new ButtonType("İptal");
						alert1.getButtonTypes().setAll(evetButton, iptalButton);

						alert1.showAndWait().ifPresent(buttonType -> {
							if (buttonType == evetButton) {

								String sqlUpdate = "UPDATE kullanicibilgi SET kullanici_bakiye = ? - ? WHERE kullanici_id = ?";
								PreparedStatement updateStatement;
								try {
									updateStatement = con.prepareStatement(sqlUpdate);
									updateStatement.setDouble(1, kullaniciBakiye);
									updateStatement.setDouble(2, fiyat);
									updateStatement.setInt(3, User.userId);

									int rowsAffected = updateStatement.executeUpdate();

									if (rowsAffected > 0) {
										System.out.println("Satın alma işlemi başarıyla tamamlandı.");
									}
									updateStatement.close();

									saticiBakiyeArttir(saticiKullaniciId, fiyat);
									urunDurumunuGuncelle(urunId);
									satisTablosunaUrunEkle(urunId);

								} catch (SQLException e) {

									e.printStackTrace();
								}
							} else if (buttonType == iptalButton) {
								System.out.println("Satın alma işlemi iptal edildi");
							}
						});

					} else {
						System.out.println("Yetersiz bakiye. Satın alma işlemi gerçekleştirilemedi.");
					}
				}

				resultSet.close();
				statement.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private void satisTablosunaUrunEkle(int urunId2) {
		
		Connection con = Baglanti.baglan();

		if (con != null) {

			try {

				String sqlUpdate = "INSERT INTO satis (satis_tarih,urun_ıd,kullanici_ıd) values(?,?,?)";
				PreparedStatement updateStatement;
				updateStatement = con.prepareStatement(sqlUpdate);
				Date now = new Date();
	            Timestamp time = new Timestamp(now.getTime());
				updateStatement.setTimestamp(1,time);
				updateStatement.setInt(2, urunId2);
				updateStatement.setInt(3,User.userId );
				updateStatement.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}

	}

	private void urunDurumunuGuncelle(int urunId2) {

		Connection con = Baglanti.baglan();

		if (con != null) {

			try {

				String sqlUpdate = "UPDATE urun SET urun_durumu = 'Satıldı' WHERE urun_Id = ?";
				PreparedStatement updateStatement;
				updateStatement = con.prepareStatement(sqlUpdate);
				updateStatement.setInt(1, urunId2);

				updateStatement.executeUpdate();

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
	}

	private void saticiBakiyeArttir(int saticiKullaniciId2, double fiyat2) {

		Connection con = Baglanti.baglan();

		if (con != null) {
			try {
				String sql = "SELECT kullanici_bakiye FROM kullanicibilgi WHERE kullanici_id = ?";
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setInt(1, saticiKullaniciId2);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					double kullaniciBakiye = resultSet.getDouble("kullanici_bakiye");

					String sqlUpdate = "UPDATE kullanicibilgi SET kullanici_bakiye = ? + ? WHERE kullanici_id = ?";
					PreparedStatement updateStatement;
					try {
						updateStatement = con.prepareStatement(sqlUpdate);
						updateStatement.setDouble(1, kullaniciBakiye);
						updateStatement.setDouble(2, fiyat);
						updateStatement.setInt(3, saticiKullaniciId2);

						int rowsAffected = updateStatement.executeUpdate();

						if (rowsAffected > 0) {
							System.out.println("Satıcı bakiye güncellendi.");
						}
						updateStatement.close();

					} catch (SQLException e) {

						e.printStackTrace();
					}

				}

			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}

}
