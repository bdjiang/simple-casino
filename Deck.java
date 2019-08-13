//Brandon Jiang
//To create a deck class managing the deck object
//No bugs

public class Deck {
  //This begins at -1 because in the drawNextCard, the index is added on before returning the card
  int deckIndex = -1;
  //While this begins at 0 because nothing is returned in the dicard method
  int discardIndex = 0;

  //These are initialized outside the methods because they are used in multiple methods
  Card[] deck = new Card[52];
  Card[] discard = new Card[52];

  public Deck() {
    //I go 0 to 51, with each set of 13 representing a different suit
    for(int i = 1; i <= 13; i++) {
      Card h = new Card(i, "Hearts");
      deck[i-1] = h;
    }
    for(int i = 14; i <= 26; i++) {
      Card d = new Card(i-13, "Diamonds");
      deck[i-1] = d;
    }
    for(int i = 27; i <= 39; i++) {
      Card c = new Card(i-26, "Clubs");
      deck[i-1] = c;
    }
    for(int i = 40; i <= 51; i++) {
      Card s = new Card(i-39, "Spades");
      deck[i-1] = s;
    }
    shuffle();
  }
  //This shuffle method is Fisher-Yates
  public void shuffle() {
    for(int i = 51; i > 0; i--) {
      int j = (int)(Math.random()*(i+1));
      Card temp = deck[j];
      deck[j] = deck[i];
      deck[i] = temp;
    }
  }
  public Card drawNextCard() {
    if(deckIndex < 51) {
      deckIndex++;
      return deck[deckIndex];
    } else {
      //If the index has gone above 51, that means all cards are in the discard pile
      //So, return discard pile to the deck and shuffle, then reset the deckIndex
      deck = discard.clone();
      shuffle();
      deckIndex = 0;
      return deck[deckIndex];
    }

  }
  public void discard(Card c) {
    discard[discardIndex] = c;
    discardIndex++;
    //Will reset the position of the discardIndex if it has gone above 51
    if(discardIndex > 51) {
      discardIndex = 0;
    }
  }
}
