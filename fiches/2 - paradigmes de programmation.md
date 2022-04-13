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

- elle impose une discipline aux transferts indirects du contrôle d'exécution

## Programmation fonctionnelle

- elle impose une discipline aux affectations de valeurs

## Conclusion

- chaque paradigme interdit certaines choses, en disant ce que l'on ne peut pas faire
- ces 3 paradigmes s'alignent avec les 3 principaux sujets que doit traiter l'architecture :
	- les fonctions (comportement)
	- la séparation des composants
	- la bonne gestion des données
