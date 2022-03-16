package cn.compose.alert.config;

import com.contive.plugin.did.DistributedId;
import com.contive.plugin.did.DistributedIdFactory;
import com.contive.plugin.did.Group;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @ClassName
 * @Description 生成分布式ID
 * @author llz
 * @Date 2020年3月23日 下午4:20:45
 * @version 1.0.0
 */
@Configuration
public class DistributedIdConfig {
	@Value("${distributedId.address}")
	private String ipPorts;
	@Value("${distributedId.group}")
	private String groupName;

	@Bean
	public DistributedId distributedId() {
		// 初始化分布式ID生成器
		// 参数为zookeeper地址，集群多个地址使用英文符,分割
		DistributedIdFactory.initZookeeper(ipPorts);
		// 同样业务标识的组，分布式环境下生成的ID是不重复的
		return DistributedIdFactory.get(new Group(groupName));
	}
}