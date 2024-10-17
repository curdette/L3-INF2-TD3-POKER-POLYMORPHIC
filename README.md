# Diagramme des classes actuel

![Diagramme des classes](https://github.com/user-attachments/assets/f3841df6-3ff1-49b4-9ca6-16fa95f5cc2f)

# Poker Polymorphique

Nous devons maintenant supporter toutes les combinaisons du poker:
- Paire (max 2 cartes identiques)
- Double Paire (2 Paires dans la main)
- Brelan (max 3 cartes identiques)
- Couleur (Toutes les cartes ont la même couleur)
- Suite (Toutes les cartes se suivent)
- Full (1 brelan et une paire dans la main)
- Carré (4 cartes identiques)
- Suite colorée (une suite et une couleur en même temps)

## Contexte
L’objectif de cet exercice est d’utiliser le polymorphisme pour modéliser les différentes combinaisons de mains de poker. Vous allez implémenter une hiérarchie de classes pour représenter chaque combinaison (Paire, Brelan, Suite, etc.), et utiliser le polymorphisme pour comparer les mains des joueurs de manière dynamique.

## Consignes

1. **Créer une classe abstraite `MainPoker`** :
   - Attribut : `cards` (liste de 5 objets `Card`).
   - Méthodes :
     - `MainPoker(Card[] cards)` : initialise les cartes.
     - `abstract int getForce()` : retourne la force de la combinaison. Chaque sous-classe implémentera cette méthode pour calculer sa propre force.
     - `abstract String getName()` : retourne le nom de la combinaison (Paire, Brelan, etc.).

2. **Créer des sous-classes pour chaque combinaison** :
   - Créez les classes `Paire`, `DoublePaire`, `Brelan`, `Suite`, `Couleur`, `Full`, `Carre`, et `QuinteFlush`.
   - Chaque sous-classe hérite de `MainPoker` et implémente les méthodes `getForce()` et `getName()` :
     - **Exemple pour `Paire`** :
       ```java
       public class Paire extends MainPoker {
           public Paire(Card[] cards) {
               super(cards);
           }

           @Override
           public int getForce() {
               return 2; // La Paire a une force de 2
           }

           @Override
           public String getName() {
               return "Paire";
           }
       }
       ```

3. **Modifier la classe `HandResult` pour détecter les combinaisons** :
   - Ajoutez une méthode `MainPoker determinerMain(Card[] cards)` qui analyse les cartes et retourne un objet de type `MainPoker`.
   - Exemple de logique :
     ```java
     public static MainPoker determinerMain(Card[] cards) {
         if (/* condition pour une Paire */) {
             return new Paire(cards);
         } else if (/* condition pour un Brelan */) {
             return new Brelan(cards);
         }
         // Ajoutez les autres conditions pour les autres combinaisons
     }
     ```

4. **Utiliser le polymorphisme pour comparer les mains dans la classe `Player`** :
   - Modifiez la méthode `beats` pour qu’elle utilise `MainPoker` de manière polymorphique. Plutôt que de traiter chaque combinaison séparément, comparez les objets en fonction de leur force :
     ```java
     public boolean beats(Player other) {
         MainPoker myHand = HandResult.determinerMain(this.cards);
         MainPoker otherHand = HandResult.determinerMain(other.cards);
         
         if (myHand.getForce() > otherHand.getForce()) {
             return true;
         } else if (myHand.getForce() == otherHand.getForce()) {
             // Si les combinaisons sont les mêmes, on compare les cartes
             return this.compareHighCards(other);
         }
         return false;
     }
     ```

5. **Créer un classement dynamique des mains** :
   - Utilisez le polymorphisme pour créer une liste des joueurs et leurs combinaisons triées par force.
   - Affichez le gagnant et sa combinaison, puis affichez les autres joueurs en ordre décroissant de force.

## Objectifs pédagogiques
- **Utilisation du polymorphisme** : La méthode `determinerMain()` retourne un objet de type `MainPoker`, mais l’objet retourné peut être de tout type de combinaison spécifique.
- **Modularité et extensibilité** : Ajouter une nouvelle combinaison (par exemple, une "Quinte Flush Royale") nécessite uniquement une nouvelle sous-classe et une mise à jour mineure dans `determinerMain()`.
- **Encapsulation des comportements** : Chaque type de combinaison de poker encapsule ses propres comportements, permettant ainsi une gestion dynamique des combinaisons de manière polymorphique.

**Note** : Assurez-vous de tester plusieurs mains pour vérifier que le système de comparaison fonctionne correctement et que le polymorphisme est bien utilisé pour gérer chaque type de main de manière appropriée.
