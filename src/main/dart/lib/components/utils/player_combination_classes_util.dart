part of fremad;

class PlayerWithNotes{
  Player player;
  List<PlayerNote> playerNoteList;
  
  PlayerWithNotes(this.player, this.playerNoteList);
}

class PlayerAndUserMeta{
  Player player;
  UserMeta userMeta;
  
  PlayerAndUserMeta(this.player, this.userMeta);
}