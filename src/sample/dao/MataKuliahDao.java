package sample.dao;

import sample.classes.Mahasiswa;
import sample.classes.MataKuliah;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MataKuliahDao implements DAO<MataKuliah> {
    @Override
    public ArrayList<MataKuliah> getAll() throws SQLException {
        String query = "SELECT * FROM mata_kuliah";

        Statement statement = db.createStatement();

        ResultSet result = statement.executeQuery(query);

        ArrayList<MataKuliah> listMataKuliah = new ArrayList<>();
        while (result.next()){
            int id = result.getInt("id");
            String nama = result.getString("nama");
            listMataKuliah.add(new MataKuliah(id, nama));
        }

        return  listMataKuliah;
    }

    @Override
    public boolean save(MataKuliah mataKuliah) throws SQLException {
        return false;
    }

    @Override
    public boolean update(MataKuliah mataKuliah) {
        return false;
    }

    @Override
    public boolean delete(MataKuliah mataKuliah) throws SQLException {
        return false;
    }
}
