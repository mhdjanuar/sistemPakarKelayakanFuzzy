/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package application.dao;

import application.models.TukModel;
import java.util.List;

/**
 *
 * @author mhdja
 */
public interface TukDao {
    List<TukModel> findAll();
    int create(TukModel tuk);
    int update(TukModel tuk);
    int delete(int id);
}
