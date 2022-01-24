# King Domino

![King Domino](./assets/img/readme.png)

## Comment jouer?

- 1 : Affiche le menu
- 2 : Affiche le tableau
- 3 : Affiche les cartes
- a : Change de tableau
- s : Mélange le plateau (shuffle)
- r : Redémarre le jeu
- f : Plein écran (fullscren)
- Esc/q : Quitter

## Installation

### Sans docker

- Installer [openjdk 11](https://openjdk.java.net/install/)

```sh
./gradlew run # Lance le jeu
```

### Avec docker sur Mac et Linux
```sh
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)" # Installe homebrew
brew install docker make # Installe make et docker
make docker # Lance le jeu dans un conteneur docker
```
