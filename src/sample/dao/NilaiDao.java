package sample.dao;

import sample.DatabaseConnection;
import sample.classes.Mahasiswa;
import sample.classes.Nilai;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class NilaiDao implements DAO<Nilai> {

    @Override
    public ArrayList<Nilai> getAll() throws SQLException {
        String query = "SELECT nilai.id as id_nilai, mahasiswa.nrp, mahasiswa.nama as nama_mahasiswa, mata_kuliah.id as id_matkul, mata_kuliah.nama as matkul, nilai.nilai" +
                " FROM nilai " +
                "JOIN mahasiswa ON nilai.nrp = mahasiswa.nrp " +
                "JOIN mata_kuliah on nilai.id_mata_kuliah = mata_kuliah.id";

        Statement statement = db.createStatement();

        ResultSet result = statement.executeQuery(query);

        ArrayList<Nilai> listNilai = new ArrayList<>();
        while (result.next()){
            int id_nilai = result.getInt("id_nilai");
            String nrp = result.getString("nrp");
            String nama = result.getString("nama_mahasiswa");
            int id_matkul = result.getInt("id_matkul");
            String matkul = result.getString("matkul");
            int nilai = result.getInt("nilai");
            listNilai.add(new Nilai(id_nilai, nrp, nama, id_matkul, matkul, nilai));
        }

        return listNilai;
    }

    @Override
    public boolean save(Nilai nilai) throws SQLException {
        String query = "INSERT INTO nilai(id, nrp, id_mata_kuliah, nilai) VALUES (NULL, ?, ?, ?)";

        PreparedStatement statement = db.prepareStatement(query);

        statement.setString(1, nilai.getNrp());
        statement.setInt(2, nilai.getIdMatkul());
        statement.setInt(3, nilai.getNilai());

        return statement.execute();
    }

    @Override
    public boolean update(Nilai nilai) {
        return false;
    }

    @Override
    public boolean delete(Nilai nilai) throws SQLException {
        String query = "DELETE FROM nilai WHERE id = " + nilai.getId();

        Statement statement = db.createStatement();

        return statement.execute(query);
    }
}
