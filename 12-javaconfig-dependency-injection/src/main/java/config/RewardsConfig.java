package config;

import javax.sql.DataSource;

/**
 * TODO-00: In this lab, you are going to exercise the following:
 * - Creating Spring configuration class 创建一个Spring的配置类
 * - Defining bean definitions within the configuration class 在配置类中定义对象
 * - Specifying the dependency relationships among beans 在对象中指定依赖关系
 * - Injecting dependencies through constructor injection 通过构造方法注入依赖关系
 * - Creating Spring application context in the test code 在测试代码中创建Spring应用程序上下文
 *   (WITHOUT using Spring testContext framework)
 *
 * TODO-01: Make this class a Spring configuration class 使这个类变成Spring的配置类
 * - Use an appropriate annotation. 使用一个合适的注解
 *
 * TODO-02: Define four empty @Bean methods, one for the reward-network and three for the repositories.
 * 定义4个空的@Bean方法,一个为了奖励网络，3个为了数据库
 * - The names of the beans should be:
 *   - rewardNetwork
 *   - accountRepository
 *   - restaurantRepository
 *   - rewardRepository
 *
 * TODO-03: Inject DataSource through constructor injection 通过构造方法注入DataSource
 * - Each repository implementation has a DataSource
 *   property to be set, but the DataSource is defined
 *   elsewhere (TestInfrastructureConfig.java), so you
 *   will need to define a constructor for this class
 *   that accepts a DataSource parameter.
 * - As it is the only constructor, @Autowired is optional.
 *
 * TODO-04: Implement each @Bean method to contain the code
 *          needed to instantiate its object and set its dependencies
 *          实现每个@Bean方法以包含实例化其对象和设置其依赖项所需的代码
 * - You can create beans from the following implementation classes
 *   - rewardNetwork bean from RewardNetworkImpl class
 *   - accountRepository bean from JdbcAccountRepository class
 *   - restaurantRepository bean from JdbcRestaurantRepository class
 *   - rewardRepository bean from JdbcRewardRepository class
 * - Note that return type of each bean method should be an interface
 *   not an implementation.
 */

public class RewardsConfig {

	// Set this by adding a constructor.
	private DataSource dataSource;

}
