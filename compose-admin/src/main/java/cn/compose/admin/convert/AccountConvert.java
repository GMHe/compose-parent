package cn.compose.admin.convert;

import cn.compose.admin.dto.AccountDTO;
import cn.compose.admin.dto.UserDTO;
import cn.compose.admin.entity.Account;
import cn.compose.admin.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface AccountConvert {
    AccountConvert INSTANCE = Mappers.getMapper(AccountConvert.class);

    AccountDTO toDTO(Account account);

    List<AccountDTO> collectionCvt(List<Account> entities);


}