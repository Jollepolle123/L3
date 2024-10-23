import app.ImageEditor;
import app.ImageResizer;
import app.ImageRotator;
import app.ImageBrightnessAdjuster;
import app.ImageColorFilter;
import app.NegativeColorConverter;
import java.awt.image.BufferedImage;

public interface ImageAction {
    void execute(ImageEditor editor);
}

class ResizeAction implements ImageAction {

    private int width, height;

    ResizeAction(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void execute(ImageEditor editor) {
        try {
            ImageResizer resizer = new ImageResizer();
            BufferedImage resizedImage = resizer.resizeImage(editor.getImage(), this.width, this.height);
            editor.setImage(resizedImage);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

class RotateAction implements ImageAction {

    private float rotation;

    RotateAction(float rotation) {
        this.rotation = rotation;
    }

    @Override
    public void execute(ImageEditor editor) {
        try {
            ImageRotator rotator = new ImageRotator();
            BufferedImage rotatedImage = rotator.rotateImage(editor.getImage(), this.rotation);
            editor.setImage(rotatedImage);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

class BrightnessAction implements ImageAction {

    private float brightnessMultiplier;

    BrightnessAction(float brightnessMultiplier) {
        this.brightnessMultiplier = brightnessMultiplier;
    }

    @Override
    public void execute(ImageEditor editor) {
        float brightnessMultiplier;
        try {
            ImageBrightnessAdjuster brightnessAdjuster = new ImageBrightnessAdjuster();
            BufferedImage brightenedImage = brightnessAdjuster.adjustBrightness(editor.getImage(), this.brightnessMultiplier);
            editor.setImage(brightenedImage);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

class ColorFilterAction implements ImageAction {

    private Float redMultiplier;
    private Float greenMultiplier;
    private Float blueMultiplier;

    ColorFilterAction(float redMultiplier, float greenMultiplier, float blueMultiplier) {
        this.redMultiplier = redMultiplier;
        this.greenMultiplier = greenMultiplier;
        this.blueMultiplier = blueMultiplier;
    }

    @Override
    public void execute(ImageEditor editor) {
        try {
            ImageColorFilter colorFilter = new ImageColorFilter();
            BufferedImage filteredImage = colorFilter.adjustColor(editor.getImage(), this.redMultiplier, this.greenMultiplier, this.blueMultiplier);
            editor.setImage(filteredImage);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}

class InvertColorAction implements ImageAction {

    @Override
    public void execute(ImageEditor editor) {
        try {
            NegativeColorConverter colorConverter = new NegativeColorConverter();
            BufferedImage convertedImage = colorConverter.convertColors(editor.getImage());
            editor.setImage(convertedImage);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
