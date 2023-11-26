package business;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "reward")
public class Reward implements Serializable {

    @ManyToMany(mappedBy = "rewards", cascade = CascadeType.DETACH)
    private List<PaymentAccount> paymentAccounts;

    @Id
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String rewardId;
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String rewardName;
    @Column(columnDefinition = "varchar(20)", nullable = false)
    private String rewardType;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int costPoint;

    public List<PaymentAccount> getCurrentAccounts() {
        return paymentAccounts;
    }

    public void setCurrentAccounts(List<PaymentAccount> paymentAccounts) {
        this.paymentAccounts = paymentAccounts;
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public String getRewardType() {
        return rewardType;
    }

    public void getRewardType(String rewardType) {
        this.rewardType = rewardType;
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
