# Obligatorisk oppgave 3 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende student:
* Einar D. Amundsen, s325864, s325864@oslomet.no


# Oppgavebeskrivelse

For å løse Oppgave 1 så startet jeg med å se på koden som den refererte til, og skrev den mer eller mindre av kompendiet.
Da den skulle benytte seg av forelder sørget jeg for at ved hvert steg, så blir en variabel oppdatert som parent, og når hjelpenoden er null, 
så lages det en ny node med par som forelder, og settes inn som par.venstre hvis den er mindre , eller til høyre hvis den er større eller duplikat.
Deretter så oppdateres antall, for å ha riktig størelse på treet, og return true for å henvise at innlegging var velykket.

For Oppgave 2 så sjekker jeg først om verdien er null, eller om treet er tomt, i så fall så returnes 0.
Deretter, så lager jeg en int som skal returnes (startverdi 0), og en hjelpenode for å gå over treet.
Sjekker node-verdi mot verdien vi leter etter, om den (verdien vi leter etter) er større, eller mindre enn node.verdi.
Hvis den er mindre går vi ned venstresiden, er den større, så går vi ned høyresiden, hvis den er hverken eller er det verdien vi leter etter, så 
counter oppdateres, og vi går ned til høyre da leggInn() fra oppgave1 setter duplikater til høyre, så om vi finner flere, så er de den veien.
Til slutt, så returner vi counter for å få antall ganger den ønskete verdien blir gjentatt i treet.

For Oppgave 3 så går vi over treet, for å finne postorden.
Da vi skal bare finne den første, så går vi inn i for å finne det leaf'et som er lengst til venstre.
Dette gjør vi ved å sjekke at om hjelpenoden vi er i har et venstre"barn", hvis den har så lar vi hjelpenoden vår bli samme som venstrebarnet.
Hvis den ikke har det, så sjekker vi om den har et høyrebarn, og har den det, går vi ned dit, og sjekker igjen om det er et venstrebarn..
Når vi kommer til en som har hverken venstre, eller høyre barn, så har vi kommet til den endelige verdien, og vi returnerer den verdien.

For nestePostorden så slet jeg lenge med å få til, da jeg trodde jeg skulle gjøre oppgave 4, så jeg prøvde veldig lenge å finne en metode for å få neste og neste og neste.
Hva jeg gjorde, var å sjekke aller først om forelderen til til verdien var null, i så fall, så var p == rot, og vi var kommet til slutten av treet.
Deretter, så sjekket jeg om p hadde et "søsken", altså om dens forelder hadde et annet barn. Hvis den hadde det, så fortsatte sjekket jeg derifra først mot venstre, så mot høyre. til jeg fant en ny ende.
Hvis den ikke hadde et søsken, så var foreldren den neste i rekken.

For Oppgave 4

For Oppgave 5
