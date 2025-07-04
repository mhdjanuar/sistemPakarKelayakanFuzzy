/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.daoimpl;

import application.dao.HasilTukDao;
import application.models.HasilTukModel;
import application.models.RemarksSummaryModel;
import application.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HasilTukDaoImpl implements HasilTukDao {

    private Connection connection = null;
    private PreparedStatement pstmt = null;
    private ResultSet resultSet = null;
    private String query;

    public HasilTukDaoImpl() {
        connection = DatabaseUtil.getInstance().getConnection();
    }
    
    @Override
    public List<HasilTukModel> findAll() {
        List<HasilTukModel> list = new ArrayList<>();

        String query = "SELECT " +
                       "hasil_tuk.id AS hasil_id, " +
                       "hasil_tuk.id_tuk, " +
                       "hasil_tuk.nilai, " +
                       "hasil_tuk.remarks, " +
                       "tuk.nama_tuk AS nama_tuk " +
                       "FROM hasil_tuk " +
                       "INNER JOIN tuk ON hasil_tuk.id_tuk = tuk.id";

        System.out.println("Eksekusi query findAll: " + query);

        try {
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                HasilTukModel data = new HasilTukModel();
                data.setId(resultSet.getInt("hasil_id"));
                data.setIdTuk(resultSet.getInt("id_tuk"));
                data.setNilai(resultSet.getDouble("nilai"));
                data.setRemarks(resultSet.getString("remarks"));
                data.setNamaTuk(resultSet.getString("nama_tuk"));

                // Jika kamu ingin ambil nama TUK juga, pastikan HasilTukModel punya field 'namaTuk'
                // data.setNamaTuk(resultSet.getString("nama_tuk"));

                list.add(data);

                System.out.println("Data ditemukan: ID = " + data.getId() +
                                   ", TUK ID = " + data.getIdTuk() +
                                   ", Nilai = " + data.getNilai() +
                                   ", Remarks = " + data.getRemarks());
            }

            System.out.println("Total data hasil_tuk: " + list.size());

        } catch (SQLException e) {
            System.err.println("Error saat eksekusi query findAll():");
            e.printStackTrace();
        } finally {
            close(); // Pastikan method ini menutup resultSet, pstmt, dan connection dengan aman
        }

        return list;
    }


    @Override
    public int create(HasilTukModel hasil) {
        try {
            query = "INSERT INTO hasil_tuk (id_tuk, nilai, remarks) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, hasil.getIdTuk());
            pstmt.setDouble(2, hasil.getNilai());
            pstmt.setString(3, hasil.getRemarks());

            int result = pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close();
        }
    }

    @Override
    public int update(HasilTukModel hasil) {
        try {
            query = "UPDATE hasil_tuk SET id_tuk = ?, nilai = ?, remarks = ? WHERE id = ?";
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, hasil.getIdTuk());
            pstmt.setDouble(2, hasil.getNilai());
            pstmt.setString(3, hasil.getRemarks());
            pstmt.setInt(4, hasil.getId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            close();
        }
    }
    
    @Override
    public List<RemarksSummaryModel> getRemarksSummary() {
        List<RemarksSummaryModel> summaryList = new ArrayList<>();

        String query = 
            "SELECT " +
            "remarks, " +
            "COUNT(*) AS jumlah, " +
            "ROUND(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM hasil_tuk), 2) AS persentase " +
            "FROM hasil_tuk " +
            "GROUP BY remarks";

        System.out.println("Eksekusi query untuk mengambil summary remarks:");
        System.out.println(query); // log query

        try {
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                RemarksSummaryModel summary = new RemarksSummaryModel();
                summary.setRemarks(resultSet.getString("remarks"));
                summary.setJumlah(resultSet.getInt("jumlah"));
                summary.setPersentase(resultSet.getDouble("persentase"));

                System.out.println("Data Ditemukan: " + summary.getRemarks() +
                                   ", Jumlah: " + summary.getJumlah() +
                                   ", Persentase: " + summary.getPersentase());

                summaryList.add(summary);
            }

            System.out.println("Total hasil: " + summaryList.size());

        } catch (SQLException e) {
            System.err.println("Gagal mengeksekusi query getRemarksSummary:");
            e.printStackTrace();
        } finally {
            close();
        }

        return summaryList;
    }



    private void close() {
        try {
            if (pstmt != null) pstmt.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
