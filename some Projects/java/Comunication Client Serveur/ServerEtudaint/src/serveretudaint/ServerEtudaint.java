/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serveretudaint;

import java.io.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author salah
 */
public class ServerEtudaint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Etudiant[] tabEtuds = {new Etudiant(1,"MEFTAHI","Amine", "SEG", 10), 
            new Etudiant(2,"ZENATI","Bachir", "SEG", 12),
            new Etudiant(3,"MILOUDI","Carl", "SEG", 9),
            new Etudiant(4,"ZIANI","Karim", "SI", 13),
            new Etudiant(5,"GEROUDJI","Saleh", "SI", 8),
            new Etudiant(7,"ISLI","Hamid", "RID", 15),
            new Etudiant(8,"BELAILI","Mohamed", "RID", 12),
            new Etudiant(9,"BRAHIMI","Yassin", "RID", 7)};
        List<Etudiant> list = new LinkedList<>(Arrays.asList(tabEtuds));
        ServerSocket server = null;
        server = new ServerSocket(7777);
        Socket sock = server.accept();
        ObjectOutputStream sockout = new ObjectOutputStream(sock.getOutputStream());
        DataInputStream sockin = new DataInputStream(sock.getInputStream());
        DataOutputStream ds = new DataOutputStream(sock.getOutputStream());

        while (true) {

            System.out.println("nouveau op");
            int a = sockin.readInt();
            switch (a) {
                case 1:
                    System.out.println("op 1");
                    parnom(sockin, sockout, list);
                    break;
                case 2:
                    System.out.println("op 2");
                    parmoy(sockin, sockout, list);
                    break;
                case 3:
                    System.out.println("op 3");
                    trier(sockin, sockout, list);
                    break;
                case 4:
                    System.out.println("op 4");
                    ajouter(sockin, list);
                    break;
                case 5:
                    System.out.println("op 5");
                    modifier(sockin, ds, list);
                    break;
                case 6:
                    System.out.println("op 6");
                    supprimer(sockin, list);
                    break;
            }

            //   sockout.close(); 
        }

    }

    private static boolean parnom(DataInputStream sockin, ObjectOutputStream sockout, List<Etudiant> list) {
        String recu = null;
        try {
            recu = sockin.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
        }
        int d = 0;

        System.out.println("recu:" + recu);
        String nom = recu.trim();

        for (Etudiant tabEtud : list) {
            if (tabEtud.getNom().equals(nom)) {
                try {
                    sockout.writeObject(tabEtud);
                    d = 1;
                } catch (IOException ex) {
                    Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
                }
                return true;
            }

        }
        if (d == 0) {
            try {
                sockout.writeObject(null);

            } catch (IOException ex) {
                Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private static void parmoy(DataInputStream sockin, ObjectOutputStream sockout, List<Etudiant> list) {
        int recu = 0;
        try {
            recu = sockin.readInt();
            System.err.println("" + recu);
        } catch (IOException ex) {
            Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (Etudiant tabEtud : list) {

            if (tabEtud.getMoy() > recu) {
                try {
                    //  System.out.println(""+tabEtud);
                    sockout.writeObject(tabEtud);
                } catch (IOException ex) {
                    Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }
        try {
            sockout.writeObject(null);
        } catch (IOException ex) {
            Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void trier(DataInputStream sockin, ObjectOutputStream sockout, List<Etudiant> list) {

        Comparator<Etudiant> c = (Etudiant o1, Etudiant o2) -> {
            if (o1.getMoy() > o2.getMoy()) {
                return -1;
            } else if (o1.getMoy() < o2.getMoy()) {
                return 1;
            } else {
                return 0;
            }
        };
        // Arrays.sort(list, c);
        list.sort(c);

        for (Etudiant tabEtud : list) {

            try {
                System.out.println("" + tabEtud);
                sockout.writeObject(tabEtud);
            } catch (IOException ex) {
                Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        try {
            sockout.writeObject(null);
        } catch (IOException ex) {
            Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void supprimer(DataInputStream sockin, List<Etudiant> list) {
        String recu = null;
        try {
            recu = sockin.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("recu:" + recu);
        String nom = recu.trim();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNom().equals(nom)) {
                list.remove(i);
                break;
            }
        }

        for (Etudiant a : list) {
            System.out.println(a);
        }
    }

    private static void ajouter(DataInputStream sockin, List<Etudiant> list) {
        System.err.println("rrrrrrrrrrrrrrr");
        String a = null;
        String b = null;
        int c = 0;
        try {
            a = sockin.readUTF();
            b = sockin.readUTF();
            c = sockin.readInt();
        } catch (IOException ex) {
            Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
        }

        list.add(new Etudiant(a, b, c));
        for (Etudiant etudiant : list) {
            System.out.println(etudiant);
        }

    }

    private static void modifier(DataInputStream sockin, DataOutputStream sockout, List<Etudiant> list) {
        String nom = null;
        int d = 0;
        try {
            nom = sockin.readUTF();
            System.out.println("modifie " + nom);
        } catch (IOException ex) {
            Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Etudiant tabEtud : list) {
            if (tabEtud.getNom().equals(nom)) {
                try {
                   sockout.writeUTF("Done");
                   System.out.println("serveretudaint.ServerEtudaint.modifier()");
                    String n = sockin.readUTF();
                    System.out.println("nom done: " + n);
                    tabEtud.setNom(n);

                    String sp = sockin.readUTF();
                    tabEtud.setSpecialite(sp);
                    System.out.println("sp done:" + sp);
                    int moy = sockin.readInt();
                    tabEtud.setMoy(moy);
                    System.out.println("moy done: " + moy);
                    d = 1;
                    break;

                } catch (IOException ex) {
                    Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        if (d == 0) {
            try {
                sockout.writeUTF("l'eudiant n'existe pas");
            } catch (IOException ex) {
                Logger.getLogger(ServerEtudaint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
