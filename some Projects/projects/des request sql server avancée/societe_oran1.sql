/*Fragmentation horizontale primaire de ClientInfo en fonction de la valeur de l'attribut ville*/
/*client*/
create table clientoran AS SELECT * from sys.clientinfo"@lien" where ville='oran';
SELECT * FROM clientoran ;
/*facture*/
create table factureoran as SELECT * from sys.FACTURE"@lien"  f   where EXISTS (select * from clientoran  c  where c.ID_CLIENT=f.ID_CLIENT and c.ville='oran');
CREATE table factureoran as SELECT * FROM SYS.factureoran"@lien";
SELECT  * FROM factureoran ;
/*lingefacture*/
CREATE table lingefactureoran as SELECT * FROM SYS.lingefactureoran"@lien";
SELECT  * FROM lingefactureoran ;