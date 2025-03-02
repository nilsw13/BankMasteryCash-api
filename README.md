# API Spring avec Serveur Tomcat Embarqué Personnalisé

## Aperçu

Ce projet est une API construite avec le framework Spring, mais **sans utiliser Spring Boot**. L'objectif principal est d'explorer et de comprendre les mécanismes sous-jacents que Spring Boot abstrait habituellement, afin de renforcer ma compréhension technique et éviter d'accumuler de la dette technique liée à une dépendance excessive aux abstractions de haut niveau.

## Motivation

Dans le monde du développement Java moderne, Spring Boot est devenu la norme pour créer rapidement des applications Spring. Cependant, cette commodité s'accompagne d'un coût : beaucoup de développeurs utilisent Spring Boot sans comprendre comment les choses fonctionnent "sous le capot". Ce projet est un exercice délibéré pour:

- Approfondir ma compréhension des mécanismes fondamentaux de Spring
- Apprendre à configurer manuellement un serveur web pour une application Spring
- Maîtriser le cycle de vie des composants Spring et leur initialisation
- Comprendre la chaîne de filtres et comment les requêtes sont traitées
- Gagner en autonomie technique par rapport aux abstractions de Spring Boot

## Caractéristiques techniques

- Configuration manuelle d'un serveur Tomcat embarqué
- Initialisation de l'ApplicationContext Spring sans l'autoconfiguration de Spring Boot
- Configuration manuelle de la chaîne de filtres Spring Security
- Utilisation de Spring JDBC pour l'accès aux données de bas niveau (sans les abstractions de Spring Data)
- Configuration basée sur les annotations Java plutôt que sur des fichiers XML
- Structure du projet épurée, mettant en évidence les composants essentiels

## Structure du code

Le point d'entrée de l'application se trouve dans la classe `AppLauncher`. Voici ce qu'elle fait:

```java
public class AppLauncher {
    public static void main(String args[]) throws LifecycleException {
        // Initialisation du serveur Tomcat embarqué
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        
        // Création du contexte de l'application
        Context tomcatCtx = tomcat.addContext("", null);
        
        // Ajout d'un écouteur pour l'initialisation de Spring
        tomcatCtx.addServletContainerInitializer(
                (set, servletContext) -> {
                    new MyWebAppInitializer().onStartup(servletContext);
                },
                null
        );
        
        // Création et configuration du contexte de l'application Spring
        WebApplicationContext appCtx = createApplicationContext(tomcatCtx.getServletContext());
        
        // Configuration de la servlet de dispatching Spring
        DispatcherServlet dispatcherServlet = new DispatcherServlet(appCtx);
        Wrapper servlet = tomcat.addServlet(tomcatCtx, "dispatcherServlet", dispatcherServlet);
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/*");
        
        // Démarrage du serveur
        tomcat.start();
    }
    
    // Méthode pour créer le contexte d'application Spring
    public static WebApplicationContext createApplicationContext(ServletContext servletContext){
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfiguration.class);
        ctx.setServletContext(servletContext);
        ctx.refresh();
        ctx.registerShutdownHook();
        return ctx;
    }
}
```
