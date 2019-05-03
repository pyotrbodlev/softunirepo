package fbdb.app.entities;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StatisticID implements Serializable {
    private Game game;
    private Player player;

    public StatisticID() {
    }

    public StatisticID(Game game, Player player) {
        this.game = game;
        this.player = player;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StatisticID)) return false;
        StatisticID that = (StatisticID) o;
        return Objects.equals(getGame().getId(), that.getGame().getId()) &&
                Objects.equals(getPlayer().getId(), that.getPlayer().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGame().getId(), getPlayer().getId());
    }
}
