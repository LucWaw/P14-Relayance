# language: fr

Fonctionnalité:  Ajouter un client
  En tant que commercial je veux ajouter un client pour pouvoir le contacter

  Scénario: Ajouter un client
    Étant donné un commercial utilise le crm
    Quand Le commercial appuie sur le bouton ajout client
    Et Le commercial ajoute "John Doe" comme nom
    Et Le commercial ajoute "truc@mail.com" comme email
    Et Le commercial appuie sur le bouton sauvegarder
    Alors Le nouveau client est dans la liste des clients