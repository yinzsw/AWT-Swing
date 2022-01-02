package top.yinzsw.dics.launch.ui.component;

import javax.swing.*;
import java.awt.*;

public class BoxGenerator extends Box {
    int vGap, hGap;
    Component[][] components;

    public BoxGenerator(Component[][] components, int vGap, int hGap) {
        super(BoxLayout.Y_AXIS);
        this.components = components;
        this.vGap = vGap;
        this.hGap = hGap;
        formatVBox();
    }


    private void formatVBox() {
        add(createVerticalStrut(vGap));
        for (Component[] hComponents : components) {
            Box hBox = createHorizontalBox();
            hBox.add(createHorizontalStrut(hGap));
            for (Component hComponent : hComponents) {
                hBox.add(hComponent);
                hBox.add(createHorizontalStrut(hGap));
            }
            add(hBox);
            add(createVerticalStrut(vGap));
        }
    }
}
