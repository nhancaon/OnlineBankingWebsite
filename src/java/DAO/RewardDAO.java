package DAO;

import business.PaymentAccount;
import business.Reward;
import DAO.PaymentAccountDAO;
import Exception.HandleException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Reward addReward(String rewardName, int price, int costPoint, String type) {

        Reward rewardEntity = new Reward();
        rewardEntity.setRewardId(generateUniqueId());
        rewardEntity.setRewardName(rewardName);
        rewardEntity.setCostPoint(costPoint);
        rewardEntity.setPrice(price);
        rewardEntity.setRewardType(type);
        create(rewardEntity);
        return null;
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

    public Reward getRewardsById(String rewardId) {

        List<Reward> result = super.findWithNamedQuery("SELECT r FROM Reward r WHERE r.rewardId = :rewardId", "rewardId", rewardId);

        if (!result.isEmpty()) {
            return result.get(0);
        }

        return null;
    }

    public Reward findExistingReward(String accountNumber, String rewardId) {

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("accountNumber", accountNumber);
        parameters.put("rewardId", rewardId);

        List<Reward> rewards = super.findWithNamedQuery(
                "SELECT r FROM PaymentAccount pa JOIN pa.rewards r WHERE pa.accountNumber = :accountNumber AND r.rewardId = :rewardId",
                parameters);

        return !rewards.isEmpty() ? rewards.get(0) : null;
    }

    public Reward redeemReward(String rewardId, String defaultAccountNumber) throws HandleException {

        PaymentAccountDAO paymentAccountDAO = new PaymentAccountDAO();

        PaymentAccount currentAccount = paymentAccountDAO.findExistingPaymentAccount(defaultAccountNumber);
        Reward existingReward = findExistingReward(defaultAccountNumber, rewardId);

        Reward reward = getRewardsById(rewardId);

        if (currentAccount != null && reward != null) {
            if (existingReward == null) {
                if (currentAccount.getRewardPoint() >= reward.getCostPoint()) {
                    currentAccount.setRewardPoint(currentAccount.getRewardPoint() - reward.getCostPoint());

                    List<Reward> rewards = currentAccount.getRewards();
                    if (rewards == null) {
                        rewards = new ArrayList<>();
                    }
                    rewards.add(reward);
                    currentAccount.setRewards(rewards);

                    paymentAccountDAO.update(currentAccount);

                    return reward;
                } else {
                    throw new HandleException("Insufficient reward points to redeem the reward", 409);
                }             
            } else {
                throw new HandleException("You have already reedeem this reward", 409);
            }
        } else {
            throw new HandleException("Payment account or reward not found", 409);
        }

    }

}
