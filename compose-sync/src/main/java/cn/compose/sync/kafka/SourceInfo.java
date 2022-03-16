package cn.compose.sync.kafka;

import cn.compose.sync.entity.Source;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author hgm
 * @date 2020/12/15
 */
@Setter
@Getter
@ToString
public class SourceInfo extends Source {

    private Map<String, String> keyMappingMap;
}
