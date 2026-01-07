package com.alura.forumhub.infra.security;

import com.alura.forumhub.domain.usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Recupera o token do cabeçalho
        var tokenJWT = recuperarToken(request);

        if (tokenJWT != null) {
            try {
                // 2. Valida o token e extrai o e-mail (subject)
                var subject = tokenService.getSubject(tokenJWT);

                // 3. Busca o usuário no banco de dados
                var usuario = repository.findByEmail(subject);
                if (usuario != null) {
                    // 4. Cria o objeto de autenticação do Spring Security
                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );

                    // 5. Autentica o usuário no contexto da requisição
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (RuntimeException e) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Token JWT inválido ou expirado.");
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.replace("Bearer ", "").trim();
        }
        return null;
    }
}