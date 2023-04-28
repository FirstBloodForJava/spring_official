package rewards;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import rewards.internal.RewardNetworkImplRequiresNew;
import rewards.internal.account.AccountRepository;
import rewards.internal.restaurant.RestaurantRepository;
import rewards.internal.reward.RewardRepository;


@Configuration
@Import(SystemTestConfig.class)
public class SystemTestRequiresNewConfig {

	// 为什么注入两个RewardNetwork对象，@Autowired注入的RewardNetwork对象会是一个被JDK代理的对象?因为这个对象的方法被@Transaction注解了
	@Bean
	public RewardNetwork rewardNetwork(
		AccountRepository accountRepository,
		RestaurantRepository restaurantRepository,
		RewardRepository rewardRepository ) {
		return new RewardNetworkImplRequiresNew(
			accountRepository, 
			restaurantRepository, 
			rewardRepository);
	}
	
}
