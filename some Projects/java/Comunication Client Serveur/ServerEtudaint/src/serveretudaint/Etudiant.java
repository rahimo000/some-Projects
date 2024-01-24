/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveretudaint;
import java.io.Serializable;
/**
 *
 * @author salah
 */
public class Etudiant implements Serializable {
            int ID;
            String nom;
            String prenom;
            String specialite;
            int moy;

            public Etudiant(int ID , String nom , String prenom , String specialite ,int moy ) {
                this.ID = ID;
                this.nom = nom;
                this.prenom = prenom;
                this.specialite = specialite;
                this.moy = moy;

            }

    Etudiant(String a, String b, int c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

            String getNom(){
                return nom;
            }

            public String getSpecialite() {
                return specialite;
            }

            public int getMoy() {
                return moy;
            }
            
            public int get() {
                return moy;
            }

            public void setNom(String nom) {
                this.nom = nom;
            }

            public void setSpecialite(String specialite) {
                this.specialite = specialite;
            }

            public void setMoy(int moy) {
                this.moy = moy;
            }
            
            
            
                
            @Override
            public String toString(){
                return "Etudiant: "+ID+" "+nom+" "+prenom+" "+specialite+" : "+moy;
            }
}