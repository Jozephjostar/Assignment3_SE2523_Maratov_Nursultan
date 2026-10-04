public class Circle extends Shape {
    private final int radius;

    public Circle(String id, Renderer renderer, int radius) {
        super(id, renderer);
        this.radius = radius;
    }

    public Circle(String id, Renderer renderer) {
        this(id, renderer, 2);
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public String execute() {
        return renderer.renderCircle(radius);
    }
}
