package exercise.mapper;

import exercise.dto.UserCreateDTO;
import exercise.dto.UserDTO;
import exercise.model.User;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(
        uses = {JsonNullableMapper.class, ReferenceMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class UserMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "passwordDigest", source = "passwordDigest")
    public abstract User map(UserCreateDTO model);
    @Mapping(target = "email", source = "email")
    @Mapping(target = "passwordDigest", ignore = true)
    public abstract UserDTO map(User model);

    @BeforeMapping
    public void encryptPassword(UserCreateDTO data) {
        var password = data.getPasswordDigest();
        data.setPasswordDigest(passwordEncoder.encode(password));
    }
}
