# TP cybersécurité M1 S1

## Comment tester l'API ?
- `https://github.com/J-Dudek/tp1-cybersecu.git`
- `gh repo clone J-Dudek/tp1-cybersecu`
#### OU
- `docker run -it -p 8282:8282  julienm1/tp1-cybersecurite:latest`
#### Ensuite ?
Après avoir lancé l'application :
- Se rendre sur http://localhost:8282/swagger-ui.html , vous aurez alors accès aux différentes fonctionnalités de l'API.

## SUJET
> ---
>
>Projet Individuel
>Deadline : 10/02/21
>
>---
>
>Ecrivez un projet sur lequel vous avez carte blanche (carte blanche sur le langage et les fonctionnalités du projet).
>
>Pour que le projet soit reçevable, ses pré-requis sont :
>
>- Le projet doit pouvoir être lancé avec une commande docker (et donc doit avoir un Dockerfile).
>  - Si la commande Docker ne fonctionne pas, la note sera sévère parce que je vais pas débugger le code de 30 personnes.
>  - La commande doit apparaitre dans /README.md
>
>- Le projet doit faire apparaitre un dossier /tests qui contiendra les tests unitaires qui permettraient de trouver les vulnérabilités dans votre code (vous avez carte blanche sur votre framework de test)
>  - Par exemple : Un test d'injection SQL sur un paramètre GET
>
>- Le projet doit faire un apparaitre un dossier /exploit qui contiendra les scripts qui permettront d'exploiter les vulnérabilités (vous avez carte blanche sur le langage du script, il faut que le script soit concis)
>
>- Le projet doit être un dépôt github publique, pour que je puisse y jeter des coups d'oeil et pour les corrections
>
>- Le code doit être lisible et donc correctement commenté pour quelqu'un qui n'est pas dev (genre moi) mais qui sait lire de code (pas de "i++; // on incrémente i"). Si le code n'est pas propre et me fait perdre du temps quand je le lis, il y aura des malus.
>
>- Le fichier README.md doit faire apparaitre les objectifs de sécurité de l'application :
>   - Ses objectif en Confidentialité, Intégrité et Disponibilité (note /5)
>
>- Le fichier README.md doit faire apparaitre un graph mermaidjs avec la surface d'attaque
>
>- Un fichier Excel avec la même analyse de sécurité que le premier TP doit aussi apparaitre.
>
>---
>
>Ce TP est le projet du cours et donnera votre note de ce cours !
>---
