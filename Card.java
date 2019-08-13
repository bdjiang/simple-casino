//Brandon Jiang
//gagdude@brandeis.edu
//No bugs

//No comments because most of this is self-explanatory
public class Card {
  String suit;
  String color;
  int value;

  public Card(int value, String suit) {
    this.value = value;
    this.suit = suit;
    if(suit.equals("Hearts") || suit.equals("Diamonds")) {
      color = "red";
    } else {
      color = "black";
    }
  }

  public int getValue() {
    return value;
  }
  public String getColor() {
    return color;
  }
  public String getSuit() {
    return suit;
  }
  public String toString() {
    if(value > 1 && value < 11) {
      return value + " of " + suit;
    } else if(value == 1) {
      return "Ace of " + suit;
    } else if(value == 11) {
      return "Jack of " + suit;
    } else if(value == 12) {
      return "Queen of " + suit;
    } else {
      return "King of " + suit;
    }
  }
}
