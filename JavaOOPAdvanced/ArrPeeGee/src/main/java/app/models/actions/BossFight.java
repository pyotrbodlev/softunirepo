package app.models.actions;

import app.contracts.Action;
import app.contracts.Targetable;
import app.models.Config;

import java.util.Comparator;
import java.util.List;

public class BossFight implements Action {

    @Override
    public String executeAction(List<Targetable> participants) {

        String error = "There should be at least 1 participant for boss fight!";
        String noBoss = "Invalid boss.";


        if (!participants.get(0).getClass().getSimpleName().equals("Boss")) {
            return noBoss;
        }

        if (participants.size() < 2) {
            return error;
        }


        StringBuilder sb = new StringBuilder();
        int i = 1;

        Targetable boss = participants.get(0);

        while (true) {
            if (!boss.isAlive()) {
                break;
            }

            if (i > participants.size() - 1) {
                i = 1;
            }

            Targetable player = participants.get(i);

            if (!player.isAlive()) {
                i++;
                continue;
            }

            boolean allParticipantsIsAlive = false;
            for (Targetable participant : participants) {
                if (participant.isAlive()) {
                    allParticipantsIsAlive = true;
                }
            }

            if (!allParticipantsIsAlive) {
                break;
            }

            boss.attack(player);
            player.attack(boss);

//            boss.takeDamage(player.getDamage());
//
//            if (boss.isAlive()) {
//                player.takeDamage(boss.getDamage());
//            } else {
//                boss.giveReward(player);
//                break;
//            }

            i++;
        }

        if (boss.isAlive()) {
            sb.append("Boss has slain them all!");
            boss.levelUp();
        } else {
            sb.append(String.format("%s  has been slain by:", boss.getName()));
            participants.sort(Comparator.comparing(Targetable::getName));

            for (Targetable participant : participants.subList(1, participants.size())) {
                participant.levelUp();
                participant.receiveReward(Config.BOSS_INDIVIDUAL_REWARD);
                sb.append(System.lineSeparator());
                sb.append(participant.toString());
            }

        }

        return sb.toString();
    }


}
