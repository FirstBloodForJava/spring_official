package rewards.internal;

import rewards.Dining;
import rewards.RewardConfirmation;
import rewards.RewardNetwork;
import rewards.internal.account.Account;
import rewards.internal.account.AccountRepository;
import rewards.internal.restaurant.Restaurant;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;

/**
 * Rewards an Account for Dining at a Restaurant.
 * 
 * The sole Reward Network implementation. This object is an application-layer service responsible for coordinating with
 * the domain-layer to carry out the process of rewarding benefits to accounts for dining.
 * 唯一的奖励网络实现。该对象是负责协调的应用层服务域层对餐饮账户进行奖励福利的过程。
 * 
 * Said in other words, this class implements the "reward account for dining" use case.
 * 换句话说，这个类实现了“用餐奖励帐户”用例。
 *
 * TODO-00: In this lab, you are going to exercise(练习) the following:
 * - Understanding internal operations that need to be performed to implement
 *   "rewardAccountFor" method of the "RewardNetworkImpl" class
 *   理解需要执行以实现的内部操作RewardNetworkImpl类的"rewardAccountFor"方法
 * - Writing test code using stub implementations of dependencies  使用依赖项的存根实现编写测试代码
 * - Writing both target code and test code without using Spring framework 在不使用Spring框架的情况下编写目标代码和测试代码
 *
 * TODO-01: Review the Rewards Application document (Refer to the lab document)
 * TODO-02: Review project dependencies (Refer to the lab document)
 * TODO-03: Review Rewards Commons project (Refer to the lab document)
 * TODO-04: Review RewardNetwork interface and RewardNetworkImpl class below
 * TODO-05: Review the RewardNetworkImpl configuration logic (Refer to the lab document)
 * TODO-06: Review sequence diagram (Refer to the lab document)
 */
public class RewardNetworkImpl implements RewardNetwork {

	private AccountRepository accountRepository;

	private RestaurantRepository restaurantRepository;

	private RewardRepository rewardRepository;

	/**
	 * Creates a new reward network.
	 * @param accountRepository the repository for loading accounts to reward 用于加载帐户奖励的存储库
	 * @param restaurantRepository the repository for loading restaurants that determine how much to reward 加载餐馆的存储库，决定奖励多少
	 * @param rewardRepository the repository for recording a record of successful reward transactions 用于记录成功奖励事务记录的存储库
	 */
	public RewardNetworkImpl(AccountRepository accountRepository, RestaurantRepository restaurantRepository,
			RewardRepository rewardRepository) {
		this.accountRepository = accountRepository;
		this.restaurantRepository = restaurantRepository;
		this.rewardRepository = rewardRepository;
	}

	public RewardConfirmation rewardAccountFor(Dining dining) {
		// TODO-07: Write code here for rewarding an account according to
		//          the sequence diagram in the lab document 在这里编写代码奖励一个帐户根据实验文件中的序列图
		//
		// TODO-08: Return the corresponding reward confirmation 返回相应的奖励确认

		// 获取账号信息 dining中存在用户编号 通过用户编号查找他的受益人信息
		Account userAccount = accountRepository.findByCreditCard(dining.getCreditCardNumber());

		// 餐厅回款比例信息 根据餐厅编号，查找出餐饮的返现比例
		Restaurant userRestaurant = restaurantRepository.findByMerchantNumber(dining.getMerchantNumber());

		// 餐厅奖赏确定
		RewardConfirmation rewardConfirmation = rewardRepository.confirmReward(
				userAccount.makeContribution(userRestaurant.calculateBenefitFor(userAccount,dining)), dining);
		return rewardConfirmation;
	}
}