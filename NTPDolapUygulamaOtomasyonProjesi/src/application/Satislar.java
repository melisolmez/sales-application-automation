package application;
import java.sql.Timestamp;

public class Satislar {

	private int satis_ıd;
	private Timestamp satis_tarih;
	private int urun_ıd;
	private int kullanici_ıd;
	
	public Satislar(int satis_ıd, Timestamp satis_tarih, int urun_ıd, int kullanici_ıd) {
		super();
		this.satis_ıd = satis_ıd;
		this.satis_tarih = satis_tarih;
		this.urun_ıd = urun_ıd;
		this.kullanici_ıd = kullanici_ıd;
	}
	public int getSatis_ıd() {
		return satis_ıd;
	}
	public void setSatis_ıd(int satis_ıd) {
		this.satis_ıd = satis_ıd;
	}
	public Timestamp getSatis_tarih() {
		return satis_tarih;
	}
	public void setSatis_tarih(Timestamp satis_tarih) {
		this.satis_tarih = satis_tarih;
	}
	public int getUrun_ıd() {
		return urun_ıd;
	}
	public void setUrun_ıd(int urun_ıd) {
		this.urun_ıd = urun_ıd;
	}
	public int getKullanici_ıd() {
		return kullanici_ıd;
	}
	public void setKullanici_ıd(int kullanici_ıd) {
		this.kullanici_ıd = kullanici_ıd;
	}

}
