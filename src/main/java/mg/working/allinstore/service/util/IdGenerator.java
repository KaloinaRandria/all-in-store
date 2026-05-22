package mg.working.allinstore.service.util;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class IdGenerator {

    private final JdbcTemplate jdbcTemplate;

    public IdGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Génère un ID avec préfixe + séquence PostgreSQL
     *
     * @param prefix        Préfixe (ex: "DM", "CMD", "FAC")
     * @param sequenceName  Nom de la séquence dans PostgreSQL (ex: "demande_seq")
     * @param padding       Nombre de chiffres (ex: 3 → DM001, 5 → DM00001)
     * @return ID généré
     */
    public String generateId(String prefix, String sequenceName, int padding) {

        if (prefix == null || sequenceName == null || padding <= 0) {
            throw new IllegalArgumentException("Prefix, sequenceName et padding doivent être valides");
        }


        Long nextVal = jdbcTemplate.queryForObject(
                "SELECT nextval(?)",
                Long.class,
                sequenceName
        );

        if (nextVal == null) {
            throw new RuntimeException("Impossible de récupérer la valeur de la séquence : " + sequenceName);
        }


        String formattedNumber = String.format("%0" + padding + "d", nextVal);
        return prefix + formattedNumber;
    }

    // Méthodes de convenance (surcharges)
    public String generateId(String prefix, String sequenceName) {
        return generateId(prefix, sequenceName, 5);
    }
}
