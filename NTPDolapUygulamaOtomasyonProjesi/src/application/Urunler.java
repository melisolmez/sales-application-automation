package application;

public class Urunler {
	
	private int urun_Id;
	private String urun_ad;
	private double urun_fiyat;
	private String urun_acıklama;
	private String urun_ozelligi;
	private String urun_cinsiyet;
	private String urun_durumu;
	private int urun_kullaniciId;
	private int urun_kategoriId;
	private String kategori_ad;
	private String kullanici_ad;
	private String kullanici_soyad;
	private String tamAd;

	public Urunler(int urun_Id, String urun_ad, double urun_fiyat, String urun_acıklama, String urun_ozelligi
		, String urun_cinsiyet, String urun_durumu,String kategori_ad,String kullanici_ad,String kullanici_soyad,int urun_kullaniciId) {
		this.urun_Id = urun_Id;
		this.urun_ad = urun_ad;
		this.urun_fiyat = urun_fiyat;
		this.urun_acıklama = urun_acıklama;
		this.urun_ozelligi = urun_ozelligi;
		this.urun_cinsiyet = urun_cinsiyet;
		this.urun_durumu = urun_durumu;
		this.kategori_ad=kategori_ad;
		this.kullanici_ad=kullanici_ad;
		this.kullanici_soyad=kullanici_soyad;
		this.urun_kullaniciId=urun_kullaniciId;

	}

	public void setTamAd(String tamAd) {
		this.tamAd = tamAd;
	}
	public String getTamAd() {
        return kullanici_ad + " " + kullanici_soyad;
    }

	public String getKullanici_ad() {
		return kullanici_ad;
	}


	public void setKullanici_ad(String kullanici_ad) {
		this.kullanici_ad = kullanici_ad;
	}


	public String getKullanici_soyad() {
		return kullanici_soyad;
	}


	public void setKullanici_soyad(String kullanici_soyad) {
		this.kullanici_soyad = kullanici_soyad;
	}
	
	public int getUrun_Id() {
		return urun_Id;
	}
	public void setUrun_Id(int urun_Id) {
		this.urun_Id = urun_Id;
	}
	public int getUrun_kategoriId() {
		return urun_kategoriId;
	}
	public void setUrun_kategoriId(int urun_kategoriId) {
		this.urun_kategoriId = urun_kategoriId;
	}
	public String getKategori_ad() {
		return kategori_ad;
	}
	public void setKategori_ad(String kategori_ad) {
		this.kategori_ad = kategori_ad;
	}
	public String getUrun_ad() {
		return urun_ad;
	}
	public void setUrun_ad(String urun_ad) {
		this.urun_ad = urun_ad;
	}
	public double getUrun_fiyat() {
		return urun_fiyat;
	}
	public void setUrun_fiyat(double urun_fiyat) {
		this.urun_fiyat = urun_fiyat;
	}
	public String getUrun_acıklama() {
		return urun_acıklama;
	}
	public void setUrun_acıklama(String urun_acıklama) {
		this.urun_acıklama = urun_acıklama;
	}
	public String getUrun_ozelligi() {
		return urun_ozelligi;
	}
	public void setUrun_ozelligi(String urun_ozelligi) {
		this.urun_ozelligi = urun_ozelligi;
	}
	
	public String getUrun_cinsiyet() {
		return urun_cinsiyet;
	}
	public void setUrun_cinsiyet(String urun_cinsiyet) {
		this.urun_cinsiyet = urun_cinsiyet;
	}
	public String getUrun_durumu() {
		return urun_durumu;
	}
	public void setUrun_durumu(String urun_durumu) {
		this.urun_durumu = urun_durumu;
	}
	public int getUrun_kullaniciId() {
		return urun_kullaniciId;
	}
	public void setUrun_kullaniciId(int urun_kullaniciId) {
		this.urun_kullaniciId = urun_kullaniciId;
	}
	public String getKategoriAd() {
		return kategori_ad;
	}
	public void setKategoriAd(String kategoriAd) {
		this.kategori_ad = kategoriAd;
	}

}
