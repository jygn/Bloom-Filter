/**
 * @author Jessy Grondin (20119453)
 * @author Prénom Nom (Matricule)
 */
public class BloomFilter {

    private int numHashes;
    private BitSet bitSet;

    /**
     * Crée un filtre de Bloom basé sur la taille de l'ensemble de bits et du
     * nombre de fonctions de hachage.
     *
     * @param numBits taille de l'ensemble de bits
     * @param numHashes nombre de fonctions de hachage
     */
    public BloomFilter(int numBits, int numHashes) {
        // TODO À compléter
        bitSet = new BitSet(numBits);
        this.numHashes = numHashes;
    }

    /**
     * Crée un filtre de Bloom basé sur le nombre d'éléments attendus et de la
     * probabilité de faux positifs désirée.
     *
     * @param numElems nombre d'éléments à insérer
     * @param falsePosProb probabilité de faux positifs
     */
    public BloomFilter(int numElems, double falsePosProb) {
        // TODO À compléter
    }

    /**
     * Ajoute un élément au filtre de Bloom.
     *
     * @param key l'élément à insérer
     */
    public void add(byte[] key) {
        // TODO À compléter
        int bit_index;



    }

    /**
     * Cherche pour l'élément dans le filtre de Bloom.
     *
     * @param key l'élément à trouver
     * @return si l'élément est possiblement dans le filtre
     */
    public boolean contains(byte[] key) {
        return false; // TODO À compléter
    }

    /**
     * Remet à zéro le filtre de Bloom.
     */
    public void reset() {
        // TODO À compléter
    }

    /**
     * Retourne le nombre de bits du filtre de Bloom.
     *
     * @return nombre de bits
     */
    public int size() {
        return 0; // TODO À compléter
    }

    /**
     * Retourne le nombre d'éléments insérés dans le filtre de Bloom.
     *
     * @return nombre d'éléments insérés
     */
    public int count() {
        return 0; // TODO À compléter
    }

    /**
     * Retourne la probabilité actuelle de faux positifs.
     *
     * @return probabilité de faux positifs
     */
    public double fpp() {
        return 0.0; // TODO À compléter
    }


    /**
     * Hash function qui utilise l'encodage ASCII
     *
     * Source : M. A. Weiss, Data Structures and Algorithm Analysis in Java (Third
     *          Edition), 2012
     * @param key
     * @param fn
     * @return
     *
     */
    public int hash (byte[] key, int fn) {

        // TODO revoir.. ressemble bcp a la fonction du livre..
        int hash = fn * 127;

        for (int i = 0; i < key.length; i++) {
            hash = 37 * hash + key[i];
        }

        return hash % bitSet.getBset_len(); // ne depasse pas taille du tableau de bits
    }

    public static void main (String args[]) {

        BloomFilter bf = new BloomFilter(43, 2);
        byte[] key = {'a'};
        int h = bf.hash(key, 2);
    }
}
