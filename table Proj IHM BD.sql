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

delete joueur where idjoueur = 13;
select * from joueur;

select nom from joueur,partie,champion 
where pseudo = 'IAMUTO' 
and joueur.idjoueur = partie.idjoueur 
and partie.idchamp = champion.idchamp
group by nom having max(partie.idchamp)=
(select count(partie.idchamp) from joueur, partie where pseudo='IAMUTO' and joueur.idjoueur = partie.idjoueur );
(select count(partie.idchamp) from joueur, partie where pseudo='IAMUTO' and joueur.idjoueur = partie.idjoueur );

//demander comment récuperer un nom de champ qui est le plus utiliser c'est à dire max(count())
