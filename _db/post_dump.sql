--DBS projekt init script
CREATE TABLE trieda(
id SERIAL NOT NULL PRIMARY KEY,
nazov VARCHAR(20),
cena FLOAT
);

CREATE TABLE druh_zasielky (
id SERIAL NOT NULL PRIMARY KEY,
nazov VARCHAR(30),
cena FLOAT
);

CREATE TABLE sluzba (
id SERIAL NOT NULL PRIMARY KEY,
trieda_id BIGINT REFERENCES trieda(id) ON DELETE CASCADE,
druh_id BIGINT REFERENCES druh_zasielky(id) ON DELETE CASCADE,
poistenie BOOLEAN NOT NULL DEFAULT FALSE,
potvrdenie_o_doruceni BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE stav_zasielky (
id SERIAL NOT NULL PRIMARY KEY,
stav VARCHAR(20)
);

CREATE TABLE zakaznik (
id SERIAL NOT NULL PRIMARY KEY,
meno VARCHAR(20),
priezvisko VARCHAR(20),
ulica_cislo VARCHAR(50),
mesto VARCHAR(20),
psc VARCHAR(5)
);

CREATE TABLE pozicia (
id SERIAL NOT NULL PRIMARY KEY,
pozicia VARCHAR(20),
plat INT
);

CREATE TABLE pobocka (
id SERIAL NOT NULL PRIMARY KEY,
nazov VARCHAR(50),
ulica_cislo VARCHAR(50),
mesto VARCHAR(20)
);

CREATE TABLE zamestnanec (
id SERIAL NOT NULL PRIMARY KEY,
meno VARCHAR(20),
priezvisko VARCHAR(20),
pozicia_id BIGINT REFERENCES pozicia(id) ON DELETE CASCADE,
pobocka_id BIGINT REFERENCES pobocka(id) ON DELETE CASCADE,
ulica_cislo VARCHAR(50),
mesto VARCHAR(20)
);

CREATE TABLE zasielka (
id SERIAL NOT NULL PRIMARY KEY,
sluzba_id BIGINT REFERENCES sluzba(id) ON DELETE CASCADE,
podana DATE,
hmotnost INT,
pobocka_id BIGINT REFERENCES pobocka(id) ON DELETE CASCADE,
zamestnanec_id BIGINT REFERENCES zamestnanec(id) ON DELETE CASCADE,
odosielatel_id BIGINT REFERENCES zakaznik(id) ON DELETE CASCADE,
adresat_id BIGINT REFERENCES zakaznik(id) ON DELETE CASCADE,
stav_zasielky_id BIGINT REFERENCES stav_zasielky(id) ON DELETE CASCADE,
cena FLOAT
);

INSERT INTO trieda (nazov, cena) VALUES
('štandard', 0),
('1. trieda', 0.5);

INSERT INTO druh_zasielky(nazov, cena) VALUES
('listová zásielka', 0.5),
('balík', 4),
('expres zásielka', 6);

INSERT INTO stav_zasielky (id, stav) VALUES
(1, 'podaná'),
(2, 'na ceste'),
(3, 'doručená');

INSERT INTO pozicia(pozicia, plat) VALUES
('riaditeľ', 1200),
('pracovník', 600),
('vedúci oddelenia', 900);

INSERT INTO pobocka(id, nazov, ulica_cislo, mesto) VALUES
(1, 'Bratislava Staré mesto', 'Letná 10', 'Bratislava'),
(2, 'Bratislava Nové mesto', 'Svätoplukova 4', 'Bratislava'),
(3, 'Bratislava Petržalka', 'Masarykova 9', 'Bratislava');

INSERT INTO zamestnanec(id, meno, priezvisko, pozicia_id, pobocka_id, ulica_cislo, mesto) VALUES
(1, 'Katarína', 'Pekná', 2, 1, 'Košícká 85', 'Senec'),
(2, 'František', 'Stromokocúr', 2, 2, 'Levočská 24', 'Bratislava'),
(3, 'Martin', 'Imrich', 2, 3, 'Kvetná 4', 'Malacky'),
(4, 'Jakub', 'Pullmann', 2, 1, 'Sabinovská 45', 'Žiar nad Hronom');

INSERT INTO zakaznik(meno, priezvisko, ulica_cislo, mesto, psc) VALUES
('Jozef', 'Vaľko', '285', 'Chminianska Nová Ves', '08233'),
('Rastislav', 'Polivka', 'SNP 13', 'Trenčín', '91101'),
('Dominik', 'Kapitančík', 'Prešovská 45', 'Košice', '04001'),
('Jakub', 'Snopko', 'Listová 9', 'Bratislava', '84105'),
('Ondrej', 'Velecký', 'Lipová 98', 'Žilina', '01009'),
('Marko', 'Ondruš', 'MDŽ 25', 'Spišské Vlachy', '05361'),
('Zdeno', 'Konrád', 'Jarková 4', 'Prešov', '08001'),
('Hana', 'Kuntová', 'Lipová 14', 'Levice', '93405'),
('Martin', 'Beblavý', 'Brodná 4', 'Košice', '04001'),
('Štefan', 'Vaľko', '285', 'Chminianska Nová Ves', '08233');

INSERT INTO sluzba(trieda_id, druh_id, poistenie, potvrdenie_o_doruceni) VALUES
(2, 3, true, false),
(1, 1, false, true),
(2, 2, true, false),
(1, 2, true, true),
(2, 3, true, false);

INSERT INTO zasielka(sluzba_id, podana, hmotnost, pobocka_id, zamestnanec_id, odosielatel_id, adresat_id, stav_zasielky_id, cena) VALUES
(1, '9.4.2016', 125, 2, 2, 1, 2, 2, 7.95),
(2, '5.4.2016', 75, 1, 4, 3, 4, 2, 1.21),
(3, '5.4.2016', 115, 1, 4, 5, 6, 2, 5.95),
(4, '10.4.2016', 142, 2, 2, 7, 8, 1, 5.95),
(5, '8.4.2016', 145, 3, 3, 9, 10, 2, 5.95);

