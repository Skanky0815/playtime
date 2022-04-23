# PlayTime

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Skanky0815_playtime&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=Skanky0815_playtime)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=Skanky0815_playtime&metric=coverage)](https://sonarcloud.io/summary/new_code?id=Skanky0815_playtime)

PlayTime ist eine Community Platform für Pen&Paper, Tabletop und Boardgames Spieler. Die Spieler haben über PlayTime die
Möglichkeit sich zu Spielgruppen zu finden und Spielesessions zu planen.

## User Stories
Die Stories bilden gleichzeitig auch Kontexte/Module inder Software wieder.

- [x] [Registrierung](doku/userStories/registration.md) (*Benutzer anlegen und Password vergeben*)
- [ ] [Benutzereinstellungen](doku/userStories/userSettings.md) (*E-Mai-Adresse oder Password änder und Benutzer löschen*)
- [ ] [Spielerprofil verwalten](doku/userStories/playerProfile.md) 
- [ ] [Spielebibliothek](doku/userStories/gameLibrary.md)
- [ ] [Spielgruppe verwalten](doku/userStories/playerGroups.md)
- [ ] [Verfügbarkeitszeiten verwalten](doku/userStories/slotManagement.md)
- [ ] [Spielsitzung verwalten](doku/userStories/appointments.md)
- [ ] [Neuigkeiten übersicht](doku/userStories/newsFeed.md) (*Neuigkeiten zu Spiele oder Erweiterungen, Spielgruppen und Events*)
- [ ] [Spieler eigenschaften](doku/userStories/playerStats.md) (*Statistiken zu Genre, Häufigkeit, usw*)

## Architektur

1. [Einführung und Ziele](doku/architecture/1_Einfuehrung_Ziele.md)
2. [Randbedingung](doku/architecture/2_Randbedingung.md)
3. [Kontextabgrenzung](doku/architecture/3_Kontextabgrenzung.md)
4. [Lösungsstrategie](doku/architecture/4_Loesungsstrategie.md)
5. [Bausteinsicht](doku/architecture/5_Bausteinsicht.md)
6. [Laufzeitsicht](doku/architecture/6_Laufzeitsicht.md)
7. [Verteilungssicht](doku/architecture/7_Verteilungssicht.md)
8. [Querschnittliche Konzepte](doku/architecture/8_Querschnittliche_Konzepte.md)
9. [Entwurfsentscheidungen](doku/architecture/9_Entwurfsentscheidungen.md)
10. [Qualitätsanforderung](doku/architecture/10_Qualitaetsanforderung.md)
11. [Risiken und technische Schulden](doku/architecture/11_Risiken_technische_Schulden.md)
12. [Glossar](doku/architecture/12_Glossar.md)

## DevSetup

Um das System lokal zu starten, muss zu erst `$ docker compose up -d` ausgeführt werden im anschluss kan die Applikation mit dem _local_ Profil gestartet werden. 


### Keycloak 
Zugang zum [Keycloak](http://localhost:8081/auth/admin/master/console) backend:
- **Username**: admin
- **Password**: admin

### MailHog
Zugang zum [MailHog](http://localhost:8025/#)

### Anforderungen

- Docker
- JDK