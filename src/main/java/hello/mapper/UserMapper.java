package hello.mapper;

import hello.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM UserInfo")
    List<UserInfo> findAll();

    @Select("SELECT * FROM UserInfo WHERE id = #{id}")
    UserInfo findById(@Param("id") Long id);

    @Select("SELECT * FROM UserInfo WHERE name = #{name}")
    UserInfo findByName(@Param("name") String name);

    @Select("DELETE FROM UserInfo WHERE id = #{id}")
    UserInfo deleteById(@Param("id") Long id);

    @Insert("INSERT INTO UserInfo(name, age) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Insert("UPDATE UserInfo SET name = #{name}, age = #{age} WHERE id = #{id}")
    int update(@Param("id") Long id, @Param("name") String name, @Param("age") Integer age);
}