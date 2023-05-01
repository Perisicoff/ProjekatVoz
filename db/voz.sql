DROP SCHEMA IF EXISTS voz;
CREATE SCHEMA voz DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE voz;

CREATE TABLE vozovi (
	id BIGINT AUTO_INCREMENT,
    broj VARCHAR(100) NOT NULL,
    naziv VARCHAR(100) NOT NULL,
    datumIVreme DATETIME NOT NULL,
    cena DOUBLE NOT NULL,
    brMesta INT NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE karte (
	id BIGINT AUTO_INCREMENT,
    vozId BIGINT NOT NULL,
    datumIVreme DATETIME NOT NULL,
    kupac VARCHAR(100) NOT NULL,
    razred INT NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY(vozId) REFERENCES vozovi(id),
    FOREIGN KEY(vozId) REFERENCES vozovi(id) ON DELETE CASCADE
);

# radi lakšeg debug-ovanja, broj mesta u svim vozovima je mali
# vozovi: broj, naziv, datum i vreme polaska, cena karte, broj mesta
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (1, '2405', 'Regio', '2022-05-06 07:43', 600.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (2, '543', 'Soko', '2022-05-06 13:00', 1000.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (3, '749', 'Brzi', '2022-05-06 21:30', 800.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (4, '541', 'Soko', '2022-05-07 07:04', 1000.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (5, '2409', 'Regio', '2022-05-07 10:30', 600.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (6, '749', 'Brzi', '2022-05-07 21:30', 800.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (7, '2411', 'Regio', '2022-05-08 11:30', 600.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (8, '553', 'Soko', '2022-05-08 14:00', 1000.00, 5);
INSERT INTO vozovi (id, broj, naziv, datumIVreme, cena, brMesta) VALUES (9, '749', 'Brzi', '2022-05-08 21:30', 800.00, 5);

# PRVI, DRUGI i sl. su pseudoreference i odnose se na vozove iznad
# karte: voz, datum i vreme prodaje, kupac, razred
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (1 , '2022-05-01 10:00', 'Kupac 1', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (2 , '2022-05-01 12:00', 'Kupac 2', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (3 , '2022-05-01 16:00', 'Kupac 4', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (1 , '2022-05-02 11:00', 'Kupac 5', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (4 , '2022-05-02 13:00', 'Kupac 6', 2);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (1 , '2022-05-02 15:00', 'Kupac 7', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (5 , '2022-05-02 17:00', 'Kupac 8', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (2 , '2022-05-03 12:00', 'Kupac 9', 2);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (7 , '2022-05-03 14:00', 'Kupac 10', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (2 , '2022-05-03 16:00', 'Kupac 11', 2);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (8 , '2022-05-03 18:00', 'Kupac 12', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (2 , '2022-05-04 13:00', 'Kupac 13', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (9 , '2022-05-04 15:00', 'Kupac 14', 2);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (3 , '2022-05-04 17:00', 'Kupac 15', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (4 , '2022-05-04 19:00', 'Kupac 16', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (3 , '2022-05-05 14:00', 'Kupac 17', 2);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (5 , '2022-05-05 16:00', 'Kupac 18', 2);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (3 , '2022-05-05 18:00', 'Kupac 19', 1);
INSERT INTO karte (vozId, datumIVreme,  kupac, razred) VALUES (6 , '2022-05-05 20:00', 'Kupac 20', 1);