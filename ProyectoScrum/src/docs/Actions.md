# UNO Game – Project Documentation

## 1. Project structure

The project should follow this structure:

```text
UNO/
│
└── src/
    ├── actions/
    │   └── Actions.java
    │
    ├── Main.java
    │
    └── Card.java
```

### File responsibilities

| File | Responsibility |
| --- | --- |
| `Main.java` | Initializes and manages the game |
| `Card.java` | Defines the Card object |
| `actions/Actions.java` | Contains methods for drawing and playing cards |

## 2. Creating the Card type

Before creating the game, we need a `Card` class.

Each card must contain:

- A color.
- A value or action type.

UNO has four standard colors:

- RED
- BLUE
- GREEN
- YELLOW

Special cards:

- SKIP
- REVERSE
- DRAW\_TWO
- WILD
- WILD\_DRAW\_FOUR

We can represent these using Java enums.

```java
public class Card {

    public enum Color {
        RED, BLUE, GREEN, YELLOW, WILD
    }

    public enum Value {
        ZERO, ONE, TWO, THREE, FOUR,
        FIVE, SIX, SEVEN, EIGHT, NINE,
        SKIP, REVERSE, DRAW_TWO,
        WILD, WILD_DRAW_FOUR
    }

    private Color color;
    private Value value;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}
```

### Creating a card

To create a red number 5 card:

```java
Card card = new Card(
    Card.Color.RED,
    Card.Value.FIVE
);
```

To create a Wild card:

```java
Card wild = new Card(
    Card.Color.WILD,
    Card.Value.WILD
);
```

## 3. Initializing the game

The game initialization takes place in `Main.java`.

The initialization process should:

1. Create the deck.
2. Shuffle the deck.
3. Create between 2 and 3 players.
4. Deal 7 cards to each player.
5. Create the discard pile.
6. Place the first card on the discard pile.
7. Start the game loop.

Example:

```java

import actions.Actions;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        // Create the deck
        ArrayList<Card> deck = new ArrayList<>();

        // Create the discard pile
        ArrayList<Card> discardPile = new ArrayList<>();

        // Create player hands
        ArrayList<Card> player1 = new ArrayList<>();
        ArrayList<Card> player2 = new ArrayList<>();

        // Example of creating a card
        Card exampleCard = new Card(
            Card.Color.RED,
            Card.Value.FIVE
        );

        deck.add(exampleCard);

        // Draw a card
        Card drawnCard = Actions.drawCard(
            deck,
            discardPile
        );

        if (drawnCard != null) {
            player1.add(drawnCard);
        }

        // Display player hand
        System.out.println("Player 1 hand:");

        for (Card card : player1) {
            System.out.println(card);
        }
    }
}
```

## 4. Using the Actions methods

All card operations are stored in:

```java
package actions;

public class Actions {
    // Methods go here
}
```

Import the class into `Main.java`:

```java
import actions.Actions;
```

### 4.1 Drawing a card

Method:

```java
Actions.drawCard(deck, discardPile);
```

Parameters:

| Parameter | Type | Description |
| --- | --- | --- |
| `deck` | `ArrayList<Card>` | Cards available to draw |
| `discardPile` | `ArrayList<Card>` | Previously played cards |

Example:

```java
Card drawnCard = Actions.drawCard(deck, discardPile);

if (drawnCard != null) {
    player1.add(drawnCard);
}
```

The method:

1. Checks whether the deck is empty.
2. Recycles the discard pile if necessary.
3. Generates a random index.
4. Removes the selected card from the deck.
5. Returns the drawn card.

### 4.2 Playing a card

Method:

```java
Actions.playCard(hand, discardPile, cardIndex, currentColor);
```

Parameters:

| Parameter | Type | Description |
| --- | --- | --- |
| `hand` | `ArrayList<Card>` | Current player's hand |
| `discardPile` | `ArrayList<Card>` | Discard pile |
| `cardIndex` | `int` | Index of the selected card |
| `currentColor` | `String` | Currently active color |

Example:

```java
boolean played = Actions.playCard(
    player1,
    discardPile,
    0,
    "RED"
);

if (played) {
    System.out.println("Card played successfully!");
}
```

The method:

1. Checks whether the selected index is valid.
2. Checks whether the card can be played.
3. Checks the Wild Draw Four restriction.
4. Removes the card from the player's hand.
5. Adds the card to the discard pile.
6. Returns `true` if successful or `false` otherwise.

## 5. Important implementation notes

Before developing the complete game, some improvements to the initial methods are necessary.

| Feature | Implementation |
| --- | --- |
| Random drawing | Use `Random.nextInt()` |
| Card validation | Compare enum values instead of Strings |
| Wild color selection | Ask the player to choose a new color |
| Wild Draw Four | Check for cards matching the current color |
| Draw Two | Make the next player draw two cards |
| Skip | Skip the next player's turn |
| Reverse | Change turn direction |
| UNO declaration | Check when a player has one card remaining |
| Scoring | Calculate points when someone wins a round |

Important design decision: Since `Card.Color` is an enum, I recommend changing `currentColor` from `String` to `Card.Color` in `Actions.playCard()`. This avoids inconsistencies such as `"Red"` versus `"RED"`.

Also, the current implementation of `drawCard()` uses a random index. In a traditional UNO deck, cards are drawn from the top of a shuffled deck. You can keep the random drawing approach if it is a project requirement, but it differs slightly from standard UNO gameplay.
