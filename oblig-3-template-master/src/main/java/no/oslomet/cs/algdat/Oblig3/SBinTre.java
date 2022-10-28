package no.oslomet.cs.algdat.Oblig3;


import java.util.*;

public class SBinTre<T> {
    private static final class Node<T>   // en indre nodeklasse
    {
        private T verdi;                   // nodens verdi
        private Node<T> venstre, høyre;    // venstre og høyre barn
        private Node<T> forelder;          // forelder

        // konstruktør
        private Node(T verdi, Node<T> v, Node<T> h, Node<T> forelder) {
            this.verdi = verdi;
            venstre = v;
            høyre = h;
            this.forelder = forelder;
        }

        private Node(T verdi, Node<T> forelder)  // konstruktør
        {
            this(verdi, null, null, forelder);
        }

        @Override
        public String toString() {
            return "" + verdi;
        }

    } // class Node

    private Node<T> rot;                            // peker til rotnoden
    private int antall;                             // antall noder
    private int endringer;                          // antall endringer

    private final Comparator<? super T> comp;       // komparator

    public SBinTre(Comparator<? super T> c)    // konstruktør
    {
        rot = null;
        antall = 0;
        comp = c;
    }

    public boolean inneholder(T verdi) {
        if (verdi == null) return false;

        Node<T> p = rot;

        while (p != null) {
            int cmp = comp.compare(verdi, p.verdi);
            if (cmp < 0) p = p.venstre;
            else if (cmp > 0) p = p.høyre;
            else return true;
        }

        return false;
    }

    public int antall() {
        return antall;
    }

    public String toStringPostOrder() {
        if (tom()) return "[]";

        StringJoiner s = new StringJoiner(", ", "[", "]");

        Node<T> p = førstePostorden(rot); // går til den første i postorden
        while (p != null) {
            s.add(p.verdi.toString());
            p = nestePostorden(p);
        }

        return s.toString();
    }

    public boolean tom() {
        return antall == 0;
    }

    public boolean leggInn(T verdi) {

        Objects.requireNonNull(verdi, "Ulovlig med nullverdier!");

        Node<T> node = rot, par = null;                     // Node starter i rot, par = parent = forelder.
        int temp = 0;                                       // hjelpevariabel

        while (node != null)                                // fortsetter til noden er ute av treet
        {
            par = node;                                     // par er forelder til node
            temp = comp.compare(verdi,node.verdi);          // bruker komparatoren
            node = temp < 0 ? node.venstre : node.høyre;    // flytter noden
        }

        // noden er nå null, dermed ute av treet, par er den siste noden vi passerte,

        node = new Node<>(verdi, par);                      // oppretter en ny node

        if (par == null) rot = node;                        // Hvis par er null, så er treet tomt, og noden blir rot.
        else if (temp < 0) par.venstre = node;             // venstre barn til par
        else par.høyre = node;                              // høyre barn til par

        antall++;                                           // Oppdaterer antall med 1
        return true;                                        // vellykket innlegging
    }

    /*public boolean fjern(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }

    public int fjernAlle(T verdi) {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }*/

    public int antall(T verdi) {
        if (verdi == null) {                            //Hvis oppgitte verdi er null, så returner den automatisk 0, da ingenting er ingen ganger i treet.
            return 0;
        }
        if (tom()){                                     //Hvis treet er tomt, så returner den 0, da et tomt tre inneholder ingen verdier (duh)
            return 0;
        }
        int counter = 0;                                //Har en counter som skal telle antall ganger vi finner en verdi.

        Node<T> node = rot;                             //Hjelpenode som skal gå over treet

        while (node != null) {                          //Itererer over treet til vi når et leaf, også hopper av.
            int sml = comp.compare(verdi, node.verdi);  //sml = sammenligning = compare
            if (sml < 0) node = node.venstre;           //Hvis noden er større enn verdien vi leter etter, så går vi ned venstresiden
            else if (sml > 0) node = node.høyre;        //Hvis noden er mindre enn verdien vi leter etter, så går vi ned høyresiden
            else {                                      //Hvis sml == 0, så er verdi == node.verdi, vi har funnet verdien vi leter etter, og
                counter++;                              //vi oppdaterer counter, da vi har funnet en verdi
                node = node.høyre;                      //Vi går til høyre, da leggInn() legger inn duplikater til høyre (så neste vil ligge ned den ruten, om den finnes)
            }
        }
        return counter;                                 //returnerer counter som et integral.

    }

    /*public void nullstill() {
        throw new UnsupportedOperationException("Ikke kodet ennå!");
    }*/

    private static <T> Node<T> førstePostorden(Node<T> p) {
        while (p.venstre != null) {                                 //Postorden følger idéen om at venstre, høyre, node
            p = p.venstre;                                          //Så, vi ruller over og går så langt mot venstre som mulig i treet.
        }
        while (p.høyre != null) {
            p = p.høyre;
            while (p.venstre != null) {
                p = p.venstre;
            }
        }
        return p;
    }

    private static <T> Node<T> nestePostorden(Node<T> p) {
        if (p.forelder == null) {
            return null;
        }

        Node<T> sos = p.forelder.høyre;                         //sos = søsken = forelders andre barn

        if (sos == null || p == sos)                            //hvis p == sos, så er det et tegn på at den verdien vi var på var det høyre barnet.
            return p.forelder;
        while(sos.venstre != null) {                            //Deretter så er det likt som i førstePostorden, leter til vi kommer i bånn.
            sos = sos.venstre;
        }
        while(sos.høyre != null) {
            sos = sos.høyre;
        }

        return sos;
    }

    public void postorden(Oppgave<? super T> oppgave) {
        Node<T> p = førstePostorden(rot);

        while (p != null) {
            oppgave.utførOppgave(p.verdi);
            p = nestePostorden(p);
        }
    }

    public void postordenRecursive(Oppgave<? super T> oppgave) {
        postordenRecursive(rot, oppgave);
    }

    private void postordenRecursive(Node<T> p, Oppgave<? super T> oppgave) {
        if (p == null) {
            return;
        }
        postordenRecursive(p.venstre, oppgave);
        postordenRecursive(p.høyre, oppgave);
        oppgave.utførOppgave(p.verdi);
    }

    public ArrayList<T> serialize() {
        ArrayList<T> result = new ArrayList<>();
        ArrayDeque<Node<T>> queue = new ArrayDeque<>();

        queue.addLast(rot);

        while (!queue.isEmpty()) {
            Node<T> current = queue.removeFirst();

            if (current.venstre != null) {
                queue.addLast(current.venstre);
            }
            if (current.høyre != null) {
                queue.addLast(current.høyre);
            }
            result.add(current.verdi);
        }

        return result;
    }

    static <K> SBinTre<K> deserialize(ArrayList<K> data, Comparator<? super K> c) {
        SBinTre<K> tre = new SBinTre<>(c);

        for (K p : data) {
            tre.leggInn(p);
        }
        return tre;
    }

} // ObligSBinTre