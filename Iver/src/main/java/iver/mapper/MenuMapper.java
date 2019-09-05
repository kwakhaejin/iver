package iver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import iver.dto.MenuDto;

@Mapper
/*@Mapper 인터페이스는 Mybatis의 SqlSessionTemplate을 이용하여 Sql-Mapping을 수행합니다. 
즉, @Mapper 인터페이스가 어떤 메소드를 선언하고 있건 간에, 기본적인 동작 방식은 동일하다는 얘기입니다. 
그래서 이 때 사용되는 것이 바로 Java Proxy입니다. Mybatis는 MapperProxy라는 InvocationHandler 구현체를 이용하여 @Mapper 인터페이스의 구현체를 동적으로 생성합니다. */
public interface MenuMapper {
	List<MenuDto> getMenuList(MenuDto menu) throws Exception;
}
