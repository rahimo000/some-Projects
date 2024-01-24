/*Fragmentation verticale de Client en deux fragments: ClientInfo (Informations personelles) ClientChiffreAF (Chiffre d’Affaire)*/
create table ClientInfo as select id_client , nom , prenom , ville from client;
create table ClientChiffreAF as select id_client , chchiffre_affaire from client;

/*Fragmentation horizontale */
/*factureoran*/
create table factureoran AS SELECT * from FACTURE  f where EXISTS (select * from clientinfo  c  where c.ID_CLIENT=f.ID_CLIENT and c.ville='oran');
/*lingefactureoran*/
create table lingefactureoran AS SELECT * from lingefacture  lf where EXISTS (select * from factureoran  fo  where fo.ID_fact=lf.ID_FACT_FK );


/*facturetlemcen*/
create table facturetlemcen AS SELECT * from FACTURE  f where EXISTS (select * from clientinfo  c  where c.ID_CLIENT=f.ID_CLIENT and c.ville='tlemcen');
/*lingefacturetlemcen*/
create table lingefacturtlemcen AS SELECT * from lingefacture  lf where EXISTS (select * from facturetlemcen  fo  where fo.ID_fact=lf.ID_FACT_FK );
SELECT * from lingefacturtlemcen ;

/*facturealger*/
create table facturealger AS SELECT * from FACTURE  f where EXISTS (select * from clientinfo  c  where c.ID_CLIENT=f.ID_CLIENT and c.ville='alger');
/*lingefacturealger*/
create table lingefacturealger AS SELECT * from lingefacture  lf where EXISTS (select * from facturealger  fo  where fo.ID_fact=lf.ID_FACT_FK );
SELECT * from lingefacturealger ;

/*facturetemouchent*/
create table facturetemouchent AS SELECT * from FACTURE  f where EXISTS (select * from clientinfo  c  where c.ID_CLIENT=f.ID_CLIENT and c.ville='ain_temouchent');
/*lingefacturealger*/
create table lingefacturetemouchent AS SELECT * from lingefacture  lf where EXISTS (select * from facturetemouchent  fo  where fo.ID_fact=lf.ID_FACT_FK );
SELECT * from lingefacturetemouchent ;












/*Select Cl.Nom, F.date from Client Cl, Facture F
Where Cl.NumClt = F.NumClt and Cl.ville = ‘oran’ or Cl.ville = ‘alger’*/
Select Cl.Nom, f.datee from Client Cl, Facture F Where Cl.id_client = f.id_client and Cl.ville = ‘oran’ or Cl.ville = ‘alger’ ;


