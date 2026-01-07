# GestionTODO

GestionTODO est une application Java basée sur Maven qui permet de gérer des tâches (TODOs). Elle inclut des fonctionnalités telles que l'ajout, la récupération, la mise à jour et la suppression de tâches.

## Fonctionnalités
- Ajouter une tâche
- Récupérer toutes les tâches
- Marquer une tâche comme complétée
- Supprimer une tâche

## Prérequis
- Java 17 ou supérieur
- Maven 3.6+
- Docker (optionnel, pour la containerisation)
- SonarQube (optionnel, pour l'analyse de la qualité du code)

## Installation
1. Clonez le dépôt :
   ```bash
   git clone https://github.com/votre-utilisateur/votre-repo.git
   cd GestionTODO
   ```
2. Compilez et empaquetez le projet :
   ```bash
   mvn clean package
   ```
3. Exécutez l'application :
   ```bash
   java -jar target/GestionTODO-1.0-SNAPSHOT.jar
   ```

## Tests
Pour exécuter les tests unitaires :
```bash
mvn test
```

## Analyse de la qualité du code
Pour exécuter une analyse SonarQube :
```bash
mvn sonar:sonar
```
Assurez-vous que SonarQube est configuré et en cours d'exécution.

## Dockerisation
1. Construisez l'image Docker :
   ```bash
   docker build -t gestiontodo:1.0 .
   ```
2. Lancez le conteneur :
   ```bash
   docker run --rm -p 8080:8080 gestiontodo:1.0
   ```

## Contribution
Les contributions sont les bienvenues ! Veuillez ouvrir une issue ou soumettre une pull request.

## Licence
Ce projet est sous licence MIT. Consultez le fichier [LICENSE](LICENSE) pour plus d'informations.

---

**Note :** Assurez-vous de ne pas inclure d'informations sensibles (comme des jetons ou des mots de passe) dans le dépôt public.
