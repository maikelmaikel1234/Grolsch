# Java Web Applicatie voor verzamelen gebruikersinformatie

## Project Omschrijving
In het kader van de DevPro cursus hebben we opgedane kennis verwerkt in deze Java web applicatie.
De applicatie gebruikt servlets in combinatie met javascript/AJAX om in een wizard gebruikersinformatie te verzamelen en die client- en server-side te valideren. 
In de laatste stap van de wizard worden alle ingevulde velden getoond.
In het project wordt gebruik gemaakt van AJAX voor het verzenden van de validatierequests naar de server en het tonen van de volgende stap of errormeldingen. 

## Architectuur
Het project is opgebouwd volgens het Model-View-Controller (MVC) patroon:
- Model: Java klassen die de gebruikersinformatie representeren.
- View: Java klassen die HTML genereren die de verschillende stappen weergeven in de browser.
- Controller: Servlets die de requests van de client afhandelen, validatie uitvoeren, de logica van de wizard beheren en communiceren met het model.

## Hoe werkt de applicatie?
- Meerdere servlets met aparte pagina's voor:
  - login-pagina 
  - voorkeuren-pagina
  - de wizard die gebruikersinformatie verzamelt.
- Het verzamelen van de gebruikersinformatie gebeurt volgens het SPA-principe, waarbij we AJAX gebruiken. 
- Validatie vindt zowel client- als server-side plaats. Clientside voor gebruikersgemak en serverside voor beveiliging.
- Om te voorkomen dat bij iedere stap in de wizard de pagina gerefreshed wordt, wordt gebruik gemaakt van AJAX. 
- De user wordt bijgehouden in de sessie. Indien de gebruiker niet is ingelogd, wordt de gebruiker geforward naar de login pagina. 

## Gebruikte technologieen
- Java 17
- Maven
- Servlets
- Jetty Server
- HTML/CSS
- JavaScript/AJAX
- JSON en GSON

## Applicatie starten
- voer het commando mvn jetty:run uit in de terminal
- open een browser en ga naar http://localhost:8080/ajaxHome of http://localhost:8080/login om in te loggen_

