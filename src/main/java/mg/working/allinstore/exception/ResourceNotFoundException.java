package mg.working.allinstore.exception;

/**
 * Exception levée lorsqu'une ressource est introuvable en base de données.
 * Utilisable pour toutes les entités de l'application.
 *
 * Exemples d'utilisation :
 *   throw new ResourceNotFoundException(Article.class, id);
 *   throw new ResourceNotFoundException(Famille.class, idFamille);
 */
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final Object resourceId;

    /**
     * Constructeur principal : génère automatiquement un message clair.
     *
     * @param resourceClass la classe de l'entité introuvable (ex: Article.class)
     * @param resourceId    l'identifiant recherché
     */
    public ResourceNotFoundException(Class<?> resourceClass, Object resourceId) {
        super(String.format("%s introuvable avec l'id : %s",
                resourceClass.getSimpleName(), resourceId));
        this.resourceName = resourceClass.getSimpleName();
        this.resourceId = resourceId;
    }

    /**
     * Constructeur alternatif pour un message personnalisé libre.
     *
     * @param message message d'erreur explicite
     */
    public ResourceNotFoundException(String message) {
        super(message);
        this.resourceName = null;
        this.resourceId = null;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Object getResourceId() {
        return resourceId;
    }
}
