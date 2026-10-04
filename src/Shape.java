public abstract class Shape {
    protected final String id;
    protected Renderer renderer;

    public Shape(String id, Renderer renderer) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("Shape ID cannot be null or blank");
        }
        if (renderer == null) {
            throw new IllegalArgumentException("Renderer cannot be null");
        }
        this.id = id;
        this.renderer = renderer;
    }

    public String getId() {
        return id;
    }

    public Renderer getRenderer() {
        return renderer;
    }

    public void setImplementation(Renderer renderer) {
        if (renderer == null) {
            throw new IllegalArgumentException("Renderer cannot be null");
        }
        this.renderer = renderer;
    }

    public abstract String execute();
}
