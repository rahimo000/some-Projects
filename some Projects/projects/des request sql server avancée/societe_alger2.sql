*Fragmentation horizontale primaire de ClientInfo en fonction de la valeur de l'attribut ville*/
/*client*/
create table clientalger AS SELECT * from sys.clientinfo"@lien" where ville='alger';
SELECT * FROM clientalger ;
/*facture*/
CREATE table facturealger as SELECT * FROM SYS.facturealger"@lien";
SELECT  * FROM facturealger ;
/*lingefacture*/
CREATE table lingefacturealger as SELECT * FROM SYS.lingefacturealger"@lien";
SELECT  * FROM lingefacturealger ;