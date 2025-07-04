/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.daoimpl;

import application.dao.TukDao;
import application.models.TukModel;
import application.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TukDaoImpl implements TukDao {
    private Connection dbConnection = null;
    private PreparedStatement pstmt = null;
    private ResultSet resultSet = null;
    private String query;

    public TukDaoImpl() {
        dbConnection = DatabaseUtil.getInstance().getConnection();
    }

    @Override
    public List<TukModel> findAll() {
        List<TukModel> list = new ArrayList<>();
        try {
            query = "SELECT * FROM tuk";
            pstmt = dbConnection.prepareStatement(query);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                TukModel tuk = new TukModel();
                tuk.setId(resultSet.getInt("id"));
                tuk.setNamaTuk(resultSet.getString("nama_tuk"));
                tuk.setInstansiPenyelenggara(resultSet.getString("instansi_penyelenggara"));
                tuk.setAlamat(resultSet.getString("alamat"));
                tuk.setNoTelepon(resultSet.getString("no_telepon"));
                tuk.setEmail(resultSet.getString("email"));
                list.add(tuk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeStatement();
        }
        return list;
    }

    @Override
    public int create(TukModel tuk) {
        try {
            query = "INSERT INTO tuk(nama_tuk, instansi_penyelenggara, alamat, no_telepon, email) VALUES (?, ?, ?, ?, ?)";
            pstmt = dbConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, tuk.getNamaTuk());
            pstmt.setString(2, "Instansi");
            pstmt.setString(3, tuk.getAlamat());
            pstmt.setString(4, "089123123123");
            pstmt.setString(5, "tuk@gmail.com");

            int result = pstmt.executeUpdate();
            resultSet = pstmt.getGeneratedKeys();
            if (resultSet.next()) return resultSet.getInt(1);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement();
        }
    }

    @Override
    public int update(TukModel tuk) {
        try {
            query = "UPDATE tuk SET nama_tuk = ?, instansi_penyelenggara = ?, alamat = ?, no_telepon = ?, email = ? WHERE id = ?";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setString(1, tuk.getNamaTuk());
              pstmt.setString(2, "Instansi");
            pstmt.setString(3, tuk.getAlamat());
            pstmt.setString(4, "089123123123");
            pstmt.setString(5, "tuk@gmail.com");
            pstmt.setInt(6, tuk.getId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement();
        }
    }

    @Override
    public int delete(int id) {
        try {
            query = "DELETE FROM tuk WHERE id = ?";
            pstmt = dbConnection.prepareStatement(query);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeStatement();
        }
    }

    private void closeStatement() {
        try {
            if (pstmt != null) pstmt.close();
            if (resultSet != null) resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

