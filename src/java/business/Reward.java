package business;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Reward implements Serializable {

    @ManyToMany(mappedBy = "rewards",cascade = CascadeType.DETACH)
    private List<CurrentAccount> currentAccounts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rewardId;
    @Column(columnDefinition = "varchar(20)")
    private String rewardName;
    private int price;
    private int costPoint;

    public List<CurrentAccount> getCurrentAccounts() {
        return currentAccounts;
    }

    public void setCurrentAccounts(List<CurrentAccount> currentAccounts) {
        this.currentAccounts = currentAccounts;
    }

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCostPoint() {
        return costPoint;
    }

    public void setCostPoint(int costPoint) {
        this.costPoint = costPoint;
    }
}
