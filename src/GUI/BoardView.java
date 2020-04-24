package GUI;

import core.Entity;
import core.XY;

public interface BoardView {
    public Class<? extends Entity> getEntityType(int x, int y);
    public XY getSize();
}
