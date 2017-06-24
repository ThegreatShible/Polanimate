


package sample;

import java.io.*;
import java.util.*;

public class Personne implements Comparable<Personne> {
    private String nom;
    private String prenom;
    private String user;
    private String passwd;
    private String chemin;// le chemin vers le dossier qui contient ses annimation accessible avec le getter
    private String type="";// il sera rempli dans login et remis avec l'instance de type personne et accessible avec le getter
    private static String fichier="index.txt";
    private static HashMap<String,String> index;
    public final static String NomFichEns ="Enseignant";
    public final static String NomFichEtu ="Etudiant";

    public Personne(){
    }
    public Personne(String Nom,String Prenom,String user,String passwd){
        this.nom=Nom;
        this.prenom=Prenom;
        this.user=user;
        this.passwd=MD5(passwd);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    private ArrayList<Personne> Lire (String Nom_fich) // lecture du fichier en mettant les objets dans une liste
    {
        File f=new File (Nom_fich) ;
        ArrayList<Personne> Comptes = new ArrayList();
        Personne pers;
        if (!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
        try (FileInputStream fis = new FileInputStream(f)) {
            Scanner sc = new Scanner(fis);
            // Lecture des données du fichier
            while (sc.hasNext()){
                pers = new Personne(sc.next(),sc.next(),sc.next(),sc.next());
                pers.setChemin(sc.next());
                // insertion des données dans la liste Comptes
                Comptes.add(pers);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return Comptes;
    }

    private boolean recherche(int type,String Nom_fich) // type represente le type de recherche 0 pour login et 1 pour sign in
    {
        ArrayList<Personne> Comptes;
        int i=0;
        boolean trouv=false;
        Personne pers;
        Comptes=Lire(Nom_fich);
        switch (type) {
            case (0): { // cas de login
                while (i < Comptes.size() && !trouv) {
                    pers= Comptes.get(i);
                    // Verification du la concurdence entre le Username et le Passwd
                    if (getUser().equals(pers.getUser()) && MD5(getPasswd()).equals(pers.getPasswd())) {
                        trouv = true;
                        setNom(pers.getNom());
                        setPrenom(pers.getPrenom());
                        setChemin(pers.getChemin());
                    }
                    i++;
                }
            }
            break;
            case (1):  // cas de sign in
                trouv=(Personne.contientUser(getUser()));

                break;
        }
        return trouv;
    }

    private static boolean contientUser(String user)
    {
        lireIndex();
        return index.containsKey(user);
    }
    private static void inserIndex(String user, String type) // sert à rechercher si la donnée se trouve dans l'index et de l'inserer sinon
    {
        File f=new File (fichier) ;
        if( !contientUser(user))
        {
            if (!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
            try (PrintWriter print = new PrintWriter(new FileOutputStream(f, true))) {
                print.print(user+" ");
                print.println(type);
            } catch (IOException e) {
                e.printStackTrace();
            }
            index.put(user,type);
        }

    }

    private static  void lireIndex()// sert a lire le fichier d'index
    {
        File f=new File (fichier) ;
        index = new HashMap<>();
        if (!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
        try (FileInputStream fis = new FileInputStream(f)) {
            Scanner sc = new Scanner(fis);
            // Lecture des données du fichier
            while (sc.hasNext()){
                index.put(sc.next(),sc.next());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Personne signIn(String nom, String prenom, String user, String passwd,String type) throws CompteNonExistantException, CompteDejaExistantException {
        String Nom_fich="";
        Personne pers;
        switch (type)
        {
            case "ens" :  Nom_fich=NomFichEns;
                break;
            case "etud" :   Nom_fich=NomFichEtu;
                break;
            case "aucun" : throw new CompteNonExistantException();

        }
        pers = new Personne(nom,prenom,user,passwd);
        File f=new File (Nom_fich) ;
        // tester si le fichier existe et le creer dans le cas contraire
        if (!f.exists()) {try {f.createNewFile();} catch (IOException e) {e.printStackTrace();}}
        if (!pers.recherche(1,Nom_fich)) {
            inserIndex(pers.getUser(),type);
            try (PrintWriter print = new PrintWriter(new FileOutputStream(f, true))) {
                print.print(pers.getNom() + " ");
                print.print(pers.getPrenom() + " ");
                print.print(pers.getUser() + " ");
                print.print(pers.getPasswd() + " ");
                // creation du repertoire

                pers.setChemin("Anim\\"+Nom_fich+"\\"+pers.getUser()+ "_doc"); // le chemin vers le docier qui contient les annimations
                f = new File(pers.getChemin());
                boolean isCreated = f.mkdirs();// creation du docier qui contiendra les fichiers de sauvegarde
                // pour creer un  fichier dans le repertoire new File("rep./ficher1.txt")
                if (!isCreated) {
                    //System.out.println(" le repertoire n'est pas crée");
                }
                else print.println(pers.getChemin());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else throw new CompteDejaExistantException();
        return pers;
    }

    public static Personne logIn(String User, String Password) throws CompteNonExistantException {
        String type = "aucun";
        Personne pers=new Personne();
        String Nom_fich="";
        if (!contientUser(User)) throw new CompteNonExistantException();
        else {
            type = index.get(User);
            switch (type) {
                case "ens":
                    Nom_fich = NomFichEns;
                    break;
                case "etud":
                    Nom_fich = NomFichEtu;
                    break;
            }
            boolean exist = false;
            File f = new File(Nom_fich);
            // connection au fichier
            if (!f.exists()) {
                try {
                    f.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            pers.setUser(User);
            pers.setPasswd(Password);
            pers.setType(type);
            exist = pers.recherche(0, Nom_fich);
            if (exist) {
                //System.out.println("Connexion etablie");
            }
            else throw new CompteNonExistantException();
        }
        return pers;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public String getChemin() {
        return chemin;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPasswd(String passwd) {
        this.passwd = MD5(passwd);
    }



    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }

    @Override
    public int compareTo(Personne o) {
        return nom.compareTo(o.getNom());
    }
    public void lister () // donne la liste des etudiants des groupes du prof (selon combien il a de groupes)
    {
        ArrayList<Personne> liste = new ArrayList<>();
        liste=Lire(NomFichEtu);
        Collections.sort(liste);
        for (Personne p: liste  ) {
           // System.out.println(p.getNom()+" "+p.getPrenom()+" "+p.getChemin());// à changer avec un lien ou je ne sais quoi
        }
    }


    public void listerRepertoire(String rep) {  // sert à lire les fichiers se trouvant dans un repertoir donné et a les afficher
        // dans notre cas on l'utilisera pour acceder aux animations des eleves à travers
        File d = new File(rep);
        String[] listefichiers;
        int i;
        listefichiers = d.list();
        for (i = 0; i < listefichiers.length; i++) {
            System.out.println(listefichiers[i]);
        }
    }

}



