package application;

public class KullaniciBilgileri {
	
	private int kullanici_id;
	private String kullanici_ad;
	private String kullanici_soyad;
	private String kullanici_telefon;
	private String kullanici_mail;
	private String kullanici_sifre;
	private String kullanici_adres;
	private Double kullanici_bakiye;
	
	public KullaniciBilgileri(int kullanici_id, String kullanici_ad, String kullanici_soyad, String kullanici_telefon,
			String kullanici_mail, String kullanici_sifre, String kullanici_adres, Double kullanici_bakiye) {
		super();
		this.kullanici_id = kullanici_id;
		this.kullanici_ad = kullanici_ad;
		this.kullanici_soyad = kullanici_soyad;
		this.kullanici_telefon = kullanici_telefon;
		this.kullanici_mail = kullanici_mail;
		this.kullanici_sifre = kullanici_sifre;
		this.kullanici_adres = kullanici_adres;
		this.kullanici_bakiye = kullanici_bakiye;
	}
	public int getKullanici_id() {
		return kullanici_id;
	}
	public void setKullanici_id(int kullanici_id) {
		this.kullanici_id = kullanici_id;
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
	public String getKullanici_telefon() {
		return kullanici_telefon;
	}
	public void setKullanici_telefon(String kullanici_telefon) {
		this.kullanici_telefon = kullanici_telefon;
	}
	public String getKullanici_mail() {
		return kullanici_mail;
	}
	public void setKullanici_mail(String kullanici_mail) {
		this.kullanici_mail = kullanici_mail;
	}
	public String getSifre() {
		return kullanici_sifre;
	}
	public void setSifre(String sifre) {
		this.kullanici_sifre = sifre;
	}
	public String getKullanici_adres() {
		return kullanici_adres;
	}
	public void setKullanici_adres(String kullanici_adres) {
		this.kullanici_adres = kullanici_adres;
	}
	public Double getKullanici_bakiye() {
		return kullanici_bakiye;
	}
	public void setKullanici_bakiye(Double kullanici_bakiye) {
		this.kullanici_bakiye = kullanici_bakiye;
	}
	
}
