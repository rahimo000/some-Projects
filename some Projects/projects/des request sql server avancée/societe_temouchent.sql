*Fragmentation horizontale primaire de ClientInfo en fonction de la valeur de l'attribut ville*/
/*client*/
create table clienttemouchent AS SELECT * from sys.clientinfo"@lien" where ville='ain_temouchent';
SELECT * FROM clienttemouchent ;
/*facture*/
CREATE table facturetemouchent as SELECT * FROM SYS.facturetemouchent"@lien";
SELECT  * FROM facturetemouchent ;
/*lingefacture*/
CREATE table lingefacturetemouchent as SELECT * FROM SYS.lingefacturetemouchent"@lien";
SELECT  * FROM lingefacturetemouchent ;