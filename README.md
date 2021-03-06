# King Domino

![King Domino](./assets/img/readme.png)

## Comment jouer?

### Contrôles principaux
- 1 : Focus le 1er domino
- 2 : Focus le 2e domino
- 3 : Focus le 3e domino
- 4 : Focus le 4e domino
- r : Tourne le domino
- n : Skip le panneau
- Flèches : Se déplacer
- Space/Enter : Sélectionner ou insérer
- Esc/q : Quitter
- f : Plein écran
- d : Mode débogage

### Contrôles en mode débogage
- p : Affiche le tableau
- j : Affiche le menu
- k : Reconstruit la scène
- l : Affiche le jeu de carte
- t : Redémarre le jeu
- w : Affiche la valeur du plateau
- e : Affiche la scène finale
- Esc/q : Quitter

## Installation

### Sans docker

- Installer [openjdk 11](https://openjdk.java.net/install/)

```sh
./gradlew run # Lance le jeu
```

### Avec docker (et docker-compose) sur Mac et Linux
```sh
docker-compose up
```
## Règles

![Règles p1](./assets/img/règles-1.png)
![Règles p2](./assets/img/règles-2.png)
