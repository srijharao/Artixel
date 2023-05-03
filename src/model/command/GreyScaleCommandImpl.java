package model.command;

import java.util.HashMap;
import java.util.Map;

import exception.ValidationException;
import model.image.Image;
import model.strategy.Operator;


/**
 * Implementation of IGreyScaleCommand. Initializes command map for GreyScale and executes
 * respective transformation by applying operation on the image.
 */
public class GreyScaleCommandImpl implements IGreyScaleCommand {

    private final Map<String, Operator> greyScaleCommandMap;

    /**
     * This class represents an implementation of the IGreyScaleCommand interface,
     * which contains methods for converting an image to grayscale.
     * It maintains a map of grayscale transformation objects with their names as keys.
     */
    public GreyScaleCommandImpl() {
        greyScaleCommandMap = new HashMap<>();
        greyScaleCommandMap.put("red-component", new GreyScaleRedTransform());
        greyScaleCommandMap.put("green-component", new GreyScaleGreenTransform());
        greyScaleCommandMap.put("blue-component", new GreyScaleBlueTransform());
        greyScaleCommandMap.put("luma-component", new GreyScaleLumaTransform());
        greyScaleCommandMap.put("intensity-component", new GreyScaleIntensityTransform());
        greyScaleCommandMap.put("value-component", new GreyScaleValueTransform());
    }

    @Override
    public Image execute(Image image, String component) throws ValidationException {
        if (greyScaleCommandMap.containsKey(component)) {
            return greyScaleCommandMap.get(component).applyOperator(image);
        } else {
            throw new ValidationException("Invalid component");
        }
    }


}
