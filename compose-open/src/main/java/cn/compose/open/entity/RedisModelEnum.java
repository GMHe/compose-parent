package cn.compose.open.entity;

/**
 * redis工作模式
 *
 * @author： ouyu
 * @version： 2019年11月14日
 * @since： 2019年11月14日
 */
public enum RedisModelEnum {

    /**
     * 单机模式
     */
    SINGEL("0", "单机模式"),

    /**
     * 哨兵模式
     */
    SENTINEL("1", "哨兵模式"),

    /**
     * 集群模式
     */
    CLUSTER("2", "集群");

    private String model;
    private String desc;

    RedisModelEnum(String model, String desc) {
        this.model = model;
        this.desc = desc;
    }

    public String getModel() {
        return this.model;
    }

    public String getDesc() {
        return this.desc;
    }
}
