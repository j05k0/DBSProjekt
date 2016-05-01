SELECT * from zamestnanec

SELECT meno, priezvisko, plat
FROM zamestnanec z
JOIN pozicia p ON p.id = z.pozicia_id

SELECT z.meno, z.priezvisko, p.nazov, z.pobocka_id 
FROM zamestnanec z
JOIN pobocka p ON p.id = z.pobocka_id
WHERE p.nazov LIKE 'Bratislava Nové mesto'

SELECT * from zakaznik
SELECT * from zamestnanec
SELECT * FROM trieda

SELECT * FROM trieda

SELECT cena
FROM druh_zasielky d
WHERE d.id = 1

SELECT cena FROM trieda WHERE id = 2

SELECT cena FROM druh_zasielky WHERE id = 1

select id from zamestnanec where meno = 'Jakub' AND priezvisko = 'Pullmann'

select * from stav_zasielky

select * from zasielka
select * from sluzba
select * from druh_zasielky
select * from pobocka
select * from zakaznik
select * from stav_zasielky
select * from trieda
select meno || ' ' || priezvisko from zamestnanec

SELECT z.id, d.nazov AS druh, z.podana, p.nazov AS pobocka, odos.meno || ' ' || odos.priezvisko AS odosielatel, adre.meno || ' ' || adre.priezvisko AS adresat
FROM zasielka z
JOIN sluzba s ON s.id = z.sluzba_id
JOIN druh_zasielky d ON d.id = s.druh_id
JOIN pobocka p ON p.id = z.pobocka_id
JOIN zakaznik odos ON odos.id = z.odosielatel_id 
JOIN zakaznik adre ON adre.id = z.adresat_id

SELECT d.nazov AS druh, t.nazov AS trieda, s.potvrdenie_o_doruceni, s.poistenie, z.podana, z.hmotnost, p.nazov AS pobocka, zam.meno || ' ' || zam.priezvisko AS zamestnanec, 
odos.meno || ' ' || odos.priezvisko AS odosielatel, odos.ulica_cislo AS odosielatel_ulica, odos.mesto AS odosielatel_mesto, odos.psc AS odosielatel_psc, 
adre.meno || ' ' || adre.priezvisko AS adresat, adre.ulica_cislo AS adresat_ulica, adre.mesto AS adresat_mesto, adre.psc AS adresat_psc, 
stav.stav, z.cena
FROM zasielka z
JOIN sluzba s ON s.id = z.sluzba_id
JOIN druh_zasielky d ON d.id = s.druh_id
JOIN pobocka p ON p.id = z.pobocka_id
JOIN trieda t ON t.id = s.trieda_id
JOIN zamestnanec zam ON zam.id = z.zamestnanec_id
JOIN zakaznik odos ON odos.id = z.odosielatel_id 
JOIN zakaznik adre ON adre.id = z.adresat_id
JOIN stav_zasielky stav ON stav.id = z.stav_zasielky_id
WHERE z.id = 2

SELECT * FROM druh_zasielky
SELECT * FROM pobocka
select * from zamestnanec

SELECT z.hmotnost, z.pobocka_id, z.zamestnanec_id, s.druh_id, s.trieda_id, s.potvrdenie_o_doruceni, s.poistenie
FROM zasielka z
JOIN sluzba s ON s.id = z.sluzba_id
WHERE z.id = 2

select * from zasielka
select * from zakaznik
select * from stav_zasielky

delete from zasielka where id = 7

SELECT z.id, d.nazov AS druh, z.podana, p.nazov AS pobocka, odos.meno || ' ' || odos.priezvisko AS odosielatel, adre.meno || ' ' || adre.priezvisko AS adresat
FROM zasielka z
JOIN sluzba s ON s.id = z.sluzba_id
JOIN druh_zasielky d ON d.id = s.druh_id
JOIN pobocka p ON p.id = z.pobocka_id
JOIN zakaznik odos ON odos.id = z.odosielatel_id 
JOIN zakaznik adre ON adre.id = z.adresat_id
WHERE z.pobocka_id = 1 AND z.zamestnanec_id = 4 AND z.stav_zasielky_id = 2

DELETE z.*, s.*, odos.*, adre.* 
FROM zasielka z
JOIN sluzba s ON s.id = z.sluzba_id 
JOIN zakaznik odos ON odos.id = z.odosielatel_id
JOIN zakaznik adre ON adre.id = z.adresat_id
WHERE z.id = 8

SELECT * from zasielka

SELECT zam.meno || ' ' || zam.priezvisko AS zamestnanec, count(z.id) AS pocet
FROM zasielka z
RIGHT JOIN zamestnanec zam ON z.zamestnanec_id = zam.id
GROUP BY zam.meno, zam.priezvisko
ORDER BY pocet DESC

SELECT sluzba_id, odosielatel_id, adresat_id FROM zasielka WHERE id = 8

DELETE FROM sluzba WHERE id = ?