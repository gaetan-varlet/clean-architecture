# Principes de conception

Les principes SOLID ont pour but de guider l'organisation des fonctions et des données dans des classes, de même que le choix des relations entre ces classes.

Objectif :
- tolérance au changement
- facilité de compréhension
- composant fondamentaux pouvant être utilisés dans de nombreux systèmes


## Single Responsibility Principle (SRP)

- chaque module ne doit accepter qu'une seule raison d'évoluer

## Open-Closed Principle (OCP)

- un logiciel doit être conçu pour évoluer par ajout de nouveau code et non en modifiant le code existant

## Liskov Substitution Principle (LSP)

- pour qu'un logiciel puisse être construit à partir de parties interchangeables, ces parties doivent se conformer à un contrat permettant aux parties d'êtres substituées l'une à l'autre

## Interface Segregation Principle (ISP)

- les concepteurs de logiciels doivent éviter à tout prix de créer des dépendances par rapport à des éléments dont ils n'ont pas l'usage

## Dependency Inversion Principle (DIP)

- le code source qui incarne les règles de haut niveau ne doit pas dépendre du code qui implémente les règles à bas niveau. Les détauks doivent dépendre des règles générales
