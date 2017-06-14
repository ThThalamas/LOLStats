Create or replace Procedure ajouterJoueur
( pseudo varchar2,rang varchar2, mmr number)
is
  i number;
begin
  select count(*)into i from joueur;
  i := i + 1;
  insert into Joueur(idjoueur,pseudo,rank,mmr) values(i,pseudo,rang,mmr);
  
end ajouterJoueur;
/







delete joueur where pseudo='matheo';
delete joueur where idjoueur = 13;
select * from joueur;

select pseudo, role from joueur,partie,champion 
where pseudo = 'IAMUTO' 
and joueur.idjoueur = partie.idjoueur 
and partie.idchamp = champion.idchamp
group by pseudo having count(idchamp) = (select count(idchamp) from joueur,partie,champion 
where pseudo = 'IAMUTO' 
and joueur.idjoueur = partie.idjoueur 
and partie.idchamp = champion.idchamp
group by pseudo having max(count(idchamp)));

select role from 
(select pseudo, role from joueur join partie using(idjoueur) join champion using(idchamp)
where pseudo='IAMUTO') 
group by role having count(*)= 
(select max(count(*)) from (select pseudo,role from joueur join partie using(idjoueur) join champion using(idchamp)
where pseudo='IAMUTO') group by role);

select count(*) from joueur, partie where joueur.idjoueur = partie.idjoueur and pseudo ='IAMUTO';

//demander comment récuperer un nom de champ qui est le plus utiliser c'est à dire max(count())
select * from (select pseudo, role from joueur join partie using(idjoueur) join champion using(idchamp)
where pseudo='IAMUTO');


select count(*) from joueur, partie where joueur.idjoueur = partie.idjoueur and pseudo ='IAMUTO';

select pseudo,role from 
(select pseudo, role from joueur join partie using(idjoueur) join champion using(idchamp)
) 
group by role having count(*)= 
(select max(count(*)) from (select pseudo,role from joueur join partie using(idjoueur) join champion using(idchamp)
) group by role);


select distinct role from champion;


select nom from champion where role='Adc';

select count(*) from joueur,partie where joueur.idjoueur=partie.idjoueur and gagnant=1 and pseudo='IAMUTO';