package fbdb.app.entities;

import javax.persistence.*;

@Entity
@Table(name = "player_statistics")
public class PlayerStatistic {
    //Game, Player, Scored Goals, Player Assists, Played Minutes During Game, (PK = Game + Player)
    private int id;
    private Game game;
    private Player player;
    private int scoredGoals;
    private int playerAssists;
    private int playedMinutesDuringGame;


    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = this.game.getId() + this.player.getId();
    }

    @ManyToOne
    @JoinColumn(name = "game_id", referencedColumnName = "id")
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Column
    public int getScoredGoals() {
        return scoredGoals;
    }

    public void setScoredGoals(int scoredGoals) {
        this.scoredGoals = scoredGoals;
    }

    @Column
    public int getPlayerAssists() {
        return playerAssists;
    }

    public void setPlayerAssists(int playerAssists) {
        this.playerAssists = playerAssists;
    }

    @Column
    public int getPlayedMinutesDuringGame() {
        return playedMinutesDuringGame;
    }

    public void setPlayedMinutesDuringGame(int playedMinutesDuringGame) {
        this.playedMinutesDuringGame = playedMinutesDuringGame;
    }
}
