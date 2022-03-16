package cn.compose.admin.convert;

import cn.compose.admin.dto.UserDTO;
import cn.compose.admin.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface UserConvert {
    UserConvert INSTANCE = Mappers.getMapper(UserConvert.class);

    @Mapping(source = "name", target = "userName")
    UserDTO toDTO(User user);

    List<UserDTO> collectionCvt(List<User> entities);


}