# PROJ731-Compteur-de-mots

Réalisation d'un compteur de mots pour fichier txt. Le programme retourne une map avec tous les mots présents dans le texte et leur nombre d'occurence associé.
Projet réalisé en Java.

# Description du programme

- Premièrement le programme lit le fichier donné en entrée et compte le nombre de mots qu'il contient

- Ensuite, par un calcul logarithmique, il determine combien de threads à créer pour réaliser les maps

- Les threads sont donc créés et leurs mots associés leur sont transmis

- Quand toutes les maps sont créées on les récupères pour n'en faire qu'une seule, qui sera notre résultat 

- Enfin la map finale est écrite sur le fichier txt de sortie et celui-ci est envoyé à l'utilisateur.


# Utilisation du programme

Pour utiliser le programme, il faut lancer le serveur, puis dans le main, rentrer le nom du fichier txt désiré.
Le programme créé alors un fichier txt avec le résultat attendu.
