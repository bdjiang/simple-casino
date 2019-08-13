//Brandon Jiang
//gagdude@brandeis.edu
//11/29/16
//To create a casino where one can play simple war or blackjack
//No bugs

import java.util.*;

public class Casino {
  public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    Deck deck = new Deck();
    System.out.println("Welcome to the casino! Type b to play blackjack, or w to play simple war");
    String userAnswer = console.next();
    if(userAnswer.equals("w")) {
      simpleWar(console, deck);
    } else if(userAnswer.equals("b")) {
      blackjack(console, deck);
    } else {
      System.out.println("That was not an option");
    }
  }
  public static void simpleWar(Scanner console, Deck deck) {
    int money = 1000;
    System.out.println("Welcome to simple war! The object is to have a card with a higher value than the dealer.");
    System.out.println("You make a bet. Then, you and the computer draw a card.");
    System.out.println("If your card has a larger value than the computer’s card, then the you win, and get the value of the bet added to your total.");
    System.out.println("Otherwise, the computer wins, and you lose your bet. You begin with $1000");
    //This is the condition for the do-while loop
    int cont = 1;
    do {
      int bet;
      //Loop used to prevent user from betting more than they have
      do {
        System.out.print("What is your bet? $");
        bet = console.nextInt();
        if(bet > money) {
          System.out.println("You can't bet more than you have!");
        }
      } while(bet > money);
      Card user = deck.drawNextCard();
      Card computer = deck.drawNextCard();
      System.out.println("You drew the " + user.toString() + " and the computer drew the " + computer.toString() + "!");
      if(user.getValue() > computer.getValue()) {
        money = money + bet;
        System.out.println("You won the round! You now have $" + money);
        deck.discard(user);
        deck.discard(computer);
      } else if(computer.getValue() > user.getValue()) {
        money = money - bet;
        System.out.println("You lost the round! You now have $" + money);
        deck.discard(user);
        deck.discard(computer);
        if(money < 1) {
          System.out.println("You have lost all your money! Game over!");
          cont = 0;
          break;
        }
      } else {
        System.out.println("It's a draw! You currently have $" + money);
      }
      System.out.println("Enter 0 to quit, or 1 to continue playing");
      cont = console.nextInt();
    } while(cont == 1);
    if(money > 0) {
      System.out.println("You ended with $" + money);
    }
  }
  public static void blackjack(Scanner console, Deck deck) {
    int money = 1000;
    System.out.println("Welcome to blackjack! In this version, Aces will only count as 1.");
    System.out.println("You will make a bet before every round. You begin with $1000");
    //The condition for the do-while loop
    int cont = 1;
    int bet;
    do {
      //Loop used to prevent the user from betting more than they have
      do {
        System.out.print("What is your bet? $");
        bet = console.nextInt();
        if(bet > money) {
          System.out.println("You can't bet more than you have!");
        }
      } while(bet > money);
      int userValue = 0;
      Card user1 = deck.drawNextCard();
      Card user2 = deck.drawNextCard();
      System.out.println("You drew the " + user1.toString() + " and the " + user2.toString());
      int user1Value = user1.getValue();
      int user2Value = user2.getValue();
      //Sets any face cards to a value of 10
      if(user1.getValue() > 10) {
        user1Value = 10;
      }
      if(user2.getValue() > 10) {
        user2Value = 10;
      }
      userValue = user1Value + user2Value;
      System.out.println("That's a total of " + userValue + " points!");
      deck.discard(user1);
      deck.discard(user2);
      //Condition for the do-while loop of hitting or standing
      boolean endUser = false;
      do {
        System.out.println("Would you like to hit or stand? Type in your answer");
        if(console.next().equals("stand")) {
          endUser = true;
        } else {
          Card userNext = deck.drawNextCard();
          int userNextValue = userNext.getValue();
          //Sets any face cards to a value of 10
          if(userNext.getValue() > 10) {
            userNextValue = 10;
          }
          userValue = userValue + userNextValue;
          System.out.println("You drew the " + userNext.toString());
          System.out.println("You now have " + userValue + " points!");
          deck.discard(userNext);
          if(userValue > 21) {
            System.out.println("That's more than 21! You're finished!");
            //Sets value to 0 if the user has exceeded 21 points
            userValue = 0;
            endUser = true;
            deck.discard(userNext);
          }
        }
      } while(endUser == false);
      System.out.println("****************************");
      int compValue = 0;
      Card comp1 = deck.drawNextCard();
      Card comp2 = deck.drawNextCard();
      System.out.println("The computer drew the " + comp1.toString() + " and the " + comp2.toString());
      int comp1Value = comp1.getValue();
      int comp2Value = comp2.getValue();
      //Sets any face cards to a value of 10
      if(comp1.getValue() > 10) {
        comp1Value = 10;
      }
      if(comp2.getValue() > 10) {
        comp2Value = 10;
      }
      compValue = comp1Value + comp2Value;
      System.out.println("The computer's first two cards are valued at " + compValue + " points!");
      deck.discard(comp1);
      deck.discard(comp2);
      //Condition for the loop of hitting and standing for the computer
      boolean end = false;
      do {
        if(compValue < 17) {
          Card compNext = deck.drawNextCard();
          int compNextValue = compNext.getValue();
          //Sets any face cards to a value of 10
          if(compNext.getValue() > 10) {
            compNextValue = 10;
          }
          compValue = compValue + compNextValue;
          System.out.println("The computer drew the " + compNext.toString());
          System.out.println("The computer now has " + compValue + " points!");
          deck.discard(compNext);
          if(compValue > 21) {
            System.out.println("That's more than 21. It's finished!");
            //Resets value to 0 if the computer has exceeded 21 points
            compValue = 0;
            end = true;
          }
        } else {
          end = true;
        }
      } while(end == false);
      //This comparison shows why I reset the values if either player exceeded 21 points
      if(compValue == userValue) {
        System.out.println("It's a draw! You don't lose anything");
        //Rest is straightforward
      } else if (userValue > compValue) {
        money = money + bet;
        System.out.println("You win! You've gotten " + bet + " added to your money");
        System.out.println("You now have $" + money);
      } else {
        money = money - bet;
        System.out.println("You lost! You've lost $" + bet + " and now have $" + money);
        if(money < 1) {
          System.out.println("You have lost all your money! Game over!");
          cont = 0;
          //To prevent the dialog asking if they would like to play again
          break;
        }
      }
      System.out.println("Would you like to play again? Type 1 for yes, or 0 for no");
      cont = console.nextInt();
    } while(cont == 1);
    if(money > 0) {
      System.out.println("You ended with $" + money);
    }
  }
}
