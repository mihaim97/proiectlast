package com.ubb.ro.proiect1.security.util;

public class JwtProperties {
    public static final String SECRET = "PrOiect2022Postuniv"; // cheie secreta pentru criptare si decriptare
    public static final int EXPIRATION_TIME = 29000000;// 6700000; // 10 zile cica
    public static final String TOKEN_PREFIX = "Bearer "; // prefix pentru token ex {Authorization: Bearer <token>}
    public static final String HEADER = "Authorization"; // parte din token si anume header
    public static final String HEADER_USER = "Authority"; // parte din token si anume header
    public static final String ROLES = "Granted"; // parte din token si anume header

}
