# Rapport Application Bataille Navale en Java

##### [Lien du repo github](https://github.com/Ikanaa/BatailleNavale)

### Présentation 

Cette application est une implémentation du jeu classique "Bataille Navale" développée en Java avec JavaFX pour l'interface graphique. Le jeu permet à un joueur d'affronter l'ordinateur dans une partie de bataille navale traditionnelle.

L'application propose une interface graphique complète avec trois écrans principaux :

- Un menu d'accueil permettant de lancer une partie normale ou avec mode triche
- Un écran de placement des bateaux où le joueur peut positionner sa flotte
- L'écran de jeu principal pour le déroulement de la partie

Les joueurs disposent d'une flotte composée de 5 bateaux de tailles différentes :

- Porte-avion (5 cases)
- Croiseur (4 cases)
- Contre-torpilleur (3 cases)
- Sous-marin (3 cases)
- Torpilleur (2 cases)

Le jeu respecte les règles classiques de la bataille navale : les joueurs placent leurs bateaux sur une grille, puis à tour de rôle, tentent de découvrir et couler les bateaux adverses en ciblant des coordonnées sur la grille ennemie. Le premier à détruire entièrement la flotte adverse remporte la partie.

L'architecture du programme suit un modèle MVC (Modèle-Vue-Contrôleur) avec séparation claire entre la logique de jeu (classe Bataille), les données (classe Bateau) et l'interface utilisateur (les contrôleurs et fichiers FXML). Cette conception modulaire facilite la maintenance et l'évolution future du projet.

L'application intègre également un mode "triche" optionnel pour les joueurs souhaitant visualiser la position des bateaux de l'ordinateur à des fins d'apprentissage ou de test.

### Liste des classe de l'application

1. Main (ikana.Main) - Classe principale de l'application qui étend javafx.application.Application
2. Bataille (ikana.Bataille) - Logique principale du jeu avec gestion des grilles
3. Bateau (ikana.Bateau) - Représentation d'un bateau avec ses propriétés
4. ControllerMenu (ikana.ControllerMenu) - Contrôleur pour l'écran de menu
5. ControllerSelection (ikana.ControllerSelection) - Contrôleur pour l'écran de sélection des bateaux
6. ControllerJeu (ikana.ControllerJeu) - Contrôleur pour l'écran de jeu principal

### Ressource
#### Fichier FXML (JavaFX)

- menu.fxml - Interface du menu principal
- selection_bateau.fxml - Interface de placement des bateaux
- game.fxml - Interface de jeu

#### Images

- image/torpilleur.png
- image/contre-torpilleur.png
- image/sous-marin.png
- image/croiseur.png
- image/porte_avion.png
- image/battleship.png
- image/lapis.jpg (fond)

### Diagramme UML

```mermaid
classDiagram
    class Main {
        -Bataille bataille
        -boolean avecTriche
        -Stage planche
        -Scene sceneJeu
        -Scene sceneMenu
        -Scene sceneSelection
        -ControllerJeu controllerJeu
        -ControllerSelection controllerSelection
        -ControllerMenu controllerMenu
        +start(Stage stage)
        +main(String[] args)
        +selectionVersJeu()
        +versSelection()
        +recommencer()
        +terminerApplication()
    }
    
    class Bataille {
        +Random rand
        +int[][] grilleOrdi
        +int[][] grilleJeu
        +static final Map<Integer, Bateau> LISTEBATEAU
        +Bataille()
        +boolean initGrilleJoueur(int idBateau, int ligne, int colonne, int direction)
    }
    
    class Bateau {
        -String nom
        -int taille
        -int id
        +Bateau(String nom, int taille, int id)
    }
    
    class ControllerMenu {
        -Main referenceMain
        +setReferenceMain(Main _referenceMain)
        +jouer(ActionEvent event)
        +jouerAvecTriche(ActionEvent event)
        +quitter(ActionEvent event)
    }
    
    class ControllerSelection {
        -Bataille bataille
        -Main referenceMain
        -ImageView bateauEnDeplacement
        -GridPane grilleJoueur
        +setBataille(Bataille _bataille)
        +setReferenceMain(Main _referenceMain)
        +actionGrilleJoueur(ActionEvent event)
        +tournerBateau(ActionEvent event)
        +verifierFinDeSelection()
        +appliquerFond()
        +appliquerDecalageBateau(ImageView bateau)
        +getIdBateau(ImageView bateau)
        +demarrerPartie(ActionEvent event)
    }
    
    class ControllerJeu {
        -Bataille bataille
        -boolean triche
        -boolean finDeParti
        -Main referenceMain
        -GridPane grilleEnnemie
        -GridPane grilleJoueur
        +setBataille(Bataille _bataille)
        +setReferenceMain(Main _referenceMain)
        +actionGrilleEnnemi(ActionEvent event)
        +actionGrilleJoueur(ActionEvent event)
        +appliquerDecalageBateau(ImageView bateau)
        +placerBateau(ImageView bateauEnDeplacement, int ligne, [...])
        +placerBateau([...], int colonne, int direction, GridPane grille)        
        +setTriche(boolean triche)
        +toogleTriche(ActionEvent event)
        +quitter(ActionEvent event)
        +recommencer(ActionEvent event)
    }

    Main --> Bataille : crée
    Main --> ControllerJeu : référence
    Main --> ControllerSelection : référence
    Main --> ControllerMenu : référence
    
    ControllerJeu --> Bataille : référence
    ControllerJeu --> Main : référence
    
    ControllerSelection --> Bataille : référence
    ControllerSelection --> Main : référence
    
    ControllerMenu --> Main : référence
    
    Bataille --> Bateau : utilise
```

### Diagramme d'objet

```mermaid
classDiagram
    class main {
        bataille: Bataille
        avecTriche: false
        planche: Stage
        sceneJeu: Scene
        sceneMenu: Scene
        sceneSelection: Scene
        controllerJeu: ControllerJeu
        controllerSelection: ControllerSelection
        controllerMenu: ControllerMenu
    }
    
    class bataille {
        rand: Random
        grilleOrdi: int[10][10]
        grilleJeu: int[10][10]
        LISTEBATEAU: Map<Integer, Bateau>
    }
    
    class porteAvion {
        type: "porte-avion"
        taille: 5
        chiffreIdentifiant: 1
    }
    
    class croiseur {
        type: "croiseur"
        taille: 4
        chiffreIdentifiant: 2
    }
    
    class contreTorpilleur {
        type: "contre-torpilleur"
        taille: 3
        chiffreIdentifiant: 3
    }
    
    class sousMarin {
        type: "sous-marin"
        taille: 3
        chiffreIdentifiant: 4
    }
    
    class torpilleur {
        type: "torpilleur"
        taille: 2
        chiffreIdentifiant: 5
    }
    
    class controllerJeu {
        bataille: Bataille
        triche: false
        finDeParti: false
        referenceMain: Main
        grilleEnnemie: GridPane
        grilleJoueur: GridPane
    }
    
    class controllerSelection {
        bataille: Bataille
        referenceMain: Main
        bateauEnDeplacement: null
        grilleJoueur: GridPane
    }
    
    class controllerMenu {
        referenceMain: Main
    }

    main --> bataille
    main --> controllerJeu
    main --> controllerSelection
    main --> controllerMenu
    
    bataille --> porteAvion
    bataille --> croiseur
    bataille --> contreTorpilleur
    bataille --> sousMarin
    bataille --> torpilleur
    
    controllerJeu --> bataille
    controllerJeu --> main
    
    controllerSelection --> bataille
    controllerSelection --> main
    
    controllerMenu --> main
```

### Diagramme de cas d'utilisation

```mermaid
graph TD
    User((Joueur))
    
    subgraph "Application Bataille Navale"
        UC1[Lancer une partie normale]
        UC2[Lancer une partie avec triche]
        UC3[Positionner ses bateaux]
        UC4[Attaquer la grille ennemie]
        UC5[Voir les attaques de l'ordinateur]
        UC6[Activer/Désactiver le mode triche]
        UC7[Quitter le jeu]
        UC8[Recommencer une partie]
        UC9[Visualiser les bateaux ennemis]
        UC10[Voir l'état des flottes]
    end
    
    User --- UC1
    User --- UC2
    User --- UC3
    User --- UC4
    User --- UC5
    User --- UC6
    User --- UC7
    User --- UC8
    
    UC2 -.-> UC9
    UC6 -.-> UC9
    UC4 -.-> UC10
    UC5 -.-> UC10
    
    %% Notes
    classDef note fill:#ffffcc,stroke:#999
    
    N2[Démarre une partie où les<br>bateaux de l'ordinateur<br>sont visibles]
    class N2 note
    UC2 -.- N2
    
    N3[Le joueur peut faire pivoter<br>et placer ses 5 bateaux<br>sur la grille]
    class N3 note
    UC3 -.- N3
    
    N6[Disponible uniquement<br>pendant une partie]
    class N6 note
    UC6 -.- N6
```

### Test Fonctionnels

Pour pouvoir automatiser le processus de test fonctionnel on ajoute une classe TestFonctionnels.
Dans la classe main, on ajoute une condition qui vérifie l'argument de lancement ```test``` et qui, le cas échéant, exécute tous les tests sans lancer l'application.
Il faut noter que les tests d'intégration ne permettent pas de garantir le bon fonctionnement de l'interface graphique qui devrait être éprouvé par un humain.

Résultat : Tous les tests d'intégration passé ✅

### NB