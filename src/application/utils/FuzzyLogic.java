/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package application.utils;

import application.models.FuzzyValue;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mhdja
 */
public class FuzzyLogic {
    // Fungsi: membuat semua rules
    public static List<Rule> generateRules() {
        List<Rule> rules = new ArrayList<>();
        String[] levels = {"rendah", "cukup", "tinggi"};

        for (String kompetensi : levels) {
            for (String sarana : levels) {
                for (String dokumen : levels) {
                    for (String pengalaman : levels) {
                        int score = 0;

                        score += levelToScore(kompetensi);
                        score += levelToScore(sarana);
                        score += levelToScore(dokumen);
                        score += levelToScore(pengalaman);

                        String output;
                        if (score <= 3) {
                            output = "tidak_layak";
                        } else if (score <= 6) {
                            output = "dipertimbangkan";
                        } else {
                            output = "layak";
                        }

                        rules.add(new Rule(kompetensi, sarana, dokumen, pengalaman, output));
                    }
                }
            }
        }

        return rules;
    }

    private static int levelToScore(String level) {
        switch (level) {
            case "cukup":
                return 1;
            case "tinggi":
                return 2;
            case "rendah":
            default:
                return 0;
        }
    }

    // Fungsi: proses fuzzy inference dan defuzzifikasi
    public double applyRules(FuzzyValue fvKompetensi, FuzzyValue fvSarana, FuzzyValue fvDokumen, FuzzyValue fvPengalaman) {
        List<Rule> rules = generateRules();
        double totalAlphaZ = 0;
        double totalAlpha = 0;

        for (Rule rule : rules) {
            double muKompetensi = getMembership(rule.kompetensi, fvKompetensi);
            double muSarana = getMembership(rule.sarana, fvSarana);
            double muDokumen = getMembership(rule.dokumen, fvDokumen);
            double muPengalaman = getMembership(rule.pengalaman, fvPengalaman);

            double alpha = Math.min(Math.min(muKompetensi, muSarana), Math.min(muDokumen, muPengalaman));
            double z = outputValue(rule.output);

            totalAlpha += alpha;
            totalAlphaZ += alpha * z;

            System.out.printf("Rule: [%s, %s, %s, %s] => %s | α: %.2f, Z: %.2f\n",
                    rule.kompetensi, rule.sarana, rule.dokumen, rule.pengalaman, rule.output, alpha, z);
        }

        return totalAlpha == 0 ? 0 : totalAlphaZ / totalAlpha;
    }

    
    // Output nilai Z untuk setiap kesimpulan
    public double outputValue(String label) {
        switch (label.toLowerCase()) {
            case "tidak_layak": return 30;
            case "dipertimbangkan": return 60;
            case "layak": return 90;
            default: return 0;
        }
    }
    
    public String finalConclusion(double z) {
        if (z >= 75) return "Layak";
        else if (z >= 45) return "Dipertimbangkan";
        else return "Tidak Layak";
    }

    // Ambil nilai µ sesuai label
    public double getMembership(String label, FuzzyValue fv) {
        switch (label.toLowerCase()) {
            case "rendah": return fv.rendah;
            case "cukup": return fv.cukup;
            case "tinggi": return fv.tinggi;
            default: return 0;
        }
    }
    
    // Fuzzifikasi untuk 1 variabel input
    public FuzzyValue fuzzify(double value) {
        double rendah = segitigaTurun(0, 40, value);
        double cukup = segitiga(30, 50, 70, value);
        double tinggi = segitigaNaik(60, 100, value);
        return new FuzzyValue(rendah, cukup, tinggi);
    }

    // Fungsi keanggotaan segitiga simetris
    static double segitiga(double a, double b, double c, double x) {
        if (x <= a || x >= c) return 0;
        else if (x == b) return 1;
        else if (x < b) return (x - a) / (b - a);
        else return (c - x) / (c - b);
    }

    static double segitigaNaik(double a, double b, double x) {
        if (x <= a) return 0;
        else if (x >= b) return 1;
        else return (x - a) / (b - a);
    }

    static double segitigaTurun(double a, double b, double x) {
        if (x <= a) return 1;
        else if (x >= b) return 0;
        else return (b - x) / (b - a);
    }

    // Rule class
    static class Rule {
        String kompetensi;
        String sarana;
        String dokumen;
        String pengalaman;
        String output;

        Rule(String k, String s, String d, String p, String out) {
            this.kompetensi = k;
            this.sarana = s;
            this.dokumen = d;
            this.pengalaman = p;
            this.output = out;
        }
    }
}
