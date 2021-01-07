# Filtre de bloom - Project d'école

- Un filtre de bloom est représenté par un bitset de n bits où l'ojectif est de vérifier si une clé fait partie du set ou non rapidement tout en optimisant la mémoire utilisée. 

- Le filtre de bloom a 2 paramètres : soit le nombre de bits n du set et le nombre de fonctions de hachage k.
(Les fonctions de hachage sont très importante pour éviter le nombre de collisions, mais le but du TP n'est pas de créer une fonction de hachage)

- Par exemple, si on ajoute une clé au bitset à l'aide de 3 fonctions de hachage, on doit hacher cette clé 3 fois et mettre le bit à 1 aux 3 index obtenus. 
Donc par la suite, pour vérifier si cette même clé est présente dans le bitset, il suffit de vérifier si les bits sont à 1 aux 3 index.

- Par contre, le filtre de bloom peut indiquer qu'une clé fait partie du set sans que ça soit le cas, il s'agit donc des faux positifs.
Ceux-ci surviennent lorsque d'autres clés ont certaines valeurs de hachage en commun avec une clé.

- Ainsi, le plus gros défi est de choisir k et m correctement pour diminuer la probabilité d'avoir des faux positifs. 
