package ucl.comp0022.team2.helper;

import redis.clients.jedis.Jedis;

public class RedisHelper {
    private static final String ip = "127.0.0.1"; //172.17.0.3
    private static final int port = 6379;

    /**
     * Getting Jedis
     * @return Jedis object
     */
    public static Jedis getJedis() {
        Jedis jedis = null;
        try {
            jedis = new Jedis(ip, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jedis;
    }

    /**
     * Closing Jedis
     * @param jedis Jedis object
     */
    public static void closeJedis(Jedis jedis) {
        if(jedis != null) {
            try {
                jedis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
