package fbdb.app.core;

import fbdb.app.entities.*;

import javax.persistence.EntityManager;

public class Engine {
    private EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run(){
        this.entityManager.getTransaction().begin();

        PlayerStatistic ps = new PlayerStatistic();
        Game game = new Game();
        Player player = new Player();

        Team chelsea = new Team();
        Team liv = new Team();

        chelsea.setName("Chelsea");
        liv.setName("Liverpool");

        player.setTeam(chelsea);
        player.setName("hazard");

        game.setHomeTeam(chelsea);
        game.setAwayTeam(liv);

        game.setHomeGoals(5);
        game.setAwayGoals(0);
        StatisticID statisticID = new StatisticID();
        statisticID.setGame(game);
        statisticID.setPlayer(player);

        ps.setId(statisticID);
        this.entityManager.persist(game);
        this.entityManager.persist(player);
        this.entityManager.persist(chelsea);
        this.entityManager.persist(liv);

        PlayerStatistic ps2 = new PlayerStatistic();
        ps2.setId(statisticID);
        ps2.setScoredGoals(game.getHomeGoals() + game.getAwayGoals());

        this.entityManager.persist(ps);
        this.entityManager.persist(ps2);
        this.entityManager.getTransaction().commit();
    }
}
