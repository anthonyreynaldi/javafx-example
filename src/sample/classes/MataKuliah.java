package sample.classes;

public class MataKuliah {
    private final int id;
    private final String nama;

    public MataKuliah(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    @Override
    public String toString() {
        return this.getNama();
    }
}
