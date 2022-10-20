package lt.knygynas.Knygu.rezervavimas.security;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService  {
}
