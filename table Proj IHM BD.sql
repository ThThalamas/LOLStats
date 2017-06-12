Create or replace Procedure ajouterJoueur
( varchar pseudo,varchar rank, number mmr)
declare
  number i;
begin
  select count(*) from joueur into i;
  insert into Joueur values(i++,pseudo,rank,mmr);
  
end ajouterJoueur;