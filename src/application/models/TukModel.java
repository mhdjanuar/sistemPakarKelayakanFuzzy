package application.models;

public class TukModel {
    private int id;
    private String namaTuk;
    private String instansiPenyelenggara;
    private String alamat;
    private String noTelepon;
    private String email;
    private String kecamatan;
    private String kabupaten;
    private String provinsi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaTuk() {
        return namaTuk;
    }

    public void setNamaTuk(String namaTuk) {
        this.namaTuk = namaTuk;
    }

    public String getInstansiPenyelenggara() {
        return instansiPenyelenggara;
    }

    public void setInstansiPenyelenggara(String instansiPenyelenggara) {
        this.instansiPenyelenggara = instansiPenyelenggara;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
}
