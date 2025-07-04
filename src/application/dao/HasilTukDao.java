/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package application.dao;

import application.models.HasilTukModel;
import application.models.RemarksSummaryModel;
import java.util.List;

/**
 *
 * @author mhdja
 */
public interface HasilTukDao {
    List<HasilTukModel> findAll();
    int create(HasilTukModel hasil);
    int update(HasilTukModel hasil);
    List<RemarksSummaryModel> getRemarksSummary();
}
