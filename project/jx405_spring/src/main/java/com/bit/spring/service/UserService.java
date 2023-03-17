package com.bit.spring.service;

import com.bit.spring.model.UserCustomDetails;
import com.bit.spring.model.UserDTO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final String NAMESPACE = "mapper.UserMapper";
    private SqlSession session;
    private BCryptPasswordEncoder passwordEncoder;  // password는 데이터베이스에 인코딩되어 저장되어야한다. (시큐리티에서 막음)

    @Autowired
    public UserService(SqlSession session, BCryptPasswordEncoder passwordEncoder) {

        this.session = session;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO auth(UserDTO attempt) {
        // mapper의 userMapper.auth 에 parameter를 (UserDTO)attempt를 주고 설정해둔 result로 userDTO를 받는다
        return session.selectOne(NAMESPACE + ".auth", attempt);
    }

    public boolean validate(String username) {
        return session.selectOne(NAMESPACE + ".validate", username) == null;
    }

    public boolean register(UserDTO attempt) {
        if (validate(attempt.getUsername())) {
            session.insert(NAMESPACE + ".register", attempt);
            return true;
        } else {
            return false;
        }
    }

//    public void update(UserDTO attempt) {
//        session.update(NAMESPACE + ".update", attempt);
//    }
//
//    public void delete(int id) {
//        session.delete(NAMESPACE + ".delete", id);
//    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDTO user = session.selectOne(NAMESPACE + ".validate", s);
        if (user != null) {
            return new UserCustomDetails(user);
        }
        return null;
    }

    public List<UserDTO> selectAll(){
        return session.selectList(NAMESPACE+".selectAll");
    }

    public void update(UserDTO userDTO){
        session.update(NAMESPACE+".update", userDTO);
    }
    public void encrypt(){
        for(UserDTO u : selectAll()){
            u.setPassword(passwordEncoder.encode(u.getPassword()));
            update(u);
        }
    }


}
