Feature: Opérations sur les vins

	Scenario: recherche de tous les vins quand base vide
		When j'envoie la requete en GET sur l'url "/vin"
		Then le code HTTP de la réponse est 200
		Then le contenu de la reponse doit etre strictement
			"""
			[]
			"""

	Scenario: insertion d'un vin puis récupération de tous les vins
		When j'envoie la requete en POST sur l'url "/vin" avec le contenu
			"""
			{
				"chateau": "toto",
				"appellation": "tutu",
				"prix": 25.1
			}
			"""
		Then le code HTTP de la réponse est 200
		When j'envoie la requete en GET sur l'url "/vin"
		Then le code HTTP de la réponse est 200
		Then le contenu de la reponse doit etre strictement
			"""
			[
				{
					"id": 1,
					"chateau": "toto",
					"appellation": "tutu",
					"prix": 25.1
				}
			]
			"""
