# Paradigmes de programmation

- 1938 : Alan Turing établi les fondations de la programmation informatique, en écrivant en binaire
- fin des années 40, langage assembleur, qui libère le programmeur de l'obligation de convertir les instructions de ses programmes en suites de 0 et 1
- théorie de la programmation, manière de construire les programmes indépendamment du langage choisi. Il existe 3 paradigmes :
	- programmation structurée
	- programmation orientée objet
	- programmation fonctionnelle

## Programmation structurée

- elle impose une **discipline aux transferts directs du contrôle d'exécution**
- sous-ensemble de la programmation impérative (paradigme qui décrit les opérations en séquences d'instructions), célèbre pour avoir tenté d'éliminer l'instruction `goto`
- l’instruction `goto` est une instruction présente dans de nombreux langages de programmation, utilisée pour réaliser des sauts inconditionnels dans un programme, changeant ainsi le flot de contrôle naturel du programme qui consiste à aller exécuter l'instruction suivante. L’exécution est alors envoyée à une autre instruction repérée par une étiquette ou label
- remplacement par les structures de contrôle conditionnelles (`if/then/else`) et de répétition (`do/while/repeat`)
- les programmes sont complexes et difficiles à comprendre. Afin de les rendre plus compréhensible (isolément et sans connaître le contexte) et de prouver qu'ils fonctionnent correctement, il faut les décomposer récursivement en unités de plus en plus petites (des fonctions), ce que l'utilisation de `goto` empêche. On parle de **décomposition fonctionnelle**
- cela permet de mettre en place des tests pour essayer de prouver que chaque fonction vérifiable est incorrecte, à défaut on considère que les fonctions sont suffisamment correctes pour répondre aux objectifs qu'on leur a attribués

## Programmation orientée objet

- elle impose une **discipline aux transferts indirects du contrôle d'exécution**
- la POO consiste en la définition d'un objet, qui représente un concept, une idée ou toute entité du monde physique. Un objet possède des attributs et des méthodes. Elle se définie selon 3 concepts :
	- **l'encapsulation** : regroupement d'attributs avec un ensemble de méthodes pour permettre la lecture et la manipulation
	- **l'héritage** : mécanisme qui permet d'inclure dans une classe les caracéristiques d'une autre classe
	- **le polymorphisme** : concept consistant à fournir une interface unique à des entités pouvant avoir différents types. Cela permet de modifier le comportement d'une classe fille par rapport à sa mère, notamment le comportement des méthodes héritées en concervant la même signature
- faire de l'encapsulation, de l'héritage et du polymorphisme était déjà possible dans des langagues non OO, cependant le polymorphisme était périlleux à mettre en place, alors qu'il est simple à mettre en place dans les langages OO
- grâce au polymorphisme, l'**inversion des dépendances** est possible avec l'utilisation d'interface
- cela permet par exemple que la partie base de données dépendent des règles métier plutôt que l'inverse. Le code source du module des règles métier n'aura jamais à mentionner les détails du module de la partie base de données, ce qui permet par exemple d'avoir des modules indépendant d'un point de vue compilation et déploiement

## Programmation fonctionnelle

- elle impose une **discipline aux affectations de valeurs**
- **la valeur des variables ne change jamais**, afin de se prémunir des effets de bord des opérations d'affectation (conflit d'accès par exemple). On parle d'**immutabilité**
- au contraire, elle met en avant l'application de fonctions
- nécessite un espace mémoire et une vitesse de processeur importante
- les applications sont conformes non plus au modèle CRUD mais au seulement au modèle Créer et Lire
- c'est le fonctionnement des logiciels de gestion de contrôle de version de code source
- exemple d'une application bancaire où l'on enregistre chaque opération. Lorsqu'on veut récupérer le solde :
	- approche fonctionnelle : enchaînement des opérations depuis le début pour calculer le solde
	- approche classique : le solde est mis à jour après chaque opération, il est donc accessible sans calcul
	- approche intermédiaire, dite **sourçage d'événements** : enregistrement d'un état figé, par exemple toutes les nuits, et recalcul des mouvements successifs correspondants aux transactions depuis ce moment

## Conclusion

- chaque paradigme interdit certaines choses, en disant ce que l'on ne peut pas faire
- ces 3 paradigmes s'alignent avec les 3 principaux sujets que doit traiter l'architecture :
	- les fonctions (comportement)
	- la séparation des composants
	- la bonne gestion des données
