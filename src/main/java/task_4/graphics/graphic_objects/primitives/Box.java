package task_4.graphics.graphic_objects.primitives;

import task_4.graphics.geometry.points.Point;
import task_4.graphics.graphic_objects.polygons.RealPolygon;
import task_4.graphics.lighting.ColorLight;

import java.util.*;


public class Box
    extends GraphicPrimitive
{
    private final double width;
    private final double height;
    private final double depth;
    private final Map<DirectionSide, Side> sides = new HashMap<>();
    {
        for (DirectionSide directionSide : DirectionSide.values()) {
            sides.put(directionSide, new Side());
        }
    }

    public Box(double width, double height, double depth) {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    public Side getSide(DirectionSide directionSide) {
        return sides.get(directionSide);
    }

    @Override
    public Set<RealPolygon> getPolygons() {
        double w = width / 2;
        double h = height / 2;
        double d = depth / 2;

        Point ftl = new Point(-w, h, -d);  // front top left
        Point ftr = new Point(w, h, -d);  // front top right
        Point fbl = new Point(-w, -h, -d);  // front bottom left
        Point fbr = new Point(w, -h, -d);  // front bottom right
        Point btl = new Point(-w, h, d);  // back top left
        Point btr = new Point(w, h, d);  // back top right
        Point bbl = new Point(-w, -h, d);  // back bottom left
        Point bbr = new Point(w, -h, d);  // back bottom right

        RealPolygon front = new RealPolygon(ftl, ftr, fbr, fbl);
        RealPolygon top = new RealPolygon(ftl, ftr, btr, btl);
        RealPolygon bottom = new RealPolygon(fbl, fbr, bbr, bbl);
        RealPolygon left = new RealPolygon(ftl, btl, bbl, fbl);
        RealPolygon right = new RealPolygon(ftr, btr, bbr, fbr);
        RealPolygon behind = new RealPolygon(btl, btr, bbr, bbl);

        front.setColor(sides.get(DirectionSide.FRONT).color);
        front.setFill(sides.get(DirectionSide.FRONT).isFill);
        top.setColor(sides.get(DirectionSide.TOP).color);
        top.setFill(sides.get(DirectionSide.TOP).isFill);
        bottom.setColor(sides.get(DirectionSide.BOTTOM).color);
        bottom.setFill(sides.get(DirectionSide.BOTTOM).isFill);
        left.setColor(sides.get(DirectionSide.LEFT).color);
        left.setFill(sides.get(DirectionSide.LEFT).isFill);
        right.setColor(sides.get(DirectionSide.RIGHT).color);
        right.setFill(sides.get(DirectionSide.RIGHT).isFill);
        behind.setColor(sides.get(DirectionSide.BEHIND).color);
        behind.setFill(sides.get(DirectionSide.BEHIND).isFill);

        return Set.of(front, top, bottom, left, right, behind);
    }


    public enum DirectionSide {
        FRONT,
        TOP,
        BOTTOM,
        LEFT,
        RIGHT,
        BEHIND
    }


    public static class Side {
        private ColorLight color = new ColorLight(0, 0, 0);
        private boolean isFill = true;

        private Side() {}

        public void setColor(ColorLight color) {
            this.color = color;
        }

        public void setIsFill(boolean isFill) {
            this.isFill = isFill;
        }
    }
}
