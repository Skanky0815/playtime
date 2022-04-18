# Die Zugangsdaten, sowie die Rollen und Rechte der Benutzer werden in Keycloak gespeichert

* Status: accepted
* Deciders: Rico Schulz
* Date: 2021-12-13

## Context and Problem Statement

Die Benutzer von Playtime brauchen zugangsdaten um ihre Daten vor anderen zu schützten. Zudem sollen nicht alle Benutzer
alles in dem Portal machen können, dafür wird ein Rollen und Rechte System gebraucht.

## Considered Options

* Keycloak
* eine eigene Implementierung

## Decision Outcome

Gewählte Option: "Keycloak", gute integrierung in Spring Boot, via Docker leicht aufzusetzen.

### Positive Consequences

* Team kennt Keycloak bereits von der Arbeit in Projekten

### Negative Consequences

* Team kennt noch nicht alle möglichkeiten
* Team muss sich erst in die API einarbeiten

## Pros and Cons of the Options

### eine eigene Implementierung

* Gut, weil das Team die gesamte Kontroller über die Implementierung hat
* Schlecht, komplexe Domäne ferne arbeit