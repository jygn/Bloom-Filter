/**
 * @author Jessy Grondin (20119453)
 * @author Prénom Nom (Matricule)
 *
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

        if (key.length == 0)    // null
            return;

        int bit_index;

        for (int i = 0; i < numHashes; i++) { // active les filtres
            bit_index = hash(key, i); // hash 0, 1, ... n
            bitSet.set(bit_index);
        }
    }

    /**
     * Cherche pour l'élément dans le filtre de Bloom.
     *
     * @param key l'élément à trouver
     * @return si l'élément est possiblement dans le filtre
     */
    public boolean contains(byte[] key) {

        if (numHashes < 1)
            return false;

        int bit_index;

        for (int i = 0; i < numHashes; i++) {
            bit_index = hash(key, i); // hash 0, 1, ... n
            if (!bitSet.get(bit_index))
                return false;
        }

        return true;
    }

    /**
     * Remet à zéro le filtre de Bloom.
     */
    public void reset() {
        for (int i = 0; i < bitSet.getBset_len(); i++) {
            if (bitSet.get(i))  // si 1 on toggle le bit
                bitSet.clear(i);
        }
    }

    /**
     * Retourne le nombre de bits du filtre de Bloom.
     *
     * @return nombre de bits
     */
    public int size() { // TODO bien la taille du tableau de bits??
        return bitSet.getBset_len();
    }

    /**
     * Retourne le nombre d'éléments insérés dans le filtre de Bloom.
     *
     * @return nombre d'éléments insérés
     */
    public int count() { // TODO bien la nb de '1'?

        int cpt = 0;
        for (int i = 0; i < bitSet.getBset_len(); i++) {
            if (bitSet.get(i))
                cpt++;
        }

        return cpt;
    }

    /**
     * Retourne la probabilité actuelle de faux positifs.
     *
     * @return probabilité de faux positifs
     */
    public double fpp() {
        double p = Math.exp((double)(-numHashes * count()) / size());   // p = e^(-kn/m)
        return Math.pow((1 - p), numHashes);    // (1 - p)^k
    }


    /**
     * Hash function qui utilise l'encodage ASCII
     *
     * Source : M. A. Weiss, Data Structures and Algorithm Analysis in Java (Third
     *          Edition), 2012
     *
     */
    public int hash (byte[] key, int fn) {

        // TODO revoir...

        int hash = fn * 127;

        int f = fn * 17;
        if (!(f % 2 == 0)) // impair?
            f++;

        for (byte b : key) {
            hash = (37+f) * hash + b;
        }

        return hash % bitSet.getBset_len(); // ne depasse pas taille du tableau de bits
    }

    public static void main (String args[]) {

        BloomFilter bf = new BloomFilter(43, 3);

        byte[] key1 = {};
        byte[] key2 = {'a','d','e','d'};

        bf.add(key1);
        bf.add(key2);

        System.out.println(bf.contains(key1));
        System.out.println(bf.contains(key2));
        System.out.println("Prob faux positif = "+ bf.fpp());

        bf.reset();
    }
}
