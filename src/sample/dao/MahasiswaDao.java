package sample.dao;

import sample.classes.Mahasiswa;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MahasiswaDao implements DAO<Mahasiswa> {
    @Override
    public ArrayList<Mahasiswa> getAll() throws SQLException {
        String query = "SELECT * FROM mahasiswa";

        Statement statement = db.createStatement();

        ResultSet result = statement.executeQuery(query);

        ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();
        while (result.next()){
            String nrp = result.getString("nrp");
            String nama = result.getString("nama");
            listMahasiswa.add(new Mahasiswa(nrp, nama));
        }

        return  listMahasiswa;
    }

    @Override
    public boolean save(Mahasiswa mahasiswa) {
        return false;
    }

    @Override
    public boolean update(Mahasiswa mahasiswa) {
        return false;
    }

    @Override
    public boolean delete(Mahasiswa mahasiswa) {
        return false;
    }
}
