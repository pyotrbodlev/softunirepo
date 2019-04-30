package gringotts.app.core;

import gringotts.app.entities.WizardDeposits;

import javax.persistence.EntityManager;

public class Engine implements Runnable {
    private final EntityManager entityManager;

    public Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void run() {
        WizardDeposits wizardDeposits = new WizardDeposits("Pesho", "Ivanov", -4);

        this.entityManager.getTransaction().begin();
        this.entityManager.persist(wizardDeposits);
        this.entityManager.getTransaction().commit();
    }
}
