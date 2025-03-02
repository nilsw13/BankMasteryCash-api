# Projet API Spring avec Serveur Tomcat Embarqué Personnalisé

Ce projet a pour but de m'exercer à utiliser le framework Spring en profondeur, en particulier les mécanismes sous-jacents à Spring Boot. L'objectif est de comprendre comment fonctionne Spring sans l'abstraction élevée de Spring Boot, afin de ne pas accumuler de dette technique et de mieux maîtriser les concepts fondamentaux.

## Objectifs

- Comprendre les mécanismes de base de Spring (contexte d'application, injection de dépendances, etc.).
- Configurer manuellement un serveur Tomcat embarqué sans utiliser Spring Boot.
- Ajouter manuellement la chaîne de filtres Spring et configurer un `DispatcherServlet`.
- Explorer la configuration manuelle des listeners et des initialiseurs de servlets.

## Technologies Utilisées

- **Spring Framework** : Pour la gestion des dépendances, la configuration de l'application, et la gestion des requêtes HTTP.
- **Apache Tomcat** : Serveur embarqué personnalisé pour exécuter l'application.
- **Java** : Langage de programmation principal.
- **Spring JDBC** : Pour interagir directement avec la base de données sans utiliser d'ORM, afin de comprendre les mécanismes de base de l'accès aux données.

## Structure du Projet

- **AppLauncher** : Classe principale qui configure et démarre le serveur Tomcat embarqué.
- **MyWebAppInitializer** : Classe qui initialise la configuration Spring et ajoute la chaîne de filtres.
- **ApplicationConfiguration** : Classe de configuration Spring pour définir les beans et autres configurations.

## Configuration et Exécution

### Prérequis

- Java JDK 17.
- Maven pour la gestion des dépendances.

### Étapes pour Exécuter le Projet

1. **Cloner le dépôt** :
   ```bash
   git clone https://github.com/votre-utilisateur/votre-projet.git
   cd votre-projet
   ```

2. **Configurer la base de données** :

    Assurez-vous d'avoir une base de données configurée (par exemple, MySQL, PostgreSQL, etc.).

    Modifiez le fichier application.properties pour y ajouter les informations de connexion à votre base de données.

3. **Compiler et exécuter le projet**:
   ```bash
   mvn clean install
   mvn package
   cd target
   java -jar BankMasteryCash-api-1.0-SNAPSHOT.jar
   ```
4. **Accès à l'api en developpement**:
   http://localhost:8080


