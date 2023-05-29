package sample.classes;

public class Mahasiswa {
    private final String nrp;
    private final String nama;

    public Mahasiswa(String nrp, String nama) {
        this.nrp = nrp;
        this.nama = nama;
    }

    public String getNrp() {
        return nrp;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return this.getNrp() + " - " + this.getNama();
    }
}
