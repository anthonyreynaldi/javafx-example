package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import sample.classes.Mahasiswa;
import sample.classes.MataKuliah;
import sample.classes.Nilai;
import sample.dao.MahasiswaDao;
import sample.dao.MataKuliahDao;
import sample.dao.NilaiDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Controller {

    @FXML
    ComboBox<Mahasiswa> cbxMahasiswa;

    @FXML
    ComboBox<MataKuliah> cbxMatkul;

    @FXML
    TextField txtNilai;

    @FXML
    Button btnSimpan;

    @FXML
    TableView<Nilai> tableNilai;
    @FXML
    TableColumn<Nilai, String> colNrp;
    @FXML
    TableColumn<Nilai, String> colNama;
    @FXML
    TableColumn<Nilai, String> colMatkul;
    @FXML
    TableColumn<Nilai, String> colNilai;

    public Controller() throws SQLException {
    }

    @FXML
    void initialize() throws SQLException {
        //get data mahasiswa and set to combo box mahasiswa
        setCbxMahasiswa();

        //get data mata kuliah and set to combo box mata kuliah
        setCbxMataKuliah();

        //get data nilai and set to table nilai
        setTableNilai();

        //bind class Nilai property to TableColumn
        colNrp.setCellValueFactory(new PropertyValueFactory<Nilai, String>("nrp"));
        colNama.setCellValueFactory(new PropertyValueFactory<Nilai, String>("nama"));
        colMatkul.setCellValueFactory(new PropertyValueFactory<Nilai, String>("matkul"));
        colNilai.setCellValueFactory(new PropertyValueFactory<Nilai, String>("nilai"));
    }

    public void setCbxMahasiswa() throws SQLException {
        MahasiswaDao mahasiswaDao = new MahasiswaDao();

        //get all data
        ArrayList<Mahasiswa> listMahasiswa = mahasiswaDao.getAll();

        //convert to observablelist
        ObservableList<Mahasiswa> obListMahasiswa = FXCollections.observableArrayList();
        listMahasiswa.forEach((mahasiswa -> obListMahasiswa.add(mahasiswa)));

        //set to combo box
        cbxMahasiswa.setItems(obListMahasiswa);
    }

    public void setCbxMataKuliah() throws SQLException {
        MataKuliahDao mataKuliahDao = new MataKuliahDao();

        //get all data
        ArrayList<MataKuliah> listMatakuliah = mataKuliahDao.getAll();

        //convert to observablelist
        ObservableList<MataKuliah> obListMataKuliahh = FXCollections.observableArrayList();
        listMatakuliah.forEach((mahasiswa -> obListMataKuliahh.add(mahasiswa)));

        //set to combo box
        cbxMatkul.setItems(obListMataKuliahh);
    }

    public void setTableNilai() throws SQLException {
        NilaiDao nilaiDao = new NilaiDao();

        //get all data
        ArrayList<Nilai> listNilai = nilaiDao.getAll();

        //convert to observablelist
        ObservableList<Nilai> oblistNilai = FXCollections.observableArrayList();
        listNilai.forEach((mahasiswa -> oblistNilai.add(mahasiswa)));

        //set to combo box
        tableNilai.setItems(oblistNilai);
    }

    public void alert(String msg){
        Alert alert = new Alert(Alert.AlertType.WARNING, msg);
        alert.show();
    }

    public void success(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg);
        alert.show();
    }

    public void reset(){
        //reset combo box mahasiswa
        cbxMahasiswa.getSelectionModel().clearSelection();
        cbxMahasiswa.setButtonCell(new ComboBoxListCell("Pilih Mahasiswa"));

        //reset combo box mata kuliaih
        cbxMatkul.getSelectionModel().clearSelection();
        cbxMatkul.setButtonCell(new ComboBoxListCell("Pilih Mata Kuliah"));
        txtNilai.setText("");
    }

    @FXML
    public void simpan() throws SQLException {

        //check input completion
        if (cbxMahasiswa.getSelectionModel().isEmpty()){
            alert("Pilih Mahasiswa");
            return;
        }

        if (cbxMatkul.getSelectionModel().isEmpty()){
            alert("Pilih Mata Kuliah");
            return;
        }

        if (txtNilai.getText().isEmpty()){
            alert("Isi Nilai");
            return;
        }

        //get value input
        String nrp = cbxMahasiswa.getSelectionModel().getSelectedItem().getNrp();
        int id_matkul = cbxMatkul.getSelectionModel().getSelectedItem().getId();
        int nilai = Integer.parseInt(txtNilai.getText());

        NilaiDao nilaiDao = new NilaiDao();
        boolean result = nilaiDao.save(new Nilai(0, nrp, id_matkul, nilai));

        if (!result){
            success("Data Berhasil Diinput");
            setTableNilai();
            reset();
        }else {
            alert("Data Gagal Diinput");
        }
    }

    @FXML
    public void hapus(KeyEvent keyEvent) throws SQLException {

        if (keyEvent.getCode() != KeyCode.DELETE || tableNilai.getSelectionModel().isEmpty()){
            return;
        }

        Nilai nilai = tableNilai.getSelectionModel().getSelectedItem();

        NilaiDao nilaiDao = new NilaiDao();
        boolean result = nilaiDao.delete(new Nilai(nilai.getId(), nilai.getNrp(), nilai.getIdMatkul(), nilai.getNilai()));

        if (!result){
            success("Data Berhasil Dihapus");
            setTableNilai();
            reset();
        }else {
            alert("Data Gagal Dihapus");
        }
    }
}
