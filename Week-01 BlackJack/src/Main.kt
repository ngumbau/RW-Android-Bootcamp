/*
Blackjack is a game played with regular playing cards. Playing cards consist of a ‘pip’, the numeric or face value of
the card, and a suit.

Initially, the player receives two cards in a hand.
The goal of the game is for the cards’ pip total to equal and not exceed 21.
Face cards are counted as 10, Aces as 11 and the other cards as their numeric pip value.

You will create a subset of this game that deals and evaluates the first two cards from a deck.

Create a program that
- creates a deck of cards,
- deals two cards from that deck into a hand and
- evaluates that hand of cards by finding the sum of the pips.
- Display the cards in the hand and
- the total of the pips in the hand.
*/
/**
 * A class that contains two members, a String for the pip and a Char for the suit.
 * @param   cardPip - represents the card pip
 * @param   cardSuit - represents the card suit.
 */
class Card(cardId: String, cardPip: Int, cardSuit: Char){
    val id: String = cardId
    val pip: Int = cardPip
    val suit: Char = cardSuit

    /**
     * Returns the pip value of a card
     * @return pip value of the card.
     */
    fun calculateCardValue(): Int{
        return pip
    }

    /**
     * Returns the string representation of the card
     * @return the string value of this card
     */
    fun printCard(): String{
        return "$pip $suit"
    }
}

fun main(){

    /**
     * Creates a deck containing a collectioon of suits and a collections of pips, A deck contains Aces of all suits (A),
     *  numeric cards of all suits (2..9), Face cards of all suits (J,Q,K). There are no jokers in this deck.
     *  @return a full card deck minus jokers.
     */
    fun createDeck(): MutableMap<String, Card>{

        val suits: Array<Char> = arrayOf('\u2663','\u2660','\u2666','\u2665')
        val cardCollection: MutableMap<String, Card> = mutableMapOf<String, Card>()

        // Ace = 11, Numeric Cards = 2 to 9, Face Cards = Jack, Queen, King = 10, No joker in Black Jack
        for (suit: Char in suits){
            // ACE - Putting Aces into the deck
            cardCollection.put("ACE-$suit", Card(cardId = "ACE-$suit"  , cardPip = 11, cardSuit = suit))
            // Numeric cards - Putting Numeric Cards into the deck
            for(numericCard in 2..9){
                cardCollection.put("$numericCard-$suit",Card(cardId = "$numericCard-$suit"  , cardPip = numericCard,
                    cardSuit = suit))
            }
            //Jack, Queen, King - Putting face cards into the deck
            cardCollection.put("JACK-$suit" , Card(cardId = "JACK-$suit"  , cardPip = 10, cardSuit = suit))
            cardCollection.put("Queen-$suit", Card(cardId = "QUEEN-$suit"  , cardPip = 10, cardSuit = suit))
            cardCollection.put("KING-$suit", Card(cardId = "KING-$suit"  , cardPip = 10, cardSuit = suit))
        }
        return cardCollection
    }

    /**
     * Deals a specified number of cards from a deck.
     * @param deck - deck of cards to remove a number of cards from.
     * @param numberhand - number of cards it should place in the hand (default 2 cards)
     * @return a collection containing the number of cards specified.
     */
    fun dealHand(deck: MutableMap<String,Card>,numberHand: Int = 2): MutableList<Card?> {
        val hand: MutableList<Card?> = mutableListOf()
        var randomCardID: String?
        deck.keys.random()

        for(index in 1..numberHand){
            randomCardID = deck.keys.random()
            hand.add(deck[randomCardID])
        }
        return hand
    }

    /**
     * Calculates the total value of a hand
     * @param hand - list of cards selected by the dealer.
     * @return total value of the hand
     */
    fun evaluateHand(hand: MutableList<Card?>): Int{
        var total: Int = 0
        for (card in hand){
            total += card?.pip ?: 0
        }
        return total
    }

    /**
     * Prints out the hand, cards and outcome in a readable way.
     * @param hand - cards dealt by dealer
     * @param total - value of the cards
     **/
    fun printResults(hand: MutableList<Card?>, total: Int){
        var message: String = "Your hand was:\n"
        for (card: Card? in hand) {
            message += "\t\t${card?.printCard()}\n"
        }
        message += "For a total of: $total\n"
        //    If the total happens to be 21, then it’s a lucky player, print the message ‘You Win!’ below the total.
        if (total == 21) {
            message += "You Win!"
        }
        if(hand[0]?.pip == 11 && hand[1]?.pip == 11){
            message += "You Lose!"
        }
        println(message)
    }

    /**
     *  Removes cards that have been dealt a deck
     *  @param hand - cards to be removed.
     *  @returns modified deck.
     */
    fun remove(hand: MutableList<Card?>, deck: MutableMap<String,Card>): MutableMap<String,Card>{
        for (card in hand){
            deck.remove(card?.id)
        }
        return deck
    }

    // create new deck and print - works
    val deck = createDeck()
    println(deck)


    // deals two cards from that deck into a hand
    val hand = dealHand(deck)
    println(hand)

    // remove hand from deck
    val modifiedDeck = remove(hand, deck)
    println(modifiedDeck)

    //evaluates that hand of cards by finding the sum of the pips.
    val total = evaluateHand(hand)
    println(total)

    //Display the cards in the hand and
    printResults(hand, total)

}