/**
 * Enumeration class Letters.
 *
 * Letters is based on the standard 26-letter English Alphabet.  Each letter represents a
 * character that may be played on the scrabblescrabble board, and is associated with a
 * numeric score and initial quantity.
 *
 * @author James Grieder
 * @version 1.0
 */
public enum Letters {
   A(1, 9), B(3, 2), C(3, 2), D(2, 4),
   E(1, 12), F(4, 2), G(2, 3), H(4, 2),
   I(1, 9), J(8, 1), K(5, 1), L(1, 4),
   M(3, 2), N(1, 6), O(1, 8), P(3, 2),
   Q(10, 1), R(1, 6), S(1, 4), T(1, 6),
   U(1, 4), V(4, 2), W(4, 2), X(8, 1),
   Y(4, 2), Z(10, 1);


   private int letterScore; // The score associated with a letter
   private int defaultQuantity; // The quantity of each letter at the start of the game


   /**
    * Initializes all letters and their associated letterScores and default quantities.
    *
    * @param letterScore the score of this letter.
    * @param quantity the quantity of this letter.
    */
   Letters(int letterScore, int quantity)
   {
      this.letterScore = letterScore;
      this.defaultQuantity = quantity;
   }
   
   
   /**
    * Gets the letterScore of this letter.
    * 
    * @return the letterScore of a letter.
    */
   public int getLetterScore() {
      return this.letterScore;
   }


   /**
    * Gets the default quantity of this letter.
    *
    * @return the default quantity of a letter.
    */
   public int getQuantity(){return this.defaultQuantity;}
}
