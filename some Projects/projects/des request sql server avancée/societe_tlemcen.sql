/*client*/
create table clienttlemcen as select * FROM SYS.clientinfo"@lien" where ville='tlemcen' ;
SELECT * FROM clienttlemcen ;
/*facture*/
CREATE table facturetlemcen as SELECT * FROM SYS.facturetlemcen"@lien";
SELECT  * FROM facturetlemcen ;
/*lingefacture*/
CREATE table lingefacturetlemcen as SELECT * FROM SYS.lingefacturtlemcen"@lien";
SELECT  * FROM lingefacturetlemcen ;