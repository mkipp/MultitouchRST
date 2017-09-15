package de.hsa.interaction;

import javafx.scene.shape.Shape;

/**
 * Wrapper around basic shape to enable translation, rotation and scaling
 * by multitouch.
 * 
 * @author Michael Kipp
 */
public class Touchable {

    private Shape shape;
    private double touchOffsetX;
    private double touchOffsetY;
    private int translateTouchID = -1;

    public Touchable(Shape s) {
        shape = s;

        shape.setOnRotate(e -> {
            shape.setRotate(shape.getRotate() + e.getAngle());
            e.consume();
        });
        
        shape.setOnZoom(e -> {
            shape.setScaleX(shape.getScaleX() * e.getZoomFactor());
            shape.setScaleY(shape.getScaleY() * e.getZoomFactor());
            e.consume();
        });
        
        shape.setOnTouchPressed(e -> {
            if (translateTouchID == -1) {
                touchOffsetX = e.getTouchPoint().getSceneX() - shape.getTranslateX();
                touchOffsetY = e.getTouchPoint().getSceneY() - shape.getTranslateY();
                translateTouchID = e.getTouchPoint().getId();
            }
            e.consume();
        });

        shape.setOnTouchMoved(e -> {
            if (e.getTouchPoint().getId() == translateTouchID) {
                shape.setTranslateX(e.getTouchPoint().getSceneX() - touchOffsetX);
                shape.setTranslateY(e.getTouchPoint().getSceneY() - touchOffsetY);
            }            
            e.consume();
        }
        );

        shape.setOnTouchReleased(e
                -> {
            if (e.getTouchPoint().getId() == translateTouchID) {
                translateTouchID = -1;
            }
            e.consume();
        }
        );
    }
}
