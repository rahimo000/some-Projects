/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientetudiant;

import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author salah
 */
import serveretudaint.Etudiant;

public class ClientEtudiant {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String hostname = "localhost";

        Socket sock = null;
        DataOutputStream sockout = null;
        ObjectInputStream sockin = null;
        DataInputStream so = null;
        try {
            sock = new Socket(hostname, 7777);
            sockout = new DataOutputStream(sock.getOutputStream());
            so = new DataInputStream(sock.getInputStream());
            sockin = new ObjectInputStream(sock.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("*************************MENU*************************\n "
                + "Choix:\n"
                + "1 - Cherche à l'étudiant par nom\n"
                + "2 - Cherche à l'étudiant par moyenne\n"
                + "3 - trier\n"
                + "4 - ajouter un etudiant\n"
                + "5 - modifier un etudiant\n"
                + "6 - supprimer un etudiant\n");
        while (true) {

            Scanner a = new Scanner(System.in);
            int b = a.nextInt();
            switch (b) {
                case 1:

                    try {
                        sockout.writeInt(1);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    par_nom(sockin, sockout);
                    System.out.println("Autre operation");
                    break;
                case 2:
                    try {
                        sockout.writeInt(2);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    par_moy(sockin, sockout);
                    System.out.println("Autre operation");
                    break;
                case 3:
                    try {
                        sockout.writeInt(3);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    trier(sockin);
                    System.out.println("Autre operation");
                    break;

                case 4:
                    try {
                        sockout.writeInt(4);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    ajouter(sockout);
                    System.out.println("Autre operation");
                    break;

                case 5:
                    try {
                        sockout.writeInt(5);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    modifier(sockout, so);
                    System.out.println("Autre operation");
                    break;

                case 6:
                    try {
                        sockout.writeInt(6);
                    } catch (IOException ex) {
                        Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    supprimer(sockout);
                    System.out.println("Autre operation");
                    break;

            }

        }
    }

    private static boolean par_nom(ObjectInputStream sockin, DataOutputStream sockout) {

        System.out.println("Donnez nom d'etudiant");
        Scanner sc = new Scanner(System.in);
        String NomEtud = sc.nextLine();
        try {
            sockout.writeUTF(NomEtud);
        } catch (IOException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object recu = null;
        try {
            recu = sockin.readObject();
        } catch (IOException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }

        Etudiant etudiant = (Etudiant) recu;
        if (etudiant == null) {
            System.out.println("l'etudaint n'existe pas");
            return false;
        } else {
            System.out.println("server -> client: " + etudiant);
            return true;
        }

    }

    private static void par_moy(ObjectInputStream sockin, DataOutputStream sockout) {
        System.out.println("Donnez la moyene");
        Scanner sc = new Scanner(System.in);
        int NomEtud = sc.nextInt();
        try {
            sockout.writeInt(NomEtud);
        } catch (IOException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
        Object recu = null;
        Etudiant etudiant;
        int f = 0;
        while (f == 0) {
            try {
                recu = sockin.readObject();
                if (recu == null) {
                    break;
                }
            } catch (IOException ex) {
                Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            etudiant = (Etudiant) recu;
            System.out.println("server -> client: " + etudiant);

        }

    }

    private static void trier(ObjectInputStream sockin) {

        Object recu = null;
        Etudiant etudiant;
        int f = 0;
        while (f == 0) {
            try {
                recu = sockin.readObject();
                if (recu == null) {
                    break;
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
            }
            etudiant = (Etudiant) recu;
            System.out.println("server -> client: " + etudiant);

        }

    }

    private static void supprimer(DataOutputStream sockout) {
        System.out.println("Donnez nom d'etudiant");
        Scanner sc = new Scanner(System.in);
        String NomEtud = sc.nextLine();
        try {
            sockout.writeUTF(NomEtud);
        } catch (IOException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void ajouter(DataOutputStream sockout) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Donnez le nom de l'etudiant");
        String nom = sc.nextLine();
        System.out.println("Donnez le specialite de l'etudiant");
        String sp = sc.nextLine();
        System.out.println("Donnez la moyenne de l'etudiant");
        int moy = sc.nextInt();
        try {
            sockout.writeUTF(nom);
            sockout.writeUTF(sp);
            sockout.writeInt(moy);
        } catch (IOException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void modifier(DataOutputStream sockout, DataInputStream sockin) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Donnez le nom de l'etudaint");
        String no = sc.nextLine();
        String ans = null;
        try {
            sockout.writeUTF(no);

            if ("Done".equals(sockin.readUTF())) {
                System.out.println("Donnez autreu nom sinon garder");
                String nom = sc.nextLine();
                sockout.writeUTF(nom);
                System.out.println("Donnez autre specialité sinon garder");
                String sp = sc.nextLine();
                sockout.writeUTF(sp);
                System.out.println("Donnez autre moyenne sinon garder");
                int moy = sc.nextInt();
                sockout.writeInt(moy);
            } else {
                System.out.println("L'etudiant n'existe pas");
            }

        } catch (IOException ex) {
            Logger.getLogger(ClientEtudiant.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
