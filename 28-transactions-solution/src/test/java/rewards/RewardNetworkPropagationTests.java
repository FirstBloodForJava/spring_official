package rewards;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A system test that demonstrates how propagation settings affect transactional execution.
 * 演示传播设置如何影响事务执行的系统测试。
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={SystemTestRequiresNewConfig.class})
public class RewardNetworkPropagationTests {

	/**
	 * The object being tested.
	 */
	//@Qualifier("rewardNetwork1")
	@Autowired
	private RewardNetwork rewardNetwork;

	/**
	 * A template to use for test verification
	 */
	private JdbcTemplate template;

	/**
	 * Manages transaction manually 手动管理事务
	 */
	@Autowired
	private PlatformTransactionManager transactionManager;

	// 在找到DataSource对象的情况下注入对象
	@Autowired
	public void initJdbcTemplate(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	@Test
	public void testPropagation() {
		// Open a transaction for testing 打开一个事务进行测试
		//TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		Dining dining = Dining.createDining("100.00", "1234123412341234", "1234567890");
		try{
			RewardConfirmation rewardConfirmation = rewardNetwork.rewardAccountFor(dining);
		}catch (NullPointerException e){
			System.out.println("test: " + e);
		}

		// Rollback the transaction test transaction 回滚事务测试事务

		// 为了测试外面的事务是否会影响RewardNetwork类rewardAccountFor方法的事务
		String updateSql = "update T_ACCOUNT_BENEFICIARY SET SAVINGS = ? where ACCOUNT_ID = ? and NAME = ?";
		template.update(updateSql, new BigDecimal(4), 0, "Annabelle");

		//transactionManager.rollback(status);

		String sql = "select SAVINGS from T_ACCOUNT_BENEFICIARY where NAME = ?";
		assertEquals(Double.valueOf(4.00), template.queryForObject(sql, Double.class, "Annabelle"));
		assertEquals(Double.valueOf(4.00), template.queryForObject(sql, Double.class, "Corgan"));
	}
}