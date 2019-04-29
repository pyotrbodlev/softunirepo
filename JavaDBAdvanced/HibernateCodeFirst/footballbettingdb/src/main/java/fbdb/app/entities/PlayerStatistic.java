package fbdb.app.entities;

import javax.persistence.*;

@Entity
@Table( name = "player_statistics")
public class PlayerStatistic {
    //Game, Player, Scored Goals, Player Assists, Played Minutes During Game, (PK = Game + Player)
    private StatisticID id;
    private int scoredGoals;
    private int playerAssists;
    private int playedMinutesDuringGame;

    @EmbeddedId
    public StatisticID getId() {
        return id;
    }

    public void setId(StatisticID id) {
        this.id = id;
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
