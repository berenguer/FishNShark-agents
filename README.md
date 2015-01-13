# Système multi-agents

M. CHASTE
PP. BERENGUER

M2 IAGL

13-01-2015

Université de Lille 1

## Des requins qui mangent des poissons

Une grille regroupe des agents. Les poissons et requins sont des agents.
Sur une grille de taille n x n, **les agents sont placés aléatoirement**.
A chaque tour de la partie les agents peuvent, selon le délais fixé, donner naissance à un nouvel agent.
Un poisson peut se deplacer dans une case alentour si elle est vide.
Lorsque le moment est venu, un poisson accouche.

Les requins quand à eux meurt s'il n'ont plus de point de vie.
En se déplaçant sur une case contenant un poisson, le poisson est mangé il disparait, et le requin regagne ses points de vie.

## Exécution

Nécessite Java 1.7.

Lancer Eclipse -> File -> Import
Dans la partie "Générale" -> **"Existing Projects into Workspace"**

Ensuite il faut choisir la classe src.controller.Main comme classe principale.

### GUI

Lancer la partie de façon automatique en cliquant sur le bouton "Run".
Le bouton "Next" permet de faire seulement une seule itération du jeu.

## Configuration de la partie

Dans le controller.Main il faut créer un :
Envirronnement(taille de la grille, nombre de poisson, nombre de requins)

Dans Envirronnement il est possible de paramétrer le nombre de vie des poissons, ou le delais nécessaire pour accoucher d'un nouvel agent.
Lorsque l'Envirronnement est crée le constructeur fait appel à la méthode Envirronnement.initiateGrid().

Cette méthode **Envirronnement.initiateGrid()** construit aléatoirement la grille.

## Modification des paramètres des Agents
Dans Envirronnement.initiateGrid() modifier les valeurs de new Agent(..)
Exemple :
Fish(int posX, int posY, int birthClassDelay, int deathClassDelay, Environnement env)
Shark(int posX, int posY, int birthClassDelay, int deathClassDelay, Environnement env)

## Architecture

├── controller

│   └── Main.java

├── model

│   ├── agent

│   │   ├── Agent.java

│   │   ├── Fish.java

│   │   └── Shark.java

│   ├── Environnement.java

│   ├── NumberOfAgentsExceedSizeException.java

│   └── Observable.java

└── view

    ├── GridPanel.java
    
    ├── MainFrame.java
    
    └── Observer.java
    
## Implémentation

controller.Main construit un Envirronnment avec une taille et des agents à construire/placer.
Le patron MVC est utilisé. Model sera l'Envirronnement et les agents.
La vue sera MainFrame qui comporte un grille pour afficher le jeu (mer, poissons, requins).

Le **patern Observeur/Observable** est utilisé. La vue est abonnée à l'Envirronnement.
Envirronnement implémente Observable.
MainFrame implémente Observeur.
Lors d'un itération de la partie via Envirronnement.doIt() alors l'Envirronnement prévient ses abonnées, ici la vue.

## Statistiques

Dans le répertoire **stats/** sont présents des enregistrements de valeurs obtenues lors de l'exécution d'une partie.
Certains fichiers d'enregistrements disposent d'un graphe au nom éponyme.
