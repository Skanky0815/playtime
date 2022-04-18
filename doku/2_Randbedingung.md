# 2. Randbedingung

Beim Lösungsentwurf waren zu Beginn verschiedene Randbedingungen zu beachten, sie wirken in der Lösung fort. Dieser
Abschnitt stellt sie dar und erklärt auch – wo nötig – deren Motivation.

## 2.1 Technisch

| Randbedingung             | Erläuterungen, Hintergrund                                                           |
|---------------------------|--------------------------------------------------------------------------------------|
| Implementierung in Kotlin | Die Software wird in Kotlin implementiert                                            |
| Testabdeckung             | Die Testabdeckung mit UnitTest soll mindestens 90% sein                              |
| Architektur Tests         | Die Einhaltung der Architektur wir via [ArchUnit](https://www.archunit.org) getestet |

## 2.2 Organisatorisch

| Randbedingung                          | Erläuterungen, Hintergrund                                                                                                          |
|----------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| Team                                   | One-Man-Show :D                                                                                                                     |
| Zeitplan                               | Beginn der Entwicklung ist Dezember 2021 der erste Prototype soll Mitte 2022 lauffähig sein                                         |
| Vorgehensmodell                        | Die Entwicklung wird Iterativ erfolgen und inkrementell                                                                             |
| Entwicklungswerkzeuge                  | Entwickelt mit in IntelliJ, die Architektur wird via Markdown und DrawIo dokumentiert                                               |
| Konfigurations- und Versionsverwaltung | Der Code wird auf GitHub verwaltet                                                                                                  | 
| Testwerkzeuge und -prozesse            | JUnit im Annotationsstil sowohl für inhaltliche Richtigkeit als auch für Integrationstests und die Einhaltung von Effizienzvorgaben |

## 2.3 Konventionen

| Randbedingung      | Erläuterungen, Hintergrund                                                                                                   |
|--------------------|------------------------------------------------------------------------------------------------------------------------------|
| Sprache            | Der Code wird komplett in Englisch verfasst und die Dokumentation in Deutsch.                                                |
| Coding conventions | Der Code wird nach den Code conventions von [kotlinlang.org](https://kotlinlang.org/docs/coding-conventions.html) formatiert |
| DDD                | Das Projekt wird frei nach DDD umgesetzt. Einzelne Domänen bekommen ein eigenes Modul.                                       |