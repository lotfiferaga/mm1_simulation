# File d'attente M/M/1 

une file M/M/1 est un type de file d'attente classique.        
M/M/1 utilise la notation de Kendall, et signifie que les clients arrivent à un intervalle qui suit une loi exponentielle,      
sont mis en attente dans une file et sont traités dans l'ordre d'arrivée, selon une loi elle aussi exponentielle.   
![alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/6/65/Mm1_queue.svg/220px-Mm1_queue.svg.png)

# Modélisation 

Une file M/M/1 peut être vue comme un processus stochastique dont l'espace d'états est le nombre de clients dans le système.

Les clients arrivent selon une loi exponentielle de taux λ, changeant l'état du système de i à i + 1.
Le taux de service suit une loi exponentielle de paramètre μ.     
Un serveur traite les clients un à un selon le mode premier arrivé, premier servi.       
Quand le service est fini, les clients quittent le système et le processus passe de l'état i à l'état i - 1. 

La chaîne sous-jacente est la suivante :
![alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/MM1_queue_state_space.svg/605px-MM1_queue_state_space.svg.png)

# Stabilité et régime stationnaire
La file est stable si et seulement si le taux d'arrivée est strictement plus petit que le taux de départ

Le nombre de clients dans le système pendant le régime stationnaire suit une loi géométrique.
