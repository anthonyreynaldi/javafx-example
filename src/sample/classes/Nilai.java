package sample.classes;

public class Nilai {
    private final int id;
    private final String nrp;
    private final String nama;
    private final int idMatkul;
    private final String matkul;
    private final int nilai;

    public Nilai(int id, String nrp, String nama, int idMatkul, String matkul, int nilai) {
        this.id = id;
        this.nrp = nrp;
        this.nama = nama;
        this.idMatkul = idMatkul;
        this.matkul = matkul;
        this.nilai = nilai;
    }

    public Nilai(int id, String nrp, int idMatkul,int nilai) {
        this.id = id;
        this.nrp = nrp;
        this.nama = "";
        this.idMatkul = idMatkul;
        this.matkul = "";
        this.nilai = nilai;
    }

    public int getId() {
        return id;
    }

    public String getNrp() {
        return nrp;
    }

    public String getNama() {
        return nama;
    }

    public int getIdMatkul() {
        return idMatkul;
    }

    public String getMatkul() {
        return matkul;
    }

    public int getNilai() {
        return nilai;
    }
}
