/**
 * @author Jessy Grondin (20119453)
 * @author Prénom Nom (Matricule)
 */
public class BitSet {

    private short[] bitSet;
    private static final int short_size = 16; // short = 2 bytes = 16 bits
    private int bSet_len;

    /**
     * Crée un ensemble de bits, d'une certaine taille. Ils sont initialisés à
     * {@code false}.
     *
     * @param nbits taille initiale de l'ensemble
     */
    public BitSet(int nbits) {

        if (nbits < 0)
            nbits = 0;

        bSet_len = nbits;

        int nbShort = getNbShort(nbits);
        bitSet = new short[nbShort];
        for (int i = 0; i < nbShort; i++)
            bitSet[i] = 0;
    }

    public int getBset_len() {
        return bSet_len;
    }

    /**
     * Retourne la valeur du bit à l'index spécifié.
     *
     * @param bitIndex l'index du bit
     * @return la valeur du bit à l'index spécifié
     *
     * Source: https://en.wikipedia.org/wiki/Mask_(computing)#Masking_bits_to_1
     */
    public boolean get(int bitIndex) {

        if (bitIndex > bSet_len || bitIndex < 0)
            return false;

        int dec_i = binToDec16(bitIndex);
        int byte_i = bitIndex / short_size;

        int b = bitSet[byte_i] & dec_i;  // ex: 00000100 AND 00000001 = 00000000
        return b == dec_i;
    }

    /**
     * Définit le bit à l'index spécifié comme {@code true}.
     *
     * @param bitIndex l'index du bit
     *
     * Source: https://en.wikipedia.org/wiki/Mask_(computing)#Masking_bits_to_1
     */
    public void set(int bitIndex) {

        if (bitIndex >=  bSet_len || bitIndex < 0)
            return;

        int dec_i = binToDec16(bitIndex);
        int byte_i = bitIndex / short_size;

        bitSet[byte_i] |= dec_i; // ex: 0000 0100 OR 0000 0001 = 00000101
    }

    /**
     * Définit le bit à l'index spécifié comme {@code false}.
     *
     * @param bitIndex l'index du bit
     */
    public void clear(int bitIndex) {       // TODO : tester cette fonction...
        if (bitIndex >=  bSet_len || bitIndex < 0)
            return;

        int dec_i = binToDec16(bitIndex);
        int byte_i = bitIndex / short_size;

        bitSet[byte_i] ^= dec_i; // ex: 0000 0100 XOR 0000 0100 = 00000000
    }


    /*
      ********************************** Utility functions ************************************
    */

    /**
     * Fonction qui calcul le nombre de shorts requis pour un nombre x de bits
     * @param nbits x
     * @return nombre de shorts
     */
    private int getNbShort (int nbits) {
        int nbShort = nbits / short_size;
        if (nbits % short_size > 0)
            nbShort++; // 1 short de plus pour le reste des bits

        return nbShort;
    }

    /**
     * Fonction qui convertit un index binaire en valeur decimale sur 16 bits
     * @param bin index binaire
     * @return valeur en decimal
     */
    private int binToDec16 (int bin) {
        return  (int)Math.pow(2, (bin % short_size)); // transforme l'index en valeur decimal sur 16 bits
    }


//    /**
//     * test
//     * TODO: a supprimer..
//     *
//     */
//    public static void main (String[] args) {
//
//        BitSet b = new BitSet(25);
//        b.set(8);
//        b.set(7);
//        /*
//         *      0000 0000
//         *      1000 0000 OR
//         *      1000 0000
//         *
//         *      1000 0000
//         *      0100 0000 OR
//         *      1100 0000
//         */
//
//        b.set(25);
//        b.set(0);
//        b.set(-1);
//        b.set(24);
//
//        boolean t = b.get(0);
//        System.out.println(t);
//
//        b.clear(0);
//        t = b.get(0);
//        System.out.println(t);
//
//    }
}
