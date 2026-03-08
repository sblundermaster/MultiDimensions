package MultiDimensions.FirstVersion.DimOne;

public class ObjectZero {
    double cord;
    ObjectOne parent;
    public ObjectZero(ObjectOne parent, double cord) {
        this.parent = parent;
        this.cord = cord;
    }
    public ObjectZero(ObjectOne parent) {
        new ObjectZero(parent, 0.0f);
    }
}