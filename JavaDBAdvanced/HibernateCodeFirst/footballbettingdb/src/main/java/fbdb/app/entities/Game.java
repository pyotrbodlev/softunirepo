package fbdb.app.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "games")
public class Game {
    private int id;
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;
    private Date dateOfGame;
    private double homeTeamBetRate;
    private double awayTeamBetRate;
    private double drawBetRate;
    private Round round;
    private Competition competition;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    @ManyToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    @Column(name = "home_goals")
    public int getHomeGoals() {
        return homeGoals;
    }

    public void setHomeGoals(int homeGoals) {
        this.homeGoals = homeGoals;
    }

    @Column(name = "away_goals")
    public int getAwayGoals() {
        return awayGoals;
    }

    public void setAwayGoals(int awayGoals) {
        this.awayGoals = awayGoals;
    }

    @Column(name = "date_of_game")
    public Date getDateOfGame() {
        return dateOfGame;
    }

    public void setDateOfGame(Date dateOfGame) {
        this.dateOfGame = dateOfGame;
    }

    @Column(name = "home_team_bet_rate")
    public double getHomeTeamBetRate() {
        return homeTeamBetRate;
    }

    public void setHomeTeamBetRate(double homeTeamBetRate) {
        this.homeTeamBetRate = homeTeamBetRate;
    }

    @Column(name = "away_team_bet_rate")
    public double getAwayTeamBetRate() {
        return awayTeamBetRate;
    }

    public void setAwayTeamBetRate(double awayTeamBetRate) {
        this.awayTeamBetRate = awayTeamBetRate;
    }

    @Column(name = "draw_bet_rate")
    public double getDrawBetRate() {
        return drawBetRate;
    }

    public void setDrawBetRate(double drawBetRate) {
        this.drawBetRate = drawBetRate;
    }

    @ManyToOne
    @JoinColumn(name = "round_id", referencedColumnName = "id")
    public Round getRound() {
        return round;
    }

    public void setRound(Round round) {
        this.round = round;
    }

    @ManyToOne
    @JoinColumn(name = "competition_id", referencedColumnName = "id")
    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}
