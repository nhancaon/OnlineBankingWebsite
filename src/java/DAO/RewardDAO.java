package DAO;

import business.Reward;
import java.util.List;

public class RewardDAO extends JpaDAO<Reward> implements GenericDAO<Reward> {

    @Override
    public Reward create(Reward t) {
        return super.create(t);
    }

    @Override
    public Reward get(Object id) {
        return super.find(Reward.class, id);
    }

    @Override
    public void delete(Object id) {
        super.delete(Reward.class, id);

    }

    @Override
    public List<Reward> listAll() {
        return super.findWithNamedQuery("");
    }

    @Override
    public long count() {

        return super.countWithNamedQuery("");
    }

    public List<Reward> getAllRewards() {

        List<Reward> result = super.findWithNamedQuery("SELECT r FROM Reward r");

        if (!result.isEmpty()) {
            return result;
        }

        return null;

    }

    public List<Reward> getRewardsByType(String rewardType) {

        List<Reward> result = super.findWithNamedQuery("SELECT r FROM Reward r WHERE r.rewardType = :rewardType", "rewardType", rewardType);

        if (!result.isEmpty()) {
            return result;
        }

        return null;
    }
    
    public Reward redeemReward() {
        
        return null;
    }

}
