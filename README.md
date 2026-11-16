# Floorball Team Manager

Floorball Team Manager on Spring Bootilla toteutettu verkkosovellus salibandyjoukkueiden ja pelaajien hallintaan.
Sovelluksessa käyttäjä voi lisätä, tarkastella, muokata ja poistaa joukkueita sekä pelaajia. 
Pelaajat voidaan liittää joukkueisiin, ja jokaisen joukkueen pelaajia voidaan tarkastella erikseen.


## Ominaisuudet

- Joukkueiden lisääminen, tarkasteleminen, muokkaaminen ja poistaminen
- Pelaajien lisääminen, tarkasteleminen, muokkaaminen ja poistaminen
- Pelaajien liittäminen joukkueisiin
- Yksittäisen joukkueen pelaajien tarkasteleminen
- Lomakkeiden tietojen validointi
- Käyttäjien kirjautuminen ja käyttöoikeuksien hallinta
- REST-rajapinta joukkueiden ja pelaajien tietojen hakemiseen


## Käytetyt teknologiat

- **Java 17** – Sovelluksen ohjelmointikieli
- **Spring Boot** – Sovelluksen kehitysalusta
- **Spring MVC** – HTTP-pyyntöjen käsittely
- **Thymeleaf** – HTML-sivujen muodostaminen
- **Spring Data JPA** – Tietokannan käsittely
- **H2 Database** – Sovelluksen tietokanta
- **Spring Security** – Kirjautuminen ja käyttöoikeudet
- **Bean Validation** – Lomakkeiden tietojen validointi
- **HTML ja CSS** – Käyttöliittymän rakenne ja ulkoasu
- **Maven** – Riippuvuuksien ja projektin hallinta


## Sovelluksen käynnistäminen

Sovelluksen käynnistäminen edellyttää Java 17:n asentamista.

1. Kloonaa projekti GitHubista:

   ```bash
   git clone https://github.com/ristikartano/floorball-team-manager.git
   ```

2. Siirry projektin kansioon:

   ```bash
   cd floorball-team-manager
   ```

3. Määritä kirjautumisen salasanat ympäristömuuttujiin. Esimerkiksi Windows PowerShellissä:

   ```powershell
   $env:USER_PASSWORD="oma-kayttajasalasana"
   $env:ADMIN_PASSWORD="oma-yllapitosalasana"
   ```

4. Käynnistä sovellus Maven Wrapperilla:

   ```powershell
   .\mvnw.cmd spring-boot:run
   ```

5. Avaa selaimessa:

   http://localhost:8080/teams

Sovellus käyttää H2-muistitietokantaa, joten tallennetut tiedot eivät säily sovelluksen uudelleenkäynnistyksen jälkeen.


## Käyttäjäroolit ja käyttöoikeudet

Sovelluksessa on kaksi käyttäjäroolia:

- **USER** – Voi tarkastella pelaajia, joukkueita ja yksittäisen joukkueen pelaajalistaa.
- **ADMIN** – Voi tarkastella, lisätä, muokata ja poistaa pelaajia sekä joukkueita. Ylläpitäjällä on myös pääsy REST-rajapintoihin ja H2-tietokannan hallintakonsoliin, jos konsoli on otettu käyttöön.

Sovellukseen kirjaudutaan Spring Securityn kirjautumislomakkeella.

Käyttäjätunnukset ovat `user` ja `admin`. Salasanat määritellään ympäristömuuttujilla.


## REST-rajapinnat

Sovelluksessa on kaksi REST-rajapintaa, jotka palauttavat tietoja JSON-muodossa.

- **GET /api/players** – Palauttaa kaikki pelaajat ja heidän joukkueidensa tiedot.
- **GET /api/teams** – Palauttaa kaikki joukkueet.

REST-rajapintojen käyttäminen edellyttää ADMIN-roolia.

Rajapinnat mahdollistavat vain tietojen hakemisen. Pelaajien ja joukkueiden lisääminen, muokkaaminen ja poistaminen tapahtuu sovelluksen käyttöliittymän kautta.
